import React from 'react';
import AndGateShape from '../../../../containers/Component/ComponentShape/LogicGate/AndGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { AND_GATE } from '../../../../store/component/types';
/**
 *Creates an And Gate using a draggable component wrapping an AndGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:kyhorne
 */
const AndGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={AND_GATE}>
    <AndGateShape />
  </DraggableComponent>
);

export default AndGate;
