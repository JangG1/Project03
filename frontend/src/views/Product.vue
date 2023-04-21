<template>
<div class="rec" data-aos="fade-down" data-aos-delay="250" data-aos-easing="ease-out" data-aos-duration="2000">
    <div>지금 떠나기 좋은 여행</div>
    <div class="rec2">Recommended travel destinations</div>
</div>

<!--좌우 슬라이드 버튼-->
<div data-aos="flip-down" data-aos-duration="2000">
    <button type="button" class="prev" @click="prev"><i class="fas fa-chevron-left"></i></button>
    <button type="button" class="next" @click="next"><i class="fas fa-chevron-right"></i></button>
</div>
<!--추천 여행지 박스-->
<div class="slide_wrapper" data-aos="flip-down" data-aos-duration="2000">
    <ul class="slides flex">
        <span class="prodBox" v-for="imageUrl in imageUrls" :key="imageUrl" :src="imageUrl.url" loading="lazy">
            <li><img class="slidesImg" :src="imageUrl?.url"></li>
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
        prev() {
            let slideCount = imageUrls.length

            if (this.currentIdx > 0) {
                this.moveSlide(this.currentIdx - 1);
            } else {
                this.moveSlide(slideCount - 4);
            }
        },
        next() {

            let slideCount = imageUrls.length

            if (this.currentIdx < slideCount - 4) {
                this.moveSlide(this.currentIdx + 1);
            } else {
                this.moveSlide(0);
            }

        },
        moveSlide(num) {
            let slides = document.querySelector('.slides'),
                slide = document.querySelectorAll('.slides li'),
                slideCount = slide.length,
                slideWidth = 300,
                slideMargin = 20

            slides.style.width =
                (slideWidth + slideMargin) * slideCount - slideMargin + 'px';

            slides.style.left = -num * 215 + 'px';

            this.currentIdx = num;
        },
    },
    mounted() {
        this.next();
    },
}
</script>

<style>
.rec {    
    color: white;
    text-align: center;
    font-size: 60px;    
    padding-top: 10%;
    margin-bottom: 10%;
}

.rec2{
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
    font-size: 24px;
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

li {
    list-style: none;
    padding-bottom: 1%;
}

.flex {
    display: flex;
    padding: 0;
    margin: 0;
}

.flex-jc-c {
    justify-content: center;
}

.slidesImg {
    width: 98%;
    height: 250px;
    border-radius: 8px;
    border-top-left-radius: 20px;
}

.slide_wrapper {
    position: relative;
    width: 68%;
    height: 700px;
    overflow: hidden;

}

.slides {
    position: absolute;
    transition: left 0.5s ease-out;
}

.prodBox {
    border-radius: 20px;
    margin: 12px;
    width: 190px;
    height: 380px;
    background-color: rgba(255, 255, 255, 0.379);
}

#prodCity,
#prodWay,
#prodPrice {
    font-size: 12px;
    font-family: 'NanumBarunGothicBold';
    color: rgb(240, 239, 239);
    margin-left: 7%;
}

.detailBtn {
    border: 1px solid rgba(193, 188, 188, 0.58);
    border-radius: 4px;
    font-size: 14px;
    color: white;
    margin-left: 7%;
    margin-top: 7%;
    width: 115px;
    background-color: rgba(255, 255, 255, 0.436);
}

.prev {
    float: left;
    margin-left: 10%;
    margin-top: 15%;
    width: 50px;
    height: 50px;
    border: none;
    background-color: rgba(255, 255, 255, 0);
    border-top: 10px solid rgb(232, 232, 232);
    border-right: 10px solid rgb(232, 232, 232);
    transform: rotate(225deg);
}

.next {
    float: right;
    margin-right: 14%;
    margin-top: 15%;
    width: 50px;
    height: 50px;
    border: none;
    background-color: rgba(255, 255, 255, 0);
    border-top: 10px solid rgb(232, 232, 232);
    border-right: 10px solid rgb(232, 232, 232);
    transform: rotate(45deg);
}

@media (min-width: 1900px) {
    .rec {
        color: white;
        text-align: center;
        font-size: 60px;
        padding-top: 10%;
        margin-bottom: 10%;
    }

    .moreBox {
        text-align: center;
    }

    .moreLogo {
        width: 150px;
        height: 150px;
        margin-top: 250px;
        margin-left: 70px;
        margin-bottom: 20px;
    }

    .moreText {
        margin-left: 70px;
        font-size: 20px;
        font-weight: 900;
        color: white;
        cursor: pointer;
    }

    .slidesImg {
        width: 98%;
        height: 300px;
        border-radius: 8px;
        border-top-left-radius: 20px;
    }

    .slide_wrapper {
        position: relative;
        width: 70%;
        height: 700px;
        overflow: hidden;
        margin-top: 10%;
        margin-bottom: 15%;
    }

    .slides {
        position: absolute;
        transition: left 0.5s ease-out;
    }

    .prodBox {
        border-radius: 20px;
        margin: 18px;
        width: 298px;
        height: 640px;
        background-color: rgba(255, 255, 255, 0.461);
    }

    #prodCity,
    #prodWay,
    #prodPrice {
        font-size: 20px;
        font-family: 'NanumBarunGothicBold';
        color: rgb(240, 239, 239);
        margin-left: 7%;
    }

    .detailBtn {
        border: 1px solid rgba(193, 188, 188, 0.58);
        border-radius: 4px;
        font-size: 18px;
        color: white;
        margin-left: 7%;
        margin-top: 10px;
        width: 115px;
        background-color: rgba(255, 255, 255, 0.436);
    }

    .prev {
        float: left;
        margin-left: 12%;
        margin-top: 20%;
        width: 50px;
        height: 50px;
        border: none;
        background-color: rgba(255, 255, 255, 0);
        border-top: 10px solid rgb(232, 232, 232);
        border-right: 10px solid rgb(232, 232, 232);
        transform: rotate(225deg);
    }

    .next {
        float: right;
        margin-right: 12%;
        margin-top: 20%;
        width: 50px;
        height: 50px;
        border: none;
        background-color: rgba(255, 255, 255, 0);
        border-top: 10px solid rgb(232, 232, 232);
        border-right: 10px solid rgb(232, 232, 232);
        transform: rotate(45deg);
    }

    .flex {
        padding: 0;
        margin: 0;
    }
}
</style>
