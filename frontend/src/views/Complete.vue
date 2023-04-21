<template>
<div>
    <div>
        <span class="comTitle">예약이 완료되었습니다.</span><br>
    </div>

    <!-- 승객 정보(성인) -->
    <span v-for="(pas,index) in chooseInfo?.AdultCount" :key="index">
        <div class="comInfoList">
            <div class="comInfoTitle">
                성인
                <span v-if="index == 0">1 [예약자]</span>
                <span v-if="index > 0">{{ index + 1 }}</span>
            </div>
            <div class="comInfoLeft">
                <span class="listLeft">한글 이름</span><br><br>
                <span class="listLeft">영어 이름</span><br><br>
                <span class="listLeft">생년 월일</span><br><br>
                <span class="listLeft">성별</span>
            </div>
            <div class="comInfoRight">
                <span class="listRight">{{addAdult[index]?.korName}}</span><br><br>
                <span class="listRight">{{addAdult[index]?.engLastName}} {{addAdult[index]?.engFirstName}}</span><br><br>
                <span class="listRight">{{addAdult[index]?.birthday}}</span><br><br>
                <span class="listRight">{{addAdult[index]?.gender}}</span>
            </div>
        </div>
    </span>

    <!-- 승객 정보(유아) -->
    <span v-for="(pas,index) in parseInt(chooseInfo?.ChildCount)" :key="index">
        <div class="comInfoList">
            <div class="comInfoTitle">
                유아 {{ index + 1 }}
            </div>
            <div class="comInfoLeft">
                <span class="listLeft">한글 이름</span><br><br>
                <span class="listLeft">영어 이름</span><br><br>
                <span class="listLeft">생년 월일</span><br><br>
                <span class="listLeft">성별</span>
            </div>
            <div class="comInfoRight">
                <span class="listRight">{{addChild[index]?.korName}}</span><br><br>
                <span class="listRight">{{addChild[index]?.engLastName}} {{addChild[index]?.engFirstName}}</span><br><br>
                <span class="listRight">{{addChild[index]?.gender}}</span><br><br>
                <span class="listRight">{{addChild[index]?.birthday}}</span>
            </div>
        </div>
    </span>

    <!-- 승객 정보(소아) -->
    <span v-for="(pas,index) in parseInt(chooseInfo?.InfantCount)" :key="index">
        <div class="comInfoList">
            <div class="comInfoTitle">
                소아 {{ index + 1 }}
            </div>
            <div class="comInfoLeft">
                <span class="listLeft">한글 이름</span><br><br>
                <span class="listLeft">영어 이름</span><br><br>
                <span class="listLeft">생년 월일</span><br><br>
                <span class="listLeft">성별</span>
            </div>
            <div class="comInfoRight">
                <span class="listRight">{{addInfant[index]?.korName}}</span><br><br>
                <span class="listRight">{{addInfant[index]?.engLastName}} {{addInfant[index]?.engFirstName}}</span><br><br>
                <span class="listRight">{{addInfant[index]?.gender}}</span><br><br>
                <span class="listRight">{{addInfant[index]?.birthday}}</span>
            </div>
        </div>
    </span>

    <div class="comAreaList">
        <div class="comInfoTitle">
            가는편
        </div>
        <div class="comInfoLeft">
            <span class="listLeft">항공편</span><br><br>
            <span class="listLeft">여행지</span><br><br>
            <span class="listLeft">여행 일정</span><br><br>
            <span class="listLeft">승객 수</span><br><br>
            <span class="listLeft">좌석</span>
        </div>
        <div class="comInfoRight">
            <span class="listRight">{{ startInfo?.flight1 }}</span><br><br>
            <span class="listRight">{{ chooseInfo?.fromArea }} →&nbsp; {{ chooseInfo?.toArea }}</span><br><br>
            <span class="listRight"> {{chooseInfo?.startYear}}-{{chooseInfo?.startMonth}}-{{chooseInfo?.startDay}}({{chooseInfo.startWeek}}) {{ startInfo.startTime1 }} ~ {{ startInfo.arriveTime1 }}</span><br><br>
            <span class="listRight" v-if="chooseInfo?.InfantCount > 0">, 소아 {{chooseInfo?.InfantCount}}명</span>
            <span class="listRight" v-if="chooseInfo?.ChildCount > 0">, 유아 {{chooseInfo?.ChildCount}}명</span>
            <span class="listRight">성인 {{chooseInfo?.AdultCount}}명</span><br><br>

            <span class="listRight">{{ chooseInfo?.seat }} {{ startInfo?.seatClass1 }}</span>
        </div>
    </div>
    <div class="comAreaList" v-if="chooseInfo?.returnYear">
        <div class="comInfoTitle">
            오는편
        </div>
        <div class="comInfoLeft">
            <span class="listLeft">항공편</span><br><br>
            <span class="listLeft">여행지</span><br><br>
            <span class="listLeft">여행 일정</span><br><br>
            <span class="listLeft">승객 수</span><br><br>
            <span class="listLeft">좌석</span>
        </div>
        <div class="comInfoRight">
            <span class="listRight">{{ returnInfo?.flight2 }}</span><br><br>
            <span class="listRight">{{ chooseInfo?.toArea }} →&nbsp; {{ chooseInfo?.fromArea }}</span><br><br>
            <span class="listRight">{{chooseInfo?.returnYear}}-{{chooseInfo?.returnMonth}}-{{chooseInfo?.returnDay}}({{chooseInfo?.returnWeek}}) {{ returnInfo?.startTime2 }} ~ {{ returnInfo?.arriveTime2 }}</span><br><br>
            <span class="listRight" v-if="chooseInfo?.InfantCount > 0">, 소아 {{chooseInfo?.InfantCount}}명</span>
            <span class="listRight" v-if="chooseInfo?.ChildCount > 0">, 유아 {{chooseInfo?.ChildCount}}명</span>
            <span class="listRight">성인 {{chooseInfo?.AdultCount}}명</span><br><br>
            <span class="listRight">{{ chooseInfo?.seat }} {{ returnInfo?.seatClass2 }}</span>
        </div>
    </div>

    <div class="comPayList">
        <div class="comPayTitle">
            결제
        </div>
        <div class="comInfoLeft">
            <span class="listLeft">결제 수단</span><br><br>
            <span class="listLeft">결제 후 포인트</span><br><br>

        </div>
        <div class="comInfoRight">
            <span class="listRight">포인트 결제</span><br><br>
            <span class="listRight">{{holdPoint}}</span><br><br>
        </div>
    </div>

    <div class="comBtn">
        <button type="button" class="comBtn1" @click="home">확인</button>
    </div>
