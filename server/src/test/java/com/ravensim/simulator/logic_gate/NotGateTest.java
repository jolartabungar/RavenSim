package com.ravensim.simulator.logic_gate;

import com.ravensim.simulator.event.PropagationEvent;
import com.ravensim.simulator.event.RequestToUpdateEvent;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.InvalidSignalException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import com.ravensim.simulator.util.Common;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

class NotGateTest {
  private SimulationEngine context;

  @BeforeEach
  void setUp() {
    context = Mockito.mock(SimulationEngine.class, Mockito.RETURNS_DEEP_STUBS);
  }

  @AfterEach
  void tearDown() {
    context = null;
  }

  @Test
  void constructor_isListeningToInput()
      throws InvalidBitWidthException, IncompatibleBitWidthsException, InvalidSignalException {
    var input = new Port(context);
    var output = new Port(context);
    new NotGate(context, input, output);
    input.receive(new PropagationEvent(0));
    Mockito.verify(context.getEventRegistry()).add(Mockito.any());
  }

  @Test
  void update_requestToUpdateIsInvoked()
      throws InvalidBitWidthException, IncompatibleBitWidthsException {
    new NotGate(context, new Port(context), new Port(context)).update(new RequestToUpdateEvent());
    Mockito.verify(context.getEventRegistry()).add(Mockito.any());
  }

  @Test
  void run_doNotTransmitIfNoSignalChange()
      throws InvalidBitWidthException, IncompatibleBitWidthsException {
    var output = Mockito.mock(Port.class);
    Mockito.when(output.getBitWidth()).thenReturn(Port.DEFAULT_BIT_WIDTH);
    new NotGate(context, new Port(context), output);
    Mockito.verify(output, Mockito.times(0)).transmit();
  }

  @Test
  void run_transmitIfSignalChange()
      throws InvalidBitWidthException, InvalidNumberOfPortsException,
          IncompatibleBitWidthsException, InvalidSignalException {
    var output = Mockito.spy(new Port(context, Common.BIT_WIDTH));
    var input = new Port(context, Common.BIT_WIDTH);
    input.setSignal(Common.randomSignal());
    new NotGate(context, input, output);
    Mockito.verify(output).transmit();
  }

  @RepeatedTest(Common.NUMBER_OF_TEST)
  void run_correctOutput()
      throws InvalidBitWidthException, InvalidNumberOfPortsException,
          IncompatibleBitWidthsException, InvalidSignalException {
    var context = Mockito.mock(SimulationEngine.class, Mockito.RETURNS_DEEP_STUBS);
    var output = new Port(context, Common.BIT_WIDTH);
    var input = new Port(context, Common.BIT_WIDTH);
    var signal = Common.randomSignal();
    input.setSignal(signal);
    new NotGate(context, input, output);
    Assertions.assertEquals(output.getSignal(), ~signal);
  }
}
