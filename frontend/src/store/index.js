import { createStore } from 'vuex'
import axios from 'axios';
import createPersistedState from 'vuex-persistedstate';

export default createStore({
    plugins: [
        createPersistedState({
            paths: [
                'chooseInfo',
                'startInfo',
                'returnInfo',
                'userInfo', 
                'userInfo2', 
                'engName',
                'addAdult',
                'addChild',
                'addInfant',
                'access_token', 
                'refresh_token', 
                'isLogin', 
                'isLoginError',
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
        chooseInfo: '',
        startInfo: '',
        returnInfo: '',
        userInfo: '',
        userInfo2: '',
        engName: '',
        access_token: '',
        refresh_token: '',
        addAdult: '',
        addChild: '',
        addInfant: '',
        OAuth: null,        
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
    setUserInfo(state, payload) {
        state.userInfo2 = payload
    },
    setChooseInfo(state, payload){
        state.chooseInfo = payload
    },
    setStartInfo(state, payload){
        state.startInfo = payload
    },
    setReturnInfo(state, payload){
        state.returnInfo = payload
    },
    setEngName(state, payload){
        state.engName = payload
    },
    setAddAdult(state, payload){
        state.addAdult = payload
    },
    setAddChild(state, payload){
        state.addChild = payload
    },
    setAddInfant(state, payload){
        state.addInfant = payload
    },
    setToken(state, payload) {
        state.access_token = payload
        state.refresh_token = payload
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
    },
    loginSuccess(state, payload) {
        state.isLogin = true
        state.isLoginError = false
        state.userInfo = payload
    },
    loginError(state) {
        state.isLogin = false
        state.isLoginError = true
        state.userInfo = ''
    },
    logout(state) {        
        state.isLogin = false
        state.isLoginError = false
        state.userInfo = ''
    },
},
  actions: {
    login({ commit } ) {
        commit("loginSuccess")
    },
    logout({ commit } ) {
        commit("logout")
    },
    setLoading({ commit }, payload) {
        if (payload) {
            commit("onLoad");
        } else {
            commit("offLoad");
        }
        },
        chooseInfo({ commit }, payload) {            
            console.log(payload)
            commit("setChooseInfo", payload)
        },
        startInfo({ commit }, payload) {            
            console.log(payload)
            commit("setStartInfo", payload)
        },
        returnInfo({ commit }, payload) {            
            console.log(payload)
            commit("setReturnInfo", payload)
        },
        async setUserInfo({ commit }, payload) {            
            let info = JSON.parse(payload)
            let userInfo = {
                lastName: info[0].name.substr(0,1),
                firstName: info[0].name.substr(1,3),
                email: info[0].email,
                profile: info[0].profile,
                gender: info[0].gender,
                birthday: info[0].birthday,
                access_token: info[0].access_token,
                refresh_token: info[0].refresh_token
            }
            commit("loginSuccess", userInfo)
        },
        async setUserInfo2({ commit }, payload) {        
            console.log(payload)
            commit("setUserInfo", payload)
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
        async engName({ commit }, payload) {
            commit('setEngName', payload)
        },
        async addAdult({ commit }, payload) {
            commit('setAddAdult', payload)
        },
        async addChild({ commit }, payload) {
            commit('setAddChild', payload)
        },
        async addInfant({ commit }, payload) {
            commit('setAddInfant', payload)
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
    },
    
  modules: {
  },
})
