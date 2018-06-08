<template>
  <div id='app'>
    <div>
      <el-button @click="startHacking">Start</el-button>
    </div>
    <div>
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#">Auth0 - Vue</a>

            <router-link :to="'/'"
                         class="btn btn-primary btn-margin">
              Home
            </router-link>

            <button
                class="btn btn-primary btn-margin"
                v-if="!authenticated"
                @click="login()">
              Log In
            </button>

            <button
                class="btn btn-primary btn-margin"
                v-if="authenticated"
                @click="logout()">
              Log Out
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

const {login, logout, authenticated, authNotifier} = auth;

export default {
  name: 'app',
  data() {
    authNotifier.on('authChange', authState => {
      this.authenticated = authState.authenticated;
    });
    return {
      auth,
      authenticated,
    };
  },
  methods: {
    login,
    logout,
    startHacking() {
      this.$notify({
        title: 'It works!',
        type: 'success',
        message: 'We\'ve laid the ground work for you. It\'s time for you to build something epic!',
        duration: 5000,
      });
    },
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
