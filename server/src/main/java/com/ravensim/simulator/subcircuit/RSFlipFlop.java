package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.ManyInputsManyOutputs;
import com.ravensim.simulator.logic_gate.NandGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class RSFlipFlop extends SubCircuit {

  public RSFlipFlop(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
          IncompatibleBitWidthsException {
    super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException {
    var io    = new ManyInputsManyOutputs(inputs, outputs);
    var s     = io.getInputs().get(0);
    var clock = io.getInputs().get(1);
    var r     = io.getInputs().get(2);
    var q     = io.getOutputs().get(0);
    var q_not = io.getOutputs().get(1);
    // Instantiate the ports
    var t0 = new Port(ctx, io.getCollectiveBitWidth());
    var t1 = new Port(ctx, io.getCollectiveBitWidth());
    var t2 = new Port(ctx, io.getCollectiveBitWidth());
    var t3 = new Port(ctx, io.getCollectiveBitWidth());
    var t4 = new Port(ctx, io.getCollectiveBitWidth());
    var t5 = new Port(ctx, io.getCollectiveBitWidth());
    var t6 = new Port(ctx, io.getCollectiveBitWidth());
    var t7 = new Port(ctx, io.getCollectiveBitWidth());
    // Create the circuit
    new NandGate(ctx, List.of(s, clock), t0);
    new NandGate(ctx, List.of(clock, r), t3);
    new NandGate(ctx, List.of(t1, t2), q);
    new NandGate(ctx, List.of(t5, t4), q_not);
    // Connect the wires
    var random = new Random();
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t1);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t3, t4);
    ctx.getVirtualWireMediator().connect(random.nextInt(), q_not, t2);
    ctx.getVirtualWireMediator().connect(random.nextInt(), q, t5);
  }
}
