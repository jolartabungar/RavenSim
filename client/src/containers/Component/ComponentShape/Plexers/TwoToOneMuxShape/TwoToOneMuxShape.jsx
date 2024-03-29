import React from 'react';
import { Rect, Group, Text } from 'react-konva';
import {
  muxSize,
  commonShadowProps,
  smallMuxSize,
  commonShapeProps,
} from '../../../../../util/style';

/**
 * A shape template for a TwoToOneMux object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:stephenagberien
 */
const TwoToOneMuxShape = ({
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
  const height = 80;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
      <Rect height={height} width={dimension} {...commonShadowProps(isShadow, isSmall)} />
      <Text x={0.05 * dimension} y={0.2 * height} text="A" fill="#FFFFFF"/>
      <Text x={0.05 * dimension} y={0.7 * height} text="B" fill="#FFFFFF"/>
      <Text x={0.4 * dimension} y={0.8 * height} text="sela" fill="#FFFFFF"/>
      <Text x={0.8  * dimension} y={0.5 * height} text="Q" fill="#FFFFFF"/>
    </Group>
  );
};
  
export default TwoToOneMuxShape;