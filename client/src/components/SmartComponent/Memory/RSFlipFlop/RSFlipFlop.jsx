import React from 'react';
import RSFlipFlopShape from '../../../../containers/Component/ComponentShape/Memory/RSFlipFlopShape';
import DraggableComponent from '../../../DraggableComponent';
import { RS_FLIP_FLOP } from '../../../../store/component/types';
/**
 * Creates a draggable version of the RSFlipFlopShape for the user to interact with
 * @author: kajhemmingsen
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const RSFlipFlop = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={RS_FLIP_FLOP}>
    <RSFlipFlopShape />
  </DraggableComponent>
);

export default RSFlipFlop;
