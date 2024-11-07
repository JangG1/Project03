<template>
  <div data-aos="fade-up" data-aos-delay="300" data-aos-easing="ease-out">
    <div class="step">
      <div>① 검색</div>
      &nbsp;
      <div class="step2">❷ 항공편</div>
      &nbsp;
      <div>③ 결제</div>
    </div>
    <div class="rtnSchedule">여행 일정</div>
    <div class="rtnInfo">
      <button type="button">
        <!-- 출발지 -> 도착지 -->
        <div class="info1">
          {{ chooseInfo?.fromArea }} &nbsp;
          <span class="arrow">⇀</span>
          {{ chooseInfo?.toArea }}
        </div>
        <img src="../assets/vertical.jpg" width="12" class="ver" />
        <div class="info2">
          <img
            class="rtnInfoImg"
            src="../assets/calendar.png"
            width="25"
            height="25"
          />
          &nbsp;
          <!-- 출발일 -->
          <span
            >{{ chooseInfo?.startYear }}-{{ chooseInfo?.startMonth }}-{{
              chooseInfo?.startDay
            }}({{ chooseInfo?.startWeek }})</span
          >
          <!-- 도착일 -->
          <span>
            ~ {{ chooseInfo?.returnYear }}-{{ chooseInfo?.returnMonth }}-{{
              chooseInfo?.returnDay
            }}({{ chooseInfo?.returnWeek }})</span
          >
        </div>
        <img src="../assets/vertical.jpg" width="12" class="ver" />
        <!-- 승객 수 -->
        <div class="info3">
          <img
            class="rtnInfoImg"
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

    <br />

    <div class="rtnStartInfo">
      <button type="button">
        <div>가는 편</div>
        <span>│</span>
        <div class="startInfo1">
          {{ chooseInfo?.fromArea }} &nbsp; →&nbsp;
          {{ chooseInfo?.toArea }}
        </div>
        <span>│</span>
        <div class="startInfo2">
          {{ chooseInfo?.startDate }} {{ startInfo?.startTime1 }} ~
          {{ startInfo?.arriveTime1 }} &nbsp; {{ chooseInfo?.seat }}
          {{ startInfo?.seatClass1 }}
        </div>
      </button>
    </div>
    <div class="day-seat-select" v-for="(res, index) in at" :key="res">
      <div type="button" class="timeSelect" style="cursor: default">
        <div class="startTime">
          <h3>{{ res.start }}</h3>
          <div class="startArea">
            {{ chooseInfo?.fromArea }} <br /><br />
            <img src="../assets/Logo.png" class="flightLogo" />
            {{ res?.flight }}
          </div>
        </div>
        <img type="image" class="arrow2" src="../assets/arrow.jpg" />
        <div class="arriveTime">
          <h3>{{ res.arrive }}</h3>
          <div class="arriveArea">{{ chooseInfo?.toArea }}</div>
        </div>
      </div>

      <button
        type="button"
        class="seatSelect"
        @click="[selectStandard(index), selectFlight(index)]"
      >
        <h3>
          {{ chooseInfo?.seat }} {{ res?.standard }}
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="30"
            height="30"
            fill="currentColor"
            class="check"
          >
            <path
              d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"
            />
          </svg>
        </h3>
        <span>{{ AddComma(res.priceStandard) + " 원" }}</span
        ><br />
        <span>{{ Math.floor(Math.random() * (10 - 1) + 1) }}석</span>
      </button>
      <button
        type="button"
        class="seatSelect"
        @click="[selectFlex(index), selectFlight(index)]"
      >
        <h3>
          {{ chooseInfo?.seat }} {{ res?.flex }}
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="30"
            height="30"
            fill="currentColor"
            class="check"
          >
            <path
              d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"
            />
          </svg>
        </h3>
        <span>{{ AddComma(res?.priceFlex) + " 원" }}</span
        ><br />
        <span>{{ Math.floor(Math.random() * (10 - 1) + 1) }}석</span>
      </button>
    </div>

    <div class="blank"></div>

    <div class="rtnFootNav">
      <span class="rtnFootNav1">예상 결제 금액</span>
      <span class="rtnStartPrice"
        >{{ AddComma(selectPrice) + " 원" }}&nbsp;</span
      >
      <button
        type="button"
        v-show="!loginCheck"
        class="rtnSubmitBtn1"
        @click="submit()"
      >
        비회원 결제 하기
      </button>
      <button type="button" class="rtnSubmitBtn2" @click="loginSubmit()">
        회원 결제 하기
      </button>
    </div>
  </div>

  <!-- 로그인 모달 -->
  <div v-if="!isLogin">
    <LoginModal
      class="loginModal"
      @closeModal="loginModal = false"
      :loginModal="loginModal"
    />
  </div>
</template>

<script>
import arrivalTime from "../components/arrivalTime.json";
import LoginModal from "@/components/LoginModal.vue";
import axios from "axios";
import AOS from "aos";
import "aos/dist/aos.css";
import { jwtDecode } from "jwt-decode";
import { EX_IP } from "../config";

const at = arrivalTime;

