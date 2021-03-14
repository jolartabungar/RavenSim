package com.ravensim.simulator.io;

import com.ravensim.simulator.port.Port;

import java.util.List;
import java.util.Objects;

public class ManyInputsManyOutputs extends AbstractIO {
  public static final int MAX_NUMBER_OF_INPUTS = 32;

  public ManyInputsManyOutputs(List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException {
    super(invariant(inputs), invariant(outputs));
  }

  private static List<Port> invariant(List<Port> ports) throws InvalidNumberOfPortsException {
    ports.stream().forEach(Objects::requireNonNull);
    if (ports.size() < 2) {
      throw new InvalidNumberOfPortsException("expected at least two inputs");
    } else if (ports.size() > MAX_NUMBER_OF_INPUTS) {
      throw new InvalidNumberOfPortsException(
          String.format("the input size must be less than or equal to %s", MAX_NUMBER_OF_INPUTS));
    }
    return ports;
  }

  public List<Port> getInputs() {
    return inputs;
  }

  public List<Port> getOutputs() {
    return outputs;
  }
}
