import Callback from '@/components/Callback'
import Home from '@/components/Home'
import Vue from 'vue'
import Router from 'vue-router'

// noinspection JSUnresolvedFunction
Vue.use(Router)

const router = new Router({
  mode  : 'history',
  routes: [
    {
      path     : '/',
      name     : '主页',
      component: Home,
    },
    {
      path     : '/dashboard',
      name     : '控制台',
      component: Home,
    },
    {
      path     : '/callback',
      name     : '登录成功',
      component: Callback,
    },
    {
      path    : '*',
      redirect: '/',
    },
  ],
})

export default router
