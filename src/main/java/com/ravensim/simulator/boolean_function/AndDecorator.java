package com.ravensim.simulator.boolean_function;

import com.ravensim.simulator.boolean_operator.AndOperator;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.TwoInputsOneOutput;
import com.ravensim.simulator.port.Port;

public class AndDecorator extends BooleanFunctionDecorator<TwoInputsOneOutput> {
  public AndDecorator(
      BooleanFunction booleanFunctionToBeDecorated, Port input0, Port input1, Port output)
      throws IncompatibleBitWidthsException {
    super(
        booleanFunctionToBeDecorated,
        new AndOperator(),
        new TwoInputsOneOutput(input0, input1, output));
  }

  @Override
  public void evaluate() {
    super.evaluate();
    function.evaluate(io);
  }
}
