package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.ManyInputsOneOutput;
import com.ravensim.simulator.logic_gate.NandGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class TwoToOneMux extends SubCircuit {

  public TwoToOneMux(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
          IncompatibleBitWidthsException {
    super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException {
    var io = new ManyInputsOneOutput(inputs, outputs.get(0));
    var a = io.getInputs().get(0);
    var b = io.getInputs().get(1);
    // select input a
    var sela = io.getInputs().get(2); 
    // output
    var q = io.getOutput();
    // Instantiate the ports
    var t0  = new Port(ctx, io.getCollectiveBitWidth());
    var t1  = new Port(ctx, io.getCollectiveBitWidth());
    var t2  = new Port(ctx, io.getCollectiveBitWidth());
    var t3  = new Port(ctx, io.getCollectiveBitWidth());
    var t4  = new Port(ctx, io.getCollectiveBitWidth());
    var t5  = new Port(ctx, io.getCollectiveBitWidth());
    // Create the circuit
    new NandGate(ctx, List.of(sela, sela), t0);
    new NandGate(ctx, List.of(a, sela), t4);
    new NandGate(ctx, List.of(b, t1), t2);
    new NandGate(ctx, List.of(t5, t3), q);
    // Connect the wires
    var random = new Random();
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t1);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t2, t3);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t4, t5);
  }
}
