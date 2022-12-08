<template>
<div>
    <div class="pStep">
        <div>① 검색</div> &nbsp; <div>② 항공편</div> &nbsp; <div class="step3">❸ 결제</div>
    </div>
    <div class="schedule">
        여행 일정
    </div>

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

    <div class="pInfo">
        <button type="button">
            <div class="pInfo1">
                {{fromArea}} &nbsp;
                <img src="../assets/arrow2.jpg"> &nbsp;
                {{toArea}}
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <div class="pInfo2">
                <img class="infoImg" src="../assets/calendar.png" width="30" height="30"> &nbsp;
                <span>{{Format(startDate)}}</span> <span v-show="returnDate.length > 1"> ~ {{Format(returnDate)}}</span>
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <div class="pInfo3">
                <img class="pInfoImg" src="../assets/person.png" width="20" height="20"> &nbsp;
                {{AdultCount}}
                <span v-show="InfantCount.substr(4,2) > 0">
                    {{ChildCount}}
                </span>
                <span v-show="InfantCount.substr(4,2) > 0">
                    {{InfantCount}}
                </span>
            </div>
        </button>
    </div>

    <br><br>
    <div class="startInfoTitle">
        여정 정보
    </div>
    <div class="pStartInfo">
        <button type="button">
            <div>
                가는 편
            </div>
            <span>│</span>
            <div class="pStartInfo1">
                {{fromArea}} &nbsp;
                →&nbsp;
                {{toArea}}
            </div>
            <span>│</span>
            <div class="pStartInfo2">
                {{Format(startDate)}} {{startTime1}} ~ {{arriveTime1}} &nbsp; {{seat}} {{seatClass1}}
            </div>

        </button>
    </div>

    <br>

    <div class="pArriveInfo" v-show="returnDate.length > 1">
        <button type="button">
            <div>
                오는 편
            </div>
            <span>│</span>
            <div class="pArriveInfo1">
                {{fromArea}} &nbsp;
                →&nbsp;
                {{toArea}}
            </div>
            <span>│</span>
            <div class="pArriveInfo2">
                {{Format(returnDate)}} {{startTime2}} ~ {{arriveTime2}} &nbsp; {{seat}} {{seatClass2}}
            </div>

        </button>
    </div>
    <br><br>
    <!--승객 정보-->
    <input type="button" class="passengerInfo">

    <div class="passengerInfoTitle">
        승객 정보
        <br>
        <h5>[<span class="asterisk"> * </span>는 필수 입력 사항입니다.]</h5>
    </div>
    <div class="passengerTitle" @click="showPassInfo">
        {{AdultCount}} <span class="arrow">{{arrow}}</span>
    </div>
    <div v-show="passInfo" class="passInfo">
        <div class="passInfo1">
            <div class="passInfo1-1">
                <h5>승객 성<span class="asterisk"> *</span></h5><br>
                <input type="text" placeholder="예) 홍" v-model="korLastName" class="passText" disabled='disabled' >
            </div>
            <div class="passInfo1-2">
                <h5>승객 이름<span class="asterisk"> *</span></h5><br>
                <input type="text" placeholder="예) 길동" v-model="korFirstName" class="passText" disabled='disabled'>
            </div>
        </div>

        <div class="passInfo1">
            <div class="passInfo1-1">
                <h5>영문 성<span class="asterisk"> *</span></h5><br>
                <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                <input type="text" placeholder="예) HONG" v-model="engLastName" class="passText" >
            </div>
            <div class="passInfo1-2">
                <h5>영문 이름<span class="asterisk"> *</span></h5><br>
                <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                <input type="text" placeholder="예) GIL DONG" v-model="engFirstName" class="passText">
            </div>
        </div>

        <div class="passInfo2">
            <div class="passInfo2-1">
                <h5>성별<span class="asterisk"> *</span></h5><br>
                <input type="button" value="남자" class="maleBtn" id="maleBtn" disabled='disabled' autofocus>
                <input type="button" value="여자" class="femaleBtn" id="femaleBtn" disabled='disabled' autofocus>
            </div>
            <div class="passInfo2-2">
                <h5>생년 월일 (MM.DD) <span class="asterisk" disabled='disabled'> *</span></h5><br>
                <input type="text" class="passText" v-model="birthday">
            </div>
        </div>

    </div>
