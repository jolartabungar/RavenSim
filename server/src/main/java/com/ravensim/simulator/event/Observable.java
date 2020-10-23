package com.ravensim.simulator.event;

public interface Observable<T extends Observer> {
  void addObserver(T observer);
}
