import React, { useState } from 'react';
import { Group, Circle } from 'react-konva';
import XorGateShape from '../XorGateShape';
import {
  largeXnorGateHeight,
  largeXnorGateWidth,
  smallXnorGateHeight,
  smallXnorGateWidth,
  commonShapeProps,
  commonShadowProps,
  smallNegateRadius,
  largeNegateRadius,
} from '../../../../../util/style';

import AttributeTable from '../../AttributeTable';
import Portal from '../../Portal';
/**
 * A shape template for an XnorGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:kajhemmingsen
 */
const XnorGateShape = ({
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
  const width = isSmall ? smallXnorGateWidth : largeXnorGateWidth;
  const height = isSmall ? smallXnorGateHeight : largeXnorGateHeight;
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
        <XorGateShape
          isNegated
          isMain={false}
          isSmall={isSmall}
          isShadow={isShadow}
          {...commonShadowProps(isShadow, isSmall)}
        />
        <Circle
          radius={negateRadius}
          x={width + negateRadius}
          y={height / 2}
          {...commonShadowProps(isShadow, isSmall)}
        />
      </Group>
      <Portal>
        <AttributeTable name="XNOR" openDialog={open} closeDialog={() => setOpen(false)} />
      </Portal>
    </>
  );
};

export default XnorGateShape;
