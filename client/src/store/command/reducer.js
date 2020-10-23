import { TICK, TOGGLE_POKE } from './types';

const initialState = {
  isOn: false,
  isPoke: false,
};

const commandReducer = (state = { ...initialState }, action) => {
  switch (action.message) {
    case TICK:
      return { ...state, isOn: !state.isOn };
    case TOGGLE_POKE:
      return { ...state, isPoke: !state.isPoke };
    default:
      return state;
  }
};

export default commandReducer;
