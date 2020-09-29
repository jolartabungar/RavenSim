package com.ravensim.simulator.boolean_function;

import com.ravensim.simulator.boolean_operator.Proposition;
import com.ravensim.simulator.io.AbstractIO;

import java.util.Objects;

public abstract class BooleanFunctionDecorator<T extends AbstractIO> implements BooleanFunction {

  private final BooleanFunction booleanFunctionToBeDecorated;

  protected Proposition<T> function;

  protected T io;

  public BooleanFunctionDecorator(
      BooleanFunction booleanFunctionToBeDecorated, Proposition function, T io) {
    Objects.requireNonNull(booleanFunctionToBeDecorated);
    this.booleanFunctionToBeDecorated = booleanFunctionToBeDecorated;
    this.function = function;
    this.io = io;
  }

  @Override
  public void evaluate() {
    booleanFunctionToBeDecorated.evaluate();
  }
}
