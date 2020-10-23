package com.ravensim.simulator.message_factory;

import com.ravensim.simulator.simulation.SimulationEngine;

public abstract class AbstractSignalChange extends MessageFactory<Integer> {
  protected static final String ACTION = "SetSignal";

  public AbstractSignalChange(SimulationEngine context, Integer id, Integer payload) {
    super(context, id, payload);
  }
}
