<template>
  <div class="part1">
    <div class="homeNavigation" id="top">
      <!-- 네비게이션 바-->

      <!-- Fastrip 로고 -->
      <div class="homeNavBar">
        <lottie-player
          class="homeLogo"
          src="https://assets9.lottiefiles.com/packages/lf20_ghibwmba.json"
          background="transparent"
          speed="1"
          loop
          autoplay
        ></lottie-player>
        <a class="homeLogoLink" href="/">Fastrip</a>
      </div>

      <div class="homeSubLink">
        <div class="homeSubRoute">
          <a class="homeLink" href="/">Home</a>
        </div>
        <div class="homeSubRoute">
          <a href="/Reservation">Reservation</a>
        </div>
        <div class="homeSubRoute">
          <a href="/Contact">Contact</a>
        </div>
      </div>

      <!-- 로그인 썸네일 -->
      <!--로그인-->
      <div class="homeLoginBtn" v-if="!isLogin">
        <lottie-player
          class="homeLoginBtn1"
          src="https://assets1.lottiefiles.com/packages/lf20_voi0gxts.json"
          background="transparent"
          speed="1"
          loop
          autoplay
        ></lottie-player>
        <div class="loginText" v-if="!isLogin" @click="loginModal = true">
          Login
        </div>
      </div>

      <div class="homeLoginBtn2" v-if="isLogin">
        <ProfileItem
          :profile="getProfile"
          :email="getEmail"
          @click="showLoginMenu"
        />

        <div class="homeLoginName" v-if="isLogin">
          {{ this.$store.state.userInfo.lastName
          }}{{ this.$store.state.userInfo.firstName }} 님
        </div>

        <div class="homeLoginMenu" v-if="loginMenu">
          <img src="@/assets/cloud.png" />
          <!--카카오 로그아웃-->
          <div
            class="homeLogoutBtn"
            @click="kakaoLogout"
            v-if="loginMenu"
            v-show="OAuth === 'kakao'"
          >
            Logout
          </div>

          <!--네이버 로그아웃-->
          <div
            class="homeLogoutBtn"
            @click="naverLogout"
            v-if="loginMenu"
            v-show="OAuth === 'naver'"
          >
            Logout
          </div>
        </div>
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
    <!----- 네비게이션 바 시작---------------------------------------------------->

    <!----- 네비게이션 바 종료 ---------------------------------------------------->

    <!--공지 모달-->
    <div
      v-if="NoticeModalView == true"
      class="NoticeModalView"
      :class="{ active: NoticeModalView }"
    >
      <NoticeModal
        @noticeView="noticeView"
        @move="move"
        @close="NoticeModalPopUp"
      ></NoticeModal>
    </div>

    <!----- 배너&예약박스 시작 ---------------------------------------------------->

    <!--항공 예약 박스-->
    <div class="resForm" data-aos="fade-down" data-aos-duration="2000">
      <div class="res-area-select">
        <button
          type="button"
          @click="toggleRoundTrip"
          class="btn-field"
          id="resRoundTrip"
        >
          왕복
        </button>
        <button
          type="button"
          @click="toggleOneWay"
          class="btn-field"
          id="resOneWay"
        >
          편도
        </button>
        <br />
        <br />
        <div class="FromTo">
          <!--FromArea-->
          <span
            v-if="fromBtn1"
            type="button"
            id="fromValue"
            class="fromBtn"
            @click="fromAreaPopUp"
            width="200"
            data-aos="fade"
            data-aos-duration="2000"
            >{{ fromImgName.substring(0, 3) }}<br />{{
              fromImgName.substring(3, 10)
            }}</span
          >
          <span
            v-if="toBtn2"
            type="button"
            id="toValue"
            class="toBtn"
            @click="toAreaPopUp"
            width="200"
            data-aos="fade"
            data-aos-duration="2000"
            >{{ toImgName.substring(0, 3) }}<br />{{
              toImgName.substring(3, 10)
            }}</span
          >
          <div
            v-if="fromAreaView == true"
            class="fromAreaView"
            :class="{ active: fromAreaView }"
          >
            <FromArea
              @close="fromAreaPopUp"
              @update-fromArea="updateFromArea"
              data-aos="fade-down"
              data-aos-duration="2000"
            ></FromArea>
          </div>

          <!--Area Change-->
          <img
            type="button"
            class="ppg-refresh"
            src="../assets/change.png"
            @click="change"
            loading="lazy"
          />

          <!--ToArea-->
          <span
            v-if="fromBtn2"
            type="button"
            class="fromBtn"
            @click="fromAreaPopUp"
            width="200"
            data-aos="fade"
            data-aos-duration="2000"
            >{{ fromImgName.substring(0, 3) }}<br />{{
              fromImgName.substring(3, 10)
            }}</span
          >
          <span
            v-if="toBtn1"
            type="button"
            class="toBtn"
            @click="toAreaPopUp"
            width="200"
            data-aos="fade"
            data-aos-duration="2000"
            >{{ toImgName.substring(0, 3) }}<br />{{
              toImgName.substring(3, 10)
            }}</span
          >
          <div
            v-if="toAreaView == true"
            class="toAreaView"
            :class="{ active: toAreaView }"
          >
            <ToArea
              @close="toAreaPopUp"
              @update-toArea="updateToArea"
              data-aos="fade-down"
              data-aos-duration="2000"
            ></ToArea>
          </div>
        </div>

        <div class="hr"></div>

        <!--여행 날짜 선택-->

        <!--왕복 날짜 선택-->
        <Datepicker
          v-if="datePickerShow1"
          class="datePicker"
          @update:model-value="datepickerShow1"
          v-model="bothWay"
          placeholder=" 가는날 ~ 오는날"
          format="yyyy-MM-dd"
          :min-date="new Date()"
          :partial-range="false"
          data-aos="flip-down"
          data-aos-delay="100"
          modelAuto
          range
        >
          <template #month="{ value }">
            {{ value + 1 + "월" }}
          </template>
          <template #month-overlay="{ value }">
            {{ value + 1 + "월" }}
          </template>
        </Datepicker>
        <div type="button" class="selectDate1" v-if="selectDate1">
          <div class="selectDate2">
            {{ Format1(bothWay) }}
          </div>
        </div>

        <!--편도 날짜 선택-->
        <Datepicker
          v-if="datePickerShow2"
          class="datePicker"
          @update:model-value="datepickerShow2"
          v-model="oneWay"
          placeholder=" 가는날"
          format="yyyy-MM-dd"
          :min-date="new Date()"
          data-aos-delay="100"
          data-aos="flip-down"
        >
          <template #month="{ value }">
            {{ value + 1 + "월" }}
          </template>
          <template #month-overlay="{ value }">
            {{ value + 1 + "월" }}
          </template>
        </Datepicker>
        <div type="button" class="selectDate1" v-if="selectDate2">
          <div class="selectDate2">
            {{ Format2(oneWay) }}
          </div>
        </div>
      </div>

      <!--승객수 팝업-->
      <button
        type="button"
        class="btn-field"
        id="resPassenger"
        @click="countModalPopUp"
      >
        <div class="count1">승객 수</div>
        <div class="countImg">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="currentColor"
            viewBox="0 0 16 16"
          >
            <path
              d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"
            />
          </svg>
        </div>
        <div class="count2">
          <span class="adultCount">성인{{ AdultCount }}명</span>
          <span v-if="ChildCount > 0" class="childCount"
            >, 소아{{ ChildCount }}명</span
          >
          <span v-if="InfantCount > 0" class="infantCount"
            >, 유아{{ InfantCount }}명</span
          >
        </div>
      </button>

      <div v-if="countModal" class="countModal" :class="{ active: countModal }">
        <HeadCount
          @close="countModalPopUp()"
          :AdultCount="AdultCount"
          :ChildCount="ChildCount"
          :InfantCount="InfantCount"
          @update-count="updateCount"
        ></HeadCount>
      </div>
      <div></div>
      <!--좌석 선택-->

      <select id="inputState" class="form-select">
        <option selected>좌석 등급</option>
        <option>일반석</option>
        <option>프레스티지석</option>
        <option>일등석</option>
      </select>

      <input
        type="submit"
        @click="submit()"
        value="항공편 검색"
        class="submit-btn"
      />
    </div>
    <!--항공 예약박스 종료-->

    <!--배너 시작-->
    <div class="bannerBox" data-aos="fade-down" data-aos-duration="2000">
      <!--배너 이미지1-->
      <!--<img class="banner" src="../assets/bannerImage/sky.jpg">-->
      <div class="mainBanner">
        <div class="container1">
          <h2>Fastrip</h2>
          <h3>Airline Ticketing</h3>
        </div>
      </div>
    </div>

    <div class="slidesa flex"></div>

    <!--배너 종료-->
    <!----- 배너&예약박스 종료 ---------------------------------------------------->

    <!-- 조회버튼 -->
    <div class="refer" data-aos="fade-down">
      <!--예약 조회 버튼-->
      <button type="button" class="btn btn-lg btn-default">
        <a href="/Reservation">✈ &nbsp; 예약 조회</a>
      </button>
      <!-- 버튼-->
      <span class="vertical">|</span>
      <button
        type="button"
        class="btn btn-lg btn-default"
        id="checkIn"
        @click="ing"
      >
        <a href="">✔ &nbsp; 체크인</a>
      </button>
      <span class="vertical">|</span>
      <!--항공편 조회 버튼-->
      <button type="button" class="btn btn-lg btn-default" @click="ing">
        &nbsp;
        <a href="">🕒 &nbsp; 항공편 현황</a>
      </button>
    </div>

    <!--</div>

