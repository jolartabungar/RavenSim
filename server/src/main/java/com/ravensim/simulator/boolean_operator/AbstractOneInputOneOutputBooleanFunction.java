package com.ravensim.simulator.boolean_operator;

import com.ravensim.simulator.io.OneInputOneOutput;
import com.ravensim.simulator.port.BitIndexOutOfBoundsException;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class AbstractOneInputOneOutputBooleanFunction implements Proposition<OneInputOneOutput> {

  private final Predicate<Boolean> booleanOperator;

  public AbstractOneInputOneOutputBooleanFunction(Predicate<Boolean> booleanOperator) {
    this.booleanOperator = booleanOperator;
  }

  @Override
  public void evaluate(OneInputOneOutput io) {
    var input = io.getInput();
    var output = io.getOutput();
    output.clear();
    IntStream.range(0, io.getCollectiveBitWidth())
        .forEach(
            i -> {
              // This will always execute without throwing an exception because the i/o device sets
              // the collective bit width upon instantiation, and the collective bit width is what
              // bounds the iterator.
              try {
                if (booleanOperator.test(input.getBit(i))) {
                  output.setBit(i);
                }
              } catch (BitIndexOutOfBoundsException e) {
              }
            });
  }
}
