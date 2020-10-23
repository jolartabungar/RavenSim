package com.ravensim.simulator.boolean_operator;

public class AndOperator extends AbstractTwoInputOneOutputBooleanFunction {
  public AndOperator() {
    super((op1, op2) -> op1 && op2);
  }
}
