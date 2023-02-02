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
        userInfo2: null,
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
        state.access_token = payload.data
        state.refresh_token = payload
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
    setLoading({ commit }, payload) {
        if (payload) {
            commit("onLoad");
        } else {
            commit("offLoad");
        }
    },
        async setUserInfo({ commit }, payload) {
            console.log("setUserInfo" + payload)
            let userInfo = {
                name: payload.name,
                email: payload.email,
                profile: payload.profile,
                gender: payload.gender,
                birthday: payload.birthday,
            }
            commit("loginSuccess", userInfo)
        },
         getToken({ commit }, payload) {
             axios.get('/api/auth/kakao/accessToken', {
                params: {
                    code: payload
                }
            })
            .then((res) => {
                console.log(res);
                commit('setToken', res)  
            })
            .catch((err) => {
                console.log(err);
            });        
        },
         setInfo({ commit }, payload) {
             axios.get('/api/auth/kakao/callback', {
                params: {
                    code: payload
                }
            })
                .then((res) => {
                    console.log(res);
                    let userInfo2 = {
                        name: res.data.properties.nickname,
                        email: res.data.kakao_account.email,
                        profile: res.data.properties.thumbnail_image,
                        birthday: res.data.kakao_account.birthday,
                        gender: res.data.kakao_account.gender,
                    }
                    commit("setUserInfo", userInfo2)  
                })
                .catch((err) => {
                    console.log(err);
                    commit("loginError")
                    //alert("이메일과 비밀번호를 확인하세요.")
                });        
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
        logout({ commit }) {
            commit("logout")
            router.push({ name: "Home" })
        },
    },
    
  modules: {
  },
})
