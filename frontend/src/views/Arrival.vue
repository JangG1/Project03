<template>
<div>
    <div class="step">
        <div>① 검색</div> &nbsp; <div class="step2">❷ 항공편</div> &nbsp; <div>③ 결제</div>
    </div>
    <div class="arrSchedule">
        여행 일정
    </div>
    <div class="arrInfo">
        <button type="button">
            <div class="info1">
                {{chooseInfo.fromArea}} &nbsp;
                <img src="../assets/arrow2.jpg"> &nbsp;
                {{chooseInfo.toArea}}
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <div class="info2">
                <img class="arrInfoImg" src="../assets/calendar.png" width="30" height="30"> &nbsp;
                <span>{{chooseInfo.startYear}}-{{chooseInfo.startMonth}}-{{chooseInfo.startDay}}({{chooseInfo.startWeek}})</span>
                <span v-if="chooseInfo.returnYear"> ~ {{chooseInfo.returnYear}}-{{chooseInfo.returnMonth}}-{{chooseInfo.returnDay}}({{chooseInfo.returnWeek}})</span>
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <div class="info3">
                <img class="arrInfoImg" src="../assets/person.png" width="20" height="20"> &nbsp;
                성인 {{chooseInfo.AdultCount}}명
                <span v-if="chooseInfo.ChildCount > 0">
                    , 유아 {{chooseInfo.ChildCount}}명
                </span>
                <span v-if="chooseInfo.InfantCount > 0">
                    , 소아 {{chooseInfo.InfantCount}}명
                </span>
            </div>
        </button>
    </div>
    <br>
    <div class="arrStartInfo">
        <button type="button">
            <div>
                가는 편
            </div>
            <span>│</span>
            <div class="startInfo1">
                {{chooseInfo.fromArea}} &nbsp;
                →&nbsp;
                {{chooseInfo.toArea}}
            </div>
            <span>│</span>
            <div class="startInfo2">
                {{chooseInfo.startDate}} {{startInfo.startTime1}} ~ {{startInfo.arriveTime1}} &nbsp; {{chooseInfo.seat}} {{startInfo.seatClass1}}
            </div>

        </button>
    </div>
    <div class="day-seat-select" v-for="(res, index) in at" :key="res">

        <div type="button" class="timeSelect" style="cursor: default">
            <div class="startTime">
                <h3>{{res.start}}</h3>
                <span> {{ chooseInfo.fromArea }}</span>
                <div class="flight">
                    <img src="@/assets/Logo.png" class="flightLogo">
                    {{ res.flight }}
                </div>
            </div>
            <img type="image" class="arrow2" src="../assets/arrow.jpg">
            <div class="arriveTime">
                <h3>{{res.arrive}} </h3>
                <span>{{ chooseInfo.toArea }}</span>
            </div>
        </div>

        <button type="button" class="seatSelect" @click="[selectStandard(index),selectFlight(index)]">
            <h3>{{chooseInfo.seat}} {{res.standard}}
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="check">
                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                </svg>
            </h3>
            <span>{{AddComma(res.priceStandard)+ " 원"}}</span><br>
            <span>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</span>
        </button>
        <button type="button" class="seatSelect" @click="[selectFlex(index),selectFlight(index)]">
            <h3>{{chooseInfo.seat}} {{res.flex}}
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="check">
                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                </svg>
            </h3>
            <span>{{AddComma(res.priceFlex)+ " 원"}}</span><br>
            <span>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</span>
        </button>

    </div>

    <div class="blank"></div>


    <div class="arrFootNav">
        <span class="arrFootNav1">예상 결제 금액</span>
        <span class="arrStartPrice">{{AddComma(selectPrice)+ " 원"}}&nbsp;</span>
        <button type="button" class="arrSubmitBtn1" @click="submit()">비회원 결제 하기</button>
        <button type="button" class="arrSubmitBtn2" @click="loginSubmit()">회원 결제 하기</button>
    </div>
</div>
<div v-if="!isLogin">
    <LoginModal2 @closeModal="loginModal = false" :loginModal="loginModal" />
</div>
</template>

<script>
import arrivalTime from '../components/arrivalTime.json';
import LoginModal2 from "@/components/LoginModal2.vue";

const at = arrivalTime;

