import React from 'react';
import FourToOneMuxShape from '../../../../containers/Component/ComponentShape/Plexers/FourToOneMuxShape';
import DraggableComponent from '../../../DraggableComponent';
import { FOUR_TO_ONE_MUX } from '../../../../store/component/types';
/**
 * Creates a draggable version of the FourToOneMuxShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const FourToOneMux = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={FOUR_TO_ONE_MUX}>
    <FourToOneMuxShape />
  </DraggableComponent>
);

export default FourToOneMux;
