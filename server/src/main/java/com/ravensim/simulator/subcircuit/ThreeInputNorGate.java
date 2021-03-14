package com.ravensim.simulator.subcircuit;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.ManyInputsOneOutput;
import com.ravensim.simulator.logic_gate.NorGate;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;

import java.util.List;
import java.util.Random;

public class ThreeInputNorGate extends SubCircuit 
{
  public ThreeInputNorGate(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws InvalidNumberOfPortsException, InvalidBitWidthException,
          IncompatibleBitWidthsException 
  {
    super(ctx, inputs, outputs);
  }

  @Override
  protected void build(SimulationEngine ctx, List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException 
    {
        var io    = new ManyInputsOneOutput(inputs, outputs.get(0));
        var a     = io.getInputs().get(0);
        var b     = io.getInputs().get(1);
        var c     = io.getInputs().get(2);
        // Output
        var d     = io.getOutput();    
        // Create the circuit 
        new NorGate(ctx, List.of(a,b,c), d);
    }
}