<div class="part2" id="part2">-->

    <!-- 추천 여행지 -->
    <Product></Product>
    <!----------------->

    <hr />
  </div>

  <div class="recommendProduct" id="part3">
    <div class="">
      <br />
      <div
        class="travelTip"
        data-aos="fade-down"
        data-aos-delay="100"
        data-aos-easing="linear"
        data-aos-duration="2000"
      >
        <h1>복잡한 여행을 Easy하게!</h1>
        <br />
        <h3>전자서식을 작성하시면 여행이 한결 여유로워집니다.</h3>
        <br />
        <a @click="ing">자세히 보기</a>
      </div>
      <lottie-player
        class="travelImg"
        src="https://assets4.lottiefiles.com/packages/lf20_pNWSdrwrSb.json"
        data-aos="fade-up"
        data-aos-delay="50"
        data-aos-easing="linear"
        data-aos-duration="1000"
        background="transparent"
        speed="1"
        loop
        autoplay
      ></lottie-player>
    </div>
  </div>

  <!--여행 도우미-->
  <div class="part4" id="part4">
    <lottie-player
      class="part4Lottie"
      src="https://assets1.lottiefiles.com/packages/lf20_jlmgqgx2.json"
      background="transparent"
      speed="1"
      loop
      autoplay
    ></lottie-player>
    <div
      class="side"
      data-aos="fade-right"
      data-aos-delay="150"
      data-aos-easing="ease-out"
      data-aos-duration="2000"
    >
      <h2>여행의 완성을 위한 경험</h2>
    </div>
    <div
      class="sideTip"
      data-aos="fade-right"
      data-aos-delay="150"
      data-aos-easing="ease-out"
      data-aos-duration="2000"
    >
      <div class="sideTip1">
        <a @click="ing">🔍&nbsp; 예약 조회</a>
        <a @click="ing">🏦&nbsp; 호텔</a>
        <a @click="ing">🚗&nbsp; 렌터카</a>
      </div>
      <div class="sideTip2">
        <a @click="ing">📖&nbsp; 기내 면세점</a>
        <a @click="ing">📝&nbsp; 여행자 보험</a>
        <a @click="ing">📋&nbsp; 여행 상품</a>
      </div>
    </div>
  </div>

  <!-- FOOTER -->
  <footer class="Footer">
    <div class="FootLeft">
      <!-- <img class="footLogo" src="../assets/Logo2.png" loading="lazy"> -->
      <div class="footLogoBox">
        <lottie-player
          class="footLogo1"
          src="https://assets9.lottiefiles.com/packages/lf20_ghibwmba.json"
          background="transparent"
          speed="1"
          loading="lazy"
          loop
          autoplay
        ></lottie-player>
        <div class="footLogo2">Fastrip</div>
      </div>
      <p>&copy; 2022 Company, Fastrip</p>
    </div>

    <div class="FootRight">
      <a class="focus" href="#top">✈ Top</a>
    </div>
  </footer>
