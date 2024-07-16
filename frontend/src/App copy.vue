<template>
  <div class="navigation" v-if="this.$route.name !== 'Home'">
    <!-- 네비게이션 바-->
    <div class="navBar">
      <!-- Fastrip 로고 -->
      <lottie-player
        class="logo"
        src="https://assets9.lottiefiles.com/packages/lf20_ghibwmba.json"
        background="transparent"
        speed="1"
        loop
        autoplay
      ></lottie-player>
      <a class="logoLink" href="/">Fastrip</a>
      <div class="subLink">
        <a class="homeLink" href="/">Home</a>
        <a href="/">Destinations</a>
        <a href="/Reservation">Reservation</a>
        <a href="/Contact">Contact</a>
      </div>
      <!-- 로그인 썸네일 -->
      <!--로그인-->
      <div class="loginBtn1" v-if="!isLogin">
        <lottie-player
          class="loginBtn1-1"
          src="https://assets1.lottiefiles.com/packages/lf20_voi0gxts.json"
          background="transparent"
          speed="1"
          loop
          autoplay
        ></lottie-player>
        <div class="loginText" v-if="!isLogin" @click="loginModal = true">
          Login
        </div>
      </div>

      <div class="loginBtn2" v-if="isLogin">
        <ProfileItem
          :profile="getProfile"
          :email="getEmail"
          @click="showLoginMenu"
        />
      </div>

      <div class="loginName" v-if="isLogin">
        {{ this.$store.state.userInfo.lastName
        }}{{ this.$store.state.userInfo.firstName }} 님
      </div>

      <div class="loginMenu" v-if="loginMenu"></div>
      <img class="loginMenu" v-if="loginMenu" src="@/assets/cloud.png" />
      <!--카카오 로그아웃-->
      <div
        class="logoutBtn"
        @click="kakaoLogout"
        v-if="loginMenu"
        v-show="OAuth === 'kakao'"
      >
        Logout
      </div>
      <!--네이버 로그아웃-->
      <div
        class="logoutBtn"
        @click="naverLogout"
        v-if="loginMenu"
        v-show="OAuth === 'naver'"
      >
        Logout
      </div>
    </div>
  </div>
  <!-- 로그인 모달 -->
  <div v-if="!isLogin">
    <LoginModal
      class="loginModal"
      @closeModal="loginModal = false"
      :loginModal="loginModal"
    />
  </div>

  <!-- 로딩화면 -->
  <div class="layerPopup" v-show="isLoading"></div>

  <router-view></router-view>
</template>

<script>
import ProfileItem from "@/components/ProfileItem.vue";
import LoginModal from "@/components/LoginModal.vue";
import axios from "axios";

export default {
  name: "App",
  data() {
    return {
      loginModal: false,
      loginMenu: false,
      OAuth: this.$store.state.userInfo.OAuth,
    };
  },
  components: {
    ProfileItem,
    LoginModal,
  },
  computed: {
    getProfile() {
      if (this.$store.state.userInfo == null)
        return require("@/assets/weblogin1.png");
      return this.$store.state.userInfo.profile;
    },
    isLogin() {
      return this.$store.state.isLogin;
    },
    isLoading() {
      return this.$store.state.isLoad;
    },
  },
  methods: {
    showLoginMenu() {
      this.loginMenu = this.loginMenu ? false : true;
    },
    hideParams() {
      history.pushState(null, "", `/`);
    },
    setParamInfo() {
      this.$store.dispatch("setUserInfo", this.$route.query);
    },
    kakaoLogout() {
      let access_token = this.$store.state.userInfo.access_token;

      axios
        .get("http://58.225.45.251:8200/api/kakao/logout/main/" + access_token)
        .then((response) => {
          alert(response.data);
          this.$store.dispatch("logout");
          this.$router.push("/");
          location.reload();
        });
      this.$store.dispatch("logout");
      this.$router.push("/");
      location.reload();
    },
    naverLogout() {
      let access_token = this.$store.state.userInfo.access_token;

      axios
        .get("http://58.225.45.251:8200/api/naver/logout/main/" + access_token)
        .then((response) => {
          alert(response.data);
          this.$store.dispatch("logout");
          this.$router.push("/");
          location.reload();
        });
      this.$store.dispatch("logout");
      this.$router.push("/");
      location.reload();
    },
  },

  mounted() {
    this.hideParams();
    this.setParamInfo();
  },
};
</script>

