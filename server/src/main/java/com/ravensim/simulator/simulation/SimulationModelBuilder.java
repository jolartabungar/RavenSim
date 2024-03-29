package com.ravensim.simulator.simulation;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.logic_gate.*;
import com.ravensim.simulator.model.*;
import com.ravensim.simulator.model.Event;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.save_load.FileManager;
import com.ravensim.simulator.signal.Button;
import com.ravensim.simulator.signal.Clock;
import com.ravensim.simulator.subcircuit.*;
import org.springframework.web.socket.WebSocketSession;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SimulationModelBuilder {
  // Event Names
  private static final String START_SIMULATION = "StartSimulation";
  private static final String STOP_SIMULATION = "StopSimulation";
  private static final String LOAD_CIRCUIT = "LoadCircuit";
  private static final String SAVE_CIRCUIT = "SaveCircuit";
  private static final String CREATE_COMPONENT = "CreateComponent";
  private static final String BUTTON_PRESS = "ButtonPress";

  private SimulationEngine simulationEngine;
  // The mapping of all ports on the grid space. It assumes the location of the ports must be
  // unique.
  // todo Maybe create a separate port mediator class?
  private final Map<Point, Port> locationOfPort;
  private final Map<Integer, Button> locationOfButton;

  private CircuitModel model;
  private FileManager fileManager;

  public SimulationModelBuilder(WebSocketSession session) {
    locationOfPort = new ConcurrentHashMap<>();
    locationOfButton = new ConcurrentHashMap<>();
    model = new CircuitModel();
    fileManager = new FileManager();
    simulationEngine = new SimulationEngine(session, this);
    new Thread(simulationEngine).start();
  }

  public void messageReducer(TextMessage message) {
    // Switch depending on whether the message is an event or command.
    if (message instanceof Event) {
      eventReducer((Event) message);
    } else if (message instanceof Command) {
      commandReducer((Command) message);
    } else {
      throw new UnsupportedOperationException(
          String.format("%s is neither an event or command", message));
    }
  }

  private void eventReducer(Event event) {
    // Remodel the simulation based off all changes in the circuit.
    event.getMessage().getCircuitChanges().forEach(this::actionReducer);
  }

  private void commandReducer(Command command) {
    var message = command.getMessage();
    switch (message) {
      case START_SIMULATION:
        // Only start the simulation if it is not already running as the client may invoke start
        // multiple times.
        simulationEngine.startSimulation();
        break;
      case STOP_SIMULATION:
        simulationEngine.shutdown();
        break;
      case BUTTON_PRESS:
        new Thread(locationOfButton.get(69)).start();
        break;
      case LOAD_CIRCUIT:
        System.out.println("Load circuit sequence initiated.");
        loadSave(command.getFileName());
        break;
      case SAVE_CIRCUIT:
        System.out.println("Save circuit sequence initiated.");
        initiateSave(command.getFileName());
        break;
      default:
        throw new UnsupportedOperationException(String.format("%s is an invalid command", message));
    }
  }

  private void actionReducer(CircuitChange change) {
    var action = change.getAction();
    if (action.equals(CREATE_COMPONENT)) {
      createComponentReducer(change);
    } else {
      throw new UnsupportedOperationException(String.format("%s is an invalid action", action));
    }
  }

  private void createComponentReducer(CircuitChange change) {
    // Switch based on the type of component to create.3
    var type = change.getType();
    switch (type) {
      case ComponentType.WIRE:
        createWire(change);
        break;
      case ComponentType.CLOCK:
        createClock(change);
        break;
      case ComponentType.BUTTON:
        createButton(change);
        break;
      default:
        try {
          createLogicGate(change);
        } catch (IncompatibleBitWidthsException
                | InvalidNumberOfPortsException
                | InvalidBitWidthException e) {
          // todo
        }
        break;
    }

    model.update(change);
  }

  private void createWire(CircuitChange change) {
    var from = change.getProperties().getFrom();
    var to = change.getProperties().getTo();
    // todo Explanation.
    connect(change.getId(), from, to);
    connect(change.getId(), to, from);
  }

  private void createClock(CircuitChange change) {
    var port = portCreationHandler(change.getProperties().getOutput());
    new Clock(simulationEngine, port);
  }

  private void createButton(CircuitChange change) {
    var port = portCreationHandler(change.getProperties().getOutput());
    locationOfButton.put(69, new Button(port));
  }

  private void createLogicGate(CircuitChange change)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException,
          InvalidBitWidthException {
    var inputPorts = instantiateInputPorts(change);
    var outputPorts = instantiateOutputPorts(change);
    var type = change.getType();

    switch (type) {
      case ComponentType.AND_GATE:
        new AndGate(simulationEngine, inputPorts, outputPorts.get(0));
        break;
      case ComponentType.NAND_GATE:
        new NandGate(simulationEngine, inputPorts, outputPorts.get(0));
        break;
      case ComponentType.NOR_GATE:
        new NorGate(simulationEngine, inputPorts, outputPorts.get(0));
        break;
      case ComponentType.NOT_GATE:
        new NotGate(simulationEngine, inputPorts.get(0), outputPorts.get(0));
        break;
      case ComponentType.OR_GATE:
        new OrGate(simulationEngine, inputPorts, outputPorts.get(0));
        break;
      case ComponentType.XNOR_GATE:
        new XnorGate(simulationEngine, inputPorts, outputPorts.get(0));
        break;
      case ComponentType.XOR_GATE:
        new XorGate(simulationEngine, inputPorts, outputPorts.get(0));
        break;
      case ComponentType.D_FLIP_FLOP:
        new DFlipFlop(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.RS_FLIP_FLOP:
        new RSFlipFlop(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.JK_FLIP_FLOP:
        new JKFlipFlop(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.JK_FLIP_FLOP_PRE_CLR:
        new JKFlipFlopPRECLR(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.HALF_ADDER:
        new HalfAdder(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.FULL_ADDER:
        new FullAdder(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.HALF_SUBTRACTOR:
        new HalfSubtractor(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.FULL_SUBTRACTOR:
        new FullSubtractor(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.EIGHT_TO_THREE_ENCODER:
        new EighttoThreeEncoder(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.THREE_TO_EIGHT_DECODER:
        new ThreetoEightDecoder(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.TWO_TO_ONE_MUX:
        new TwoToOneMux(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.FOUR_TO_ONE_MUX:
        new FourToOneMux(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.ONE_TO_TWO_DEMUX:
        new OneToTwoDemux(simulationEngine, inputPorts, outputPorts);
        break;
      case ComponentType.ONE_TO_FOUR_DEMUX:
        new OneToFourDemux(simulationEngine, inputPorts, outputPorts);
        break;
      default:
        throw new UnsupportedOperationException(
                String.format("%s is an unimplemented component type", type));
    }
  }

  public void connect(Integer id, Point from, Point to) {
    // Instantiate ports at the to and from position if they do not already exist.
    simulationEngine
        .getVirtualWireMediator()
        .connect(id, portCreationHandler(from), portCreationHandler(to));
  }

  private Port portCreationHandler(Point point) {
    Port port;
    synchronized (locationOfPort) {
      port = locationOfPort.get(point);
      if (port == null) {
        try {
          port = new Port(simulationEngine);
        } catch (InvalidBitWidthException e) {
          // todo
        }
        locationOfPort.put(point, port);
      }
    }
    return port;
  }

  private List<Port> instantiatePorts(CircuitChange change) {
    // Create a port for every input and output.
    List<Port> ports = new ArrayList<>();
    ports.addAll(
        change.getProperties().getInputs().stream()
            .map(this::portCreationHandler)
            .collect(Collectors.toList()));
    ports.addAll(
        change.getProperties().getOutputs().stream()
            .map(this::portCreationHandler)
            .collect(Collectors.toList()));
    return ports;
  }

  private List<Port> instantiateInputPorts(CircuitChange change) {
    // Create a port for every input
    return change.getProperties().getInputs().stream()
            .map(this::portCreationHandler).collect(Collectors.toList());
  }

  private List<Port> instantiateOutputPorts(CircuitChange change) {
    // Create a port for every input
    return change.getProperties().getOutputs().stream()
            .map(this::portCreationHandler).collect(Collectors.toList());
  }

  private void rebuildModel(CircuitModel loadedModel) {
    for (CircuitChange change: loadedModel.getChanges()) {
      this.actionReducer(change);
    }
  }

  // Temporary save and load calls
  // To Do: Allow option for filename input

  public void initiateSave(String fileName) {
    fileManager.saveToFile(model, fileName);
  }

  public void loadSave(String fileName) {
    // Remove everything in the current change list since we are loading a new save
    this.model.clearChanges();

    rebuildModel(fileManager.loadFromFile(fileName));
    simulationEngine.loadCircuit(new CircuitModel(model));
  }
}
