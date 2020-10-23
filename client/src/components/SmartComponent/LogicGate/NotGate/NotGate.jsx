import React from 'react';
import DraggableComponent from '../../../DraggableComponent';
import { NOT_GATE } from '../../../../store/component/types';
import NotGateShape from '../../../../containers/Component/ComponentShape/LogicGate/NotGateShape';
/**
 *Creates a Not Gate using a draggable component wrapping a NotGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:kyhorne
 */
const NotGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={NOT_GATE}>
    <NotGateShape />
  </DraggableComponent>
);

export default NotGate;
