<template>
    <div class="rec">
        지금 떠나기 좋은 여행
    </div>
<!--슬라이드 좌우 버튼-->
<button class="PB" type="button" @click="prev"></button>
<button class="NB" type="button" @click="next"></button>
<div class="image-album">    
    <div class="images">
        <span class="prodBox" v-for="imageUrl in imageUrls" :key="imageUrl.index" :src="imageUrl.url">
            <img class="image" :src="imageUrl.url" /><br>
            <span id="prodCity">{{imageUrl.city}}</span><br>
            <span id="prodWay">{{imageUrl.way}}</span><br>
            <span id="prodPrice">{{"KRW " + AddComma(imageUrl.price)}}</span><br>
            <button type="button" class="detailBtn">자세히 보기</button>123
        </span>

    </div>

</div>

<div>

</div>
</template>

<script>
export default {
    name: "imageSlider",
    data() {
        return {
            curPos: 0,
            postion: 0,
            IMAGE_WIDTH: 0,
            images: null,
            userText: null,
            imageUrls: [{
                    url: "https://github.com/JangG1/Project03/blob/master/frontend/src/assets/rec1.jpg?raw=true",
                    city: '서울/인천 - 두바이',
                    way: '일반석/왕복',
                    price: 1168300
                },
                {
                    url: "https://github.com/JangG1/Project03/blob/master/frontend/src/assets/rec2.jpg?raw=true",
                    city: '서울/인천 - 후쿠오카',
                    way: '일반석/왕복',
                    price: 508800
                },
                {
                    url: "https://github.com/JangG1/Project03/blob/master/frontend/src/assets/rec3.jpg?raw=true",
                    city: '서울/인천 - 싱가포르',
                    way: '일반석/왕복',
                    price: 1471600
                },
                {
                    url: "https://github.com/JangG1/Project03/blob/master/frontend/src/assets/rec4.jpg?raw=true",
                    city: '서울/인천 - 바르셀로나',
                    way: '일반석/왕복',
                    price: 1626600
                },
                {
                    url: "https://github.com/JangG1/Project03/blob/master/frontend/src/assets/rec5.jpg?raw=true",
                    city: '서울/인천 - 치앙마이',
                    way: '일반석/왕복',
                    price: 913900
                },
                {
                    url: "https://github.com/JangG1/Project03/blob/master/frontend/src/assets/rec6.jpg?raw=true",
                    city: '서울/인천 - 부다페스트',
                    way: '일반석/왕복',
                    price: 1364500
                },
                {
                    url: "https://github.com/JangG1/Project03/blob/master/frontend/src/assets/rec7.jpg?raw=true",
                    city: '서울/인천 - 타이베이',
                    way: '일반석/왕복',
                    price: 548300
                },
                {
                    url: "https://github.com/JangG1/Project03/blob/master/frontend/src/assets/rec8.jpg?raw=true",
                    city: '서울/인천 - 텔아비브',
                    way: '일반석/왕복',
                    price: 1435300
                },

            ],
        };
    },
    methods: {
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
.rec{
    text-align: center;
    font-size: 60px;
    margin-top: 15%;  
    margin-bottom: 7%;    
}

.prodBox{
    border-radius: 20px;
    margin: 10px;
    height: 580px;
    padding-right: 5px;    
    background-color: rgba(255, 255, 255, 0.379);
}

#prodCity,
#prodWay,
#prodPrice {
    font-size: 20px;
    font-family: 'NanumBarunGothicBold';
    color: rgb(116, 116, 116);
    margin-left: 7%;

}

.detailBtn {
    border: 1px solid rgb(193, 188, 188);
    border-radius: 4px;
    font-size: 18px;
    color: rgba(139, 139, 139, 0.993);
    margin-left: 7%;
    margin-top: 20px;
    width: 115px;
    background-color: rgba(244, 238, 238, 0.646);
}

.image-album {
    width: 1300px;
    max-width: 1500px;
    overflow: hidden;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 15%;
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
    border-top: 10px solid rgb(197, 197, 197);
    border-right: 10px solid rgb(197, 197, 197);
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
    border-top: 10px solid rgb(197, 197, 197);
    border-right: 10px solid rgb(197, 197, 197);
    transform: rotate(45deg);
    margin-top: 14%;
    float: right;
    margin-right: 11%;
}
</style>
