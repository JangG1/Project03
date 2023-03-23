<template>
<div class="navigation" v-if="this.$route.name !== 'Home'">
    <!-- 네비게이션 바-->
    <div class="navBar">
        <!-- Fastrip 로고 -->
        <!-- <router-link v-bind:to="'/'" class="logoRouter"><img src="./assets/Logo2.png" class="logo"></router-link> -->
        <a class="logoLink" href="/">Fastrip</a>

        <!-- 예약 조회 버튼 -->
        <!-- <ul class="nav">
            <router-link to="/Reservation" class="nav-link px-2 link-secondary">예약</router-link>
        </ul> -->

        <div class="subLink">
        <a class="homeLink" href="/">Home</a>
        <a href="#part2">Dentinations</a>
        <a href="/Reservation">Reservation</a>
        <a href="/Test">Test</a>
        </div>
        <!-- 로그인 썸네일 -->
            <!--로그인-->
            <!-- <div class="email" v-if="isLogin">{{ this.$store.state.userInfo.email }} 님</div> -->
            <div class="loginBtn" @click="loginModal = true">
                <ProfileItem :profile="getProfile" :email="getEmail" />
                <div class="loginText" v-if="!isLogin">Login</div>
            </div>
            <!--로그아웃-->
            <div class="logoutBtn" @click="logout" v-if="isLogin">
                Logout
            </div>


    </div>
</div>
    <!-- 로그인 모달 -->
    <div v-if="!isLogin">
        <LoginModal class="loginModal" @closeModal="loginModal = false" :loginModal="loginModal" />
    </div>



    <!-- <Home v-if="$route.name !== 'Arrival'"></Home>     -->

    <!-- 로딩화면 -->
    <div class="layerPopup" v-show="isLoading">
        <div class="spinner"></div>
    </div>

    <router-view></router-view>
</template>

<script>
import ProfileItem from "@/components/ProfileItem.vue";
import LoginModal from "@/components/LoginModal.vue";
import axios from 'axios';

export default {
    name: "App",
    data() {
        return {
            loginModal: false,
        };
    },
    components: {
        ProfileItem,
        LoginModal,
    },
    computed: {
        getProfile() {
            if (this.$store.state.userInfo == null)
                return require("@/assets/weblogin1.png");
            return this.$store.state.userInfo.profile;
        },
        isLogin() {
            if (this.$store.state.isLogin == true) {
                //console.log("로그인 되었습니다.")
            }
            return this.$store.state.isLogin;
        },
        isLoading() {
            //console.log("loading " + this.$store.state.isLoad);
            return this.$store.state.isLoad;
        },
    },
    methods: {
        hideParams() {
            history.pushState(null, "", `/Test`)
        },
        setParamInfo() {
            if (this.$route.query.email != null) {
                this.$store.dispatch("setUserInfo", this.$route.query)
            }
        },
        logout() {
            let access_token = this.$store.state.userInfo.access_token;
            let email = this.$store.state.userInfo.email;

            axios.get('/api/kakao/logout/' + access_token, {
                params: {
                    email: email
                }
                })
                .then((response) => {
                    alert(response.data)
                })

            this.$store.dispatch("logout");
            this.$router.push('/')

        },
    },

    mounted() {
        this.hideParams();
        this.setParamInfo();
    },
};
</script>

<style>
#app {
    font-family: "NanumBarunGothicBold";
    white-space: nowrap;            
}

@font-face {
    font-family: "NanumBarunGothicBold";
    src: url('assets/fonts/NanumBarunGothic.eot')format('eot');
    src: url('assets/fonts/NanumBarunGothic.woff')format('woff');
}

.navigation{
    height: 130px;
    background-color: rgba(22, 122, 122, 0.507);
}

.navBar {
    display: flex;            
}

.navBar > a,
.subLink > a{
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    color: white;
    font-weight: 900;
    margin-top: 1.5%;
    margin-left: 3%;
}

.logoLink{
    font-size: 40px;
    padding-left: 1%;
}

.subLink{    
    margin-left: 40%;
    margin-top: 2.5%;        
}

.subLink > a{
    padding: 20px;
}
/* .email {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    color: rgb(95, 95, 95);
    padding: 3%;
    margin-right: 10px;
} */

.email {            
    color: white;
    padding: 3%;
    margin-right: 10px;
}

.nav{
    font-weight: 900;    
}

.nav-link {
    margin-top: 40px;
    margin-left: 15px;
}

.loginBtn {
    display: flex;
    margin-top: 2.3%;
    margin-left: 11%;
}

.loginText{
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    font-size: 18px;
    font-weight: 900;
    margin-top: 10%;
    margin-left: 20%;
    cursor: pointer;
    color: white;
}

.logoutBtn{
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    color: white;
    font-weight: 900;
    padding: 10px 0;    
    margin-top: 2.3%;
    margin-left: 1%;
    font-size: 18px;
}

/* .logoutBtn {
    color: #999;
    border: 1px solid black;
    border-radius: 10px;
    cursor: pointer;
    height: 50px;
    width: 100px;
    padding: 10px 0;
    border: 3px solid rgb(193, 188, 188);
    margin-left: 5px;
    margin-right: 20px;
    text-align: center;    
} */

.layerPopup {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.8);
    z-index: 1000;
    justify-content: center;
    align-items: center;
    margin: 0 0 0 0;
}

.spinner {
    position: absolute;
    top: 50%;
    left: 50%;
    border: 8px solid #f3f3f3;
    /* Light grey */
    border-top: 8px solid teal;
    /* Blue */
    border-radius: 50%;
    width: 60px;
    height: 60px;
    animation: spinner 2s linear infinite;
}

@keyframes spinner {
    0% {
        transform: rotate(0deg);
    }

    100% {
        transform: rotate(360deg);
    }
}

.loginModal {
    content: url("@/assets/Logo2.png");
    width: 200px;
    height: 200px;
}

@media (min-width: 1000px) {
    .loginModal {
        content: none;
        width: 500px;
        height: 550px;
    }
}
</style>
