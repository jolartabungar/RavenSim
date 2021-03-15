package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.ManyInputsManyOutputs;
import com.ravensim.simulator.logic_gate.NotGate;
import com.ravensim.simulator.logic_gate.AndGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class ThreetoEightDecoder extends SubCircuit {

  public ThreetoEightDecoder(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
            IncompatibleBitWidthsException {
        super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException 
    {
        var io = new ManyInputsManyOutputs(inputs, outputs);
        var a0 = io.getInputs().get(0);
        var a1 = io.getInputs().get(1);
        var a2 = io.getInputs().get(2);
        // output
        var y0 = io.getOutputs().get(0);
        var y1 = io.getOutputs().get(1);
        var y2 = io.getOutputs().get(2);
        var y3 = io.getOutputs().get(3);
        var y4 = io.getOutputs().get(4);
        var y5 = io.getOutputs().get(5);
        var y6 = io.getOutputs().get(6);
        var y7 = io.getOutputs().get(7);
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
        var t12  = new Port(ctx, io.getCollectiveBitWidth());
        var t13  = new Port(ctx, io.getCollectiveBitWidth());
        var t14  = new Port(ctx, io.getCollectiveBitWidth());
        var t15  = new Port(ctx, io.getCollectiveBitWidth());
        var t16  = new Port(ctx, io.getCollectiveBitWidth());
        var t17  = new Port(ctx, io.getCollectiveBitWidth());
        var t18  = new Port(ctx, io.getCollectiveBitWidth());
        var t19  = new Port(ctx, io.getCollectiveBitWidth());
        var t20  = new Port(ctx, io.getCollectiveBitWidth());
        var t21  = new Port(ctx, io.getCollectiveBitWidth());
        var t22  = new Port(ctx, io.getCollectiveBitWidth());
        var t23  = new Port(ctx, io.getCollectiveBitWidth());
        var t24  = new Port(ctx, io.getCollectiveBitWidth());
        var t25  = new Port(ctx, io.getCollectiveBitWidth());
        var t26  = new Port(ctx, io.getCollectiveBitWidth());
        var t27  = new Port(ctx, io.getCollectiveBitWidth());
        var t28  = new Port(ctx, io.getCollectiveBitWidth());
        var t29  = new Port(ctx, io.getCollectiveBitWidth());
        var t30  = new Port(ctx, io.getCollectiveBitWidth());
        
        // Create the circuit
        new NotGate(ctx, a0, t0);
        new NotGate(ctx, a1, t1);
        new NotGate(ctx, a2, t2);
        new AndGate(ctx, List.of(t3, t7), t15);
        new AndGate(ctx, List.of(a0, t8), t17);
        new AndGate(ctx, List.of(t4, a1), t19);
        new AndGate(ctx, List.of(a0, a1), t21);
        new AndGate(ctx, List.of(t5, t9), t23);
        new AndGate(ctx, List.of(a0, t10), t25);
        new AndGate(ctx, List.of(t6, a1), t27);
        new AndGate(ctx, List.of(a0, a1), t29);
        new AndGate(ctx, List.of(t16, t11), y0);
        new AndGate(ctx, List.of(t18, t12), y1);
        new AndGate(ctx, List.of(t20, t13), y2);
        new AndGate(ctx, List.of(t22, t14), y3);
        new AndGate(ctx, List.of(t24, a2), y4);
        new AndGate(ctx, List.of(t26, a2), y5);
        new AndGate(ctx, List.of(t28, a2), y6);
        new AndGate(ctx, List.of(t30, a2), y7);
        
        // Connect the wires
        var random = new Random();
        ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t3);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t4);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t5);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t0, t6);

        ctx.getVirtualWireMediator().connect(random.nextInt(), t1, t7);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t1, t8);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t1, t9);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t1, t10);

        ctx.getVirtualWireMediator().connect(random.nextInt(), t2, t11);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t2, t12);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t2, t13);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t2, t14);

        ctx.getVirtualWireMediator().connect(random.nextInt(), t15, t16);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t17, t18);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t19, t20);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t21, t22);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t23, t24);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t25, t26);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t27, t28);
        ctx.getVirtualWireMediator().connect(random.nextInt(), t29, t30);
        
        
    }
}