import { createStore } from 'vuex'
import axios from 'axios';

import router from "../router"

export default createStore({
  state: {
    access_token: '',
    refresh_token: '',
    userInfo: null,
    isLogin: false,
    isLoginError: false,
    isLoad: false,
},
getters: {
    getFilteredProduct:(state) => (keyword) => {
        const filtered = state.products.filter((object) => 
          object.title.toLowerCase().includes(keyword.toLowerCase()) || 
          object.description.toLowerCase().includes(keyword.toLowerCase()
        ));
        if (filtered) return filtered;
    },
},
  mutations: {
    onLoad(state) {
        state.isLoad = true;
    },
    offLoad(state) {
        state.isLoad = false;
    },
    setToken(state, payload) {
        localStorage.setItem("access_token", payload.data.access_token);
        localStorage.setItem("refresh_token", payload.data.refresh_token);
        state.access_token = payload.data.access_token
        state.refresh_token = payload.data.refresh_token
    },
    setAccessToken(state, payload) {
        localStorage.setItem("access_token", payload.data.access_token);
        state.access_token = payload.data.access_token
    }
    ,
    loginSuccess(state, payload) {
        state.isLogin = true
        state.isLoginError = false
        state.userInfo = payload
    },
    loginError(state) {
        state.isLogin = false
        state.isLoginError = true
        state.userInfo = null
    },
    logout(state) {
        localStorage.clear();
        state.isLogin = false
        state.isLoginError = false
        state.userInfo = null
    },
},
  actions: {
        setLoading({ commit }, payload) {
            if (payload)
                commit("onLoad");
            else
                commit("offLoad");
        },
        async setUserInfo({ commit }, payload) {
            let userInfo = {
                email: payload.email,
                profile: payload.profile
            }
            commit("loginSuccess", userInfo)
        },
        async getToken({ commit }, loginObj) {
            await axios
                .post("/api/login", loginObj)
                .then((res) => {
                    console.log(res);
                    commit('setToken', res)
                })
                .catch((err) => {
                    console.log(err);
                    commit("loginError")
                    alert("이메일과 비밀번호를 확인하세요.")
                });
        },
        async getRefreshToken({ commit }) {
            console.log("getRefreshToken call");
            if (!Object.prototype.hasOwnProperty.call(localStorage, "access_token"))
                return;
            localStorage.setItem('access_token', "");

            let config = {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('refresh_token')
                }
            }
            await axios
                .post("/api/token/refresh", config)
                .then((res) => {
                    console.log(res);
                    commit('setAccessToken', res)
                }).catch((err) => {
                    console.log('refreshToken error : ', err.config);
                    commit('logout')
                });
        },
        logout({ commit }) {
            commit("logout")
            router.push({ name: "Home" })
        },
    },
    
  modules: {
  },
})
