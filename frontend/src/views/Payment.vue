<template>
  <div>
    <div class="pStep">
      <div>① 검색</div>
      &nbsp;
      <div>② 항공편</div>
      &nbsp;
      <div class="step3">❸ 결제</div>
    </div>

    <div class="pInfo">
      <div class="pLeft">
        <div class="pSchedule">여행 일정</div>
        <br />
        <button type="button">
          <!-- 출발지 -> 도착지 -->
          <div class="pInfo1">
            {{ chooseInfo?.fromArea }}
            <span class="arrow2">⇀</span>
            {{ chooseInfo?.toArea }}
          </div>
          <img src="../assets/vertical.jpg" width="12" class="ver" />
          <div class="pInfo2">
            <img
              class="infoImg"
              src="../assets/calendar.png"
              width="30"
              height="30"
            />
            &nbsp;
            <!-- 출발일 -->
            <span
              >{{ chooseInfo?.startYear }}-{{ chooseInfo?.startMonth }}-{{
                chooseInfo?.startDay
              }}({{ chooseInfo?.startWeek }})</span
            >
            <!-- 도착일 -->
            <span v-if="chooseInfo?.returnYear">
              ~ {{ chooseInfo?.returnYear }}-{{ chooseInfo?.returnMonth }}-{{
                chooseInfo?.returnDay
              }}({{ chooseInfo?.returnWeek }})</span
            >
          </div>
          <img src="../assets/vertical.jpg" width="12" class="ver" />
          <!-- 승객 수 -->
          <div class="pInfo3">
            <img
              class="pInfoImg"
              src="../assets/person.png"
              width="20"
              height="20"
            />
            &nbsp; 성인 {{ chooseInfo?.AdultCount }}명
            <span v-if="chooseInfo?.ChildCount > 0">
              , 유아 {{ chooseInfo?.ChildCount }}명
            </span>
            <span v-if="chooseInfo?.InfantCount > 0">
              , 소아 {{ chooseInfo?.InfantCount }}명
            </span>
          </div>
        </button>
      </div>
    </div>

    <div class="pRight">
      <div class="pPayInfo">
        <h5>항공 운송료</h5>
        <br />
        <span>운임</span
        ><span class="price">{{ Fare(returnInfo?.totalPrice) }}</span
        ><br />
        <span>유류할증료</span
        ><span class="price">{{ Fuel(returnInfo?.totalPrice) }}</span
        ><br />
        <span>세금, 수수료 및 기타 요금</span
        ><span class="price">{{ Tax(returnInfo?.totalPrice) }}</span
        ><br />
        <hr />
        <span>성인</span> {{ chooseInfo?.AdultCount }} 명<span class="price">{{
          Format1(returnInfo?.totalPrice)
        }}</span
        ><br />
        <span v-if="chooseInfo.ChildCount >= 1"
          >유아 {{ chooseInfo?.ChildCount }} 명<span class="price">{{
            Format2(returnInfo?.totalPrice)
          }}</span></span
        ><br />
        <span v-if="chooseInfo.InfantCount >= 1"
          >소아 {{ chooseInfo?.InfantCount }} 명<span class="price">{{
            Format3(returnInfo?.totalPrice)
          }}</span></span
        ><br />
        <hr />
      </div>
      <div class="pTotalPrice">
        <span class="pTotal">총액</span
        ><span class="price">{{ AddComma1(returnInfo?.totalPrice) }} 원</span>
      </div>
    </div>

    <div class="pStartInfo">
      <button type="button">
        <div>가는 편</div>
        <span>│</span>
        <div class="pStartInfo1">
          {{ chooseInfo?.fromArea }} &nbsp; →&nbsp;
          {{ chooseInfo?.toArea }}
          &nbsp;&nbsp;{{ flight1 }}
        </div>
        <span>│</span>
        <div class="pStartInfo2">
          {{ chooseInfo?.startYear }}-{{ chooseInfo?.startMonth }}-{{
            chooseInfo?.startDay
          }}({{ chooseInfo?.startWeek }}) {{ startInfo?.startTime1 }} ~
          {{ startInfo?.arriveTime1 }} &nbsp; {{ chooseInfo?.seat }}
          {{ startInfo?.seatClass1 }}
        </div>
      </button>
    </div>
    <br />
    <div class="pArriveInfo" v-if="chooseInfo?.returnYear">
      <button type="button">
        <div>오는 편</div>
        <span>│</span>
        <div class="pArriveInfo1">
          {{ chooseInfo?.toArea }} &nbsp; →&nbsp;
          {{ chooseInfo?.fromArea }}
          &nbsp;&nbsp; {{ flight2 }}
        </div>
        <span>│</span>
        <div class="pArriveInfo2">
          {{ chooseInfo?.returnYear }}-{{ chooseInfo?.returnMonth }}-{{
            chooseInfo?.returnDay
          }}({{ chooseInfo?.returnWeek }}) {{ returnInfo?.startTime2 }} ~
          {{ returnInfo?.arriveTime2 }} &nbsp; {{ chooseInfo?.seat }}
          {{ returnInfo?.seatClass2 }}
        </div>
      </button>
    </div>

    <br /><br />
    <!--승객 정보-->
    <br />
    <div class="passengerInfoTitle">
      승객 정보
      <br />
      <h5>[<span class="asterisk"> * </span>는 필수 입력 사항입니다.]</h5>
    </div>

    <div class="pasNameHint">
      <span>
        예약 후 성명 변경은 불가하오니 여권에 기재된 성명을 정확히 확인하시기
        바랍니다.<br />
        발음상 변화가 없는 단순 영문 성명 철자변경의 경우 로그인한 본인에 한해
        변경하기를 이용하시기 바랍니다.
      </span>
    </div>
    <!--회원 예약자 정보-->
    <div class="passengerBox">
      <div class="passengerTitle" @click="showPassInfo1">
        성인 1 [예약자]<span class="arrow1">{{ arrow }}</span>
      </div>
      <div v-if="passInfo" class="passInfo">
        <div class="passInfo1">
          <div class="passInfo1-1">
            <h5>승객 성<span class="asterisk"> *</span></h5>
            <br />
            <!--회원-->
            <input
              type="text"
              v-if="isLogin()"
              v-model="korLastName1"
              class="passText"
            />
            <!--비회원-->
            <input
              type="text"
              v-if="!isLogin()"
              placeholder="예) 홍"
              v-model="korLastName1"
              class="passText"
            />
          </div>
          <div class="passInfo1-2">
            <h5>승객 이름<span class="asterisk"> *</span></h5>
            <br />
            <!--회원-->
            <input
              type="text"
              v-if="isLogin()"
              v-model="korFirstName1"
              class="passText"
            />
            <!--비회원-->
            <input
              type="text"
              v-if="!isLogin()"
              placeholder="예) 길동"
              v-model="korFirstName1"
              class="passText"
            />
          </div>
        </div>

        <div class="passInfo1">
          <div class="passInfo1-1">
            <h5>영문 성<span class="asterisk"> *</span></h5>
            <br />
            <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
            <input
              type="text"
              @change="LastNameTest"
              placeholder="예) HONG"
              v-model="engLastName1"
              class="passText"
            />
          </div>
          <div class="passInfo1-2">
            <h5>영문 이름<span class="asterisk"> *</span></h5>
            <br />
            <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
            <input
              type="text"
              @change="FirstNameTest"
              placeholder="예) GILDONG"
              v-model="engFirstName1"
              class="passText"
            />
          </div>
        </div>

        <div class="passInfo1-email" v-if="!this.email">
          <div class="passInfo1-1-email">
            <h5>이메일<span class="asterisk"> *</span></h5>
            <br />
            <input type="text" v-model="passEmail1" class="passEmail" />
            <span class="emailAtSign">@</span>
            <select
              v-if="emailSelect"
              v-model="passEmail2"
              id="passEmail2"
              class="emailSelect"
              @change="showTextbar"
            >
              <option selected disabled>이메일을 입력해주세요.</option>
              <option>naver.com</option>
              <option>nate.com</option>
              <option>gmail.com</option>
              <option>직접입력</option>
            </select>
            <input
              type="text"
              v-if="emailText"
              v-model="passEmail2"
              id="passEmail2"
              class="emailText"
            />
          </div>
          <div class="passInfo1-2-email"></div>
          <h5 id="hint-email">
            <img src="@/assets/email.jpg" class="hint-email-img" />
            &nbsp;
            <span class="hint-email-text">
              <span class="asterisk"> *</span>
              예약시 이메일 입력으로 예약 조회가 가능합니다.
            </span>
          </h5>
        </div>

        <div class="passInfo2">
          <div class="passInfo2-1">
            <h5>성별<span class="asterisk"> *</span></h5>
            <br />
            <input
              type="button"
              value="남자"
              class="maleBtn"
              id="maleBtn1"
              @click="Gender2"
            />
            <input
              type="button"
              value="여자"
              class="femaleBtn"
              id="femaleBtn1"
              @click="Gender3"
            />
          </div>
          <div class="passInfo2-2">
            <h5>
              생년 월일 (MMDD)
              <span class="asterisk" disabled="disabled"> *</span>
            </h5>
            <br />
            <!--회원-->
            <input
              type="text"
              v-if="isLogin()"
              class="passText"
              v-model="birthday1"
            />
            <!--비회원-->
            <input
              type="text"
              v-if="!isLogin()"
              placeholder="예) MMDD"
              v-model="birthday1"
              class="passText"
            />
          </div>
        </div>
        <div class="passInfo3">
          <span class="insertTip"
            ><span class="asterisk"> *</span> 모든 항목은 공백을 포함하지
            않습니다.</span
          >
          <button
            type="button"
            @click="addPassInfo1(pas)"
            class="passCheckBtn"
            id=""
          >
            확인
          </button>
        </div>
      </div>
      <br />
    </div>

    <!--추가 예약자 정보(성인)-->
    <div
      class="passengerBox"
      v-for="(pas, index) in parseInt(chooseInfo?.AdultCount) - 1"
      :key="index"
    >
      <div class="passengerTitle" @click="showPassInfo2(pas)">
        <span> 성인 {{ pas + 1 }} </span>
        <span class="arrow1">{{ arrow }}</span>
      </div>
      <span v-if="pas == addPas1" class="passInfo">
        <div class="passInfo">
          <div class="passInfo1">
            <div class="passInfo1-1">
              <h5>승객 성<span class="asterisk"> *</span></h5>
              <br />
              <input
                type="text"
                placeholder="예) 홍"
                v-model="korLastName2"
                class="passText"
              />
            </div>
            <div class="passInfo1-2">
              <h5>승객 이름<span class="asterisk"> *</span></h5>
              <br />
              <input
                type="text"
                placeholder="예) 길동"
                v-model="korFirstName2"
                class="passText"
              />
            </div>
          </div>

          <div class="passInfo1">
            <div class="passInfo1-1">
              <h5>영문 성<span class="asterisk"> *</span></h5>
              <br />
              <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
              <input
                type="text"
                @change="LastNameTest"
                placeholder="예) HONG"
                v-model="engLastName2"
                class="passText"
              />
            </div>
            <div class="passInfo1-2">
              <h5>영문 이름<span class="asterisk"> *</span></h5>
              <br />
              <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
              <input
                type="text"
                @change="FirstNameTest"
                placeholder="예) GILDONG"
                v-model="engFirstName2"
                class="passText"
              />
            </div>
          </div>

          <div class="passInfo2">
            <div class="passInfo2-1">
              <h5>성별<span class="asterisk"> *</span></h5>
              <br />
              <input
                type="radio"
                class="maleRadio"
                name="gender"
                value="남자"
              />
              <span class="maleRadioLabel">남자</span>
              <input
                type="radio"
                class="femaleRadio"
                name="gender"
                value="여자"
              />
              <span class="femaleRadioLabel">여자</span>
            </div>
            <div class="passInfo2-2" style="margin-left: 267px">
              <h5>
                생년 월일 (MMDD)
                <span class="asterisk" disabled="disabled"> *</span>
              </h5>
              <br />
              <input
                type="text"
                class="passText"
                v-model="birthday2"
                placeholder="예) 0101"
              />
            </div>
          </div>
          <div class="passInfo3">
            <span class="insertTip"
              ><span class="asterisk"> *</span> 모든 항목은 공백을 포함하지
              않습니다.</span
            >
            <button
              type="button"
              @click="addPassInfo2(pas)"
              class="passCheckBtn"
              id=""
            >
              확인
            </button>
          </div>
        </div>
        <br />
      </span>
    </div>

    <!--추가 예약자 정보(유아)-->
    <div
      class="passengerBox"
      v-for="(pas, index) in parseInt(chooseInfo?.ChildCount)"
      :key="index"
    >
      <div class="passengerTitle" @click="showPassInfo3(pas)">
        <span> 유아 {{ pas }} </span>
        <span class="arrow1">{{ arrow }}</span>
      </div>
      <span v-if="pas == addPas2" class="passInfo">
        <div class="passInfo">
          <div class="passInfo1">
            <div class="passInfo1-1">
              <h5>승객 성<span class="asterisk"> *</span></h5>
              <br />
              <input
                type="text"
                placeholder="예) 홍"
                v-model="korLastName2"
                class="passText"
              />
            </div>
            <div class="passInfo1-2">
              <h5>승객 이름<span class="asterisk"> *</span></h5>
              <br />
              <input
                type="text"
                placeholder="예) 길동"
                v-model="korFirstName2"
                class="passText"
              />
            </div>
          </div>

          <div class="passInfo1">
            <div class="passInfo1-1">
              <h5>영문 성<span class="asterisk"> *</span></h5>
              <br />
              <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
              <input
                type="text"
                @change="LastNameTest"
                placeholder="예) HONG"
                v-model="engLastName2"
                class="passText"
              />
            </div>
            <div class="passInfo1-2">
              <h5>영문 이름<span class="asterisk"> *</span></h5>
              <br />
              <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
              <input
                type="text"
                @change="FirstNameTest"
                placeholder="예) GILDONG"
                v-model="engFirstName2"
                class="passText"
              />
            </div>
          </div>

          <div class="passInfo2">
            <div class="passInfo2-1">
              <h5>성별<span class="asterisk"> *</span></h5>
              <br />
              <input
                type="radio"
                class="maleRadio"
                name="gender"
                value="남자"
              />
              <span class="maleRadioLabel">남자</span>
              <input
                type="radio"
                class="femaleRadio"
                name="gender"
                value="여자"
              />
              <span class="femaleRadioLabel">여자</span>
            </div>
            <div class="passInfo2-2" style="margin-left: 267px">
              <h5>
                생년 월일 (MMDD)
                <span class="asterisk" disabled="disabled"> *</span>
              </h5>
              <br />
              <input
                type="text"
                class="passText"
                v-model="birthday2"
                placeholder="예) 0101"
              />
            </div>
          </div>
          <div class="passInfo3">
            <span class="insertTip"
              ><span class="asterisk"> *</span> 모든 항목은 공백을 포함하지
              않습니다.</span
            >
            <button
              type="button"
              @click="addPassInfo3(pas)"
              class="passCheckBtn"
              id=""
            >
              확인
            </button>
          </div>
        </div>
        <br />
      </span>
    </div>

    <!--추가 예약자 정보(소아)-->
    <div
      class="passengerBox"
      v-for="(pas, index) in parseInt(chooseInfo?.InfantCount)"
      :key="index"
    >
      <div class="passengerTitle" @click="showPassInfo4(pas)">
        <span> 소아 {{ pas }} </span>
        <span class="arrow1">{{ arrow }}</span>
      </div>
      <span v-if="pas == addPas3" class="passInfo">
        <div class="passInfo">
          <div class="passInfo1">
            <div class="passInfo1-1">
              <h5>승객 성<span class="asterisk"> *</span></h5>
              <br />
              <input
                type="text"
                placeholder="예) 홍"
                v-model="korLastName2"
                class="passText"
              />
            </div>
            <div class="passInfo1-2">
              <h5>승객 이름<span class="asterisk"> *</span></h5>
              <br />
              <input
                type="text"
                placeholder="예) 길동"
                v-model="korFirstName2"
                class="passText"
              />
            </div>
          </div>

          <div class="passInfo1">
            <div class="passInfo1-1">
              <h5>영문 성<span class="asterisk"> *</span></h5>
              <br />
              <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
              <input
                type="text"
                @change="LastNameTest"
                placeholder="예) HONG"
                v-model="engLastName2"
                class="passText"
              />
            </div>
            <div class="passInfo1-2">
              <h5>영문 이름<span class="asterisk"> *</span></h5>
              <br />
              <h5 id="hint">보기와 같이 정확하게 입력해주세요.</h5>
              <input
                type="text"
                @change="FirstNameTest"
                placeholder="예) GILDONG"
                v-model="engFirstName2"
                class="passText"
              />
            </div>
          </div>

          <div class="passInfo2">
            <div class="passInfo2-1">
              <h5>성별<span class="asterisk"> *</span></h5>
              <br />
              <input
                type="radio"
                class="maleRadio"
                name="gender"
                value="남자"
              />
              <span class="maleRadioLabel">남자</span>
              <input
                type="radio"
                class="femaleRadio"
                name="gender"
                value="여자"
              />
              <span class="femaleRadioLabel">여자</span>
            </div>
            <div class="passInfo2-2" style="margin-left: 267px">
              <h5>
                생년 월일 (MMDD)
                <span class="asterisk" disabled="disabled"> *</span>
              </h5>
              <br />
              <input
                type="text"
                class="passText"
                v-model="birthday2"
                placeholder="예) 0101"
              />
            </div>
          </div>
          <div class="passInfo3">
            <span class="insertTip"
              ><span class="asterisk"> *</span> 모든 항목은 공백을 포함하지
              않습니다.</span
            >
            <button
              type="button"
              @click="addPassInfo4(pas)"
              class="passCheckBtn"
              id=""
            >
              확인
            </button>
          </div>
        </div>
        <br />
      </span>
    </div>
  </div>

  <button class="note" @click="showNoteInfo">
    <div class="noteTitle">
      유의사항 <span class="arrow1">{{ arrow }}</span>
    </div>
    <div class="note1" v-if="noteInfo">
      <span class="asterisk">*</span> 예약 후 성명 변경은 불가하오니 실제
      탑승하실 분의 여권에 기재된 영문 성명으로 정확하게 입력하시기 바랍니다.
    </div>
  </button>

  <div class="consentInfoTitle">확인 및 동의</div>
  <div class="consent" v-if="consentInfo">
    <h4>
      <button
        type="button"
        @click="consent1"
        id="consentBtn1"
        class="consentBtn"
        v-if="(consentBtn1 = true)"
      >
        ✔ 동의
      </button>
      &nbsp; <span v-if="false">{{ this.$store.state.consentBtn1 }}</span
      >[필수] 운송약관, 운임 규정, 수하물 규정을 확인하였으며 이에 동의합니다.
    </h4>
    <span>
      Fastrip 항공권을 구매하시는 것은 본 항공사와 운송계약 체결에 동의하는
      것으로 운임규정은 항공권 변경, 취소 등에 따른 수수료와 사전좌석배정,
      <br />
      좌석승급 등 구매하는 항공권 운임에 적용되는 세부 조건을 기재하고 있으며,
      운송 약관은 운송 계약 체결에 따른 계약조건을 명시합니다.<br />
      Fastrip은 '항공교통이용자 보호기준'에 따라 항공교통이용자 서비스 계획을
      게시합니다.
    </span>
    <br /><br />
    <h4>
      <button
        type="button"
        @click="consent2"
        id="consentBtn2"
        class="consentBtn"
        v-if="(consentBtn2 = true)"
      >
        ✔ 동의
      </button>
      &nbsp; <span v-if="false">{{ this.$store.state.consentBtn2 }}</span
      >[필수] 위험품 안내를 확인하였습니다.
      <button type="button" class="IATAList" @click="IATAModalPopUp()">
        보기
      </button>
    </h4>
    <span>
      고객 안전을 위하여 항공기 내부로 반입이 금지된 폭발성, 인화성, 유독성 물질
      및 무기로 사용될 수 있는 품목에 대한 안내 사항 입니다.<br />
      예약 후 성명 변경은 불가하오니 실제 탑승하실 분의 여권에 기재된 영문
      성명으로 정확하게 입력하시기 바랍니다. 성명 입력 안내
    </span>
  </div>

  <button class="document" @click="showDocumentInfo">
    <div class="documentTitle">
      구비 서류 안내 <span class="arrow1">{{ arrow }}</span>
    </div>
    <div class="document1" v-if="documentInfo">
      <h4>항공보안법 관련 안내사항</h4>
      <span>
        항공보안법 규정에 따라 성인, 소아, 유아 전 승객의 신원확인을 위해
        신분증명서와 탑승권 확인이 필요하오니 소지 여부를 반드시 재확인하여
        주시기 바랍니다.<br />
        모든 신분증 및 여행서류는 탑승자의 책임 하에 준비되어야 합니다.
      </span>
    </div>
  </button>

  <div class="paymentInfoTitle">결제</div>
  <div class="payment">
    <div class="pay1">
      <div class="cardPay" @click="ing">신용/체크카드</div>
      <div class="pay" @click="ing">
        <img
          src="@/assets/pay/naverPay.png"
          style="width: 35px; height: 30px; border-radius: 10px"
        />
        &nbsp; 네이버페이
      </div>
      <div class="pay" @click="ing">
        <img
          src="@/assets/pay/samsungPay.png"
          style="width: 40px; height: 30px"
        />
        삼성페이
      </div>
    </div>
    <div class="pay2">
      <div class="pay" @click="ing">
        <img
          src="@/assets/pay/kakaoPay.jpg"
          style="width: 35px; height: 30px; border-radius: 10px"
        />
        &nbsp; 카카오페이
      </div>
      <div class="pay" @click="ing">
        <img src="@/assets/pay/payco.jpg" style="width: 60px; height: 30px" />
        PAYCO
      </div>
      <div class="pointPay" @click="PointPayment">포인트 결제</div>
    </div>
    <div class="holdPoint" v-if="PointPaymentInfo">
      보유하신 포인트 : {{ AddComma2(holdPoint) + "p" }}
      <button type="button" class="addPoint" @click="addPoint">
        포인트 충전하기
      </button>
    </div>
  </div>

  <div class="payFootNav">
    <div class="payFootNav1">예상 결제 금액</div>
    <div class="payStartPrice">{{ AddComma1(returnInfo?.totalPrice) }} 원</div>
    <button type="button" class="paySubmitBtn" @click="PayModalPopUp()">
      예약 하기
    </button>
  </div>

  <div
    v-if="PayModalView"
    class="PayModalView"
    :class="{ active: PayModalView }"
  >
    <PayModal
      :PayModalView="PayModalView"
      :passEmail1="passEmail1"
      :passEmail2="passEmail2"
      :korLastName="korLastName1"
      :korFirstName="korFirstName1"
      :engLastName="engLastName1"
      :engFirstName="engFirstName1"
      :birthday="birthday1"
      :gender="gender1"
      :addAdult="addAdult"
      :addChild="addChild"
      :addInfant="addInfant"
      :totalPoint="totalPoint"
      @closeModal="closeModal"
    ></PayModal>
  </div>

  <div
    v-if="IATAModalView"
    class="IATAModalView"
    :class="{ active: IATAModalView }"
  >
    <IATAModal @close="IATAModalPopUp"></IATAModal>
  </div>
