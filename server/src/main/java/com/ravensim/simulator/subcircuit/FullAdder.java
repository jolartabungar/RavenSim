package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.ManyInputsManyOutputs;
import com.ravensim.simulator.logic_gate.AndGate;
import com.ravensim.simulator.logic_gate.XorGate;
import com.ravensim.simulator.logic_gate.OrGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class FullAdder extends SubCircuit {

  public FullAdder(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
          IncompatibleBitWidthsException {
    super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException {
    var io = new ManyInputsManyOutputs(inputs, outputs);
    var a = io.getInputs().get(0);
    var b = io.getInputs().get(1);
    var cin = io.getInputs().get(2);
    // output
    var s = io.getOutputs().get(0);
    var cout = io.getOutputs().get(1);
    // Instantiate the ports
    var t0  = new Port(ctx, io.getCollectiveBitWidth());
    var t1  = new Port(ctx, io.getCollectiveBitWidth());
    var t2  = new Port(ctx, io.getCollectiveBitWidth());
    var t3  = new Port(ctx, io.getCollectiveBitWidth());
    var t4  = new Port(ctx, io.getCollectiveBitWidth());
    var t5  = new Port(ctx, io.getCollectiveBitWidth());
    var t6  = new Port(ctx, io.getCollectiveBitWidth());
    // Create the circuit
    new XorGate(ctx, List.of(a, b), t0);
    new XorGate(ctx, List.of(t1, cin), s);
    new AndGate(ctx, List.of(a, b), t3);
    new AndGate(ctx, List.of(t2, cin), t5);
    new OrGate(ctx, List.of(t6, t4), cout);
      // Connect the wires
      var random = new Random();
      ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t1);
      ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t2);
      ctx.getVirtualWireMediator().connect(random.nextInt(), t3, t4);
      ctx.getVirtualWireMediator().connect(random.nextInt(), t5, t6);
  }
}