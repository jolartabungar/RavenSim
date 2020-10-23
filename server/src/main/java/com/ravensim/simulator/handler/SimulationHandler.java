package com.ravensim.simulator.handler;

import com.google.gson.GsonBuilder;
import com.ravensim.simulator.model.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SimulationHandler extends TextWebSocketHandler {
  private static final Logger LOGGER =
      LogManager.getLogger(SimulationHandler.class.getSimpleName());

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    LOGGER.info("A new connection has been established with: " + session.getRemoteAddress());
    SessionMap.getInstance().put(session);
  }

  @Override
  protected void handleTextMessage(
      WebSocketSession session, org.springframework.web.socket.TextMessage message)
      throws Exception {
    var gson =
        new GsonBuilder()
            .registerTypeAdapter(TextMessage.class, new TextMessage.TextMessageDeserializer())
            .create();
    // Get the simulation model mapped to this session and pass in the message payload.
    // todo The simulation model should be mapped to the circuit ID for concurrent use.
    SessionMap.getInstance()
        .get(session)
        .messageReducer(gson.fromJson(message.getPayload(), TextMessage.class));
  }
}
