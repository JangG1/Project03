<template>
<div>
    <!-- 네비게이션 바-->
    <div class="navBar">
        <!-- 로고 -->
        <router-link v-bind:to="'/'" class="logoRouter"><img src="./assets/Logo2.png" class="logo"></router-link>
        <!-- 예약버튼 -->
        <ul class="nav">
            <router-link to="/Reservation" class="nav-link px-2 link-secondary">예약</router-link>
        </ul>

        <ul class="nav">
            <router-link to="/Calendar" class="nav-link px-2 link-secondary">달력</router-link>
        </ul>

        <ul class="nav">
            <router-link to="/Cookie" class="nav-link px-2 link-secondary">쿠키</router-link>
        </ul>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
            <router-link to="/Test" class="nav-link px-2 link-secondary">Test</router-link>
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
        },
    },
};
</script>

<style>
#app {
    font-family: "NanumBarunGothicBold";
    white-space:nowrap;
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

.profileBox {
    display: flex;
    margin-top: 46px;
    margin-right: 1%;
}

.nav-link {
    margin-top: 40px;
    margin-left: 15px;
}

.logoutBtn {
    color: #999;
    border: 1px solid black;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px 20px 10px 20px;
    border: 3px solid rgb(193, 188, 188);

}
</style>
