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
<button type="button" @click="test">test</button>
</template>

<script>
//import axios from 'axios';
import ProfileItem from "@/components/ProfileItem.vue";

export default {
    name: "HelloWorld",
    data() {
        return {
            name: '',
            email: '',
            gender: '',
            birthday: '',

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
        test() {
            let today = new Date();

            let year = today.getFullYear(); // 년도
            let month = today.getMonth() + 1; // 월
            let date = today.getDate(); // 날짜
            let day = today.getDay(); // 요일 

            return alert(year + "/" + month + "/" + date + "/" + day)
        },
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
            // eslint-disable-next-line
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
    }
}
</script>

<style scoped>
.main {
    padding: 0 25%;
}
</style>
