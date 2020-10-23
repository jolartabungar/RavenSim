package com.ravensim.simulator.event;

import com.ravensim.simulator.logic_gate.LogicGate;

// A request to update event is called by a logic gate that is needing to recompute its outputs at a
// scheduled time.
public class RequestToUpdateEvent extends Event {
  private Integer scheduledTime;
  private LogicGate source;

  public RequestToUpdateEvent() {
    this.source = null;
    this.scheduledTime = null;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof RequestToUpdateEvent)) {
      return false;
    }

    RequestToUpdateEvent event = (RequestToUpdateEvent) obj;
    return scheduledTime == event.getScheduledTime() && source.equals(event.getSource());
  }

  public Integer getScheduledTime() {
    return scheduledTime;
  }

  public void setScheduledTime(Integer scheduledTime) {
    this.scheduledTime = scheduledTime;
  }

  public LogicGate<?> getSource() {
    return source;
  }

  public void setSource(LogicGate source) {
    this.source = source;
  }

  @Override
  public String toString() {
    return String.format(
        "%s: %s, Source: %s, Scheduled Time: %d",
        this.getClass().getSimpleName(), this.hashCode(), source.hashCode(), scheduledTime);
  }
}
