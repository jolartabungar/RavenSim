import React from 'react';
import ThreeInputAndGateShape from '../../../../containers/Component/ComponentShape/LogicGate/ThreeInputAndGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { THREE_INPUT_AND_GATE } from '../../../../store/component/types';
/**
 *Creates an And Gate using a draggable component wrapping an ThreeInputAndGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:MaryOji
 */
const ThreeInputAndGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={THREE_INPUT_AND_GATE}>
    <ThreeInputAndGateShape />
  </DraggableComponent>
);

export default ThreeInputAndGate;
