<template>
<div class="container">
    <div class="info">
        <h3>여행 예약 정보
            <button class="closeBtn" @click="this.$emit('close')">X</button>
        </h3>
    </div>
    <br>

    <div class="infoList">
        <div class="mInfo">
            승객 정보
        </div>
        <h3>한글 이름</h3><br>
        <h3>영어 이름</h3><br>
        <h3>생년 월일</h3><br>
        <h3>성별</h3><br>        
    </div>

    <div class="infoList">
        <div class="To">
           가는편
        </div>
        <h3>항공편</h3><br>
        <h3>여행 일정</h3><br>
        <h3>승객 수</h3><br>
        <h3>좌석</h3><br>
    </div>

    <div class="infoList">
        <div class="From">
            오는편
        </div>
        <h3>항공편</h3><br>
        <h3>여행 일정</h3><br>
        <h3>승객 수</h3><br>
        <h3>좌석</h3><br>
    </div>
    <br>

    <!--test-->
    <button type="button" class="reserBtn" @click="submit()">예약 하기</button>
</div>
</template>

<script>
export default {
    name: 'HelloWorld',
    components: {

    },
    data() {
        return {

        }
    },
    methods: {
        submit(){
            alert("예약하시겠습니까?")
        }
    },

}
</script>

<style>

.info h3 {
    margin-top: 2%;
    margin-left: 4%;
    color: teal;
    font-weight: 900;
}

.closeBtn {
    float: right;
    border: none;
    font-size: 24px;
    color: #999;
    background-color: white;
}

.infoList {
    border: 1px solid teal;
    border-radius: 4px;
    margin-bottom: 40px;
    
}

.infoList h3 {
    font-size: 20px;
    font-weight: 900;
    color: #999;
}

.mInfo,
.To,
.From{
    font-weight: 900;
    font-size: 24px;
    color: white;
    background-color: teal;
    padding: 6px;
    margin-bottom: 20px;
}

.reserBtn {
    width: 180px;
    height: 50px;
    font-size: 24px;
    color: white;
    border: 1px solid white;
    border-radius: 4px;
    background-color: teal;
}
</style>
