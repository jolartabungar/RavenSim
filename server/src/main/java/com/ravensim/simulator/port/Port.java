package com.ravensim.simulator.port;

import com.ravensim.simulator.event.Observable;
import com.ravensim.simulator.event.PortObserver;
import com.ravensim.simulator.event.PropagationEvent;
import com.ravensim.simulator.event.RequestToUpdateEvent;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Port extends BitSetAdapter implements Observable<PortObserver>, Serializable {
  public static final int DEFAULT_BIT_WIDTH = 1;
  private static final Logger LOGGER = LogManager.getLogger(Port.class.getSimpleName());
  private final List<PortObserver> observers;
  private final SimulationEngine context;

  public Port(SimulationEngine context) throws InvalidBitWidthException {
    this(context, DEFAULT_BIT_WIDTH);
  }

  public Port(SimulationEngine context, int bitWidth) throws InvalidBitWidthException {
    super(bitWidth);
    this.context = context;
    observers = new ArrayList<>();
  }

  public void receive(PropagationEvent event) throws InvalidSignalException {
    LOGGER.info(String.format("Received [%s] at port %s", event, this));
    setSignal(event.getSignal());
    // Notify all listeners that the signal of this port has changed. Typically, this is meant to
    // notify the logic gate that this port is associated with to update.
    observers.forEach(listener -> listener.update(new RequestToUpdateEvent()));
  }

  public void transmit() {
    // Transmit a new propagation event with the current signal of this port.
    var event = new PropagationEvent(getSignal());
    LOGGER.info(String.format("Transmitting [%s] from port %s", event, this));
    context.getVirtualWireMediator().transmit(this, event);
  }

  @Override
  public void addObserver(PortObserver observer) {
    observers.add(observer);
  }
}
