package com.ravensim.simulator.simulation;

import com.ravensim.simulator.broker.MessageBroker;
import com.ravensim.simulator.event.EventRegistry;
import com.ravensim.simulator.event.ShutdownEvent;
import com.ravensim.simulator.event.Shutdownable;
import com.ravensim.simulator.handler.SessionMap;
import com.ravensim.simulator.signal.TickerGenerator;
import com.ravensim.simulator.wire.VirtualWireMediator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.Session;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements Runnable, Shutdownable, Serializable {
  // The polling timeout is the amount of time before awaiting the next simulated event to occur.
  // The simulation will shutdown upon timing out.
  public static final int POLLING_TIMEOUT = 5000;
  // The shutdown timeout is the amount of time the executor service will block before awaiting all
  // tasks to finish execution. The executor service will force shutdown all jobs upon timing out.
  public static final int SHUTDOWN_TIMEOUT = 5000;
  private static final Logger LOGGER = LogManager.getLogger(SimulationEngine.class.getSimpleName());
  private static final int INITIAL_SIMULATION_TIME = 0;
  private final ExecutorService threadPool;
  private final VirtualWireMediator virtualWireMediator;
  private final TickerGenerator tickerGenerator;
  private final EventRegistry eventRegistry;
  private final MessageBroker messageBroker;
  private int time;
  private boolean isRunning, hasStarted;
  private final SimulationModelBuilder modelRef;

  public SimulationEngine(SimulationModelBuilder modelRef) {
    this(null, modelRef);
  }

  public SimulationEngine(WebSocketSession session, SimulationModelBuilder modelRef) {
    this.modelRef = modelRef;
    time = INITIAL_SIMULATION_TIME;
    threadPool = Executors.newWorkStealingPool();
    isRunning = false;
    eventRegistry = new EventRegistry(this);
    // Pass in the current communication mechanism with the client to the message broker. The
    // session may be null as the simulation can be run without a WebSocket connection.
    messageBroker = new MessageBroker(session);
    // Subscribe to events from the message broker as we will need to know when the client has
    // started or stopped the simulation.
    messageBroker.addObserver(this);
    // Pass a reference to the message broker so the wires can indicate when their signal changes to
    // the client.
    virtualWireMediator = new VirtualWireMediator(this);
    // todo The client should be able to specify the tick frequency.
    tickerGenerator = new TickerGenerator(this);
    threadPool.submit(messageBroker);
    hasStarted = false;
  }

  @Override
  public void run() {
    isRunning = true;
    while (isRunning) {
      // Retrieve the latest events.
      var events = eventRegistry.retrieveTheLatestEvents(POLLING_TIMEOUT, TimeUnit.MILLISECONDS);
      // The optional is empty if the polling times out.
      if (events.isPresent()) {
        LOGGER.info(String.format("Executing the following event(s): %s", events));
        // Set the simulation time to the scheduled time of the most recent event,
        time = events.get().get(events.get().size() - 1).getScheduledTime();
        LOGGER.info(String.format("The new simulation time is %d", time));
        // Invoke an update on the source of each event
        events
            .get()
            .parallelStream()
            .forEach(
                event -> {
                  // Do not invoke an update on the source if the thread pool is in the process of
                  // shutting down.
                  if (!threadPool.isShutdown()) {
                    threadPool.execute(event.getSource());
                  }
                });
      } else if (hasStarted && isRunning) {
        // Check to see if the simulation is still running, as another thread may have already
        // invoked shutdown. The shutdown will occur if there are no more events in the queue or the
        // client has requested a halt to the simulation.
        shutdown();
      }
    }
  }

  public void shutdown() {

    // First modify the shutdown field as we want all other threads executing services provided by
    // this class to halt.
    isRunning = false;
    // This runnable routine blocks while awaiting the termination of the executor; therefore, it
    // should be executed concurrently while shutting down other services.
    new Thread(
        () -> {
          try {
            threadPool.shutdown();
            threadPool.awaitTermination(SHUTDOWN_TIMEOUT, TimeUnit.MILLISECONDS);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } finally {
            threadPool.shutdownNow();
          }
        });
    // Save sequence goes here
    System.out.println(modelRef.getLocationOfButton());
    System.out.println(modelRef.getLocationOfPort());

    try {
      FileOutputStream fos = new FileOutputStream(".//test.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(modelRef.getLocationOfButton());
      oos.writeObject(modelRef.getLocationOfPort());
    } catch (IOException ioe){
      ioe.printStackTrace();
    }

    // Shutdown the ticker and message broker.
    tickerGenerator.shutdownNow();
    messageBroker.shutdownNow();
    LOGGER.info("Shutdown complete");
  }

  public void startSimulation() {
    // Start the ticker and message broker.
    if (!hasStarted) {
      hasStarted = true;
      threadPool.submit(tickerGenerator);
    }
  }

  public boolean hasStarted() {
    return hasStarted;
  }

  public MessageBroker getMessageBroker() {
    return messageBroker;
  }

  public TickerGenerator getTickerGenerator() {
    return tickerGenerator;
  }

  public VirtualWireMediator getVirtualWireMediator() {
    return virtualWireMediator;
  }

  public EventRegistry getEventRegistry() {
    return eventRegistry;
  }

  public int getTime() {
    return time;
  }

  @Override
  public void update(ShutdownEvent event) {
    shutdown();
  }
}
