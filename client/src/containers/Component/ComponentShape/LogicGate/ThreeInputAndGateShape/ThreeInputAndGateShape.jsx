import React, { useState } from 'react';
import { Rect, Text, Group } from 'react-konva';
import {
  commonShadowProps,
  smallNandGateHeight,
  smallNandGateWidth,
  commonShapeProps,
  smallAndGateHeight,
  smallAndGateWidth,
  largeAndGateWidth,
  largeAndGateHeight,
} from '../../../../../util/style';

import AttributeTable from '../../AttributeTable';
import Portal from '../../Portal';
/**
 * A shape template for an AndGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:maryoji
 */
const ThreeInputAndGateShape = ({
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
  const width = isSmall ? (isNegated ? smallNandGateWidth : smallAndGateWidth) : largeAndGateWidth;
  const height = isSmall
    ? isNegated
      ? smallNandGateHeight
      : smallAndGateHeight
    : largeAndGateHeight;
  const [open, setOpen] = useState(false);
  
  function onClick(event) {
    if (event.evt.detail === 2) {
      setOpen(true);
    }
  }
  return (
    <Group
        onDragStart={onDragStart}
        onDragEnd={onDragEnd}
        onDragMove={onDragMove}
        onMouseDown={onMouseDown}
        onClick={onClick}
        {...commonShapeProps(x, y, draggable, isSmall)}
    >
        <Rect
            width={width}
            height={height}
            cornerRadius={[0, height / 2, height / 2, 0]}
        {...commonShadowProps(isShadow, isSmall)}
        />
        <Text
            x={0.05 * height} 
            y={0.1 * width}
            text="A"
        />
        <Text
            x={0.05 * height} 
            y={0.4 * width}
            text="B"
        />
        <Text
            x={0.05 * height} 
            y={0.7 * width}
            text="C"
        />
        <Text
            x={0.8 * height} 
            y={0.4 * width}
            text="D"
        />
      <Portal>
        <AttributeTable name="Three Input AND" openDialog={open} closeDialog={() => setOpen(false)} />
      </Portal>
    </Group>
  );
};

export default ThreeInputAndGateShape;
