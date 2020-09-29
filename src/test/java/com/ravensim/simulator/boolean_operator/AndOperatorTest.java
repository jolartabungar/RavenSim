package com.ravensim.simulator.boolean_operator;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.TwoInputsOneOutput;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.InvalidSignalException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import com.ravensim.simulator.util.Common;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

class AndOperatorTest {

  private AndOperator operator;
  private TwoInputsOneOutput io;

  @BeforeEach
  void setUp() throws InvalidBitWidthException, IncompatibleBitWidthsException {
    var context = Mockito.mock(SimulationEngine.class);
    io =
        new TwoInputsOneOutput(
            new Port(context, Common.BIT_WIDTH),
            new Port(context, Common.BIT_WIDTH),
            new Port(context, Common.BIT_WIDTH));
    operator = new AndOperator();
  }

  @AfterEach
  void tearDown() {
    operator = null;
    io = null;
  }

  @RepeatedTest(Common.NUMBER_OF_TEST)
  void evaluate_isCorrectOutput() throws InvalidSignalException {
    io.getInput0().setSignal(Common.randomSignal());
    io.getInput1().setSignal(Common.randomSignal());
    operator.evaluate(io);
    Assertions.assertEquals(
        io.getOutput().getSignal(), io.getInput0().getSignal() & io.getInput1().getSignal());
  }

  @Test
  void evaluate_outputSignalResetsOnEveryEvaluation() throws InvalidSignalException {
    io.getInput0().setSignal(Common.randomSignal());
    io.getInput1().setSignal(Common.randomSignal());
    operator.evaluate(io);
    io.getInput0().setSignal(Common.randomSignal());
    io.getInput1().setSignal(Common.randomSignal());
    operator.evaluate(io);
    Assertions.assertEquals(
        io.getOutput().getSignal(), io.getInput0().getSignal() & io.getInput1().getSignal());
  }
}
