<template>
  <div id='app'>
    <div>
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#">NTM银行</a>

            <router-link :to="'/'"
                         class="btn btn-primary btn-margin">
              主页
            </router-link>

            <button
                class="btn btn-primary btn-margin"
                v-if="!authenticated"
                @click="login()">
              登录
            </button>

            <button
                class="btn btn-primary btn-margin"
                v-if="authenticated"
                @click="logout()">
              登出
            </button>

          </div>
        </div>
      </nav>

      <div class="container">
        <router-view
            :auth="auth"
            :authenticated="authenticated">
        </router-view>
      </div>
    </div>
  </div>
</template>

<script>
import AuthService from './auth/AuthService';

const auth = new AuthService();

let {login, logout, authenticated, authNotifier} = auth;

export default {
  name: 'app',
  data() {
    authNotifier.on('authChange', authState => {
      authenticated = authState.authenticated;
    });

    return {
      auth,
      authenticated,
    };
  },
  methods: {
    login,
    logout,
  },
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  text-align:  center;
  color:       #2C3E50;
  margin-top:  60px;
}
</style>
