<template>
<div class="login-modal px-5 py-5" v-if="loginModal">
    <div class="login-text">
        <p>로그인</p>
    </div>
    <div class="loginBtns mt-4 mb-5">
        <div @click="kakaoLogin" class="kakaoBtn mb-3">
            <img src="@/assets/kakaoLogo.png" />
            <br>
            <!-- 카카오 아이디로 로그인 -->
        </div>
        <div @click="naverLogin" class="naverBtn">
            <img src="@/assets/naverLogo.jpg" />
            <br>
            <!-- 네이버 아이디로 로그인 -->
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
import axios from 'axios';

export default {
    name: "LoginModal",
    props: {
        loginModal: Boolean,
    },
    methods: {
        //카카오 로그인
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
            const userInfo = {};
            await window.Kakao.API.request({
                url: "/v2/user/me",
                success: (res) => {
                    const kakao_account = res.kakao_account;
                    userInfo.email = kakao_account.email;
                    userInfo.profile = kakao_account.profile.thumbnail_image_url;
                    userInfo.name = kakao_account.profile.nickname;
                    userInfo.birthday = kakao_account.birthday;
                    userInfo.gender = kakao_account.gender;

                    if (userInfo.email != "") {
                        axios
                            .post("/api/user/save/normal", 
                            JSON.stringify(userInfo), {
                                headers: {
                                    "Content-Type": `application/json`
                                },
                            })
                            .then((res) => {
                                res;
                                console.log("가입성공");
                                console.log("email : " + userInfo.email);
                            })
                            .catch((err) => {
                                err;
                                console.log("기존가입");
                            });
                    }

                },              
            });
            let form = new FormData();
            form.append("email", userInfo.email);
            form.append("password", "Fastrip");

            this.$store.dispatch("getToken", form);
            this.$store.dispatch("setUserInfo", userInfo);

            setTimeout(this.kakaoLogout, 6000000); // 1시간후 로그아웃 함수 실행.

        },
        kakaoLogout() {            
            // if (!window.Kakao.Auth.getAccessToken()) {
            //     console.log("Not logged in.");
            //     return;
            // }

            window.Kakao.Auth.logout(function () {
                alert("로그아웃 되었습니다.");
                window.location.href = "/";
            });

            localStorage.clear(); // 전체삭제
        },

        //네이버 로그인
        naverLogin() {

        },
        //구글 로그인 이후 실행되는 콜백함수(성공)
        async naverInfo() {

        },
        naverLogout() {

        },
    },
    mounted() {

    }
};
</script>

<style>
p {
    color: #999;
    font-size: 24px;
}

.login-text p {
    color: teal;
    font-size: 25px;
    font-weight: 900;
    padding-top: 20px;
}

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
    padding: 20px;
    border-radius: 20px;
}

.kakaoBtn>img {
    width: 53%;
    cursor: pointer;
}

.naverBtn>img {
    width: 60%;
    cursor: pointer;
}

.closeBtn>button {
    width: 150px;
    height: 50px;
    border: none;
    border: 1.5px solid white;
    background: teal;
    border-radius: 20px;
    font-size: 24px;
    color: white;
    display: table;
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;
    margin-bottom: 30px;
}
</style>
