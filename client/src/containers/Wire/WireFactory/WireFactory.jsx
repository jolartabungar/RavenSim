import React from 'react';
import { connect } from 'react-redux';
import WireShape from '../WireShape';

/**
 * Desc
 * @param {*} props
 * @author:kyhorne
 */
const WireFactory = (props) => {
  const wires = [];
  props.wireReducer.wires.forEach(({ points, signal }) => {
    wires.push(<WireShape points={points} signal={signal} />);
  });
  return wires;
};

const mapStateToProps = (state) => ({
  wireReducer: state.wireReducer,
});

export default connect(mapStateToProps, null)(WireFactory);
