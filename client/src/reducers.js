import { combineReducers } from 'redux'
import { routerReducer } from 'react-router-redux'

import app from './app/reducer'
import nominations from './hugo/reducers/nominations'
import user from './kansa/reducers/user'

export default combineReducers({
  app,
  nominations,
  user,
  routing: routerReducer
});
