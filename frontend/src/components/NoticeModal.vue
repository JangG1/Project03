<template>
<div class="">
    <h4 class="noticeTitle">Fastrip 공지사항</h4>
    <hr>
    <div class="noticeBody1">
        <span class="noticeBody1-1">Fastrip에 오신것을 환영합니다.</span><br>
        <span class="noticeBody1-2">Fastrip을 통해 가장 빠르게 예매 해보세요!</span><br>
    </div>
    <div class="noticeBody2">
        <div class="noticeBody2-1">
            <div class="noticeBody2Btn1">
                <span class="text1">지금 떠나기 좋은 여행</span><br>
                <a class="text2-1" href="#part2">자세히 보기 &nbsp; ➞</a>
            </div>
            <img src="@/assets/part2.jpg">
        </div>
        <div class="noticeBody2-2">
            <div class="noticeBody2Btn2">
                <span class="text1">복잡한 여행을 Easy하게!</span><br>
                <a class="text2-2" href="#part3">자세히 보기 &nbsp; ➞</a>
            </div>
            <img src="@/assets/travel1.jpg">
        </div>
        <div class="noticeBody2-3">
            <div class="noticeBody2Btn3">
                <span class="text1">여행의 완성을 위한 경험</span><br>
                <a class="text2-3" href="#part4">자세히 보기 &nbsp; ➞</a>
            </div>
            <img src="@/assets/part4.jpg">
        </div>
    </div>
</div>

<div class="foot">
    <button class="closeNotice" @click="setNoticeView">닫기</button>
    <label class="closeNoticeToday">오늘 더 이상 안보기</label>
    <input type="checkbox" id="Chkbox" class="closeNoticeTodayCheck" value="closeCheck" @click="closeToday">

</div>
</template>

<script>
import VueCookies from 'vue-cookies'

export default {
    methods: {
        move(){
            alert("test")
            this.$emit('move')
        },
        closeModal() {
            this.$emit('close')
        },
        setNoticeView() { //cookie 설정
            const checkbox = document.getElementById('Chkbox');

            const is_checked = checkbox.checked;

            this.$emit("noticeView",is_checked)                        

            this.$emit('close')

        },
        getNoticeView() {            
            if (VueCookies.get('view') == "true") {
                this.$emit('noticeView')
            } else if(VueCookies.get('view') == "false"){
                console.log("false")
            }
        },
    },
    mounted() {

    },
}
</script>

<style>
.noticeTitle {
    color: teal;
    font-size: 18px;
    font-weight: 900;    
    text-align: center;
}

.foot {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    height: 35px;    
    color: white;
    background: teal;
    font-weight: bold;
    justify-content: space-between;
    align-items: center;
    cursor: pointer;
    border-bottom-left-radius: 8px;
    border-bottom-right-radius: 8px;
}

.closeNotice {
    color: white;
    background: teal;
    border: none;
    font-size: 12px;
    font-weight: 900;
    float: right;
    margin-top: 1.9%;
    margin-right: 3%;
}

.closeNoticeToday {
    color: white;
    background: teal;
    border: none;
    font-size: 12px;
    float: right;
    margin-top: 2.2%;
    margin-right: 2%;
}

.closeNoticeTodayCheck {
    float: right;
    margin-top: 2.5%;
    margin-right: 1.5%;
}

.noticeImg {
    width: 100%;
}

.noticeBody1 {
    text-align: center;
}

.noticeBody1-1 {
    font-size: 16px;
    font-weight: 900;
    color: rgba(0, 0, 0, 0.646);
}

.noticeBody1-2 {
    font-size: 14px;
    font-weight: 900;
    color: rgba(58, 58, 58, 0.646);
}

.noticeBody2 {
    height: 300px;
    margin-top: 4%;
    border: 1px solid teal;
    border-radius: 4px;
}

.noticeBody2-1 {
    height: 33.3%;
}

.noticeBody2-2 {
    height: 33.3%;
}

.noticeBody2-3 {
    height: 33.3%;
}

.noticeBody2-1 img,
.noticeBody2-3 img {
    height: 100%;
    width: 100%;
    overflow: hidden;
}

.noticeBody2-2 img{
    height: 95%;
    width: 40%;
    margin-right: 8%;
    float: right;
}

.noticeBody2Btn1,
.noticeBody2Btn2,
.noticeBody2Btn3 {
    color: white;
    text-align: center;
    padding: 26px;    
    z-index: 10;
    position: absolute;    
    width: 40%;
}

.noticeBody2Btn2 {
    color: #999;
}

.text1 {
    font-size: 12px;
    font-weight: 900;    
}

.text2 {
    font-size: 12px;
    font-weight: 900;    
    cursor: pointer;
}

.text2-1,
.text2-3{
    font-size: 12px;
    color: white;
}

.text2-2{
    font-size: 12px;
    color: #999;
}

.text2-1:visited,
.text2-3:visited {
    color: white;
}

.text2-2:visited {
    color: #999;
}

</style>