</div>

<!-- FOOTER -->
<footer class="Footer">
    <div class="FootLeft">
        <img class="footLogo" src="../assets/Logo2.png">
        <span> Fastrip</span>
        <p>&copy; 2022 Company, Fastrip</p>
    </div>

    <div class="FootRight">
        <a class="topBtn" href="#top">✈ Top</a>
    </div>
</footer>
</template>

<script>
export default {
    name: 'HelloWorld',
    components: {

    },
    data() {
        return {
            chooseInfo: this.$store.state.chooseInfo,
            startInfo: this.$store.state.startInfo,
            returnInfo: this.$store.state.returnInfo,
            userInfo2: this.$store.state.userInfo2,
            engName: this.$store.state.engName,
            childCountView: false,
            infantCountView: false,
            holdPoint: this.$store.state.holdPoint,
            addAdult: this.$store.state.addAdult,
            addChild: this.$store.state.addChild,
            addInfant: this.$store.state.addInfant,
        }
    },
    props: {},
    methods: {
        home() {
            /*this.$router.replace({
                name: 'Home'
            })*/
            this.$router.push('/')
        },
        Reservation() {
            this.$router.push({
                name: 'Reservation'
            })
        },
        AddComma(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return num.toString().replace(regexp, ",");
        },
        Gender(value) {
            var string = value.toString();
            var total = string.substring(0, 6);

            if (total == "male") {
                return this.value = "남자"
            } else if (total == "female") {
                return this.value = "여자"
            }

        },
    },
}
</script>

