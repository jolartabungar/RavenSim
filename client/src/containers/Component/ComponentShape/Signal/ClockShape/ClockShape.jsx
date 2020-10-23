import React from 'react';
import { Rect, Group, Line } from 'react-konva';
import {
  highSignalColor,
  lowSignalColor,
  clockSize,
  clockSignalStrokeWidth,
  sidebarClockSize,
  commonShadowProps,
  sidebarClockSignalStrokeWidth,
  commonShapeProps,
} from '../../../../../util/style';

/**
 * A shape template for Clock object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:kyhorne
 */
const ClockShape = ({
  x,
  y,
  isOn,
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
  const padding = isSmall ? sidebarClockSignalStrokeWidth : clockSignalStrokeWidth;
  const strokeWidth = isSmall ? sidebarClockSignalStrokeWidth : clockSignalStrokeWidth;
  return (
    <Group
      {...commonShapeProps(x, y, draggable, isSmall)}
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
    >
      <Rect width={size} height={size} {...commonShadowProps(isShadow, isSmall)} />
      <Line
        points={
          isOn
            ? [padding, size / 2, padding, size - padding / 2]
            : [padding, padding / 2, padding, size / 2]
        }
        stroke={isOn ? highSignalColor : lowSignalColor}
        strokeWidth={strokeWidth}
      />
      <Line
        points={
          isOn
            ? [padding, size - padding, size / 2 + padding / 2, size - padding]
            : [padding, padding, size / 2 + padding / 2, padding]
        }
        stroke={isOn ? highSignalColor : lowSignalColor}
        strokeWidth={strokeWidth}
      />
      <Line
        points={[size / 2, size - padding, size / 2, padding]}
        stroke={isOn ? highSignalColor : lowSignalColor}
        strokeWidth={strokeWidth}
      />
      <Line
        points={
          isOn
            ? [size / 2 - padding / 2, padding, size - padding / 2, padding]
            : [size / 2 - padding / 2, size - padding, size - padding / 2, size - padding]
        }
        stroke={isOn ? highSignalColor : lowSignalColor}
        strokeWidth={strokeWidth}
      />
      <Line
        points={
          isOn
            ? [size - padding, padding, size - padding, size / 2]
            : [size - padding, size - padding, size - padding, size / 2]
        }
        stroke={isOn ? highSignalColor : lowSignalColor}
        strokeWidth={strokeWidth}
      />
    </Group>
  );
};

export default ClockShape;
