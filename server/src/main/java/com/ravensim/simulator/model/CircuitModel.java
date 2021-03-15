package com.ravensim.simulator.model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public void clearChanges() {
        this.changeList.clear();
    }

    public ArrayList<CircuitComponent> getJsonInfo(){

     ArrayList<CircuitComponent> circuits = new ArrayList<>();

        String jsonInfo = "";

        // Get the information for each circuit in the system
        for(CircuitChange c: changeList) {
            CircuitComponent cc;
            switch (c.getType()) {
                case ComponentType.AND_GATE:
                case ComponentType.NOT_GATE:
                case ComponentType.XOR_GATE:
                case ComponentType.OR_GATE:
                case ComponentType.NAND_GATE:
                case ComponentType.NOR_GATE:
                case ComponentType.XNOR_GATE:
                    List<Point> points = c.getProperties().getInputs();

                    // Calculate the relative cursor position of each gate
                    // The cursor is positioned on the top left corner of the component and that will be used client-side to place
                    // the component in the right place
                    int x_position = points.get(0).x;
                    int y_position = points.get(0).y - 20;

                    cc = new CircuitComponent(c.getType(),new Point(x_position, y_position));
                    circuits.add(cc);
                    break;
                case ComponentType.CLOCK:
                case ComponentType.BUTTON:
                    Point p = c.getProperties().getOutput();

                    // Calculate the offset of the Clock or Button
                    x_position = p.x - 40;
                    y_position = p.y - 20;

                    cc = new CircuitComponent(c.getType(), new Point(x_position, y_position));
                    circuits.add(cc);
                    break;

                case ComponentType.WIRE:

                    // Save the start and end points of the wire
                    Point start = c.getProperties().getFrom();
                    Point end = c.getProperties().getTo();
                    WireComponent wc = new WireComponent(start, end);

                    circuits.add(wc);
                    break;
            }


        }

        return circuits;
    }
}
