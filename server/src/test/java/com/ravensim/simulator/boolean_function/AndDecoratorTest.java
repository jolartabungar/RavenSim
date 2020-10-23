package com.ravensim.simulator.boolean_function;

import com.ravensim.simulator.boolean_operator.AndOperator;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.InvalidSignalException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import com.ravensim.simulator.util.Common;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

class AndDecoratorTest {

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
        () -> new AndDecorator(null, new Port(context), new Port(context), new Port(context)));
  }

  @Test
  void constructor_nullInput0() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new AndDecorator(simpleBooleanFunction, null, new Port(context), new Port(context)));
  }

  @Test
  void constructor_nullInput1() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new AndDecorator(simpleBooleanFunction, new Port(context), null, new Port(context)));
  }

  @Test
  void constructor_nullOutput() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new AndDecorator(simpleBooleanFunction, new Port(context), new Port(context), null));
  }

  @Test
  void constructor_nullInput0AndOutput() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new AndDecorator(simpleBooleanFunction, null, new Port(context), null));
  }

  @Test
  void constructor_nullInput1AndOutput() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new AndDecorator(simpleBooleanFunction, new Port(context), null, null));
  }

  @Test
  void constructor_nullInput0AndInput1() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new AndDecorator(simpleBooleanFunction, null, null, new Port(context)));
  }

  @Test
  void constructor_nullInputsAndOutput() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> new AndDecorator(simpleBooleanFunction, null, null, null));
  }

  @Test
  void constructor_sanityCheck() {
    Assertions.assertDoesNotThrow(
        () ->
            new AndDecorator(
                simpleBooleanFunction, new Port(context), new Port(context), new Port(context)));
  }

  @Test
  void constructor_incompatibleInput0() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () ->
            new AndDecorator(
                simpleBooleanFunction, new Port(context), new Port(context, 2), new Port(context)));
  }

  @Test
  void constructor_incompatibleInput1() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () ->
            new AndDecorator(
                simpleBooleanFunction, new Port(context, 2), new Port(context), new Port(context)));
  }

  @Test
  void constructor_incompatibleOutput() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () ->
            new AndDecorator(
                simpleBooleanFunction, new Port(context), new Port(context), new Port(context, 2)));
  }

  @Test
  void constructor_incompatibleInputsAndOutput() {
    Assertions.assertThrows(
        IncompatibleBitWidthsException.class,
        () ->
            new AndDecorator(
                simpleBooleanFunction,
                new Port(context),
                new Port(context, 2),
                new Port(context, 3)));
  }

  @Test
  void evaluate_invokeEvaluateOnSimpleBooleanFunction()
      throws InvalidBitWidthException, IncompatibleBitWidthsException {
    new AndDecorator(simpleBooleanFunction, new Port(context), new Port(context), new Port(context))
        .evaluate();
    Mockito.verify(simpleBooleanFunction).evaluate();
  }

  @Test
  void evaluate_invokeSuperEvaluateBeforeEvaluatingSelf()
      throws InvalidBitWidthException, IncompatibleBitWidthsException {
    var operator = Mockito.mock(AndOperator.class);
    var andDecorator =
        new AndDecorator(
            simpleBooleanFunction, new Port(context), new Port(context), new Port(context));
    ReflectionTestUtils.setField(andDecorator, "function", operator);
    var inOrder = Mockito.inOrder(simpleBooleanFunction, operator);
    andDecorator.evaluate();
    inOrder.verify(simpleBooleanFunction).evaluate();
    inOrder.verify(operator).evaluate(Mockito.any());
  }

  @RepeatedTest(Common.NUMBER_OF_TEST)
  void evaluate_isCorrectOutput()
      throws InvalidBitWidthException, IncompatibleBitWidthsException, InvalidSignalException {
    var input0 = new Port(context, Common.BIT_WIDTH);
    var input1 = new Port(context, Common.BIT_WIDTH);
    var output = new Port(context, Common.BIT_WIDTH);
    input0.setSignal(Common.randomSignal());
    input1.setSignal(Common.randomSignal());
    new AndDecorator(simpleBooleanFunction, input0, input1, output).evaluate();
    Assertions.assertEquals(output.getSignal(), input0.getSignal() & input1.getSignal());
  }
}
