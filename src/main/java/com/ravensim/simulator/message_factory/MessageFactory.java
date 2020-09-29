package com.ravensim.simulator.message_factory;

import com.ravensim.simulator.simulation.SimulationEngine;

import java.io.Serializable;

public abstract class MessageFactory<T> {

  public MessageFactory(SimulationEngine context, Integer id, T payload) {
    context.getMessageBroker().add(buildLogicGate(id, payload));
  }

  protected abstract Serializable buildLogicGate(Integer id, T payload);
}
