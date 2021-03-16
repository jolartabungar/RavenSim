import React from 'react';
import ThreeInputOrGateShape from '../../../../containers/Component/ComponentShape/LogicGate/ThreeInputOrGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { THREE_INPUT_OR_GATE } from '../../../../store/component/types';

/**
 *Creates an Or Gate using a draggable component wrapping an OrGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:maryoji
 */
const ThreeInputOrGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={THREE_INPUT_OR_GATE}>
    <ThreeInputOrGateShape />
  </DraggableComponent>
);

export default ThreeInputOrGate;