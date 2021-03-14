import React from 'react';
import { Group, Shape } from 'react-konva';
import {
  commonShadowProps,
  commonShapeProps,
  smallOrGateWidth,
  largeOrGateHeight,
  largeOrGateWidth,
  smallNorGateHeight,
  smallNorGateWidth,
  smallOrGateHeight,
} from '../../../../../util/style';

/**
 * A shape template for an OrGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:kajhemmingsen
 */
const OrGateShape = ({
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
  const width = isSmall ? (isNegated ? smallNorGateWidth : smallOrGateWidth) : largeOrGateWidth;
  const height = isSmall ? (isNegated ? smallNorGateHeight : smallOrGateHeight) : largeOrGateHeight;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      name={'OrGate'}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
      <Shape
        width={width}
        heigh={height}
        sceneFunc={(context, shape) => {
          context.beginPath();
          context.moveTo(0, 0);
          context.quadraticCurveTo(0.8 * width, 0, width, 0.5 * height);
          context.quadraticCurveTo(0.8 * width, height, 0, height);
          context.quadraticCurveTo((13 / 30) * width, 0.5 * height, 0, 0);
          context.closePath();
          context.fillStrokeShape(shape);
        }}
        {...commonShadowProps(isShadow, isSmall)}
      />
    </Group>
  );
};

export default OrGateShape;
