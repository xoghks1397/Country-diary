import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    id: '',
    name: '',
    email: '',
    loginChk: false,
    message : ''
  },
  getters: {
    
    getLoginChk(state) {
      if (localStorage.loginChk) return localStorage.loginChk
      return state.loginChk
    },
    getEmail(state) {
      if (localStorage.email) return localStorage.email
      return state.email
    },
    getUserid(state) {
      if (localStorage.id) return localStorage.id
      return state.id
    },
    getUserName(state) {
      if (localStorage.name) return localStorage.name
      return state.name
    },
    getMessage(state) {
      return state.message
    },
  },
  mutations: {
    // payload 가 response
    LOGIN(state, payload) {
      // state.accessToken = accessToken
      if (payload['status'] === false) {
        // 로그인 실패
        this.loginChk = false
        state.message = payload['data']
      } else {
        state.loginChk = payload['status']
        state.email = payload['object'].email;
        localStorage.email = state.email
        state.name = payload['object'].name;
        localStorage.name = state.name
        state.id = payload['object'].id
        localStorage.id = state.id
        localStorage.accessToken = payload['data']
      }
    },
    LOGOUT(state) {
      state.name = ''
      localStorage.clear()
      state.loginChk = false
    },
  },
  actions: {
    LOGIN(context, user) {
      return axios({
        method: 'post',
        url: "http://ec2-3-135-227-210.us-east-2.compute.amazonaws.com:8089/user/login",

        params: {
          id: user.id,
          password: user.password,
        },
      }).then((response) => {
        context.commit('LOGIN', response.data)
      })
    },
    LOGOUT(context) {
      context.commit('LOGOUT')
    },
  },
  modules: {},
})