</template>

<script>
import PayModal from "@/components/PayModal.vue";
import IATAModal from "@/components/IATAModal.vue";

export default {
  name: "HelloWorld",
  components: {
    PayModal,
    IATAModal,
  },
  data() {
    return {
      chooseInfo: this.$store.state.chooseInfo,
      startInfo: this.$store.state.startInfo,
      returnInfo: this.$store.state.returnInfo,
      selectPrice: this.startPrice,
      seatPrice: 0,
      price: 0,
      totalPoint: 0,
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
      gender2: document.getElementById("maleBtn2"),
      autofocus: true,
      PayModalView: false,
      IATAModalView: false,
      PointPaymentInfo: false,
      holdPoint: this.$store.state.holdPoint,
      name: "",
      email: this.$store.state.userInfo.email,
      emailText: false,
      emailSelect: true,
      addAdult: [],
      addChild: [],
      addInfant: [],
    };
  },
  props: {},
  methods: {
    showPassInfo1() {
      this.passInfo = this.passInfo ? false : true;

      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }
    },
    showPassInfo2(value) {
      //승객 정보 입력란 초기화
      this.korLastName2 = "";
      this.korFirstName2 = "";
      this.engLastName2 = "";
      this.engFirstName2 = "";
      this.birthday2 = "";

      this.addPas1 = value ? false : true; //성인 입력창

      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }

      return (this.addPas1 = value);
    },
    showPassInfo3(value) {
      //승객 정보 입력란 초기화
      this.korLastName2 = "";
      this.korFirstName2 = "";
      this.engLastName = "";
      this.engFirstName = "";
      this.birthday2 = "";

      this.addPas2 = value ? false : true; //유아 입력창

      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }

      return (this.addPas2 = value);
    },
    showPassInfo4(value) {
      //승객 정보 입력란 초기화
      this.korLastName2 = "";
      this.korFirstName2 = "";
      this.engLastName2 = "";
      this.engFirstName2 = "";
      this.birthday2 = "";

      this.addPas3 = value ? false : true; //소아 입력창

      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }

      return (this.addPas3 = value);
    },
    addPassInfo1() {
      // 예약자 정보
      let str_space = /\s/; // 공백 체크

      //비회원 예약시 이메일 누락시 경고
      if (this.$store.state.isLogin == false) {
        if (this.passEmail1 == null || this.passEmail2 == null) {
          alert("이메일을 입력해주세요.");
          return false;
        }
      }

      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }

      //유효성 검사1
      if (this.korLastName1 == null) {
        alert("승객 성을 확인해 주세요.");
        return false;
      } else if (this.korFirstName1 == null) {
        alert("승객 이름을 확인해 주세요");
        return false;
      } else if (this.engLastName1 == "") {
        alert("영문 성을 확인해 주세요.");
        return false;
      } else if (this.engFirstName1 == null) {
        alert("영문 이름을 확인해 주세요");
        return false;
      } else if (this.gender1 == null) {
        alert("성별을 확인해 주세요.");
        return false;
      } else if (this.birthday1 == null) {
        alert("생년 월일을 확인해 주세요.");
        return false;
      } else if (str_space.exec(this.engLastName1)) {
        // 공백 체크
        alert("영문 성 항목에는 공백을 사용할 수 없습니다.");
        return false;
      } else if (str_space.exec(this.engFirstName1)) {
        // 공백 체크
        alert("영문 이름 항목에는 공백을 사용할 수 없습니다.");
        return false;
      }

      //유효성 검사2-1
      else if (this.LastNameTest() == false) {
        if (this.FirstNameTest() == false) {
          return false;
        }
      }

      //유효성 검사2-2
      if (this.LastNameTest() != false) {
        if (this.FirstNameTest() != false) {
          this.addPas1 = true;
          if (this.chooseInfo.AdultCount == 1) {
            this.addPas2 = true;
          }
        }
      }

      //예약자 정보 추가
      if (this.addAdult[0] == undefined) {
        this.addAdult.push({
          korName: this.korLastName1 + this.korFirstName1,
          engLastName: this.engLastName1,
          engFirstName: this.engFirstName1,
          gender: this.gender1,
          birthday: this.birthday1,
        });
      }

      //기존 예약자 정보가 입력된 경우 수정
      if (this.addAdult[0] != undefined) {
        this.addAdult.splice(0, 1, {
          korName: this.korLastName1 + this.korFirstName1,
          engLastName: this.engLastName1,
          engFirstName: this.engFirstName1,
          gender: this.gender1,
          birthday: this.birthday1,
        });
      }

      this.addPas1 = true;
      return (this.passInfo = false);
    },
    addPassInfo2(value) {
      // 추가 승객(성인)
      let genderSelected = document.querySelector(
        "input[type=radio][name=gender]:checked"
      );

      if (this.korLastName2 == "") {
        alert("성을 확인해 주세요.");
        return false;
      } else if (this.korFirstName2 == "") {
        alert("이름을 확인해 주세요");
        return false;
      } else if (this.engLastName2 == "") {
        alert("영문 성을 확인해 주세요.");
        return false;
      } else if (this.engFirstName2 == null) {
        alert("영문 이름을 확인해 주세요");
        return false;
      } else if (genderSelected == null) {
        alert("성별을 확인해 주세요.");
        return false;
      } else if (this.birthday2 == null) {
        alert("생년 월일을 확인해 주세요.");
        return false;
      }

      if (this.LastNameTest() != false) {
        if (this.FirstNameTest() != false) {
          return this.addPassInfo2Push(value);
        }
      }
    },
    addPassInfo2Push(value) {
      // 추가 승객(성인) 정보
      let genderSelected = document.querySelector(
        "input[type=radio][name=gender]:checked"
      );

      //해당 인덱스 공란 경우 등록
      if (this.addAdult[value] == undefined) {
        this.addAdult.push({
          korName: this.korLastName2 + this.korFirstName2,
          engLastName: this.engLastName2,
          engFirstName: this.engFirstName2,
          gender: genderSelected.value,
          birthday: this.birthday2,
        });
      }

      //해당 인덱스 기존 정보가 입력된 경우 수정
      if (this.addAdult[value] != undefined) {
        this.addAdult.splice(value, 1, {
          korName: this.korLastName2 + this.korFirstName2,
          engLastName: this.engLastName2,
          engFirstName: this.engFirstName2,
          gender: genderSelected.value,
          birthday: this.birthday2,
        });
      }

      //승객 정보 입력란 초기화
      this.korLastName2 = "";
      this.korFirstName2 = "";
      this.engLastName2 = "";
      this.engFirstName2 = "";
      this.birthday2 = "";
      genderSelected == false;

      this.addPas1 = value + 1 ? false : true; //성인 입력창

      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }

      if (value + 1 == Math.max(this.chooseInfo.AdultCount)) {
        this.addPas2 = true; //유아 입력창
        return (this.addPas1 = false); //성인 입력창
      }

      return (this.addPas1 = value + 1); // 인원 최대값 이전까지 1씩 증가
    },
    addPassInfo3(value) {
      // 추가 승객(유아)
      let genderSelected = document.querySelector(
        "input[type=radio][name=gender]:checked"
      );

      if (this.korLastName2 == "") {
        alert("성을 확인해 주세요.");
        return false;
      } else if (this.korFirstName2 == "") {
        alert("이름을 확인해 주세요");
        return false;
      } else if (this.engLastName2 == "") {
        alert("영문 성을 확인해 주세요.");
        return false;
      } else if (this.engFirstName2 == null) {
        alert("영문 이름을 확인해 주세요");
        return false;
      } else if (genderSelected == null) {
        alert("성별을 확인해 주세요.");
        return false;
      } else if (this.birthday2 == null) {
        alert("생년 월일을 확인해 주세요.");
        return false;
      }

      if (this.LastNameTest() != false) {
        if (this.FirstNameTest() != false) {
          return this.addPassInfo3Push(value);
        }
      }
    },
    addPassInfo3Push(value) {
      // 추가 승객(유아) 정보
      let genderSelected = document.querySelector(
        "input[type=radio][name=gender]:checked"
      );

      //해당 인덱스 공란 경우 등록
      if (this.addChild[value - 1] == undefined) {
        this.addChild.push({
          korName: this.korLastName2 + this.korFirstName2,
          engLastName: this.engLastName2,
          engFirstName: this.engFirstName2,
          gender: genderSelected.value,
          birthday: this.birthday2,
        });
      }

      //해당 인덱스 기존 정보가 입력된 경우 수정
      if (this.addChild[value - 1] != undefined) {
        this.addChild.splice(value - 1, 1, {
          korName: this.korLastName2 + this.korFirstName2,
          engLastName: this.engLastName2,
          engFirstName: this.engFirstName2,
          gender: genderSelected.value,
          birthday: this.birthday2,
        });
      }

      //승객 정보 입력란 초기화
      this.korLastName2 = "";
      this.korFirstName2 = "";
      this.engLastName2 = "";
      this.engFirstName2 = "";
      this.birthday2 = "";
      genderSelected == false;

      this.addPas2 = value ? false : true;

      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }

      if (value + 1 > Math.max(this.chooseInfo.ChildCount)) {
        this.addPas3 = true; //유아 입력창
        return (this.addPas2 = false); //성인 입력창
      }

      return (this.addPas2 = value + 1); // 인원 최대값 이전까지 1씩 증가
    },
    addPassInfo4(value) {
      // 추가 승객(소아)
      let genderSelected = document.querySelector(
        "input[type=radio][name=gender]:checked"
      );

      if (this.korLastName2 == "") {
        alert("성을 확인해 주세요.");
        return false;
      } else if (this.korFirstName2 == "") {
        alert("이름을 확인해 주세요");
        return false;
      } else if (this.engLastName2 == "") {
        alert("영문 성을 확인해 주세요.");
        return false;
      } else if (this.engFirstName2 == null) {
        alert("영문 이름을 확인해 주세요");
        return false;
      } else if (genderSelected == null) {
        alert("성별을 확인해 주세요.");
        return false;
      } else if (this.birthday2 == null) {
        alert("생년 월일을 확인해 주세요.");
        return false;
      }

      if (this.LastNameTest() != false) {
        if (this.FirstNameTest() != false) {
          return this.addPassInfo4Push(value);
        }
      }
    },
    addPassInfo4Push(value) {
      // 추가 승객(소아) 정보
      let genderSelected = document.querySelector(
        "input[type=radio][name=gender]:checked"
      );

      //해당 인덱스 공란 경우 등록
      if (this.addInfant[value - 1] == undefined) {
        this.addInfant.push({
          korName: this.korLastName2 + this.korFirstName2,
          engLastName: this.engLastName2,
          engFirstName: this.engFirstName2,
          gender: genderSelected.value,
          birthday: this.birthday2,
        });
      }

      //해당 인덱스 기존 정보가 입력된 경우 수정
      if (this.addInfant[value - 1] != undefined) {
        this.addInfant.splice(value - 1, 1, {
          korName: this.korLastName2 + this.korFirstName2,
          engLastName: this.engLastName2,
          engFirstName: this.engFirstName2,
          gender: genderSelected.value,
          birthday: this.birthday2,
        });
      }

      //승객 정보 입력란 초기화
      this.korLastName2 = "";
      this.korFirstName2 = "";
      this.engLastName2 = "";
      this.engFirstName2 = "";
      this.birthday2 = "";
      genderSelected == false;

      this.addPas3 = value ? false : true;

      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }

      if (value + 1 > Math.max(this.chooseInfo.InfantCount)) {
        return (this.addPas3 = false);
      }

      return (this.addPas3 = value + 1); // 인원 최대값 이전까지 1씩 증가
    },
    ing() {
      alert("준비중입니다.");
    },
    isLogin() {
      return this.$store.state.isLogin;
    },
    showTextbar() {
      if (this.passEmail2 == "직접입력") {
        this.emailSelect = false;
        this.emailText = true;
      }
    },
    Format1(value) {
      let regexp = /\B(?=(\d{3})+(?!\d))/g;
      let price = value * this.chooseInfo.AdultCount;
      return price.toString().replace(regexp, ",") + " 원";
    },
    Format2(value) {
      let regexp = /\B(?=(\d{3})+(?!\d))/g;
      let price = value * 0.8 * this.chooseInfo.ChildCount;
      return "(-20%)" + price.toString().replace(regexp, ",") + " 원";
    },
    Format3(value) {
      let regexp = /\B(?=(\d{3})+(?!\d))/g;
      let price = value * 0.5 * this.chooseInfo.InfantCount;
      return "(-50%)" + price.toString().replace(regexp, ",") + " 원";
    },
    LastNameTest() {
      let str_space = /\s/; // 공백 체크

      if (str_space.exec(this.korLastName1)) {
        // 공백 체크
        alert("승객 성 항목에는 공백을 사용할 수 없습니다.");
        return false;
      } else if (str_space.exec(this.engLastName1)) {
        // 공백 체크
        alert("영문 성 항목에는 공백을 사용할 수 없습니다.");
        return false;
      } else if (str_space.exec(this.korLastName2)) {
        // 공백 체크
        alert("승객 성 항목에는 공백을 사용할 수 없습니다.");
        return false;
      } else if (str_space.exec(this.engLastName2)) {
        // 공백 체크
        alert("영문 성 항목에는 공백을 사용할 수 없습니다.");
        return false;
      } else if (str_space.exec(this.birthday1)) {
        // 공백 체크
        alert("생일 항목에는 공백을 사용할 수 없습니다.");
        return false;
      }

      if (this.engLastName1 == null) {
        alert("영문 성을 확인해주세요");
        return false;
      } else if (
        /^[A-Z\s]*$/.test(this.engLastName1) == false &&
        /^[A-Z\s]*$/.test(this.engLastName2) == false
      ) {
        // 초기화
        alert("영문 성을 대문자로 입력해 주세요.");
        this.engLastName1 = "";
        return false;
      }
    },
    FirstNameTest() {
      let str_space = /\s/; // 공백 체크

      if (str_space.exec(this.korFirstName1)) {
        // 공백 체크
        alert("승객 이름 항목에는 공백을 사용할 수 없습니다.");
        return false;
      } else if (str_space.exec(this.engFirstName1)) {
        // 공백 체크
        alert("영어 이름 항목에는 공백을 사용할 수 없습니다.");
        this.engFirstName1 = "";
        return false;
      } else if (str_space.exec(this.korFirstName2)) {
        // 공백 체크
        alert("승객 이름 항목에는 공백을 사용할 수 없습니다.");
        return false;
      } else if (str_space.exec(this.engFirstName2)) {
        // 공백 체크
        alert("영어 이름 항목에는 공백을 사용할 수 없습니다.");
        this.engFirstName2 = "";
        return false;
      } else if (str_space.exec(this.birthday2)) {
        // 공백 체크
        alert("생일 항목에는 공백을 사용할 수 없습니다.");
        return false;
      }

      if (this.engFirstName1 == null) {
        alert("영문 이름을 확인해주세요");
        return false;
      } else if (
        /^[A-Z\s]*$/.test(this.engFirstName1) == false &&
        /^[A-Z\s]*$/.test(this.engFirstName2) == false
      ) {
        // 초기화
        alert("영문 이름을 대문자로 입력해 주세요.");
        this.engFirstName1 = "";
        return false;
      }
    },
    PointPayment() {
      this.PointPaymentInfo = true;
    },
    addPoint() {
      this.holdPoint += 500000;
      this.$store.dispatch("holdPoint", this.holdPoint);
    },
    closeModal() {
      this.PayModalView = false;
    },
    IATAModalPopUp() {
      this.IATAModalView = this.IATAModalView ? false : true;
    },
    AddComma1(num) {
      let regexp = /\B(?=(\d{3})+(?!\d))/g;
      let price = num * this.chooseInfo.AdultCount;

      if (this.chooseInfo.ChildCount >= 1 && this.chooseInfo.InfantCount >= 1) {
        let addPrice1 = price + num * this.chooseInfo.ChildCount * 0.8;
        let addPrice2 = addPrice1 + num * this.chooseInfo.InfantCount * 0.5;
        this.totalPoint = addPrice2;
        return addPrice2.toString().replace(regexp, ",");
      } else if (this.chooseInfo.ChildCount >= 1) {
        let addPrice = price + num * this.chooseInfo.ChildCount * 0.8;
        this.totalPoint = addPrice;
        return addPrice.toString().replace(regexp, ",");
      } else if (this.chooseInfo.InfantCount >= 1) {
        let addPrice = price + num * this.chooseInfo.InfantCount * 0.5;
        this.totalPoint = addPrice;
        return addPrice.toString().replace(regexp, ",");
      }

      this.totalPoint = price;
      return price.toString().replace(regexp, ",");
    },
    AddComma2(num) {
      let regexp = /\B(?=(\d{3})+(?!\d))/g;

      return num.toString().replace(regexp, ",");
    },
    consent1() {
      if (this.consentBtn1 == true) {
        const target = document.getElementById("consentBtn1");
        target.style.color = "white";
        target.style.background = "teal";
        target.style.border = "3px solid teal";
        this.consentBtn1 = false;
        this.$store.dispatch("consentBtn1", true);
      }
    },
    consent2() {
      if (this.consentBtn2 == true) {
        const target = document.getElementById("consentBtn2");
        target.style.color = "white";
        target.style.background = "teal";
        target.style.border = "3px solid teal";
        this.consentBtn2 = false;
        this.$store.dispatch("consentBtn2", true);
      }
    },
    Gender1() {
      if (this.gender1 == "male" || this.gender1 == "M") {
        let target = document.getElementById("maleBtn1");
        target.style.color = "teal";
        target.style.border = "3px solid teal";
        target.value = "남자" + "  ✔";
        this.gender1 = "남자";
      } else if (this.gender1 == "female" || this.gender1 == "F") {
        let target = document.getElementById("femaleBtn1");
        target.style.color = "teal";
        target.style.border = "3px solid teal";
        target.value = "여자" + "  ✔";
        this.gender1 = "여자";
      }
    },
    Gender2() {
      let t1 = document.getElementById("maleBtn1").value.substring(0, 2);

      this.gender1 = t1;

      let target1 = document.getElementById("maleBtn1");
      let target2 = document.getElementById("femaleBtn1");

      target1.style.color = "teal";
      target1.style.border = "3px solid teal";
      target1.value = "남자" + "  ✔";

      target2.style.color = "#999";
      target2.style.border = "3px solid #999";
      target2.value = "여자";
    },
    Gender3() {
      let t2 = document.getElementById("femaleBtn1").value.substring(0, 2);

      this.gender1 = t2;

      let target1 = document.getElementById("maleBtn1");
      let target2 = document.getElementById("femaleBtn1");
      target2.style.color = "teal";
      target2.style.border = "3px solid teal";
      target2.value = "여자" + "  ✔";

      target1.style.color = "#999";
      target1.style.border = "3px solid #999";
      target1.value = "남자";
    },
    Fare(value) {
      let regexp = /\B(?=(\d{3})+(?!\d))/g;
      let Fare = Math.floor(parseInt(value) * 0.7) + " 원";
      return Fare.toString().replace(regexp, ",");
    },
    Fuel(value) {
      let regexp = /\B(?=(\d{3})+(?!\d))/g;
      let Fuel = Math.floor(parseInt(value) * 0.2) + " 원";
      return Fuel.toString().replace(regexp, ",");
    },
    Tax(value) {
      let regexp = /\B(?=(\d{3})+(?!\d))/g;
      let Tax = Math.floor(parseInt(value) * 0.1) + " 원";
      return Tax.toString().replace(regexp, ",");
    },
    showNoteInfo() {
      this.noteInfo = this.noteInfo ? false : true;
      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }
    },
    showDocumentInfo() {
      this.documentInfo = this.documentInfo ? false : true;
      if (this.arrow == "▼") {
        this.arrow = "▲";
      } else {
        this.arrow = "▼";
      }
    },

    PayModalPopUp() {
      if (
        this.addAdult[0].korName == "" ||
        this.addAdult[0].engFirstName == "" ||
        this.addAdult[0].engLastName == "" ||
        this.addAdult[0].birthday == "" ||
        this.addAdult[0].gender == ""
      ) {
        alert("승객 정보를 확인해주세요");
        return false;
      }

      if (
        this.$store.state.consentBtn1 == "선택1" &&
        this.$store.state.consentBtn2 == "선택2"
      ) {
        console.log("필수 체크 완료");
      } else {
        alert("[확인 및 동의] 필수 동의 항목을 확인해주세요.");
        return false;
      }

      console.log("1");
      if (this.holdPoint < this.totalPoint) {
        alert("포인트가 모자랍니다.");
        return false;
      } else if (this.holdPoint >= this.totalPoint) {
        if (
          this.engLastName1 != null &&
          this.engFirstName1 != null &&
          this.$store.state.consentBtn1 == "선택1" &&
          this.$store.state.consentBtn2 == "선택2"
        ) {
          console.log("2");
          this.PayModalView = true;
        }
      }
    },
  },
  mounted() {
    this.Gender1();
  },
};
</script>

