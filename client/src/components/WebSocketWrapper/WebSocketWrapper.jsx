import React, { Component } from 'react';
import { connect } from 'react-redux';
import { wsConnect } from '../../store/websocket/actions';

/**
 * Establishes a WebSocket connection with a host and renders the children components.
 * @author:kyhorne
 */
class WebSocketWrapper extends Component {
  componentDidMount() {
    const { props } = this;
    const { host } = props;
    props.wsConnect(host);
  }

  render() {
    const { children } = this.props;
    return <div>{children}</div>;
  }
}
const mapDispatchToProps = {
  wsConnect,
};

export default connect(null, mapDispatchToProps)(WebSocketWrapper);