</template>

<script>
import Datepicker from "@vuepic/vue-datepicker";
import ProfileItem from "@/components/ProfileItem.vue";
import LoginModal from "@/components/LoginModal.vue";
import HeadCount from "./HeadCount.vue";
import FromArea from "./FromArea.vue";
import ToArea from "./ToArea.vue";
import axios from "axios";
import Product from "./Product.vue";
import NoticeModal from "@/components/NoticeModal.vue";
import VueCookies from "vue-cookies";
import AOS from "aos";
import "aos/dist/aos.css";
import data from "../components/Banner.json";

const imageUrls = data;

export default {
  name: "HelloWorld",
  components: {
    Datepicker,
    HeadCount,
    FromArea,
    ToArea,
    Product,
    NoticeModal,
    ProfileItem,
    LoginModal,
  },
  props: [""],
  data() {
    return {
      loginModal: false,
      loginMenu: false,
      OAuth: this.$store.state.userInfo.OAuth,
      toBtn: "",
      fromBtn: "",
      bothWay: [],
      oneWay: [],
      countModal: false,
      datePickerShow1: true,
      datePickerShow2: false,
      selectDate1: false,
      selectDate2: false,
      product: [],
      count: 1,
      fromAreaView: false,
      toAreaView: false,
      AdultCount: 1,
      ChildCount: 0,
      InfantCount: 0,
      fromImgName: "SEL 서울",
      toImgName: "TO 도착지",
      image: "",
      fromBtn1: true,
      toBtn1: true,
      fromBtn2: false,
      toBtn2: false,
      NoticeModalView: true,
      week: ["일", "월", "화", "수", "목", "금", "토"],
      startDate: [
        this.startYear,
        this.startMonth,
        this.startDay,
        this.startWeek,
      ],
      startYear: "",
      returnDate: [
        this.returnYear,
        this.returnMonth,
        this.returnDay,
        this.returnWeek,
      ],
      currentcIdx: 0,
      imageUrls: imageUrls,
    };
  },
  created() {
    AOS.init();
  },
  computed: {
    getProfile() {
      if (this.$store.state.userInfo == null)
        return require("@/assets/weblogin1.png");
      return this.$store.state.userInfo.profile;
    },
    isLogin() {
      return this.$store.state.isLogin;
    },
  },
  methods: {
    ing() {
      alert("준비중입니다.");
    },
    showLoginMenu() {
      this.loginMenu = this.loginMenu ? false : true;
    },
    bannerPrev() {
      let slideCount = imageUrls.length;

      if (this.currentIdx > 0) {
        this.bannerSlide(this.currentIdx - 1);
      } else {
        this.bannerSlide(slideCount - 5);
      }
    },
    bannerNext() {
      let slideCount = imageUrls.length;

      if (this.currentIdx < slideCount - 5) {
        this.bannerSlide(this.currentIdx + 1);
      } else {
        this.bannerSlide(0);
      }
    },
    bannerSlide(num) {
      let slides = document.querySelector(".slidesa"),
        slide = document.querySelectorAll(".bannerBox"),
        slideCount = slide.length,
        slideWidth = 1000,
        slideMargin = 20;

      slides.style.width =
        (slideWidth + slideMargin) * slideCount - slideMargin + "px";

      slides.style.left = -num * 1282 + "px";

      this.currentIdx = num;
    },
    kakaoLogout() {
      let access_token = this.$store.state.userInfo.access_token;

      axios
        .get("http://58.225.45.251:8200/api/kakao/logout/main/" + access_token)
        .then((response) => {
          alert(response.data);
          this.$store.dispatch("logout");
          this.$router.push("/");
          location.reload();
        });
      this.$store.dispatch("logout");
      this.$router.push("/");
      location.reload();
    },
    naverLogout() {
      let access_token = this.$store.state.userInfo.access_token;

      axios
        .get("http://58.225.45.251:8200/api/naver/logout/main/" + access_token)
        .then((response) => {
          alert(response.data);
          this.$store.dispatch("logout");
          this.$router.push("/");
          location.reload();
        });
      this.$store.dispatch("logout");
      this.$router.push("/");
      location.reload();
    },
    hideParams() {
      history.pushState(null, "", `/`);
    },
    setParamInfo() {
      if (this.$route.query.email != null) {
        this.$store.dispatch("setUserInfo", this.$route.query);
        location.reload();
      }
    },
    NoticeModalPopUp() {
      this.NoticeModalView = false;
    },
    datepickerShow1() {
      this.datePickerShow1 = false;
      this.selectDate1 = true;
    },
    datepickerShow2() {
      this.datePickerShow2 = false;
      this.selectDate2 = true;
    },
    toggleRoundTrip() {
      const target1 = document.getElementById("resRoundTrip");
      target1.style.color = "rgb(13, 165, 165)";
      target1.style.background = "rgba(250, 250, 250, 0)";
      target1.style.borderBottom = "3px solid teal";

      const target2 = document.getElementById("resOneWay");
      target2.style.color = "#999";
      target2.style.background = "rgba(250, 250, 250, 0)";
      target2.style.borderBottom = "none";

      this.datePickerShow1 = true;
      this.datePickerShow2 = false;
      this.selectDate1 = false;
      this.selectDate2 = false;
      this.bothWay = "";
      this.oneWay = "";
    },
    toggleOneWay() {
      const target1 = document.getElementById("resOneWay");
      target1.style.color = "rgb(13, 165, 165)";
      target1.style.background = "rgba(250, 250, 250, 0)";
      target1.style.borderBottom = "3px solid teal";

      const target2 = document.getElementById("resRoundTrip");
      target2.style.color = "#999";
      target2.style.background = "rgba(250, 250, 250, 0)";
      target2.style.borderBottom = "none";

      this.datePickerShow1 = false;
      this.datePickerShow2 = true;
      this.selectDate1 = false;
      this.selectDate2 = false;
      this.bothWay = "";
      this.oneWay = "";
    },
    Format(value) {
      this.show1 = false;
      var regexp = /(\d{3}(\d{3})(\d))$/g;
      return value.toString().substr(0, 16).replace(regexp, "");
    },
    change() {
      this.fromBtn1 = this.fromBtn1 ? false : true;
      this.toBtn1 = this.toBtn1 ? false : true;
      this.fromBtn2 = this.fromBtn2 ? false : true;
      this.toBtn2 = this.toBtn2 ? false : true;
    },
    countModalPopUp() {
      this.countModal = this.countModal ? false : true;
    },
    fromAreaPopUp() {
      this.fromAreaView = this.fromAreaView ? false : true;
    },
    toAreaPopUp() {
      this.toAreaView = this.toAreaView ? false : true;
    },
    updateCount(AdultCount, ChildCount, InfantCount) {
      this.AdultCount = AdultCount;
      this.ChildCount = ChildCount;
      this.InfantCount = InfantCount;
      this.countModal = this.countModal ? false : true;
      console.log("Home : " + this.AdultCount);
      console.log("Home : " + this.ChildCount);
      console.log("Home : " + this.InfantCount);
    },
    updateFromArea(image) {
      this.fromImgName = image;
      this.fromAreaView = this.fromAreaView ? false : true;
    },

    updateToArea(image) {
      this.toImgName = image;
      this.toAreaView = this.toAreaView ? false : true;
    },
    Format1(value) {
      let startDate1 = new Date(value[0]);
      let startDate2 = new Date(value[1]);

      let year1 = startDate1.getFullYear();
      let month1 = String(startDate1.getMonth() + 1).padStart(2, "0");
      let day1 = String(startDate1.getDate()).padStart(2, "0");
      let date1 = this.week[startDate1.getDay()];

      let year2 = startDate2.getFullYear();
      let month2 = String(startDate2.getMonth() + 1).padStart(2, "0");
      let day2 = String(startDate2.getDate()).padStart(2, "0");
      let date2 = this.week[startDate2.getDay()];

      return (
        year1 +
        "-" +
        month1 +
        "-" +
        day1 +
        "(" +
        date1 +
        ") ~ " +
        year2 +
        "-" +
        month2 +
        "-" +
        day2 +
        "(" +
        date2 +
        ")"
      );
    },
    Format2(value) {
      let startDate = new Date(value);

      let year = startDate.getFullYear();
      let month = String(startDate.getMonth() + 1).padStart(2, "0");
      let day = String(startDate.getDate()).padStart(2, "0");
      let date = this.week[startDate.getDay()];

      return year + "-" + month + "-" + day + "(" + date + ") ";
    },
    noticeView(value) {
      //cookie 설정
      if (value == true) {
        VueCookies.set("view", value, "1d");
      }
    },
    getNoticeView() {
      if (VueCookies.get("view") == "true") {
        this.NoticeModalView = false;
      } else if (VueCookies.get("view") == "false") {
        this.NoticeModalView = true;
      }
    },
    submit() {
      let chooseInfo = {};
      let seat =
        document.getElementById("inputState").options[
          document.getElementById("inputState").selectedIndex
        ].value;
      let fromArea = "";
      let toArea = "";

      if (this.selectDate1 == true) {
        //왕복 출발일
        this.startYear = this.bothWay[0].getFullYear();
        this.startMonth = this.bothWay[0].getMonth() + 1;
        this.startDay = this.bothWay[0].getDate();
        this.startWeek = this.week[this.bothWay[0].getDay()];

        //도착일
        this.returnYear = this.bothWay[1].getFullYear();
        this.returnMonth = this.bothWay[1].getMonth() + 1;
        this.returnDay = this.bothWay[1].getDate();
        this.returnWeek = this.week[this.bothWay[1].getDay()];
      } else if (this.selectDate2 == true) {
        //편도 출발일
        this.startYear = this.oneWay.getFullYear();
        this.startMonth = this.oneWay.getMonth() + 1;
        this.startDay = this.oneWay.getDate();
        this.startWeek = this.week[this.oneWay.getDay()];

        this.returnYear = "";
        this.returnMonth = "";
        this.returnDay = "";
        this.returnWeek = "";
      }

      if (this.selectDate1 == false && this.selectDate2 == false) {
        alert("날짜를 선택해주세요.");
        return false;
      }

      if (this.fromImgName == "SEL") {
        alert("출발지를 선택해주세요.");
        return false;
      }

      if (this.toImgName == "TO 도착지") {
        alert("도착지를 선택해주세요.");
        return false;
      }

      if (seat == "좌석 등급") {
        alert("좌석을 선택해주세요.");
        return false;
      }

      if (this.fromBtn1 == true) {
        fromArea = this.fromImgName;
        toArea = this.toImgName;
      } else {
        fromArea = this.toImgName;
        toArea = this.fromImgName;
      }

      chooseInfo = {
        fromArea: fromArea,
        toArea: toArea,
        seat: seat,
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
      };

      this.$store.dispatch("chooseInfo", chooseInfo);

      this.$router.push("Departure");
    },
  },

  mounted() {
    this.getNoticeView();
    this.hideParams();
    this.setParamInfo();
    this.bannerNext();
  },
};
</script>

