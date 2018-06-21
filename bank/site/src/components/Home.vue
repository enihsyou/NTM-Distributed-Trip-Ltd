<template>
  <div>
    <h4 v-if="!authenticated">
      未登录，请进行
      <a @click="auth.login()" class='btn btn-outline-primary' role='button'>登录</a>
      再继续
    </h4>


    <template v-if="authenticated">
      <div class="container">
        <!-- Content here -->

        <button
            class="btn btn-primary btn-margin"
            @click="get_user_info()">
          获取交易记录
        </button>
      </div>

      <table class="table table-hover">
        <caption>账户交易记录</caption>
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">交易ID</th>
          <th scope="col">时间</th>
          <th scope="col">简要说明</th>
          <th scope="col">金额</th>
          <th scope="col">余额</th>
        </tr>
        </thead>
        <tbody>

        <tr v-if='initialized' v-for='(t, index) in this.account.transactions' :key='t.transaction_id' :class='row_class(t)'>
          <th scope="row">{{index + 1}}</th>
          <td>{{t.transaction_id}}</td>
          <td>{{t.created_time}}</td>
          <td>{{t.description}}</td>
          <td>{{t.amount}}</td>
          <td>{{t.amountAfter}}</td>
        </tr>
        </tbody>
      </table>

      <div class="alert alert-warning" role="alert" v-if='message' @click='message=""'>
        {{message}}
      </div>

      <div class="input-group">
        <div class="input-group-prepend">
          <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{ actionType }}</button>
          <div class="dropdown-menu">
            <a class="dropdown-item" @click='actionType = "存入"'>存入</a>
            <a class="dropdown-item" @click='actionType = "取出"'>取出</a>
          </div>
        </div>
        <div class="input-group-prepend">
          <span class="input-group-text">￥</span>
        </div>
        <input type="text" class="form-control" @keyup.enter='fire_command()' v-model='amount'>
      </div>

    </template>

  </div>
</template>

<script>
import axios_ from 'axios'

const axios = axios_.create({
  headers: { 'Authorization': `Bearer ${localStorage.getItem('access_token')}` },
})

export default {
  name : 'home',
  props: { auth: {}, authenticated: false },

  data() {
    /**
     * 订单结构定义
     * @typedef {Object} Transaction
     * @property {number} transaction_id - 订单id
     * @property {string} category - 操作类型
     * @property {number} amount
     * @property {number} amountAfter
     * @property {Date} created_time
     */

    return {
      initialized: false,
      actionType : '选择操作',
      account    : {
        username    : 'aaa@a.com',
        account_id  : 2,
        balance     : 10,
        /**@type {Transaction[]}*/
        transactions: [],
        orders      : [],
      },
      amount     : 0,
      message    : '',
    }
  },

  methods: {
    update(value) {

    },

    get_user_info() {

      axios.get('user_info')
          .then(value => {
            this.account = value.data
            this.account.transactions.sort((a, b) => a.transaction_id - b.transaction_id)
          })
          .then(() => this.initialized = true)
    },

    fire_command() {
      let type = this.actionType
      let amount = this.amount

      switch (type) {
        case '存入':
          axios({
            method: 'post',
            url   : 'transactions',
            params: {
              'command': 'Deposit',
              'amount' : amount,
            },
          }).then(value => {
            this.account.transactions.push(value.data)
          })
          break
        case '取出':
          axios({
            method: 'post',
            url   : 'transactions',
            params: {
              'command': 'Withdraw',
              'amount' : amount,
            },
          }).then(value => {
            this.account.transactions.push(value.data)
          }).catch(error => {
            this.message = error.response.data.message
          })
          break
      }
    },

    row_class(row) {
      let style = ''

      switch (row.category) {
        case 'Deposit':
          style = 'table-success'
          break
        case 'Withdraw':
          style = 'table-primary'
          break
        case 'Payment':
          style = 'table-info'
          break
      }
      return style
    },
  },
}
</script>

<style>

</style>
