import { createStore, applyMiddleware } from 'redux';
import ReduxThunk from 'redux-thunk';
import { createLogger } from 'redux-logger';
import rootReducer from './reducers';

const logger = createLogger();

const store = createStore(rootReducer, applyMiddleware(logger, ReduxThunk));

export default store;
