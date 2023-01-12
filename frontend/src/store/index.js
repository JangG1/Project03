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
                'birthday',
                'holdPoint',]
        })
    ],    
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
        consentBtn1: '',
        consentBtn2: '',
        holdPoint: '',
        res_no: null,
    },
  mutations: {
    setToken(state, payload) {
        localStorage.setItem("access_token", payload.data.access_token);
        localStorage.setItem("refresh_token", payload.data.refresh_token);
        state.access_token = payload.data.access_token
        state.refresh_token = payload.data.refresh_token
    },
    setConsent1(state) {
        console.log(state.consentBtn1)
        state.consentBtn1 = "선택1"
    },
    setConsent2(state) {
        console.log(state.consentBtn2)
        state.consentBtn2 = "선택2"
    },
    setResNo(state, payload){
        console.log(state.payload)
        state.res_no = payload
    },
    setHoldPoint(state, payload) {
        state.holdPoint = payload
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
        console.log("loginSuccess " + payload)
        console.log("loginSuccess " + payload.name)
        console.log("loginSuccess " + payload.email)
        console.log("loginSuccess " + payload.gender)
        console.log("loginSuccess " + payload.birthday)
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
            console.log("setUserInfo" + payload)
            let userInfo = {
                name: payload.name,
                email: payload.email,
                profile: payload.profile,
                gender: payload.gender,
                birthday: payload.birthday,
            }            
            console.log("setUserInfo name " + payload.name)
            commit("loginSuccess", userInfo)
        },
        /*async getToken({ commit }, loginObj) {
            console.log("getToken" + loginObj)
            await axios
                .post("/api/test1", loginObj)
                .then((res) => {
                    console.log(res);
                    commit('setToken', res)
                })
                .catch((err) => {
                    console.log(err);
                    commit("loginError")
                    alert("이메일과 비밀번호를 확인하세요.")
                });
        },*/
        async consentBtn1({ commit }, payload) {
                console.log(payload)
                commit('setConsent1', payload)
        },
        async consentBtn2({ commit }, payload) {
            console.log(payload)
            commit('setConsent2', payload)
        },
        async res_no({ commit }, payload) {
            console.log(payload)
            commit('setResNo', payload)
        },
        async holdPoint({ commit }, payload) {
            commit('setHoldPoint', payload)
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
