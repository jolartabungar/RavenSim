import React from 'react';
import { Rect, Group, Text } from 'react-konva';
import {
  muxSize,
  commonShadowProps,
  smallMuxSize,
  commonShapeProps,
} from '../../../../../util/style';

/**
 * A shape template for a FourToOneMux object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:stephenagberien
 */
const FourToOneMuxShape = ({
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
  const height = 100;
  return (
    <Group
      onDragStart={onDragStart}
      onDragEnd={onDragEnd}
      onDragMove={onDragMove}
      onMouseDown={onMouseDown}
      {...commonShapeProps(x, y, draggable, isSmall)}
    >
        <Rect height={height} width={dimension} {...commonShadowProps(isShadow, isSmall)} />
        <Text x={0.05 * dimension} y={0.1 * height} text="A" fill="#FFFFFF"/>
        <Text x={0.05 * dimension} y={0.3 * height} text="B" fill="#FFFFFF"/>
        <Text x={0.05 * dimension} y={0.5 * height} text="C" fill="#FFFFFF"/>
        <Text x={0.05 * dimension} y={0.7 * height} text="D" fill="#FFFFFF"/>
        <Text x={0.3 * dimension} y={0.8 * height} text="sela" fill="#FFFFFF"/>
        <Text x={0.6 * dimension} y={0.8 * height} text="selb" fill="#FFFFFF"/>
        <Text x={0.8  * dimension} y={0.4 * height} text="Q" fill="#FFFFFF"/>
    </Group>
  );
};
  
export default FourToOneMuxShape;