<template>
<div class="">
    <div class="infoTitle">
        <span class="title" style="font-size: 30px;">여행 예약 정보</span>{{ addAdult }}
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
                <span class="listRight">{{korLastName}} {{korFirstName}}</span><br><br>
                <span class="listRight">{{engLastName}} {{engFirstName}}</span><br><br>
                <span class="listRight">{{birthday}}</span><br><br>
                <span class="listRight">{{gender}}</span>
            </div>
        </div>
    </span>

    <!--추가 승객 정보(성인)-->
    <span v-for="(pas,index) in parseInt(chooseInfo.AdultCount) - 1" :key="index">
        <div class="infoList">
            <div class="title2">
                [성인] 승객 정보 {{ index + 2 }}
            </div>
            <div class="infoLeft">
                <span class="listLeft">한글 이름</span><br><br>
                <span class="listLeft">영어 이름</span><br><br>
                <span class="listLeft">생년 월일</span><br><br>
                <span class="listLeft">성별</span>
            </div>
            <div class="infoRight">
                <span class="listRight">{{addAdult[index].korName}}</span><br><br>
                <span class="listRight">{{addAdult[index].engName}}</span><br><br>
                <span class="listRight">{{addAdult[index].gender}}</span><br><br>
                <span class="listRight">{{addAdult[index].birthday}}</span>
            </div>
        </div>
    </span>

    <!-- 추가 승객 정보(유아) -->
    <span v-for="(pas,index) in parseInt(chooseInfo.ChildCount)" :key="index">
        <div class="infoList">
            <div class="title2">
                [유아] 승객 정보 {{ index + 1 }}
            </div>
            <div class="infoLeft">
                <span class="listLeft">한글 이름</span><br><br>
                <span class="listLeft">영어 이름</span><br><br>
                <span class="listLeft">생년 월일</span><br><br>
                <span class="listLeft">성별</span>
            </div>
            <div class="infoRight">
                <span class="listRight">{{addChild[index].korName}} </span><br><br>
                <span class="listRight">{{addChild[index].engName}} </span><br><br>
                <span class="listRight">{{addChild[index].gender}}</span><br><br>
                <span class="listRight">{{addChild[index].birthday}}</span>
            </div>
        </div>
    </span>

    <!-- 추가 승객 정보(소아)-->
    <span v-for="(pas,index) in parseInt(chooseInfo.InfantCount)" :key="index">
        <div class="infoList">
            <div class="title2">
                [소아] 승객 정보 {{ index + 1 }}
            </div>
            <div class="infoLeft">
                <span class="listLeft">한글 이름</span><br><br>
                <span class="listLeft">영어 이름</span><br><br>
                <span class="listLeft">생년 월일</span><br><br>
                <span class="listLeft">성별</span>
            </div>
            <div class="infoRight">
                <span class="listRight">{{addInfant[index].korName}}</span><br><br>
                <span class="listRight">{{addInfant[index].engName}}</span><br><br>
                <span class="listRight">{{addInfant[index].gender}}</span><br><br>
                <span class="listRight">{{addInfant[index].birthday}}</span>
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
    <button type="button" class="reserBtn" @click="test()">test</button>
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
        passEmail1: {
            type: String,
            default: ''
        },
        passEmail2: {
            type: String,
            default: ''
        },
        korLastName: {
            type: String,
            default: ''
        },
        korFirstName: {
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
        birthday: {
            type: String,
            default: ''
        },
        gender: {
            type: String,
            default: ''
        },
        addAdult: {
            type: String,
            default: ''
        },
        addChild: {
            type: String,
            default: ''
        },
        addInfant: {
            type: String,
            default: ''
        },
        totalPoint: {
            type: Number
        },

    },
    methods: {
        test() {
            console.log("test")
            const adultKorName = [];
            const adultEngName = [];
            const adultBirthday = [];
            const adultGender = [];

            const childKorName = [];
            const childEngName = [];
            const childBirthday = [];
            const childGender = [];
            
            const infantKorName = [];
            const infantEngName = [];
            const infantBirthday = [];
            const infantGender = [];

            for (let i = 0; i < this.addAdult.length; i++) {
                console.log(i)
                adultKorName.push(this.addAdult[i].korName);
                adultEngName.push(this.addAdult[i].engName);
                adultBirthday.push(this.addAdult[i].birthday);
                adultGender.push(this.addAdult[i].gender);
            }

            for (let i = 0; i < this.addChild.length; i++) {
                console.log(i)
                adultKorName.push(this.addChild[i].korName);
                adultEngName.push(this.addChild[i].engName);
                adultBirthday.push(this.addChild[i].birthday);
                adultGender.push(this.addChild[i].gender);
            }

            for (let i = 0; i < this.addInfant.length; i++) {
                console.log(i)
                adultKorName.push(this.addInfant[i].korName);
                adultEngName.push(this.addInfant[i].engName);
                adultBirthday.push(this.addInfant[i].birthday);
                adultGender.push(this.addInfant[i].gender);
            }

            axios.post("/res/resPost/addPas", {
                adultKorName: adultKorName,
                adultEngName: adultEngName,
                adultBirthday: adultBirthday,
                adultGender: adultGender,

                childKorName: childKorName,
                childEngName: childEngName,
                childBirthday: childBirthday,
                childGender: childGender,

                infantKorName: infantKorName,
                infantEngName: infantEngName,
                infantBirthday: infantBirthday,
                infantGender: infantGender,
                })
                .then(res => {
                    console.log(res)
                    console.log("추가 승객 보내짐")
                })
                .catch(err => {
                    console.log(err)
                    console.log("추가 승객 안보내짐")
                })
        },
        closeModal() {
            this.$emit('closeModal')
        },
        submit() {
            const startDate = this.chooseInfo.startYear + "-" + this.chooseInfo.startMonth + "-" + this.chooseInfo.startDay + '(' + this.chooseInfo.startWeek + ')';
            const returnDate = this.chooseInfo.returnYear + "-" + this.chooseInfo.returnMonth + "-" + this.chooseInfo.returnDay + '(' + this.chooseInfo.returnWeek + ')';

            let korName = this.korLastName + this.korFirstName;
            let engName = this.engLastName + this.engFirstName;

            let addAdult = this.addAdult
            let addChild = this.addChild
            let addInfant = this.addInfant

            let infantCount = this.chooseInfo.InfantCount
            let childCount = this.chooseInfo.ChildCount
            let adultCount = this.chooseInfo.AdultCount

            let trigger = adultCount + childCount + infantCount;

            if (this.chooseInfo.returnYear != " ") {
                this.way = "왕복"
            } else {
                this.way = "편도";
            }

            let email = "";

            if (this.$store.state.isLogin == false) {
                email = this.passEmail1 + "@" + this.passEmail2;
            } else if (this.$store.state.isLogin == true) {
                email = this.userInfo.email;
            }

            let userInfo = [{
                korName: korName
            }, {
                engName: engName
            }, {
                gender: this.gender
            }, {
                birthday: this.birthday
            }];

            axios.post("/res/resPost", {
                    email: email,
                    korName: korName,
                    engName: engName,
                    gender: this.gender,
                    birthday: this.birthday,
                    addAdult: addAdult,
                    addChild: addChild,
                    addInfant: addInfant,
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
                    console.log("예약자 정보 보내짐")
                    if (trigger > 1) {
                        this.submit2();
                    } else {
                        let usePoint = this.holdPoint - this.totalPoint;
                        this.$store.dispatch("holdPoint", usePoint);
                        this.$store.dispatch("setUserInfo2", userInfo);
                        alert("예약이 완료 되었습니다.")
                        this.$router.push('Complete');
                    }
                })
                .catch(err => {
                    console.log(err)
                    console.log("예약자 정보 안보내짐")
                })
        },
        submit2() {

            let addAdult = this.addAdult
            let addChild = this.addChild
            let addInfant = this.addInfant

            let korName = this.korLastName + this.korFirstName;
            let engName = this.engLastName + this.engFirstName;

            let userInfo = [{
                korName: korName
            }, {
                engName: engName
            }, {
                gender: this.gender
            }, {
                birthday: this.birthday
            }];

            axios.post("/res/resPost/addPas", {
                    addAdult: addAdult,
                    addChild: addChild,
                    addInfant: addInfant,
                })
                .then(res => {
                    console.log(res)
                    console.log("추가 승객 보내짐")
                })
                .catch(err => {
                    console.log(err)
                    console.log("추가 승객 안보내짐")
                })

            let usePoint = this.holdPoint - this.totalPoint;
            this.$store.dispatch("holdPoint", usePoint);
            this.$store.dispatch("setUserInfo2", userInfo);
            this.$store.dispatch("addAdult", addAdult);
            this.$store.dispatch("addChild", addChild);
            this.$store.dispatch("addInfant", addInfant);
            alert("예약이 완료 되었습니다.")
            this.$router.push('Complete');
        },
    },
    mounted() {},
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
