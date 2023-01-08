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
                <span>{{startYear}}-{{startMonth}}-{{startDay}}({{startWeek}})</span> <span v-show="returnYear"> ~ {{returnYear}}-{{returnMonth}}-{{returnDay}}({{returnWeek}})</span>
            </div>
            <img src="../assets/vertical.jpg" width="12" class="ver">
            <div class="pInfo3">
                <img class="pInfoImg" src="../assets/person.png" width="20" height="20"> &nbsp;
                성인 {{AdultCount}} 명
                <span v-show="ChildCount > 0">
                , 유아 {{ChildCount}} 명
                </span>
                <span v-show="InfantCount > 0">
                , 소아 {{InfantCount}} 명
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
                &nbsp;&nbsp;{{ flight1 }}
            </div>
            <span>│</span>
            <div class="pStartInfo2">
                {{startYear}}-{{startMonth}}-{{startDay}}({{startWeek}})
                {{startTime1}} ~ {{arriveTime1}} &nbsp; {{seat}} {{seatClass1}}
            </div>

        </button>
    </div>

    <br>

    <div class="pArriveInfo" v-show="returnYear != null">
        <button type="button">
            <div>
                오는 편
            </div>
            <span>│</span>
            <div class="pArriveInfo1">
                {{fromArea}} &nbsp;
                →&nbsp;
                {{toArea}}
                &nbsp;&nbsp; {{ flight2 }}
            </div>
            <span>│</span>
            <div class="pArriveInfo2">
                {{returnYear}}-{{returnMonth}}-{{returnDay}}({{returnWeek}})
                {{startTime2}} ~ {{arriveTime2}} &nbsp; {{seat}} {{seatClass2}}
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
                <input type="text" placeholder="예) 홍" v-model="korLastName" class="passText" disabled='disabled'>
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
                <input type="text" @keyup="engLastNameTest" placeholder="예) HONG" v-model="engLastName" class="passText">
            </div>
            <div class="passInfo1-2">
                <h5>영문 이름<span class="asterisk"> *</span></h5><br>
                <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                <input type="text" @keyup="engFirstNameTest" placeholder="예) GIL DONG" v-model="engFirstName" class="passText">
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

<div class="consentInfoTitle">
    확인 및 동의
</div>
<div class="consent" v-show="consentInfo">
    <h4>
        <button type="button" @click="consent1" id="consentBtn1" class="consentBtn" v-if="consentBtn1 = true">✔ 동의</button> &nbsp;
        <span v-show="false">{{ this.$store.state.consentBtn1 }}</span>[필수] 운송약관, 운임 규정, 수하물 규정을 확인하였으며 이에 동의합니다.
    </h4>
    <span>
        Fastrip 항공권을 구매하시는 것은 본 항공사와 운송계약 체결에 동의하는 것으로 운임규정은 항공권 변경, 취소 등에 따른 수수료와 사전좌석배정, <br>
        좌석승급 등 구매하는 항공권 운임에 적용되는 세부 조건을 기재하고 있으며, 운송 약관은 운송 계약 체결에 따른 계약조건을 명시합니다.<br>
        Fastrip은 '항공교통이용자 보호기준'에 따라 항공교통이용자 서비스 계획을 게시합니다.
    </span>
    <br><br>
    <h4>
        <button type="button" @click="consent2" id="consentBtn2" class="consentBtn" v-if="consentBtn2 = true">✔ 동의</button> &nbsp;
        <span v-show="false">{{ this.$store.state.consentBtn2 }}</span>[필수] 위험품 안내를 확인하였습니다.
        <button type="button" class="IATAList" @click="IATAModalPopUp()">보기</button>
    </h4>
    <span>
        고객 안전을 위하여 항공기 내부로 반입이 금지된 폭발성, 인화성, 유독성 물질 및 무기로 사용될 수 있는 품목에 대한 안내 사항 입니다.
    </span>
