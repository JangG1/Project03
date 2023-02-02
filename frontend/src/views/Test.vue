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

    <button @click="[redirect(),redirect2()]">Kakao Test</button>


{{ res }}<br>
accessToken : {{ $store.state.access_token }}<br>
name : {{$store.state.name2}} <br>
email : {{$store.state.email2}} <br>
gender : {{$store.state.gender2}} <br>
birth : {{$store.state.birthday2}} <br>
profile : {{$store.state.profile2}} <br>

==========================================================<br>
<button @click="getUserInfo">getUserInfo</button><br>
{{ userInfo }}<br>

<div v-for="userInfo in userInfo" :key="userInfo">
    {{ userInfo.id }}<br>
    {{ userInfo.email }}<br>
    {{ userInfo.name }}<br>
    {{ userInfo.profile }}<br>
    <img :src="userInfo.profile" style="width: 110px; height: 110px;"><br>
    {{ userInfo.gender }}<br>
    {{ userInfo.birthday }}<br>
    {{ userInfo.access_token }}<br>
</div>
</template>

<script>
import ProfileItem from "@/components/ProfileItem.vue";
import axios from 'axios';

export default {
    name: "HelloWorld",
    data() {
        return {
            name: '',
            email: '',
            gender: '',
            birthday: '',
            userInfo: [],
            res: [],
            code: "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8200/api/auth/kakao/callback&response_type=code",
            profile: '',

        }
    },
    components: {
        ProfileItem
    },
    created() {},
    methods: {
        redirect() {
            window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8200/api/auth/kakao/callback&response_type=code";
        },
        redirect2(){
            setTimeout(() => window.location.href = "/Test", 100);
        },
        getUserInfo() {
            axios.get('/api/kakao/info', {})
                .then((res) => {
                    console.log(res.data)
                    this.userInfo = res.data                                        
                })
        },
        /*kakaoLoginTest() {
            window.Kakao.Auth.authorize({
                redirectUri: 'http://localhost:8200/api/auth/kakao/callback',
            })
            this.kakaoLoginTest2();
        },
        async kakaoLoginTest2() {
            //REST API KEY : 89675f71eb67437191dff96a64831fe8
            location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8080/Test&response_type=code";

            let APIUrl = location.href.toString().replace("http://localhost:8080/Test?code=", "");

            this.$store.dispatch("setInfo", APIUrl)
            this.$store.dispatch("getToken", APIUrl)

        },
        kakaoLogin() {
            window.Kakao.Auth.login({
                scope: "profile_image, account_email",
                success: this.kakaoInfo,
            });
            this.$emit("closeModal");
        },
        async kakaoInfo(authObj) {
            console.log("=============1==============")
            console.log(authObj);
            console.log("===============2============")
            console.log(authObj.access_token);

            axios.get('/api/auth/kakao/callback', {
                    params: {
                        code: authObj.access_token
                    }
                })
                .then((response) => {
                    this.res = response.data
                })
        },*/
        kakaoLogout() {
            /*if (!window.Kakao.Auth.getAccessToken()) {
                console.log("Not logged in.");
                return;
            }*/
            window.Kakao.Auth.logout(function () {
                window.location.href = "/Test";
            });
            localStorage.clear(); // 전체삭제
        },
    },
    mounted() {

    },
}
</script>

<style>

</style>
