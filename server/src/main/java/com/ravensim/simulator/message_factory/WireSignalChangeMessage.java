package com.ravensim.simulator.message_factory;

import com.ravensim.simulator.model.CircuitChange;
import com.ravensim.simulator.model.Event;
import com.ravensim.simulator.model.Message;
import com.ravensim.simulator.model.Properties;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.io.Serializable;
import java.util.Collections;

public class WireSignalChangeMessage extends AbstractSignalChange {

  private static final String TYPE = "Wire";

  public WireSignalChangeMessage(SimulationEngine context, Integer id, Integer payload) {
    super(context, id, payload);
  }

  @Override
  protected Serializable buildLogicGate(Integer id, Integer payload) {
    return new Event(
        new Message(
            Collections.singletonList(
                new CircuitChange(
                    ACTION, TYPE, id, new Properties(null, null, null, null, null, payload)))));
  }
}
