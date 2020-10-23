import React from 'react';
import NorGateShape from '../../../../containers/Component/ComponentShape/LogicGate/NorGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { NOR_GATE } from '../../../../store/component/types';
/**
 *Creates a Nor Gate using a draggable component wrapping a NorGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:kajhemmingsen
 */
const NorGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={NOR_GATE}>
    <NorGateShape />
  </DraggableComponent>
);

export default NorGate;
