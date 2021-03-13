package com.ravensim.simulator.model;

import java.awt.*;

// This class will take all the details of a component and send this info to the client using JSON.
public class CircuitComponent {
    private String type;
    private Point point;

    public CircuitComponent(String type, Point point) {
        this.point = point;
        this.type = type;
    }

    public CircuitComponent(String type) {
        this.type = type;
    }

    public Point getPoint() {
        return point;
    }

    public String getType() {
        return type;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setType(String type) {
        this.type = type;
    }
}
