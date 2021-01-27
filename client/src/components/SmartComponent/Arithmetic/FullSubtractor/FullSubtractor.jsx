import React from 'react';
import FullSubtractorShape from '../../../../containers/Component/ComponentShape/Arithmetic/FullSubtractorMuxShape';
import DraggableComponent from '../../../DraggableComponent';
import { FULL_SUBTRACTOR } from '../../../../store/component/types';
/**
 * Creates a draggable version of the FullSubtractorShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the Subtractor is mapped to on the grid
 * @param {number} y the y coordinate that the Subtractor is mapped to on the grid
 */
const FullSubtractor = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={FULL_SUBTRACTOR}>
    <FullSubtractorShape />
  </DraggableComponent>
);

export default FullSubtractor;