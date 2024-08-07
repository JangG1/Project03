<template>
  <div class="navigation" v-if="this.$route.name !== 'Home'">
    <!-- 네비게이션 바-->

    <!-- Fastrip 로고 -->
    <div class="mainLink">
      <lottie-player
        class="logo"
        src="https://assets9.lottiefiles.com/packages/lf20_ghibwmba.json"
        background="transparent"
        speed="1"
        loop
        autoplay
      ></lottie-player>
      <a class="logoLink" href="/">Fastrip</a>
    </div>

    <div class="subLink">
      <div class="subRoute">
        <a class="homeLink" href="/">Home</a>
      </div>
      <div class="subRoute">
        <a href="/Reservation">Reservation</a>
      </div>
      <div class="subRoute">
        <a href="/Contact">Contact</a>
      </div>
    </div>

    <!-- 로그인 썸네일 -->
    <!--로그인-->
    <div class="loginBtn" v-if="!isLogin">
      <lottie-player
        class="loginBtn1"
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

    <!--로그인 성공-->
    <div class="loginBtn2" v-if="isLogin">
      <ProfileItem
        :profile="getProfile"
        :email="getEmail"
        @click="showLoginMenu"
      />

      <div class="loginName" v-if="isLogin">
        {{ this.$store.state.userInfo.lastName
        }}{{ this.$store.state.userInfo.firstName }} 님
      </div>

      <div class="loginMenu" v-if="loginMenu">
        <img src="@/assets/cloud.png" />

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

a {
  text-decoration: none;
}

a:hover {
  color: white;
}

.navigation {
  height: 75px;
  background: linear-gradient(
    135deg,
    rgba(16, 99, 79, 0.963) 0%,
    rgba(146, 199, 179, 0.959) 100%
  );
  display: flex;
}

.mainLink {
  display: flex;
}

.subLink {
  width: 30%;
  display: flex;
  margin-left: 38%;
  line-height: 60px;
}

.subRoute {
  padding: 2%;
}

.subRoute > a {
  font-size: 16px;
  font-family: "SEBANG_Gothic_Bold";
  color: white;
  font-weight: 100;
}

a:visited,
.subRoute > a:visited {
  color: white;
}

a:hover,
.subRoute > a:hover {
  color: white;
}

.logo {
  float: left;
  width: 70px;
  height: 70px;
  padding-left: 1%;
}

.logoLink {
  float: left;
  font-size: 30px;
  color: white;
  text-align: center;
  line-height: 70px;
  margin-left: 5%;
}

.loginBtn {
  margin-left: 6%;
  display: flex;
  float: right;
  width: 10%;
}

.loginBtn1 {
  pointer-events: none;
  width: 60px;
  height: 60px;
  border-radius: 6px;
  border: none;
  cursor: pointer;
  margin-top: 2%;
  margin-left: 4.5%;
}

.loginBtn2 {
  margin-left: 6%;
  display: flex;
  float: right;
  width: 10%;
}

.loginName {
  font-family: "SEBANG_Gothic_Bold";
  font-size: 15px;
  font-weight: 900;
  cursor: pointer;
  color: white;
  width: 30%;
  text-align: center;
  line-height: 75px;
  margin-left: 10%;
}

.logoutBtn {
  font-family: "SEBANG_Gothic_Bold";
  font-size: 15px;
  font-weight: 900;
  cursor: pointer;
  color: white;
  margin-top: -60px;
  margin-left: -90px;
  z-index: 10;
}

.loginMenu > img {
  border-radius: 10px;
  width: 100px;
  height: 100px;
  z-index: 20;

  margin-top: 35px;
  margin-left: -115px;
}

.loginText {
  font-family: "SEBANG_Gothic_Bold";
  font-size: 15px;
  font-weight: 900;
  cursor: pointer;
  color: white;
  width: 33%;
  text-align: center;
  line-height: 75px;
  margin-left: 2%;
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

.spinner {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 8px solid #f3f3f3; /* Light grey */
  border-top: 8px solid teal; /* Blue */
  border-radius: 50%;
  width: 60px;
  height: 60px;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
