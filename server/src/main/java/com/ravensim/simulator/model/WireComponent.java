package com.ravensim.simulator.model;


import java.awt.*;

public class WireComponent extends CircuitComponent {
    private Point start;
    private Point end;

    public WireComponent(Point start, Point end){
        super(ComponentType.WIRE);
        this.start = start;
        this.end = end;
    }

    public Point getEnd() {
        return end;
    }

    public Point getStart() {
        return start;
    }
}
