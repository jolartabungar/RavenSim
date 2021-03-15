import React from 'react';
import { Rect, Group, Text } from 'react-konva';
import {
  muxSize,
  commonShadowProps,
  smallMuxSize,
  commonShapeProps,
} from '../../../../../util/style';

/**
 * A shape template for a ThreetoEightDecoder object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:stephenagberien
 */
const ThreetoEightDecoderShape = ({
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
  const dimension = isSmall ? smallMuxSize : muxSize;
  const height = 360;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
      <Rect height={height} width={dimension} {...commonShadowProps(isShadow, isSmall)} />
      <Text x={0.05  * dimension} y={0.2 * height} text="A0" fill="#FFFFFF"/>
      <Text x={0.05  * dimension} y={0.5 * height} text="A1" fill="#FFFFFF"/>
      <Text x={0.05  * dimension} y={0.8 * height} text="A2" fill="#FFFFFF"/>
      <Text x={0.8 * dimension} y={0.1 * height} text="Y0" fill="#FFFFFF"/>
      <Text x={0.8 * dimension} y={0.2 * height} text="Y1" fill="#FFFFFF"/>
      <Text x={0.8 * dimension} y={0.32 * height} text="Y2" fill="#FFFFFF"/>
      <Text x={0.8 * dimension} y={0.43 * height} text="Y3" fill="#FFFFFF"/>
      <Text x={0.8 * dimension} y={0.55 * height} text="Y4" fill="#FFFFFF"/>
      <Text x={0.8 * dimension} y={0.65 * height} text="Y5" fill="#FFFFFF"/>
      <Text x={0.8 * dimension} y={0.76 * height} text="Y6" fill="#FFFFFF"/>
      <Text x={0.8 * dimension} y={0.86 * height} text="Y7" fill="#FFFFFF"/>
    </Group>
  );
};
  
export default ThreetoEightDecoderShape;