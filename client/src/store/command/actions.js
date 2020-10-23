import {
  START_SIMULATION,
  STOP_SIMTULATION,
  TICK,
  COMMAND,
  TOGGLE_POKE,
  BUTTON_PRESS,
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
