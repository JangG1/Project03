<template>
  <div class="">
    <div class="infoTitle">
      <div class="title">여행 예약 정보</div>
      <button class="payCloseBtn" @click="closeModal">X</button>
    </div>
    <br />

    <!-- 승객 정보(성인) -->
    <span v-for="(pas, index) in parseInt(chooseInfo?.AdultCount)" :key="index">
      <div class="infoList">
        <div class="title2">
          성인
          <span v-if="index == 0">1 [예약자]</span>
          <span v-if="index > 0">{{ index + 1 }}</span>
        </div>
        <div class="infoLeft">
          <span class="listLeft">한글 이름</span><br /><br />
          <span class="listLeft">영어 이름</span><br /><br />
          <span class="listLeft">생년 월일</span><br /><br />
          <span class="listLeft">성별</span>
        </div>
        <div class="infoRight">
          <span class="listRight">{{ addAdult?.[index].korName }}</span
          ><br /><br />
          <span class="listRight"
            >{{ addAdult?.[index].engLastName }}
            {{ addAdult?.[index].engFirstName }}</span
          ><br /><br />
          <span class="listRight">{{ addAdult?.[index].birthday }}</span
          ><br /><br />
          <span class="listRight">{{ addAdult?.[index].gender }}</span>
        </div>
      </div>
    </span>

    <!-- 승객 정보(유아) -->
    <span v-for="(pas, index) in parseInt(chooseInfo?.ChildCount)" :key="index">
      <div class="infoList">
        <div class="title2">유아 {{ index + 1 }}</div>
        <div class="infoLeft">
          <span class="listLeft">한글 이름</span><br /><br />
          <span class="listLeft">영어 이름</span><br /><br />
          <span class="listLeft">생년 월일</span><br /><br />
          <span class="listLeft">성별</span>
        </div>
        <div class="infoRight">
          <span class="listRight">{{ addChild?.[index].korName }}</span
          ><br /><br />
          <span class="listRight"
            >{{ addChild?.[index].engLastName }}
            {{ addChild?.[index].engFirstName }}</span
          ><br /><br />
          <span class="listRight">{{ addChild?.[index].birthday }}</span
          ><br /><br />
          <span class="listRight">{{ addChild?.[index].gender }}</span>
        </div>
      </div>
    </span>

    <!-- 승객 정보(소아) -->
    <span v-for="(pas, index) in parseInt(chooseInfo.InfantCount)" :key="index">
      <div class="infoList">
        <div class="title2">소아 {{ index + 1 }}</div>
        <div class="infoLeft">
          <span class="listLeft">한글 이름</span><br /><br />
          <span class="listLeft">영어 이름</span><br /><br />
          <span class="listLeft">생년 월일</span><br /><br />
          <span class="listLeft">성별</span>
        </div>
        <div class="infoRight">
          <span class="listRight">{{ addInfant?.[index].korName }}</span
          ><br /><br />
          <span class="listRight"
            >{{ addInfant?.[index].engLastName }}
            {{ addInfant?.[index].engFirstName }}</span
          ><br /><br />
          <span class="listRight">{{ addInfant?.[index].birthday }}</span
          ><br /><br />
          <span class="listRight">{{ addInfant?.[index].gender }}</span>
        </div>
      </div>
    </span>

    <div class="areaList">
      <div class="title2">가는편</div>
      <div class="infoLeft">
        <span class="listLeft">항공편</span><br /><br />
        <span class="listLeft">여행 경로</span><br /><br />
        <span class="listLeft">여행 일정</span><br /><br />
        <span class="listLeft">승객 수</span><br /><br />
        <span class="listLeft">좌석</span>
      </div>
      <div class="infoRight">
        <span class="listRight">{{ startInfo?.flight1 }}</span
        ><br /><br />
        <span class="listRight"
          >{{ chooseInfo?.fromArea }} &nbsp;→&nbsp;
          {{ chooseInfo?.toArea }}</span
        ><br /><br />
        <span class="listRight"
          >{{ chooseInfo?.startYear }}-{{ chooseInfo?.startMonth }}-{{
            chooseInfo?.startDay
          }}({{ chooseInfo.startWeek }}) {{ startInfo?.startTime1 }} ~
          {{ startInfo?.arriveTime1 }}</span
        ><br /><br />
        <span class="listRight" v-if="chooseInfo?.InfantCount > 0"
          >, 소아 {{ chooseInfo?.InfantCount }}명</span
        >
        <span class="listRight" v-if="chooseInfo?.ChildCount > 0"
          >, 유아 {{ chooseInfo?.ChildCount }}명</span
        >
        <span class="listRight">성인 {{ chooseInfo?.AdultCount }}명</span>
        <br /><br />
        <span class="listRight"
          >{{ chooseInfo?.seat }} {{ startInfo?.seatClass1 }}</span
        >
      </div>
    </div>

    <div class="areaList" v-if="chooseInfo?.returnYear">
      <div class="title2">오는편</div>
      <div class="infoLeft">
        <span class="listLeft">항공편</span><br /><br />
        <span class="listLeft">여행 경로</span><br /><br />
        <span class="listLeft">여행 일정</span><br /><br />
        <span class="listLeft">승객 수</span><br /><br />
        <span class="listLeft">좌석</span>
      </div>
      <div class="infoRight">
        <span class="listRight">{{ returnInfo?.flight2 }}</span
        ><br /><br />
        <span class="listRight"
          >{{ chooseInfo?.toArea }} &nbsp;→&nbsp;
          {{ chooseInfo?.fromArea }}</span
        ><br /><br />
        <span class="listRight"
          >{{ chooseInfo?.returnYear }}-{{ chooseInfo?.returnMonth }}-{{
            chooseInfo?.returnDay
          }}({{ chooseInfo?.returnWeek }}) {{ returnInfo?.startTime2 }} ~
          {{ returnInfo?.arriveTime2 }}</span
        ><br /><br />
        <span class="listRight" v-if="chooseInfo?.InfantCount > 0"
          >, 소아 {{ chooseInfo?.InfantCount }}명</span
        >
        <span class="listRight" v-if="chooseInfo?.ChildCount > 0"
          >, 유아 {{ chooseInfo?.ChildCount }}명</span
        >
        <span class="listRight">성인 {{ chooseInfo?.AdultCount }}명</span>
        <br /><br />
        <span class="listRight"
          >{{ chooseInfo?.seat }} {{ returnInfo?.seatClass2 }}</span
        >
      </div>
    </div>
    <br />

    <div class="payList">
      <div class="title2">결제</div>
      <div class="infoLeft">
        <span class="listLeft">결제 수단</span><br /><br />
        <span class="listLeft">결제 후 포인트</span><br /><br />
      </div>
      <div class="infoRight">
        <span class="listRight">포인트 결제</span><br /><br />
        <span class="listRight">{{ AddComma(holdPoint - totalPoint) }}</span
        ><br /><br />
      </div>
    </div>

    <button type="button" class="reserBtn" @click="submit()">예약 하기</button>
  </div>

  <!-- 로딩화면 -->
  <div class="layerPopup" v-show="isLoading"></div>
