package com.ravensim.simulator.save_load;

import com.ravensim.simulator.model.CircuitChange;
import com.ravensim.simulator.model.CircuitModel;
import com.ravensim.simulator.model.Properties;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class FileManagerTest {

    private FileManager fm;
    private String filename = "test";

    @BeforeEach
    void setUp() throws InvalidBitWidthException {
        // Create the FileManager
        fm = new FileManager();
    }

    @AfterEach
    void tearDown() {
        fm = null;
    }

    void compare_model(CircuitModel loadedModel, CircuitModel cm){
        for(int x = 0; x < loadedModel.getChanges().size(); x++){

            // Get the CircuitChanges and Properties
            CircuitChange loadedCircuit = loadedModel.getChanges().get(x);
            CircuitChange savedCircuit = cm.getChanges().get(x);

            Properties loadedProperties = loadedCircuit.getProperties();
            Properties savedProperties = savedCircuit.getProperties();

            // Assert that all information remained the same
            assert(loadedCircuit.getAction().equals(savedCircuit.getAction()));
            assert(loadedCircuit.getId().equals(savedCircuit.getId()));
            assert(loadedCircuit.getType().equals(savedCircuit.getType()));

            // Check the fields of Properties associated with a Wire
            if(loadedCircuit.getType().equals("Wire")){
                assert(loadedProperties.getFrom().equals(savedProperties.getFrom()));
                assert(loadedProperties.getTo().equals(savedProperties.getTo()));
                assert(loadedProperties.getSignal() == savedProperties.getSignal());
                assert(loadedProperties.getInputs() == null);
                assert(loadedProperties.getOutputs() == null);
                assert(loadedProperties.getOutput() == null);
            } else { // The only other components we saved use the same properties
                assert(loadedProperties.getInputs().equals(savedProperties.getInputs()));
                assert(loadedProperties.getOutputs().equals(savedProperties.getOutputs()));
                assert(loadedProperties.getSignal() == savedProperties.getSignal());
                assert(loadedProperties.getOutput() == null);
                assert(loadedProperties.getFrom() == null);
                assert(loadedProperties.getTo() == null);
            }
        }
    }

    @Test
    void save_and_load_test() {

        // Create the components to save
        // Create circuit change messages to save
        // First create an AndGate
        List<Point> inPoints1 = new ArrayList<Point>();
        inPoints1.add(new Point(780,380));
        inPoints1.add(new Point(780,420));
        List<Point> outPoints1 = new ArrayList<Point>();
        outPoints1.add(new Point(860,400));
        Properties p1 = new Properties(inPoints1,outPoints1,null,null,null,0);
        CircuitChange c1 = new CircuitChange("CreateComponent","AndGate", 0, p1);

        // Next Create an OrGate
        List<Point> inPoints2 = new ArrayList<Point>();
        inPoints2.add(new Point(540,480));
        inPoints2.add(new Point(540,520));
        List<Point> outPoints2 = new ArrayList<Point>();
        outPoints2.add(new Point(620,500));
        Properties p2 = new Properties(inPoints2,outPoints2, null, null, null, 0);
        CircuitChange c2 = new CircuitChange("CreateComponent", "OrGate",1, p2);

        // Create a wire that connects the two gates
        Properties p3 = new Properties(null,null,null,new Point(780,420),new Point(620,500),0);
        CircuitChange c3 = new CircuitChange("CreateComponent", "Wire", 2, p3);

        // Add all of this to a CircuitModel to be saved
        CircuitModel cm = new CircuitModel();
        cm.update(c1);
        cm.update(c2);
        cm.update(c3);

        fm.saveToFile(cm,filename);

        // Check if the file exists
        File f = new File(".//test.ser");
        assert(f.exists());

        CircuitModel loadedModel = fm.loadFromFile(filename);

        // Check if each CircuitChange in the loaded model retains the same information
        compare_model(loadedModel,cm);
    }
    @Test
    void save_and_load_new_components_test() {
        // Create a EighttoThreeEncoder
        List<Point> inPoints1 = new ArrayList<Point>();
        inPoints1.add(new Point(780,260));
        inPoints1.add(new Point(780,300));
        inPoints1.add(new Point(780,340));
        inPoints1.add(new Point(780,360));
        inPoints1.add(new Point(780,400));
        inPoints1.add(new Point(780,440));
        inPoints1.add(new Point(780,480));
        inPoints1.add(new Point(780,520));
        List<Point> outPoints1 = new ArrayList<Point>();
        outPoints1.add(new Point(900,300));
        outPoints1.add(new Point(900,400));
        outPoints1.add(new Point(900,520));

        Properties p1 = new Properties(inPoints1,outPoints1,null,null,null,0);
        CircuitChange c1 = new CircuitChange("CreateComponent", "EighttoThreeEncoder",0,p1);

        // Create a HalfAdder
        List<Point> inPoints2 = new ArrayList<Point>();
        inPoints2.add(new Point(300,600));
        inPoints2.add(new Point(300,640));
        List<Point> outPoints2 = new ArrayList<Point>();
        outPoints2.add(new Point(420,600));
        outPoints2.add(new Point(420,640));
        Properties p2 = new Properties(inPoints2, outPoints2, null,null,null,0);
        CircuitChange c2 = new CircuitChange("CreateComponent", "HalfAdder",1,p2);

        // Create a JKFlipFlopPRECLR
        List<Point> inPoints3 = new ArrayList<Point>();
        List<Point> outPoints3 = new ArrayList<Point>();
        inPoints3.add(new Point(460,160));
        inPoints3.add(new Point(460,180));
        inPoints3.add(new Point(460, 200));
        inPoints3.add(new Point(500,140));
        inPoints3.add(new Point(500,220));
        outPoints3.add(new Point(540,160));
        outPoints3.add(new Point(540,200));
        Properties p3 = new Properties(inPoints3, outPoints3,null,null,null,0);
        CircuitChange c3 = new CircuitChange("CreateComponent", "JKFlipFlopPRECLR",2,p3);

        // Create a FullSubtractor
        List<Point> inPoints4 = new ArrayList<Point>();
        List<Point> outPoints4 = new ArrayList<Point>();
        inPoints4.add(new Point(640,680));
        inPoints4.add(new Point(640,700));
        inPoints4.add(new Point(640,720));
        outPoints4.add(new Point(760,680));
        outPoints4.add(new Point(760,720));
        Properties p4 = new Properties(inPoints4,outPoints4,null,null,null,0);
        CircuitChange c4 = new CircuitChange("CreateComponent","FullSubtractor",3,p4);

        // Create the CircuitModel
        CircuitModel cm = new CircuitModel();
        cm.update(c1);
        cm.update(c2);
        cm.update(c3);
        cm.update(c4);

        // Save the file
        fm.saveToFile(cm,filename);

        // Check if the file exists
        File f = new File(".//test.ser");
        assert(f.exists());

        CircuitModel loadedModel = fm.loadFromFile(filename);

        // Ensure all information is saved
        compare_model(loadedModel,cm);
    }
}
