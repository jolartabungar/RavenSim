import React, { useState } from 'react';
import { Text, Group, Circle } from 'react-konva';
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
   * @author:maryoji
   */
  const ThreeInputNorGateShape = ({
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
           <Text
              x={0.18 * height} 
              y={0.1 * width}
              text="A"
          />
          <Text
              x={0.23 * height} 
              y={0.45 * width}
              text="B"
          />
          <Text
              x={0.18 * height} 
              y={0.78 * width}
              text="C"
          />
          <Text
              x={0.8 * height} 
              y={0.47 * width}
              text="D"
          />
        </Group>
        <Portal>
          <AttributeTable name="Three Input NOR" openDialog={open} closeDialog={() => setOpen(false)} />
        </Portal>
      </>
    );
  };
  export default ThreeInputNorGateShape;