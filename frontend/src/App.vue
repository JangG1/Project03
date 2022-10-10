<template>
<div>
    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
    <meta name="theme-color" content="#7952b3">

    <!-- 네비게이션 바-->
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <!-- 로고 -->

        <router-link class="pageLink" v-bind:to="'/'">
            <div class="logo">
                <router-link v-bind:to="'/'"> <img src="./assets/Logo.png" style="width:110px; height:80px; "></router-link>
            </div>
        </router-link>
        <!-- 예약버튼 -->
        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

            <router-link to="/Reservation" class="nav-link px-2 link-secondary"></router-link>

        </ul>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

            <router-link to="/Calendar" class="nav-link px-2 link-secondary"></router-link>

        </ul>

        <div class="profileBox mt-5">
            <!--로그인-->
            <div @click="loginModal = true">
                <ProfileItem :profile="getProfile" :email="getEmail" />
            </div>
            <!--로그아웃-->
            <!-- <div class="logoutBtn" @click="logout" v-show="!isLogin">로그아웃</div> -->
            <div class="logoutBtnEmpty" @click="logout" v-show="isLogin">로그아웃</div>
        </div>

    </div>
  

    <div v-show="!isLogin">
        <Modal @closeModal="loginModal = false" :loginModal="loginModal" />
    </div>
    <div class="layerPopup" v-show="isLoading">
        <div class="spinner"></div>
    </div>

    <hr>

    <router-view></router-view>
</div>
</template>

<script>
import ProfileItem from "@/components/ProfileItem.vue";
import Modal from "@/components/Modal.vue";

export default {
    name: "App",
    data() {
        return {
            loginModal: false,
        };
    },
    mounted() {
        console.log(this.isLogin);
    },
    components: {
        ProfileItem,
        Modal,
    },
    computed: {
        getProfile() {
            if (this.$store.state.userInfo == null)
                return require("@/assets/weblogin1.png");
            return this.$store.state.userInfo.profile;
        },
        getEmail() {
            if (this.$store.state.userInfo == null) return null;
            return this.$store.state.userInfo.email;
        },
        isLogin() {
            return this.$store.state.isLogin;
        },
        isLoading() {
            console.log("loading " + this.$store.state.isLoad);
            return this.$store.state.isLoad;
        },
    },
    methods: {
        logout() {
            this.$store.commit("logout");
            this.$router.go("/");
        }
    },
};
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    max-width: 90%;
    margin: 0 auto;
}

.logo {
    height: 60px;
}

nav {
    padding: 30px;
}

nav a {
    font-weight: bold;
    color: #2c3e50;
}

nav a.router-link-exact-active {
    color: #42b983;
}

.nav-link {
    margin-top: 45px;
    margin-left: 15px;
}

.profileBox {
    float: right;
    cursor: pointer;
}

.profileBox>div {
    float: left;
}

.logoutBtn {
    color: white;
    border: 1px solid white;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px 20px 10px 20px;
}

.logoutBtnEmpty {
    color: black;
    border: 1px solid black;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px 20px 10px 20px;
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
    border-top: 8px solid black;
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
</style>