export default {
  name: "HelloWorld",
  components: {
    LoginModal,
  },
  data() {
    return {
      at,
      seatClass2: "",
      startTime2: "",
      arriveTime2: "",
      selectPrice: this.$store.state.startInfo.startPrice,
      loginModal: false,
      chooseInfo: this.$store.state.chooseInfo,
      startInfo: this.$store.state.startInfo,
      loginCheck: this.$store.state.isLogin,
    };
  },
  created() {
    AOS.init();
  },
  methods: {
    hideParams() {
      history.pushState(null, "", `/Return`);
    },
    setParamInfo() {
      const queryParams = this.$route.query;
      if (queryParams.token) {
        const token = queryParams.token;

        try {
          // JWT 디코드
          const decodedToken = jwtDecode(token);

          this.$store.dispatch("setUserInfo", decodedToken);

          location.reload();
        } catch (error) {
          console.error("JWT 디코딩 실패:", error);
        }
      }
    },
    getUserInfo() {
      if (this.$store.state.isLogin == true) {
        axios.get(EX_IP + ":8200/api/kakao/info").then((response) => {
          this.userInfo = response.data;
          this.$store.dispatch("setUserInfo", JSON.stringify(this.userInfo));
        });
      }
    },
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
      this.selectPrice = parseInt(this.selectPrice) + parseInt(priceStandard);
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
      this.selectPrice = parseInt(this.selectPrice) + parseInt(priceFlex);
      this.startTime2 = startTime;
      this.arriveTime2 = arriveTime;
      this.seatClass2 = seatClass;
    },
    selectFlight(index) {
      let flight = this.at[index].flight;

      this.flight2 = flight;
    },
    loginSubmit() {
      if (this.flight2 == null) {
        return alert("도착지를 선택해주세요.");
      }

      if (!this.$store.state.isLogin) {
        alert("로그인을 진행해 주세요.");
        return (this.loginModal = true);
      }

      if (this.$store.state.isLogin == true) {
        this.submit();
      }
    },
    submit() {
      let returnInfo = {};

      if (this.flight2 == null) {
        return alert("항공편을 선택해주세요.");
      }

      returnInfo = {
        flight2: this.flight2,
        seatClass2: this.seatClass2,
        startTime2: this.startTime2,
        arriveTime2: this.arriveTime2,
        totalPrice: this.selectPrice,
      };

      this.$store.dispatch("returnInfo", returnInfo);

      this.$router.push("Payment");
    },
  },
  mounted() {
    this.hideParams();
    this.setParamInfo();
  },
};
</script>

<style>
.blank {
  height: 150px;
}

.flightLogo {
  width: 20px;
  height: 20px;
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
  margin-top: 17px;
  margin-left: 14px;
}

.rtnSchedule {
  color: black;
  color: teal;
  font-size: 24px;
  font-weight: 900;
  margin-left: 7%;
  margin-top: 2%;
}

.rtnInfo {
  margin-left: auto;
  margin-right: auto;
  width: 880px;
}

.rtnInfo button {
  width: 100%;
  height: 75px;
  background-color: white;
  font-size: 14px;
  border: 0.5px solid #999;
  display: flex;
  box-shadow: 4px 4px 4px rgb(68, 68, 68);
  float: left;
  margin-bottom: 25px;
}

.rtnInfo button div {
  padding: 22px;
}

.rtnInfo button:hover {
  border: 2px solid teal;
}

.rtnInfo2 {
  margin-top: 0.3%;
}

.rtnInfo3 {
  margin-top: 0.6%;
}

.rtnStartInfo button {
  margin-left: auto;
  margin-right: auto;
  color: white;
  width: 700px;
  height: 70px;
  background-color: rgba(34, 168, 168, 0.689);
  font-size: 16px;
  border: 0.5px solid #999;
  display: flex;
  box-shadow: 4px 4px 4px rgb(68, 68, 68);
}

.rtnStartInfo span {
  padding-top: 12px;
  font-size: 30px;
}

.rtnStartInfo button div {
  padding: 21px;
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
  height: 10px;
}

.arriveTime {
  float: right;
  margin-top: 8%;
  margin-right: 8%;
}

.startTime > h3,
.arriveTime > h3 {
  font-size: 20px;
}

.startArea,
.arriveArea {
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
  margin-top: 10%;
  margin-left: 7%;
}

.seatSelect {
  border: 1px solid;
  border-top: 4px solid black;
  opacity: 0.6;
  transition: 0.3s;
  font-size: 16px;
}

.seatSelect > h3 {
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

.rtnFootNav {
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

.rtnFootNav1 {
  margin-left: 30px;
}

.rtnStartPrice {
  width: 150px;
  font-size: 14px;
  margin-left: 65%;
}

.rtnSubmitBtn1 {
  width: 15%;
  height: 250%;
  font-size: 14px;
  border-radius: 4px;
  margin-left: 2%;
  color: white;
  background: teal;
  border: 1px solid white;
}

.rtnSubmitBtn2 {
  width: 15%;
  height: 250%;
  font-size: 14px;
  font-weight: 900;
  border-radius: 4px;
  color: rgb(6, 165, 165);
  background: white;
  border: 3px solid rgba(34, 168, 168, 0.689);
  margin-left: 1%;
}

@media (min-width: 1000px) {
  .loginModal {
    content: none;
    width: 300px;
    height: 380px;
  }
}
</style>
