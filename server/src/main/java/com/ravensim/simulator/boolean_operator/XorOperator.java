package com.ravensim.simulator.boolean_operator;

public class XorOperator extends AbstractTwoInputOneOutputBooleanFunction {
  public XorOperator() {
    super((op1, op2) -> op1 ^ op2);
  }
}
