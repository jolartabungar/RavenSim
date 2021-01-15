package com.ravensim.simulator.simulation;

import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.logic_gate.*;
import com.ravensim.simulator.model.CircuitChange;
import com.ravensim.simulator.model.Command;
import com.ravensim.simulator.model.Event;
import com.ravensim.simulator.model.TextMessage;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.signal.Button;
import com.ravensim.simulator.signal.Clock;
import com.ravensim.simulator.subcircuit.DFlipFlop;
import org.springframework.web.socket.WebSocketSession;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SimulationModelBuilder {
  private static final String START_SIMULATION = "StartSimulation";
  private static final String STOP_SIMULATION = "StopSimulation";
  private static final String CREATE_COMPONENT = "CreateComponent";
  private static final String WIRE = "Wire";
  private static final String AND_GATE = "AndGate";
  private static final String NAND_GATE = "NandGate";
  private static final String NOR_GATE = "NorGate";
  private static final String NOT_GATE = "NotGate";
  private static final String OR_GATE = "OrGate";
  private static final String XNOR_GATE = "XnorGate";
  private static final String XOR_GATE = "XorGate";
  private static final String CLOCK = "Clock";
  private static final String D_FLIP_FLOP = "DFlipFlop";
  private static final String BUTTON = "InputButton";
  private static final String BUTTON_PRESS = "ButtonPress";
  private SimulationEngine simulationEngine;
  // The mapping of all ports on the grid space. It assumes the location of the ports must be
  // unique.
  // todo Maybe create a separate port mediator class?
  private final Map<Point, Port> locationOfPort;
  private final Map<Integer, Button> locationOfButton;
  private Thread engineThread;

  public SimulationModelBuilder(WebSocketSession session) {
    locationOfPort = new ConcurrentHashMap<>();
    locationOfButton = new ConcurrentHashMap<>();
    initializeSimulationEngine(session);
  }

  public SimulationModelBuilder(WebSocketSession session, Map<Point, Port> portMap, Map<Integer, Button> buttonMap) {
    locationOfPort = portMap;
    locationOfButton = buttonMap;
    initializeSimulationEngine(session);
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

  private void initializeSimulationEngine(WebSocketSession session) {
    simulationEngine = new SimulationEngine(session, this);
    engineThread = new Thread(simulationEngine);
    engineThread.start();
  }

  private void eventReducer(Event event) {
    // Remodel the simulation based off all changes in the circuit.
    event.getMessage().getCircuitChanges().forEach(this::actionReducer);
  }

  private void commandReducer(Command command) {
    var message = command.getMessage();
    if (message.equals(START_SIMULATION)) {
      // Only start the simulation if it is not already running as the client may invoke start
      // multiple times.
      simulationEngine.startSimulation();
    } else if (message.equals(STOP_SIMULATION)) {
      simulationEngine.shutdown();
    } else if (message.equals(BUTTON_PRESS)) {
      new Thread(locationOfButton.get(69)).start();
    } else {
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
    if (type.equals(WIRE)) {
      createWire(change);
    } else if (type.equals(CLOCK)) {
      createClock(change);
    } else if (type.equals(BUTTON)) {
      createButton(change);
    } else {
      try {
        createLogicGate(change);
      } catch (IncompatibleBitWidthsException
          | InvalidNumberOfPortsException
          | InvalidBitWidthException e) {
        // todo
      }
    }
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
    var ports = instantiatePorts(change);
    var size = ports.size();
    var type = change.getType();
    if (type.equals(AND_GATE)) {
      new AndGate(simulationEngine, ports.subList(0, size - 1), ports.get(size - 1));
    } else if (type.equals(NAND_GATE)) {
      new NandGate(simulationEngine, ports.subList(0, size - 1), ports.get(size - 1));
    } else if (type.equals(NOR_GATE)) {
      new NorGate(simulationEngine, ports.subList(0, size - 1), ports.get(size - 1));
    } else if (type.equals(NOT_GATE)) {
      new NotGate(simulationEngine, ports.get(0), ports.get(1));
    } else if (type.equals(OR_GATE)) {
      new OrGate(simulationEngine, ports.subList(0, size - 1), ports.get(size - 1));
    } else if (type.equals(XNOR_GATE)) {
      new XnorGate(simulationEngine, ports.subList(0, size - 1), ports.get(size - 1));
    } else if (type.equals(XOR_GATE)) {
      new XorGate(simulationEngine, ports.subList(0, size - 1), ports.get(size - 1));
    } else if (type.equals(D_FLIP_FLOP)) {
      new DFlipFlop(simulationEngine, ports.subList(0, 2), ports.subList(2, size));
    } else {
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

  public Map<Point, Port> getLocationOfPort(){
    return locationOfPort;
  }

  public Map<Integer, Button> getLocationOfButton(){
    return locationOfButton;
  }
}
