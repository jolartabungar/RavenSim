import React from 'react';
import { Rect, Text, Group } from 'react-konva';
import {
  clockSize,
  sidebarClockSize,
  commonShadowProps,
  commonShapeProps,
} from '../../../../../util/style';

/**
 * A shape template for a Source object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:kyhorne
 */
const SourceShape = ({
  x,
  y,
  onMouseDown,
  onDragEnd,
  onDragMove,
  onDragStart,
  isSmall,
  isShadow,
  draggable,
}) => {
  if (isShadow) {
    return (
      <Rect width={clockSize} height={clockSize} x={x} y={y} {...commonShadowProps(isShadow)} />
    );
  }
  const size = isSmall ? sidebarClockSize : clockSize;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
      <Rect
        width={size}
        height={size}
        {...commonShadowProps(isShadow, isSmall)}
      />
      <Text
        x={0.45 * size}
        y={0.4 * size}
        text="1"
        fill="#FFFFFF"
      />
    </Group>
  );
};

export default SourceShape;
