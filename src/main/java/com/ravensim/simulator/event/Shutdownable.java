package com.ravensim.simulator.event;

public interface Shutdownable extends Observer<ShutdownEvent> {
  void update(ShutdownEvent event);
}
