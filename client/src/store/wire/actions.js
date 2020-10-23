import { SHOW_WIRE_PREVIEW, CREATE_WIRE, SET_WIRE_SIGNAL } from './types';

export const showWirePreview = (points) => ({
  type: SHOW_WIRE_PREVIEW,
  points,
});

export const createWire = (points) => ({
  type: CREATE_WIRE,
  points,
});

export const setWireSignal = (id, signal) => ({
  type: SET_WIRE_SIGNAL,
  id,
  signal,
});
