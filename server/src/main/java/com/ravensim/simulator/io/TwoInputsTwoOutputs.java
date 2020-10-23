package com.ravensim.simulator.io;

import com.ravensim.simulator.port.Port;

import java.util.List;
import java.util.Objects;

public class TwoInputsTwoOutputs extends AbstractIO {
  public TwoInputsTwoOutputs(List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException {
    super(invariant(inputs), invariant(outputs));
  }

  private static List<Port> invariant(List<Port> ports) throws InvalidNumberOfPortsException {
    ports.stream().forEach(Objects::requireNonNull);
    if (ports.size() < 2) {
      throw new InvalidNumberOfPortsException("expected at least two inputs");
    }
    if (ports.size() < 2) {
      throw new InvalidNumberOfPortsException("expected at least two outputs");
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
