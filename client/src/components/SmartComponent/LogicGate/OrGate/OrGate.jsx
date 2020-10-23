import React from 'react';
import OrGateShape from '../../../../containers/Component/ComponentShape/LogicGate/OrGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { OR_GATE } from '../../../../store/component/types';

/**
 *Creates an Or Gate using a draggable component wrapping an OrGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:kajhemmingsen
 */
const OrGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={OR_GATE}>
    <OrGateShape />
  </DraggableComponent>
);

export default OrGate;
