import React from 'react';
import SourceShape from '../../../../containers/Component/ComponentShape/Wiring/SourceShape';
import DraggableComponent from '../../../DraggableComponent';
import { SOURCE } from '../../../../store/component/types';

/**
 * Creates a draggable version of the SourceShape for the user to interact with
 * @author: kajhemmingsen
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const Source = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={SOURCE}>
    <SourceShape />
  </DraggableComponent>
);

export default Source;
