import React from 'react';
import JKFlipFlopShape from '../../../../containers/Component/ComponentShape/Memory/JKFlipFlopShape';
import DraggableComponent from '../../../DraggableComponent';
import { JK_FLIP_FLOP } from '../../../../store/component/types';
/**
 * Creates a draggable version of the JKFlipFlopShape for the user to interact with
 * @author: kajhemmingsen
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const JKFlipFlop = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={JK_FLIP_FLOP}>
    <JKFlipFlopShape />
  </DraggableComponent>
);

export default JKFlipFlop;
