import React from 'react';
import { Line, Group } from 'react-konva';
import {
  cellSize,
  gridWidth,
  gridLineColor,
  gridHeight,
} from '../../../util/style';

/**
 * Renders the grid using Konva lines
 * @author: kyhorne
 */
const SimpleGrid = () => {
  const padding = cellSize;
  const gridLayer = [];
  for (let i = 0; i < gridWidth / padding; i++) {
    gridLayer.push(
      <Line
        points={[Math.round(i * padding) + 0.5, 0, Math.round(i * padding) + 0.5, gridHeight]}
        stroke={gridLineColor}
        strokeWidth={1}
      />,
    );
  }
  gridLayer.push(<Line points={[0, 0, 10, 10]} />);
  for (let j = 0; j < gridHeight / padding; j++) {
    gridLayer.push(
      <Line
        points={[0, Math.round(j * padding), gridWidth, Math.round(j * padding)]}
        stroke={gridLineColor}
        strokeWidth={0.5}
      />,
    );
  }
  return <Group>{gridLayer}</Group>;
};

export default SimpleGrid;