<style>
li {
  list-style: none;
  padding-bottom: 1%;
}

.bannerBox {
  margin-right: 1%;
  border-radius: 20px;
}

/*.mainBannerLottie {
  position: absolute;
  margin-top: -10%;
  z-index: 1;
}*/

.mainBanner {
  border-radius: 15px;
  border: 1.5px solid white;
  background: rgba(255, 255, 255, 0.368);
  height: 460px;
  margin-left: 1%;
  margin-right: 1%;
}

.banner {
  margin-left: 1%;
  height: 480px;
  border: 1.5px solid white;
  border-radius: 8px;
  border-top-left-radius: 20px;
  overflow: hidden;
  opacity: 0.95;
}

.homeLoginMenu > img {
  border-radius: 10px;
  width: 100px;
  height: 100px;
  z-index: 20;

  margin-top: 35px;
  margin-left: -115px;
}

.loginModal {
  width: 300px;
  height: 380px;
}

.homeNavigation {
  height: 75px;
  background-color: rgba(22, 122, 122, 0);
  display: flex;
}

.homeNavBar {
  display: flex;
}

.homeSubRoute > a:visited {
  color: white;
}

.homeSubRoute > a:hover {
  color: white;
}

.homeSubRoute > a {
  font-size: 16px;
  font-family: "SEBANG_Gothic_Bold";
  color: white;
  font-weight: 100;
}

