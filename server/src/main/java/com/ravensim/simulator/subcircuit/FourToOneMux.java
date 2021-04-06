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

public class FourToOneMux extends SubCircuit {

  public FourToOneMux(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
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
    var c = io.getInputs().get(2);
    var d = io.getInputs().get(3);
    // select inputs a and b
    var sela = io.getInputs().get(4); 
    var selb = io.getInputs().get(5);
    // output
    var q = io.getOutput();
    // Instantiate the ports
    var t0   = new Port(ctx, io.getCollectiveBitWidth());
    var t1   = new Port(ctx, io.getCollectiveBitWidth());
    var t2   = new Port(ctx, io.getCollectiveBitWidth());
    var t3   = new Port(ctx, io.getCollectiveBitWidth());
    var t4   = new Port(ctx, io.getCollectiveBitWidth());
    var t5   = new Port(ctx, io.getCollectiveBitWidth());
    var t6   = new Port(ctx, io.getCollectiveBitWidth());
    var t7   = new Port(ctx, io.getCollectiveBitWidth());
    var t8   = new Port(ctx, io.getCollectiveBitWidth());
    var t9   = new Port(ctx, io.getCollectiveBitWidth());
    var t10  = new Port(ctx, io.getCollectiveBitWidth());
    var t11  = new Port(ctx, io.getCollectiveBitWidth());
    var t12  = new Port(ctx, io.getCollectiveBitWidth());
    var t13  = new Port(ctx, io.getCollectiveBitWidth());
    // Create the circuit using Nand Gates
    new NandGate(ctx, List.of(sela,sela), t0);
    new NandGate(ctx, List.of(selb,selb), t1);
    new NandGate(ctx, List.of(a,t2,t3), t6);
    new NandGate(ctx, List.of(b,sela,t5), t7);
    new NandGate(ctx, List.of(c,t4,selb), t8);
    new NandGate(ctx, List.of(d,sela,selb), t9);
    new NandGate(ctx, List.of(t10,t11,t12,t13), q);
    // Connect the wires
    var random = new Random();
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t2);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t1, t3);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t4);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t1, t5);

    ctx.getVirtualWireMediator().connect(random.nextInt(), t6, t10);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t7, t11);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t8, t12);
    ctx.getVirtualWireMediator().connect(random.nextInt(), t9, t13);
  }
}
