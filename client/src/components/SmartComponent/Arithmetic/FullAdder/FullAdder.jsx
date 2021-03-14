import React from 'react';
import FullAdderShape from '../../../../containers/Component/ComponentShape/Arithmetic/FullAdderMuxShape';
import DraggableComponent from '../../../DraggableComponent';
import { FULL_ADDER } from '../../../../store/component/types';
/**
 * Creates a draggable version of the FullAdderShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the Adder is mapped to on the grid
 * @param {number} y the y coordinate that the Adder is mapped to on the grid
 */
const FullAdder = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={FULL_ADDER}>
    <FullAdderShape />
  </DraggableComponent>
);

export default FullAdder;