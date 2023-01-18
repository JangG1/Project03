<template>
<div>
    <div class="step">
        <div>① 검색</div> &nbsp; <div class="step2">❷ 항공편</div> &nbsp; <div>③ 결제</div>
    </div>
    <div class="schedule">
        여행 일정
    </div>
    <div class="info">
        <button type="button">
            <div class="info1">
                {{fromArea}} &nbsp;
                <img src="../assets/arrow2.jpg"> &nbsp;
                {{toArea}}
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <div class="info2">
                <img class="infoImg" src="../assets/calendar.png" width="30" height="30"> &nbsp;
                <span>{{startYear}}-{{startMonth}}-{{startDay}}({{startWeek}})</span> 
                <span v-if="returnYear"> ~ {{returnYear}}-{{returnMonth}}-{{returnDay}}({{returnWeek}})</span>
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <div class="info3">
                <img class="infoImg" src="../assets/person.png" width="20" height="20"> &nbsp;
                성인 {{AdultCount}}명
                <span v-if="ChildCount > 0">
                    , 유아 {{ChildCount}}명
                </span>
                <span v-if="InfantCount > 0">
                    , 소아 {{InfantCount}}명
                </span>
            </div>
        </button>
    </div>
    <br>
    <div class="startInfo">
        <button type="button">
            <div>
                가는 편
            </div>
            <span>│</span>
            <div class="startInfo1">
                {{fromArea}} &nbsp;
                →&nbsp;
                {{toArea}}
            </div>
            <span>│</span>
            <div class="startInfo2">
                {{startDate}} {{startTime1}} ~ {{arriveTime1}} &nbsp; {{seat}} {{seatClass1}}
            </div>

        </button>
    </div>
    <div class="day-seat-select" v-for="(res, index) in at" :key="res">

        <div type="button" class="timeSelect" style="cursor: default">
            <div class="startTime">
                <h3>{{res.start}}</h3>
                <span> {{ fromArea }}</span>
                <div class="flight">
                    <img src="@/assets/Logo.png" class="flightLogo">
                    {{ res.flight }}
                </div>
            </div>
            <img type="image" class="arrow2" src="../assets/arrow.jpg">
            <div class="arriveTime">
                <h3>{{res.arrive}} </h3>
                <span>{{ toArea }}</span>
            </div>
        </div>

        <button type="button" class="seatSelect" @click="[selectStandard(index),selectFlight(index)]">
            <h3>{{seat}} {{res.standard}}
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="check">
                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                </svg>
            </h3>
            <span>{{AddComma(res.priceStandard)+ " 원"}}</span><br>
            <span>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</span>
        </button>
        <button type="button" class="seatSelect" @click="[selectFlex(index),selectFlight(index)]">
            <h3>{{seat}} {{res.flex}}
                <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="check">
                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                </svg>
            </h3>
            <span>{{AddComma(res.priceFlex)+ " 원"}}</span><br>
            <span>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</span>
        </button>

    </div>

    <div class="blank"></div>

    <!--Footer-->
    <div class="footNav">
        <span class="footNav1">예상 결제 금액</span>
        <span class="startPrice">{{AddComma(selectPrice)+ " 원"}}&nbsp;</span>
        <button type="button" class="submitBtn" @click="submit()">다음 여정</button>
    </div>
</div>
</template>

<script>
import arrivalTime from '../components/arrivalTime.json';

const at = arrivalTime;

