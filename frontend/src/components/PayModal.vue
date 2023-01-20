<template>
<div class="">
    <div class="infoTitle">
        <span class="title" style="font-size: 30px;">여행 예약 정보</span>
        <button class="payCloseBtn" @click="closeModal">X</button>
    </div>
    <br>

    <span>
        <div class="infoList">
            <div class="title2">
                승객 정보 <span v-if="user.AdultCount > 1">1</span>
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
    </span>

    <span v-for="(pas,index) in parseInt(user.AdultCount) - 1" :key="index">
        <div class="infoList">
            <div class="title2">
                승객 정보 {{ index + 2 }}
            </div>
            <div class="infoLeft">
                <span class="listLeft">한글 이름</span><br><br>
                <span class="listLeft">영어 이름</span><br><br>
                <span class="listLeft">생년 월일</span><br><br>
                <span class="listLeft">성별</span>
            </div>
            <div class="infoRight">

                <span class="listRight">{{addPassKorName[index*2+1]}} {{addPassKorName[index*2]}}</span><br><br>
                <span class="listRight">{{addPassEngName[index*2+1]}} {{addPassEngName[index*2]}}</span><br><br>
                <span class="listRight">{{addPassBirthday[index]}}</span><br><br>
                <span class="listRight">{{addPassGender[index]}}</span>
            </div>
        </div>
    </span>

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
            <span class="listRight">{{user.startYear}}-{{user.startMonth}}-{{user.startDay}}({{user.startWeek}})
                {{user.startTime1}} ~ {{user.arriveTime1}}</span><br><br>
            <span class="listRight" v-if="user.InfantCount > 0">, 소아 {{user.InfantCount}}명</span>
            <span class="listRight" v-if="user.ChildCount > 0">, 유아 {{user.ChildCount}}명</span>
            <span class="listRight">성인 {{user.AdultCount}} 명 </span>
            <br><br>
            <span class="listRight">{{user.seat}} {{user.seatClass1}}</span>
        </div>
    </div>

    <div class="areaList" v-if="user.returnYear != ' '">
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
            <span class="listRight">{{user.returnYear}}-{{user.returnMonth}}-{{user.returnDay}}({{user.returnWeek}})
                {{user.startTime2}} ~ {{user.arriveTime2}}</span><br><br>
            <span class="listRight" v-if="user.InfantCount > 0">, 소아 {{user.InfantCount}}명</span>
            <span class="listRight" v-if="user.ChildCount > 0">, 유아 {{user.ChildCount}}명</span>
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
import axios from 'axios';