</div>

<button class="note" @click="showNoteInfo">
    <div class="noteTitle">
        유의사항 <span class="arrow">{{arrow}}</span>
    </div>
    <div class="note1" v-show="noteInfo">
        <li>
            예약 후 성명 변경은 불가하오니 실제 탑승하실 분의 여권에 기재된 영문 성명으로 정확하게 입력하시기 바랍니다. 성명 입력 안내
        </li>
        <li>
            입력하신 회원번호로 탑승 마일리지가 적립되며, 마일리지 적립율은 항공사에 따라 다를 수 있습니다.
        </li>
    </div>
</button>
<!--footer-->
<div class="footNav">
    <span class="footNav1">예상 결제 금액</span>
    <span class="startPrice">{{AddComma(startPrice)}}</span>
    <button type="button" class="submitBtn" @click="test()">Test</button>
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
            passInfo: true,
            noteInfo: true,
            arrow: "▲",
            korLastName: this.$store.state.name.substring(0, 1),
            korFirstName: this.$store.state.name.substring(1, 3),
            gender: this.$store.state.gender,
            autofocus: true,
            birthday: this.$store.state.birthday.toString().replace(/\B(?=(\d{2})+(?!\d))/g, "."),
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
        test() {
            console.log(this.korLastName)
            console.log(this.korFirstName)
            console.log(this.engLastName)
            console.log(this.engFirstName)
            console.log(this.gender)
            console.log(this.birthday)

            if(this.engFirstName == null){
                alert("영문 이름을 입력해주세요")
            }

            if(this.engLastName == null){
                alert("영문 성을 입력해주세요")
            }
        },
        Gender() {
            if (this.gender == "male") {
                const target = document.getElementById('maleBtn');
                target.disabled = false;            
                target.style.color="teal"
                target.style.border="3px solid teal"                
                return target.value = "남자" + "  ✔"
            } else {
                const target = document.getElementById('femaleBtn');
                target.disabled = false;
                target.style.color="teal"
                target.style.border="3px solid teal"
                return target.value = "여자" + "  ✔"
            }

        },
        Fare(value) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            let Fare = Math.floor(parseInt(value) * 0.7) + " 원";
            return Fare.toString().replace(regexp, ",");
        },
        Fuel(value) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            let Fuel = Math.floor(parseInt(value) * 0.2) + " 원";
            return Fuel.toString().replace(regexp, ",");
        },
        Tax(value) {
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
        showPassInfo() {
            this.passInfo = (this.passInfo) ? false : true
            this.arrow = "▼";
        },
        showNoteInfo() {
            this.noteInfo = (this.noteInfo) ? false : true
            this.arrow = "▼"
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
        this.Gender()
    }
}
</script>

<style>
#hint{
    font-size: 18px;
    color: teal;
}

.pStep {
    float: right;
    margin-right: 200px;
    font-size: 30px;
    display: flex;
}

.step3 {
    color: teal;
}

.hr {
    color: white;
}

h4 {
    color: black;
    padding-bottom: 10px;
}

.ver {
    margin-top: 25px;
    margin-left: 14px;
}

.arrow {
    color: white;
    float: right;
    margin-right: 30px;
}

.schedule {
    color: teal;
    font-size: 25px;
    font-weight: 900;
    margin-left: 9.5%;
    padding-top: 10px;
}

.pInfo {
    margin-top: 10px;
    margin-left: auto;
    margin-right: auto;
    width: 1330px;
    display: flex;
}

.pInfo button {
    width: 1400px;
    height: 100px;
    background-color: white;
    font-size: 24px;
    border: 0.5px solid #999;
    display: flex;
    box-shadow: 4px 4px 4px rgb(68, 68, 68);

}

