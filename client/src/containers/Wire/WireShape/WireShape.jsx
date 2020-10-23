import React from 'react';
import { Line } from 'react-konva';
import { lowSignalColor, wireWidth, highSignalColor } from '../../../util/style';
import { HIGH_SIGNAL } from '../../../store/wire/types';

const getStrokeColor = (signal) => {
  switch (signal) {
    case HIGH_SIGNAL:
      return highSignalColor;
    default:
      return lowSignalColor;
  }
};

const WireShape = ({ points, signal }) => (
  <Line stroke={getStrokeColor(signal)} points={points} strokeWidth={wireWidth} />
);

export default WireShape;
