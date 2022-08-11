import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token:localStorage.getItem('token')||"",
    userInfo:localStorage.getItem('userInfo')||"",
  },
  getters: {

  },
  //同步修改state里面的值
  mutations: {
    SET_TOKEN:(state,token)=>{
      state.token=token
    },
    SET_USERINFO:(state,userInfo)=>{
      state.userInfo=userInfo
    }
  },
  //异步调用mutations里面的方法
  // contxt.commit 利用上下文触发mutations某个方法
  // vue代码里面 this.$store.dispatch触发action里面的定义的方法
  actions: {
    setToken(context,token){
      context.commit("SET_TOKEN",token)
    },
    clearToken(context){
      context.commit("SET_TOKEN","")
    },
    setUserInfo(context,userInfo){
      context.commit("SET_USERINFO",userInfo)
    },
    clearUserInfo(context){
      context.commit("SET_USERINFO","")
    }
  },
  modules: {
  }
})
