import React from 'react';
import { connect } from 'react-redux';
import WireShape from '../WireShape/WireShape';
import Hideable from '../../Hideable';

const WirePreview = ({ wireReducer }) => (
  <Hideable isHidden={wireReducer.isTheWirePreviewHidden}>
    <WireShape points={wireReducer.points} />
  </Hideable>
);

const mapStateToProps = (state) => ({
  wireReducer: state.wireReducer,
});

export default connect(mapStateToProps)(WirePreview);
