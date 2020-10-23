import React from 'react';
import { connect } from 'react-redux';
import Port from '../../components/Port';

/**
 * Creates a port object at a given coordinate.
 * @param {*} portReducer a full collection of ports.
 * @author:kyhorne
 */
const PortFactory = ({ portReducer }) =>
  portReducer.ports.map((port) => {
    const { x, y } = port;
    return <Port x={x} y={y} />;
  });

const mapStateToProps = (state) => ({
  portReducer: state.portReducer,
});

export default connect(mapStateToProps)(PortFactory);
