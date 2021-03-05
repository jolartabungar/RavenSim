import React from 'react';
import TwoToOneMuxShape from '../../../../containers/Component/ComponentShape/Plexers/TwoToOneMuxShape';
import DraggableComponent from '../../../DraggableComponent';
import { TWO_TO_ONE_MUX } from '../../../../store/component/types';
/**
 * Creates a draggable version of the TwoToOneMuxShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const TwoToOneMux = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={TWO_TO_ONE_MUX}>
    <TwoToOneMuxShape />
  </DraggableComponent>
);

export default TwoToOneMux;
