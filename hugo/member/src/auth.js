export default class {

  constructor(api, store) {
    this.api = api;
    this.store = store;
  }

  getUser = () => this.api.GET('kansa/user');
  keyRequest = (email) => this.api.POST('kansa/key', { email });
  keyLogin = (email, key) => this.api.POST('kansa/login', { email, key });

  tryLogin = (nextState, replace, callback) => {
    const loc = nextState.location.pathname;
    console.log(loc, 'tryLogin');
    this.getUser()
      .then(data => {
        replace('/' + data.people[0].id);
        callback();
      })
      .catch(err => {
        if (loc !== '/') replace('/');
        console.error('login failed', err);
        callback();
      });
  }

  doLogin = (nextState, replace, callback) => {
    const { email, key } = nextState.params;
    this.keyLogin(email, key)
      .then(() => this.tryLogin(nextState, replace, callback))
      .catch(e => callback(e));
  }

}
