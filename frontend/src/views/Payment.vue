<template>
<div>
    <div class="pStep">
        <div>① 검색</div> &nbsp; <div>② 항공편</div> &nbsp; <div class="step3">❸ 결제</div>
    </div>

    <div class="pInfo">
        <div class="blank">
            blank
        </div>
        <div class="pRight">
            <div class="pPayInfo">
                <h5>항공 운송료 </h5><br>
                <span>운임</span><span class="price">{{Fare(returnInfo.totalPrice)}}</span><br>
                <span>유류할증료</span><span class="price">{{Fuel(returnInfo.totalPrice)}}</span><br>
                <span>세금, 수수료 및 기타 요금</span><span class="price">{{Tax(returnInfo.totalPrice)}}</span><br>
                <hr>
                <span>성인</span> {{chooseInfo.AdultCount}} 명<span class="price">{{Format1(returnInfo.totalPrice)}}</span><br>
                <span v-if="chooseInfo.ChildCount >= 1">유아 {{chooseInfo.ChildCount}} 명</span><span class="price">{{Format2(returnInfo.totalPrice)}}</span><br>
                <span v-if="chooseInfo.InfantCount >= 1">소아 {{chooseInfo.InfantCount}} 명</span><span class="price">{{Format3(returnInfo.totalPrice)}}</span><br>
            </div>
            <div class="pTotalPrice">
                <span class="pTotal">총액</span><span class="price">{{AddComma1(returnInfo.totalPrice)}} 원</span>
            </div>
        </div>

        <div class="pLeft">
            <div class="pSchedule">
                여행 일정
            </div>
            <button type="button">
                <div class="pInfo1">
                    {{chooseInfo.fromArea}} &nbsp;
                    <img src="../assets/arrow2.jpg"> &nbsp;
                    {{chooseInfo.toArea}}
                </div>
                <img src="../assets/vertical.jpg" width="12" class="ver">
                <div class="pInfo2">
                    <img class="infoImg" src="../assets/calendar.png" width="30" height="30"> &nbsp;
                    <span>{{chooseInfo.startYear}}-{{chooseInfo.startMonth}}-{{chooseInfo.startDay}}({{chooseInfo.startWeek}})</span>
                    <span v-if="returnYear != ' '"> ~ {{chooseInfo.returnYear}}-{{chooseInfo.returnMonth}}-{{chooseInfo.returnDay}}({{chooseInfo.returnWeek}})</span>
                </div>
                <img src="../assets/vertical.jpg" width="12" class="ver">
                <div class="pInfo3">
                    <img class="pInfoImg" src="../assets/person.png" width="20" height="20"> &nbsp;
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
    </div>

    <br><br>

    <div class="pStartInfoTitle">
        여정 정보
    </div>
    <div class="pStartInfo">
        <button type="button">
            <div>
                가는 편
            </div>
            <span>│</span>
            <div class="pStartInfo1">
                {{chooseInfo.fromArea}} &nbsp;
                →&nbsp;
                {{chooseInfo.toArea}}
                &nbsp;&nbsp;{{ flight1 }}
            </div>
            <span>│</span>
            <div class="pStartInfo2">
                {{chooseInfo.startYear}}-{{chooseInfo.startMonth}}-{{chooseInfo.startDay}}({{chooseInfo.startWeek}})
                {{startInfo.startTime1}} ~ {{startInfo.arriveTime1}} &nbsp; {{chooseInfo.seat}} {{startInfo.seatClass1}}
            </div>

        </button>
    </div>

    <br>

    <div class="pArriveInfo" v-if="returnYear != ''">
        <button type="button">
            <div>
                오는 편
            </div>
            <span>│</span>
            <div class="pArriveInfo1">
                {{chooseInfo.toArea}} &nbsp;
                →&nbsp;
                {{chooseInfo.fromArea}}
                &nbsp;&nbsp; {{ flight2 }}
            </div>
            <span>│</span>
            <div class="pArriveInfo2">
                {{chooseInfo.returnYear}}-{{chooseInfo.returnMonth}}-{{chooseInfo.returnDay}}({{chooseInfo.returnWeek}})
                {{returnInfo.startTime2}} ~ {{returnInfo.arriveTime2}} &nbsp; {{chooseInfo.seat}} {{returnInfo.seatClass2}}
            </div>

        </button>
    </div>
    <br><br>
    <!--승객 정보-->
    <br>
    <div class="passengerInfoTitle">
        승객 정보
        <br>
        <h5>[<span class="asterisk"> * </span>는 필수 입력 사항입니다.]</h5>
    </div>

    <div class="pasNameHint">
        <span>
            예약 후 성명 변경은 불가하오니 여권에 기재된 성명을 정확히 확인하시기 바랍니다.<br>
            발음상 변화가 없는 단순 영문 성명 철자변경의 경우 로그인한 본인에 한해 변경하기를 이용하시기 바랍니다.
        </span>
    </div>
    <!--회원 예약자 정보-->
    <div class="passengerBox">
        <div class="passengerTitle" @click="showPassInfo1">
            성인 1 [예약자]<span class="arrow">{{arrow}}</span>
        </div>
        <div v-if="passInfo" class="passInfo">
            <div class="passInfo1">
                <div class="passInfo1-1">
                    <h5>승객 성<span class="asterisk"> *</span></h5><br>
                    <!--회원-->
                    <input type="text" v-if="isLogin()" v-model="korLastName1" class="passText" disabled='disabled'>
                    <!--비회원-->
                    <input type="text" v-if="!isLogin()" placeholder="예) 홍" v-model="korLastName1" class="passText">
                </div>
                <div class="passInfo1-2">
                    <h5>승객 이름<span class="asterisk"> *</span></h5><br>
                    <!--회원-->
                    <input type="text" v-if="isLogin()" v-model="korFirstName1" class="passText" disabled='disabled'>
                    <!--비회원-->
                    <input type="text" v-if="!isLogin()" placeholder="예) 길동" v-model="korFirstName1" class="passText">
                </div>
            </div>

            <div class="passInfo1">
                <div class="passInfo1-1">
                    <h5>영문 성<span class="asterisk"> *</span></h5><br>
                    <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                    <input type="text" @keyup="engLastNameTest" placeholder="예) HONG" v-model="engLastName1" class="passText">
                </div>
                <div class="passInfo1-2">
                    <h5>영문 이름<span class="asterisk"> *</span></h5><br>
                    <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                    <input type="text" @keyup="engFirstNameTest" placeholder="예) GIL DONG" v-model="engFirstName1" class="passText">
                </div>
            </div>

            <div class="passInfo2">
                <div class="passInfo2-1">
                    <h5>성별<span class="asterisk"> *</span></h5><br>
                    <input type="button" value="남자" class="maleBtn" id="maleBtn1" disabled='disabled' autofocus>
                    <input type="button" value="여자" class="femaleBtn" id="femaleBtn1" disabled='disabled' autofocus>
                </div>
                <div class="passInfo2-2">
                    <h5>생년 월일 (MMDD) <span class="asterisk" disabled='disabled'> *</span></h5><br>
                    <!--회원-->
                    <input type="text" v-if="isLogin()" class="passText" v-model="birthday1">
                    <!--비회원-->
                    <input type="text" v-if="!isLogin()" placeholder="예) MMDD" v-model="birthday1" class="passText">
                </div>

            </div>
            <div class="passInfo3">
                <button type="button" @click="showPassInfo1(pas + 1)" class="passCheckBtn" id="">확인</button>
            </div>

        </div>
        <br>
    </div>

    <!--추가 예약자 정보(성인)-->
    <div class="passengerBox" v-for="(pas, index) in parseInt(chooseInfo.AdultCount)-1" :key="index">
        <div class="passengerTitle" @click="showPassInfo2(pas)">
            <span>
                성인 {{ (pas + 1) }}
            </span>
            <span class="arrow">{{arrow}}</span>
        </div>
        <span v-if="pas == addPas1" class="passInfo">
            <div class="passInfo">
                <div class="passInfo1">
                    <div class="passInfo1-1">
                        <h5>승객 성<span class="asterisk"> *</span></h5><br>
                        <input type="text" placeholder="예) 홍" v-model="korLastName2" class="passText">
                    </div>
                    <div class="passInfo1-2">
                        <h5>승객 이름<span class="asterisk"> *</span></h5><br>
                        <input type="text" placeholder="예) 길동" v-model="korFirstName2" class="passText">
                    </div>
                </div>

                <div class="passInfo1">
                    <div class="passInfo1-1">
                        <h5>영문 성<span class="asterisk"> *</span></h5><br>
                        <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                        <input type="text" @keyup="engLastNameTest" placeholder="예) HONG" v-model="engLastName2" class="passText">
                    </div>
                    <div class="passInfo1-2">
                        <h5>영문 이름<span class="asterisk"> *</span></h5><br>
                        <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                        <input type="text" @keyup="engFirstNameTest" placeholder="예) GIL DONG" v-model="engFirstName2" class="passText">
                    </div>
                </div>

                <div class="passInfo2">

                    <div class="passInfo2-1">
                        <h5>성별<span class="asterisk"> *</span></h5><br>
                        <input type='radio' class="maleRadio" name='gender' value='남자' />
                        <span class="maleRadioLabel">남자</span>
                        <input type='radio' class="femaleRadio" name='gender' value='여자' />
                        <span class="femaleRadioLabel">여자</span>
                    </div>
                    <div class="passInfo2-2" style="margin-left:267px">
                        <h5>생년 월일 (MM.DD) <span class="asterisk" disabled='disabled'> *</span></h5><br>
                        <input type="text" class="passText" v-model="birthday2" placeholder="예) 01.01">
                    </div>

                </div>
                <div class="passInfo3">
                    <button type="button" @click="showPassInfo2(pas + 1)" class="passCheckBtn" id="">확인</button>
                </div>
            </div>
            <br>
        </span>
    </div>

    <!--추가 예약자 정보(유아)-->
    <div class="passengerBox" v-for="(pas, index) in parseInt(chooseInfo.ChildCount)" :key="index">
        <div class="passengerTitle" @click="showPassInfo2(pas)">
            <span>
                유아 {{ pas }}
            </span>
            <span class="arrow">{{arrow}}</span>
        </div>
        <span v-if="pas == addPas2" class="passInfo">
            <div class="passInfo">
                <div class="passInfo1">
                    <div class="passInfo1-1">
                        <h5>승객 성<span class="asterisk"> *</span></h5><br>
                        <input type="text" placeholder="예) 홍" v-model="korLastName2" class="passText">
                    </div>
                    <div class="passInfo1-2">
                        <h5>승객 이름<span class="asterisk"> *</span></h5><br>
                        <input type="text" placeholder="예) 길동" v-model="korFirstName2" class="passText">
                    </div>
                </div>

                <div class="passInfo1">
                    <div class="passInfo1-1">
                        <h5>영문 성<span class="asterisk"> *</span></h5><br>
                        <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                        <input type="text" @keyup="engLastNameTest" placeholder="예) HONG" v-model="engLastName2" class="passText">
                    </div>
                    <div class="passInfo1-2">
                        <h5>영문 이름<span class="asterisk"> *</span></h5><br>
                        <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                        <input type="text" @keyup="engFirstNameTest" placeholder="예) GIL DONG" v-model="engFirstName2" class="passText">
                    </div>
                </div>

                <div class="passInfo2">

                    <div class="passInfo2-1">
                        <h5>성별<span class="asterisk"> *</span></h5><br>
                        <input type='radio' class="maleRadio" name='gender' value='남자' />
                        <span class="maleRadioLabel">남자</span>
                        <input type='radio' class="femaleRadio" name='gender' value='여자' />
                        <span class="femaleRadioLabel">여자</span>
                    </div>
                    <div class="passInfo2-2" style="margin-left:267px">
                        <h5>생년 월일 (MM.DD) <span class="asterisk" disabled='disabled'> *</span></h5><br>
                        <input type="text" class="passText" v-model="birthday2" placeholder="예) 01.01">
                    </div>

                </div>
                <div class="passInfo3">
                    <button type="button" @click="showPassInfo3(pas + 1)" class="passCheckBtn" id="">확인</button>
                </div>
            </div>
            <br>
        </span>
    </div>

    <!--추가 예약자 정보(소아)-->
    <div class="passengerBox" v-for="(pas, index) in parseInt(chooseInfo.InfantCount)" :key="index">
        <div class="passengerTitle" @click="showPassInfo4(pas + 1)">
            <span>
                소아 {{ pas }}
            </span>
            <span class="arrow">{{arrow}}</span>
        </div>
        <span v-if="pas == addPas3" class="passInfo">
            <div class="passInfo">
                <div class="passInfo1">
                    <div class="passInfo1-1">
                        <h5>승객 성<span class="asterisk"> *</span></h5><br>
                        <input type="text" placeholder="예) 홍" v-model="korLastName2" class="passText">
                    </div>
                    <div class="passInfo1-2">
                        <h5>승객 이름<span class="asterisk"> *</span></h5><br>
                        <input type="text" placeholder="예) 길동" v-model="korFirstName2" class="passText">
                    </div>
                </div>

                <div class="passInfo1">
                    <div class="passInfo1-1">
                        <h5>영문 성<span class="asterisk"> *</span></h5><br>
                        <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                        <input type="text" @keyup="engLastNameTest" placeholder="예) HONG" v-model="engLastName2" class="passText">
                    </div>
                    <div class="passInfo1-2">
                        <h5>영문 이름<span class="asterisk"> *</span></h5><br>
                        <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
                        <input type="text" @keyup="engFirstNameTest" placeholder="예) GIL DONG" v-model="engFirstName2" class="passText">
                    </div>
                </div>

                <div class="passInfo2">

                    <div class="passInfo2-1">
                        <h5>성별<span class="asterisk"> *</span></h5><br>
                        <input type='radio' class="maleRadio" name='gender' value='남자' />
                        <span class="maleRadioLabel">남자</span>
                        <input type='radio' class="femaleRadio" name='gender' value='여자' />
                        <span class="femaleRadioLabel">여자</span>
                    </div>
                    <div class="passInfo2-2" style="margin-left:267px">
                        <h5>생년 월일 (MM.DD) <span class="asterisk" disabled='disabled'> *</span></h5><br>
                        <input type="text" class="passText" v-model="birthday2" placeholder="예) 01.01">
                    </div>

                </div>
                <div class="passInfo3">
                    <button type="button" @click="showPassInfo4(pas+1)" class="passCheckBtn" id="">확인</button>
                </div>
            </div>
            <br>
        </span>
    </div>

