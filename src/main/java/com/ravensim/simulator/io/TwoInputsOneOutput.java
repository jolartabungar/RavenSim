package com.ravensim.simulator.io;

import com.ravensim.simulator.port.Port;

import java.util.List;
import java.util.Objects;

public class TwoInputsOneOutput extends AbstractIO {
  public TwoInputsOneOutput(Port input0, Port input1, Port output)
      throws IncompatibleBitWidthsException {
    super(invariant(input0, input1), invariant(output));
  }

  private static List<Port> invariant(Port input0, Port input1) {
    var inputs = List.of(input0, input1);
    inputs.stream().forEach(Objects::requireNonNull);
    return inputs;
  }

  public Port getInput0() {
    return inputs.get(0);
  }

  public Port getInput1() {
    return inputs.get(1);
  }

  public Port getOutput() {
    return outputs.get(0);
  }
}
