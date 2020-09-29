package com.ravensim.simulator.boolean_operator;

import com.ravensim.simulator.io.AbstractIO;

public interface Proposition<T extends AbstractIO> {
  void evaluate(T io);
}
