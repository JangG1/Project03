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

<button @click="kakaoLoginTest">test</button>

{{ res }}
TOKEN : {{ $store.state.access_token }}
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
            res: []
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
        kakaoLoginTest() {
            //REST API KEY : 89675f71eb67437191dff96a64831fe8
            window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8080/Test&response_type=code";                        

            let APIUrl = window.location.href.toString().replace("http://localhost:8080/Test?code=", "");

            //alert(APIUrl)

            axios.get('/api/auth/kakao/callback', {
                    params: {
                        code: APIUrl
                    }
                })
                .then((response) => {
                    this.res = response.data                    
                })

                //let access_token = this.res;

                alert("res!!!" + this.res)

                //this.$store.dispatch("getToken", access_token);                
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
        test() {

        },
    },
    mounted() {

    },

    checkCookie(name) {
        if (cookie.get(name) !== 'Y') this.showModal('browserPopup');
    }
}
</script>

<style>

</style>
