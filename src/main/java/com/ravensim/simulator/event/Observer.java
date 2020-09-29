package com.ravensim.simulator.event;

public interface Observer<T extends Event> {
  void update(T event);
}
