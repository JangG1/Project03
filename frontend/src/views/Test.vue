<template>
<div class="main">
    <img src="@/assets/kakaoLogo.png" @click="kakaoLoginBtn()" style="width: 200px;" alt="카카오 로그인" />
</div>
<button type="button" @click="kakaoLogin">로그인</button>
<button type="button" @click="kakaoLogout">로그아웃</button>
<ProfileItem :profile="getProfile" />

name : {{$store.state.name}} <br>
email : {{$store.state.email}} <br>
gender : {{$store.state.gender}} <br>
birth : {{$store.state.birthday}} <br>
Test : {{$store.state.userInfo}} <br>
  
=======================================================
<br>
<button @click="test">test</button>
{{ res }}
</template>

<script>
import ProfileItem from "@/components/ProfileItem.vue";
import cookie from 'js-cookie';
import axios from 'axios';

export default {
    name: "HelloWorld",
    data() {
        return {
            name: '',
            email: '',
            gender: '',
            birthday: '',
            res:[]
        }
    },
    components: {
        ProfileItem
    },
    computed: {
        getProfile() {
            if (this.$store.state.userInfo == null)
                return require("@/assets/weblogin1.png");
            return this.$store.state.userInfo.profile;
        },
    },
    created() {},
    methods: {
        kakaoLogin() {
            // console.log(window.Kakao);
            window.Kakao.Auth.login({
                scope: "profile_nickname, profile_image, account_email, gender, birthday",
                success: this.kakaoInfo,
            });
        },
        async kakaoInfo(authObj) {
            console.log(authObj);
            const userInfo = {
                name: null,
                email: null,
                profile: null,
                gender: null,
                birthday: null
            };
            await window.Kakao.API.request({
                url: "/v2/user/me",
                success: (res) => {
                    const kakao_account = res.kakao_account;
                    userInfo.name = kakao_account.profile.nickname;
                    userInfo.email = kakao_account.email;
                    userInfo.profile = kakao_account.profile.thumbnail_image_url;
                    userInfo.gender = kakao_account.gender;
                    userInfo.birthday = kakao_account.birthday;

                    console.log(userInfo.name)
                    console.log(userInfo.profile)
                    console.log(authObj.access_token)
                },
                fail: (error) => {
                    this.$router.push("/errorPage");
                    console.log(error);
                },

            });
            this.$store.dispatch('setUserInfo', userInfo)

            this.name = this.$store.state.userInfo.name;
            this.email = this.$store.state.userInfo.email;
            this.gender = this.$store.state.userInfo.gender;
            this.birthday = this.$store.state.userInfo.birthday;
        },
        kakaoLogout() {
            if (!window.Kakao.Auth.getAccessToken()) {
                console.log("Not logged in.");
                return;
            }
            window.Kakao.Auth.logout(function () {
                alert("로그아웃 되었습니다.");
                window.location.href = "/Test";
            });
            localStorage.clear(); // 전체삭제
        },
        test(){
//로그인 시 DB로 name과 email 전송
//로그인 상태에서 예약 완료시 예약데이터에 name과 email 전송
//로그인 상태에서 email 기준으로 예약되었던 email과 매칭 후 예약 내역 조회
        let email = this.$store.state.email;
            axios.get('/res/resList/' + email)
                .then((response) => {
                    this.res = response.data
                })
        },
    },
    mounted() {                
        this.test();
    },

    checkCookie(name) {
        if (cookie.get(name) !== 'Y') this.showModal('browserPopup');
    }
}
</script>

<style>

</style>