<style>
.PayModalView {
  padding: 20px;
  position: fixed;
  top: 45%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 35%;
  height: 70%;
  border-radius: 15px;
  background-color: white;
  box-shadow: 2px 2px 10px lightgrey;
  overflow: auto;
}

.PayModalView::-webkit-scrollbar {
  width: 10px;
  /*스크롤바의 너비*/
}

.PayModalView::-webkit-scrollbar-thumb {
  background-color: teal;
  border-radius: 10px;
  /*스크롤바의 색상*/
}

.PayModalView::-webkit-scrollbar-track {
  background-color: white;
  /*스크롤바 트랙 색상*/
}

/* 화면 축소 전 후 */

@media (max-width: 700px) {
  .PayModalView {
    content: url(@/assets/Logo2.png);
    padding: 20px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 25%;
    height: 25%;
    border-radius: 15px;
    background-color: white;
    box-shadow: 2px 2px 10px lightgrey;
  }
}

.pRight {
  width: 270px;
  height: 310px;
  margin-left: 970px;
  padding: 40px 0;
  background-color: rgba(34, 168, 168, 0.712);
  border-radius: 10px;
  position: absolute;
}

.pPayInfo {
  border-left: none;
  border-right: none;
  width: 250px;
  height: 210px;
  padding-left: 7%;
  font-size: 12px;
  font-weight: 900;
  color: white;
}

