import {
  CREATE_COMPONENT,
  CLEAR_GRID,
  SHOW_COMPONENT_PREVIEW,
  SET_COMPONENT_PREVIEW_TYPE,
  SHOW_COMPONENT_SHADOW,
  HIDE_COMPONENT_SHADOW,
  SET_COMPONENT_SHADOW_TYPE,
} from './types';

const initialState = {
  // The current component being dragged from the side bar.
  sidebar: {
    isHidden: true,
    x: undefined,
    y: undefined,
    type: undefined,
  },
  // A list of components on the grid.
  grid: [],
  // The current shadow displayed from moving a component on the grid space.
  shadow: {
    isHidden: true,
    x: undefined,
    y: undefined,
    type: undefined,
  },
};

const componentReducer = (state = { ...initialState }, action) => {
  const { sidebar, grid, shadow } = state;
  switch (action.type) {
    case CREATE_COMPONENT: {
      const { x, y, componentType } = action;
      grid.push({
        type: componentType,
        x,
        y,
      });
      return {
        ...state,
        sidebar: {
          isHidden: true,
          type: undefined,
          x: undefined,
          y: undefined,
        },
      };
    }
    case CLEAR_GRID: {
      return {
        ...state,
        grid: [],
      };
    }
    case SHOW_COMPONENT_PREVIEW:
      return {
        ...state,
        sidebar: {
          ...sidebar,
          x: action.x,
          y: action.y,
          isHidden: false,
        },
      };
    case SET_COMPONENT_PREVIEW_TYPE:
      return {
        ...state,
        sidebar: { ...sidebar, type: action.componentType },
      };
    case SHOW_COMPONENT_SHADOW:
      return {
        ...state,
        shadow: {
          ...shadow,
          x: action.x,
          y: action.y,
          isHidden: false,
        },
      };
    case SET_COMPONENT_SHADOW_TYPE:
      return {
        ...state,
        shadow: { ...shadow, type: action.componentType },
      };
    case HIDE_COMPONENT_SHADOW:
      return {
        ...state,
        shadow: {
          ...shadow,
          isHidden: true,
          type: undefined,
        },
      };
    default:
      return state;
  }
};

export default componentReducer;
