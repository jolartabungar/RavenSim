package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.ManyInputsManyOutputs;
import com.ravensim.simulator.logic_gate.AndGate;
import com.ravensim.simulator.logic_gate.NotGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class OneToFourDemux extends SubCircuit {

  public OneToFourDemux(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
          IncompatibleBitWidthsException {
    super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException {
    var io = new ManyInputsManyOutputs(inputs, outputs);
    var d = io.getInputs().get(0);
    // select input a
    var sela = io.getInputs().get(1);
    var selb = io.getInputs().get(2);
    // output
    var y0 = io.getOutputs().get(0);
    var y1 = io.getOutputs().get(1);
    var y2 = io.getOutputs().get(2);
    var y3 = io.getOutputs().get(3);
    // Instantiate the ports
    var t0  = new Port(ctx, io.getCollectiveBitWidth());
    var t1  = new Port(ctx, io.getCollectiveBitWidth());
    var t2  = new Port(ctx, io.getCollectiveBitWidth());
    var t3  = new Port(ctx, io.getCollectiveBitWidth());
    var t4  = new Port(ctx, io.getCollectiveBitWidth());
    var t5  = new Port(ctx, io.getCollectiveBitWidth());
    // Create the circuit
    new NotGate(ctx, sela, t0);
    new NotGate(ctx, selb, t1);
    new AndGate(ctx, List.of(t2, t4, d), y0);
    new AndGate(ctx, List.of(sela, t5, d), y1);
    new AndGate(ctx, List.of(t3, selb, d), y2);
    new AndGate(ctx, List.of(sela, selb, d), y3);
    // Connect the wires
    var random = new Random();
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t2);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t3);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t1, t4);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t1, t5);
  }
}
