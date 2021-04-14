package com.ravensim.simulator.save_load;

import com.ravensim.simulator.model.*;
import com.ravensim.simulator.port.InvalidBitWidthException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircuitModelTest {

    private CircuitModel cm;
    private int size;
    private ArrayList<CircuitChange> components;

    @BeforeEach
    void setUp() throws InvalidBitWidthException {
        // Create the FileManager
        cm = new CircuitModel();

        // Create components for the changeList

        // Create a Component
        List<Point> inPoints1 = new ArrayList<Point>();
        inPoints1.add(new Point(680,360));
        List<Point> outPoints1 = new ArrayList<Point>();
        outPoints1.add(new Point(760,360));
        Properties p1 = new Properties(inPoints1, outPoints1, null,null,null,0);
        CircuitChange c1 = new CircuitChange("CreateComponent", "NotGate", 0, p1);

        // Create a Button
        Properties p2 = new Properties(null,null,new Point(480,360),null,null,0);
        CircuitChange c2 = new CircuitChange("CreateComponent","InputButton",1, p2);

        // Create a Wire
        Properties p3 = new Properties(null,null,null,new Point(680,360),new Point(480,360),0);
        CircuitChange c3 = new CircuitChange("CreateComponent","Wire",2,p3);

        cm.update(c1);
        cm.update(c2);
        cm.update(c3);

        components = new ArrayList<CircuitChange>();
        components.add(c1);
        components.add(c2);
        components.add(c3);

        size = cm.getChanges().size();
    }

    @AfterEach
    void tearDown() {
        cm = null;
        size = 0;
        components =null;
    }

    @Test
    void test_update() {
        // Get the original size before the update
        int original_size = cm.getChanges().size();

        // Create a new Component to add
        Properties p = new Properties(null,null,new Point(400,320),null,null,0);
        CircuitChange c = new CircuitChange("CreateComponent","InputButton",3, p);

        cm.update(c);

        assert(cm.getChanges().size() == original_size + 1);
        assert(cm.getChanges().get(original_size).equals(c));
    }

    @Test
    void test_getChanges() {
        assert(cm.getChanges().size() == size);
        assert(cm.getChanges().equals(components));
    }

    @Test
    void test_clearChanges() {
        assert(cm.getChanges().size() == size);
        cm.clearChanges();
        assert(cm.getChanges().size() == 0);
    }

    @Test
    void test_getJsonInfo() {
        CircuitChange component = cm.getChanges().get(0);
        CircuitChange button = cm.getChanges().get(1);
        CircuitChange wire = cm.getChanges().get(2);

        ArrayList<CircuitComponent> jsonMessages = cm.getJsonInfo();

        // Ensure that the method creates the correct JSON information
        // First check the component
        assert(component.getType().equals(jsonMessages.get(0).getType()));

        // Get the x and y of the component's top left point
        int component_y = component.getProperties().getInputs().get(0).y;
        int component_x = component.getProperties().getInputs().get(0).x;

        assert(jsonMessages.get(0).getPoint().equals(new Point(component_x,component_y - 20)));

        // Next check the button
        assert(jsonMessages.get(1).getType().equals(button.getType()));

        // get the coordinates of the button's output point
        int button_x = button.getProperties().getOutput().x;
        int button_y = button.getProperties().getOutput().y;

        assert(jsonMessages.get(1).getPoint().equals(new Point(button_x-40,button_y-20)));

        // Finally check the wire
        WireComponent wc = (WireComponent) jsonMessages.get(2);
        assert(wc.getType().equals(wire.getType()));

        // There is no offsets used for wires so ensure the points remain the same
        assert(wc.getStart().equals(wire.getProperties().getFrom()));
        assert(wc.getEnd().equals(wire.getProperties().getTo()));
    }
}
