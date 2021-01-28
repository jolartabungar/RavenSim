package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.TwoInputsTwoOutputs;
import com.ravensim.simulator.logic_gate.AndGate;
import com.ravensim.simulator.logic_gate.NotGate;
import com.ravensim.simulator.logic_gate.XorGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class HalfSubtractor extends SubCircuit {

  public HalfSubtractor(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
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
    var d = io.getOutputs().get(0);
    var bout = io.getOutputs().get(1);
    // Instantiate the ports
    var t0  = new Port(ctx, io.getCollectiveBitWidth());
    var t1  = new Port(ctx, io.getCollectiveBitWidth());
    // Create the circuit
    new XorGate(ctx, List.of(a, b), d);
    new NotGate(ctx, a, t0);
    new AndGate(ctx, List.of(t1, b), bout);
    // Connect the wires
    var random = new Random();
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t1);
  }
}
