package com.ravensim.simulator.boolean_function;

import com.ravensim.simulator.boolean_operator.XorOperator;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.TwoInputsOneOutput;
import com.ravensim.simulator.port.Port;

public class XorDecorator extends BooleanFunctionDecorator<TwoInputsOneOutput> {
  public XorDecorator(
      BooleanFunction booleanFunctionToBeDecorated, Port input0, Port input1, Port output)
      throws IncompatibleBitWidthsException {
    super(
        booleanFunctionToBeDecorated,
        new XorOperator(),
        new TwoInputsOneOutput(input0, input1, output));
  }

  @Override
  public void evaluate() {
    super.evaluate();
    function.evaluate(io);
  }
}
