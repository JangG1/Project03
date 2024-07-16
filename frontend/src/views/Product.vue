<template>
    <lottie-player class="cloudBack1" src="https://assets7.lottiefiles.com/packages/lf20_unJ8q8.json" background="transparent" speed="2" loop autoplay></lottie-player>
<div class="rec" data-aos="fade-down" data-aos-delay="250" data-aos-easing="ease-out" data-aos-duration="2000">
    <div>지금 떠나기 좋은 여행</div>
    <div class="rec2">Recommended travel destinations</div>        
</div>

<!--좌우 슬라이드 버튼-->
<div data-aos="fade-down" data-aos-duration="2000">
    <button type="button" class="prodPrev" @click="prodPrev"></button>
    <button type="button" class="prodNext" @click="prodNext"></button>
</div>
<!--추천 여행지 박스-->
<div class="slided_wrapper" data-aos="flip-down" data-aos-duration="2000">        
    <ul class="slidesd flex">
        <span class="prodBox" v-for="imageUrl in imageUrls" :key="imageUrl" :src="imageUrl.url">
            <li><img class="slidesdImg" :src="imageUrl?.url" ></li>
            <li><span id="prodCity">{{imageUrl?.city}}</span></li>
            <li><span id="prodWay">{{imageUrl?.way}}</span></li>
            <li><span id="prodPrice">{{"KRW " + imageUrl?.price}}</span></li>
            <li><button type="button" class="detailBtn" @click="ing">자세히 보기</button></li>                        
        </span>
        <div class="moreBox">
            <img src="../assets/Logo3.png" class="moreLogo"><br>
            <span class="moreText" @click="ing">추천 여행지 더보기 →</span>
        </div>
    </ul>
</div>
</template>

<script>
import data from "../components/Product.json";
import AOS from 'aos';
import "aos/dist/aos.css";

const imageUrls = data;

export default {
    name: "HelloWorld",
    data() {
        return {
            currentcIdx: 0,
            imageUrls: imageUrls,
        }
    },
    created() {
        AOS.init();
    },
    methods: {
        ing() {
            alert("준비중입니다.")
        },
        prodPrev() {
            let slideCount = imageUrls.length

            if (this.currentIdx > 0) {
                this.prodMoveSlide(this.currentIdx - 1);
            } else {
                this.prodMoveSlide(slideCount - 4);
            }
        },
        prodNext() {
            let slideCount = imageUrls.length

            if (this.currentIdx < slideCount - 4) {
                this.prodMoveSlide(this.currentIdx + 1);
            } else {
                this.prodMoveSlide(0);
            }

        },
        prodMoveSlide(num) {
            let prodSlides = document.querySelector('.slidesd'),
                slide = document.querySelectorAll('.slides li'),
                slideCount = slide.length,
                slideWidth = 300,
                slideMargin = 20

            prodSlides.style.width =
                (slideWidth + slideMargin) * slideCount - slideMargin + 'px';

            prodSlides.style.left = -num * 215 + 'px';

            this.currentIdx = num;
        },
    },
    mounted() {
        this.prodNext();
    },
}
</script>

<style>
.rec {
    color: white;
    text-align: center;
    font-size: 50px;
    padding-top: 10%;
    margin-bottom: 2%;
}

.rec2 {
    font-family: 'SEBANG_Gothic_Bold';
    font-size: 20px;
}

.moreBox {
    text-align: center;
}

.moreLogo {
    width: 80x;
    height: 80px;
    margin-top: 180px;
    margin-left: 30px;
    margin-bottom: 20px;
    background-color: rgba(255, 255, 255, 0);
}

.moreText {
    margin-left: 30px;
    font-size: 20px;
    font-weight: 900;
    color: white;
    cursor: pointer;
}

.flex {
    display: flex;
    padding: 0;
    margin: 0;
}

.flex-jc-c {
    justify-content: center;
}

.slidesdImg {
    width: 97%;
    height: 255px;
    border-radius: 8px;
    border-top-left-radius: 20px;
}

.cloudBack1{
    display: flex;
    position: absolute;            
}

.cloudBack2{
    display: flex;
    position: absolute;        
}

.slided_wrapper {
    position: relative;
    width: 68%;
    height: 430px;
    overflow: hidden;
    margin-left: 200px;
    margin-bottom: 200px;        
}

.slidesd {
    position: absolute;
    transition: left 0.5s ease-out;
}

.prodBox {
    border-radius: 20px;
    margin: 12px;
    width: 190px;
    height: 390px;
    /* background-color: rgba(3, 47, 19, 0.803); */
    /*background-color: rgba(22, 122, 122, 0.507);*/
    background-color: rgba(84, 126, 173, 0.852);
}

#prodCity,
#prodWay,
#prodPrice {
    font-size: 12px;
    font-family: 'SEBANG_Gothic_Bold';
    color: rgb(240, 239, 239);
    margin-left: 7%;
}

.detailBtn {
    border: none;
    border-radius: 4px;
    font-size: 14px;
    color: white;
    margin-left: 7%;
    margin-top: 4%;
    width: 115px;
    background-color: rgba(255, 255, 255, 0.436);
}

.prodPrev {
    position: absolute;
    margin-left: 100px;
    margin-top: 10%;
    width: 50px;
    height: 50px;
    border: none;
    background-color: rgba(255, 255, 255, 0);
    border-top: 10px solid rgb(232, 232, 232);
    border-right: 10px solid rgb(232, 232, 232);
    transform: rotate(225deg);
}

.prodNext {
    position: absolute;
    margin-left: 87%;
    margin-top: 10%;
    width: 50px;
    height: 50px;
    border: none;
    background-color: rgba(255, 255, 255, 0);
    border-top: 10px solid rgb(232, 232, 232);
    border-right: 10px solid rgb(232, 232, 232);
    transform: rotate(45deg);
    z-index: 10;
}

</style>
