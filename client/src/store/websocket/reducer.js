import { WS_CONNECTED } from './types';

const initialState = { connected: false };

const websocketReducer = (state = { ...initialState }, action) => {
  switch (action.type) {
    case WS_CONNECTED:
      return { ...state, connected: true };
    default:
      return state;
  }
};

export default websocketReducer;
