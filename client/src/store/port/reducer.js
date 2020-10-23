import { CREATE_PORTS } from './types';

const initialState = {
  ports: [],
};

const portReducer = (state = { ...initialState }, action) => {
  switch (action.type) {
    case CREATE_PORTS:
      state.ports.push(...action.ports);
      return { ...state };
    default:
      return state;
  }
};

export default portReducer;
