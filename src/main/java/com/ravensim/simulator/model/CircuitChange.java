package com.ravensim.simulator.model;

public class CircuitChange {

  private String action;

  private String type;

  private Integer id;

  private Properties properties;

  public CircuitChange(String action, String type, Integer id, Properties properties) {
    this.action = action;
    this.type = type;
    this.id = id;
    this.properties = properties;
  }

  public String getAction() {
    return action;
  }

  public String getType() {
    return type;
  }

  public Integer getId() {
    return id;
  }

  public Properties getProperties() {
    return properties;
  }
}
