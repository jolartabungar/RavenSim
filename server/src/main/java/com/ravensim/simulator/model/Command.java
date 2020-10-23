package com.ravensim.simulator.model;

public class Command extends TextMessage<String> {
  private static final String TYPE = "Command";

  private Integer id;

  public Command(String message, Integer id) {
    super(TYPE, message);
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
}
