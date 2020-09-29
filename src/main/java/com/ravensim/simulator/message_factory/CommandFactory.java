package com.ravensim.simulator.message_factory;

import com.ravensim.simulator.simulation.SimulationEngine;

import java.io.Serializable;

public abstract class CommandFactory {
  public CommandFactory(SimulationEngine context) {
    context.getMessageBroker().add(buildMessage());
  }

  public abstract Serializable buildMessage();
}
