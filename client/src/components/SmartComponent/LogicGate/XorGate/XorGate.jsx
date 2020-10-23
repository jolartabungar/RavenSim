import React from 'react';
import XorGateShape from '../../../../containers/Component/ComponentShape/LogicGate/XorGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { XOR_GATE } from '../../../../store/component/types';

/**
 *Creates an Xor Gate using a draggable component wrapping an Xor template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:kajhemmingsen
 */
const XorGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={XOR_GATE}>
    <XorGateShape />
  </DraggableComponent>
);

export default XorGate;