</div>

<button class="note" @click="showNoteInfo">
    <div class="noteTitle">
        유의사항 <span class="arrow">{{arrow}}</span>
    </div>
    <div class="note1" v-if="noteInfo">
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
<div class="consent" v-if="consentInfo">
    <h4>
        <button type="button" @click="consent1" id="consentBtn1" class="consentBtn" v-if="consentBtn1 = true">✔ 동의</button> &nbsp;
        <span v-if="false">{{ this.$store.state.consentBtn1 }}</span>[필수] 운송약관, 운임 규정, 수하물 규정을 확인하였으며 이에 동의합니다.
    </h4>
    <span>
        Fastrip 항공권을 구매하시는 것은 본 항공사와 운송계약 체결에 동의하는 것으로 운임규정은 항공권 변경, 취소 등에 따른 수수료와 사전좌석배정, <br>
        좌석승급 등 구매하는 항공권 운임에 적용되는 세부 조건을 기재하고 있으며, 운송 약관은 운송 계약 체결에 따른 계약조건을 명시합니다.<br>
        Fastrip은 '항공교통이용자 보호기준'에 따라 항공교통이용자 서비스 계획을 게시합니다.
    </span>
    <br><br>
    <h4>
        <button type="button" @click="consent2" id="consentBtn2" class="consentBtn" v-if="consentBtn2 = true">✔ 동의</button> &nbsp;
        <span v-if="false">{{ this.$store.state.consentBtn2 }}</span>[필수] 위험품 안내를 확인하였습니다.
        <button type="button" class="IATAList" @click="IATAModalPopUp()">보기</button>
    </h4>
    <span>
        고객 안전을 위하여 항공기 내부로 반입이 금지된 폭발성, 인화성, 유독성 물질 및 무기로 사용될 수 있는 품목에 대한 안내 사항 입니다.
    </span>
