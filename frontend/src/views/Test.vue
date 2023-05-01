<template>
=======================================================
<br>

<input type="text" v-model="test">{{ this.state }}<br>

<button type="button" @click="test1()">test1</button>AWS http://52.44.188.93:8200/api/<br>
<button type="button" @click="test2()">test2</button>127.0.0.1<br>
<button type="button" @click="kakaoLogin()">kakaoLogin</button><br>
</template>

<script>
import axios from 'axios';

export default {
    name: "HelloWorld",
    data() {
        return {
            test: '',
            state: '대기중'
        }
    },
    components: {},
    created() {},
    methods: {
        test1() {
            axios.post("http://52.44.188.93:8200/api/axiosTest", {
                    test: this.test
                })
                .then(res => {
                    console.log(res)
                    console.log("보내짐")
                    this.state = "보내짐"
                })
                .catch(err => {
                    console.log(err)
                    console.log("안보내짐")
                    this.state = "안보내짐"
                })
        },
        test2() {
            axios.post("http://192.168.56.1:8200/api/axiosTest", {
                    test: this.test
                })
                .then(res => {
                    console.log(res)
                    console.log("보내짐")
                    this.state = "보내짐"
                })
                .catch(err => {
                    console.log(err)
                    console.log("안보내짐")
                    this.state = "안보내짐"
                })
        },

        kakaoLogin() {

            window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://52.44.188.93:8200/api/auth/kakaoLogin/main&response_type=code";

        },

    },
    mounted() {},
}
</script>

<style>
.b1,
.b2,
.b3,
.b4 {
    border: 1px solid;
    border-radius: 4px;
    width: 1800px;
    margin-left: 3%;
    margin-right: 3%;
}

ul.imgs li {
    position: absolute;
    left: 640px;
    transition-delay: 1s;
    /* 새 슬라이드가 이동해 오는 동안 이전 슬라이드 이미지가 배경이 보이도록 지연 */
}

.bullets {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    bottom: 80px;
    z-index: 2;
}

.bullets label {
    display: inline-block;
    border: 2px solid rgba(154, 154, 154, 0.55);
    border-radius: 4px;
    background-color: #fff;
    width: 25px;
    height: 10px;
    margin-left: 3%;
    margin-right: 3%;
}

/* 현재 선택된 불릿 배경 흰색으로 구분 표시 */
.slider input[type=radio]:nth-child(1):checked~.bullets>label:nth-child(1) {
    background-color: rgba(154, 154, 154, 0.55);
}

.slider input[type=radio]:nth-child(2):checked~.bullets>label:nth-child(2) {
    background-color: rgba(154, 154, 154, 0.55);
}

.slider input[type=radio]:nth-child(3):checked~.bullets>label:nth-child(3) {
    background-color: rgba(154, 154, 154, 0.55);
}

.slider input[type=radio]:nth-child(4):checked~.bullets>label:nth-child(4) {
    background-color: rgba(154, 154, 154, 0.55);
}

.slider input[type=radio]:nth-child(1):checked~ul.imgs>li:nth-child(1) {
    left: 0;
    transition: 0.5s;
    z-index: 1;
}

.slider input[type=radio]:nth-child(2):checked~ul.imgs>li:nth-child(2) {
    left: 0;
    transition: 0.5s;
    z-index: 1;
}

.slider input[type=radio]:nth-child(3):checked~ul.imgs>li:nth-child(3) {
    left: 0;
    transition: 0.5s;
    z-index: 1;
}

.slider input[type=radio]:nth-child(4):checked~ul.imgs>li:nth-child(4) {
    left: 0;
    transition: 0.5s;
    z-index: 1;
}
</style>
