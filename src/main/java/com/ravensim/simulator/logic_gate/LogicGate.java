package com.ravensim.simulator.logic_gate;

import com.ravensim.simulator.boolean_function.BooleanFunction;
import com.ravensim.simulator.boolean_function.SimpleBooleanFunction;
import com.ravensim.simulator.event.PortObserver;
import com.ravensim.simulator.event.RequestToUpdateEvent;
import com.ravensim.simulator.io.AbstractIO;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class LogicGate<T extends AbstractIO> implements PortObserver, Runnable {
  public static final int PROPAGATION_DELAY = 1;
  private static final Logger LOGGER = LogManager.getLogger(LogicGate.class.getSimpleName());
  protected final BooleanFunction booleanFunction;
  protected final SimulationEngine context;
  protected T io;

  public LogicGate(SimulationEngine context, T io) throws IncompatibleBitWidthsException {
    this.context = context;
    this.io = io;
    // todo Test that listenToAllInputs and buildLogicGate is called before invoking run
    listenToAllInputs();
    booleanFunction = buildLogicGate(new SimpleBooleanFunction());
    // The logic gate should evaluate its inputs on instantiation because the input signals may
    // change before the simulation starts.
    run();
    LOGGER.info(String.format("Instantiated a new logic gate %s", this));
  }

  protected abstract void listenToAllInputs();

  protected abstract BooleanFunction buildLogicGate(BooleanFunction logicGate)
      throws IncompatibleBitWidthsException;

  @Override
  public void update(RequestToUpdateEvent event) {
    event.setSource(this);
    event.setScheduledTime(PROPAGATION_DELAY + context.getTime());
    context.getEventRegistry().add(event);
  }

  @Override
  public String toString() {
    return String.format("[Type: %s, Hashcode: %s]", getClass().getSimpleName(), hashCode());
  }
}
