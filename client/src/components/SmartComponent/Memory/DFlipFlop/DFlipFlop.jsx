import React from 'react';
import DFlipFlopShape from '../../../../containers/Component/ComponentShape/Memory/DFlipFlopShape';
import DraggableComponent from '../../../DraggableComponent';
import { D_FLIP_FLOP } from '../../../../store/component/types';
/**
 * Creates a draggable version of the DFlipFlopShape for the user to interact with
 * @author: kajhemmingsen
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const DFlipFlop = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={D_FLIP_FLOP}>
    <DFlipFlopShape />
  </DraggableComponent>
);

export default DFlipFlop;
