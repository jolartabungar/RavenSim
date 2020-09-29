package com.ravensim.simulator.boolean_operator;

import com.ravensim.simulator.io.TwoInputsOneOutput;
import com.ravensim.simulator.port.BitIndexOutOfBoundsException;

import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public abstract class AbstractTwoInputOneOutputBooleanFunction
    implements Proposition<TwoInputsOneOutput> {

  private final BiPredicate<Boolean, Boolean> booleanOperator;

  public AbstractTwoInputOneOutputBooleanFunction(BiPredicate<Boolean, Boolean> booleanOperator) {
    this.booleanOperator = booleanOperator;
  }

  @Override
  public void evaluate(TwoInputsOneOutput io) {
    var input0 = io.getInput0();
    var input1 = io.getInput1();
    var output = io.getOutput();
    output.clear();
    IntStream.range(0, io.getCollectiveBitWidth())
        .forEach(
            i -> {
              // This will always execute without throwing an exception because the i/o device sets
              // the collective bit width upon instantiation, and the collective bit width is what
              // bounds the iterator.
              try {
                if (booleanOperator.test(input0.getBit(i), input1.getBit(i))) {
                  output.setBit(i);
                }
              } catch (BitIndexOutOfBoundsException e) {
              }
            });
  }
}
