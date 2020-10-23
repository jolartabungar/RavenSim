package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.TwoInputsTwoOutputs;
import com.ravensim.simulator.logic_gate.NandGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class DFlipFlop extends SubCircuit {

  public DFlipFlop(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
          IncompatibleBitWidthsException {
    super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException {
    var io = new TwoInputsTwoOutputs(inputs, outputs);
    var clock = io.getInputs().get(0);
    var d = io.getInputs().get(1);
    var q = io.getOutputs().get(0);
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
    new NandGate(ctx, List.of(d, d), t0);
    new NandGate(ctx, List.of(d, clock), t2);
    new NandGate(ctx, List.of(clock, t1), t4);
    new NandGate(ctx, List.of(t3, t7), q);
    new NandGate(ctx, List.of(t6, t5), q_not);
    // Connect the wires
    var random = new Random();
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t1);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t2, t3);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t4, t5);
    ctx.getVirtualWireMediator().connect(random.nextInt(), q_not, t7);
    ctx.getVirtualWireMediator().connect(random.nextInt(), q, t6);
  }
}
