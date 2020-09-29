package com.ravensim.simulator.logic_gate;

import com.ravensim.simulator.boolean_function.BooleanFunction;
import com.ravensim.simulator.boolean_function.NotDecorator;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.OneInputOneOutput;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotGate extends LogicGate<OneInputOneOutput> {
  private static final Logger LOGGER = LogManager.getLogger(NotGate.class.getSimpleName());

  public NotGate(SimulationEngine simulationEngine, Port input, Port output)
      throws IncompatibleBitWidthsException {
    super(simulationEngine, new OneInputOneOutput(input, output));
  }

  @Override
  protected void listenToAllInputs() {
    io.getInput().addObserver(this);
  }

  @Override
  protected BooleanFunction buildLogicGate(BooleanFunction logicGate)
      throws IncompatibleBitWidthsException {
    return new NotDecorator(logicGate, io.getInput(), io.getOutput());
  }

  @Override
  public void run() {
    var oldSignal = io.getOutput().getSignal();
    booleanFunction.evaluate();
    var newSignal = io.getOutput().getSignal();
    // Only transmit from this logic gate if there is a change in the output signal.
    if (newSignal == oldSignal) {
      LOGGER.info(
          String.format(
              "Skipping transmission from logic hate: %s as there is no change in the output signal",
              hashCode()));
    } else {
      io.getOutput().transmit();
    }
  }
}
