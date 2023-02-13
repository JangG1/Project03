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
                승객 정보 <span v-if="chooseInfo.AdultCount > 1">1</span>
            </div>
            <div class="infoLeft">
                <span class="listLeft">한글 이름</span><br><br>
                <span class="listLeft">영어 이름</span><br><br>
                <span class="listLeft">생년 월일</span><br><br>
                <span class="listLeft">성별</span>
            </div>
            <div class="infoRight">

                <span class="listRight">{{userInfo.lastName+userInfo.firstName}}</span><br><br>
                <span class="listRight">{{engLastName}} {{engFirstName}}</span><br><br>
                <span class="listRight">{{userInfo.birthday}}</span><br><br>
                <span class="listRight">{{Gender(userInfo.gender)}}</span>
            </div>
        </div>
    </span>

    <span v-for="(pas,index) in parseInt(chooseInfo.AdultCount) + parseInt(chooseInfo.ChildCount) + parseInt(chooseInfo.InfantCount) - 1" :key="index">
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
            <span class="listRight">{{startInfo.flight1}}</span><br><br>
            <span class="listRight">{{chooseInfo.fromArea}} &nbsp;→&nbsp; {{chooseInfo.toArea}}</span><br><br>
            <span class="listRight">{{chooseInfo.startYear}}-{{chooseInfo.startMonth}}-{{chooseInfo.startDay}}({{chooseInfo.startWeek}})
                {{startInfo.startTime1}} ~ {{startInfo.arriveTime1}}</span><br><br>
            <span class="listRight" v-if="chooseInfo.InfantCount > 0">, 소아 {{chooseInfo.InfantCount}}명</span>
            <span class="listRight" v-if="chooseInfo.ChildCount > 0">, 유아 {{chooseInfo.ChildCount}}명</span>
            <span class="listRight">성인 {{chooseInfo.AdultCount}} 명 </span>
            <br><br>
            <span class="listRight">{{chooseInfo.seat}} {{startInfo.seatClass1}}</span>
        </div>
    </div>

    <div class="areaList" v-if="chooseInfo.returnYear != ' '">
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
            <span class="listRight">{{returnInfo.flight2}}</span><br><br>
            <span class="listRight">{{chooseInfo.toArea}} &nbsp;→&nbsp; {{chooseInfo.fromArea}}</span><br><br>
            <span class="listRight">{{chooseInfo.returnYear}}-{{chooseInfo.returnMonth}}-{{chooseInfo.returnDay}}({{chooseInfo.returnWeek}})
                {{returnInfo.startTime2}} ~ {{returnInfo.arriveTime2}}</span><br><br>
            <span class="listRight" v-if="chooseInfo.InfantCount > 0">, 소아 {{chooseInfo.InfantCount}}명</span>
            <span class="listRight" v-if="chooseInfo.ChildCount > 0">, 유아 {{chooseInfo.ChildCount}}명</span>
            <span class="listRight">성인 {{chooseInfo.AdultCount}}명</span>
            <br><br>
            <span class="listRight">{{chooseInfo.seat}} {{returnInfo.seatClass2}}</span>
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
            <span class="listRight">{{holdPoint - totalPoint}}</span><br><br>
        </div>
    </div>

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
            chooseInfo: this.$store.state.chooseInfo,
            startInfo: this.$store.state.startInfo,
            returnInfo: this.$store.state.returnInfo,
            userInfo: this.$store.state.userInfo,
            childCountView: false,
            infantCountView: false,
            holdPoint: this.$store.state.holdPoint,
            fullname: [],            
        }
    },
    props: {
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
        totalPoint: {
            type: Number
        },

    },
    methods: {
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
            alert("예약 되었습니다.")

            const startDate = this.chooseInfo.startYear + "-" + this.chooseInfo.startMonth + "-" + this.chooseInfo.startDay + '(' + this.chooseInfo.startWeek + ')';
            const returnDate = this.chooseInfo.returnYear + "-" + this.chooseInfo.returnMonth + "-" + this.chooseInfo.returnDay + '(' + this.chooseInfo.returnWeek + ')';
            
            let engName = this.engLastName + " " + this.engFirstName;
            let addPassKorName = this.addPassKorName;
            let addPassEngName = this.addPassEngName;
            let addPassGender = this.addPassGender;
            let addPassBirthday = this.addPassBirthday;

            if (this.chooseInfo.returnYear != " ") {
                this.way = "왕복"
            } else {
                this.way = "편도";
            }
           
             axios.post("/res/resPost", {
                    email: this.userInfo.email,
                    korName: this.userInfo.lastName + this.userInfo.firstName,
                    engName: engName,
                    gender: this.userInfo.gender,
                    birthday: this.userInfo.birthday,
                    addPassKorName: addPassKorName,
                    addPassEngName: addPassEngName,
                    addPassGender: addPassGender,
                    addPassBirthday: addPassBirthday,                    
                    seat: this.chooseInfo.seat,
                    seatClass1: this.startInfo.seatClass1,
                    seatClass2: this.returnInfo.seatClass2,
                    way: this.way,
                    flight1: this.startInfo.flight1,
                    flight2: this.returnInfo.flight2,
                    fromArea: this.chooseInfo.fromArea,
                    toArea: this.chooseInfo.toArea,
                    oneWayArea: "",
                    startDate: startDate,
                    returnDate: returnDate,
                    infantCount: this.chooseInfo.InfantCount,
                    childCount: this.chooseInfo.ChildCount,
                    adultCount: this.chooseInfo.AdultCount,
                    startTime1: this.startInfo.startTime1,
                    arriveTime1: this.startInfo.arriveTime1,
                    startTime2: this.returnInfo.startTime2,
                    arriveTime2: this.returnInfo.arriveTime2
                })
                .then(res => {
                    console.log(res)
                    console.log("보내짐")
                    this.$store.dispatch("engName", engName)
                    this.$store.dispatch("addPassKorName", addPassKorName)
                    this.$store.dispatch("addPassEngName", addPassEngName)
                    this.$store.dispatch("addPassGender", addPassGender)
                    this.$store.dispatch("addPassBirthday", addPassBirthday)
                    this.$router.push('Complete')
                })
                .catch(err => {
                    console.log(err)
                    console.log("안보내짐")
                })                

            let usePoint =  this.holdPoint - this.totalPoint;
            this.$store.dispatch("holdPoint", usePoint);
                    
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
