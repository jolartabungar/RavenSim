package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.ManyInputsManyOutputs;
import com.ravensim.simulator.logic_gate.OrGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class EighttoThreeEncoder extends SubCircuit {

  public EighttoThreeEncoder(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
            IncompatibleBitWidthsException {
        super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException {
    var io = new ManyInputsManyOutputs(inputs, outputs);
    var y7 = io.getInputs().get(0);
    var y6 = io.getInputs().get(1);
    var y5 = io.getInputs().get(2);
    var y4 = io.getInputs().get(3);
    var y3 = io.getInputs().get(4);
    var y2 = io.getInputs().get(5);
    var y1 = io.getInputs().get(6);
    var y0 = io.getInputs().get(7);
    // output
    var a2 = io.getOutputs().get(0);
    var a1 = io.getOutputs().get(1);
    var a0 = io.getOutputs().get(2);
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
    var t11  = new Port(ctx, io.getCollectiveBitWidth());
    // Create the circuit
    new OrGate(ctx, List.of(y7, y6), t0);
    new OrGate(ctx, List.of(t1, y5), t2);
    new OrGate(ctx, List.of(t3, y4), a2);
    new OrGate(ctx, List.of(y7, y6), t4);
    new OrGate(ctx, List.of(t5, y3), t6);
    new OrGate(ctx, List.of(t7, y2), a1);
    new OrGate(ctx, List.of(y7, y5), t8);
    new OrGate(ctx, List.of(t9, y3), t10);
    new OrGate(ctx, List.of(t11, y1), a0);
    // Connect the wires
    var random = new Random();
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t1);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t2, t3);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t4, t5);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t6, t7);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t8, t9);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t10, t11);
  }
}