</template>

<script>
import axios from "axios";

export default {
  name: "HelloWorld",
  components: {},
  data() {
    return {
      chooseInfo: this.$store.state.chooseInfo,
      startInfo: this.$store.state.startInfo,
      returnInfo: this.$store.state.returnInfo,
      userInfo: this.$store.state.userInfo,
      childCountView: false,
      infantCountView: false,
      holdPoint: this.$store.state.holdPoint,
      fullname: [],
    };
  },
  props: {
    passEmail1: {
      type: String,
      default: "",
    },
    passEmail2: {
      type: String,
      default: "",
    },
    korLastName: {
      type: String,
      default: "",
    },
    korFirstName: {
      type: String,
      default: "",
    },
    engLastName: {
      type: String,
      default: "",
    },
    engFirstName: {
      type: String,
      default: "",
    },
    birthday: {
      type: String,
      default: "",
    },
    gender: {
      type: String,
      default: "",
    },
    addAdult: {
      type: String,
      default: "",
    },
    addChild: {
      type: String,
      default: "",
    },
    addInfant: {
      type: String,
      default: "",
    },
    totalPoint: {
      type: Number,
    },
  },
  computed: {
    isLoading() {
      return this.$store.state.isLoad;
    },
  },
  methods: {
    closeModal() {
      this.$emit("closeModal");
    },
    AddComma(num) {
      let regexp = /\B(?=(\d{3})+(?!\d))/g;

      return num.toString().replace(regexp, ",");
    },
    submit() {
      const startDate =
        this.chooseInfo.startYear +
        "-" +
        this.chooseInfo.startMonth +
        "-" +
        this.chooseInfo.startDay +
        "(" +
        this.chooseInfo.startWeek +
        ")";
      let returnDate =
        this.chooseInfo.returnYear +
        "-" +
        this.chooseInfo.returnMonth +
        "-" +
        this.chooseInfo.returnDay +
        "(" +
        this.chooseInfo.returnWeek +
        ")";

      let addAdult = this.addAdult;
      let addChild = this.addChild;
      let addInfant = this.addInfant;

      let korName = this.korLastName + this.korFirstName;

      let userInfo = [
        {
          korName: korName,
        },
        {
          engLastName: this.engLastName,
        },
        {
          engFirstName: this.engFirstName,
        },
        {
          gender: this.gender,
        },
        {
          birthday: this.birthday,
        },
      ];

      if (this.chooseInfo.returnYear != "") {
        this.way = "왕복";
      } else {
        this.way = "편도";
        returnDate = "";
        this.returnInfo.seatClass2 = "";
        this.returnInfo.flight2 = "";
        this.returnInfo.startTime2 = "";
        this.returnInfo.arriveTime2 = "";
      }

      let email = "";

      //로그인 상태 이메일
      if (this.$store.state.isLogin == true) {
        if (this.$store.state.userInfo.email == "") {
          email = this.passEmail1 + "@" + this.passEmail2;
        } else if (this.$store.state.userInfo.email != "") {
          email = this.userInfo.email;
        }
      }

      //비로그인 상태 이메일
      if (this.$store.state.isLogin == false) {
        email = this.passEmail1 + "@" + this.passEmail2;
      }

      this.$store.dispatch("setLoading", true);
      axios
        .post("http://58.225.45.251:8200/api/res/resPost", {
          email: email,
          seat: this.chooseInfo.seat,
          seatClass1: this.startInfo.seatClass1,
          seatClass2: this.returnInfo.seatClass2,
          way: this.way,
          flight1: this.startInfo.flight1,
          flight2: this.returnInfo.flight2,
          fromArea: this.chooseInfo.fromArea,
          toArea: this.chooseInfo.toArea,
          oneWayArea: "",
          startDate: startDate,
          returnDate: returnDate,
          infantCount: this.chooseInfo.InfantCount,
          childCount: this.chooseInfo.ChildCount,
          adultCount: this.chooseInfo.AdultCount,
          startTime1: this.startInfo.startTime1,
          arriveTime1: this.startInfo.arriveTime1,
          startTime2: this.returnInfo.startTime2,
          arriveTime2: this.returnInfo.arriveTime2,
          addAdult: addAdult,
          addChild: addChild,
          addInfant: addInfant,
        })
        .then((res) => {
          console.log(res);
          console.log("예약자 정보 보내짐");
        })
        .catch((err) => {
          console.log(err);
          console.log("예약자 정보 안보내짐");
        });
      this.$store.dispatch("setLoading", false);

      let usePoint = this.holdPoint - this.totalPoint;
      this.$store.dispatch("holdPoint", usePoint);
      this.$store.dispatch("setUserInfo2", userInfo);
      this.$store.dispatch("addAdult", addAdult);
      this.$store.dispatch("addChild", addChild);
      this.$store.dispatch("addInfant", addInfant);

      this.$store.dispatch("setLoading", false);

      alert("예약이 완료 되었습니다.");
      this.$router.push("Complete");
    },
  },
  mounted() {},
};
</script>

