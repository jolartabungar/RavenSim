import React from 'react';

import TFlipFlopShape from '../../../../containers/Component/ComponentShape/Memory/TFlipFlopShape';
import DraggableComponent from '../../../DraggableComponent';
import { T_FLIP_FLOP } from '../../../../store/component/types';
/**
 * Creates a draggable version of the TFlipFlopShape for the user to interact with
 * @author: kajhemmingsen
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const TFlipFlop = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={T_FLIP_FLOP}>
    <TFlipFlopShape />
  </DraggableComponent>
);

export default TFlipFlop;