export default {
    name: 'HelloWorld',
    components: {
        LoginModal2
    },
    data() {
        return {
            at,
            seatClass2: '',
            startTime2: '',
            arriveTime2: '',
            selectPrice: this.$store.state.startInfo.startPrice,
            loginModal: false,
            chooseInfo : this.$store.state.chooseInfo,
            startInfo : this.$store.state.startInfo
        }
    },
    props: {
    },
    methods: {
        AddComma(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return num.toString().replace(regexp, ",");
        },
        selectStandard(index) {
            let priceStandard = this.at[index].priceStandard;
            let startTime = this.at[index].start;
            let arriveTime = this.at[index].arrive;
            let seatClass = this.at[index].standard;
            
            this.selectPrice = this.$store.state.startInfo.startPrice;
            this.selectPrice = parseInt(this.selectPrice) + parseInt(priceStandard)
            this.startTime2 = startTime;
            this.arriveTime2 = arriveTime;
            this.seatClass2 = seatClass;

        },
        selectFlex(index) {
            let priceFlex = this.at[index].priceFlex;
            let startTime = this.at[index].start;
            let arriveTime = this.at[index].arrive;
            let seatClass = this.at[index].flex;

            this.selectPrice = this.$store.state.startInfo.startPrice;
            this.selectPrice = parseInt(this.selectPrice) + parseInt(priceFlex)
            this.startTime2 = startTime;
            this.arriveTime2 = arriveTime;
            this.seatClass2 = seatClass;
        },
        selectFlight(index) {
            console.log(this.at[index].flight)

            let flight = this.at[index].flight;

            this.flight2 = flight;
        },
        loginSubmit() {
            if (this.flight2 == null) {
                return alert("도착지를 선택해주세요.")
            }

            if (!this.$store.state.isLogin) {
                alert("로그인을 진행해 주세요.")                
                return this.loginModal = true;
            }

            if (this.$store.state.isLogin == true) {
                this.submit();
            }
        },
        submit() {
            let returnInfo = {};

            if (this.flight2 == null) {
                return alert("도착지를 선택해주세요.")
            }

            returnInfo = {
                flight2: this.flight2,
                seatClass2: this.seatClass2,
                startTime2: this.startTime2,
                arriveTime2: this.arriveTime2,
                totalPrice: this.selectPrice,
            }

            this.$store.dispatch("returnInfo", returnInfo);        

            this.$router.push('Payment')
        }

    },
    mounted() {

    }
}
</script>

<style>
.blank {
    height: 150px;
}

.flight {
    margin-top: 15px;
    color: #999;
}

.flightLogo {
    width: 30px;
    height: 30px;
}

.step {
    float: right;
    margin-right: 200px;
    margin-bottom: 30px;
    font-size: 30px;
    display: flex;
}

.step2 {
    color: teal;
}

.ver {
    margin-top: 25px;
    margin-left: 14px;
}

.arrSchedule {
    color: teal;
    font-size: 28px;
    font-weight: 900;
    margin-left: 15%;
    margin-top: 2%;
}

.arrInfo {
    margin-left: auto;
    margin-right: auto;
    width: 1380px;
    margin-top: 20px;    
}

.arrInfo button {
    width: 100%;
    height: 100px;
    background-color: white;
    font-size: 24px;
    border: 0.5px solid #999;
    display: flex;
    box-shadow: 4px 4px 4px rgb(68, 68, 68);
    float: left;
}

.arrInfo button div {
    padding: 30px;
}

.arrInfoImg {
    margin-left: 14px;
    margin-bottom: 4px;
}

.arrInfo button:hover {
    border: 2px solid teal;
}

.arrStartInfo {
    margin-left: auto;
    margin-right: auto;
    width: 1250px;
    display: flex;    
}

.arrStartInfo button {
    color: white;
    width: 100%;
    height: 90px;
    background-color: rgba(34, 168, 168, 0.689);
    font-size: 20px;
    border: 0.5px solid #999;
    display: flex;
    box-shadow: 4px 4px 4px rgb(68, 68, 68);
    margin-top: 30px;
}

.arrStartInfo span {
    padding-top: 22px;
    font-size: 30px;
}

.arrStartInfo button div {
    padding: 30px;
}

.arrStartInfoImg {
    margin-left: 14px;
}

.day-seat-select {
    width: 1500px;
    margin-left: auto;
    margin-right: auto;
    display: flex;
    margin-top: 40px;
}

.day-seat-select button {
    width: 40%;
    height: 180px;
    background-color: white;

}

.timeSelect {
    width: 40%;
    height: 180px;
    border: 1px solid;
}

.startTime {
    width: 100px;
    float: left;
    margin-top: 8%;
    margin-left: 8%;

}

.arriveTime {
    float: right;
    margin-top: 8%;
    margin-right: 5%;
}

.timeSelect span {
    font-size: 20px;
    color: #999;
}

.arrow2 {
    width: 190px;
    height: 30px;
    margin-top: 10%;

}

.seatSelect {
    border: 1px solid;
    border-top: 4px solid black;
    opacity: 0.6;
    transition: 0.3s;
    font-size: 22px;
}

.seatSelect:hover {
    border: 4px solid teal;
    border-top: 4px solid black;

}

.seatSelect:focus {
    width: 600px;
    transform: translate(-0.5%, -10%);
    border-radius: 15px;
    background-color: teal;
    box-shadow: 4px 4px 10px rgb(68, 68, 68);
    color: white;
    opacity: 1;
    font-size: 22px;
}

.resPriceStandard {
    border: none;
}

.check {
    float: right;
    color: white;
}

.arrFootNav {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    height: 75px;
    padding: 1rem;
    color: white;
    background: teal;
    font-weight: bold;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 20px;
    box-shadow: 2px 2px 20px rgb(1, 83, 83);
}

.arrFootNav1 {
    margin-left: 30px;
}

.arrSubmitBtn1 {
    width: 240px;
    height: 130%;
    font-size: 25px;
    border-radius: 4px;
    color: white;
    background: teal;
    border: 1px solid white;
    margin-left:1%;
}

.arrSubmitBtn2 {
    width: 240px;
    height: 130%;
    font-size: 25px;
    font-weight: 900;
    border-radius: 4px;
    color: rgb(6, 165, 165);
    background: white;
    border: 3px solid rgba(34, 168, 168, 0.689);
    margin-left:1%;
}

.arrStartPrice {
    font-size: 20px;
    margin-left: 62%;
}
</style>
