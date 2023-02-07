<template>
<div class="main">
    <img src="@/assets/kakaoLogo.png" @click="kakaoLoginBtn()" style="width: 200px;" alt="카카오 로그인" />
</div>
=======================================================
<br>

    <button @click="redirect()">Kakao Login</button>
    <button type="button" @click="logout">로그아웃</button>

<a href="javascript:window.history.back();"></a>
<!---->
<div v-if="$store.state.userInfo != null">
name : {{$store.state.userInfo.name}} <br>
email : {{$store.state.userInfo.email}} <br>
gender : {{$store.state.userInfo.gender}} <br>
birth : {{$store.state.userInfo.birthday}} <br>
profile : {{$store.state.userInfo.profile}} <br>
access_token : {{$store.state.userInfo.access_token}} <br>
</div>
==========================================================<br>

==========================================================<br>
<button @click="getUserInfo">getUserInfo</button><br>

</template>

<script>
import axios from 'axios';

export default {
    name: "HelloWorld",
    data() {
        return {
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
    components: {
    },
    created() {},
    methods: {
        redirect() {
            window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8200/api/auth/kakao/callback&response_type=code";                                   
        },
        getUserInfo() {            
            axios.get('/api/kakao/info')
                .then((response) => {
                    this.userInfo = response.data
                    console.log(this.userInfo)
                    this.$store.dispatch("setUserInfo", JSON.stringify(this.userInfo));
                })
        },
        logout(){
            let access_token = this.$store.state.userInfo.access_token;
            
            axios.get('/api/kakao/logout/' + access_token)
                .then((response) => {                    
                    alert(response.data)
                })

                
                this.$store.dispatch("logout");

                //window.location.href = "/";
        },

    },
    mounted() {
        
    },
}
</script>

<style>

</style>
