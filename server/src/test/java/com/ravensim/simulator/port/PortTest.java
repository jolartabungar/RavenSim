package com.ravensim.simulator.port;

import com.ravensim.simulator.event.PortObserver;
import com.ravensim.simulator.event.PropagationEvent;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PortTest {

  private Port port;

  @BeforeEach
  void setUp() throws InvalidBitWidthException {
    port = new Port(Mockito.mock(SimulationEngine.class));
  }

  @AfterEach
  void tearDown() {
    port = null;
  }

  @Test
  void constructor_testDefaultBitWidth() {
    Assertions.assertEquals(port.getBitWidth(), Port.DEFAULT_BIT_WIDTH);
  }

  @Test
  void constructor_greaterThanMaxBitWidth() {
    Assertions.assertThrows(
        InvalidBitWidthException.class,
        () -> new Port(Mockito.mock(SimulationEngine.class), BitSetAdapter.MAX_BIT_WIDTH + 1));
  }

  @Test
  void constructor_bitWidthIsMax() {
    Assertions.assertDoesNotThrow(
        () -> new Port(Mockito.mock(SimulationEngine.class), BitSetAdapter.MAX_BIT_WIDTH));
  }

  @Test
  void constructor_bitWidthIsZero() {
    Assertions.assertThrows(
        InvalidBitWidthException.class, () -> new Port(Mockito.mock(SimulationEngine.class), 0));
  }

  @Test
  void constructor_bitWidthIsLessThanZero() {
    Assertions.assertThrows(
        InvalidBitWidthException.class, () -> new Port(Mockito.mock(SimulationEngine.class), -1));
  }

  @Test
  void receive_signalIsSet() throws InvalidSignalException {
    var signal = 0b1;
    port.receive(new PropagationEvent(signal));
    Assertions.assertEquals(port.getSignal(), signal);
  }

  @Test
  void receive_signalWillCauseOverflow() {
    Assertions.assertThrows(
        InvalidSignalException.class, () -> port.receive(new PropagationEvent(0b11)));
  }

  @Test
  void transmit_virtualWireTransmitIsCalled() throws InvalidBitWidthException {
    var simulationContext = Mockito.mock(SimulationEngine.class, Mockito.RETURNS_DEEP_STUBS);
    var virtualWireMediator = simulationContext.getVirtualWireMediator();
    var port = new Port(simulationContext);
    port.transmit();
    // Must use any as the parameter since the event is instantiated inside the transmit method;
    // therefore, it will have a unique hash.
    Mockito.verify(virtualWireMediator).transmit(Mockito.eq(port), Mockito.any());
  }

  @Test
  void receive_listenersAreNotified() throws InvalidSignalException {
    var listener = Mockito.mock(PortObserver.class);
    port.addObserver(listener);
    port.receive(Mockito.mock(PropagationEvent.class));
    Mockito.verify(listener).update(Mockito.any());
  }

  @Test
  void addListener_listenerIsAddedWithNoInteraction() {
    var listener = Mockito.mock(PortObserver.class);
    port.addObserver(listener);
    Mockito.verifyNoInteractions(listener);
  }
}
