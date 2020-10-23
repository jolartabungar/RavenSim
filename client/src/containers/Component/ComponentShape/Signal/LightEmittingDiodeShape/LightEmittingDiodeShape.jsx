import React from 'react';
import { Circle } from 'react-konva';
import {
  highSignalColor,
  lowSignalColor,
  commonShadowProps,
  commonShapeProps,
  sidebarLEDRadius,
  LEDRadius,
} from '../../../../../util/style';

/**
 * A shape template for a LightEmittingDiode object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:kajhemmingsen
 */
const LightEmittingDiodeShape = ({
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
      <Circle radius={LEDRadius} x={x} y={y} {...commonShadowProps(isShadow)} />
    );
  }
  const circleRadius = isSmall ? sidebarLEDRadius : LEDRadius;
  return (
    <Circle
      radius={circleRadius}
      {...commonShapeProps(x, y, isSmall)}
      fill={
        isOn ? highSignalColor : lowSignalColor
      }
      onDragStart={onDragStart}
      onDragMove={onDragMove}
      onDragEnd={onDragEnd}
      onMouseDown={onMouseDown}
      draggable={draggable}
    />
  );
};

export default LightEmittingDiodeShape;
