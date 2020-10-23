package com.ravensim.simulator.signal;

import com.ravensim.simulator.port.BitIndexOutOfBoundsException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class Clock extends Signal {
  private static final Logger LOGGER = LogManager.getLogger(Clock.class.getSimpleName());
  // The identifier associated with this clock.
  private final UUID id;

  public Clock(SimulationEngine simulationEngine, Port output) {
    // todo Ensure that the port has a bit width of one.
    super(simulationEngine, output);
    // Generate a random identifier upon instantiation.
    id = UUID.randomUUID();
    LOGGER.info(String.format("Instantiated a new clock with id: %s", id));
  }

  @Override
  public void run() {
    // Alternate between high and low signals.
    try {
      if (output.getBit(0)) {
        output.clear();
      } else {
        output.setBit(0);
      }
    } catch (BitIndexOutOfBoundsException e) {
      // Do nothing.
    }
    LOGGER.info(String.format("Transmitting a new signal from: %s with signal: %s", id, output));
    output.transmit();
  }

  @Override
  public String toString() {
    return String.format("Clock: %s", id);
  }
}
