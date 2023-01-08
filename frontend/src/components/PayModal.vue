<template>
<div class="">
    <div class="infoTitle">
        <span class="title">여행 예약 정보</span>
        <button class="payCloseBtn" @click="closeModal">X</button>
    </div>
    <br>

    <div class="infoList">
        <div class="title2">
            승객 정보
        </div>
        <div class="infoLeft">
            <span class="listLeft">한글 이름</span><br><br>
            <span class="listLeft">영어 이름</span><br><br>
            <span class="listLeft">생년 월일</span><br><br>
            <span class="listLeft">성별</span>
        </div>
        <div class="infoRight">
            <span class="listRight">{{user.korLastName}} {{user.korFirstName}}</span><br><br>
            <span class="listRight">{{engLastName}} {{engFirstName}}</span><br><br>
            <span class="listRight">{{user.birthday}}</span><br><br>
            <span class="listRight">{{Gender(user.gender)}}</span>
        </div>
    </div>

    <div class="areaList">
        <div class="title2">
            가는편
        </div>
        <div class="infoLeft">
            <span class="listLeft">항공편</span><br><br>
            <span class="listLeft">여행 경로</span><br><br>
            <span class="listLeft">여행 일정</span><br><br>
            <span class="listLeft">승객 수</span><br><br>
            <span class="listLeft">좌석</span>
        </div>
        <div class="infoRight">
            <span class="listRight">{{user.flight1}}</span><br><br>
            <span class="listRight">{{user.fromArea}} &nbsp;→&nbsp; {{user.toArea}}</span><br><br>
            <span class="listRight">{{user.startYear}} {{user.startTime1}} ~ {{user.arriveTime1}}</span><br><br>            
            <span class="listRight" v-show="user.InfantCount > 0">, 소아 {{user.InfantCount}}명</span>
            <span class="listRight" v-show="user.ChildCount > 0">, 유아 {{user.ChildCount}}명</span>
            <span class="listRight">성인 {{user.AdultCount}} 명 </span>
            <br><br>
            <span class="listRight">{{user.seat}} {{user.seatClass1}}</span>
        </div>
    </div>

    <div class="areaList">
        <div class="title2">
            오는편
        </div>
        <div class="infoLeft">
            <span class="listLeft">항공편</span><br><br>
            <span class="listLeft">여행 경로</span><br><br>
            <span class="listLeft">여행 일정</span><br><br>
            <span class="listLeft">승객 수</span><br><br>
            <span class="listLeft">좌석</span>
        </div>
        <div class="infoRight">
            <span class="listRight">{{user.flight2}}</span><br><br>
            <span class="listRight">{{user.toArea}} &nbsp;→&nbsp; {{user.fromArea}}</span><br><br>
            <span class="listRight">{{user.returnYear}} {{user.startTime2}} ~ {{user.arriveTime2}}</span><br><br>
            <span class="listRight" v-show="user.InfantCount > 0">, 소아 {{user.InfantCount}}명</span>
            <span class="listRight" v-show="user.ChildCount > 0">, 유아 {{user.ChildCount}}명</span>
            <span class="listRight">성인 {{user.AdultCount}}명</span>
            <br><br>
            <span class="listRight">{{user.seat}} {{user.seatClass2}}</span>
        </div>
    </div>
    <br>

    <div class="payList">
        <div class="title2">
            결제
        </div>
        <div class="infoLeft">
            <span class="listLeft">결제 수단</span><br><br>
            <span class="listLeft">결제 후 포인트</span><br><br>
        </div>
        <div class="infoRight">
            <span class="listRight">포인트 결제</span><br><br>
            <span class="listRight">{{holdPoint - user.startPrice}}</span><br><br>
        </div>
    </div>

    <!--submit-->
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
            childCountView: false,
            infantCountView: false,
            holdPoint: this.$store.state.holdPoint,
        }
    },
    props: {
        user: {
            type: String,
            default: ''
        },
        engLastName: {
            type: String,
            default: ''
        },
        engFirstName: {
            type: String,
            default: ''
        },

    },
    methods: {
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
        closeModal() {
            this.$emit('close')
        },
        Gender(value) {
            let gender = value
            if (gender == "male") {
                gender = "남자"
            } else if (gender == "female") {
                gender = "여자"
            }
            return gender
        },
        submit() {
            alert("예약하시겠습니까?")

            this.$router.push({
                name: 'Complete',
                params: {
                    flight1: this.user.flight1,
                    flight2: this.user.flight2,
                    korLastName: this.user.korLastName,
                    korFirstName: this.user.korFirstName,
                    engLastName: this.engLastName,
                    engFirstName: this.engFirstName,
                    birthday: this.user.birthday,
                    gender: this.user.gender,
                    fromArea: this.user.fromArea,
                    toArea: this.user.toArea,
                    seat: this.user.seat,
                    seatClass1: this.user.seatClass1,
                    seatClass2: this.user.seatClass2,
                    startYear: this.user.startYear,
                    startMonth: this.user.startMonth,
                    startDay: this.user.startDay,
                    startWeek: this.user.startWeek,
                    returnYear: this.user.returnYear,
                    returnMonth: this.user.returnMonth,
                    returnDay: this.user.returnDay,
                    returnWeek: this.user.returnWeek,
                    InfantCount: this.user.InfantCount,
                    ChildCount: this.user.ChildCount,
                    AdultCount: this.user.AdultCount,
                    startTime1: this.user.startTime1,
                    arriveTime1: this.user.arriveTime1,
                    startTime2: this.user.startTime2,
                    arriveTime2: this.user.arriveTime2,
                    startPrice: this.user.startPrice,
                }
            });

            let usePoint = this.holdPoint - this.user.startPrice
            this.$store.dispatch("holdPoint", usePoint);

        }
    },
}
</script>

<style>
.infoTitle {
    margin-top: 2%;
    margin-left: 4%;
    color: teal;
    font-weight: 900;
}

.payCloseBtn {
    color: teal;
    font-weight: 900;
    font-size: 22px;
    float: right;
    border: 1px solid white;
    background-color: white;
}

.payModalCloseBtn {
    float: right;
    border: none;
    font-size: 24px;
    color: #999;
    background-color: white;
}

.payList {
    border: 1px solid teal;
    border-radius: 4px;
    height: 200px;
    margin-bottom: 40px;
}

.infoList {
    border: 1px solid teal;
    border-radius: 4px;
    height: 320px;
    margin-top: 30px;
    margin-bottom: 20px;

}

.areaList {
    border: 1px solid teal;
    border-radius: 4px;
    height: 380px;
    margin-top: 30px;
    margin-bottom: 20px;

}

.infoLeft {
    font-size: 20px;
    font-weight: 900;
    color: #999;
    float: left;
    margin-left: 40px;
}

.infoRight {
    font-size: 20px;
    font-weight: 900;
    float: right;
    margin-right: 40px;
}

.listLeft {
    float: left;
}

.listRight {
    float: right;
}

.title2 {
    width: 100.1%;
    color: white;
    background-color: teal;
    font-weight: 900;
    font-size: 24px;
    text-align: center;
    padding: 10px 0;
    margin-bottom: 20px;
}

.reserBtn {
    width: 180px;
    height: 50px;
    font-size: 24px;
    font-weight: 900;
    color: white;
    border: 1px solid white;
    border-radius: 4px;
    background-color: teal;
}
</style>
