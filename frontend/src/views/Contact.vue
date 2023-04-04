<template>
<div class="ctTitle" data-aos="fade-up" data-aos-delay="300" data-aos-easing="ease-ou" data-aos-duration="2000">
    <h1>Contact.</h1>
</div>

<!--<div
 data-aos="fade-up"
 data-aos-offset="200"
 data-aos-delay="50"
 data-aos-duration="1000"
 data-aos-easing="ease-in-out"
 data-aos-mirror="true"
 data-aos-once="false"
 data-aos-anchor-placement="top-center"
>   </div>
-->
</template>

<script>
import AOS from 'aos';
import "aos/dist/aos.css";

export default {
    name: "HelloWorld",
    data() {
        return {
            show: true
        }
    },
    created() {
        AOS.init();
    },
    methods: {}
}
</script>

<style>
.ctTitle {
    text-align: center;
    margin-top: 5%;
}
</style>
