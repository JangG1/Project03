import { createStore } from 'vuex'
import axios from 'axios';
import router from "../router"
import createPersistedState from 'vuex-persistedstate';

export default createStore({
    plugins: [
        createPersistedState({
            paths: [
                'userInfo', 
                'access_token', 
                'refresh_token', 
                'isLogin', 
                'isLoginError',
                'name',
                'email',
                'gender',
                'birthday',]
        })
    ],
    // counter라는 state 속성을 추가

    state: {
        access_token: '',
        refresh_token: '',
        userInfo: null,
        name: null,
        email: null,
        gender: null,
        birthday: null,
        isLogin: false,
        isLoginError: false,
        isLoad: false,
    },
  mutations: {
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
        state.name = payload.name        
        state.email = payload.email        
        state.gender = payload.gender
        state.birthday = payload.birthday
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
        async setUserInfo({ commit }, payload) {
            let userInfo = {
                name: payload.name,
                email: payload.email,
                profile: payload.profile,
                gender: payload.gender,
                birthday: payload.birthday,
            }            
            commit("loginSuccess", userInfo)
        },
        async getToken({ commit }, loginObj) {
            await axios
                .post("/api/test", loginObj)
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
