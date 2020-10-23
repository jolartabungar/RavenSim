import React from 'react';
import { Group, Circle } from 'react-konva';
import AndGateShape from '../AndGateShape';
import {
  commonShadowProps,
  smallNandGateWidth,
  commonShapeProps,
  smallNegateRadius,
  largeNegateRadius,
  smallNandGateHeight,
  largeNandGateHeight,
  largeNandGateWidth,
} from '../../../../../util/style';

/**
 * A shape template for a NandGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:kajhemmingsen
 */
const NandGateShape = ({
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
  const width = isSmall ? smallNandGateWidth : largeNandGateWidth;
  const height = isSmall ? smallNandGateHeight : largeNandGateHeight;
  const negateRadius = isSmall ? smallNegateRadius : largeNegateRadius;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
      <AndGateShape
        isSmall={isSmall}
        isShadow={isShadow}
        isNegated
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

export default NandGateShape;