.pPayInfo h5 {
  font-weight: 900;
  font-size: 18px;
}

.pTotal {
  font-weight: 900;
  color: white;
}

.pTotalPrice {
  width: 270px;
  padding: 20px;
  padding-top: 20px;
  font-size: 14px;
  color: white;
  font-weight: 900;
}

.pPayInfo {
  color: white;
}

.pTotalPrice {
  color: white;
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
  width: 10px;
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
  display: flex;
  margin-right: 50px;
  margin-bottom: 30px;
  margin-top: 0.1%;
  font-size: 20px;
}

.pPayInfo hr {
  height: 2px;
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
  margin-top: 17px;
  margin-left: 14px;
}

.arrow1 {
  color: white;
  float: right;
  margin-right: 30px;
}

.arrow2 {
  font-size: 22px;
  font-weight: 900;
  margin-right: 9px;
  vertical-align: middle;
}

.pSchedule {
  color: black;
  color: teal;
  font-size: 24px;
  font-weight: 900;
  margin-top: 2.6%;
}

.pInfo {
  margin-left: 3%;
  width: 900px;
}

.pInfo button {
  width: 100%;
  height: 75px;
  background-color: white;
  font-size: 14px;
  border: 0.5px solid #999;
  display: flex;
  box-shadow: 4px 4px 4px rgb(68, 68, 68);
  float: left;
  margin-bottom: 30px;
}

