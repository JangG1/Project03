<template>
<div class="backImg">
    <span class="ctTitle" data-aos="fade-up" data-aos-delay="300" data-aos-easing="ease-ou" data-aos-duration="2000">Contact.</span>

    <div class="contBox" data-aos="fade-left" data-aos-delay="300" data-aos-easing="ease-ou" data-aos-duration="2000">
        <!--boxLeft-->
        <div class="boxLeft">
            <div class="boxTitleLeft">
                <img class="boxTitleImg1" src="@/assets/ticketImg1.png">
                <div>
                    <span class="boxTitleLeft1">Fastrip</span><br>
                    <span class="boxTitleLeft2">Airline Ticketing</span>
                </div>
                <img class="boxTitleImg2" src="@/assets/ticketImg2.jpeg">
            </div>

            <div class="boxLeftBot">
                <div class="lBox1">
                    <span class="lBox1-1">NAME</span><br><br>
                    <span class="lBox2-1">FROM</span><br><br>
                    <span class="lBox3-1">EMAIL</span><br><br>
                    <div class="lBoxBot1">
                        <span>EMAIL</span><br><img src="@/assets/naver.png">
                    </div>
                </div>
                <div class="lBox2">
                    <span class="lBox1-2">Jang Ji Won</span><br><br>
                    <span class="lBox2-2">Seoul</span><br><br>
                    <span class="lBox3-2">jangjiwon0417@naver.com</span><br><br>
                    <div class="lBoxBot2">
                        <span>Git</span><br><img src="@/assets/github-mark.png" @click="moveGit">
                    </div>
                </div>
                <div class="lBox3">
                    <span class="lBox4-1">GIT</span><span class="lBox4-2">JangG1</span><br><br>
                    <span class="lBox5-1">SNS</span><span class="lBox5-2">d_o_nii</span><br><br>
                    <div class="lBoxBot3">
                        <span>SNS</span><br><img src="@/assets/Instagram.svg.png" @click="moveSns">
                    </div>
                </div>
                <div class="lBox4">
                    <img src="@/assets/barcode.jpg">
                </div>
            </div>
        </div>

        <!--boxRight-->
        <div class="boxRight">
            <div class="boxTitleRight">
                <span class="boxTitleRight1">Fastrip</span><br>
                <span class="boxTitleRight2">Airline Ticketing</span>
            </div>

            <div class="boxRightBot">
                <div class="rBoxLeft">
                    <span class="rBox1-1">NAME</span><br>
                    <span class="rBox2-1">FROM</span><br>
                    <span class="rBox3-1">EMAIL</span><br>
                    <span class="rBox4-1">GIT</span><br>
                    <span class="rBox5-1">SNS</span><br>
                </div>
                <div class="rBoxRight">
                    <span class="rBox1-2">Jang Ji Won</span><br>
                    <span class="rBox2-2">Seoul</span><br>
                    <span class="rBox3-2">jangjiwon0417@naver.com</span><br>
                    <span class="rBox4-2">JangG1</span><br>
                    <span class="rBox5-2">d_o_nii</span><br>
                </div>
            </div>

            <div class="rBoxBot">
                <div class="rBoxBot1">
                    <span>EMAIL</span><br><img src="@/assets/naver.png">
                </div>
                <div class="rBoxBot2">
                    <span>Git</span><br><img src="@/assets/github-mark.png" @click="moveGit">
                </div>
                <div class="rBoxBot3">
                    <span>SNS</span><br><img src="@/assets/Instagram.svg.png" @click="moveSns">
                </div>
            </div>
        </div>
    </div>

</div>
</template>

<script>
import AOS from 'aos';
import "aos/dist/aos.css";

export default {
    name: "HelloWorld",
    data() {
        return {
            show: true,
            text: "",
        }
    },
    created() {
        AOS.init();
    },
    methods: {
        moveGit() {
            location.href = "https://github.com/JangG1";
        },
        moveSns() {
            location.href = "https://www.instagram.com/d_o_nii/";
        },
    }
}
</script>

