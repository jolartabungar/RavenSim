package com.ravensim.simulator.event;

public class PropagationEvent extends Event {
  private final int signal;

  public PropagationEvent(int signal) {
    this.signal = signal;
  }

  public int getSignal() {
    return signal;
  }

  @Override
  public String toString() {
    return String.format(
        "%s: %s, Signal: 0b%s",
        getClass().getSimpleName(), hashCode(), Integer.toBinaryString(signal));
  }
}
