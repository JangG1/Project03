<template>
<div class="navigation" v-if="this.$route.name !== 'Home'">
    <!-- 네비게이션 바-->
    <div class="navBar">
        <!-- Fastrip 로고 -->
        <a class="logoLink" href="/">Fastrip</a>

        <div class="subLink">            
        <a class="homeLink" href="/">Home</a>
        <a href="/">Destinations</a>
        <a href="/Reservation">Reservation</a>
        <a href="/Contact">Contact</a>
        </div>
        <!-- 로그인 썸네일 -->
            <!--로그인-->
            <!-- <div class="email" v-if="isLogin">{{ this.$store.state.userInfo.email }} 님</div> -->
            <div class="loginBtn" @click="loginModal = true">
                <ProfileItem :profile="getProfile"/>
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
            return this.$store.state.isLoad;
        },
    },
    methods: {
        hideParams() {
            history.pushState(null, "", `/`)
        },
        setParamInfo() {
            if (this.$route.query.email != null) {
                this.$store.dispatch("setUserInfo", this.$route.query)
            }
        },
        logout() {
            let access_token = this.$store.state.userInfo.access_token;
            let email = this.$store.state.userInfo.email;

            axios.get('http://58.225.45.251:8200/api/kakao/logout/' + access_token, {
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
    font-family: 'SEBANG_Gothic_Bold';
    white-space: nowrap;
}

@font-face {
    font-family: 'SEBANG_Gothic_Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SEBANG_Gothic_Bold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}


.navigation {
    height: 75px;
    background: linear-gradient(135deg, rgba(16, 99, 79, 0.963) 0%, rgba(146, 199, 179, 0.959) 100%);
}

.navBar {
    display: flex;
}

.navBar>a,
.subLink>a {
    font-family: 'SEBANG_Gothic_Bold';
    color: white;
    font-weight: 100;
    margin-top: 1%;
    margin-left: 3%;
    text-decoration-line: none;
}

.navBar>a:visited,
.subLink>a:visited {
    color: white;
}

.navBar>a:hover,
.subLink>a:hover {
    color: white;
}

.logoLink {
    font-size: 30px;
    padding-left: 1%;
}

.subLink {
    margin-left: 42%;
    margin-top: 2%;
}

.subLink>a {
    font-size: 16px;
    padding: 10px;
}

.nav {
    font-weight: 900;
}

.loginBtn {
    display: flex;
    margin-top: 1.4%;
    margin-left: 6%;
}

.loginText {
    font-family: 'SEBANG_Gothic_Bold';
    font-size: 14px;
    font-weight: 900;
    margin-top: 14%;
    margin-left: 24%;
    cursor: pointer;
    color: white;
}

.logoutBtn {    
    font-family: 'SEBANG_Gothic_Bold';
    color: white;
    font-weight: 900;
    padding: 10px 0;
    margin-top: 1.4%;
    margin-left: 1.2%;
    font-size: 14px;
    cursor: pointer;
}

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
