import React, { useState } from 'react';
import { Rect } from 'react-konva';
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
 * @author:kyhorne
 */
const AndGateShape = ({
  isShadow,
  isSmall,
  isNegated,
  isMain,
  x,
  y,
  draggable,
  onDragStart,
  onDragEnd,
  onDragMove,
  onMouseDown,
}) => {
  const width = isSmall ? (isNegated ? smallNandGateWidth : smallAndGateWidth) : largeAndGateWidth;
  const [open, setOpen] = useState(false);

  function onClick(event) {
    if (event.evt.detail === 2) {
      setOpen(true);
    }
  }

  const height = isSmall
    ? isNegated
      ? smallNandGateHeight
      : smallAndGateHeight
    : largeAndGateHeight;
  return (
    <>
      <Rect
        width={width}
        height={height}
        cornerRadius={[0, height / 2, height / 2, 0]}
        onDragStart={onDragStart}
        onDragEnd={onDragEnd}
        onDragMove={onDragMove}
        onMouseDown={onMouseDown}
        onClick={isMain && onClick}
        {...commonShapeProps(x, y, draggable, isSmall)}
        {...commonShadowProps(isShadow, isSmall)}
      />
      <Portal>
        <AttributeTable name="AND" openDialog={open} closeDialog={() => setOpen(false)} />
      </Portal>
    </> 
  );
};

export default AndGateShape;
