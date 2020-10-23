package com.ravensim.simulator.boolean_function;

import com.ravensim.simulator.boolean_operator.NotOperator;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.InvalidSignalException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import com.ravensim.simulator.util.Common;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

class NotDecoratorTest {

  private BooleanFunction simpleBooleanFunction;
  private SimulationEngine context;

  @BeforeEach
  void setUp() {
    context = Mockito.mock(SimulationEngine.class);
    simpleBooleanFunction = Mockito.mock(SimpleBooleanFunction.class);
  }

  @AfterEach
  void tearDown() {
    simpleBooleanFunction = null;
    context = null;
  }

  @Test
  void constructor_nullBooleanFunctionToBeDecorated() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new NotDecorator(null, new Port(context), new Port(context)));
  }

  @Test
  void constructor_nullInput() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new NotDecorator(simpleBooleanFunction, null, new Port(context)));
  }

  @Test
  void constructor_nullOutput() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new NotDecorator(simpleBooleanFunction, new Port(context), null));
  }

  @Test
  void constructor_nullInputAndOutput() {
    Assertions.assertThrows(
        NullPointerException.class, () -> new NotDecorator(simpleBooleanFunction, null, null));
  }

  @Test
  void constructor_sanityCheck() {
    Assertions.assertDoesNotThrow(
        () -> new NotDecorator(simpleBooleanFunction, new Port(context), new Port(context)));
  }

  @Test
  void constructor_incompatibleInputsAndOutput() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () -> new NotDecorator(simpleBooleanFunction, new Port(context), new Port(context, 2)));
  }

  @Test
  void evaluate_invokeEvaluateOnSimpleBooleanFunction()
      throws InvalidBitWidthException, IncompatibleBitWidthsException {
    new NotDecorator(simpleBooleanFunction, new Port(context), new Port(context)).evaluate();
    Mockito.verify(simpleBooleanFunction).evaluate();
  }

  @Test
  void evaluate_invokeSuperEvaluateBeforeEvaluatingSelf()
      throws InvalidBitWidthException, IncompatibleBitWidthsException {
    var operator = Mockito.mock(NotOperator.class);
    var notDecorator =
        new NotDecorator(simpleBooleanFunction, new Port(context), new Port(context));
    ReflectionTestUtils.setField(notDecorator, "function", operator);
    var inOrder = Mockito.inOrder(simpleBooleanFunction, operator);
    notDecorator.evaluate();
    inOrder.verify(simpleBooleanFunction).evaluate();
    inOrder.verify(operator).evaluate(Mockito.any());
  }

  @RepeatedTest(Common.NUMBER_OF_TEST)
  void evaluate_isCorrectOutput()
      throws InvalidBitWidthException, IncompatibleBitWidthsException, InvalidSignalException {
    var input = new Port(context, Common.BIT_WIDTH);
    var output = new Port(context, Common.BIT_WIDTH);
    var signal = Common.randomSignal();
    input.setSignal(signal);
    new NotDecorator(simpleBooleanFunction, input, output).evaluate();
    Assertions.assertEquals(output.getSignal(), ~signal);
  }
}
