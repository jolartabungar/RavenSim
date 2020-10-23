package com.ravensim.simulator.event;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.logic_gate.AndGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import com.ravensim.simulator.util.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class EventRegistryTest {

  private SimulationEngine context;
  private EventRegistry eventRegistry;

  @BeforeEach
  void setUp() {
    context = Mockito.mock(SimulationEngine.class);
    eventRegistry = new EventRegistry(context);
  }

  @AfterEach
  void tearDown() {
    context = null;
    eventRegistry = null;
  }

  @Test
  void add_doNotAddIfScheduledTimeIsNull() {
    var event = new RequestToUpdateEvent();
    event.setSource(Mockito.mock(AndGate.class));
    eventRegistry.add(event);
    var events = (BlockingQueue) ReflectionTestUtils.getField(eventRegistry, "events");
    Assertions.assertEquals(events.size(), 0);
  }

  @Test
  void add_doNotAddIfSourceIsNull() {
    var event = new RequestToUpdateEvent();
    event.setScheduledTime(0);
    eventRegistry.add(event);
    var events = (BlockingQueue) ReflectionTestUtils.getField(eventRegistry, "events");
    Assertions.assertEquals(events.size(), 0);
  }

  @Test
  void add_doNotAddEventIfScheduledTimeIsLessThanSimulationTime() {
    var simulationTime = 1;
    var event = new RequestToUpdateEvent();
    Mockito.when(context.getTime()).thenReturn(simulationTime);
    event.setSource(Mockito.mock(AndGate.class));
    event.setScheduledTime(--simulationTime);
    eventRegistry.add(event);
    var events = (BlockingQueue) ReflectionTestUtils.getField(eventRegistry, "events");
    Assertions.assertEquals(events.size(), 0);
  }

  @Test
  void add_doNotAddIfEventWasAlreadyAdded() {
    var simulationTime = 1;
    var event = new RequestToUpdateEvent();
    Mockito.when(context.getTime()).thenReturn(simulationTime);
    event.setSource(Mockito.mock(AndGate.class));
    event.setScheduledTime(simulationTime);
    eventRegistry.add(event);
    eventRegistry.retrieveTheLatestEvents(Common.TIMEOUT, Common.TIME_UNIT);
    eventRegistry.add(event);
    var events = (BlockingQueue) ReflectionTestUtils.getField(eventRegistry, "events");
    Assertions.assertEquals(events.size(), 0);
    var history = (List) ReflectionTestUtils.getField(eventRegistry, "history");
    Assertions.assertEquals(history.size(), 1);
  }

  @Test
  void add_doNotAddIfEventWasAlreadyExecuted() {
    var simulationTime = 1;
    var event = new RequestToUpdateEvent();
    Mockito.when(context.getTime()).thenReturn(simulationTime);
    event.setSource(Mockito.mock(AndGate.class));
    event.setScheduledTime(simulationTime);
    eventRegistry.add(event);
    eventRegistry.add(event);
    var events = (BlockingQueue) ReflectionTestUtils.getField(eventRegistry, "events");
    Assertions.assertEquals(events.size(), 1);
  }

  @Test
  void retrieveTheLatestEvents_onlyGetEventsWithSameScheduledTimeAsSimulationTime()
      throws InterruptedException {
    Mockito.when(context.getTime()).thenReturn(5);
    addMessages();
    var events = eventRegistry.retrieveTheLatestEvents(Common.TIMEOUT, Common.TIME_UNIT);
    Assertions.assertEquals(
        events.get().size(),
        (Common.ITERATIONS_FOR_STRESS_TEST / 10) * Common.LEVEL_OF_PARALLELISM);
  }

  private void addMessages() throws InterruptedException {
    Runnable add =
        () -> {
          IntStream.range(0, Common.ITERATIONS_FOR_STRESS_TEST)
              .forEach(
                  i -> {
                    var event = new RequestToUpdateEvent();
                    if (i % 10 == 0) {
                      event.setScheduledTime(5);
                    } else {
                      Integer scheduledTime;
                      do {
                        scheduledTime = new Random().nextInt(10);
                      } while (scheduledTime == 5);
                      event.setScheduledTime(scheduledTime);
                    }
                    try {
                      event.setSource(
                          new AndGate(
                              context,
                              List.of(new Port(context), new Port(context)),
                              new Port(context)));
                    } catch (IncompatibleBitWidthsException
                        | InvalidNumberOfPortsException
                        | InvalidBitWidthException e) {
                      e.printStackTrace();
                    }
                    eventRegistry.add(event);
                  });
        };
    ExecutorService executor = Executors.newWorkStealingPool(Common.LEVEL_OF_PARALLELISM);
    for (int i = 0; i < Common.LEVEL_OF_PARALLELISM; i++) {
      executor.submit(add);
    }
    executor.shutdown();
    executor.awaitTermination(Common.TIMEOUT, Common.TIME_UNIT);
  }

  @Test
  void retrieveTheLatestEvents_returnEmptyOptionalIfPollingTimesOut() {
    var events = eventRegistry.retrieveTheLatestEvents(0, TimeUnit.SECONDS);
    Assertions.assertTrue(events.isEmpty());
  }
}
