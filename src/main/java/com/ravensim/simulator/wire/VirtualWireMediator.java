package com.ravensim.simulator.wire;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.ravensim.simulator.event.PropagationEvent;
import com.ravensim.simulator.message_factory.WireSignalChangeMessage;
import com.ravensim.simulator.port.InvalidSignalException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class VirtualWireMediator {
  private static final Logger LOGGER =
      LogManager.getLogger(VirtualWireMediator.class.getSimpleName());
  // A virtual wire is just a mapping between a source and a sink port.
  private final ConcurrentMap<Port, List<Port>> virtualWire;
  // Cache a table to map a virtual wire to its identifier.
  private final Table<Port, Port, Integer> idOfVirtualWire;
  private final SimulationEngine context;

  public VirtualWireMediator(SimulationEngine context) {
    virtualWire = new ConcurrentHashMap<>();
    idOfVirtualWire = HashBasedTable.create();
    this.context = context;
  }

  public void connect(Integer id, Port source, Port sink) {
    // todo Ensure that there are no oscillation errors.
    // todo Ensure that the source and sink ports share the same bit width.
    List<Port> sinks;
    synchronized (virtualWire) {
      synchronized (idOfVirtualWire) {
        // Create a new virtual connection with the given identifier.
        sinks = virtualWire.get(source);
        if (sinks == null) {
          sinks = new ArrayList<>();
        }
        sinks.add(sink);
        virtualWire.put(source, sinks);
        idOfVirtualWire.put(source, sink, id);
      }
    }
    // A propagation event may have been created before the sink port was instantiated. Therefore,
    // it should check for any pending events only if the simulation has not started.
    hasPendingEvents(source, sink);
    LOGGER.info(String.format("Instantiated a new virtual wire with id: %s", id));
  }

  private void hasPendingEvents(Port source, Port sink) {
    var event = context.getEventRegistry().retrieveTheLatestEventFromPort(source);
    if (event == null) {
      // The source port has not issued a propagation event before the simulation has started.
      return;
    }
    issuePropagationEvent(source, sink, event);
  }

  private void issuePropagationEvent(Port source, Port sink, PropagationEvent event) {
    // Notify the client of the signal change.
    new WireSignalChangeMessage(context, idOfVirtualWire.get(source, sink), event.getSignal());
    try {
      sink.receive(event);
    } catch (InvalidSignalException e) {
      // todo
    }
  }

  public void transmit(Port source, PropagationEvent event) {
    var sinks = virtualWire.get(source);
    // Ensure there exists a mapping from this source to a sink.
    if (sinks != null) {
      sinks.stream().forEach(sink -> issuePropagationEvent(source, sink, event));
    } else {
      LOGGER.info(
          String.format("Failed to transmit from port %s as it has no connections", source));
    }
    // A sink may have been created after a propagation event was issued and before the simulation
    // starts. Therefore, the most recent propagation event should be stored for future sinks that
    // may be created before the simulation begins.
    context.getEventRegistry().add(source, event);
  }
}
