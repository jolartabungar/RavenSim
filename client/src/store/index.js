import { createStore, combineReducers, applyMiddleware } from 'redux';
// import logger from 'redux-logger';
import { composeWithDevTools } from 'redux-devtools-extension';
import wireReducer from './wire/reducer';
import wsMiddleware from './websocket/middleware';
import commandReducer from './command/reducer';
import websocketReducer from './websocket/reducer';
import componentReducer from './component/reducer';
import portReducer from './port/reducer';

const rootReducer = combineReducers({
  wireReducer,
  commandReducer,
  websocketReducer,
  componentReducer,
  portReducer,
});

const configureStore = () => {
  const middlewares = [wsMiddleware]; // Logger
  const middleWareEnhancer = applyMiddleware(...middlewares);
  return createStore(rootReducer, composeWithDevTools(middleWareEnhancer));
};

export default configureStore;
