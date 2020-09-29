package com.ravensim.simulator.signal;

import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

public abstract class Signal implements Runnable {
  protected Port output;
  protected SimulationEngine context;

  public Signal(SimulationEngine context, Port output) {
    this.output = output;
    this.context = context;
    context.getTickerGenerator().addListener(this);
  }
}
