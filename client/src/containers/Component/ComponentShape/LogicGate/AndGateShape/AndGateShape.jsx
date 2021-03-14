import React from 'react';
import { Rect } from 'react-konva';
import {
  commonShadowProps,
  smallNandGateHeight,
  smallNandGateWidth,
  commonShapeProps,
  smallAndGateHeight,
  smallAndGateWidth,
  largeAndGateWidth,
  largeAndGateHeight,
} from '../../../../../util/style';

/**
 * A shape template for an AndGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:kyhorne
 */
const AndGateShape = ({
  isShadow,
  isSmall,
  isNegated,
  x,
  y,
  draggable,
  onDragStart,
  onDragEnd,
  onDragMove,
  onMouseDown,
}) => {
  const width = isSmall ? (isNegated ? smallNandGateWidth : smallAndGateWidth) : largeAndGateWidth;
  const height = isSmall
    ? isNegated
      ? smallNandGateHeight
      : smallAndGateHeight
    : largeAndGateHeight;
  return (
    <Rect
      width={width}
      height={height}
      cornerRadius={[0, height / 2, height / 2, 0]}
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      name={'AndGate'}
      {...commonShapeProps(x, y, draggable, isSmall)}
      {...commonShadowProps(isShadow, isSmall)}
    />
  );
};

export default AndGateShape;
