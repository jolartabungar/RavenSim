import React from 'react';
import { Group, Circle } from 'react-konva';
import XorGateShape from '../XorGateShape';
import {
  largeXnorGateHeight,
  largeXnorGateWidth,
  smallXnorGateHeight,
  smallXnorGateWidth,
  commonShapeProps,
  commonShadowProps,
  smallNegateRadius,
  largeNegateRadius,
} from '../../../../../util/style';

/**
 * A shape template for an XnorGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:kajhemmingsen
 */
const XnorGateShape = ({
  isShadow,
  isSmall,
  x,
  y,
  draggable,
  onDragStart,
  onDragEnd,
  onDragMove,
  onMouseDown,
}) => {
  const width = isSmall ? smallXnorGateWidth : largeXnorGateWidth;
  const height = isSmall ? smallXnorGateHeight : largeXnorGateHeight;
  const negateRadius = isSmall ? smallNegateRadius : largeNegateRadius;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
      <XorGateShape
        isNegated
        isSmall={isSmall}
        isShadow={isShadow}
        {...commonShadowProps(isShadow, isSmall)}
      />
      <Circle
        radius={negateRadius}
        x={width + negateRadius}
        y={height / 2}
        {...commonShadowProps(isShadow, isSmall)}
      />
    </Group>
  );
};

export default XnorGateShape;
