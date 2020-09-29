package com.ravensim.simulator.model;

public class Event extends TextMessage<Message> {

  private static final String TYPE = "Event";

  public Event(Message message) {
    this(TYPE, message);
  }

  public Event(String type, Message message) {
    super(type, message);
  }
}
