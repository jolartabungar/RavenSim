package com.ravensim.simulator.boolean_operator;

public class OrOperator extends AbstractTwoInputOneOutputBooleanFunction {
  public OrOperator() {
    super((op1, op2) -> op1 || op2);
  }
}
