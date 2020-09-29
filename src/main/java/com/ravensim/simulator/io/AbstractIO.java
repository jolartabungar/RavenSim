package com.ravensim.simulator.io;

import com.ravensim.simulator.port.Port;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractIO {

  protected final List<Port> inputs;

  protected final List<Port> outputs;

  private final int collectiveBitWidth;

  protected AbstractIO(List<Port> inputs, List<Port> outputs)
      throws IncompatibleBitWidthsException {
    this.inputs = inputs;
    this.outputs = outputs;
    collectiveBitWidth = isCompatible();
  }

  private int isCompatible() throws IncompatibleBitWidthsException {
    // Concatenate the inputs and outputs.
    List<Port> ports =
        Stream.concat(inputs.stream(), outputs.stream()).collect(Collectors.toList());
    // Use the first port to determine the expected bit width.
    var expectedBitWidth = ports.get(0).getBitWidth();
    // Test all ports; however, skip the first, to ensure that they match the expected bit width.
    if (!ports.stream().skip(1).allMatch(wire -> wire.getBitWidth() == expectedBitWidth)) {
      throw new IncompatibleBitWidthsException("mismatch of input and output bit widths");
    }
    return expectedBitWidth;
  }

  protected static List<Port> invariant(Port output) {
    Objects.requireNonNull(output);
    return Collections.singletonList(output);
  }

  public int getCollectiveBitWidth() {
    return collectiveBitWidth;
  }
}