<style>
.comTitle {
    color: teal;
    font-weight: 900;
    font-size: 30px;
    text-align: center;
    display: block;
    padding: 60px 0;
}

.comInfoList,
.comAreaList {
    content: url(@/assets/Logo2.png);
    width: 0%;
    height: 0%;
}

.comPayList {
    content: url(@/assets/Logo2.png);
    width: 40%;
    height: 50%;
    margin-left: 30%;
    margin-bottom: 30%;
}

.comBtn {
    text-align: center;
}

.comBtn1,
.comBtn2 {
    width: 10%;
    padding: 1%;
    margin: 2%;
    color: white;
    background-color: teal;
    border: none;
    border-radius: 8px;
    font-size: 20px;
    font-weight: 900;
}

.topBtn,
a:hover,
a:visited,
a:active {
    color: white;
}

.Footer {
    overflow: none;
}

/* 화면 축소 전 후 */

@media (min-width: 600px) {
    .comTitle {
        color: teal;
        font-weight: 900;
        font-size: 40px;
        text-align: center;
        display: block;
        padding: 80px 0;
    }

    .comInfoList {
        content: none;
        border: 2px solid teal;
        border-radius: 4px;
        height: 260px;
        margin-bottom: 60px;
        width: 60%;
        margin-left: 20%;
        margin-right: 20%;
        font-size: 14px;
    }

    .comInfoTitle {
        width: 100%;
        color: white;
        background-color: teal;
        font-weight: 900;
        font-size: 16px;
        text-align: center;
        padding: 10px 0;
        margin-bottom: 20px;
    }

    .comInfoLeft {
        font-size: 16px;
        font-weight: 900;
        color: #999;
        float: left;
        margin-left: 40px;
    }

    .comInfoRight {
        font-size: 16px;
        font-weight: 900;
        margin-right: 40px;
    }

    .listRight {
        float: right;
    }

    .comAreaList {
        content: none;
        border: 2px solid teal;
        border-radius: 4px;
        height: 310px;
        margin-bottom: 40px;
        width: 60%;
        margin-left: 20%;
        margin-right: 20%;
    }

    .comPayList {
        content: none;
        border: 2px solid teal;
        border-radius: 4px;
        height: 170px;
        margin-bottom: 40px;
        width: 60%;
        margin-left: 20%;
        margin-right: 20%;
    }

    .comPayTitle {
        width: 100%;
        color: white;
        background-color: teal;
        font-weight: 900;
        font-size: 16px;
        text-align: center;
        padding: 10px 0;
        margin-bottom: 20px;
    }

    .comBtn {
        text-align: center;
    }

    .comBtn1,
    .comBtn2 {
        width: 10%;
        padding: 1%;
        margin: 2%;
        color: white;
        background-color: teal;
        border: none;
        border-radius: 8px;
        font-size: 20px;
        font-weight: 900;
    }

    .Footer {
        border-top: 1px solid;
    }

    .FootLeft {
        float: left;
        margin-left: 120px;
        margin-top: 50px;
        font-size: 20px;
        font-weight: 900;
        padding-bottom: 50px;
    }

    .FootLeft>p {
        font-size: 16px;
        font-weight: 900;
    }

    .footLogo {
        width: 35%;
        height: 60px;
        margin-right: 10px;
        margin-bottom: 10px;
    }

    .FootRight {
        float: right;
        margin-right: 150px;
        margin-top: 80px;
    }

    .FootRight>a {
        font-size: 16px;
        color: black;
    }

    .FootRight>a:visited {
        color: black;
    }

    .topBtn {
        color: rgb(77, 77, 77);
    }

    .topBtn:hover {
        color: rgb(77, 77, 77);
    }

}
</style>
