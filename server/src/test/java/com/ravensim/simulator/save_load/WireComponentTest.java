package com.ravensim.simulator.save_load;

import com.ravensim.simulator.model.WireComponent;
import com.ravensim.simulator.port.InvalidBitWidthException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class WireComponentTest {
    private WireComponent wc;
    private Point start;
    private Point end;

    @BeforeEach
    void setUp() throws InvalidBitWidthException {
        start = new Point(500, 350);
        end = new Point(580,350);
        wc = new WireComponent(start,end);

    }

    @AfterEach
    void tearDown() {
        wc = null;
        start = null;
        end = null;
    }

    @Test
    void test_getEnd() {
        assert(wc.getEnd().equals(end));
    }

    @Test
    void test_getStart() {
        assert(wc.getStart().equals(start));
    }
}
