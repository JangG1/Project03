<template>
<div class="slide_wrapper ">
    <ul class="slides flex">
        <li><img src="http://placehold.it/300x300" alt=""></li>
        <li><img src="http://placehold.it/300x300" alt=""></li>
        <li><img src="http://placehold.it/300x300" alt=""></li>
        <li><img src="http://placehold.it/300x300" alt=""></li>
        <li><img src="http://placehold.it/300x300" alt=""></li>
        <li><img src="http://placehold.it/300x300" alt=""></li>
        <li><img src="http://placehold.it/300x300" alt=""></li>
        <li><img src="http://placehold.it/300x300" alt=""></li>
        <li><img src="http://placehold.it/300x300" alt=""></li>
        <li><img src="http://placehold.it/300x300" alt=""></li>
    </ul>
</div>

<div class="controls">
        <button type="button" class="prev" @click="prev"><i class="fas fa-chevron-left"></i></button>
        <button type="button" class="next" @click="next"><i class="fas fa-chevron-right"></i></button>
    </div>

<div class="ha"></div>
</template>

<script>
export default {
    name: "HelloWorld",
    data() {
        return {
            currentcIdx: 0
        }
    },
    methods: {
        prev() {
            let slide = document.querySelectorAll('.slides li'),
                slideCount = slide.length

            if (this.currentIdx > 0) {
                this.moveSlide(this.currentIdx - 1);
            } else {
                this.moveSlide(slideCount - 3);
            }
        },
        next() {
            let slide = document.querySelectorAll('.slides li'),
                slideCount = slide.length

            if (this.currentIdx < slideCount - 3) {
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
                slideMargin = 30

            slides.style.width =
                (slideWidth + slideMargin) * slideCount - slideMargin + 'px';

            slides.style.left = -num * 330 + 'px';

            this.currentIdx = num;
        },
    },
    mounted() {
        this.slide();
    },
}
</script>

<style>


li {
    list-style: none;
}

.flex {
    display: flex;
}

.flex-jc-c {
    justify-content: center;
}

.con {
    max-width: 1200px;
    margin: 0 auto;
    border: solid red;
}

.article-board-box {
    position: relative;
    border: solid red;
}

.slide_wrapper {
    border: solid red;
    position: relative;
    width: 960px;
    margin: 0 auto;
    height: 300px;
    overflow: hidden
}

.slides {
    position: absolute;
    left: 0;
    top: 0;
    transition: left 0.5s ease-out;
}

.slides li:not(:last-child) {
    justify-content: flex-start;
    margin-right: 30px;
}

.controls {
    text-align: center;
}

.controls span {
    z-index: 2;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
}

.prev {
    right: calc(100% - 40px);
    width: 30px;
    height: 30px;    
}

.controls>.next {
    left: calc(100% - 40px);
    width: 30px;
    height: 30px;
}

.ha {
    width: 50px;
}
</style>
