package com.ravensim.simulator.event;

public interface PortObserver extends Observer<RequestToUpdateEvent> {
  void update(RequestToUpdateEvent event);
}
