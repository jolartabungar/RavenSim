import React from 'react';
import { Group, Line, Circle } from 'react-konva';
import {
  largeNotGateWidth,
  largeNotGateHeight,
  commonShadowProps,
  commonShapeProps,
  smallNotGateWidth,
  smallNotGateHeight,
  smallNegateRadius,
  largeNegateRadius,
} from '../../../../../util/style';

/**
 * A shape template for a NotGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:kyhorne
 */
const NotGateShape = ({
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
  const notGateWidth = isSmall ? smallNotGateWidth : largeNotGateWidth;
  const notGateHeight = isSmall ? smallNotGateHeight : largeNotGateHeight;
  const negateRadius = isSmall ? smallNegateRadius : largeNegateRadius;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      name={'NotGate'}
      isShadow={isShadow ? true : false}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
      <Line
        points={[0, 0, 0, notGateHeight, notGateWidth, notGateHeight / 2]}
        closed
        isShadow={isShadow ? true : false}
        {...commonShadowProps(isShadow, isSmall)}
      />
      <Circle
        x={notGateWidth + negateRadius}
        y={notGateHeight / 2}
        radius={negateRadius}
        isShadow={isShadow ? true : false}
        {...commonShadowProps(isShadow, isSmall)}
      />
    </Group>
  );
};

export default NotGateShape;
