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

import java.util.ArrayList;
import java.util.List;

class XorGateTest {
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
  void constructor_isListeningToAllInputs()
      throws InvalidBitWidthException, InvalidNumberOfPortsException,
          IncompatibleBitWidthsException {
    List<Port> inputs = List.of(new Port(context), new Port(context), new Port(context));
    var output = new Port(context);
    new XorGate(context, inputs, output);
    inputs.forEach(
        port -> {
          try {
            port.receive(new PropagationEvent(0));
          } catch (InvalidSignalException e) {
          }
        });
    Mockito.verify(context.getEventRegistry(), Mockito.times(inputs.size())).add(Mockito.any());
  }

  @Test
  void constructor_invalidNumberOfInputs() {
    Assertions.assertThrows(
        InvalidNumberOfPortsException.class,
        () -> new XorGate(context, List.of(), Mockito.mock(Port.class)));
  }

  @Test
  void update_requestToUpdateIsInvoked()
      throws InvalidBitWidthException, InvalidNumberOfPortsException,
          IncompatibleBitWidthsException {
    new XorGate(context, List.of(new Port(context), new Port(context)), new Port(context))
        .update(new RequestToUpdateEvent());
    Mockito.verify(context.getEventRegistry()).add(Mockito.any());
  }

  @Test
  void run_doNotTransmitIfNoSignalChange()
      throws InvalidBitWidthException, InvalidNumberOfPortsException,
          IncompatibleBitWidthsException {
    var output = Mockito.mock(Port.class);
    Mockito.when(output.getBitWidth()).thenReturn(Port.DEFAULT_BIT_WIDTH);
    new XorGate(context, List.of(new Port(context), new Port(context)), output);
    Mockito.verify(output, Mockito.times(0)).transmit();
  }

  @Test
  void run_transmitIfSignalChange()
      throws InvalidBitWidthException, InvalidNumberOfPortsException,
          IncompatibleBitWidthsException, InvalidSignalException {
    var output = Mockito.spy(new Port(context, Common.BIT_WIDTH));
    var input0 = new Port(context, Common.BIT_WIDTH);
    var input1 = new Port(context, Common.BIT_WIDTH);
    input0.setSignal(Common.randomSignal());
    input1.setSignal(Common.randomSignal());
    new XorGate(context, List.of(input0, input1), output);
    Mockito.verify(output).transmit();
  }

  @RepeatedTest(Common.NUMBER_OF_TEST)
  void run_correctOutput()
      throws InvalidBitWidthException, InvalidNumberOfPortsException,
          IncompatibleBitWidthsException, InvalidSignalException {
    var context = Mockito.mock(SimulationEngine.class, Mockito.RETURNS_DEEP_STUBS);
    var output = new Port(context, Common.BIT_WIDTH);
    List<Port> inputs = new ArrayList<>();
    List<Integer> signals = new ArrayList<>();
    for (int i = 0; i < Common.NUMBER_OF_PORTS; i++) {
      signals.add(Common.randomSignal());
      inputs.add(new Port(context, Common.BIT_WIDTH));
      inputs.get(i).setSignal(signals.get(i));
    }
    new XorGate(context, inputs, output);
    Assertions.assertEquals(
        output.getSignal(),
        signals.stream().skip(1).reduce(signals.get(0), (accumulator, next) -> accumulator ^ next));
  }
}
