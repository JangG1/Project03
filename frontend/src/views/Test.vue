<template>
<div class="main">
    <img src="@/assets/kakaoLogo.png" @click="kakaoLoginBtn()" style="width: 200px;" alt="카카오 로그인" />
</div>
=======================================================
<br>

<button @click="redirect()">Kakao Login</button>
<button type="button" @click="logout">로그아웃</button>

<div v-if="isLogin()">로그인 상태</div><br>
<div v-if="!isLogin()">로그아웃 상태</div>

<!---->
<div v-if="$store.state.userInfo != null">
    name : {{$store.state.userInfo.lastName+$store.state.userInfo.firstName}}<br>
    email : {{$store.state.userInfo.email}} <br>
    gender : {{$store.state.userInfo.gender}} <br>
    birth : {{$store.state.userInfo.birthday}} <br>
    profile : {{$store.state.userInfo.profile}} <br>
    access_token : {{$store.state.userInfo.access_token}} <br>
    refresh_token : {{$store.state.userInfo.refresh_token}} <br>
</div>
==========================================================<br>

==========================================================<br>
<button @click="getUserInfo">getUserInfo</button><br>
<button @click="test">test</button><br>

==========================================================<br>
<button @click="point">point</button><br>{{ holdPoint }}
</template>

<script>
import axios from 'axios';

export default {
    name: "HelloWorld",
    data() {
        return {
            holdPoint: this.$store.state.holdPoint,
            name: '',
            email: '',
            gender: '',
            birthday: '',
            userInfo: {},
            res: {},
            code: "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8200/api/auth/kakao/callback&response_type=code",
            profile: '',

        }
    },
    components: {},
    created() {},
    methods: {
        point(){
            this.holdPoint += 500000;
            this.$store.dispatch("holdPoint",this.holdPoint);
        },
        redirect() {
            window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8200/api/auth/kakao/callback&response_type=code";
        },
        test() {
            window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8200/api/auth/kakao/callback2&response_type=code";
        },
        getUserInfo() {

            axios.get('/api/kakao/info')
                .then((response) => {
                    this.userInfo = response.data
                    console.log(this.userInfo)
                    this.$store.dispatch("setUserInfo", JSON.stringify(this.userInfo));
                    this.$store.dispatch("loginSuccess");
                })

        },
        logout() {
            let access_token = this.$store.state.userInfo.access_token;

            axios.get('/api/kakao/logout/' + access_token)
                .then((response) => {
                    alert(response.data)
                })

            this.$store.dispatch("logout");

            //window.location.href = "/";
        },
        isLogin() {
            return this.$store.state.isLogin;
        },

    },
    mounted() {
        //this.getUserInfo();        
    },
}
</script>

<style>

</style>
