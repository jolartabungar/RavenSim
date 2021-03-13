package com.ravensim.simulator.model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CircuitModel extends TextMessage implements Serializable {
    private static final String TYPE = "CircuitModel";
    private ArrayList<CircuitChange> changeList;

    public CircuitModel() {
        super(TYPE, null);
        this.changeList = new ArrayList<>();
    }

    public CircuitModel(CircuitModel model) {
        super(TYPE, model.getJsonInfo());
    }

    public void update(CircuitChange change) {
        this.changeList.add(change);
    }

    public ArrayList<CircuitChange> getChanges() {
        return changeList;
    }
}
