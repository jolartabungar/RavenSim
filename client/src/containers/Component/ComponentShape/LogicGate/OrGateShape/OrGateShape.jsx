import React, { useState } from 'react';
import { Shape } from 'react-konva';
import {
  commonShadowProps,
  commonShapeProps,
  smallOrGateWidth,
  largeOrGateHeight,
  largeOrGateWidth,
  smallNorGateHeight,
  smallNorGateWidth,
  smallOrGateHeight,
} from '../../../../../util/style';

import AttributeTable from '../../AttributeTable';
import Portal from '../../Portal';
/**
 * A shape template for an OrGate object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters.
 * @author:kajhemmingsen
 */
const OrGateShape = ({
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
  const width = isSmall ? (isNegated ? smallNorGateWidth : smallOrGateWidth) : largeOrGateWidth;
  const height = isSmall
    ? isNegated
      ? smallNorGateHeight
      : smallOrGateHeight
    : largeOrGateHeight;

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
        height={height}
        sceneFunc={(context, shape) => {
          context.beginPath();
          context.moveTo(0, 0);
          context.quadraticCurveTo(0.8 * width, 0, width, 0.5 * height);
          context.quadraticCurveTo(0.8 * width, height, 0, height);
          context.quadraticCurveTo((13 / 30) * width, 0.5 * height, 0, 0);
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
        <AttributeTable name="OR" openDialog={open} closeDialog={() => setOpen(false)} />
      </Portal>
    </> 
  );
};

export default OrGateShape;
