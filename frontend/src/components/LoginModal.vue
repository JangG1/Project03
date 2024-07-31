<template>
  <div class="login-modal px-5 py-5" v-if="loginModal">
    <div class="login-text">
      <p>로그인</p>
    </div>
    <div class="loginBtns">
      <!-- 카카오 아이디로 로그인 -->
      <div @click="kakaoLogin" class="kakaoBtn" v-if="$route.name !== 'Return'">
        <img src="@/assets/kakaoLogo.png" />
        <br />
      </div>
      <!-- 카카오 아이디로 로그인(Arrive 페이지용) -->
      <div @click="kakaoLogin2" class="kakaoBtn" v-if="$route.name == 'Return'">
        <img src="@/assets/kakaoLogo.png" />
        <br />
      </div>
      <!-- 네이버 아이디로 로그인 -->
      <div @click="naverLogin" class="naverBtn" v-if="$route.name !== 'Return'">
        <img src="@/assets/naverLogo.jpg" />
        <br />
      </div>
      <!-- 네이버 아이디로 로그인 -->
      <div @click="naverLogin2" class="naverBtn" v-if="$route.name == 'Return'">
        <img src="@/assets/naverLogo.jpg" />
        <br />
      </div>
      <br />
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
import { KAKAO_KEY, NAVER_KEY, EX_IP } from "../config";

export default {
  name: "LoginModal",
  props: {
    loginModal: Boolean,
  },

  methods: {
    //카카오 로그인
    kakaoLogin() {
      if (!this.$store.state.isLogin) {
        //백엔드 전송
        window.location.href =
          "https://kauth.kakao.com/oauth/authorize?client_id=" +
          KAKAO_KEY +
          "&redirect_uri=" +
          EX_IP +
          ":8200/api/auth/kakaoLogin/main&response_type=code";
        this.$store.dispatch("kakaoLogin");
      }
    },
    //카카오 로그인(Return 페이지용)
    kakaoLogin2() {
      if (!this.$store.state.isLogin) {
        window.location.href =
          "https://kauth.kakao.com/oauth/authorize?client_id=" +
          KAKAO_KEY +
          "&redirect_uri=" +
          EX_IP +
          ":8200/api/auth/kakaoLogin/return&response_type=code";
        this.$store.dispatch("kakaoLogin");
      }
    },
    naverLogin() {
      if (!this.$store.state.isLogin) {
        window.location.href =
          "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" +
          NAVER_KEY +
          "&redirect_uri=" +
          EX_IP +
          ":8200/api/auth/naverLogin/main&state=hLiDdL2uhPtsftcU1";
        this.$store.dispatch("naverLogin");
      }
    },
    //네이버 로그인(Return 페이지용)
    naverLogin2() {
      if (!this.$store.state.isLogin) {
        window.location.href =
          "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" +
          NAVER_KEY +
          "&redirect_uri=" +
          EX_IP +
          ":8200/api/auth/naverLogin/return&state=hLiDdL2uhPtsftcU1";
        this.$store.dispatch("naverLogin");
      }
    },
  },
};
</script>

<style>
.login-text > p {
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
  box-shadow: -8px -8px 16px -10px rgba(255, 255, 255, 1),
    8px 8px 16px -10px rgba(0, 0, 0, 0.15);
  border: 3px solid rgb(193, 188, 188);
}

.loginBtns {
  justify-content: center;
}

.loginBtns > div {
  margin-left: auto;
  margin-right: auto;
  width: 80%;
  padding: 10%;
  border-radius: 10px;
}

.kakaoBtn > img {
  width: 85%;
  cursor: pointer;
}

.naverBtn > img {
  width: 95%;
  cursor: pointer;
}

.closeBtn > button {
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
  margin-top: 10%;
}

.loginHint {
  font-size: 11px;
  opacity: 0.9;
}

.asterisk {
  color: red;
}
</style>
