import React from 'react';
import JKflipFlopPRECLRShape from '../../../../containers/Component/ComponentShape/Memory/JKFlipFlopPRECLRShape';
import DraggableComponent from '../../../DraggableComponent';
import { JK_FLIP_FLOP_PRE_CLR } from '../../../../store/component/types';
/**
 * Creates a draggable version of the JKFlipFlopPRECLRShape for the user to interact with
 * @author: stephenagberien
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 */
const JKFlipFlopPRECLR = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={JK_FLIP_FLOP_PRE_CLR}>
    <JKflipFlopPRECLRShape />
  </DraggableComponent>
);

export default JKFlipFlopPRECLR;
