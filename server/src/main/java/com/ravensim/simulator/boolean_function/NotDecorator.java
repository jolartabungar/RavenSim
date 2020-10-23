package com.ravensim.simulator.boolean_function;

import com.ravensim.simulator.boolean_operator.NotOperator;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.OneInputOneOutput;
import com.ravensim.simulator.port.Port;

public class NotDecorator extends BooleanFunctionDecorator<OneInputOneOutput> {
  public NotDecorator(BooleanFunction booleanFunctionToBeDecorated, Port input, Port output)
      throws IncompatibleBitWidthsException {
    super(booleanFunctionToBeDecorated, new NotOperator(), new OneInputOneOutput(input, output));
  }

  @Override
  public void evaluate() {
    super.evaluate();
    function.evaluate(io);
  }
}
