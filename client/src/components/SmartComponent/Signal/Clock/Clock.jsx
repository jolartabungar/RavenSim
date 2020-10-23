import React from 'react';
import { connect } from 'react-redux';
import ClockShape from '../../../../containers/Component/ComponentShape/Signal/ClockShape';
import DraggableComponent from '../../../DraggableComponent';
import { CLOCK } from '../../../../store/component/types';
/**
 * Creates a draggable version of the ClockShape for the user to interact with
 * @author: kyhorne
 * @param {number} x the x coordinate that the gate is mapped to on the grid
 * @param {number} y the y coordinate that the gate is mapped to on the grid
 * @param {boolean} isOn passes the state of the clock to the Shape object,
 * changing its display whenever the server ticks
 */
const Clock = ({ commandReducer: { isOn }, x, y }) => (
  <DraggableComponent x={x} y={y} type={CLOCK}>
    <ClockShape isOn={isOn} />
  </DraggableComponent>
);

const mapStateToProps = (state) => ({
  commandReducer: state.commandReducer,
});

export default connect(mapStateToProps)(Clock);
