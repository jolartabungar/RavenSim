package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.TwoInputsTwoOutputs;
import com.ravensim.simulator.logic_gate.AndGate;
import com.ravensim.simulator.logic_gate.XorGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class HalfAdder extends SubCircuit {

  public HalfAdder(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
          IncompatibleBitWidthsException {
    super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException {
    var io = new TwoInputsTwoOutputs(inputs, outputs);
    var a = io.getInputs().get(0);
    var b = io.getInputs().get(1);
    // output
    var s = io.getOutputs().get(0);
    var c = io.getOutputs().get(1);
    // Create the circuit
    new XorGate(ctx, List.of(a, b), s);
    new AndGate(ctx, List.of(a, b), c);
  }
}
