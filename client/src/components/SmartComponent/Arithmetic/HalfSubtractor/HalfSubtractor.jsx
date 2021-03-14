import React from 'react';
import HalfSubtractorShape from '../../../../containers/Component/ComponentShape/Arithmetic/HalfSubtractorMuxShape';
import DraggableComponent from '../../../DraggableComponent';
import { HALF_SUBTRACTOR } from '../../../../store/component/types';
/**
 * Creates a draggable version of the HalfSubtractorShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the Subtractor is mapped to on the grid
 * @param {number} y the y coordinate that the Subtractor is mapped to on the grid
 */
const HalfSubtractor = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={HALF_SUBTRACTOR}>
    <HalfSubtractorShape />
  </DraggableComponent>
);

export default HalfSubtractor;