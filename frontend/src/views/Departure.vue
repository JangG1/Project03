<template>
<div data-aos="fade-up" data-aos-delay="300" data-aos-easing="ease-out">
    <div class="step">
        <div>① 검색</div> &nbsp; <div class="step2">❷ 항공편</div> &nbsp; <div>③ 결제</div>
    </div>
    <div class="schedule">
        여행 일정
    </div>
    <div class="info">
        <button type="button">
            <!-- 출발지 -> 도착지 -->
            <div class="info1">
                {{chooseInfo?.fromArea}} &nbsp;
                <span class="arrow">⇀</span>
                {{chooseInfo?.toArea}}
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <div class="info2">
                <img class="infoImg" src="../assets/calendar.png" width="25" height="25"> &nbsp;
                <!--출발일-->
                <span>{{chooseInfo?.startYear}}-{{chooseInfo?.startMonth}}-{{chooseInfo?.startDay}}({{chooseInfo?.startWeek}})</span>
                <!--도착일-->
                <span v-if="chooseInfo?.returnYear"> ~ {{chooseInfo?.returnYear}}-{{chooseInfo?.returnMonth}}-{{chooseInfo?.returnDay}}({{chooseInfo?.returnWeek}})</span>
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <!-- 승객 수 -->
            <div class="info3">
                <img class="infoImg" src="../assets/person.png" width="20" height="20"> &nbsp;
                성인 {{chooseInfo?.AdultCount}}명
                <span v-if="chooseInfo?.ChildCount > 0">
                    , 유아 {{chooseInfo?.ChildCount}}명
                </span>
                <span v-if="chooseInfo?.InfantCount > 0">
                    , 소아 {{chooseInfo?.InfantCount}}명
                </span>
            </div>
        </button>
    </div>
    <br>
    <div class="day-seat-select" v-for="(res, index) in st" :key="res">

        <!--출발시간 -> 도착시간 박스-->
        <div type="button" class="timeSelect" style="cursor: default">
            <div class="startTime">
                <h3>{{ res.start }}</h3>
                <div class="startArea"> {{ chooseInfo?.fromArea }}
                    <br><br>
                    <img src="../assets/Logo.png" class="flightLogo">
                    {{ res?.flight }}
                </div>
            </div>
            <img type="image" class="arrow2" src="../assets/arrow.jpg">
            <div class="arriveTime">
                <h3>{{ res?.arrive }} </h3>
                <div class="arriveArea">{{ chooseInfo?.toArea }}</div>
            </div><br>
        </div>

        <button type="button" class="seatSelect" @click="[selectStandard(index),selectFlight(index)]">
            <h3>{{chooseInfo?.seat}} {{res?.standard}}
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="check">
                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                </svg>
            </h3>
            <span>{{AddComma(res?.priceStandard)+ " 원"}}</span><br>
            <span>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</span>
        </button>
        <button type="button" class="seatSelect" @click="[selectFlex(index),selectFlight(index)]">
            <h3>{{chooseInfo?.seat}} {{res?.flex}}
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="check">
                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                </svg>
            </h3>
            <span>{{AddComma(res?.priceFlex)+ " 원"}}</span><br>
            <span>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</span>
        </button>

    </div>
    <div class="blank"></div>

    <!--Footer-->
    <div class="footNav">
        <span class="footNav1">예상 결제 금액</span>
        <span class="startPrice">{{AddComma(selectPrice) + " 원"}}&nbsp;</span>
        <button type="button" class="submitBtn" @click="submit()">다음 여정</button>
    </div>
</div>
</template>

<script>
import startTime from '../components/startTime.json';
import AOS from 'aos';
import "aos/dist/aos.css";

const st = startTime;

