package com.ravensim.simulator.boolean_operator;

public class NotOperator extends AbstractOneInputOneOutputBooleanFunction {
  public NotOperator() {
    super(op -> !op);
  }
}
