<template>
<div class="Dleft">
    <h4 class="title">위험품 안내</h4>
    <h4>항공기 반입금지 물품</h4>
    <div class="leftbox">
        <div class="leftbox1">
            <img src="../assets/danger1.jpg" class="IATAImg">
        </div>
        <div>
            <h4>발화성/인화성 물질</h4>
            <li>
                휘발유, 페인트, 라이터용 등 발화성/인화성 물질
            </li>
        </div>
    </div>

    <div class="leftbox">
        <div class="leftbox1">
            <img src="../assets/danger2.jpg" class="IATAImg">
        </div>
        <div>
            <h4>무기 및 폭발물 종류</h4>
            <li>총기, 폭죽 등 무기 및 폭발물 종류</li>
        </div>
    </div>
    <div class="leftbox">
        <div class="leftbox1">
            <img src="../assets/danger3.jpg" class="IATAImg">
        </div>
        <div>
            <h4>리튬 배터리 장착 전자기기</h4>
            <li>
                배터리 용량이 160Wh를 초과하는 리튬<br>
                배터리가 장착된 전자기기
            </li>
            <li>
                배터리 용량이 160Wh를 초과하는 보조/<br>
                여분의 리튬 배터리
            </li>
            <li>
                리튬 배터리가 분리되지 않는 전동 휠<br>
                스마트 가방
            </li>
        </div>
    </div>
</div>
<div class="Dright">
    <div class="rightbox">
        <div class="rightbox2">
            <img src="../assets/danger4.jpg" class="IATAImg">
        </div>
        <div>
            <h4>고압가스 용기</h4>
            <li>산소캔, 부탄가스팬 등 고압가스 용기</li>
        </div>
    </div>
    <div class="rightbox">
        <div class="rightbox2">
            <img src="../assets/danger5.jpg" class="IATAImg">
        </div>
        <div>
            <h4>기타 위험 물질</h4>
            <li>소화기, 에어로졸(살충제 등), 락스, 파마약 등<br>
                탑승객 및 항공기에 위험이 될 가능성이 있는 <br>
                물질</li>
        </div>
    </div>
</div>

<div class="foot" @click="closeModal">
    <button class="closeBtn" @click="closeModal">확인</button>
</div>
</template>

<script>
export default {
    methods: {
        closeModal() {
            this.$emit('close')
        },
    }
}
</script>

<style>
.leftbox h4 {
    margin-top: 50px;
    margin-left: 120px;
    color: rgb(79, 79, 79);
}

.leftbox li {
    margin-top: 10px;
    margin-left: 120px;
    color: rgb(79, 79, 79);
}

.rightbox h4 {
    margin-top: 50px;
    margin-left: 120px;
    color: rgb(79, 79, 79);
}

.rightbox li {
    margin-top: 10px;
    margin-left: 120px;
    color: rgb(79, 79, 79);
}

.leftbox1 {
    float: left;
}

.rightbox2 {
    position: absolute;
}

.title {
    color: teal;
    font-size: 25px;
    font-weight: 900;
    margin-top: 20px;
}

.Dleft {}

.Dright {}

.Dleft::-webkit-scrollbar {
    width: 10px;
}

.Dleft::-webkit-scrollbar-thumb {
    background-color: teal;
    border-radius: 10px;
    /*스크롤바의 색상*/
}

.Dleft::-webkit-scrollbar-track {
    background-color: white;
    border-radius: 10px;
    /*스크롤바 트랙 색상*/
}

@media (min-width: 1970px) {

    .Dleft {
        margin-left: 1%;
        float: left;
    }

    .Dright {
        float: right;
        margin-top: 10%;
        margin-right: 7%;
    }

    .Dleft::-webkit-scrollbar {
        width: 10px;
    }

    .Dleft::-webkit-scrollbar-thumb {
        background-color: teal;
        border-radius: 10px;
        /*스크롤바의 색상*/
    }

    .Dleft::-webkit-scrollbar-track {
        background-color: white;
        border-radius: 10px;
        /*스크롤바 트랙 색상*/
    }

}

.IATAImg {
    width: 100px;
    height: 100px;
}

.foot {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    height: 75px;
    color: white;
    background: teal;
    font-weight: bold;
    justify-content: space-between;
    align-items: center;
    box-shadow: 2px 2px 20px rgb(1, 83, 83);
    cursor: pointer;
}

.closeBtn {
    color: white;
    background: teal;
    border: none;
    margin-left: 50%;
    margin-right: 50%;
    padding: 19px 0;
    font-size: 24px;

}

@media (min-width: 1970px) {}
</style>