export default {
    name: 'HelloWorld',
    data() {
        return {
            st,
            start: '',
            arrive: '',
            price: '',
            selectPrice: 0,
            seatPrice: 0,
            chooseInfo: this.$store.state.chooseInfo,
        }
    },
    created() {
        AOS.init();
    },
    methods: {
        AddComma(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return num.toString().replace(regexp, ",");
        },
        selectStandard(index) {
            let priceStandard = this.st[index].priceStandard;
            let startTime = this.st[index].start;
            let arriveTime = this.st[index].arrive;
            let seatClass = this.st[index].standard;

            this.selectPrice = priceStandard;
            this.startTime = startTime;
            this.arriveTime = arriveTime;
            this.seatClass = seatClass;
        },
        selectFlex(index) {
            let priceFlex = this.st[index].priceFlex;
            let startTime = this.st[index].start;
            let arriveTime = this.st[index].arrive;
            let seatClass = this.st[index].flex;

            this.selectPrice = priceFlex;
            this.startTime = startTime;
            this.arriveTime = arriveTime;
            this.seatClass = seatClass;
        },
        selectFlight(index) {
            let flight = this.st[index].flight;

            this.flight1 = flight;
        },
        submit() {
            let startInfo = {};

            startInfo = {
                flight1: this.flight1,
                seatClass1: this.seatClass,
                startTime1: this.startTime,
                arriveTime1: this.arriveTime,
                startPrice: this.selectPrice,
            }

            if (this.selectPrice == 0) {
                return alert("항공편을 선택해주세요.")
            }

            this.$store.dispatch("startInfo", startInfo);

            if (this.chooseInfo.returnYear == "") {
                this.$router.push('Payment')
            } else if (this.chooseInfo.returnYear != "") {
                this.$router.push('Return')
            }

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


.flightLogo {
    width: 20px;    
}

.step {
    float: right;
    margin-right: 50px;
    margin-bottom: 30px;
    font-size: 20px;
    display: flex;
}

.step2 {
    color: teal;
}

.ver {    
    margin-left: 14px;
}

.schedule {
    color: black;
    color: teal;
    font-size: 24px;
    font-weight: 900;
    margin-left: 7%;
    margin-top: 2%;
}

.info {
    margin-left: auto;
    margin-right: auto;
    width: 1000px;    
}

.info button {
    width: 100%;
    height: 80px;
    background-color: white;
    font-size: 14px;
    border: 0.5px solid #999;
    display: flex;
    box-shadow: 4px 4px 4px rgb(68, 68, 68);
    float: left;
    margin-bottom: 60px;
}

.info button div {
    padding: 22px;
}

.info button:hover {
    border: 2px solid teal;
}

.info2{
    margin-top: 0.3%;
}

.info3{
    margin-top: 0.6%;
}

.day-seat-select {
    width: 900px;
    margin-left: auto;
    margin-right: auto;
    display: flex;
    margin-top: 40px;
}

.day-seat-select button {
    width: 40%;
    height: 120px;
    background-color: white;
}

.timeSelect {
    width: 40%;
    height: 120px;
    border: 1px solid;
}

.startTime {
    width: 50px;
    float: left;    
    margin-top: 8%;
    margin-left: 8%;        
}

.arriveTime {
    float: right;    
    margin-top: 8%;
    margin-right: 8%;
}

.startTime>h3,
.arriveTime>h3{
    font-size: 20px;
}

.startArea,
.arriveArea{    
    font-size: 11px;
    width: 70px;    
}

.timeSelect span {
    font-size: 14px;
    color: #999;
}

.arrow {    
    font-size: 22px;
    font-weight: 900;
    margin-right: 9px;        
    vertical-align: middle;
}

.arrow2 {
    width: 100px;
    height: 15px;
    margin-top: 8%;
    margin-left: 7%;
}

.seatSelect {
    border: 1px solid;
    border-top: 4px solid black;
    opacity: 0.6;
    transition: 0.3s;
    font-size: 16px;
}

.seatSelect>h3{
    font-size: 20px;
    margin-left: 25px;
}

.seatSelect:hover {
    border: 4px solid teal;
    border-top: 4px solid black;
}

.seatSelect:focus {
    width: 400px;
    transform: translate(-0.5%, -10%);
    border-radius: 15px;
    background-color: teal;
    box-shadow: 4px 4px 10px rgb(68, 68, 68);
    color: white;
    opacity: 1;
    font-size: 16px;
}

.resPriceStandard {
    border: none;
}

.check {
    float: right;
    color: white;
}

.footNav {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    height: 55px;
    padding: 1rem;
    color: white;
    background: teal;
    font-weight: bold;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 14px;
    box-shadow: 2px 2px 20px rgb(1, 83, 83);
}

.footNav1 {
    margin-left: 30px;    
}

.startPrice {
    font-size: 14px;
    margin-left: 70%;
}

.submitBtn {
    width: 12%;
    height: 250%;
    font-size: 16px;    
    border-radius: 4px;
    color: white;
    background: teal;
    border: 1px solid white;
}
</style>
