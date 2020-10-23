package com.ravensim.simulator.handler;

import com.ravensim.simulator.simulation.SimulationModelBuilder;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionMap {

  public static SessionMap theInstance;

  private Map<WebSocketSession, SimulationModelBuilder> sessionToSimulationModel;

  private SessionMap() {
    sessionToSimulationModel = new ConcurrentHashMap<>();
  }

  public static SessionMap getInstance() {
    if (theInstance == null) {
      theInstance = new SessionMap();
    }
    return theInstance;
  }

  public SimulationModelBuilder get(WebSocketSession session) {
    return sessionToSimulationModel.get(session);
  }

  public void put(WebSocketSession session) {
    // Create a new simulation model if one does not already exist with this session.
    if (!sessionToSimulationModel.containsKey(session)) {
      sessionToSimulationModel.put(session, new SimulationModelBuilder(session));
    }
  }
}
