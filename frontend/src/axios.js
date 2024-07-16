"use strict";
import axios from "axios";
import store from './store/index'

axios.interceptors.request.use(
    function (config) {
        if (store.state.isLogin && Object.prototype.hasOwnProperty.call(localStorage, "access_token")) {
            config.headers.Authorization = 'Bearer ' + localStorage.getItem("access_token");
            if (localStorage.getItem("access_token") === "") {
                config.headers.Authorization = 'Bearer ' + localStorage.getItem("refresh_token")
            }
        }        
        return config;
    },
    function (error) {
        // Do something with request error
        return Promise.reject(error);
    }
);

// Add a response interceptor
axios.interceptors.response.use(
    function (response) {
        // 응답 객체의 config 속성이 null이 아닌 경우에만 실행
        return response;
    },
    async function (error) {
        // Do something with response error
        try {
            const errorAPI = error.response.config;
            console.log("response ERROR!!! " + error.response.status + ", " + errorAPI.retry);
            if ((error.response.status === 403 && errorAPI.retry === undefined) && localStorage.getItem('refresh_token') != null) {
                errorAPI.retry = true;
                store.dispatch('getRefreshToken');
                return await axios(errorAPI);
            }
        } catch (err) {
            console.error('[axios.interceptors.response] error : ', err.message);
        }
        return Promise.reject(error);
    }
);
