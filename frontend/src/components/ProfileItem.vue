<template>
  <div>
    <img class="profile" :src="profile" />
    <!-- <lottie-player class="profile" src="https://assets1.lottiefiles.com/packages/lf20_voi0gxts.json"  background="transparent"  speed="1" loop autoplay ></lottie-player> -->
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
  margin-top: 55%;
  border-radius: 6px;
  border: none;
  cursor: pointer;
}
</style>
