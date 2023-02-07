import { createStore } from 'vuex'
import axios from 'axios';
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
                'holdPoint',
                'name2',
                'email2',
                'gender2',
                'birthday2',
                'profile2'
            ]
        })
    ],    
    state: {
        access_token: '',
        refresh_token: '',
        userInfo: null,
        OAuth: null,
        name: null,
        email: null,
        gender: null,
        birthday: null,
        name2: null,
        email2: null,
        profile2: null,
        gender2: null,
        birthday2: null,
        isLogin: false,
        isLoginError: false,
        isLoad: false,
        consentBtn1: '',
        consentBtn2: '',
        holdPoint: 0,
        res_no: null,
    },
  mutations: {
    onLoad(state) {
        state.isLoad = true;
    },
    offLoad(state) {
        state.isLoad = false;
    },
    setToken(state, payload) {
        state.access_token = payload
        //state.refresh_token = payload
    },
    setUserInfo(state, payload) {        
        state.userInfo2 = payload
        state.name2 = payload.name        
        state.email2 = payload.email    
        state.profile2 = payload.profile
        state.gender2 = payload.gender
        state.birthday2 = payload.birthday
    },
    setOAuth(state, payload) {        
        state.OAuth = payload
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
    },
    loginError(state) {
        state.isLogin = false
        state.isLoginError = true
        state.userInfo = null
    },
    logout(state) {        
        state.isLogin = false
        state.isLoginError = false
        state.userInfo = null
    },
},
  actions: {
    setLoading({ commit }, payload) {
        if (payload) {
            commit("onLoad");
        } else {
            commit("offLoad");
        }
    },
        async setUserInfo({ commit }, payload) {            
            let info = JSON.parse(payload)
            console.log("setUserInfo : " + info[0])
            let userInfo = {
                name: info[0].name,
                email: info[0].email,
                profile: info[0].profile,
                gender: info[0].gender,
                birthday: info[0].birthday,
                access_token: info[0].access_token
            }
            console.log(userInfo)
            commit("loginSuccess", userInfo)
        },
         getToken({ commit }, payload) {            
            console.log("user : " + payload)
            commit("setToken", payload)
        },
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
        logout({ commit } ) {
            commit("logout")
        },
    },
    
  modules: {
  },
})