</div>
<div class="consent" v-if="consentInfo">
    <span>
        예약 후 성명 변경은 불가하오니 실제 탑승하실 분의 여권에 기재된 영문 성명으로 정확하게 입력하시기 바랍니다. 성명 입력 안내
    </span>
</div>

<button class="document" @click="showDocumentInfo">
    <div class="documentTitle">
        구비 서류 안내 <span class="arrow">{{arrow}}</span>
    </div>
    <div class="document1" v-if="documentInfo">
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
            <img src="@/assets/pay/naverPay.png" style="width: 55px; height: 40px; border-radius: 10px;">
            &nbsp; 네이버페이
        </div>
        <div class="pay" @click="payment">
            <img src="@/assets/pay/samsungPay.png" style="width: 60px; height: 40px;">
            삼성페이
        </div>
    </div>
    <div class="pay2">
        <div class="pay" @click="payment">
            <img src="@/assets/pay/kakaoPay.jpg" style="width: 60px; height: 40px; border-radius: 10px;">
            &nbsp; 카카오페이
        </div>
        <div class="pay" @click="payment">
            <img src="@/assets/pay/payco.jpg" style="width: 80px; height: 40px;">
            PAYCO
        </div>
        <div class="pointPay" @click="PointPayment">
            포인트 결제
        </div>
    </div>
    <div class="holdPoint" v-if="PointPaymentInfo">
        보유하신 포인트 : {{ AddComma2(holdPoint) + "p"}}
        <button type="button" class="addPoint" @click="addPoint">
            포인트 충전하기
        </button>
    </div>
