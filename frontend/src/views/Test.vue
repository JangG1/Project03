<template>
    <div></div>
<!--
<div id="sec1" data-aos="fade-up" data-aos-delay="300"  data-aos-easing="ease-ou" data-aos-duration="2000">

<h1>웹 개발자 장지원입니다.</h1>
</div>

<div
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
.slide-fade-enter-active {
    transition: all .3s ease;
}

.slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}

.slide-fade-enter,
.slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
}
</style>
