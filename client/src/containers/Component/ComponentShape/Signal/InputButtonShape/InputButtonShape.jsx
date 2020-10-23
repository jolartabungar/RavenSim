import React from 'react';
import { Rect, Group } from 'react-konva';
import {
  clockSize,
  sidebarClockSize,
  commonShadowProps,
  commonShapeProps,
} from '../../../../../util/style';

const InputButtonShape = ({
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
      {...commonShapeProps(x, y, draggable, isSmall)}
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
    >
      <Rect height={size} width={size} {...commonShadowProps(isShadow, isSmall)} />
      <Rect height={0.9 * size} width={0.9 * size} {...commonShadowProps(isShadow, isSmall)} />
    </Group>
  );
};

export default InputButtonShape;
