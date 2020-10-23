import React from 'react';
import NandGateShape from '../../../../containers/Component/ComponentShape/LogicGate/NandGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { NAND_GATE } from '../../../../store/component/types';
/**
 *Creates a Nand Gate using a draggable component wrapping a NandGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:kajhemmingsen
 */
const NandGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={NAND_GATE}>
    <NandGateShape />
  </DraggableComponent>
);

export default NandGate;