<style>
.backImg {
    background: url("../assets/bannerImage/contact.jpg") fixed;
    height: 100%;
    padding-top: 50px;
}

.ctTitle {
    margin-left: 680px;
    margin-top: 50px;
    color: white;
    font-weight: 900;
    font-size: 160px;
}

.contBox {
    display: flex;
}

.boxRight {
    background-color: rgba(255, 255, 255, 0.906);
    border-top-right-radius: 60px;
    border-bottom-right-radius: 60px;
    width: 500px;
    height: 530px;
    margin-top: 20px;
    margin-bottom: 300px;
}

.boxLeft {
    background-color: rgba(255, 255, 255, 0.906);
    border-top-left-radius: 60px;
    border-bottom-left-radius: 60px;
    width: 1100px;
    height: 530px;
    margin-left: 200px;
    margin-top: 20px;
}

.boxLeftBot {
    height: 340px;
    border-right: 10px dashed rgb(134, 134, 134);
    display: flex;
    margin-top: 10px;
}

.boxTitleLeft:not(.boxTitleImg1, .boxTitleImg2) {
    font-weight: 900;
    border-top-left-radius: 60px;
    color: white;
    background-color: rgba(0, 79, 151);
    height: 180px;
    padding-top: 35px;
    border-right: 10px dashed rgb(134, 134, 134);
    background: linear-gradient(to left);
    display: flex;
}

.boxTitleRight {
    font-weight: 900;
    border-top-right-radius: 60px;
    color: white;
    background-color: rgba(0, 79, 151);
    height: 180px;
    padding-top: 30px;
}

.boxRightBot {
    margin-top: 30px;
    display: flex;
}

.boxTitleLeft1 {
    font-size: 60px;
    margin-left: 160px;
}

.boxTitleLeft2 {
    font-size: 20px;
    margin-left: 160px;
}

.boxTitleRight1 {
    font-size: 60px;
    margin-left: 40px;
}

.boxTitleRight2 {
    font-size: 20px;
    margin-left: 55px;
}

.boxTitleImg1 {
    margin-left: 120px;
    width: 300px;
    height: 120px;
}

.boxTitleImg2 {
    margin-left: 130px;
    margin-bottom: 40px;
    width: 120px;
    height: 120px;
}

.rBoxRight {
    margin-left: 30px;
    text-align: left;
    font-size: 24px;
    font-weight: 900;
    color: rgb(65, 65, 65);
}

.lBoxBot1>img,
.lBoxBot2>img,
.lBoxBot3>img,
.rBoxBot1>img,
.rBoxBot2>img,
.rBoxBot3>img {
    width: 30px;
}

.rBoxBot {
    display: flex;
    font-weight: 900;
    font-size: 24px;
    color: #999;
    margin-left: 30px;
    margin-top: 20px;
    text-align: center;
}

.rBoxBot2,
.rBoxBot3 {
    margin-left: 80px;
}

.rBoxBot2>img,
.rBoxBot3>img {
    cursor: pointer;
}

.rBoxLeft {
    margin-left: 20px;
    font-weight: 900;
    font-size: 24px;
    color: rgb(91, 91, 91);
}

.rBoxLeft {
    color: #999;
}

.lBox1 {
    color: #999;
    font-weight: 900;
    font-size: 24px;
    margin-top: 20px;
    margin-left: 50px;
}

.lBox2 {
    color: rgb(65, 65, 65);
    font-weight: 900;
    font-size: 24px;
    margin-top: 20px;
    margin-left: 50px;
}

.lBox3 {
    color: rgb(65, 65, 65);
    font-weight: 900;
    font-size: 24px;
    margin-top: 95px;
    margin-left: 50px;
}

.lBoxBot1,
.lBoxBot2,
.lBoxBot3 {
    margin-left: 30px;
    color: #999;
    text-align: center;
}

.lBoxBot2,
.lBoxBot3{
    cursor: pointer;
}


.lBox4>img {
    width: 60px;
    height: 240px;
    margin-top: 40px;
    margin-left: 240px;    
}

.lBox4-1,
.lBox5-1 {
    color: #999;
    margin-right: 80px;    
}
</style>
