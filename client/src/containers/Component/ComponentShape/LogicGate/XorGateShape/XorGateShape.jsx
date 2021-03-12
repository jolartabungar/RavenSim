import React, { useState } from 'react';
import { Shape } from 'react-konva';
import {
  commonShadowProps,
  commonShapeProps,
  smallXnorGateWidth,
  smallXnorGateHeight,
  smallXorGateWidth,
  smallXorGateHeight,
  largeXorGateWidth,
  largeXorGateHeight,
} from '../../../../../util/style';

import AttributeTable from '../../AttributeTable';
import Portal from '../../Portal';
/**
 * A shape template for an XorGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:kyhorne
 */
const XorGateShape = ({
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
  const width = isSmall ? (isNegated ? smallXnorGateWidth : smallXorGateWidth) : largeXorGateWidth;
  const height = isSmall
    ? isNegated
      ? smallXnorGateHeight
      : smallXorGateHeight
    : largeXorGateHeight;

  const [open, setOpen] = useState(false);

  function onClick(event) {
    if (event.evt.detail === 2) {
      setOpen(true);
    }
  }

  return (
    <>
      <Shape
        width={width}
        heigh={height}
        sceneFunc={(context, shape) => {
          context.beginPath();
          context.moveTo(0, 0);
          context.quadraticCurveTo((1 / 3) * width, 0.5 * height, 0, height);
          context.quadraticCurveTo((1 / 3) * width, 0.5 * height, 0, 0);
          context.moveTo(0.1 * width, 0);
          context.quadraticCurveTo(0.8 * width, 0, width, 0.5 * height);
          context.quadraticCurveTo(0.8 * width, height, 0.1 * width, height);
          context.quadraticCurveTo((13 / 30) * width, 0.5 * height, 0.1 * width, 0);
          context.closePath();
          context.fillStrokeShape(shape);
        }}
        onDragStart={onDragStart}
        onDragEnd={onDragEnd}
        onDragMove={onDragMove}
        onMouseDown={onMouseDown}
        onClick={isMain && onClick}
        {...commonShapeProps(x, y, draggable, isSmall)}
        {...commonShadowProps(isShadow, isSmall)}
      />
      <Portal>
        <AttributeTable name="XOR" openDialog={open} closeDialog={() => setOpen(false)} />
      </Portal>
    </>
  );
};
export default XorGateShape;
