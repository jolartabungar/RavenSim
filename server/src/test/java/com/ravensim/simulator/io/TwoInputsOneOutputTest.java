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

class TwoInputsOneOutputTest {

  private TwoInputsOneOutput io;
  private Port input0, input1, output;
  private SimulationEngine context;

  @BeforeEach
  void setUp() throws InvalidBitWidthException, IncompatibleBitWidthsException {
    context = Mockito.mock(SimulationEngine.class);
    input0 = new Port(context);
    input1 = new Port(context);
    output = new Port(context);
    io = new TwoInputsOneOutput(input0, input1, output);
  }

  @AfterEach
  void tearDown() {
    io = null;
    input0 = null;
    input1 = null;
    output = null;
    context = null;
  }

  @Test
  void isCompatible_incompatibleInput0() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () -> new TwoInputsOneOutput(new Port(context, 2), new Port(context), new Port(context)));
  }

  @Test
  void isCompatible_incompatibleInput1() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () -> new TwoInputsOneOutput(new Port(context), new Port(context, 2), new Port(context)));
  }

  @Test
  void isCompatible_incompatibleInputAndOutput() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () -> new TwoInputsOneOutput(new Port(context), new Port(context), new Port(context, 2)));
  }

  @Test
  void isCompatible_incompatibleIO() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () ->
            new TwoInputsOneOutput(
                new Port(context, 1), new Port(context, 2), new Port(context, 3)));
  }

  @Test
  void isCompatible_bitWidthIsGreaterThanOne() {
    Assertions.assertDoesNotThrow(
        () ->
            new TwoInputsOneOutput(
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH),
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH),
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH)));
  }

  @Test
  void getCollectiveBitWidth_bitWithIsOne() {
    Assertions.assertEquals(io.getCollectiveBitWidth(), Port.DEFAULT_BIT_WIDTH);
  }

  @Test
  void getCollectiveBitWidth_bitWithIsMax()
      throws InvalidBitWidthException, IncompatibleBitWidthsException {
    Assertions.assertEquals(
        new TwoInputsOneOutput(
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH),
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH),
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH))
            .getCollectiveBitWidth(),
        Port.MAX_BIT_WIDTH);
  }

  @Test
  void getInput0_sanityCheck() {
    Assertions.assertEquals(io.getInput0(), input0);
  }

  @Test
  void getInput1_sanityCheck() {
    Assertions.assertEquals(io.getInput1(), input1);
  }

  @Test
  void getOutput_sanityCheck() {
    Assertions.assertEquals(io.getOutput(), output);
  }

  @Test
  void constructor_nullInput0() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new TwoInputsOneOutput(null, input1, output));
  }

  @Test
  void constructor_nullInput1() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new TwoInputsOneOutput(input0, null, output));
  }

  @Test
  void constructor_nullOutput() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new TwoInputsOneOutput(input0, input1, null));
  }

  @Test
  void constructor_nullInput0AndOutput() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new TwoInputsOneOutput(null, input1, null));
  }

  @Test
  void constructor_nullInput1AndOutput() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new TwoInputsOneOutput(input0, null, null));
  }

  @Test
  void constructor_nullInput0AndInput1() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new TwoInputsOneOutput(null, null, output));
  }

  @Test
  void constructor_nullInputsAndOutput() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new TwoInputsOneOutput(null, null, null));
  }

  @Test
  void constructor_sanityCheck() {
    Assertions.assertDoesNotThrow(() -> new TwoInputsOneOutput(input0, input1, output));
  }
}
