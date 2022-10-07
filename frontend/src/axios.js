"use strict";
import axios from "axios";
import store from './store/index'

//axios.defaults.baseURL = 'http://localhost:8200/';
// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
//axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

/*
let config = {
    // baseURL: process.env.baseURL || process.env.apiUrl || ""
    //timeout: 60 * 1000, // Timeout
    // withCredentials: true, // Check cross-site Access-Control
};*/

axios.interceptors.request.use(
    function (config) {
        //Do something before request is sent
        if (store.state.isLogin && Object.prototype.hasOwnProperty.call(localStorage, "access_token")) {
            config.headers.Authorization = 'Bearer ' + localStorage.getItem("access_token");
            if (localStorage.getItem("access_token") === "") {
                config.headers.Authorization = 'Bearer ' + localStorage.getItem("refresh_token")
            }
        }
        // if (localStorage.getItem("access_token") != null)
        //   config.headers.Authorization = 'Bearer ' + localStorage.getItem("access_token");
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
        // Do something with response data
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