.pInfo button div {
  padding: 22px;
}

.pInfoImg {
  margin-left: 10px;
}

.pInfo button:hover {
  border: 2px solid teal;
}

.pInfo3 {
  margin-top: 3px;
}

.consentInfoTitle {
  color: black;
  color: teal;
  font-size: 24px;
  font-weight: 900;
  padding: 10px;
  margin-left: 9%;
}

.pStartInfo,
.pArriveInfo {
  margin-left: 5%;
  width: 100px;
}

.pStartInfo button,
.pArriveInfo button {
  color: white;
  width: 850px;
  height: 70px;
  background-color: rgba(34, 168, 168, 0.689);
  font-size: 16px;
  border: 0.5px solid #999;
  display: flex;
  box-shadow: 4px 4px 4px rgb(68, 68, 68);
}

.pStartInfo button div,
.pArriveInfo button div {
  padding: 21px;
}

.pStartInfo span,
.pArriveInfo span {
  padding-top: 12px;
  font-size: 30px;
}

.payFootNav {
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
  box-shadow: 2px 2px 7px rgb(1, 83, 83);
}

.payFootNav1 {
  margin-left: 30px;
}

.paySubmitBtn {
  width: 15%;
  height: 250%;
  font-size: 16px;
  border-radius: 4px;
  color: white;
  background: teal;
  border: 1px solid white;
}

