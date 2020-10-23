import React from 'react';
import XnorGateShape from '../../../../containers/Component/ComponentShape/LogicGate/XnorGateShape';
import DraggableComponent from '../../../DraggableComponent';
import { XNOR_GATE } from '../../../../store/component/types';
/**
 *Creates an Xnor Gate using a draggable component wrapping an XnorGateShape template
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @author:kajhemmingsen
 */
const XnorGate = ({ x, y }) => (
  <DraggableComponent x={x} y={y} type={XNOR_GATE}>
    <XnorGateShape />
  </DraggableComponent>
);

export default XnorGate;
