package com.ravensim.simulator.boolean_operator;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.OneInputOneOutput;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.InvalidSignalException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import com.ravensim.simulator.util.Common;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

class NotOperatorTest {

  private NotOperator operator;
  private OneInputOneOutput io;

  @BeforeEach
  void setUp() throws InvalidBitWidthException, IncompatibleBitWidthsException {
    var context = Mockito.mock(SimulationEngine.class);
    io =
        new OneInputOneOutput(
            new Port(context, Common.BIT_WIDTH), new Port(context, Common.BIT_WIDTH));
    operator = new NotOperator();
  }

  @AfterEach
  void tearDown() {
    operator = null;
    io = null;
  }

  @RepeatedTest(Common.NUMBER_OF_TEST)
  void evaluate_isCorrectOutput() throws InvalidSignalException {
    io.getInput().setSignal(Common.randomSignal());
    operator.evaluate(io);
    Assertions.assertEquals(io.getOutput().getSignal(), ~io.getInput().getSignal());
  }

  @Test
  void evaluate_outputSignalResetsOnEveryEvaluation() throws InvalidSignalException {
    io.getInput().setSignal(Common.randomSignal());
    operator.evaluate(io);
    io.getInput().setSignal(Common.randomSignal());
    operator.evaluate(io);
    Assertions.assertEquals(io.getOutput().getSignal(), ~io.getInput().getSignal());
  }
}