<style>
#app {
  font-family: "SEBANG_Gothic_Bold";
  white-space: nowrap;
}

/*@media (min-width: 1300px) {
    #app {
        font-family: 'SEBANG_Gothic_Bold';
        white-space: nowrap;
        max-width: 90%;
        margin: 0 auto;
        overflow: hidden;
    }
}*/

@font-face {
  font-family: "SEBANG_Gothic_Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SEBANG_Gothic_Bold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

.navigation {
  height: 75px;
  background: linear-gradient(
    135deg,
    rgba(16, 99, 79, 0.963) 0%,
    rgba(146, 199, 179, 0.959) 100%
  );
}

.navBar {
  display: flex;
}

.navBar > a,
.subLink > a {
  font-family: "SEBANG_Gothic_Bold";
  color: white;
  font-weight: 100;
  margin-top: 1%;
  margin-left: 3%;
  text-decoration-line: none;
}

.navBar > a:visited,
.subLink > a:visited {
  color: white;
}

.navBar > a:hover,
.subLink > a:hover {
  color: white;
}

.logo {
  position: absolute;
  width: 70px;
  height: 70px;
  padding-left: 1%;
}

.logoLink {
  font-size: 30px;
  padding-left: 35px;
}

.subLink {
  margin-left: 500px;
  margin-top: 2%;
}

.subLink > a {
  font-size: 16px;
  padding: 10px;
}

.nav {
  font-weight: 900;
}

.loginBtn1-1 {
  pointer-events: none;
  width: 60px;
  height: 60px;
  margin-top: 6px;
  border-radius: 6px;
  border: none;
  cursor: pointer;
}

.loginBtn1 {
  display: flex;
  margin-left: 4.5%;
}

.loginBtn2 {
  display: flex;
  margin-top: 1.3%;
  margin-left: 5%;
}

.loginName {
  font-family: "SEBANG_Gothic_Bold";
  color: white;
  font-weight: 900;
  padding: 10px 0;
  margin-top: 1.6%;
  margin-left: 1%;
  font-size: 12px;
  cursor: pointer;
  text-align: center;
  pointer-events: none;
}

.loginMenu {
  border: none;
  border-radius: 10px;
  width: 70px;
  background-color: white;
  position: absolute;
  margin-left: 88%;
  margin-top: 6%;
  text-align: center;
  z-index: 20;
}

.loginMenu:after {
  content: "";
  border-top: 0px solid transparent;
  border-left: 8px solid transparent;
  border-right: 8px solid transparent;
  border-bottom: 17px solid white;
  position: absolute;
  top: -15px;
  left: 25px;
}

.loginBtn {
  display: flex;
  margin-top: 1.4%;
  margin-left: 6%;
}

.loginText {
  font-family: "SEBANG_Gothic_Bold";
  font-size: 15px;
  font-weight: 900;
  margin-top: 27%;
  margin-left: 10%;
  cursor: pointer;
  color: white;
}

.logoutBtn {
  font-family: "SEBANG_Gothic_Bold";
  font-size: 12px;
  color: rgb(1, 115, 189);
  border: none;
  border-radius: 10px;
  width: 130px;
  background-color: rgba(255, 255, 255, 0);
  position: absolute;
  margin-left: 1075px;
  margin-top: 86px;
  text-align: center;
  cursor: pointer;
  z-index: 31;
}

.layerPopup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8);
  z-index: 30;
  justify-content: center;
  align-items: center;
  margin: 0 0 0 0;
}

.spinner {
  position: absolute;
  left: 30%;
  width: 550px;
  height: 550px;
}

.loginModal {
  width: 200px;
  height: 200px;
}

@media (min-width: 1000px) {
  .loginModal {
    content: none;
    width: 500px;
    height: 550px;
  }
}
</style>
