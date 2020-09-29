package com.ravensim.simulator.wire;

import com.google.common.collect.Table;
import com.ravensim.simulator.event.PropagationEvent;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.InvalidSignalException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import com.ravensim.simulator.util.Common;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class VirtualWireMediatorTest {

  private SimulationEngine context;
  private VirtualWireMediator virtualWireMediator;

  @BeforeEach
  void setUp() {
    context = Mockito.mock(SimulationEngine.class, Mockito.RETURNS_DEEP_STUBS);
    virtualWireMediator = new VirtualWireMediator(context);
  }

  @AfterEach
  void tearDown() {
    context = null;
    virtualWireMediator = null;
  }

  @RepeatedTest(Common.NUMBER_OF_TEST)
  void connect_addSinkToSameSourceStressTest()
      throws InterruptedException, InvalidBitWidthException {
    var source = Mockito.mock(Port.class);
    Mockito.when(context.hasStarted()).thenReturn(true);
    Runnable connect =
        () -> {
          IntStream.range(0, Common.ITERATIONS_FOR_STRESS_TEST)
              .forEach(
                  i ->
                      virtualWireMediator.connect(
                          Common.randomID(), source, Mockito.mock(Port.class)));
        };
    ExecutorService executor = Executors.newWorkStealingPool(Common.LEVEL_OF_PARALLELISM);
    for (int i = 0; i < Common.LEVEL_OF_PARALLELISM; i++) {
      executor.submit(connect);
    }
    executor.shutdown();
    executor.awaitTermination(Common.TIMEOUT, Common.TIME_UNIT);
    var virtualWire =
        (ConcurrentMap<Port, List<Port>>)
            ReflectionTestUtils.getField(virtualWireMediator, "virtualWire");
    var connections = virtualWire.get(source);
    Assertions.assertEquals(
        connections.size(), Common.ITERATIONS_FOR_STRESS_TEST * Common.LEVEL_OF_PARALLELISM);
    var idOfVirtualWire =
        (Table) ReflectionTestUtils.getField(virtualWireMediator, "idOfVirtualWire");
    Assertions.assertEquals(
        idOfVirtualWire.size(), Common.ITERATIONS_FOR_STRESS_TEST * Common.LEVEL_OF_PARALLELISM);
  }

  @Test
  void connect_sinkPortReceivesPendingEvent() throws InvalidSignalException {
    var sink = Mockito.mock(Port.class);
    var source = Mockito.mock(Port.class);
    var event = Mockito.mock(PropagationEvent.class);
    Mockito.when(context.getEventRegistry().retrieveTheLatestEventFromPort(source))
        .thenReturn(event);
    virtualWireMediator.connect(Common.randomID(), source, sink);
    Mockito.verify(sink).receive(event);
  }

  @Test
  void connect_notifiesClientIfThereIsAPendingEvent() {
    var sink = Mockito.mock(Port.class);
    var source = Mockito.mock(Port.class);
    Mockito.when(context.getEventRegistry().retrieveTheLatestEventFromPort(source))
        .thenReturn(Mockito.mock(PropagationEvent.class));
    virtualWireMediator.connect(Common.randomID(), source, sink);
    Mockito.verify(context.getMessageBroker()).add(Mockito.any());
  }

  @Test
  void connect_clientDoesNotReceiveEventIfThereExistsNoEvent() {
    var sink = Mockito.mock(Port.class);
    var source = Mockito.mock(Port.class);
    Mockito.when(context.getEventRegistry().retrieveTheLatestEventFromPort(source))
        .thenReturn(null);
    virtualWireMediator.connect(Common.randomID(), source, sink);
    Mockito.verify(context.getMessageBroker(), Mockito.times(Common.ZERO_INVOCATIONS))
        .add(Mockito.any());
  }

  @Test
  void connect_sinkPortDoesNotReceiveEventIfThereExistsNoEvent() throws InvalidSignalException {
    var sink = Mockito.mock(Port.class);
    var source = Mockito.mock(Port.class);
    Mockito.when(context.getEventRegistry().retrieveTheLatestEventFromPort(source))
        .thenReturn(null);
    virtualWireMediator.connect(Common.randomID(), source, sink);
    Mockito.verify(sink, Mockito.times(Common.ZERO_INVOCATIONS)).receive(Mockito.any());
  }

  @Test
  void transmit_allSinkPortsReceiveEvent() throws InvalidSignalException {
    List<Port> sinks = new ArrayList<>();
    for (int i = 0; i < Common.NUMBER_OF_PORTS; i++) {
      sinks.add(Mockito.mock(Port.class));
    }
    var source = Mockito.mock(Port.class);
    for (int i = 0; i < Common.NUMBER_OF_PORTS; i++) {
      virtualWireMediator.connect(Common.randomID(), source, sinks.get(i));
    }
    var event = Mockito.mock(PropagationEvent.class);
    virtualWireMediator.transmit(source, event);
    for (int i = 0; i < Common.NUMBER_OF_PORTS; i++) {
      Mockito.verify(sinks.get(i)).receive(event);
    }
  }

  @Test
  void transmit_allSinkPortsNotifyClientOfSignal() {
    // todo
  }

  @Test
  void transmit_clientIsNotNotifiedIfThereAreNoSinks() {
    var source = Mockito.mock(Port.class);
    virtualWireMediator.transmit(source, Mockito.mock(PropagationEvent.class));
    Mockito.verify(context.getMessageBroker(), Mockito.times(Common.ZERO_INVOCATIONS))
        .add(Mockito.any());
  }

  @Test
  void transmit_eventIsAddedToRegistry() {
    var source = Mockito.mock(Port.class);
    var event = Mockito.mock(PropagationEvent.class);
    virtualWireMediator.transmit(source, event);
    Mockito.verify(context.getEventRegistry()).add(source, event);
  }

  @Test
  void transmit_eventIsAddedToRegistryIfThereAreSinkPorts() {
    var source = Mockito.mock(Port.class);
    var event = Mockito.mock(PropagationEvent.class);
    virtualWireMediator.connect(Common.randomID(), source, Mockito.mock(Port.class));
    virtualWireMediator.transmit(source, event);
    Mockito.verify(context.getEventRegistry()).add(source, event);
  }
}