.pInfo button div {
    padding: 30px;
}

.pInfoImg {
    margin-left: 14px;
    margin-bottom: 4px;
}

.pInfo button:hover {
    border: 2px solid teal;
}

.startInfoTitle {
    color: black;
    color: teal;
    font-size: 25px;
    font-weight: 900;
    padding: 10px;
    margin-left: 9%;
}

.pStartInfo {
    margin-left: auto;
    margin-right: auto;
    width: 1200px;
    display: flex;
}

.pArriveInfo {
    margin-left: auto;
    margin-right: auto;
    width: 1200px;
    display: flex;
}

.pStartInfo button,
.pArriveInfo button {
    color: white;
    width: 100%;
    height: 90px;
    background-color: rgba(34, 168, 168, 0.689);
    font-size: 21px;
    border: 0.5px solid #999;
    display: flex;
    box-shadow: 4px 4px 4px rgb(68, 68, 68);

}

.pStartInfo button div,
.pArriveInfo button div {
    padding: 30px;
}

.pStartInfo span,
.pArriveInfo span {
    padding-top: 22px;
    font-size: 30px;
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

.right {
    float: right;
    width: 550px;
    height: 1000px;
    margin-top: 1.5%;
}

.payInfo {
    border: 0.1px solid rgb(217, 217, 217);
    border-left: none;
    border-right: none;
    width: 400px;
    height: 200px;
    padding: 30px;
    font-size: 18px;
    color: rgb(87, 86, 86);
}

.totalPrice {
    border: 0.1px solid rgb(217, 217, 217);
    border-left: none;
    border-right: none;
    width: 400px;
    height: 110px;
    padding: 30px;
    padding-top: 40px;
    font-size: 24px;
}

.price {
    float: right;
}

.passengerInfo h4 {
    float: left;
}

.passengerInfo h5 {
    float: left;
}

.asterisk {
    color: red;

}

.passengerInfo {
    width: 1225px;
    border: none;

    background: white;
}

.passengerInfoTitle {
    color: teal;
    font-size: 25px;
    font-weight: 900;
    float: left;
    text-align: left;
    padding: 10px;
    margin-left: 9%;
}

.passengerInfoTitle h5 {
    color: rgb(78, 78, 78);
    padding-top: 10px;
}

.passengerTitle {
    float: left;
    font-size: 24px;
    color: white;
    background: teal;
    width: 1210px;
    padding-bottom: 25px;
    padding-top: 25px;
    padding-left: 40px;
    text-align: left;
    margin-left: 8.5%;
    cursor: pointer;
}

.passText {
    width: 400px;
    float: left;
    padding: 20px;
    border: 2px solid teal;
    background-color: white;
}

.passText:hover {
    border: 3px solid teal;
}


.passInfo {
    width: 1210px;
    border: none;
    margin-left: 8.5%;
    margin-bottom: 40px;
}

.passInfo1,
.passInfo2 {
    width: 100%;
    border: 1px solid;
    padding: 80px;
    padding-bottom: 100px;
    display: flex;
}

.passInfo1-2 {
    margin-left: 200px;
}

.maleBtn,
.femaleBtn {
    width: 200px;
    height: 70px;
    background: white;    
    opacity: 0.6;
    transition: 0.3s;
    font-size: 20px;
}

.maleBtn:hover,
.femaleBtn:hover {
    border: 3px solid teal;
}


.passInfo2-2 {
    margin-left: 200px;
}

.note {
    width: 1222px;
    border: none;
    margin-left: 8.2%;
    margin-bottom: 10%;
    background: white;
    display: block;
}

.noteTitle {
    font-size: 24px;
    color: white;
    background: teal;
    width: 1210px;
    padding-bottom: 25px;
    padding-top: 25px;
    padding-left: 40px;
    text-align: left;
}

.note1 {
    border: 1px solid;
    width: 100%;
    text-align: left;
    padding: 20px;
    margin-bottom: 150px;
    font-size: 19px;
}
</style>
