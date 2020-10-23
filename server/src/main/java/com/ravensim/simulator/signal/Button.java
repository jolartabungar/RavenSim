package com.ravensim.simulator.signal;

import com.ravensim.simulator.port.BitIndexOutOfBoundsException;
import com.ravensim.simulator.port.Port;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Button implements Runnable {

  private static final Logger LOGGER = LogManager.getLogger(Clock.class.getSimpleName());
  private final Port output;

  public Button(Port output) {
    LOGGER.info(String.format("New button"));
    this.output = output;
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
    LOGGER.info(String.format("Transmitting signal from button"));
    output.transmit();
  }
}