export default {
    name: 'HelloWorld',
    components: {

    },
    data() {
        return {
            at,
            start: '',
            arrive: '',
            price: '',
            selectPrice: this.startPrice,
            seatPrice: 0,

        }
    },
    props: {
        flight1: {
            type: String,
            default: ''
        },
        fromArea: {
            type: String,
            default: ''
        },
        toArea: {
            type: String,
            default: ''
        },
        seat: {
            type: String,
            default: ''
        },
        seatClass1: {
            type: String,
            default: ''
        },
        seatClass2: {
            type: String,
            default: ''
        },
        startYear: {
            type: String,
            default: ''
        },
        startMonth: {
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
        startDay: {
            type: String,
            default: ''
        },
        startWeek: {
            type: String,
            default: ''
        },
        nDate: {
            type: String
        },
        AdultCount: {
            type: Number
        },
        ChildCount: {
            type: Number
        },
        InfantCount: {
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
        AddComma(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return num.toString().replace(regexp, ",");
        },
        selectStandard(index) {
            let priceStandard = this.at[index].priceStandard;
            let startTime = this.at[index].start;
            let arriveTime = this.at[index].arrive;
            let seatClass = this.at[index].standard;

            this.selectPrice = this.startPrice;
            this.selectPrice = parseInt(this.selectPrice) + parseInt(priceStandard)
            this.stTime2 = startTime;
            this.arrTime2 = arriveTime;
            this.Class2 = seatClass;

        },
        selectFlex(index) {
            let priceFlex = this.at[index].priceFlex;
            let startTime = this.at[index].start;
            let arriveTime = this.at[index].arrive;
            let seatClass = this.at[index].flex;

            this.selectPrice = this.startPrice;
            this.selectPrice = parseInt(this.selectPrice) + parseInt(priceFlex)
            this.stTime2 = startTime;
            this.arrTime2 = arriveTime;
            this.Class2 = seatClass;
        },
        selectFlight(index) {
            console.log(this.at[index].flight)

            let flight = this.at[index].flight;

            this.flight2 = flight;
        },
        submit() {
            this.$router.push({
                name: 'Payment',
                params: {
                    flight1: this.flight1,
                    flight2: this.flight2,
                    fromArea: this.fromArea,
                    toArea: this.toArea,
                    seat: this.seat,
                    seatClass1: this.seatClass1,
                    seatClass2: this.Class2,
                    startYear: this.startYear,
                    startMonth: this.startMonth,
                    startDay: this.startDay,
                    startWeek: this.startWeek,
                    returnYear: this.returnYear,
                    returnMonth: this.returnMonth,
                    returnDay: this.returnDay,
                    returnWeek: this.returnWeek,
                    AdultCount: this.AdultCount,
                    ChildCount: this.ChildCount,
                    InfantCount: this.InfantCount,
                    startTime1: this.startTime1,
                    arriveTime1: this.arriveTime1,
                    startTime2: this.stTime2,
                    arriveTime2: this.arrTime2,
                    startPrice: this.selectPrice,
                }
            });

        }

    },
    mounted() {

    }
}
</script>

<style>
.blank{
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

.schedule {
    color: teal;
    font-size: 28px;
    font-weight: 900;
    margin-left: 15%;
    margin-top: 2%;
}

.info {
    margin-left: auto;
    margin-right: auto;
    width: 1380px;
    margin-top: 20px;
}

.info button {
    width: 100%;
    height: 100px;
    background-color: white;
    font-size: 24px;
    border: 0.5px solid #999;
    display: flex;
    box-shadow: 4px 4px 4px rgb(68, 68, 68);
    float: left;
}

.info button div {
    padding: 30px;
}

.infoImg {
    margin-left: 14px;
    margin-bottom: 4px;
}

.info button:hover {
    border: 2px solid teal;
}

.startInfo {
    margin-left: auto;
    margin-right: auto;
    width: 1250px;
    display: flex;
    
}

.startInfo button {
    color: white;
    width: 100%;
    height: 90px;
    background-color: rgba(34, 168, 168, 0.689);
    font-size: 20px;
    border: 0.5px solid #999;
    display: flex;
    box-shadow: 4px 4px 4px rgb(68, 68, 68);
    
}

.startInfo span {
    padding-top: 22px;
    font-size: 30px;
}

.startInfo button div {
    padding: 30px;
}

.startInfoImg {
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

.footNav {
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

.footNav1 {
    margin-left: 30px;
}

.submitBtn {
    width: 15%;
    height: 130%;
    font-size: 25px;
    border-radius: 4px;
    color: white;
    background: teal;
    border: 1px solid white;
}

.startPrice {
    font-size: 20px;
    margin-left: 70%;
}
</style>
