package com.ravensim.simulator.port;

public interface BinaryWord {
  void clear();

  boolean getBit(int index) throws BitIndexOutOfBoundsException;

  int getBitWidth();

  void setBit(int index) throws BitIndexOutOfBoundsException;

  int getSignal();

  void setSignal(long signal) throws InvalidSignalException;
}
