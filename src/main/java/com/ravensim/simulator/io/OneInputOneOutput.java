package com.ravensim.simulator.io;

import com.ravensim.simulator.port.Port;

public class OneInputOneOutput extends AbstractIO {
  public OneInputOneOutput(Port input, Port output) throws IncompatibleBitWidthsException {
    super(invariant(input), invariant(output));
  }

  public Port getInput() {
    return inputs.get(0);
  }

  public Port getOutput() {
    return outputs.get(0);
  }
}
