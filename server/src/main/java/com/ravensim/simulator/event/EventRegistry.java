package com.ravensim.simulator.event;

import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class EventRegistry implements Serializable {
  private static final Logger LOGGER = LogManager.getLogger(EventRegistry.class.getSimpleName());
  // The initial capacity the event queue.
  private static final int INITIAL_CAPACITY = 1 << 6;
  // A collection of events awaiting execution by the simulation engine.
  private final BlockingQueue<RequestToUpdateEvent> events;
  // A collection of events that have been executed by the simulation engine. The history must be
  // recorded as two or more ports associated with the same logic gate may fire a request to update
  // with the same scheduled time. However, the logic gate should only update once for a given
  // scheduled time; therefore, it must be ensured by checking the history for equivalent events.
  private final List<RequestToUpdateEvent> history;
  private final SimulationEngine context;
  private final ConcurrentMap<Port, PropagationEvent> eventsBeforeTheSimulationStarted;

  public EventRegistry(SimulationEngine context) {
    // Sort the events according to their scheduled time.
    events =
        new PriorityBlockingQueue<>(
            INITIAL_CAPACITY, Comparator.comparingInt(RequestToUpdateEvent::getScheduledTime));
    history = new CopyOnWriteArrayList<>();
    eventsBeforeTheSimulationStarted = new ConcurrentHashMap<>();
    this.context = context;
  }

  public PropagationEvent retrieveTheLatestEventFromPort(Port source) {
    return eventsBeforeTheSimulationStarted.get(source);
  }

  public Optional<List<RequestToUpdateEvent>> retrieveTheLatestEvents(long timeout, TimeUnit unit) {
    // Return an optional list of events with the same scheduled time. It will return an empty
    // optional if there is an interrupt exception while polling.
    List<RequestToUpdateEvent> toDo = new ArrayList<>();
    do {
      // Poll for the next event in the queue.
      RequestToUpdateEvent event = null;
      try {
        event = events.poll(timeout, unit);
      } catch (InterruptedException e) {
        // Do nothing.
      }
      // The event will be null if the polling times out or it is interrupted.
      if (event == null) {
        // todo Should put the events back in the queue in order to resume the simulation at a later
        // time.
        return Optional.empty();
      }
      toDo.add(event);
      history.add(event);
      // Check the queue for more events with the same scheduled time.
    } while (events.peek() != null
        && events.peek().getScheduledTime() == toDo.get(toDo.size() - 1).getScheduledTime());
    return Optional.of(toDo);
  }

  public void add(Port source, PropagationEvent event) {
    eventsBeforeTheSimulationStarted.put(source, event);
  }

  public void add(RequestToUpdateEvent event) {
    if (event.getScheduledTime() == null || event.getSource() == null) {
      return;
    }
    // Discard the event if the simulated time surpassed the scheduled time of the event
    if (event.getScheduledTime() < context.getTime()) {
      LOGGER.info(String.format("failed to add the event with %s as it is outdated", event));
      return;
    }
    // Only register the event if there does not exist or has not existed an event with the same
    // source and scheduled time in the priority queue.
    var isSuccess = false;
    synchronized (events) {
      synchronized (history) {
        // These operations must be performed atomically, or else one thread may add an event after
        // another thread invoked contains.
        // Insert the event only if the event is unique.
        if (!(events.contains(event) || history.contains(event))) {
          do {
            // The insertion may fail due to the size restrictions of the queue; therefore, it
            // should keep
            // trying until it is successful.
            isSuccess = events.offer(event);
          } while (!isSuccess);
        }
      }
    }
    String response = String.format("[%s] to the priority queue", event);
    if (isSuccess) {
      LOGGER.info(String.format("Successfully added %s", response));
    } else {
      LOGGER.info(String.format("Failed to add %s", response));
    }
  }
}
