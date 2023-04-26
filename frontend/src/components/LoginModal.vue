<template>
<div class="login-modal px-5 py-5" v-if="loginModal">
    <div class="login-text">
        <p>로그인</p>
    </div>
    <div class="loginBtns mt-4 mb-5">
        <!-- 카카오 아이디로 로그인 -->
        <div @click="kakaoLogin" class="kakaoBtn mb-3" v-if="$route.name !== 'Arrival'">
            <img src="@/assets/kakaoLogo.png" />
            <br>
        </div>
        <!-- 카카오 아이디로 로그인(Arrive 페이지용) -->
        <div @click="kakaoLogin2" class="kakaoBtn mb-3" v-if="$route.name == 'Arrival'">
            <img src="@/assets/kakaoLogo.png" />
            <br>
        </div>
        <!-- 네이버 아이디로 로그인 -->
        <div @click="naverLogin" class="naverBtn">
            <img src="@/assets/ready_naverLogo.jpg" />
            <br>
        </div>
        <br>
        <span class="loginHint">
            <span class="asterisk"> *</span>
            비회원시에도 모든 기능이 가능합니다.
        </span>
    </div>

    <div class="closeBtn">
        <button @click="$emit('closeModal')">닫기</button>
    </div>
</div>
<!--login-modal-end-->
</template>

<script>
import axios from 'axios';

export default {
    name: "LoginModal",
    props: {
        loginModal: Boolean,
    },
    methods: {
        //카카오 로그인
        kakaoLogin() {
            if (!this.$store.state.isLogin) {
                window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://58.225.45.251:8200/api/auth/kakao/callback&response_type=code";
            }
            if (this.$route.query.email != null) {
                this.$store.dispatch("login")
            }
        },
        //카카오 로그인(Arrive 페이지용)
        kakaoLogin2() {
            if (!this.$store.state.isLogin) {
                window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://58.225.45.251:8200/api/auth/kakao/callback2&response_type=code";
            }
            if (this.$route.query.email != null) {
                this.$store.dispatch("login")
            }
        },
        logout() {
            let access_token = this.$store.state.userInfo.access_token;

            axios.get('http://58.225.45.251:8200/api/kakao/logout/' + access_token)
                .then((response) => {
                    alert(response.data)
                })

            this.$store.dispatch("logout");

            //window.location.href = "/";
        },
        naverLogin() {
            alert('준비중 입니다.')
        }
    },
    mounted() {

    }
};
</script>

<style>
.login-text>p {
    color: teal;
    font-size: 20px;
    font-weight: 900;
}

.login-modal {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    border-radius: 25px;
    background-color: white;
    z-index: 11;
    border-radius: 15px;
    box-shadow: -8px -8px 16px -10px rgba(255, 255, 255, 1), 8px 8px 16px -10px rgba(0, 0, 0, .15);
    border: 3px solid rgb(193, 188, 188);
}

.loginBtns {
    justify-content: center;
}

.loginBtns>div {
    margin-left: auto;
    margin-right: auto;
    width: 80%;
    padding: 10px;
    border-radius: 10px;
}

.kakaoBtn>img {
    width: 60%;
    cursor: pointer;
}

.naverBtn>img {
    width: 60%;
    cursor: pointer;
    opacity: 0.2;
}

.closeBtn>button {
    width: 100px;
    height: 40px;
    border: none;
    border: 1.5px solid white;
    background: teal;
    border-radius: 10px;
    font-size: 20px;
    color: white;
    display: table;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 30px;
}

.loginHint {
    font-size: 12px;
    margin-left: 6px;
    opacity: 0.9;
}

.asterisk{
    color: red;
}
</style>
