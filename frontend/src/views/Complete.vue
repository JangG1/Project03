<template>
<div>
    <div>
        <span class="comTitle">예약이 완료되었습니다.</span><br>
    </div>

    <div class="comInfoList">
        <div class="comInfoTitle">
            승객 정보
        </div>
        <div class="comInfoLeft">
            <span class="listLeft">한글 이름</span><br><br>
            <span class="listLeft">영어 이름</span><br><br>
            <span class="listLeft">생년 월일</span><br><br>
            <span class="listLeft">성별</span>
        </div>
        <div class="comInfoRight">
            <span class="listRight">{{korLastName}} {{korFirstName}}</span><br><br>
            <span class="listRight">{{engLastName}} {{engFirstName}}</span><br><br>
            <span class="listRight">{{birthday}}</span><br><br>
            <span class="listRight">{{Gender(gender)}}</span>
        </div>
    </div>

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
            <span class="listRight" v-show="InfantCount > 0">, 소아 {{InfantCount}}명</span>
            <span class="listRight" v-show="ChildCount > 0">, 유아 {{ChildCount}}명</span>            
            <span class="listRight">성인 {{AdultCount}}명</span><br><br>

            
            
            <span class="listRight">{{ seat }} {{ seatClass1 }}</span>
        </div>
    </div>

    <div class="comAreaList">
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
            <span class="listRight" v-show="InfantCount > 0">, 소아 {{InfantCount}}명</span>
            <span class="listRight" v-show="ChildCount > 0">, 유아 {{ChildCount}}명</span>            
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
        <button type="button" class="comBtn2" @click="Reservation">예약 조회하기</button>
    </div>
</div>
<!--test-->
=========================================================
<div>
    <button type="button" class="comBtn1" @click="test">test</button>    
    <button type="button" class="comBtn1" @click="testSend">testSend</button>
</div>
<!-- FOOTER -->
<hr>
<div class="footLogoName">
    <img class="footLogo" src="../assets/Logo.png">
    Fastrip
</div>
<footer class="footer">
    <p>&copy; 2022 Company, Fastrip</p>
    <a class="topBtn" href="">✈ Top</a>
</footer>
</template>

<script>
import axios from 'axios'

export default {
    name: 'HelloWorld',
    components: {

    },
    data() {
        return {
            korLastName: this.$store.state.name.substring(0, 1),
            korFirstName: this.$store.state.name.substring(1, 3),
            birthday: this.$store.state.birthday.toString().replace(/\B(?=(\d{2})+(?!\d))/g, "."),
            gender: this.$store.state.gender,
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
        engLastName: {
            type: String,
        },
        engFirstName: {
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
        test() {            

            if(this.returnYear != null){
                this.way = "왕복"
            }else if(this.returnYear == null){
                this.way = "편도";
            }

            console.log(this.korLastName)
            console.log(this.korFirstName)
            console.log(this.engLastName)
            console.log(this.engFirstName)
            console.log(this.birthday)
            console.log(this.gender)
            console.log(this.fromArea)
            console.log(this.toArea)
            console.log(this.way)
            console.log(this.seat)
            console.log(this.seatClass1)
            console.log(this.seatClass2)
            console.log(this.startYear)
            console.log(this.startMonth)
            console.log(this.startDay)
            console.log(this.startWeek)
            console.log(this.returnYear)
            console.log(this.returnMonth)
            console.log(this.returnDay)
            console.log(this.returnWeek)
            console.log(this.AdultCount)
            console.log(this.ChildCount)
            console.log(this.InfantCount)
            console.log(this.startTime1)
            console.log(this.arriveTime1)
            console.log(this.startTime2)
            console.log(this.arriveTime2)
            
        },
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

            console.log(total)
            if (total == "male") {
                return this.value = "남자"
            } else if (total == "female") {
                return this.value = "여자"
            }

        },
        testSend() {
            const startDate = this.startYear + "-" + this. startMonth + "-" + this.startDay + '(' + this.startWeek + ')';
            const returnDate = this.returnYear + "-" + this. returnMonth + "-" + this.returnDay + '(' + this.returnWeek + ')';

            if(this.returnYear != null){
                this.way = "왕복"
            }else{
                this.way = "편도";
            }

            axios.post("/res/test", {
                    email: this.$store.state.userInfo.email,
                    korFirstName: this.korFirstName,
                    korLastName: this.korLastName,
                    engFirstName: this.engFirstName,
                    engLastName: this.engLastName,
                    gender: this.gender,
                    birthday: this.birthday,
                    seat: this.seat,
                    seatClass1: this.seatClass1,
                    seatClass2: this.seatClass2,
                    way: this.way,
                    fromArea: this.fromArea,
                    toArea: this.toArea,
                    startDate: startDate,
                    returnDate: returnDate,
                    adultCount: this.AdultCount,
                    childCount: this.ChildCount,
                    infantCount: this.InfantCount,
                    startTime1: this.startTime1,
                    arriveTime1: this.arriveTime1,
                    startTime2: this.startTime2,
                    arriveTime2: this.arriveTime2,
                })
                .then(res => {
                    console.log(res)
                    console.log("보내짐")
                })
                .catch(err => {
                    console.log(err)
                    console.log("안보내짐")
                })
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
    height: 320px;
    margin-bottom: 60px;
    width: 80%;
    margin-left: 10%;
    margin-right: 10%;
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
    font-size: 20px;
    font-weight: 900;
    color: #999;
    float: left;
    margin-left: 40px;
}

.comInfoRight {
    font-size: 20px;
    font-weight: 900;
    float: right;
    margin-right: 40px;
}

.comAreaList {
    border: 2px solid teal;
    border-radius: 4px;
    height: 385px;
    margin-bottom: 40px;
    width: 80%;
    margin-left: 10%;
    margin-right: 10%;
}

.comPayList {
    border: 2px solid teal;
    border-radius: 4px;
    height: 205px;
    margin-bottom: 40px;
    width: 80%;
    margin-left: 10%;
    margin-right: 10%;
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
    font-size: 20px;
    font-weight: 900;
}
</style>
