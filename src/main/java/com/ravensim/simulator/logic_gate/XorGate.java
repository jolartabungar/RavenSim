package com.ravensim.simulator.logic_gate;

import com.ravensim.simulator.boolean_function.BooleanFunction;
import com.ravensim.simulator.boolean_function.XorDecorator;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;

public class XorGate extends VariableInput {
  public XorGate(SimulationEngine simulationEngine, List<Port> inputs, Port output)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException {
    super(simulationEngine, inputs, output);
  }

  @Override
  protected BooleanFunction baseCase(BooleanFunction logicGate, Port previousLink, int inputIndex)
      throws InvalidBitWidthException, IncompatibleBitWidthsException {
    return new XorDecorator(
        logicGate, previousLink, io.getInputs().get(inputIndex), io.getOutput());
  }

  @Override
  protected BooleanFunction defaultCase(BooleanFunction logicGate)
      throws IncompatibleBitWidthsException {
    return new XorDecorator(
        logicGate, io.getInputs().get(0), io.getInputs().get(1), io.getOutput());
  }

  @Override
  protected BooleanFunction initialStep(BooleanFunction logicGate, Port newLink, int inputIndex)
      throws IncompatibleBitWidthsException {
    return new XorDecorator(
        logicGate, io.getInputs().get(inputIndex), io.getInputs().get(inputIndex + 1), newLink);
  }

  @Override
  protected BooleanFunction intermediateStep(
      BooleanFunction logicGate, Port previousLink, Port newLink, int inputIndex)
      throws IncompatibleBitWidthsException {
    return new XorDecorator(logicGate, previousLink, io.getInputs().get(inputIndex), newLink);
  }
}
