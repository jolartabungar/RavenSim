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

public class OneToTwoDemux extends SubCircuit {

  public OneToTwoDemux(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
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
    // output
    var y0 = io.getOutputs().get(0);
    var y1 = io.getOutputs().get(1);
    // Instantiate the ports
    var t0  = new Port(ctx, io.getCollectiveBitWidth());
    var t1  = new Port(ctx, io.getCollectiveBitWidth());
    // Create the circuit
    new NotGate(ctx, sela, t0);
    new AndGate(ctx, List.of(t1, d), y0);
    new AndGate(ctx, List.of(d, sela), y1);
    // Connect the wires
    var random = new Random();
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t1);
  }
}
