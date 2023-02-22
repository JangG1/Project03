<template>
<!--<div class="main">
    <img class="b1" src="@/assets/bannerImage/2.jpg"/>
    <img class="b3" src="@/assets/bannerImage/3.jpg"/>
    <img class="b4" src="@/assets/bannerImage/4.jpg"/>
</div>-->
이름 : <input type="text" v-model="testname"><br>
성별 : <input type="text" v-model="testgender"><br>
생일 : <input type="text" v-model="testbirthday"><br>

<input type="button" @click="arrPush" value="추가"><br>
<input type="button" @click="arrCorrect" value="수정"><br>
<input type="button" @click="arrCheck" value="확인"><br>
<div v-if="testview">
    {{ arr }}<br>    
    {{ arr[0].korName }}<br>
    {{ arr[0].korName.firstName }}<br>
    {{ arr[0].korName.lastName }}<br>
</div>
<br>
<input type="button" @click="test" value="test"><br>
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

[ { name: { first: kim, last: yeji }, gender: female, birth: 0202 },
{ name: { first: kim, last: yeji }, gender: female, birth: 0202 },
{ name: { first: kim, last: yeji }, gender: female, birth: 0202 } ]

pas[0].name.first = choi

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
            axios.get('/res/JPATest')
                .then((response) => {
                    console.log("성공")
                    console.log(response.data)
                })
                .catch(err => {
                    console.log(err)
                    console.log("안보내짐")
                })                

        },
        arrPush() {
            this.arr.push({
                korName: {
                    lastName: this.testname+"l",
                    firstName: this.testname+"f"
                },
                engName: {
                    lastName: this.testgender,
                    firstName: this.testgender
                },
                birthday: {
                    birthday: this.testbirthday
                }
            })
        },
        arrCorrect() {

            this.arr.splice(1, 1, {
                name: {
                    testname: this.testname
                },
                gender: {
                    gender: this.testgender
                },
                birthday: {
                    birthday: this.testbirthday
                }
            })

            //this.arr.push({name:{testname:this.testname}})
        },
        arrCheck() {
            this.testview = true;
        },
        point() {
            this.holdPoint += 500000;
            this.$store.dispatch("holdPoint", this.holdPoint);
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
        //this.getUserInfo();        
    },
}
</script>

<style>
.b2 {
    width: 90%;
    margin-left: 5%;
    margin-right: 5%;
}
</style>
