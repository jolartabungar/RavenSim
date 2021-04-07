package com.ravensim.simulator.model;

public class Command extends TextMessage<String> {
  private static final String TYPE = "Command";

  private Integer id;
  private String fileName;

  public Command(String message, Integer id) {
    super(TYPE, message);
    this.id = id;
  }

  public Command(String message, Integer id, String fileName) {
    super(TYPE, message);
    this.id = id;
    this.fileName = fileName;
  }

  public Integer getId() {
    return id;
  }

  public String getFileName() {
	  return fileName;
  }
}
