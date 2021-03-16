import React from 'react';
import ThreeInputNorGateShape from '../../../../containers/Component/ComponentShape/LogicGate/ThreeInputNorGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { THREE_INPUT_NOR_GATE } from '../../../../store/component/types';
/**
 *Creates a Nor Gate using a draggable component wrapping a ThreeInputNorGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:maryoji
 */
const NorGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={THREE_INPUT_NOR_GATE}>
    <ThreeInputNorGateShape />
  </DraggableComponent>
);

export default ThreeInputNorGate;
