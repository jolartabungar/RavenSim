package com.ravensim.simulator.model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CircuitModel implements Serializable {
    private Map<Point, String> inputPortMap;
    private Map<Point, String> outputPortMap;
    private ArrayList<CircuitChange> changeList;

    public CircuitModel() {
        this.inputPortMap = new ConcurrentHashMap<>();
        this.outputPortMap = new ConcurrentHashMap<>();
        this.changeList = new ArrayList<>();
    }

    public void update(CircuitChange change) {
        this.changeList.add(change);
        var type = change.getType();
        for (Point point: change.getProperties().getInputs()) {
            this.addInputPort(type, point);
        }
        for (Point point: change.getProperties().getOutputs()) {
            this.addOutputPort(type, point);
        }
    }

    public void addInputPort(String gateType, Point portPoint) {
        this.inputPortMap.put(portPoint, gateType);
    }

    public void addOutputPort(String gateType, Point portPoint) {
        this.outputPortMap.put(portPoint, gateType);
    }

    public void printModel() {
        System.out.println(inputPortMap.toString());
        System.out.println(outputPortMap.toString());
    }

    public Map<Point, String> getInputs() {
        return this.inputPortMap;
    }

    public Map<Point, String> getOutputs() {
        return this.outputPortMap;
    }

    public ArrayList<CircuitChange> getChanges() {
        return changeList;
    }
}
