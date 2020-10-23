package com.ravensim.simulator.model;

import java.util.List;

public class Message {

  private List<CircuitChange> circuitChanges;

  public Message(List<CircuitChange> circuitChanges) {
    this.circuitChanges = circuitChanges;
  }

  public List<CircuitChange> getCircuitChanges() {
    return circuitChanges;
  }
}
