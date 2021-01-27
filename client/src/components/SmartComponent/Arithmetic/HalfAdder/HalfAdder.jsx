import React from 'react';
import HalfAdderShape from '../../../../containers/Component/ComponentShape/Arithmetic/HalfAdderMuxShape';
import DraggableComponent from '../../../DraggableComponent';
import { HALF_ADDER } from '../../../../store/component/types';
/**
 * Creates a draggable version of the HalfAdderShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the Adder is mapped to on the grid
 * @param {number} y the y coordinate that the Adder is mapped to on the grid
 */
const HalfAdder = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={HALF_ADDER}>
    <HalfAdderShape />
  </DraggableComponent>
);

export default HalfAdder;