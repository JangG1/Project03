import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate';

export default createStore({
    plugins: [
        createPersistedState({
            paths: [
                'chooseInfo',
                'startInfo',
                'returnInfo',
                'userInfo',
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
        engName: '',
        access_token: '',
        refresh_token: '',
        addAdult: '',
        addChild: '',
        addInfant: '',
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
        state.consentBtn1 = "선택1"
    },
    setConsent2(state) {
        state.consentBtn2 = "선택2"
    },
    setResNo(state, payload){
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
    kakaoLoginOn(state) {
        state.isKakaoLogin = true
    },
    naverloginOn(state) {
        state.isNaverLogin = true
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
        if (payload == true) {
            commit("onLoad");
        } else if ((payload == false)) {
            commit("offLoad");
        }
    },
    chooseInfo({ commit }, payload) {        
            commit("setChooseInfo", payload)
    },
    startInfo({ commit }, payload) {        
            commit("setStartInfo", payload)
    },
    returnInfo({ commit }, payload) {       
            commit("setReturnInfo", payload)
    },
    async setUserInfo({ commit }, payload) {                                
            let info = payload                        
            let userInfo = {
                lastName: info.name.substr(0,1),
                firstName: info.name.substr(1,3),
                email: info.email,
                profile: info.profile,
                gender: info.gender,
                birthday: info.birthday,
                access_token: info.access_token,
                refresh_token: info.refreshtoken,
                OAuth: info.OAuth,
            }

            console.log(userInfo)
            commit("loginSuccess", userInfo)
    },
    async consentBtn1({ commit }, payload) {
                commit('setConsent1', payload)
    },
    async consentBtn2({ commit }, payload) {
            commit('setConsent2', payload)
    },
    async res_no({ commit }, payload) {
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
    },
  modules: {
  },
})
