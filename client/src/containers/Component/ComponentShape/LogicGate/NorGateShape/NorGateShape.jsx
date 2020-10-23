import React from 'react';
import { Group, Circle } from 'react-konva';
import OrGateShape from '../OrGateShape';
import {
  commonShadowProps,
  commonShapeProps,
  smallNegateRadius,
  largeNegateRadius,
  smallNorGateWidth,
  largeNorGateWidth,
  smallNorGateHeight,
  largeNorGateHeight,
} from '../../../../../util/style';

/**
 * A shape template for a NorGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:kajhemmingsen
 */
const NorGateShape = ({
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
  const width = isSmall ? smallNorGateWidth : largeNorGateWidth;
  const height = isSmall ? smallNorGateHeight : largeNorGateHeight;
  const negateRadius = isSmall ? smallNegateRadius : largeNegateRadius;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
      <OrGateShape
        isNegated
        isSmall={isSmall}
        isShadow={isShadow}
        {...commonShadowProps(isShadow, isSmall)}
      />
      <Circle
        x={width + negateRadius}
        y={height / 2}
        radius={negateRadius}
        {...commonShadowProps(isShadow, isSmall)}
      />
    </Group>
  );
};

export default NorGateShape;