</div>
<div class="consent" v-show="consentInfo">
    <span>
        예약 후 성명 변경은 불가하오니 실제 탑승하실 분의 여권에 기재된 영문 성명으로 정확하게 입력하시기 바랍니다. 성명 입력 안내
    </span>
</div>

<button class="document" @click="showDocumentInfo">
    <div class="documentTitle">
        구비 서류 안내 <span class="arrow">{{arrow}}</span>
    </div>
    <div class="document1" v-show="documentInfo">
        <h4>
            항공보안법 관련 안내사항
        </h4>
        <span>
            항공보안법 규정에 따라 성인, 소아, 유아 전 승객의 신원확인을 위해 신분증명서와 탑승권 확인이 필요하오니 소지 여부를 반드시 재확인하여 주시기 바랍니다.<br>
            모든 신분증 및 여행서류는 탑승자의 책임 하에 준비되어야 합니다.
        </span>
    </div>
</button>

<div class="paymentInfoTitle">
    결제
</div>
<div class="payment">
    <div class="pay1">
        <div class="cardPay" @click="payment">
            신용/체크카드
        </div>
        <div class="pay" @click="payment">
            <img src="@/assets/pay/naverPay.png" style="width: 55px; height: 40px;">
            &nbsp; 네이버페이
        </div>
        <div class="pay" @click="payment">
            <img src="@/assets/pay/samsungPay.png" style="width: 80px; height: 40px;">
            삼성페이
        </div>
    </div>
    <div class="pay2">
        <div class="pay" @click="payment">
            <img src="@/assets/pay/kakaoPay.jpg" style="width: 60px; height: 40px;">
            &nbsp; 카카오페이
        </div>
        <div class="pay" @click="payment">
            <img src="@/assets/pay/payco.jpg" style="width: 80px; height: 40px;">
            &nbsp; PAYCO
        </div>
        <div class="pointPay" @click="PointPayment">
            포인트 결제
        </div>
    </div>
    <div class="holdPoint" v-show="PointPaymentInfo">
        보유하신 포인트 : {{ AddComma(holdPoint) + "p"}}
        <button type="button" class="addPoint" @click="addPoint">
            포인트 충전하기
        </button>
    </div>
</div>

<!--footer-->
<div class="footNav">
    <span class="footNav1">예상 결제 금액</span>
    <span class="startPrice">{{AddComma(startPrice) + " 원"}}</span>

    <button type="button" class="submitBtn" @click="PayModalPopUp()">예약 하기</button>
</div>

<div v-if="PayModalView == true" class="PayModalView" :class="{ active : PayModalView }">
    <PayModal :PayModalView="PayModalView" :user="user" :engFirstName="engFirstName" :engLastName="engLastName" @close="PayModalPopUp"></PayModal>
</div>

<div v-if="IATAModalView == true" class="IATAModalView" :class="{ active : IATAModalView }">
    <IATAModal @close="IATAModalPopUp"></IATAModal>
</div>
<!--test-->
</template>

<script>
import PayModal from "@/components/PayModal.vue";
import IATAModal from "@/components/IATAModal.vue";

