import React from 'react';
import OneToTwoDemuxShape from '../../../../containers/Component/ComponentShape/Plexers/OneToTwoDemuxShape';
import DraggableComponent from '../../../DraggableComponent';
import { ONE_TO_TWO_DEMUX } from '../../../../store/component/types';
/**
 * Creates a draggable version of the OneToTwoDemuxShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const OneToTwoDemux = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={ONE_TO_TWO_DEMUX}>
    <OneToTwoDemuxShape />
  </DraggableComponent>
);

export default OneToTwoDemux;
