<template>
  <div class="login-modal px-5 py-5" v-if="loginModal">
    <div class="login-text my-4">
      <h4>로그인</h4>
    </div>
    <div class="loginBtns mt-5 mb-5">
      <div @click="kakaoLogin" class="kakaoBtn mb-3">
        <img src="@/assets/kakaoLogo.png" alt="" />
        <br />
        <!-- 카카오 아이디로 로그인 -->
      </div>
      <div @click="googleLogin" class="googleBtn">
        <img src="@/assets/googleLogo.png" alt="" />
        <br />
        <!-- 구글 아이디로 로그인 -->
      </div>
      <div id="my-signin2" style="display: none"></div>
    </div>
    <div class="closeBtn">
      <button @click="$emit('closeModal')">닫기</button>
    </div>
  </div>
  <!--login-modal-end-->
</template>

<script>
import axios from "axios";
export default {
  name: "LoginModal",
  props: {
    loginModal: Boolean,
  },
  methods: {
    kakaoLogin() {
      // console.log(window.Kakao);
      window.Kakao.Auth.login({
        scope: "profile_image, account_email",
        success: this.kakaoInfo,
      });
      this.$emit("closeModal");
    },
    async kakaoInfo(authObj) {
      console.log(authObj);
      const userInfo = {
        email: null,
        profile: null,
      };
      await window.Kakao.API.request({
        url: "/v2/user/me",
        success: (res) => {
          const kakao_account = res.kakao_account;
          userInfo.email = kakao_account.email;
          userInfo.profile = kakao_account.profile.thumbnail_image_url;

          if (userInfo.email != null || userInfo.email != "") {
            axios
              .post("/api/user/save/normal", JSON.stringify(userInfo), {
                headers: { "Content-Type": `application/json` },
              })
              .then((res) => {
                res;
                console.log("가입성공");
              })
              .catch((err) => {
                err;
                console.log("기존가입");
              });
          }
        },
        fail: (error) => {
          this.$router.push("/errorPage");
          console.log(error);
        },
      });
      let form = new FormData();
      form.append("email", userInfo.email);
      form.append("password", "DMTT");
      this.$store.dispatch("getToken", form);
      this.$store.dispatch("setUserInfo", userInfo);
    },
    kakaoLogout() {
      // eslint-disable-next-line
      if (!window.Kakao.Auth.getAccessToken()) {
        console.log("Not logged in.");
        return;
      }
      window.Kakao.Auth.logout(function (response) {
        alert(response + "logout");
        window.location.href = "/";
      });
      localStorage.clear(); // 전체삭제
    },
    //구글 버튼
    googleLogin() {
      var self = this;
      window.gapi.signin2.render("my-signin2", {
        scope: "picture",
        width: 240,
        height: 50,
        longtitle: true,
        theme: "dark",
        onsuccess: this.googleInfo,
        onfailure: this.googleLogout,
      });
      setTimeout(function () {
        if (!self.googleLoginCheck) {
          const auth = window.gapi.auth2.getAuthInstance();
          auth.isSignedIn.get();
          const btn = document.querySelector(".abcRioButton");
          if (btn != null) btn.click();
        }
      }, 100);
    },
    //구글 로그인 이후 실행되는 콜백함수(성공)
    async googleInfo(googleUser) {
      //const user_join_type = "g"
      const profile = googleUser.getBasicProfile();
      const googleEmail = profile.getEmail();
      const googleProfile = profile.getImageUrl();
      let googleData = {};
      googleData.email = googleEmail;
      googleData.profile = googleProfile;
      if (googleData.email != null || googleData.profile != "") {
        await axios
          .post("/api/user/save/normal", JSON.stringify(googleData), {
            headers: { "Content-Type": `application/json` },
          })
          .then((res) => {
            res;
            console.log("가입성공");
          })
          .catch((err) => {
            err;
            console.log("가입에러");
          });
      }
      let form = new FormData();
      form.append("email", googleData.email);
      form.append("password", "DMTT");
      this.$store.dispatch("getToken", form);
      this.$store.dispatch("setUserInfo", googleData);
      this.$emit("closeModal");
    },
    googleLogout() {
      // eslint-disable-next-line
      const auth = gapi.auth2.getAuthInstance();
      auth.signOut().then(function () {
        console.log("User signed out.");
      });
      localStorage.clear();
    },
  },
};
</script>

<style>
.login-modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 30%;
  height: 50%;

  text-align: center;
  border-radius: 25px;
  background-color: white;

  z-index: 8;
  box-shadow: 2px 2px 10px lightgrey;
}

.loginBtns {
  justify-content: center;
}

.loginBtns > div {
  margin-left: auto;
  margin-right: auto;
  width: 80%;
  padding: 20px;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
}

.kakaoBtn > img {
  width: 30%;
}

.googleBtn > img {
  width: 30%;
}

.closeBtn > button {
  width: 30%;
  background: #0d66ff;
  color: white;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  padding: 3%;
}
</style>