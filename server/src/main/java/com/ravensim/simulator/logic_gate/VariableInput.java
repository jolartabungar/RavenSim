package com.ravensim.simulator.logic_gate;

import com.ravensim.simulator.boolean_function.BooleanFunction;
import com.ravensim.simulator.boolean_function.SimpleBooleanFunction;
import com.ravensim.simulator.io.IncompatibleBitWidthsException;
import com.ravensim.simulator.io.InvalidNumberOfPortsException;
import com.ravensim.simulator.io.ManyInputsOneOutput;
import com.ravensim.simulator.port.InvalidBitWidthException;
import com.ravensim.simulator.port.Port;
import com.ravensim.simulator.simulation.SimulationEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

// An abstract class defining the skeleton of an algorithm to instantiate a logic gate with a
// variable number of inputs.
public abstract class VariableInput extends LogicGate<ManyInputsOneOutput> {

  private static final Logger LOGGER = LogManager.getLogger(VariableInput.class.getSimpleName());

  public VariableInput(SimulationEngine simulationEngine, List<Port> inputs, Port output)
      throws IncompatibleBitWidthsException, InvalidNumberOfPortsException {
    super(simulationEngine, new ManyInputsOneOutput(inputs, output));
  }

  // A recursive method that instantiates the boolean function of a logic gate with a variable
  // number of inputs; utilizing, the behavior defined in the subclasses.
  //
  // There is two feasible base case:
  // 1. The logic gate has two inputs. This is the simplest case as it does not require any
  // recursive calls.
  // 2. The logic gate has more than two inputs. This case requires that two-input boolean functions
  // are cascaded insofar there are enough inputs to satisfy the desired behavior, utilizing link
  // ports to connect the cascaded boolean functions.
  //
  // Finally, there are two possible recursive cases:
  // 1. The initial recursive call. This case will utilize the first two inputs to decorate the
  // boolean function with the desired behaviour and create a link port as the output to connect to
  // the subsequent boolean functions.
  // 2. The intermediate recursive calls. This case will utilize one input and the output of the
  // previous boolean function as inputs. And instantiate a link port as output to succeeding
  // boolean functions.
  private BooleanFunction bluePrint(BooleanFunction logicGate, Port previousLink, int inputIndex) {
    // The base cases.
    var defaultNumberOfInputs = 2;
    if (io.getInputs().size() == defaultNumberOfInputs) {
      try {
        return defaultCase(logicGate);
      } catch (IncompatibleBitWidthsException e) {
        // todo
      }
    }
    if (inputIndex + 1 == io.getInputs().size()) {
      try {
        return baseCase(logicGate, previousLink, inputIndex);
      } catch (IncompatibleBitWidthsException | InvalidBitWidthException e) {
        // todo
      }
    }
    // The recursive cases.
    BooleanFunction wrappedLogicGate = null;
    Port newLink = null;
    try {
      newLink = new Port(context, io.getCollectiveBitWidth());
    } catch (InvalidBitWidthException e) {
      // Do nothing.
    }
    if (logicGate instanceof SimpleBooleanFunction) {
      try {
        wrappedLogicGate = initialStep(logicGate, newLink, inputIndex++);
      } catch (IncompatibleBitWidthsException e) {
        // todo
      }
    } else {
      try {
        wrappedLogicGate = intermediateStep(logicGate, previousLink, newLink, inputIndex);
      } catch (IncompatibleBitWidthsException e) {
        // todo
      }
    }
    // The recursive call.
    return bluePrint(wrappedLogicGate, newLink, ++inputIndex);
  }

  @Override
  protected void listenToAllInputs() {
    io.getInputs().stream().forEach(input -> input.addObserver(this));
  }

  @Override
  public BooleanFunction buildLogicGate(BooleanFunction logicGate) {
    return bluePrint(logicGate, null, 0);
  }

  protected abstract BooleanFunction baseCase(
      BooleanFunction logicGate, Port previousLink, int inputIndex)
      throws InvalidBitWidthException, IncompatibleBitWidthsException;

  protected abstract BooleanFunction defaultCase(BooleanFunction logicGate)
      throws IncompatibleBitWidthsException;

  protected abstract BooleanFunction initialStep(
      BooleanFunction logicGate, Port newLink, int inputIndex)
      throws IncompatibleBitWidthsException;

  protected abstract BooleanFunction intermediateStep(
      BooleanFunction logicGate, Port previousLink, Port newLink, int inputIndex)
      throws IncompatibleBitWidthsException;

  @Override
  public void run() {
    var oldSignal = io.getOutput().getSignal();
    booleanFunction.evaluate();
    var newSignal = io.getOutput().getSignal();
    // Transmit if there is a change in the output signal.
    if (newSignal == oldSignal) {
      LOGGER.info(
          String.format(
              "Skipping transmission from logic gate %s as there is no change in its output signal.",
              this));
    } else {
      io.getOutput().transmit();
    }
  }
}
