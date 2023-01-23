<template>
<div>
    <div>
        <span class="comTitle">예약이 완료되었습니다.</span><br>
    </div>

    <div class="comInfoList">
        <div class="comInfoTitle">
            승객 정보 <span v-if="AdultCount > 1">1</span>
        </div>
        <div class="comInfoLeft">
            <span class="listLeft">한글 이름</span><br><br>
            <span class="listLeft">영어 이름</span><br><br>
            <span class="listLeft">생년 월일</span><br><br>
            <span class="listLeft">성별</span>
        </div>
        <div class="comInfoRight">
            <span class="listRight">{{korName}}</span><br><br>
            <span class="listRight">{{engName}}</span><br><br>
            <span class="listRight">{{birthday}}</span><br><br>
            <span class="listRight">{{Gender(gender)}}</span>
        </div>
    </div>

    <span v-for="(pas,index) in parseInt(AdultCount) + parseInt(ChildCount) + parseInt(InfantCount) - 1" :key="index">
        <div class="comInfoList">
            <div class="comInfoTitle">
                승객 정보 {{ index + 2 }}
            </div>
            <div class="comInfoLeft">
                <span class="listLeft">한글 이름</span><br><br>
                <span class="listLeft">영어 이름</span><br><br>
                <span class="listLeft">생년 월일</span><br><br>
                <span class="listLeft">성별</span>
            </div>
            <div class="comInfoRight">

                <span class="listRight">{{JSON.parse(addPassKorName)[index*2+1]}}{{JSON.parse(addPassKorName)[index*2]}}</span><br><br>
                <span class="listRight">{{JSON.parse(addPassEngName)[index*2+1]}} {{JSON.parse(addPassEngName)[index*2]}}</span><br><br>
                <span class="listRight">{{JSON.parse(addPassBirthday)[index]}}</span><br><br>
                <span class="listRight">{{JSON.parse(addPassGender)[index]}}</span>
            </div>
        </div>
    </span>
    
    <div class="comAreaList">
        <div class="comInfoTitle">
            가는편
        </div>
        <div class="comInfoLeft">
            <span class="listLeft">항공편</span><br><br>
            <span class="listLeft">항공편</span><br><br>
            <span class="listLeft">여행 일정</span><br><br>
            <span class="listLeft">승객 수</span><br><br>
            <span class="listLeft">좌석</span>
        </div>
        <div class="comInfoRight">
            <span class="listRight">{{ flight1 }}</span><br><br>
            <span class="listRight">{{ fromArea }} →&nbsp; {{ toArea }}</span><br><br>
            <span class="listRight"> {{startYear}}-{{startMonth}}-{{startDay}}({{startWeek}}) {{ startTime1 }} ~ {{ arriveTime1 }}</span><br><br>
            <span class="listRight" v-if="InfantCount > 0">, 소아 {{InfantCount}}명</span>
            <span class="listRight" v-if="ChildCount > 0">, 유아 {{ChildCount}}명</span>            
            <span class="listRight">성인 {{AdultCount}}명</span><br><br>

            
            
            <span class="listRight">{{ seat }} {{ seatClass1 }}</span>
        </div>
    </div>

    <div class="comAreaList" v-if="returnYear != ' '">
        <div class="comInfoTitle">
            오는편
        </div>
        <div class="comInfoLeft">
            <span class="listLeft">항공편</span><br><br>
            <span class="listLeft">항공편</span><br><br>
            <span class="listLeft">여행 일정</span><br><br>
            <span class="listLeft">승객 수</span><br><br>
            <span class="listLeft">좌석</span>
        </div>
        <div class="comInfoRight">
            <span class="listRight">{{ flight2 }}</span><br><br>
            <span class="listRight">{{ toArea }} →&nbsp; {{ fromArea }}</span><br><br>
            <span class="listRight">{{returnYear}}-{{returnMonth}}-{{returnDay}}({{returnWeek}}) {{ startTime2 }} ~ {{ arriveTime2 }}</span><br><br>            
            <span class="listRight" v-if="InfantCount > 0">, 소아 {{InfantCount}}명</span>
            <span class="listRight" v-if="ChildCount > 0">, 유아 {{ChildCount}}명</span>            
            <span class="listRight">성인 {{AdultCount}}명</span><br><br>
            <span class="listRight">{{ seat }} {{ seatClass2 }}</span>
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
        <!-- <button type="button" class="comBtn2" @click="Reservation">예약 조회하기</button> -->
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
            childCountView: false,
            infantCountView: false,
            holdPoint: this.$store.state.holdPoint,            
        }
    },
    props: {
        flight1: {
            type: String,
        },
        flight2: {
            type: String,
        },
        korName: {
            type: String,
        },
        engName: {
            type: String,
        },
        birthday: {
            type: String,
        },
        gender: {
            type: String,
        },
        addPassKorName: {
            type: String,
        },
        addPassEngName: {
            type: String,
        },
        addPassGender: {
            type: String,
        },
        addPassBirthday: {
            type: String,
        },
        fromArea: {
            type: String,
        },
        toArea: {
            type: String,
        },
        seat: {
            type: String,
        },
        seatClass1: {
            type: String,
        },
        seatClass2: {
            type: String,
        },
        startYear: {
            type: String,
            default: ''
        },
        startMonth: {
            type: String,
            default: ''
        },
        startDay: {
            type: String,
            default: ''
        },
        startWeek: {
            type: String,
            default: ''
        },
        returnYear: {
            type: String,
            default: ''
        },
        returnMonth: {
            type: String,
            default: ''
        },
        returnDay: {
            type: String,
            default: ''
        },
        returnWeek: {
            type: String,
            default: ''
        },
        InfantCount: {
            type: Number
        },
        ChildCount: {
            type: Number
        },
        AdultCount: {
            type: Number
        },
        startTime1: {
            type: String
        },
        arriveTime1: {
            type: String
        },
        startTime2: {
            type: String
        },
        arriveTime2: {
            type: String
        },
        startPrice: {
            type: Number
        },
    },
    methods: {

        home() {
            this.$router.replace({
                name: 'Home'
            })
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
    font-size: 60px;
    text-align: center;
    display: block;
    padding: 100px 0;
}

.comInfoList {
    border: 2px solid teal;
    border-radius: 4px;
    height: 340px;
    margin-bottom: 60px;
    width: 60%;
    margin-left: 20%;
    margin-right: 20%;
}

.comInfoTitle {
    width: 100%;
    color: white;
    background-color: teal;
    font-weight: 900;
    font-size: 24px;
    text-align: center;
    padding: 10px 0;
    margin-bottom: 20px;
}

.comInfoLeft {
    font-size: 22px;
    font-weight: 900;
    color: #999;
    float: left;
    margin-left: 40px;
}

.comInfoRight {
    font-size: 22px;
    font-weight: 900;    
    margin-right: 40px;
}

.listRight{
    float: right;
}

.comAreaList {
    border: 2px solid teal;
    border-radius: 4px;
    height: 400px;
    margin-bottom: 40px;
    width: 60%;
    margin-left: 20%;
    margin-right: 20%;
}

.comPayList {
    border: 2px solid teal;
    border-radius: 4px;
    height: 205px;
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
    font-size: 24px;
    text-align: center;
    padding: 10px 0;
    margin-bottom: 20px;
}

.comBtn {
    text-align: center;
}

.comBtn1,
.comBtn2 {
    width: 12%;
    padding: 1.5%;
    margin: 2%;
    color: white;
    background-color: teal;
    border: none;
    border-radius: 8px;
    font-size: 24px;
    font-weight: 900;
}

.topBtn,
a:hover,
a:visited,
a:active {
    color: rgb(77, 77, 77);
}


.Footer {
    border-top: 1px solid;
}

.FootLeft {
    float: left;
    margin-left: 280px;
    margin-top: 30px;
    font-size: 40px;
    padding-bottom: 50px;
}

.footLogo {
    width: 100px;
    height: 70px;
}

.FootRight {
    float: right;
    margin-right: 280px;
    margin-top: 60px;
}
</style>
