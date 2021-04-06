import React from 'react';
import EighttoThreeEncoderShape from '../../../../containers/Component/ComponentShape/Plexers/EighttoThreeEncoderShape';
import DraggableComponent from '../../../DraggableComponent';
import { EIGHT_TO_THREE_ENCODER } from '../../../../store/component/types';
/**
 * Creates a draggable version of the EighttoThreeEncoderShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const EighttoThreeEncoder = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={EIGHT_TO_THREE_ENCODER}>
    <EighttoThreeEncoderShape />
  </DraggableComponent>
);

export default EighttoThreeEncoder;
