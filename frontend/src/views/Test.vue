<template>
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
<input type="button" @click="kakaoLoginTest" value="test"><br>
🎈{{ $store.state.userInfo3 }}🎈<br>
==========================================================<br>
{{ params }}<br>
{{ this.$route.query }} <br>
{{ this.$route.query.email }} <br>
{{ this.$route.query.name }} <br>
{{ this.$route.query.profile }} <br>
{{ this.$route.query.gender }} <br>
{{ this.$route.query.birthday }} <br>
{{ this.$route.query.access_token }} <br>
{{ this.$route.query.refreshtoken }} <br>
</template>

<script>
import axios from 'axios';

export default {
    name: "HelloWorld",
    data() {
        return {
            params: this.$route.query,
            holdPoint: this.$store.state.holdPoint,
            name: '',
            email: '',
            gender: '',
            birthday: '',
            userInfo: {},
            res: {},
            code: "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8200/api/auth/kakao/callback&response_type=code",
            profile: '',
            arr: [],
            testname: '',
            testgender: '',
            testbirthday: '',
            testview: false,
        }
    },
    components: {},
    created() {},
    methods: {
        test() {
            history.pushState(null, "", `/Test`)
        },
        setInfo(){
            if(this.$route.query.email != null){
            this.$store.dispatch("setUserInfo3", this.$route.query)
            }
        },
        //카카오 로그인
        kakaoLogin() {
            if (!this.$store.state.isLogin) {
                this.$store.dispatch("login")
                window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8200/api/auth/kakao/callback&response_type=code";
            }
        },
        redirect() {
            window.location.href = "https://kauth.kakao.com/oauth/authorize?client_id=89675f71eb67437191dff96a64831fe8&redirect_uri=http://localhost:8200/api/auth/kakao/callback&response_type=code";
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
        this.test();        
        this.setInfo();
    },
}
</script>

<style>
.b1,
.b2,
.b3,
.b4 {
    border: 1px solid;
    border-radius: 4px;
    width: 1800px;
    margin-left: 3%;
    margin-right: 3%;
}

ul.imgs li {
    position: absolute;
    left: 640px;
    transition-delay: 1s;
    /* 새 슬라이드가 이동해 오는 동안 이전 슬라이드 이미지가 배경이 보이도록 지연 */
}

.bullets {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    bottom: 80px;
    z-index: 2;
}

.bullets label {
    display: inline-block;
    border: 2px solid rgba(154, 154, 154, 0.55);
    border-radius: 4px;
    background-color: #fff;
    width: 25px;
    height: 10px;
    margin-left: 3%;
    margin-right: 3%;
}

/* 현재 선택된 불릿 배경 흰색으로 구분 표시 */
.slider input[type=radio]:nth-child(1):checked~.bullets>label:nth-child(1) {
    background-color: rgba(154, 154, 154, 0.55);
}

.slider input[type=radio]:nth-child(2):checked~.bullets>label:nth-child(2) {
    background-color: rgba(154, 154, 154, 0.55);
}

.slider input[type=radio]:nth-child(3):checked~.bullets>label:nth-child(3) {
    background-color: rgba(154, 154, 154, 0.55);
}

.slider input[type=radio]:nth-child(4):checked~.bullets>label:nth-child(4) {
    background-color: rgba(154, 154, 154, 0.55);
}

.slider input[type=radio]:nth-child(1):checked~ul.imgs>li:nth-child(1) {
    left: 0;
    transition: 0.5s;
    z-index: 1;
}

.slider input[type=radio]:nth-child(2):checked~ul.imgs>li:nth-child(2) {
    left: 0;
    transition: 0.5s;
    z-index: 1;
}

.slider input[type=radio]:nth-child(3):checked~ul.imgs>li:nth-child(3) {
    left: 0;
    transition: 0.5s;
    z-index: 1;
}

.slider input[type=radio]:nth-child(4):checked~ul.imgs>li:nth-child(4) {
    left: 0;
    transition: 0.5s;
    z-index: 1;
}
</style>
