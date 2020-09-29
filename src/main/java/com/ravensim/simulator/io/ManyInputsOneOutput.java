package com.ravensim.simulator.io;

import com.ravensim.simulator.port.Port;

import java.util.List;
import java.util.Objects;

public class ManyInputsOneOutput extends AbstractIO {
  public static final int MAX_NUMBER_OF_INPUTS = 32;

  public ManyInputsOneOutput(List<Port> inputs, Port output)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException {
    super(invariant(inputs), invariant(output));
  }

  private static List<Port> invariant(List<Port> inputs) throws InvalidNumberOfPortsException {
    inputs.stream().forEach(Objects::requireNonNull);
    if (inputs.size() < 2) {
      throw new InvalidNumberOfPortsException("expected at least two inputs");
    } else if (inputs.size() > MAX_NUMBER_OF_INPUTS) {
      throw new InvalidNumberOfPortsException(
          String.format("the input size must be less than or equal to %s", MAX_NUMBER_OF_INPUTS));
    }
    return inputs;
  }

  public List<Port> getInputs() {
    return inputs;
  }

  public Port getOutput() {
    return outputs.get(0);
  }
}
