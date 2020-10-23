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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ManyInputsOneOutputTest {

  private ManyInputsOneOutput io;
  private List<Port> inputs;
  private Port output;
  private SimulationEngine context;

  @BeforeEach
  void setUp()
      throws InvalidBitWidthException, IncompatibleBitWidthsException,
          InvalidNumberOfPortsException {
    context = Mockito.mock(SimulationEngine.class);
    inputs = List.of(new Port(context), new Port(context));
    output = new Port(context);
    io = new ManyInputsOneOutput(inputs, output);
  }

  @AfterEach
  void tearDown() {
    io = null;
    inputs = null;
    output = null;
    context = null;
  }

  @Test
  void isCompatible_incompatibleInputs() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () ->
            new ManyInputsOneOutput(
                List.of(new Port(context), new Port(context, 2)), new Port(context)));
  }

  @Test
  void isCompatible_incompatibleInputAndOutput() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () ->
            new ManyInputsOneOutput(
                List.of(new Port(context), new Port(context)), new Port(context, 2)));
  }

  @Test
  void isCompatible_incompatibleIO() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () ->
            new ManyInputsOneOutput(
                List.of(new Port(context, 1), new Port(context, 2)), new Port(context, 3)));
  }

  @Test
  void isCompatible_bitWidthIsGreaterThanOne() {
    Assertions.assertDoesNotThrow(
        () ->
            new ManyInputsOneOutput(
                List.of(
                    new Port(context, BitSetAdapter.MAX_BIT_WIDTH),
                    new Port(context, BitSetAdapter.MAX_BIT_WIDTH)),
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH)));
  }

  @Test
  void getCollectiveBitWidth_bitWithIsOne() {
    Assertions.assertEquals(io.getCollectiveBitWidth(), Port.DEFAULT_BIT_WIDTH);
  }

  @Test
  void getCollectiveBitWidth_bitWithIsMax()
      throws InvalidBitWidthException, IncompatibleBitWidthsException,
          InvalidNumberOfPortsException {
    Assertions.assertEquals(
        new ManyInputsOneOutput(
                List.of(
                    new Port(context, BitSetAdapter.MAX_BIT_WIDTH),
                    new Port(context, BitSetAdapter.MAX_BIT_WIDTH)),
                new Port(context, BitSetAdapter.MAX_BIT_WIDTH))
            .getCollectiveBitWidth(),
        Port.MAX_BIT_WIDTH);
  }

  @Test
  void getInputs_sanityCheck() {
    Assertions.assertEquals(io.getInputs(), inputs);
  }

  @Test
  void getOutput_sanityCheck() {
    Assertions.assertEquals(io.getOutput(), output);
  }

  @Test
  void constructor_nullInputs() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new ManyInputsOneOutput(null, output));
  }

  @Test
  void constructor_nullOutput() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new ManyInputsOneOutput(inputs, null));
  }

  @Test
  void constructor_nullInputsAndOutput() {
    Assertions.assertThrows(NullPointerException.class, () -> new ManyInputsOneOutput(null, null));
  }

  @Test
  void constructor_emptyInputList() {
    Assertions.assertThrows(
        InvalidNumberOfPortsException.class, () -> new ManyInputsOneOutput(List.of(), output));
  }

  @Test
  void constructor_oneInputEdgeCase() {
    Assertions.assertThrows(
        InvalidNumberOfPortsException.class,
        () -> new ManyInputsOneOutput(List.of(new Port(context)), output));
  }

  @Test
  void constructor_sanityCheck() {
    Assertions.assertDoesNotThrow(() -> new ManyInputsOneOutput(inputs, output));
  }

  @Test
  void constructor_moreThanTwoInputs() {
    Assertions.assertDoesNotThrow(
        () ->
            new ManyInputsOneOutput(
                List.of(new Port(context), new Port(context), new Port(context)), output));
  }

  @Test
  void constructor_moreThanMaxInputs() {
    var inputs =
        IntStream.range(0, ManyInputsOneOutput.MAX_NUMBER_OF_INPUTS + 1)
            .mapToObj(
                i -> {
                  var port = Mockito.mock(Port.class);
                  Mockito.when(port.getBitWidth()).thenReturn(Port.DEFAULT_BIT_WIDTH);
                  return port;
                })
            .collect(Collectors.toList());
    Assertions.assertThrows(
        InvalidNumberOfPortsException.class,
        () -> new ManyInputsOneOutput(inputs, new Port(context)));
  }

  @Test
  void constructor_maxInputsBoundaryCheck() {
    var inputs =
        IntStream.range(0, ManyInputsOneOutput.MAX_NUMBER_OF_INPUTS)
            .mapToObj(
                i -> {
                  var port = Mockito.mock(Port.class);
                  Mockito.when(port.getBitWidth()).thenReturn(Port.DEFAULT_BIT_WIDTH);
                  return port;
                })
            .collect(Collectors.toList());
    Assertions.assertDoesNotThrow(() -> new ManyInputsOneOutput(inputs, new Port(context)));
  }
}
