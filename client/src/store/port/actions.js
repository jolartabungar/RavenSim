import { CLEAR_PORTS, CREATE_PORTS } from './types';

// eslint-disable-next-line import/prefer-default-export
export const createPorts = (ports) => ({
  type: CREATE_PORTS,
  ports,
});

export const clearPorts = () => ({
  type: CLEAR_PORTS,
});
