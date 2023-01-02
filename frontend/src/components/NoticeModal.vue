<template>
<div class="">
    <h4 class="noticeTitle">Fastrip 공지사항</h4>
    <hr>
   <span>Fastrip에 오신것을 환영합니다!<br>
    

   </span>
</div>

<div class="foot">
    <button class="closeNotice" @click="closeModal">닫기</button>
    <label class="closeNoticeToday">오늘 더 이상 안보기</label>
    <input type="checkbox" class="closeNoticeTodayCheck" value="closeCheck" @click="closeToday">

</div>
</template>

<script>
export default {
    methods: {
        closeToday(event) {
            if (event.target.checked) {
                alert(event.target.value)
            }
        },
        closeModal() {            
            this.$emit('close')
        },
    }
}
</script>

<style>
.noticeTitle {
    color: teal;
    font-size: 25px;
    font-weight: 900;
    margin-top: 20px;
    text-align: center;
}

.foot {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    height: 50px;
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
    font-size: 16px;
    font-weight: 900;
    float: right;
    margin-top: 2%;
    margin-right: 3%;
}

.closeNoticeToday {
    color: white;
    background: teal;
    border: none;
    font-size: 16px;
    float: right;
    margin-top: 2.2%;
    margin-right: 2%;
}

.closeNoticeTodayCheck {
    float: right;
    margin-top: 3%;
    margin-right: 1.5%;
}

.noticeImg{
    width: 100%;    
}
</style>
