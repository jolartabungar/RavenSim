import { SHOW_WIRE_PREVIEW, CREATE_WIRE, SET_WIRE_SIGNAL, CLEAR_WIRES } from './types';

export const showWirePreview = (points) => ({
  type: SHOW_WIRE_PREVIEW,
  points,
});

export const createWire = (points) => ({
  type: CREATE_WIRE,
  points,
});

export const clearWires = () => ({
  type: CLEAR_WIRES,
});

export const setWireSignal = (id, signal) => ({
  type: SET_WIRE_SIGNAL,
  id,
  signal,
});