.homeLogo {
  float: left;
  width: 70px;
  height: 70px;
  padding-left: 1%;
}

.homeLogoLink {
  float: left;
  font-size: 30px;
  color: white;
  text-align: center;
  line-height: 70px;
  margin-left: 5%;
}

.homeSubLink {
  width: 30%;
  display: flex;
  margin-left: 38%;
  line-height: 60px;
}

.homeSubLink > a {
  font-size: 16px;
  padding: 10px;
}

.homeSubRoute {
  padding: 2%;
}

.homenav {
  font-weight: 900;
}

.homeLoginBtn {
  margin-left: 6%;
  display: flex;
  float: right;
  width: 10%;
}

.homeLoginBtn1 {
  pointer-events: none;
  width: 60px;
  height: 60px;
  border-radius: 6px;
  border: none;
  cursor: pointer;
  margin-top: 2%;
  margin-left: 4.5%;
}

.homeLoginBtn2 {
  margin-left: 6%;
  display: flex;
  float: right;
  width: 10%;
}

.homeLoginText {
  font-family: "SEBANG_Gothic_Bold";
  font-size: 15px;
  font-weight: 900;
  cursor: pointer;
  color: white;
  width: 33%;
  text-align: center;
  line-height: 75px;
  margin-left: 2%;
}

