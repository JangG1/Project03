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
    <div class="d-flex">
        <!-- 로고 -->
        <router-link v-bind:to="'/'"> <img src="./assets/Logo.png" class="logo"></router-link>
        <!-- 예약버튼 -->
        <ul class="nav">
            <router-link to="/Reservation" class="nav-link px-2 link-secondary">예약</router-link>
        </ul>

        <ul class="nav">
            <router-link to="/Calendar" class="nav-link px-2 link-secondary">달력</router-link>
        </ul>
        <ul class="nav">
            <router-link to="/Test" class="nav-link px-2 link-secondary">Test</router-link>
        </ul>
        
        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
            <router-link to="/Modal" class="nav-link px-2 link-secondary">Test2</router-link>
        </ul>

        <div class="profileBox">
            <!--로그인-->
            <div class="loginBtn" @click="loginModal = true">
                <ProfileItem :profile="getProfile" :email="getEmail" />
            </div>
            <!--로그아웃-->            
            <div class="logoutBtn" @click="logout" v-show="isLogin">
                로그아웃
            </div>
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
    text-align: center;
    margin-left: 2%;
    margin-right: 2%;    
}

.logo {
    width: 110px;
    height: 70px;
    margin-top: 26px;
}

.profileBox{
    display: flex;
    margin-top: 46px;
}

.nav-link {
    margin-top: 40px;
    margin-left: 15px;
}

.logoutBtn{    
    color: #999;
    border: 1px solid black;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px 20px 10px 20px;
    border: 3px solid rgb(193, 188, 188);
}
</style>
