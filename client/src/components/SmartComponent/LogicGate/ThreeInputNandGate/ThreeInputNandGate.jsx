import React from 'react';
import ThreeInputNandGateShape from '../../../../containers/Component/ComponentShape/LogicGate/ThreeInputNandGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { THREE_INPUT_NAND_GATE } from '../../../../store/component/types';
/**
 *Creates a Nand Gate using a draggable component wrapping a NandGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:kajhemmingsen
 */
const ThreeInputNandGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={THREE_INPUT_NAND_GATE}>
    <ThreeInputNandGateShape />
  </DraggableComponent>
);

export default ThreeInputNandGate;