</div>

<div class="payFootNav">
    <div class="payFootNav1">예상 결제 금액</div>
    <div class="payStartPrice">{{AddComma1(returnInfo.totalPrice)}} 원</div>

    <button type="button" class="paySubmitBtn" @click="PayModalPopUp()">예약 하기</button>
</div>

<div v-if="PayModalView == true" class="PayModalView" :class="{ active : PayModalView }">
    <PayModal :PayModalView="PayModalView" :user="user" :engLastName="engLastName1" :engFirstName="engFirstName1" :addPassKorName="addPassKorName" :addPassEngName="addPassEngName" :addPassGender="addPassGender" :addPassBirthday="addPassBirthday" @close="PayModalPopUp"></PayModal>
</div>

<div v-if="IATAModalView == true" class="IATAModalView" :class="{ active : IATAModalView }">
    <IATAModal @close="IATAModalPopUp"></IATAModal>
</div>
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
            chooseInfo: this.$store.state.chooseInfo,
            startInfo: this.$store.state.startInfo,
            returnInfo: this.$store.state.returnInfo,
            selectPrice: this.startPrice,
            seatPrice: 0,
            price: 0,
            passInfo: true,
            consentInfo: true,
            documentInfo: true,
            noteInfo: true,
            arrow: "▲",
            korLastName1: this.$store.state.userInfo.lastName,
            korLastName2: "",
            korFirstName1: this.$store.state.userInfo.firstName,
            korFirstName2: "",
            engLastName: this.engLastName1,
            engFirstName: this.engFirstName1,
            birthday1: this.$store.state.userInfo.birthday,
            birthday2: this.birthday2,
            gender1: this.$store.state.userInfo.gender,
            gender2: document.getElementById('maleBtn2'),
            autofocus: true,
            PayModalView: false,
            IATAModalView: false,
            PointPaymentInfo: false,
            holdPoint: this.$store.state.holdPoint,
            name: '',
            addPassKorName: [],
            addPassEngName: [],
            addPassGender: [],
            addPassBirthday: [],
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

                korLastName: this.korLastName1,
                korFirstName: this.korFirstName1,
                engLastName: this.engLastName1,
                engFirstName: this.engFirstName1,
                gender: this.gender1,
                birthday: this.birthday1,
            },
        }
    },
    props: {},
    methods: {
        isLogin() {
            return this.$store.state.isLogin;
        },
        Format1(value) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            let price = value * this.chooseInfo.AdultCount;
            return price.toString().replace(regexp, ",") + " 원";
        },
        Format2(value) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            let price = (value * 0.8) * this.chooseInfo.ChildCount;
            return "(-20%)" + price.toString().replace(regexp, ",") + " 원";
        },
        Format3(value) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            let price = (value * 0.5) * this.chooseInfo.InfantCount;
            return "(-50%)" + price.toString().replace(regexp, ",") + " 원";
        },
        engLastNameTest() {
            if (this.engLastName1 == null) {
                alert("영문 성을 확인해주세요")
            } else if (/^[A-Z]*$/.test(this.engLastName1)) {
                return true
            } else {
                alert("영어 대문자로 입력해 주세요.")
                this.engLastName1 = ""
            }
        },
        engFirstNameTest() {
            if (this.engFirstName1 == null) {
                alert("영문 이름을 확인해주세요")
            } else if (/^[A-Z\s]*$/.test(this.engFirstName1)) {
                return true
            } else {
                alert("영어 대문자로 입력해 주세요.")
                this.engFirstName1 = ""
            }
        },
        payment() {
            alert("준비중입니다.")
        },
        PointPayment() {
            this.PointPaymentInfo = true
        },
        addPoint() {
            this.holdPoint += 500000
            this.$store.dispatch("holdPoint", this.holdPoint);
        },
        closeModal() {
            this.PayModalView = (this.PayModalView) ? false : true
        },
        IATAModalPopUp() {
            this.IATAModalView = (this.IATAModalView) ? false : true
        },
        AddComma1(num) {
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            let price = num * this.chooseInfo.AdultCount;

            if (this.chooseInfo.ChildCount >= 1 && this.chooseInfo.InfantCount >= 1) {
                let addPrice1 = price + (num * this.chooseInfo.ChildCount * 0.8);
                let addPrice2 = addPrice1 + (num * this.chooseInfo.InfantCount * 0.5);
                return addPrice2.toString().replace(regexp, ",");
            } else if (this.chooseInfo.ChildCount >= 1) {
                let addPrice = price + (num * this.chooseInfo.ChildCount * 0.8);
                return addPrice.toString().replace(regexp, ",");
            } else if (this.chooseInfo.InfantCount >= 1) {
                let addPrice = price + (num * this.chooseInfo.InfantCount * 0.5);
                return addPrice.toString().replace(regexp, ",");
            }

            return price.toString().replace(regexp, ",");
        },
        AddComma2(num) {
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

            if (this.gender1 == "male") {
                const target = document.getElementById('maleBtn1');
                target.disabled = false;
                target.style.color = "teal"
                target.style.border = "3px solid teal"
                target.value = "남자" + "  ✔"
                return this.gender1 = "남자"
            } else if (this.gender1 == "female") {
                const target = document.getElementById('femaleBtn1');
                target.disabled = false;
                target.style.color = "teal"
                target.style.border = "3px solid teal"
                target.value = "여자" + "  ✔"
                return this.gender1 = "여자"
            } else {
                const target1 = document.getElementById('maleBtn1');
                const target2 = document.getElementById('femaleBtn1');
                target1.disabled = false;
                target2.disabled = false;

                target1.addEventListener('click', function () {
                    target1.style.color = "teal"
                    target1.style.border = "3px solid teal"
                    target1.value = "남자" + "  ✔"

                    target2.style.color = "#999"
                    target2.style.border = "3px solid #999"
                    target2.value = "여자"

                    return this.gender1 = "남자"
                });

                target2.addEventListener('click', function () {
                    target2.style.color = "teal"
                    target2.style.border = "3px solid teal"
                    target2.value = "여자" + "  ✔"

                    target1.style.color = "#999"
                    target1.style.border = "3px solid #999"
                    target1.value = "남자"

                    return this.gender1 = "여자"
                });
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
        showPassInfo1() {
            this.passInfo = (this.passInfo) ? false : true
            this.addPas1 = true;

            if (this.arrow == "▼") {
                this.arrow = "▲"
            } else {
                this.arrow = "▼"
            }
        },
        showPassInfo2(value) {
            var selected = document.querySelector('input[type=radio][name=gender]:checked');

            if (this.korLastName2 == "") {
                alert("성을 확인해 주세요.");
            } else if (this.korFirstName2 == "") {
                alert("이름을 확인해 주세요");
            } else if (this.engLastName2 == null) {
                alert("영문 성을 확인해 주세요.");
            } else if (this.engFirstName2 == null) {
                alert("영문 이름을 확인해 주세요");
            } else if (selected == null) {
                alert("성별을 확인해 주세요.");
            } else if (this.birthday2 == null) {
                alert("생년 월일을 확인해 주세요.");
            } else {
                this.showPassInfo2Push(value);
            }

        },
        showPassInfo2Push(value) {
            var selected = document.querySelector('input[type=radio][name=gender]:checked');

            this.addPassEngName.push(this.engFirstName2)
            this.addPassEngName.push(this.engLastName2)

            this.addPassKorName.push(this.korFirstName2)
            this.addPassKorName.push(this.korLastName2)

            this.addPassGender.push(selected.value)

            this.addPassBirthday.push(this.birthday2)

            this.addpas1 = (value) ? false : true

            if (this.arrow == "▼") {
                this.arrow = "▲"
            } else {
                this.arrow = "▼"
            }

            console.log(value)
            console.log(Math.max(this.AdultCount))

            if (value == Math.max(this.AdultCount)) {
                this.addpas2 = true
                this.showPassInfo3(value = 1);

                for (let i = 0; i < 2; i++) {
                    this.addPassEngName.pop();
                    this.addPassKorName.pop();

                }

                this.addPassGender.pop();
                this.addPassBirthday.pop();
                return this.addPas1 = false;
            }

            return this.addPas1 = value;

        },
        showPassInfo3(value) {
            var selected = document.querySelector('input[type=radio][name=gender]:checked');

            if (this.korLastName2 == "") {
                alert("성을 확인해 주세요.");
            } else if (this.korFirstName2 == "") {
                alert("이름을 확인해 주세요");
            } else if (this.engLastName2 == null) {
                alert("영문 성을 확인해 주세요.");
            } else if (this.engFirstName2 == null) {
                alert("영문 이름을 확인해 주세요");
            } else if (selected == null) {
                alert("성별을 확인해 주세요.");
            } else if (this.birthday2 == null) {
                alert("생년 월일을 확인해 주세요.");
            } else {
                this.showPassInfo3Push(value);
            }

        },
        showPassInfo3Push(value) {
            var selected = document.querySelector('input[type=radio][name=gender]:checked');

            this.addPassEngName.push(this.engFirstName2)
            this.addPassEngName.push(this.engLastName2)

            this.addPassKorName.push(this.korFirstName2)
            this.addPassKorName.push(this.korLastName2)

            this.addPassGender.push(selected.value)

            this.addPassBirthday.push(this.birthday2)

            this.addpas2 = (value) ? false : true

            if (this.arrow == "▼") {
                this.arrow = "▲"
            } else {
                this.arrow = "▼"
            }

            if (value > Math.max(this.ChildCount)) {
                this.addpas3 = true
                this.showPassInfo4(value = 1);

                for (let i = 0; i < 2; i++) {
                    this.addPassEngName.pop();
                    this.addPassKorName.pop();
                }
                this.addPassGender.pop();
                this.addPassBirthday.pop();
                return this.addPas2 = false;
            }

            return this.addPas2 = value;

        },
        showPassInfo4(value) {
            var selected = document.querySelector('input[type=radio][name=gender]:checked');

            if (this.korLastName2 == "") {
                alert("성을 확인해 주세요.");
            } else if (this.korFirstName2 == "") {
                alert("이름을 확인해 주세요");
            } else if (this.engLastName2 == null) {
                alert("영문 성을 확인해 주세요.");
            } else if (this.engFirstName2 == null) {
                alert("영문 이름을 확인해 주세요");
            } else if (selected == null) {
                alert("성별을 확인해 주세요.");
            } else if (this.birthday2 == null) {
                alert("생년 월일을 확인해 주세요.");
            } else {
                this.showPassInfo4Push(value);
            }

        },
        showPassInfo4Push(value) {
            var selected = document.querySelector('input[type=radio][name=gender]:checked');

            this.addPassEngName.push(this.engFirstName2)
            this.addPassEngName.push(this.engLastName2)

            this.addPassKorName.push(this.korFirstName2)
            this.addPassKorName.push(this.korLastName2)

            this.addPassGender.push(selected.value)

            this.addPassBirthday.push(this.birthday2)

            this.addPas3 = (value) ? false : true

            if (this.arrow == "▼") {
                this.arrow = "▲"
            } else {
                this.arrow = "▼"
            }

            if (value > Math.max(this.InfantCount)) {
                return this.addPas3 = false;
            }

            console.log(this.addPassEngName)
            console.log(this.addPassKorName)
            console.log(this.addPassGender)
            console.log(this.addPassBirthday)

            return this.addPas3 = value;

        },

        showNoteInfo(value) {
            alert(value)
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

        PayModalPopUp() {

            if (this.engLastName1 == null) {
                alert("영문 성을 확인해주세요")
            }

            if (this.engFirstName1 == null) {
                alert("영문 이름을 확인해주세요")
            }

            if (this.engLastName1 == null) {
                alert("영문 성을 확인해주세요")
            }

            if (this.engFirstName1 == null) {
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
                if (this.engLastName1 != null && this.engFirstName1 != null && this.$store.state.consentBtn1 == "선택1" && this.$store.state.consentBtn2 == "선택2") {
                    this.PayModalView = (this.PayModalView) ? false : true
                }
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
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 32%;
    height: 60%;
    border-radius: 15px;
    background-color: white;
    box-shadow: 2px 2px 10px lightgrey;
    overflow: auto;
}

.IATAModalView::-webkit-scrollbar {
    width: 20px;
    /*스크롤바의 너비*/
}

.IATAModalView::-webkit-scrollbar-thumb {
    background-color: teal;
    border-radius: 10px;
    /*스크롤바의 색상*/
}

.IATAModalView::-webkit-scrollbar-track {
    background-color: white;
    /*스크롤바 트랙 색상*/
}

.pStep {
    float: right;
    margin-right: 5%;
    font-size: 30px;
    display: flex;
}

/*.pRight,
    .pPayInfo,
    .pPayInfo h5,
    .pTotal,
    .pTotalPrice,
    .pPayInfo,
    .pTotalPrice {
        color: white;        
    }*/

@media (min-width: 2050px) {
    .pStep {
        float: right;
        margin-right: 10%;
        font-size: 30px;
        display: flex;
    }

    .IATAModalView {
        padding: 20px;
        width: 50%;
        height: 75%;
        border-radius: 15px;
        background-color: white;
        box-shadow: 2px 2px 10px lightgrey;
        overflow: hidden;
    }

    #hint {
        font-size: 18px;
        color: teal;
    }

}

.pRight {
    width: 1%;
    height: 1px;
    overflow: hidden;
}

@media (min-width: 1870px) {
    .pRight {
        width: 22.3%;
        height: 470px;
        float: right;
        margin-right: 2.5%;
        margin-top: 2%;
        padding: 40px 0;
        background-color: rgba(34, 168, 168, 0.689);
        border-radius: 10px;
    }

    .pPayInfo {
        border-left: none;
        border-right: none;
        width: 360px;
        height: 300px;
        padding-left: 7%;
        font-size: 19px;
        font-weight: 900;
        color: white;
    }

    .pPayInfo h5 {
        font-weight: 900;
        font-size: 24px;
    }

    .pTotal {
        font-weight: 900;
        color: white;
    }

    .pTotalPrice {
        border-top: 0.1px solid rgb(217, 217, 217);
        width: 400px;
        height: 80px;
        padding: 30px;
        padding-top: 40px;
        font-size: 24px;
        color: white;
        font-weight: 900;
    }

    .pPayInfo {
        color: white;
    }

    .pTotalPrice {
        color: white;
    }
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

.blank {
    color: white;
    font-size: 25px;
    margin-left: 4.2%;
    margin-bottom: 10px;

}

.pSchedule {
    color: teal;
    font-size: 25px;
    font-weight: 900;
    margin-left: 4.2%;
    margin-bottom: 10px;
    padding-top: 10px;
}

.pInfo {
    margin-top: 10px;
    margin-left: 5.5%;
}

.pInfo button {
    width: 1300px;
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

.pStartInfoTitle,
.consentInfoTitle {
    color: black;
    color: teal;
    font-size: 25px;
    font-weight: 900;
    padding: 10px;
    margin-left: 9%;
}

.pStartInfo {
    margin-left: 8%;
    width: 1200px;
    display: flex;
}

.pArriveInfo {
    margin-left: 8%;
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

.payFootNav {
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

.payFootNav1 {
    margin-left: 30px;
}

.paySubmitBtn {
    width: 15%;
    height: 130%;
    font-size: 25px;
    border-radius: 4px;
    color: white;
    background: teal;
    border: 1px solid white;
}

.payStartPrice {
    font-size: 20px;
    margin-left: 70%;
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
    border: none;
    background: white;
}

.passengerInfoTitle {
    color: teal;
    font-size: 25px;
    font-weight: 900;
    padding: 10px;
    margin-left: 9%;
}

.passengerInfoTitle h5 {
    color: rgb(78, 78, 78);
    padding-top: 10px;
}

.pasNameHint {
    background-color: rgba(245, 245, 245, 0.555);
    width: 1210px;
    margin-left: 8.5%;
    padding: 40px 0;
    padding-left: 40px;
    font-size: 20px;

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

.passInfo1 {
    width: 100%;
    border: 1px solid;
    padding-top: 80px;
    padding-left: 80px;
    padding-bottom: 100px;
    display: flex;
}

.passInfo2 {
    width: 100%;
    border: 1px solid;
    padding-top: 80px;
    padding-left: 80px;
    padding-bottom: 50px;
    display: flex;
    border-bottom: none;
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

.maleRadio,
.femaleRadio {
    width: 120px;
    height: 20px;
}

.maleRadioLabel,
.femaleRadioLabel {
    font-size: 26px;
    font-weight: 900;
    color: teal;
}

.maleBtn:hover,
.femaleBtn:hover {
    border: 3px solid teal;
}

.passInfo2-2 {
    margin-left: 200px;
    position: relative;
}

.passInfo3 {
    width: 100%;
    border: 1px solid;
    border-top: none;

}

.passCheckBtn {
    width: 140px;
    height: 55px;
    color: white;
    background-color: teal;
    border: none;
    border-radius: 4px;
    font-weight: 900;
    font-size: 20px;
    margin-left: 970px;
    margin-bottom: 50px;
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
    font-weight: 900;
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