<style>
.infoTitle {
  display: flex;
}

.title {
  margin-top: 2%;
  margin-left: 140px;
  color: teal;
  font-weight: 900;
  font-size: 20px;
}

.payCloseBtn {
  color: teal;
  font-weight: 900;
  font-size: 18px;
  border: 1px solid white;
  background-color: white;
  margin-left: 100px;
  margin-top: 10px;
}

.infoList {
  border: 1px solid teal;
  border-radius: 4px;
  height: 210px;
  margin-top: 30px;
  margin-bottom: 20px;
}

.areaList {
  border: 1px solid teal;
  border-radius: 4px;
  height: 250px;
  margin-top: 30px;
  margin-bottom: 10px;
}

.infoLeft {
  font-size: 12px;
  font-weight: 900;
  color: #999;
  float: left;
  margin-left: 20px;
}

.infoRight {
  font-size: 12px;
  font-weight: 900;
  float: right;
  margin-right: 20px;
}

.listLeft {
  float: left;
}

.listRight {
  float: right;
}

.title2 {
  width: 100.1%;
  color: white;
  background-color: teal;
  font-weight: 900;
  font-size: 14px;
  text-align: center;
  padding: 10px 0;
  margin-bottom: 20px;
}

.payList {
  border: 1px solid teal;
  border-radius: 4px;
  height: 135px;
  margin-bottom: 30px;
}

.reserBtn {
  width: 150px;
  height: 40px;
  font-size: 20px;
  font-weight: 900;
  color: white;
  border: 1px solid white;
  border-radius: 4px;
  background-color: teal;
  margin-left: 125px;
}

.layerPopup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8);
  z-index: 30;
  justify-content: center;
  align-items: center;
  margin: 0 0 0 0;
}

.spinner {
  position: absolute;
  left: 30%;
  width: 550px;
  height: 550px;
}
</style>
