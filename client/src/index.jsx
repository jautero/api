import React from 'react'
import ReactDOM from 'react-dom'
import { Provider } from 'react-redux'
import { IndexRedirect, IndexRoute, Router, Route, hashHistory } from 'react-router'
import { syncHistoryWithStore } from 'react-router-redux'
import { createStore } from 'redux'

import injectTapEventPlugin from 'react-tap-event-plugin'
injectTapEventPlugin();

import getMuiTheme from 'material-ui/styles/getMuiTheme'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'

import { keyLogin, tryLogin } from './app/actions/auth'
import { setNominator } from './hugo/actions'
import { PATH_IN } from './constants'
import App from './app/components/App'
import LoginForm from './app/components/LoginForm'
import MemberList from './kansa/components/MemberList'
import Nominate from './hugo/components/Nominate'
import { initHugoAdmin } from './hugo-admin/actions'
import Canon from './hugo-admin/components/Canon'
import middleware from './middleware'
import reducers from './reducers'

import './app/style.css'

const store = createStore(reducers, middleware(hashHistory));

const authCheck = (nextState, replace) => {
  const loc = nextState.location.pathname;
  const email = store.getState().user.get('email');
  if (!email) store.dispatch(tryLogin());
  else if (loc == '/') replace(PATH_IN);
}

const doLogin = ({ params: { email, key } }) => {
  store.dispatch(keyLogin(email, key));
}

const onEnterHugo = (nextState, replace) => {
  const id = parseInt(nextState.params.id);
  const person = store.getState().user.get('people', []).find(p => p.id === id);
  if (!person) store.dispatch(tryLogin());
}

const onEnterHugoAdmin = () => {
  store.dispatch(initHugoAdmin())
}

const onEnterNominations = ({ params: { id } }, _, callback) => {
  store.dispatch(setNominator(id, callback));
}

ReactDOM.render(
  <Provider store={store} >
    <MuiThemeProvider muiTheme={getMuiTheme()}>
      <Router history={syncHistoryWithStore(hashHistory, store)}>
        <Route path="/" component={App} >
          <IndexRedirect to={PATH_IN} />
          <Route path="login" component={LoginForm} />
          <Route path="login/:email/:key" onEnter={doLogin} />
          <Route path="profile" onEnter={authCheck} component={MemberList} />
          <Route path="hugo" onEnter={authCheck} >
            <IndexRedirect to={PATH_IN} />
            <Route path="admin" onEnter={onEnterHugoAdmin} component={Canon}/>
            <Route path=":id" onEnter={onEnterHugo} >
              <IndexRedirect to="nominate" />
              <Route path="nominate" onEnter={onEnterNominations} component={Nominate} />
            </Route>
          </Route>
        </Route>
      </Router>
    </MuiThemeProvider>
  </Provider>,
  document.getElementById('react')
)
