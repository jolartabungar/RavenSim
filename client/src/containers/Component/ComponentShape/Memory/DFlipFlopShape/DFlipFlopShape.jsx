import React, { useState } from 'react';
import { Rect, Line, Group, Text } from 'react-konva';
import {
  flipFlopSize,
  commonShadowProps,
  smallFlipFlopSize,
  commonShapeProps,
  logicGateStrokeColor,
} from '../../../../../util/style';


import AttributeTable from '../../AttributeTable';
import Portal from '../../Portal';
/**
 * A shape template for a DFlipFlop object. Can be a small icon for sidebar use,
 * a grid component, or a shadow, depending on passed parameters
 * @author:kajhemmingsen
 */
const DFlipFlopShape = ({
  isShadow,
  isSmall,
  isMain,
  x,
  y,
  draggable,
  onDragStart,
  onDragEnd,
  onDragMove,
  onMouseDown,
}) => {
  const dimension = isSmall ? smallFlipFlopSize : flipFlopSize;
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
        onClick={isMain && onClick}
        {...commonShapeProps(x, y, draggable, isSmall)}
      >
        <Rect height={dimension} width={dimension} {...commonShadowProps(isShadow, isSmall)} />
        <Line
          points={[0, 0.15 * dimension, 0.125 * dimension, 0.25 * dimension]}
          stroke={logicGateStrokeColor}
          {...commonShadowProps(isShadow, isSmall)}
        />
        <Line
          points={[0.125 * dimension, 0.25 * dimension, 0, 0.35 * dimension]}
          stroke={logicGateStrokeColor}
          {...commonShadowProps(isShadow, isSmall)}
        />
        <Text x={0.05 * dimension} y={0.7 * dimension} text="D" fill="#FFFFFF" />
        <Text x={0.8 * dimension} y={0.2 * dimension} text="Q" fill="#FFFFFF" />
        <Line
          points={[0.8 * dimension, 0.6 * dimension, 0.95 * dimension, 0.6 * dimension]}
          stroke={logicGateStrokeColor}
          {...commonShadowProps(isShadow, isSmall)}
        />
        <Text x={0.8 * dimension} y={0.65 * dimension} text="Q" fill="#FFFFFF" />
      </Group>
      <Portal>
        <AttributeTable name="D FLIP-FLOP" openDialog={open} closeDialog={() => setOpen(false)} />
      </Portal>
    </>
  );
};
export default DFlipFlopShape;
