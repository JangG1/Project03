<template>
<div class="rec">
    지금 떠나기 좋은 여행
</div>
<!--슬라이드 좌우 버튼-->
<button class="PB" type="button" @click="prev"></button>
<button class="NB" type="button" @click="next"></button>
<div class="image-album">
    <div class="images">
        <span class="prodBox" v-for="imageUrl in imageUrls" :key="imageUrl" :src="imageUrl.url" loading="lazy">
            <img class="image" :src="imageUrl?.url" loading="lazy"/><br>
            <span id="prodCity">{{imageUrl?.city}}</span><br>
            <span id="prodWay">{{imageUrl?.way}}</span><br>
            <span id="prodPrice">{{"KRW " + AddComma(imageUrl?.price)}}</span><br>
            <button type="button" class="detailBtn" @click="ing">자세히 보기</button>
        </span>

    </div>

</div>

<div>

</div>
</template>

<script>
import data from "../components/Product.json";

const imageUrls = data;

export default {
    name: "imageSlider",
    data() {
        return {
            curPos: 0,
            postion: 0,
            IMAGE_WIDTH: 0,
            images: null,
            userText: null,
            imageUrls: imageUrls,
        };
    },
    methods: {
        ing() {
            alert("준비중입니다.")
        },
        prev() {
            if (this.curPos > 0) {
                this.postion += this.IMAGE_WIDTH;
                this.images.style.transform = `translateX(${this.postion}px)`;
                this.curPos = this.curPos - 1;
            }
        },
        next() {
            if (this.curPos < this.imageUrls.length - 4) {
                this.postion -= this.IMAGE_WIDTH;
                this.images.style.transform = `translateX(${this.postion}px)`;
                this.curPos = this.curPos + 1;
            }
        },
        //가격부분 콤마 추가
        AddComma(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return num.toString().replace(regexp, ",");
        },
    },
    computed: {
        getImageWidth() {
            const imgWidth = document.querySelector(".images").offsetWidth - 973;
            return imgWidth;
        },

    },
    mounted() {
        this.IMAGE_WIDTH = this.getImageWidth;
        this.images = document.querySelector(".images");
    },
};
</script>

<style>
.rec {
    color: white;
    text-align: center;
    font-size: 60px;
    margin-top: 10%;
    margin-bottom: 10%;
}

.prodBox {
    border-radius: 20px;
    margin: 10px;
    height: 580px;
    padding-right: 5px;
    background-color: rgba(255, 255, 255, 0.379);
}

#prodCity,
#prodWay,
#prodPrice {
    font-size: 21px;
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
    margin-top: 20px;
    width: 115px;
    background-color: rgba(255, 255, 255, 0.436);
}

.image-album {
    width: 650px;
    max-width: 700px;
    overflow: hidden;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 20%;    
}

.image {
    width: 300px;
    height: 400px;
    margin-bottom: 10px;
    border-radius: 10px;
}

.images {
    display: flex;
    transition: transform 0.5s;
}

.PB {
    width: 50px;
    height: 50px;
    border: none;
    background-color: rgba(255, 255, 255, 0);
    border-top: 10px solid rgb(232, 232, 232);
    border-right: 10px solid rgb(232, 232, 232);
    transform: rotate(225deg);
    margin-top: 14%;
    float: left;
    margin-left: 11%;
}

.NB {
    width: 50px;
    height: 50px;
    border: none;
    background-color: rgba(255, 255, 255, 0);
    border-top: 10px solid rgb(232, 232, 232);
    border-right: 10px solid rgb(232, 232, 232);
    transform: rotate(45deg);
    margin-top: 14%;
    float: right;
    margin-right: 11%;
}

/* 화면 축소 전 후 */

@media (min-width: 1500px) {

.image-album {
    width: 970px;
    max-width: 1000px;
    overflow: hidden;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 20%;
}

}

/* 화면 축소 전 후 */

@media (min-width: 1900px) {

    .image-album {
        width: 1300px;
        max-width: 1500px;
        overflow: hidden;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 20%;
    }

}
</style>