.homeLogoutBtn {
  font-family: "SEBANG_Gothic_Bold";
  font-size: 15px;
  font-weight: 900;
  cursor: pointer;
  color: white;
  margin-top: -60px;
  margin-left: -90px;
  z-index: 10;
}

.homeLoginName {
  font-family: "SEBANG_Gothic_Bold";
  font-size: 15px;
  font-weight: 900;
  cursor: pointer;
  color: white;
  width: 30%;
  text-align: center;
  line-height: 75px;
  margin-left: 10%;
}

.part1 {
  background: url("../assets/bannerImage/sky2.jpg") fixed;
  background-size: cover;
}

.part2 {
  background: url("../assets/bannerImage/sky2.jpg") fixed;
  margin-bottom: 10%;
}

.part4 {
  /* background: url("../assets/part4.jpg"); */
  /* padding-top: 10%;
        padding-bottom: 20%; */
  display: flex;
  background-color: rgba(44, 129, 85, 0.784);
  border-top: 1px solid teal;
}

.part4Lottie {
  width: 800px;
  height: 800px;
  margin-left: 35%;
}

h1,
h2 {
  font-size: 60px;
  margin-top: 40px;
}

a {
  text-decoration: none;
  font-size: 24px;
  margin-left: 20px;
  cursor: pointer;
}

/* 예약박스 & 배너 시작 */
/* 예약박스 시작 */

.resForm {
  width: 400px;
  height: 450px;
  box-shadow: 7px 7px 15px 0px rgb(107, 138, 138),
    8px 8px 16px -10px rgb(82, 96, 96);
  border: 2px solid rgb(193, 188, 188);
  border-radius: 20px;
  background-color: rgba(252, 252, 252, 0.952);
  margin: 0 auto;
  margin-top: 0.5%;
  margin-left: 1.5%;
  position: absolute;
  padding: 10px;
  text-align: center;
  z-index: 10;
}

.res-area-select {
  height: 45%;
  background: linear-gradient(
    135deg,
    rgba(255, 255, 255, 0.612) 0%,
    rgb(237, 237, 237) 100%
  );
  border-radius: 30px;
  border: 1px solid rgb(193, 188, 188);
  padding: 10px;
}

#resRoundTrip,
#resOneWay {
  display: inline;
  margin-left: 5%;
  margin-right: 8%;
  font-size: 14px;
  border: none;
  color: #999;
  background: rgba(250, 250, 250, 0);
}

#resRoundTrip {
  color: rgb(13, 165, 165);
  background: rgba(250, 250, 250, 0);
  border-bottom: 3px solid teal;
}

#resRoundTrip:hover,
#resOneWay:hover {
  border-bottom: 3px solid teal;
}

.ppg-refresh {
  width: 10%;
  height: 10%;
  margin: 0rem 1rem 2rem 1rem;
  cursor: pointer;
  border: 4px solid rgb(225, 225, 225);
  border-radius: 10px;
}

.fromBtn,
.toBtn {
  font-size: 20px;
  width: 37%;
  height: 80px;
  border: 4px solid rgb(225, 225, 225);
  border-radius: 20px;
  background-color: white;
  padding: 6px 0;
}

.fromAreaView,
.toAreaView {
  padding: 10px;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 60%;
  height: 60%;
  border-radius: 15px;
  background-color: rgb(250, 250, 250);
  box-shadow: 2px 2px 10px lightgrey;
  z-index: 5;
  overflow: auto;
}

.toAreaView::-webkit-scrollbar {
  width: 10px;
  /*스크롤바의 너비*/
}

.toAreaView::-webkit-scrollbar-thumb {
  background-color: teal;
  border-radius: 10px;
  /*스크롤바의 색상*/
}

.toAreaView::-webkit-scrollbar-track {
  background-color: white;
  /*스크롤바 트랙 색상*/
}

