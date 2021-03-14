package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.ManyInputsManyOutputs;
import com.ravensim.simulator.logic_gate.AndGate;
import com.ravensim.simulator.logic_gate.NotGate;
import com.ravensim.simulator.logic_gate.XorGate;
import com.ravensim.simulator.logic_gate.OrGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class FullSubtractor extends SubCircuit {

  public FullSubtractor(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
          IncompatibleBitWidthsException {
    super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException {
    var io = new ManyInputsManyOutputs(inputs, outputs);
    var bin = io.getInputs().get(0);
    var a = io.getInputs().get(1);
    var b = io.getInputs().get(2);
    // output
    var d = io.getOutputs().get(0);
    var bout = io.getOutputs().get(1);
    // Instantiate the ports
    var t0  = new Port(ctx, io.getCollectiveBitWidth());
    var t1  = new Port(ctx, io.getCollectiveBitWidth());
    var t2  = new Port(ctx, io.getCollectiveBitWidth());
    var t3  = new Port(ctx, io.getCollectiveBitWidth());
    var t4  = new Port(ctx, io.getCollectiveBitWidth());
    var t5  = new Port(ctx, io.getCollectiveBitWidth());
    var t6  = new Port(ctx, io.getCollectiveBitWidth());
    var t7  = new Port(ctx, io.getCollectiveBitWidth());
    var t8  = new Port(ctx, io.getCollectiveBitWidth());
    var t9  = new Port(ctx, io.getCollectiveBitWidth());
    var t10  = new Port(ctx, io.getCollectiveBitWidth());
    // Create the circuit
    new XorGate(ctx, List.of(a, b), t0);
    new XorGate(ctx, List.of(bin, t1), d);
    new NotGate(ctx, t2, t3);
    new NotGate(ctx, a, t5);
    new AndGate(ctx, List.of(t6, b), t7);
    new AndGate(ctx, List.of(t4, bin), t8);
    new OrGate(ctx, List.of(t9, t10), bout);
      // Connect the wires
      var random = new Random();
      ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t1);
      ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t2);
      ctx.getVirtualWireMediator().connect(random.nextInt(), t3, t4);
      ctx.getVirtualWireMediator().connect(random.nextInt(), t5, t6);
      ctx.getVirtualWireMediator().connect(random.nextInt(), t8, t9);
      ctx.getVirtualWireMediator().connect(random.nextInt(), t7, t10);
  }
}