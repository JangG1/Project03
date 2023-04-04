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
    width: 50px;
    height: 50px;
    border-radius: 10px;
    border: none;
    cursor: pointer;
}
</style>