.payStartPrice {
  font-size: 14px;
  margin-left: 65%;
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
  font-size: 22px;
  font-weight: 900;
  padding: 10px;
  margin-left: 9%;
}

.passengerInfoTitle h5 {
  color: rgb(78, 78, 78);
  padding-top: 10px;
  font-size: 14px;
}

.pasNameHint {
  background-color: rgba(236, 234, 234, 0.555);
  width: 900px;
  height: 90px;
  margin-left: 8.5%;
  padding: 30px 0;
  padding-left: 40px;
  font-size: 12px;
}

.passengerTitle {
  float: left;
  font-size: 16px;
  color: white;
  background: teal;
  width: 900px;
  height: 60px;
  padding-bottom: 25px;
  padding-top: 20px;
  padding-left: 40px;
  text-align: left;
  margin-left: 8.5%;
  cursor: pointer;
}

.passText {
  width: 250px;
  height: 50px;
  float: left;
  padding: 20px;
  border: 2px solid teal;
  background-color: white;
  font-size: 10px;
}

.passInfo1-1 > h5,
.passInfo1-2 > h5,
.passInfo1-1-email > h5,
.passInfo2-1 > h5,
.passInfo2-2 > h5 {
  font-size: 12px;
}

.passText:hover {
  border: 3px solid teal;
}

