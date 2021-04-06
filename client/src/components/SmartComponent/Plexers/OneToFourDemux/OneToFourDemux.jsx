import React from 'react';
import OneToFourDemuxShape from '../../../../containers/Component/ComponentShape/Plexers/OneToFourDemuxShape';
import DraggableComponent from '../../../DraggableComponent';
import { ONE_TO_FOUR_DEMUX } from '../../../../store/component/types';
/**
 * Creates a draggable version of the OneToFourDemuxShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const OneToFourDemux = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={ONE_TO_FOUR_DEMUX}>
    <OneToFourDemuxShape />
  </DraggableComponent>
);

export default OneToFourDemux;