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
            <span class="listRight">{{ toArea }} →&nbsp; {{ fromArea }}</span><br><br>
            <span class="listRight">{{ Format(startDate) }} {{ startTime1 }} ~ {{ arriveTime1 }}</span><br><br>
            <span class="listRight" v-show="infantCountView">{{comInfantCount(InfantCount)}}</span>
            <span class="listRight" v-show="childCountView">{{comChildCount(ChildCount)}}</span>
            <span class="listRight">{{AdultCount}}</span><br><br>
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
            <span class="listRight">{{ fromArea }} →&nbsp; {{ toArea }}</span><br><br>
            <span class="listRight">{{ Format(returnDate) }} {{ startTime2 }} ~ {{ arriveTime2 }}</span><br><br>
            <span class="listRight" v-show="infantCountView">{{comInfantCount(InfantCount)}}</span>
            <span class="listRight" v-show="childCountView">{{comChildCount(ChildCount)}}</span>
            <span class="listRight">{{AdultCount}}</span><br><br>
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
            <span class="listRight">{{this.$store.state.holdPoint - startPrice}}</span><br><br>
        </div>
    </div>

    <div class="comBtn">
    <button type="button" class="comBtn1" @click="home">확인</button>
    <button type="button" class="comBtn2" @click="Reservation">예약 조회하기</button>
</div>
</div>

<!-- FOOTER -->
<hr>
<div class="footLogoName">
    <img class="footLogo" src="../assets/Logo.png">
    Fastrip
</div>
<footer class="footer">
    <p>&copy; 2022 Company, Fastrip</p>
    <a class="topBtn" href="">Top</a>
</footer>


</template>

<script>
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
        startDate: {
            type: String,
        },
        returnDate: {
            type: String,
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
        home(){
            this.$router.push({ name: 'Home'})
        },
        Reservation(){
            this.$router.push({ name: 'Reservation'})
        },
        comChildCount(value) {
            console.log(value.substr(4, 2))
            if (value.substr(4, 2) > 0) {
                return this.childCountView = ", 소아 " + value.substr(4, 2) + "명"
            } else if (value.substr(4, 2) == 0) {
                return this.ChildCount == ""
            }
        },
        comInfantCount(value) {
            console.log(value.substr(4, 2))
            if (value.substr(4, 2) > 0) {
                return this.infantCountView = ", 유아 " + value.substr(4, 2) + "명"
            } else if (value.substr(4, 2) == 0) {
                return this.infantCountView = ""
            }
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
        Format(value) {
            var string = value.toString();
            var total = string.substring(0, 16);

            var year = total.substring(11, 15);
            var day = total.substring(8, 10);
            var week = total.substring(0, 3);
            var month = total.substring(4, 7);

            if (week == "Mon") {
                week = "(월)";
            } else if (week == "Tue") {
                week = "(화)"
            } else if (week == "Wed") {
                week = "(수)"
            } else if (week == "Thu") {
                week = "(목)"
            } else if (week == "Fri") {
                week = "(금)"
            } else if (week == "Sat") {
                week = "(토)"
            } else if (week == "Sun") {
                week = "(일)"
            }

            if (month == "Jan") {
                month = "1";
            } else if (month == "Feb") {
                month = "2"
            } else if (month == "Mar") {
                month = "3"
            } else if (month == "Apr") {
                month = "4"
            } else if (month == "May") {
                month = "5"
            } else if (month == "Jun") {
                month = "6"
            } else if (month == "Jul") {
                month = "7"
            } else if (month == "Aug") {
                month = "8"
            } else if (month == "Sep") {
                month = "9"
            } else if (month == "Oct") {
                month = "10"
            } else if (month == "Nov") {
                month = "11"
            } else if (month == "Dec") {
                month = "12"
            }

            return year + "-" + month + "-" + day + week;
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

.comBtn{
    text-align: center;
}

.comBtn1,
.comBtn2{
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