.passEmail {
  width: 130px;
  height: 50px;
  float: left;
  padding: 20px;
  border: 2px solid teal;
  background-color: white;
}

.passEmail:hover {
  border: 3px solid teal;
}

.emailAtSign {
  color: teal;
  font-size: 22px;
  font-weight: 900;
  padding: 8px;
}

.emailSelect,
.emailText {
  width: 160px;
  height: 50px;
  padding-left: 10px;
  border: 2px solid teal;
  background-color: white;
  color: #999;
  font-weight: 900;
}

.emailSelect:hover {
  border: 3px solid teal;
}

.passInfo {
  width: 900px;
  border: none;
  margin-left: 8.5%;
}

.passInfo1,
.passInfo1-email {
  width: 100%;
  height: 20%;
  border: 1px solid;
  padding-top: 60px;
  padding-left: 80px;
  padding-bottom: 80px;
  display: flex;
  font-size: 13px;
}

.passInfo2 {
  width: 100%;
  height: 20%;
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

.passInfo1-2-email {
  margin-left: 120px;
}

#hint-email {
  margin-top: 50px;
  margin-right: 60px;
  font-weight: 900;
  color: #505050;
}

.hint-email-img {
  width: 35px;
  height: 35px;
}

.hint-email-text {
  font-size: 11px;
}

