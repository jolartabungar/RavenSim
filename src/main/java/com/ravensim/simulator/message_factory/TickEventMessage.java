package com.ravensim.simulator.message_factory;

import com.ravensim.simulator.model.Command;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.io.Serializable;

public class TickEventMessage extends CommandFactory {

  private static final String MESSAGE = "Tick";

  public TickEventMessage(SimulationEngine context) {
    super(context);
  }

  @Override
  public Serializable buildMessage() {
    return new Command(MESSAGE, null);
  }
}
