<template>
<div class="slide_wrapper">
    <ul class="slides">
        <div><img src="../assets/rec1.jpg">
            <br>
            <h5>서울/인천- 두바이</h5>
            <h6>일반석 왕복</h6>
            <h6>KRW 1,158,500 ~</h6>
            <br>
            <div class="btn-group">
                <button type="button" class="btn btn-sm btn-outline-secondary">자세히 보기</button>
            </div>
        </div>
        <div><img src="../assets/rec1.jpg"></div>
        <div><img src="../assets/rec1.jpg"></div>
        <div><img src="../assets/rec1.jpg"></div>
        <div><img src="../assets/rec1.jpg"></div>

    </ul>
    <p class="controls">
        <span class="prev">prev</span>
        <span class="next">next</span>

    </p>

    
</div>
</template>

<script>


export default {
    name: "HelloWorld",
    computed: {},
    components: {
        
    },
    data() {
        return {
            
        };
    },
    methods: {
        moveSlide(num){
            this.slides.style.left = -num * 330 + 'px';
        }
    }
    
}
</script>

<style>
h5 {
    font-size: 20px;
    margin-top: 25px;
}

img {
    border-radius: 10px;
    box-shadow: 4px 4px 10px 0px rgb(39, 39, 39), 8px 8px 16px -10px rgba(0, 0, 0, .15);
    margin-right: 15px;
}

.slide_wrapper {
    position: relative;
    width: 1200px;
    margin: 0 auto;
    height: 700px;
    overflow: hidden;

}

.slides {
    position: absolute;
    left: 0;
    top: 0;
    width: 2610px;
}

.slides div:not(:last-child) {
    float: left;
    margin-right: 30px;
    border: 2.5px solid #999;
    height: 560px;
    width: 265px;
    border-radius: 10px;
    color: rgb(122, 122, 122);
    background-color: rgb(245, 245, 245);
}

.controls {
    text-align: center;
    margin-top: 620px;
}

.controls span {    
    background: #333;
    color: #fff;
    padding: 10px 20px;
    margin: 0 10px;        
}

.controls span.prev{
    right:calc(100% + 50px);
    
}

.controls span.next{
    left:calc(100% + 50px);
    
}
</style>
