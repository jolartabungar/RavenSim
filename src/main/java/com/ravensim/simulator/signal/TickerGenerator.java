package com.ravensim.simulator.signal;

import com.ravensim.simulator.message_factory.TickEventMessage;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TickerGenerator implements Runnable {
  private static final Logger LOGGER = LogManager.getLogger(TickerGenerator.class.getSimpleName());
  private static final int DEFAULT_TICK_FREQUENCY = 1000;
  private final List<Signal> listeners;
  private final ExecutorService threadPool;
  private final SimulationEngine context;
  private boolean shutdownNow;

  public TickerGenerator(SimulationEngine context) {
    listeners = new ArrayList<>();
    threadPool = Executors.newWorkStealingPool();
    shutdownNow = false;
    this.context = context;
  }

  public void addListener(Signal listener) {
    synchronized (listeners) {
      listeners.add(listener);
    }
  }

  public void removeListener(Signal listener) {
    synchronized (listeners) {
      listeners.remove(listener);
    }
  }

  @Override
  public void run() {
    while (!shutdownNow && isRunning()) {
      LOGGER.info("Tick");
      // Notify the client of the tick.
      new TickEventMessage(context);
      // Submit each listener for execution concurrently.
      synchronized (listeners) {
        listeners.parallelStream().forEach(threadPool::submit);
      }
      try {
        TimeUnit.MILLISECONDS.sleep(DEFAULT_TICK_FREQUENCY);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    shutdown();
  }

  private boolean isRunning() {
    // The ticker generator will run as long as there are listeners.
    int size;
    synchronized (listeners) {
      size = listeners.size();
    }
    return size != 0;
  }

  private void shutdown() {
    // Shutdown the thread pool.
    try {
      threadPool.shutdown();
      threadPool.awaitTermination(SimulationEngine.SHUTDOWN_TIMEOUT, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      threadPool.shutdownNow();
    }
    LOGGER.info("Shutdown complete");
  }

  public void shutdownNow() {
    shutdownNow = true;
  }
}
