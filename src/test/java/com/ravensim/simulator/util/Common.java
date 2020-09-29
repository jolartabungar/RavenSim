package com.ravensim.simulator.util;

import com.ravensim.simulator.port.BitSetAdapter;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Common {

  public static final int TIMEOUT = 15;

  public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

  public static final int ITERATIONS_FOR_STRESS_TEST = 10000;

  public static final int LEVEL_OF_PARALLELISM = 1;

  public static final int ZERO_INVOCATIONS = 0;

  public static final int NUMBER_OF_TEST = 10;

  public static final int NUMBER_OF_PORTS = 3;

  public static final int BIT_WIDTH = BitSetAdapter.MAX_BIT_WIDTH;

  public static final int MAX_VALUE = Math.toIntExact((1L << BIT_WIDTH - 1) - 1);

  public static int randomID() {
    return UUID.randomUUID().hashCode();
  }

  public static int randomSignal() {
    return new Random().nextInt(MAX_VALUE);
  }
}
