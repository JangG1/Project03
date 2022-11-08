<template>
<div>
    <div class="right">
        <div class="payInfo">
            <h4>항공 운송료 </h4>
            <span>운임</span><span class="price">{{Fare(startPrice)}}</span><br>
            <span>유류할증료</span><span class="price">{{Fuel(startPrice)}}</span><br>
            <span>세금, 수수료 및 기타 요금</span><span class="price">{{Tax(startPrice)}}</span>
        </div>
        <div class="totalPrice">
            <span class="total">총액</span><span class="price">{{AddComma(startPrice)}}</span>
        </div>

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
                {{Format(startDate)}} ~ {{Format(returnDate)}}
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <div class="info3">
                <img class="infoImg" src="../assets/person.png" width="20" height="20"> &nbsp;
                {{AdultCount}}
                <span>
                    {{ChildCount}}
                </span>
                <span>
                    {{InfantCount}}
                </span>
            </div>
        </button>
    </div>
    <br>
    <p class="startInfoTitle">여정 정보</p>
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
                {{Format(startDate)}} {{startTime1}} ~ {{arriveTime1}} &nbsp; {{seat}} {{seatClass1}}
            </div>

        </button>
    </div>

    <br>

    <div class="arriveInfo">
        <button type="button">
            <div>
                오는 편
            </div>
            <span>│</span>
            <div class="startInfo1">
                {{fromArea}} &nbsp;
                →&nbsp;
                {{toArea}}
            </div>
            <span>│</span>
            <div class="startInfo2">
                {{Format(returnDate)}} {{startTime2}} ~ {{arriveTime2}} &nbsp; {{seat}} {{seatClass2}}
            </div>

        </button>
    </div>
    <!--PayList-->

</div>

<!--footer-->
<div class="footNav">
    <span>예상 결제 금액</span>
    <span class="startPrice">{{AddComma(startPrice)}}</span>
    <button type="button" class="submitBtn" @click="submit()">결제하기</button>
</div>
</template>

<script>
export default {
    name: 'HelloWorld',
    components: {},
    data() {
        return {
            start: '',
            arrive: '',
            price: '',
            selectPrice: this.startPrice,
            seatPrice: 0,

        }
    },
    props: {
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
        startDate: {
            type: String
        },
        returnDate: {
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
        Fare(value){
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            let Fare = Math.floor(parseInt(value) * 0.7) + " 원";
            return Fare.toString().replace(regexp, ",");
        },
        Fuel(value){
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            let Fuel = Math.floor(parseInt(value) * 0.2) + " 원";
            return Fuel.toString().replace(regexp, ",");
        },
        Tax(value){
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            let Tax = Math.floor(parseInt(value) * 0.1) + " 원";
            return Tax.toString().replace(regexp, ",");
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

        submit() {

            this.$router.push({
                name: 'Payment',
                params: {
                    fromArea: this.fromArea,
                    toArea: this.toArea,
                    seat: this.seat,
                    seatClass1: this.seatClass,
                    seatClass2: this.seatClass2,
                    startDate: this.startDate,
                    returnDate: this.returnDate,
                    AdultCount: this.AdultCount,
                    ChildCount: this.ChildCount,
                    InfantCount: this.InfantCount,
                    startTime1: this.startTime,
                    arriveTime1: this.arriveTime,
                    startTime2: this.startTime2,
                    arriveTime2: this.arriveTime2,
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
h4{
    color: black;
    padding-bottom: 10px;
}
.caption {
    display: flex;
    margin-left: 11%;
}

.ver {
    margin-top: 25px;
    margin-left: 14px;
}

.info {
    margin-left: auto;
    margin-right: auto;
    width: 1380px;
    display: flex;
}

.info button {
    width: 100%;
    height: 100px;
    background-color: white;
    font-size: 24px;
    border: 0.5px solid #999;
    display: flex;
    box-shadow: 4px 4px 4px rgb(68, 68, 68);

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

.startInfoTitle{
    margin-left: 5%;
    color: black;
    font-size: 30px;
}

.startInfo,.arriveInfo {
    margin-left: auto;
    margin-right: auto;
    width: 1200px;
    display: flex;
}

.startInfo button,
.arriveInfo button {
    color: white;
    width: 100%;
    height: 90px;
    background-color: rgba(34, 168, 168, 0.689);
    font-size: 20px;
    border: 0.5px solid #999;
    display: flex;
    box-shadow: 4px 4px 4px rgb(68, 68, 68);

}

.startInfo button div
,.arriveInfo button div {
    padding: 30px;
}

.startInfo span,
.arriveInfo span{
    padding-top: 22px;
    font-size: 30px;
}

.day-seat-select {
    width: 1500px;
    margin-left: auto;
    margin-right: auto;
    display: flex;
    padding: 40px;
}

.day-seat-select button {
    width: 40%;
    height: 150px;
    background-color: white;
    box-shadow: 4px 4px 8px rgb(68, 68, 68);
}

.timeSelect {
    width: 40%;
    height: 150px;
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
    width: 140px;
    height: 30px;
    margin-top: 10%;

}

.seatSelect {
    border: 1px solid;
    border-top: 4px solid black;
    opacity: 0.6;
    transition: 0.3s;
}

.seatSelect:hover {
    border: 4px solid teal;
    border-top: 4px solid black;

}

.seatSelect:focus {
    width: 600px;
    height: 150px;
    transform: translate(-0.5%, -10%);
    border-radius: 15px;
    background-color: teal;
    box-shadow: 4px 4px 10px rgb(68, 68, 68);
    color: white;
    opacity: 1;

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

.right{
    float: right;    
    padding-left: 25px;
    width: 450px;
    height: 1000px;
}

.payInfo{
    border: 0.1px solid rgb(217, 217, 217);
    border-left: none;
    border-right: none;
    width: 380px;
    height: 200px;
    padding: 30px;
    font-size: 18px;
    color: rgb(87, 86, 86);
}

.totalPrice{
    border: 0.1px solid rgb(217, 217, 217);
    border-left: none;
    border-right: none;
    width: 380px;
    height: 110px;
    padding: 30px;
    padding-top: 40px;
    font-size: 24px;    
}

.price{
    float: right;
}
</style>