.maleBtn,
.femaleBtn {
  width: 140px;
  height: 50px;
  background: white;
  opacity: 0.6;
  transition: 0.3s;
  font-size: 16px;
}

.maleRadio,
.femaleRadio {
  width: 60px;
  height: 20px;
}

.maleRadioLabel,
.femaleRadioLabel {
  font-size: 20px;
  font-weight: 900;
  color: teal;
}

.maleBtn:hover,
.femaleBtn:hover {
  border: 3px solid teal;
}

.passInfo2-2 {
  margin-left: 170px;
  position: relative;
}

.passInfo3 {
  width: 100%;
  font-size: 12px;
  border: 1px solid;
  border-top: none;
}

.insertTip {
  float: right;
  margin-right: 23%;
  margin-top: 1%;
}

.passCheckBtn {
  width: 90px;
  height: 40px;
  color: white;
  background-color: teal;
  border: none;
  border-radius: 4px;
  font-weight: 900;
  font-size: 20px;
  margin-left: 740px;
  margin-bottom: 30px;
}

.note {
  width: 910px;
  border: none;
  margin-left: 8.2%;
  margin-bottom: 2%;
  background: white;
  display: block;
}

.document {
  border: none;
  margin-left: 8.2%;
  margin-bottom: 2%;
  background: white;
  display: block;
}

.noteTitle,
.documentTitle {
  font-size: 16px;
  color: white;
  background: teal;
  width: 897.5px;
  height: 60px;
  padding-bottom: 25px;
  padding-top: 20px;
  padding-left: 40px;
  margin-top: 40px;
  text-align: left;
}

.note1,
.document1 {
  border: 1px solid;
  width: 897.5px;
  text-align: left;
  padding: 20px;
  font-size: 13px;
}

.document1 > h4 {
  font-size: 18px;
}

.consent {
  width: 900px;
  padding-bottom: 25px;
  padding-top: 25px;
  padding-left: 40px;
  text-align: left;
  border: 1px solid;
  margin-left: 8.5%;
  font-size: 14px;
  margin-bottom: 2%;
}

.consent > h4 {
  font-size: 18px;
}

.consentBtn {
  color: teal;
  background-color: white;
  border: 2.5px solid teal;
  border-radius: 4px;
  padding: 8px;
  width: 70px;
  height: 40px;
  font-size: 16px;
}

.IATAList {
  float: right;
  margin-right: 40px;
  color: white;
  background-color: teal;
  border: none;
  border-radius: 4px;
  padding: 10px;
}

.paymentInfoTitle {
  color: teal;
  font-size: 20px;
  font-weight: 900;
  float: left;
  text-align: left;
  padding: 10px;
  margin-left: 9%;
}

.payment {
  width: 900px;
  height: 310px;
  border: 1px solid black;
  background: white;
  margin-bottom: 10%;
  margin-left: 8.5%;
  margin-top: 5.5%;
  font-size: 12px;
  font-weight: 900;
}

.cardPay,
.pay,
.pointPay {
  border: 1px solid black;
  border-radius: 4px;
  width: 30%;
  height: 70px;
  text-align: center;
  padding: 20px 0;
  margin: 1%;
  color: #999;
  cursor: pointer;
  margin-top: 2%;
}

.cardPay {
  padding-top: 25px;
}

.pointPay {
  padding-top: 25px;
  color: black;
  border: 2px solid teal;
}

.pay1,
.pay2 {
  display: flex;
  margin: 1%;
  margin-left: 4.5%;
}

.holdPoint {
  font-size: 18px;
  font-weight: 900;
  margin-left: 8%;
  margin-top: 3%;
}

.addPoint {
  width: 180px;
  height: 40px;
  float: right;
  font-size: 18px;
  margin-right: 80px;
  color: white;
  background-color: teal;
  border: none;
  border-radius: 4px;
}
</style>
