<template>
<div>
    <!-- 네비게이션 바-->
    <div class="navBar">
        <!-- Fastrip 로고 -->
        <router-link v-bind:to="'/'" class="logoRouter"><img src="./assets/Logo2.png" class="logo"></router-link>

        <!-- 예약 조회 버튼 -->
        <ul class="nav">
            <router-link to="/Reservation" class="nav-link px-2 link-secondary">예약</router-link>
        </ul>

        <!-- Test 페이지 버튼 -->
        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
            <router-link to="/Test" class="nav-link px-2 link-secondary">Test</router-link>
        </ul>

        <!-- 로그인 썸네일 -->
        <div class="profileBox">
            <!--로그인-->
            <div class="email" v-if="isLogin">{{ this.$store.state.userInfo.email }} 님</div>
            <div class="loginBtn" @click="loginModal = true">
                <ProfileItem :profile="getProfile" :email="getEmail" />
            </div>
            <!--로그아웃-->
            <div class="logoutBtn" @click="logout" v-if="isLogin">
                로그아웃
            </div>
        </div>

    </div>

    <!-- 로그인 모달 -->
    <div v-if="!isLogin">
        <LoginModal class="loginModal" @closeModal="loginModal = false" :loginModal="loginModal" />
    </div>

    <hr>

    <!-- <Home v-if="$route.name !== 'Arrival'"></Home>     -->

    <!-- 로딩화면 -->
    <div class="layerPopup" v-show="isLoading">
        <div class="spinner"></div>
    </div>

    <router-view></router-view>
</div>
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

.navBar {
    display: flex;
}

.logo {
    width: 110px;
    height: 70px;
    margin-top: 26px;
    margin-left: 1%;
}

.email {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    color: rgb(95, 95, 95);
    padding: 3%;
    margin-right: 10px;
}

.profileBox {
    display: flex;
    margin-top: 46px;
}

.nav-link {
    margin-top: 40px;
    margin-left: 15px;
}

.loginBtn {
    margin-right: 10px;
    width: 30px;
    height: 30px;
}

.logoutBtn {
    color: #999;
    border: 1px solid black;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px 20px 10px 20px;
    border: 3px solid rgb(193, 188, 188);
    margin-left: 5px;
    margin-right: 20px;
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
