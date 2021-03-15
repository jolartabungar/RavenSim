import React from 'react';
import ThreetoEightDecoder from '../../../../containers/Component/ComponentShape/Plexers/ThreetoEightDecoderShape';
import DraggableComponent from '../../../DraggableComponent';
import { THREE_TO_EIGHT_DECODER } from '../../../../store/component/types';
/**
 * Creates a draggable version of the ThreetoEightDecoderShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const ThreetoEightDecoder = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={THREE_TO_EIGHT_DECODER}>
    <ThreetoEightDecoderShape />
  </DraggableComponent>
);

export default ThreetoEightDecoder;