.fromBtn:hover,
.toBtn:hover,
.ppg-refresh:hover,
.dp__menu:hover,
.dp__pointer:hover,
.selectDate1:hover,
.selectDate2:hover,
.form-select:hover,
.submit-btn:hover,
#resPassenger:hover {
  border: 2.5px solid teal !important;
}

.dp__action_row {
  width: 100% !important;
}

.dp__menu {
  width: 30%;
  height: 67%;
  margin-top: 6%;
}

.dp__pointer {
  width: 100%;
  height: 29px;
}

.dp__input {
  font-size: 10px !important;
  font-family: "SEBANG_Gothic_Bold" !important;
}

.hr {
  margin-top: 13%;
  margin-bottom: 4%;
  border: 1px solid rgb(193, 188, 188);
  padding: 0.01px;
}

.datePicker {
  width: 80%;
  height: 40px;
  border-radius: 10px;
  border: 1px solid rgb(193, 188, 188);
  box-shadow: -4px -4px 10px -8px rgba(255, 255, 255, 1),
    4px 4px 10px -8px rgba(0, 0, 0, 0.3);
  margin-left: auto;
  margin-right: auto;
  padding-left: 4px;
  padding-right: 4px;
  z-index: 2;
}

.selectDate1 {
  font-family: "SEBANG_Gothic_Bold";
  width: 80%;
  border: 1px solid rgb(193, 188, 188);
  border-radius: 10px;
  background: linear-gradient(
    135deg,
    rgba(255, 255, 255, 0.963) 0%,
    rgba(237, 237, 237, 0.959) 100%
  );
  box-shadow: -4px -4px 10px -8px rgba(255, 255, 255, 1),
    4px 4px 10px -8px rgba(0, 0, 0, 0.3);
  margin-left: auto;
  margin-right: auto;
  padding: 4px;
  font-size: 10px;
  z-index: 1;
}

.selectDate2 {
  height: 30px;
  color: #999;
  border: 1px solid rgb(193, 188, 188);
  border-radius: 4px;
  background-color: white;
  text-align: center;
  padding: 9px;
}

#resPassenger {
  width: 90%;
  height: 10%;
  color: #626262;
  background: linear-gradient(
    135deg,
    rgb(255, 255, 255) 0%,
    rgb(237, 237, 237) 100%
  );
  border: 1px solid rgb(193, 188, 188);
  border-radius: 10px;
  font-size: 10px;
  color: #999;
  background: linear-gradient(
    135deg,
    rgba(255, 255, 255, 0.963) 0%,
    rgba(237, 237, 237, 0.959) 100%
  );
  margin-top: 85px;
  cursor: pointer;
  display: inline-block;
  white-space: nowrap;
}

#inputState {
  width: 90%;
  height: 10%;
  color: #626262;
  background: linear-gradient(
    135deg,
    rgb(255, 255, 255) 0%,
    rgb(237, 237, 237) 100%
  );
  border: 1px solid rgb(193, 188, 188);
  border-radius: 10px;
  color: #999;
  background: linear-gradient(
    135deg,
    rgba(255, 255, 255, 0.963) 0%,
    rgba(237, 237, 237, 0.959) 100%
  );
  margin-top: 15px;
  margin-bottom: 15px;
  cursor: pointer;
  display: inline-block;
  white-space: nowrap;
  padding-left: 26px;
  font-size: 14px;
}

.adultCount {
  display: inline-block;
  white-space: nowrap;
}

.childCount {
  white-space: nowrap;
  display: inline-block;
}

.infantCount {
  white-space: nowrap;
  display: inline-block;
}

.countImg {
  float: right;
  margin-right: 3%;
  color: #626262;
}

.count1 {
  float: left;
  margin-left: 7%;
  margin-top: 0.5%;
  font-size: 14px;
  color: #626262;
}

.count2 {
  float: right;
  margin-right: 3%;
  margin-top: 0.5%;
  font-size: 14px;
}

.countModal {
  position: fixed;
  z-index: 1;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 60%;
  border-radius: 15px;
  background-color: rgb(244, 244, 244);
  box-shadow: 2px 2px 10px lightgrey;
}

.form-select {
  border: 1px solid rgb(193, 188, 188);
  border-radius: 20px;
  background: linear-gradient(
    135deg,
    rgba(255, 255, 255, 0.963) 0%,
    rgba(237, 237, 237, 0.959) 100%
  );
  display: table;
  margin-left: auto;
  margin-right: auto;
  margin-top: 30px;
  margin-bottom: 10px;
  cursor: pointer;
}

.submit-btn {
  width: 50%;
  height: 8%;
  border: 1px solid rgb(193, 188, 188);
  border-radius: 10px;
  font-size: 14px;
  color: white;
  background: rgb(13, 165, 165);
  /* background: rgb(1,114, 185); */
  display: table;
  margin-left: auto;
  margin-right: auto;
}

/* 예약박스 종료 */
/* 배너 시작 */

.container1 {
  margin-top: 7%;
}

.container1 h2 {
  color: white;
  font-size: 120px;
  margin-left: 50%;
  z-index: 50;
}

