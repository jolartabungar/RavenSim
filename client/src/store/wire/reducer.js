import {
  SHOW_WIRE_PREVIEW,
  CREATE_WIRE,
  SET_WIRE_SIGNAL,
  LOW_SIGNAL,
  HIGH_SIGNAL,
  ERROR_SIGNAL,
} from './types';

const initialState = {
  isTheWirePreviewHidden: true,
  points: undefined,
  wires: new Map(),
};

const mapSignal = (signal) => {
  switch (signal) {
    case 0:
      return LOW_SIGNAL;
    case 1:
      return HIGH_SIGNAL;
    default:
      return ERROR_SIGNAL;
  }
};

const wireReducer = (state = { ...initialState }, action) => {
  switch (action.type) {
    case SHOW_WIRE_PREVIEW:
      return { ...state, isTheWirePreviewHidden: false, points: action.points };
    case CREATE_WIRE:
      state.wires.set(action.id, { points: state.points, signal: LOW_SIGNAL });
      return { ...state, isTheWirePreviewHidden: true };
    case SET_WIRE_SIGNAL: {
      if (state.wires.has(action.id)) {
        state.wires.get(action.id).signal = mapSignal(action.signal);
      }
      return { ...state };
    }
    default:
      return state;
  }
};

export default wireReducer;
