<template>
<div>
    <img class="profile" :src="profile" alt="profile-logo" />
</div>
</template>

<script>
export default {
    name: "ProfileItem",
    props: {
        profile: {
            type: String,
            default: require("@/assets/Logo3.png"),
        },
        email: {
            type: String,
            default: "로그인 요청드립니다.",
        },
    },
    data() {
        return {};
    },
    methods: {},
    computed: {},
};
</script>

<style scoped>
.profile {
    width: 40px;
    height: 40px;
    border-radius: 10px;
    border: none;
    cursor: pointer;
}
</style>
