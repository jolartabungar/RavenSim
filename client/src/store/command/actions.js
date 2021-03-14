import {
  START_SIMULATION,
  STOP_SIMTULATION,
  TICK,
  COMMAND,
  TOGGLE_POKE,
  BUTTON_PRESS,
  LOAD_CIRCUIT,
  SAVE_CIRCUIT
} from './types';

export const startSimulation = () => ({
  type: COMMAND,
  message: START_SIMULATION,
});

export const togglePoke = () => ({
  type: COMMAND,
  message: TOGGLE_POKE,
});

export const stopSimulation = () => ({
  type: COMMAND,
  message: STOP_SIMTULATION,
});

export const buttonPress = () => ({
  type: COMMAND,
  message: BUTTON_PRESS,
});

export const tick = () => ({
  type: COMMAND,
  message: TICK,
});

export const loadCircuit = (name) => ({
  type: COMMAND,
  message: LOAD_CIRCUIT,
  fileName: name,
});

export const saveCircuit = (name) => ({
  type: COMMAND,
  message: SAVE_CIRCUIT,
  fileName: name,
});
