package com.ravensim.simulator.boolean_function;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SimpleBooleanFunctionTest {

  private SimpleBooleanFunction booleanFunction;

  @BeforeEach
  void setUp() {
    booleanFunction = Mockito.mock(SimpleBooleanFunction.class);
  }

  @AfterEach
  void tearDown() {
    booleanFunction = null;
  }

  @Test
  void evaluate_evaluateWasCalledOnce() {
    booleanFunction.evaluate();
    Mockito.verify(booleanFunction).evaluate();
  }
}
