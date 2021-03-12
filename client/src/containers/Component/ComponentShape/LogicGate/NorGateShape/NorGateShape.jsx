import React, { useState } from 'react';
import { Group, Circle } from 'react-konva';
import OrGateShape from '../OrGateShape';
import {
  commonShadowProps,
  commonShapeProps,
  smallNegateRadius,
  largeNegateRadius,
  smallNorGateWidth,
  largeNorGateWidth,
  smallNorGateHeight,
  largeNorGateHeight,
} from '../../../../../util/style';

import AttributeTable from '../../AttributeTable';
import Portal from '../../Portal';
/**
 * A shape template for a NorGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:kajhemmingsen
 */
const NorGateShape = ({
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
  const width = isSmall ? smallNorGateWidth : largeNorGateWidth;
  const height = isSmall ? smallNorGateHeight : largeNorGateHeight;
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
        <OrGateShape
          isNegated
          isMain={false}
          isSmall={isSmall}
          isShadow={isShadow}
          {...commonShadowProps(isShadow, isSmall)}
        />
        <Circle
          x={width + negateRadius}
          y={height / 2}
          radius={negateRadius}
          {...commonShadowProps(isShadow, isSmall)}
        />
      </Group>
      <Portal>
        <AttributeTable name="NOR" openDialog={open} closeDialog={() => setOpen(false)} />
      </Portal>
    </>
  );
};
export default NorGateShape;
