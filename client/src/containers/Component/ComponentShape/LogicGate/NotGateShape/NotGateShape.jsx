import React, { useState } from 'react';
import { Group, Line, Circle } from 'react-konva';
import {
  largeNotGateWidth,
  largeNotGateHeight,
  commonShadowProps,
  commonShapeProps,
  smallNotGateWidth,
  smallNotGateHeight,
  smallNegateRadius,
  largeNegateRadius,
} from '../../../../../util/style';


import AttributeTable from '../../AttributeTable';
import Portal from '../../Portal';
/**
 * A shape template for a NotGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:kyhorne
 */
const NotGateShape = ({
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
  const notGateWidth = isSmall ? smallNotGateWidth : largeNotGateWidth;
  const notGateHeight = isSmall ? smallNotGateHeight : largeNotGateHeight;
  const negateRadius = isSmall ? smallNegateRadius : largeNegateRadius;
  const [open, setOpen] = useState(false);

  function onClick(event) {
    if (event.evt.detail == 2) {
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
        <Line
          points={[0, 0, 0, notGateHeight, notGateWidth, notGateHeight / 2]}
          closed
          {...commonShadowProps(isShadow, isSmall)}
        />
        <Circle
          x={notGateWidth + negateRadius}
          y={notGateHeight / 2}
          radius={negateRadius}
          {...commonShadowProps(isShadow, isSmall)}
        />
      </Group>
      <Portal>
        <AttributeTable name="NOT" openDialog={open} closeDialog={() => setOpen(false)} />
      </Portal>
    </>
  );
};

export default NotGateShape;
