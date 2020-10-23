import {
  CREATE_COMPONENT,
  SHOW_COMPONENT_PREVIEW,
  SET_COMPONENT_PREVIEW_TYPE,
  SHOW_COMPONENT_SHADOW,
  HIDE_COMPONENT_SHADOW,
  SET_COMPONENT_SHADOW_TYPE,
  TOGGLE_DRAG,
} from './types';

export const setComponentPreviewType = (componentType) => ({
  type: SET_COMPONENT_PREVIEW_TYPE,
  componentType,
});

export const showComponentPreview = (x, y) => ({
  type: SHOW_COMPONENT_PREVIEW,
  x,
  y,
});

export const showComponentShadow = (x, y) => ({
  type: SHOW_COMPONENT_SHADOW,
  x,
  y,
});

export const hideComponentShadow = () => ({
  type: HIDE_COMPONENT_SHADOW,
});

export const setComponentShadowType = (componentType) => ({
  type: SET_COMPONENT_SHADOW_TYPE,
  componentType,
});

export const createComponent = (componentType, x, y) => ({
  type: CREATE_COMPONENT,
  componentType,
  x,
  y,
});

export const toggleDrag = () => ({
  type: TOGGLE_DRAG,
});
