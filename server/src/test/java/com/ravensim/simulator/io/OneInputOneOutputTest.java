package com.ravensim.simulator.io;

import com.ravensim.simulator.port.BitSetAdapter;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class OneInputOneOutputTest {

  private OneInputOneOutput io;
  private Port input, output;
  private SimulationEngine context;

  @BeforeEach
  void setUp() throws InvalidBitWidthException, IncompatibleBitWidthsException {
    context = Mockito.mock(SimulationEngine.class);
    input = new Port(context);
    output = new Port(context);
    io = new OneInputOneOutput(input, output);
  }

  @AfterEach
  void tearDown() {
    io = null;
    input = null;
    output = null;
    context = null;
  }

  @Test
  void isCompatible_incompatibleInputAndOutput() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () -> new OneInputOneOutput(new Port(context), new Port(context, 2)));
  }

  @Test
  void isCompatible_bitWidthIsGreaterThanOne() {
    Assertions.assertDoesNotThrow(
        () ->
            new OneInputOneOutput(
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH),
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH)));
  }

  @Test
  void getCollectiveBitWidth_bitWithIsOne() {
    Assertions.assertEquals(io.getInput().getBitWidth(), Port.DEFAULT_BIT_WIDTH);
  }

  @Test
  void getCollectiveBitWidth_bitWithIsMax()
      throws InvalidBitWidthException, IncompatibleBitWidthsException {
    Assertions.assertEquals(
        new OneInputOneOutput(
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH),
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH))
            .getCollectiveBitWidth(),
        Port.MAX_BIT_WIDTH);
  }

  @Test
  void getInput_sanityCheck() {
    Assertions.assertEquals(io.getInput(), input);
  }

  @Test
  void getOutput_sanityCheck() {
    Assertions.assertEquals(io.getOutput(), output);
  }

  @Test
  void constructor_nullInput() {
    Assertions.assertThrows(NullPointerException.class, () -> new OneInputOneOutput(null, output));
  }

  @Test
  void constructor_nullOutput() {
    Assertions.assertThrows(NullPointerException.class, () -> new OneInputOneOutput(input, null));
  }

  @Test
  void constructor_nullInputAndOutput() {
    Assertions.assertThrows(NullPointerException.class, () -> new OneInputOneOutput(null, null));
  }

  @Test
  void constructor_sanityCheck() {
    Assertions.assertDoesNotThrow(() -> new OneInputOneOutput(input, output));
  }
}
