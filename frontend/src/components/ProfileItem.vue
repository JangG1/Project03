<template>
<div>
    <img class="profile" :src="profile"/>
</div>
</template>

<script>
export default {
    name: "ProfileItem",
    props: {
        profile: {
            type: String,
            default: require("@/assets/Logo3.png"),
        }
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
    width: 35px;
    height: 35px;
    margin-top: 8px;
    border-radius: 10px;
    border: none;
    cursor: pointer;
}
</style>
