import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import { Provider } from 'react-redux';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import configureStore from './store';
import WebSocketWrapper from './components/WebSocketWrapper';
import App from './containers/App';

const host = 'ws://127.0.0.1:8080/v1';

const Root = () => (
  <Router>
    <Provider store={configureStore()}>
      <WebSocketWrapper host={host}>
        <Route path="/" component={App} />
      </WebSocketWrapper>
    </Provider>
  </Router>
);

ReactDOM.render(<Root />, document.getElementById('root'));