.container1 h3 {
  color: white;
  margin-bottom: 120px;
  font-size: 30px;
  margin-left: 55%;
}

.container2 h2 {
  color: rgba(238, 203, 30, 0.959);
  font-size: 120px;
  margin-left: 50%;
}

.container2 h3 {
  color: rgba(238, 203, 30, 0.959);
  font-size: 30px;
  margin-left: 50%;
}

.container2 h4 {
  color: rgba(238, 203, 30, 0.959);
  font-size: 15px;
  margin-bottom: 70px;
  margin-left: 50%;
  cursor: pointer;
}

.container3 h2 {
  color: rgb(249, 214, 250);
  font-size: 60px;
  margin-left: 75%;
  white-space: nowrap;
}

.container3 h3 {
  color: rgb(249, 214, 250);
  font-size: 26px;
  margin-left: 90%;
  white-space: nowrap;
}

.container3 h4 {
  color: rgb(249, 214, 250);
  font-size: 13px;
  margin-left: 90%;
  margin-bottom: 240px;
}

.container4 h2 {
  color: rgb(234, 246, 255);
  font-size: 90px;
  margin-left: 70%;
}

.container4 h3 {
  color: rgb(234, 246, 255);
  font-size: 14px;
  margin-left: 75%;
}

.container4 h4 {
  color: rgb(234, 246, 255);
  font-size: 14px;
  margin-left: 75%;
  margin-bottom: 80px;
}

/* 배너 종료 */
/* 예약박스 & 배너 종료 */

.vertical {
  color: white;
  font-weight: 900;
}

#checkIn {
  padding-right: 50px;
}

.refer {
  text-align: center;
  margin-top: 2%;
  font-size: 16px;
  margin-bottom: 6%;
}

.refer > button > a {
  font-size: 16px;
  color: white;
}

.refer button {
  font-weight: 900;
}

.recommendProduct {
  margin-top: 10%;
}

.travelImg {
  width: 27%;
  height: 70% !important;
  margin-left: 38%;
  margin-bottom: 10%;
}

.travelTip {
  text-align: center;
}

.travelTip > h1 {
  font-size: 30px;
}

.travelTip > h3 {
  font-size: 20px;
}

.travelTip > a {
  font-size: 20px;
}

.side {
  color: white;
  margin-left: 6%;
  margin-top: 16%;
  position: absolute;
}

.side > h2 {
  font-size: 30px;
}

.sideTip {
  width: 360px;
  height: 115px;
  margin-left: 4%;
  margin-top: 24%;
  border: 4px solid white;
  border-radius: 10px;
  color: white;
  position: absolute;
}

.sideTip1 > a,
.sideTip2 > a {
  font-size: 14px;
}

.sideTip1 {
  padding: 20px 0px;
}

.Footer {
  border-top: 1px solid;
}

.FootLeft {
  float: left;
  margin-left: 120px;
  margin-top: 50px;
  font-size: 20px;
  font-weight: 900;
  padding-bottom: 50px;
}

.FootLeft > p {
  font-size: 16px;
  font-weight: 900;
}

.footLogoBox {
  display: flex;
}

.footLogo1 {
  width: 35%;
  height: 60px;
  margin-bottom: 10px;
}

.footLogo2 {
  padding: 15px 0;
  margin-left: 5px;
  font-size: 24px;
}

.FootRight {
  float: right;
  margin-right: 150px;
  margin-top: 80px;
}

.FootRight > a {
  font-size: 16px;
  color: black;
  text-decoration-line: none;
}

.FootRight > a:visited {
  color: black;
}

.topBtn {
  color: rgb(77, 77, 77);
}

.topBtn:hover {
  color: rgb(77, 77, 77);
}

@media (min-width: 1100px) {
  .NoticeModalView {
    left: 60%;
    width: 400px;
    height: 500px;
    padding: 20px;
    position: absolute;
    top: 50%;
    transform: translate(-50%, -50%);
    border-radius: 15px;
    background-color: white;
    box-shadow: 1px 1px 6px teal;
    overflow: auto;
    z-index: 10;
  }
}

/* 화면 축소 전 후 */

@media (max-width: 1100px) {
  .container1 h2 {
    font-size: 100px;
  }

  .container1 h3 {
    font-size: 20px;
    margin-bottom: 100px;
  }

  .container2 h2 {
    font-size: 90px;
  }

  .container2 h3 {
    font-size: 20px;
  }

  .container2 h4 {
    font-size: 15px;
    margin-bottom: 90px;
  }

  .container3 h2 {
    font-size: 50px;
    margin-left: 38%;
  }

  .container3 h3 {
    font-size: 25px;
    margin-left: 70%;
  }

  .container3 h4 {
    font-size: 15px;
    margin-left: 70%;
    margin-bottom: 250px;
  }

  .container4 h2 {
    font-size: 70px;
    margin-left: 30%;
  }

  .container4 h3 {
    font-size: 15px;
    margin-left: 30%;
  }

  .container4 h4 {
    font-size: 10px;
    margin-left: 30%;
    margin-bottom: 80px;
  }
}
</style>
