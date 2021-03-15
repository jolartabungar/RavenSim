package com.ravensim.simulator.broker;

import com.google.gson.Gson;
import com.ravensim.simulator.event.Observable;
import com.ravensim.simulator.event.ShutdownEvent;
import com.ravensim.simulator.event.Shutdownable;
import com.ravensim.simulator.model.CircuitModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// This class provides a means to dispatch messages to the client asynchronously.
public class MessageBroker implements Runnable, Observable<Shutdownable> {
  private static final Logger LOGGER = LogManager.getLogger(MessageBroker.class.getSimpleName());
  // The current list of messages queued for transmission to the client.
  private final BlockingQueue<Serializable> messages;
  // The communication mechanism with the client. This variable is optional, as the simulation can
  // run locally. If there is no WebSocket connection, then the message broker will shutdown.
  private final Optional<WebSocketSession> session;
  private final List<Shutdownable> observers;
  private boolean shutdownNow;

  public MessageBroker(WebSocketSession session) {
    messages = new LinkedBlockingQueue<>();
    this.session = Optional.ofNullable(session);
    observers = new ArrayList<>();
    shutdownNow = false;
  }

  public void add(Serializable message) {
    // The insertion may fail due to the size restrictions of the queue; therefore, it should keep
    // trying until it is successful.
    boolean wasSuccess;
    do {
      wasSuccess = messages.offer(message);
    } while (!wasSuccess);
  }

  @Override
  public void run() {
    // Stop the execution if there is no communication mechanism with the client.
    if (session.isEmpty()) {
      return;
    }
    do {
      // Retrieve the most recent message in the queue.
      Object message = null;
      try {
        message = messages.take();
      } catch (InterruptedException e) {
        // tod
      }
      // Send the message only if it exists, and if the session has not closed while polling.
      if (message != null && session.get().isOpen()) {
        try {
          session.get().sendMessage(new TextMessage(new Gson().toJson(message)));
        } catch (IOException e) {
          LOGGER.warn("Broken pipe: stopping the simulation");
        }
      }
      // Shutdown the message broker if the session has closed or if it was forced to shutdown.
    } while (session.get().isOpen() && !shutdownNow);
    observers.stream().forEach(observer -> observer.update(new ShutdownEvent()));
    LOGGER.info("Shutdown complete");
  }

  public void loadCircuit(CircuitModel model) {
    if (session.isEmpty()) {
      return;
    }
    try {
      session.get().sendMessage(new TextMessage(new Gson().toJson(model)));
    } catch (IOException e) {
      LOGGER.warn("Broken pipe: Failed to send circuit model");
    }
  }

  public void shutdownNow() {
    shutdownNow = true;
  }

  @Override
  public void addObserver(Shutdownable observer) {
    observers.add(observer);
  }
}
