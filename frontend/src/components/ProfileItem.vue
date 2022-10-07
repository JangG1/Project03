<template>
  <div id="profile">
    <img :src="profile" alt="profile-logo" />
  </div>
</template>
<script>
export default {
  name: "ProfileItem",
  props: {
    profile: {
      type: String,
      default: require("@/assets/weblogin1.png"),
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
  #profile > img {
    display: inline-block;
    width: 48px;
    height: 48px;
    margin-right: 1rem;
    border-radius: 10px;
  }
</style>
