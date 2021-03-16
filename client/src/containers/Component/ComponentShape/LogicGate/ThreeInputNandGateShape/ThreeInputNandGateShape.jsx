import React, { useState } from 'react';
import { Text, Group, Circle } from 'react-konva';
import AndGateShape from '../AndGateShape';
import {
  commonShadowProps,
  smallNandGateWidth,
  commonShapeProps,
  smallNegateRadius,
  largeNegateRadius,
  smallNandGateHeight,
  largeNandGateHeight,
  largeNandGateWidth,
} from '../../../../../util/style';

import AttributeTable from '../../AttributeTable';
import Portal from '../../Portal';
/**
 * A shape template for a NandGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:kajhemmingsen
 */
const ThreeInputNandGateShape = ({
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
  const width = isSmall ? smallNandGateWidth : largeNandGateWidth;
  const height = isSmall ? smallNandGateHeight : largeNandGateHeight;
  const negateRadius = isSmall ? smallNegateRadius : largeNegateRadius;
  const [open, setOpen] = useState(false);

  function onClick(event) {
    if (event.evt.detail === 2) {
      setOpen(true);
    }
  }

  return (
    <>
      <Group
        onDragStart={onDragStart}
        onDragEnd={onDragEnd}
        onDragMove={onDragMove}
        onMouseDown={onMouseDown}
        onClick={onClick}
        {...commonShapeProps(x, y, draggable, isSmall)}
      >
        <AndGateShape
          isSmall={isSmall}
          isShadow={isShadow}
          isMain={false}
          isNegated
          {...commonShadowProps(isShadow, isSmall)}
        />
        <Circle
          x={width + negateRadius}
          y={height / 2}
          radius={negateRadius}
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
      </Group>
      <Portal>
        <AttributeTable name="Three Input NAND" openDialog={open} closeDialog={() => setOpen(false)} />
      </Portal>
    </>
  );
};

export default ThreeInputNandGateShape;
