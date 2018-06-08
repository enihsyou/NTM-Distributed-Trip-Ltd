<template>
  <div>
    <h4 v-if="authenticated">
      You are logged in!
    </h4>
    <h4 v-if="!authenticated">
      You are not logged in! Please
      <a @click="auth.login()">Log In</a>
      to continue.
    </h4>

    <button
        class="btn btn-primary btn-margin"
        @click="test()">
      Test Button
    </button>
  </div>
</template>

<script>
import '@/api/axiosConfig';
import axios from 'axios';

function test() {
  axios.get('user_info',
      {
        headers: {'Authorization': `Bearer ${localStorage.getItem('access_token')}`},
      });
}

export default {
  name: 'home',
  props: ['auth', 'authenticated'],
  methods: {
    test,
  },
};
</script>

<style>
a {
  cursor: pointer;
}
</style>
