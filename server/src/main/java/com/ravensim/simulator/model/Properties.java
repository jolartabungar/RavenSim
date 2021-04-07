package com.ravensim.simulator.model;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Properties implements Serializable {

  private List<Point> inputs;

  private List<Point> outputs;

  private Point output;

  private Point from;

  private Point to;

  private int signal;

  public Properties(
      List<Point> inputs, List<Point> outputs, Point output, Point from, Point to, int signal) {
    this.inputs = inputs;
    this.outputs = outputs;
    this.output = output;
    this.from = from;
    this.to = to;
    this.signal = signal;
  }

  public Point getOutput() {
    return output;
  }

  public Point getFrom() {
    return from;
  }

  public Point getTo() {
    return to;
  }

  public List<Point> getInputs() {
    return inputs;
  }

  public List<Point> getOutputs() {
    return outputs;
  }

  public int getSignal() {
    return signal;
  }
}