export default {
    name: 'HelloWorld',
    components: {
        PayModal,
        IATAModal
    },
    data() {
        return {
            start: '',
            arrive: '',
            price: '',
            selectPrice: this.startPrice,
            seatPrice: 0,
            passInfo: true,
            consentInfo: true,
            documentInfo: true,
            noteInfo: true,
            arrow: "▲",
            korLastName: this.$store.state.name.substring(0, 1),
            korFirstName: this.$store.state.name.substring(1, 3),
            engLastName: this.engLastName,
            engFirstName: this.engFirstName,
            birthday: this.$store.state.birthday.toString().replace(/\B(?=(\d{2})+(?!\d))/g, "."),
            gender: this.$store.state.gender,
            autofocus: true,
            PayModalView: false,
            IATAModalView: false,
            PointPaymentInfo: false,
            holdPoint: this.$store.state.holdPoint,
            user: {
                flight1: this.flight1,
                flight2: this.flight2,
                fromArea: this.fromArea,
                toArea: this.toArea,
                seat: this.seat,
                seatClass1: this.seatClass1,
                seatClass2: this.seatClass2,
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
                startTime2: this.startTime2,
                arriveTime2: this.arriveTime2,
                startPrice: this.startPrice,

                korLastName: this.$store.state.name.substring(0, 1),
                korFirstName: this.$store.state.name.substring(1, 3),
                engLastName: this.engLastName,
                engFirstName: this.engFirstName,
                gender: this.$store.state.gender,
                birthday: this.$store.state.birthday.toString().replace(/\B(?=(\d{2})+(?!\d))/g, "."),
            },
        }
    },
    props: {
        flight1: {
            type: String,
            default: ''
        },
        flight2: {
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
        startDay: {
            type: String,
            default: ''
        },
        startWeek: {
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
        engLastNameTest() {
            if (this.engLastName == null) {
                alert("영문 성을 확인해주세요")
            } else if (/^[A-Z]*$/.test(this.engLastName)) {
                return true
            } else {
                alert("영어 대문자로 입력해 주세요.")
                this.engLastName = ""
            }
        },
        engFirstNameTest() {
            if (this.engFirstName == null) {
                alert("영문 이름을 확인해주세요")
            } else if (/^[A-Z\s]*$/.test(this.engFirstName)) {
                return true
            } else {
                alert("영어 대문자로 입력해 주세요.")
                this.engFirstName = ""
            }
        },
        payment() {
            alert("준비중입니다.")
        },
        PointPayment() {
            this.PointPaymentInfo = true
        },
        addPoint() {
            this.holdPoint += 300000
            this.$store.dispatch("holdPoint", this.holdPoint);
        },
        closeModal() {
            this.PayModalView = (this.PayModalView) ? false : true
        },
        IATAModalPopUp() {
            this.IATAModalView = (this.IATAModalView) ? false : true
        },
        PayModalPopUp() {

            if (this.engLastName == null) {
                alert("영문 성을 확인해주세요")
            }

            if (this.engFirstName == null) {
                alert("영문 이름을 확인해주세요")
            }

            if (this.$store.state.consentBtn1 == "선택1" && this.$store.state.consentBtn2 == "선택2") {
                console.log("필수 체크 완료")
            } else {
                alert("[확인 및 동의] 필수 동의 항목을 확인해주세요.")
            }

            if (this.holdPoint < this.startPrice) {
                alert("포인트가 모자랍니다.")
            } else if (this.holdPoint >= this.startPrice) {
                if (this.engLastName != null && this.engFirstName != null && this.$store.state.consentBtn1 == "선택1" && this.$store.state.consentBtn2 == "선택2") {
                    this.PayModalView = (this.PayModalView) ? false : true
                }
            }

        },
        AddComma(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return num.toString().replace(regexp, ",");
        },
        consent1() {
            if (this.consentBtn1 == true) {
                const target = document.getElementById('consentBtn1');
                target.style.color = "white"
                target.style.background = "teal"
                target.style.border = "3px solid teal"
                this.consentBtn1 = false
                this.$store.dispatch("consentBtn1", true);
            }
        },
        consent2() {
            if (this.consentBtn2 == true) {
                const target = document.getElementById('consentBtn2');
                target.style.color = "white"
                target.style.background = "teal"
                target.style.border = "3px solid teal"
                this.consentBtn2 = false
                this.$store.dispatch("consentBtn2", true);
            }
        },
        Gender() {

            if (this.gender == "male") {

                const target = document.getElementById('maleBtn');
                target.disabled = false;
                target.style.color = "teal"
                target.style.border = "3px solid teal"
                return target.value = "남자" + "  ✔"
            } else {
                const target = document.getElementById('femaleBtn');
                target.disabled = false;
                target.style.color = "teal"
                target.style.border = "3px solid teal"
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
            if (this.arrow == "▼") {
                this.arrow = "▲"
            } else {
                this.arrow = "▼"
            }
        },
        showNoteInfo() {
            this.noteInfo = (this.noteInfo) ? false : true
            if (this.arrow == "▼") {
                this.arrow = "▲"
            } else {
                this.arrow = "▼"
            }
        },
        showDocumentInfo() {
            this.documentInfo = (this.documentInfo) ? false : true
            if (this.arrow == "▼") {
                this.arrow = "▲"
            } else {
                this.arrow = "▼"
            }
        },

    },
    mounted() {
        this.Gender()
    }
}
</script>

<style>
.PayModalView {
    padding: 20px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 40%;
    height: 60%;
    text-align: center;
    border-radius: 15px;
    background-color: white;
    box-shadow: 2px 2px 10px lightgrey;
    overflow: auto;
}

.PayModalView::-webkit-scrollbar {
    width: 20px;
    /*스크롤바의 너비*/
}

.PayModalView::-webkit-scrollbar-thumb {
    background-color: teal;
    border-radius: 10px;
    /*스크롤바의 색상*/
}

.PayModalView::-webkit-scrollbar-track {
    margin-top: 10px;
    -webkit-margin-bottom-collapse: 10px;
    background-color: white;
    /*스크롤바 트랙 색상*/
}

.IATAModalView {
    padding: 20px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 50%;
    height: 75%;
    border-radius: 15px;
    background-color: white;
    box-shadow: 2px 2px 10px lightgrey;
}

.IATAModalView::-webkit-scrollbar {
    width: 0px;
}

#hint {
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

.startInfoTitle,
.consentInfoTitle {
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
    margin-right: 1%;
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
    margin-bottom: 2%;
    background: white;
    display: block;
}

.document {
    width: 1222px;
    border: none;
    margin-left: 8.2%;
    margin-bottom: 2%;
    background: white;
    display: block;
}

.noteTitle,
.documentTitle {
    font-size: 24px;
    color: white;
    background: teal;
    width: 1210px;
    padding-bottom: 25px;
    padding-top: 25px;
    padding-left: 40px;
    margin-top: 40px;
    text-align: left;
}

.note1,
.document1 {
    border: 1px solid;
    width: 100%;
    text-align: left;
    padding: 20px;
    font-size: 19px;
}

.consent {
    width: 1210px;
    padding-bottom: 25px;
    padding-top: 25px;
    padding-left: 40px;
    text-align: left;
    border: 1px solid;
    margin-left: 8.5%;
    font-size: 19px;
    margin-bottom: 2%;
}

.consentBtn {
    color: teal;
    background-color: white;
    border: 2.5px solid teal;
    padding: 8px;
    width: 120px;
}

.IATAList {
    float: right;
    margin-right: 40px;
    color: white;
    background-color: teal;
    border: none;
    padding: 10px;
}

.paymentInfoTitle {
    color: teal;
    font-size: 25px;
    font-weight: 900;
    float: left;
    text-align: left;
    padding: 10px;
    margin-left: 9%;
}

.payment {
    width: 1210px;
    height: 400px;
    border: 1px solid black;
    background: white;
    margin-bottom: 10%;
    margin-left: 8.5%;
    margin-top: 5.5%;
}

.cardPay,
.pay,
.pointPay {
    border: 1px solid black;
    width: 30%;
    text-align: center;
    padding: 20px 0;
    margin: 1%;
    color: #999;
    cursor: pointer;
    margin-top: 2%;

}

.cardPay {
    padding-top: 28px;
}

.pointPay {
    padding-top: 28px;
    color: black;
}

.pay1,
.pay2 {
    display: flex;
    margin: 1%;
    margin-left: 4.5%;
}

.holdPoint {
    font-size: 25px;
    font-weight: 900;
    margin-left: 8%;
    margin-top: 4%;
}

.addPoint {
    width: 200px;
    height: 50px;
    float: right;
    margin-right: 80px;
    color: white;
    background-color: teal;
    border: none;
    border-radius: 4px;
}
</style>