export default {
    name: 'HelloWorld',
    components: {

    },
    data() {
        return {
            childCountView: false,
            infantCountView: false,
            holdPoint: this.$store.state.holdPoint,
            fullname: [],
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
        addPassKorName: {
            type: String,
            default: ''
        },
        addPassEngName: {
            type: String,
            default: ''
        },
        addPassGender: {
            type: String,
            default: ''
        },
        addPassBirthday: {
            type: String,
            default: ''
        },

    },
    methods: {
        test2() {
            const startDate = this.user.startYear + "-" + this.user.startMonth + "-" + this.user.startDay + '(' + this.user.startWeek + ')';
            const returnDate = this.user.returnYear + "-" + this.user.returnMonth + "-" + this.user.returnDay + '(' + this.user.returnWeek + ')';
          
            console.log(this.$store.state.userInfo.email)
            console.log(this.user.korLastName + this.user.korFirstName)
            console.log(this.engLastName + " " + this.engFirstName)
            console.log(this.user.gender)
            console.log(this.user.birthday)

            console.log(this.addPassKorName)
            console.log(this.addPassEngName)
            console.log(this.addPassGender)
            console.log(this.addPassBirthday)

            console.log(this.user.seat)
            console.log(this.user.seatClass1)
            console.log(this.user.seatClass2)
            console.log(this.way)
            console.log(this.user.flight1)
            console.log(this.user.flight2)
            console.log(this.user.fromArea)
            console.log(this.user.toArea)
            console.log(startDate)
            console.log(returnDate)
            console.log(this.user.InfantCount)
            console.log(this.user.ChildCount)
            console.log(this.user.AdultCount)
            console.log(this.user.startTime1)
            console.log(this.user.arriveTime1)
            console.log(this.user.startTime2)
            console.log(this.user.arriveTime2)
        },
        test() {
            const startDate = this.user.startYear + "-" + this.user.startMonth + "-" + this.user.startDay + '(' + this.user.startWeek + ')';
            const returnDate = this.user.returnYear + "-" + this.user.returnMonth + "-" + this.user.returnDay + '(' + this.user.returnWeek + ')';

            if (this.user.returnYear != " ") {
                this.way = "왕복"
            } else {
                this.way = "편도";
            }

            console.log(this.name)
            console.log(this.$store.state.userInfo.email)
            for (let i = 0; i <= parseInt(this.user.AdultCount) - 2; i++) {
                console.log(this.name[i])

                let name = this.name[i]
                console.log("name " + name)
            }
            for (let i = this.user.AdultCount; i >= parseInt(this.user.AdultCount); --i) {
                console.log(this.name[i])
                let birthday = this.name[i]
                console.log("birthday " + birthday)
            }
            for (let i = this.user.AdultCount - 1; i >= parseInt(this.user.AdultCount) - 1; --i) {
                console.log(this.name[i])
                let gender = this.name[i]
                console.log("gender " + gender)
            }

            console.log(this.user.seat)
            console.log(this.user.seatClass1)
            console.log(this.user.seatClass2)
            console.log(this.way)
            console.log(this.user.flight1)
            console.log(this.user.flight2)
            console.log(this.user.fromArea)
            console.log(this.user.toArea)
            console.log(startDate)
            console.log(returnDate)
            console.log(this.user.InfantCount)
            console.log(this.user.ChildCount)
            console.log(this.user.AdultCount)
            console.log(this.user.startTime1)
            console.log(this.user.arriveTime1)
            console.log(this.user.startTime2)
            console.log(this.user.arriveTime2)

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

            const startDate = this.user.startYear + "-" + this.user.startMonth + "-" + this.user.startDay + '(' + this.user.startWeek + ')';
            const returnDate = this.user.returnYear + "-" + this.user.returnMonth + "-" + this.user.returnDay + '(' + this.user.returnWeek + ')';

            if (this.user.returnYear != " ") {
                this.way = "왕복"
            } else {
                this.way = "편도";
            }
           
             axios.post("/res/resPost", {
                    email: this.$store.state.userInfo.email,
                    korName: this.user.korLastName + this.user.korFirstName,
                    engName: this.engLastName + " " + this.engFirstName,
                    gender: this.user.gender,
                    birthday: this.user.birthday,
                    addPassKorName: this.addPassKorName,
                    addPassEngName: this.addPassEngName,
                    addPassGender: this.addPassGender,
                    addPassBirthday: this.addPassBirthday,                    
                    seat: this.user.seat,
                    seatClass1: this.user.seatClass1,
                    seatClass2: this.user.seatClass2,
                    way: this.way,
                    flight1: this.user.flight1,
                    flight2: this.user.flight2,
                    fromArea: this.user.fromArea,
                    toArea: this.user.toArea,
                    oneWayArea: " ",
                    startDate: startDate,
                    returnDate: returnDate,
                    infantCount: this.user.InfantCount,
                    childCount: this.user.ChildCount,
                    adultCount: this.user.AdultCount,
                    startTime1: this.user.startTime1,
                    arriveTime1: this.user.arriveTime1,
                    startTime2: this.user.startTime2,
                    arriveTime2: this.user.arriveTime2
                })
                .then(res => {
                    console.log(res)
                    console.log("보내짐")
                })
                .catch(err => {
                    console.log(err)
                    console.log("안보내짐")
                })

            let usePoint = this.holdPoint - this.user.startPrice
            this.$store.dispatch("holdPoint", usePoint);

            this.$router.push({
                name: 'Complete',
                params: {
                    korName: this.user.korLastName + this.user.korFirstName,
                    engName: this.engLastName + " " + this.engFirstName,
                    gender: this.user.gender,
                    birthday: this.user.birthday,
                    addPassKorName: JSON.stringify(this.addPassKorName),
                    addPassEngName: JSON.stringify(this.addPassEngName),
                    addPassGender: JSON.stringify(this.addPassGender),
                    addPassBirthday: JSON.stringify(this.addPassBirthday),                    
                    seat: this.user.seat,
                    seatClass1: this.user.seatClass1,
                    seatClass2: this.user.seatClass2,
                    way: this.way,
                    flight1: this.user.flight1,
                    flight2: this.user.flight2,
                    fromArea: this.user.fromArea,
                    toArea: this.user.toArea,
                    oneWayArea: " ",
                    startDate: startDate,
                    returnDate: returnDate,
                    infantCount: this.user.InfantCount,
                    childCount: this.user.ChildCount,
                    adultCount: this.user.AdultCount,
                    startTime1: this.user.startTime1,
                    arriveTime1: this.user.arriveTime1,
                    startTime2: this.user.startTime2,
                    arriveTime2: this.user.arriveTime2
                }
            });

        },
        testSend() {
            this.$router.push({
                name: 'Complete',
                params: {
                    korFirstName: this.user.korFirstName1,
                    korLastName: this.user.korLastName1,
                    engFirstName: this.engFirstName,
                    engLastName: this.engLastName,
                    gender: this.user.gender,
                    birthday: this.user.birthday,
                    seat: this.user.seat,
                    seatClass1: this.user.seatClass1,
                    seatClass2: this.user.seatClass2,
                    way: this.way,
                    flight1: this.user.flight1,
                    flight2: this.user.flight2,
                    fromArea: this.user.fromArea,
                    toArea: this.user.toArea,
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
                    arriveTime2: this.user.arriveTime2
                }
            });
        },
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
