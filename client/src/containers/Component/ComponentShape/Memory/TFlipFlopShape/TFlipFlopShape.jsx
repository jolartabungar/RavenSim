import React from 'react';
import {
  Rect,
  Line,
  Group,
  Text,
} from 'react-konva';
import {
  flipFlopSize,
  commonShadowProps,
  smallFlipFlopSize,
  commonShapeProps,
  logicGateStrokeColor,
} from '../../../../../util/style';

/**
 * A shape template for a TFlipFlop object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:kajhemmingsen
 */
const TFlipFlopShape = ({
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
  const dimension = isSmall ? smallFlipFlopSize : flipFlopSize;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      name={'TFlipFlop'}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
      <Rect height={dimension} width={dimension} {...commonShadowProps(isShadow, isSmall)} />
      <Line
        points={[0, 0.15 * dimension, 0.125 * dimension, 0.25 * dimension]}
        stroke={logicGateStrokeColor}
        {...commonShadowProps(isShadow, isSmall)}
      />
      <Line
        points={[0.125 * dimension, 0.25 * dimension, 0, 0.35 * dimension]}
        stroke={logicGateStrokeColor}
        {...commonShadowProps(isShadow, isSmall)}
      />
      <Text
        x={0.05 * dimension}
        y={0.7 * dimension}
        text="T"
        fill="#FFFFFF"
      />
      <Text
        x={0.2 * dimension}
        y={0.8 * dimension}
        text="1"
        fill="#FFFFFF"
      />
      <Text
        x={0.4 * dimension}
        y={0.8 * dimension}
        text="en"
        fill="#FFFFFF"
      />
      <Text
        x={0.7 * dimension}
        y={0.8 * dimension}
        text="0"
        fill="#FFFFFF"
      />
      <Text
        x={0.8 * dimension}
        y={0.2 * dimension}
        text="Q"
        fill="#FFFFFF"
      />
      <Line
        points={[0.8 * dimension, 0.6 * dimension, 0.95 * dimension, 0.6 * dimension]}
        stroke={logicGateStrokeColor}
        {...commonShadowProps(isShadow, isSmall)}
      />
      <Text
        x={0.8 * dimension}
        y={0.65 * dimension}
        text="Q"
        fill="#FFFFFF"
      />
    </Group>
  );
};

export default TFlipFlopShape;
