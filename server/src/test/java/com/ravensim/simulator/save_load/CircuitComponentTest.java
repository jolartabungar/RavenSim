package com.ravensim.simulator.save_load;

import com.ravensim.simulator.model.CircuitComponent;
import com.ravensim.simulator.model.WireComponent;
import com.ravensim.simulator.port.InvalidBitWidthException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class CircuitComponentTest {

    private CircuitComponent cc;
    private Point p;
    private String type;

    @BeforeEach
    void setUp() throws InvalidBitWidthException {
        p = new Point(100,120);
        type = "NotGate";
        cc = new CircuitComponent(type,p);
    }

    @AfterEach
    void tearDown() {
        p = null;
        type = null;
        cc = null;
    }

    @Test
    void test_getType(){
        assert(type.equals(cc.getType()));
    }

    @Test
    void test_getPoint(){
        assert(p.equals(cc.getPoint()));
    }

    @Test
    void test_setPoint(){
        Point newPoint = new Point(489,238);
        cc.setPoint(newPoint);
        assert(cc.getPoint().equals(newPoint));
    }

    @Test
    void test_setType() {
        String str = "AndGate";
        cc.setType(str);
        assert(cc.getType().equals(str));
    }
}
