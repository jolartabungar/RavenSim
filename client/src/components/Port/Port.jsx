/* eslint-disable react/no-string-refs */
/* eslint-disable jsx-a11y/mouse-events-have-key-events */
import React from 'react';
import { Circle, Group, Line } from 'react-konva';
import { connect } from 'react-redux';
import { showWirePreview, createWire } from '../../store/wire/actions';
import {
  portRadius,
  cellSize,
  portStrokeWidth,
  portFillColor,
  portStrokeColor,
} from '../../util/style';

/**
 * Ports are the input/output structure for all components.
 * The server uses ports as signal i/o when it sends/recieves messages.
 * @author:kyhorne
 */
class Port extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      isMouseOver: false,
    };
  }

  render() {
    const { refs, props, state } = this;
    const { x, y, wireReducer } = props;
    const { isMouseOver } = state;
    const lineLength = portRadius - portStrokeWidth;
    return (
      <Group
        ref="port"
        x={x}
        y={y}
        opacity={isMouseOver ? 1 : 0}
        draggable
        onMouseOver={() => this.setState({ isMouseOver: true })}
        onMouseOut={() => this.setState({ isMouseOver: false })}
        onDragStart={() => this.setState({ isMouseOver: false })}
        onDragMove={(e) => {
          const x1 = x;
          const y1 = y;

          const x4 = Math.round(e.target.attrs.x / cellSize) * cellSize;
          const y4 = Math.round(e.target.attrs.y / cellSize) * cellSize;

          const difference = Math.round(Math.abs((x1 - x4) / 2) / cellSize) * cellSize;
          const x2 = x1 < x4 ? x1 + difference : x1 - difference;
          const y2 = y1;

          const x3 = x2;
          const y3 = y4;

          const points = [x1, y1, x2, y2, x3, y3, x4, y4];
          props.showWirePreview(points);
        }}
        onDragEnd={() => {
          props.createWire(wireReducer.points);
          refs.port.position({ x, y });
          refs.port.draw();
        }}
      >
        <Circle
          radius={portRadius}
          fill={portFillColor}
          stroke={portStrokeColor}
          strokeWidth={portStrokeWidth}
        />
        <Line
          points={[0, 0, 0, lineLength]}
          stroke={portStrokeColor}
          strokeWidth={portStrokeWidth}
        />
        <Line
          points={[0, 0, 0, -lineLength]}
          stroke={portStrokeColor}
          strokeWidth={portStrokeWidth}
        />
        <Line
          points={[0, 0, lineLength, 0]}
          stroke={portStrokeColor}
          strokeWidth={portStrokeWidth}
        />
        <Line
          points={[0, 0, -lineLength, 0]}
          stroke={portStrokeColor}
          strokeWidth={portStrokeWidth}
        />
      </Group>
    );
  }
}

const mapStateToProps = (state) => ({
  wireReducer: state.wireReducer,
});

const mapDispatchToProps = {
  showWirePreview,
  createWire,
};

export default connect(mapStateToProps, mapDispatchToProps)(Port);
