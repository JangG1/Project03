<template>
  <div data-aos="fade-up" data-aos-duration="2000">
    <div>
      <span class="passTitle">예약자 정보 {{ res_no }}</span>
      <button class="passCloseBtn" @click="closeModal">X</button>
    </div>

    <!-- 총 승객수 -->
    <div class="passCount1">
      승객수 :
      <span class="passCount2">
        (총
        {{ adultCount + childCount + infantCount ?? "로딩중" }}명)</span
      >
      <span class="passCount2" v-if="infantCount > 0"
        >, 소아 {{ infantCount ?? "로딩중" }}명</span
      >
      <span class="passCount2" v-if="childCount > 0"
        >, 유아 {{ childCount ?? "로딩중" }}명</span
      >
      <span class="passCount2">성인 {{ adultCount ?? "로딩중" }}명</span>
    </div>
    <br />

    <!-- 승객 정보 (성인) -->
    <span v-for="(pas, index) in adultPas" :key="index">
      <div class="passInfoList">
        <div class="passInfoTitle">
          성인
          <span v-if="index == 0">1 [예약자]</span>
          <span v-if="index > 0">{{ index + 1 }}</span>
        </div>
        <div class="info">
          <span class="listLeft">한글 이름 :</span>
          <span class="listRight"> {{ adultPas?.[index].korName }}</span
          ><br /><br />
          <span class="listLeft">영어 이름 :</span>
          <span class="listRight">
            {{ adultPas?.[index].engLastName }}
            {{ adultPas?.[index].engFirstName }}</span
          ><br /><br />
          <span class="listLeft">생년 월일 :</span>
          <span class="listRight"> {{ adultPas?.[index].birthday }}</span
          ><br /><br />
          <span class="listLeft">성별 :</span>
          <span class="listRight"> {{ adultPas?.[index].gender }}</span
          ><br /><br />
        </div>
      </div>
    </span>

    <!-- 승객 정보 (유아) -->
    <span v-for="(pas, index) in childPas" :key="index">
      <div v-if="pas" class="passInfoList">
        <div class="passInfoTitle" v-if="childCount == 1">
          유아 {{ childCount }}
        </div>
        <div class="passInfoTitle" v-if="childCount > 1">
          유아 {{ childCount + 1 }}
        </div>
        <div class="info">
          <span class="listLeft">한글 이름 :</span>
          <span class="listRight"> {{ childPas?.[index].korName }}</span
          ><br /><br />
          <span class="listLeft">영어 이름 :</span>
          <span class="listRight">
            {{ childPas?.[index].engLastName }}
            {{ childPas?.[index].engFirstName }}</span
          ><br /><br />
          <span class="listLeft">생년 월일 :</span>
          <span class="listRight"> {{ childPas?.[index].birthday }}</span
          ><br /><br />
          <span class="listLeft">성별 :</span>
          <span class="listRight"> {{ childPas?.[index].gender }}</span
          ><br /><br />
        </div>
      </div>
    </span>

    <!-- 승객 정보 (소아) -->
    <span v-for="(pas, index) in infantPas" :key="index">
      <div v-if="pas" class="passInfoList">
        <div class="passInfoTitle" v-if="infantCount == 1">
          소아 {{ infantCount }}
        </div>
        <div class="passInfoTitle" v-if="infantCount > 1">
          소아 {{ infantCount + 1 }}
        </div>
        <div class="info">
          <span class="listLeft">한글 이름 :</span>
          <span class="listRight"> {{ infantPas?.[index].korName }}</span
          ><br /><br />
          <span class="listLeft">영어 이름 :</span>
          <span class="listRight">
            {{ infantPas?.[index].engLastName }}
            {{ infantPas?.[index].engFirstName }}</span
          ><br /><br />
          <span class="listLeft">생년 월일 :</span>
          <span class="listRight"> {{ infantPas?.[index].birthday }}</span
          ><br /><br />
          <span class="listLeft">성별 :</span>
          <span class="listRight"> {{ infantPas?.[index].gender }}</span
          ><br /><br />
        </div>
      </div>
    </span>
  </div>
</template>

<script>
export default {
  data() {
    return {
      res1: [],
      res2: [],
      passengers: this.$store.state.temp,
      adultCount: this.$store.state.adultCount,
      childCount: this.$store.state.childCount,
      infantCount: this.$store.state.infantCount,
      adultPas: this.$store.state.adultPas,
      childPas: this.$store.state.childPas,
      infantPas: this.$store.state.infantPas,
      pasIndex: this.$store.state.res_no,
      res_no: this.$store.state.res_no,
      isLoading: true,
    };
  },
  props: {
    user: {
      type: String,
      default: "",
    },
  },
  methods: {
    closeModal() {
      this.$emit("close");
    },
    Gender(value) {
      let gender = value;
      if (gender == "male") {
        gender = "남자";
      } else if (gender == "female") {
        gender = "여자";
      }
      return gender;
    },
  },
  mounted() {},
};
</script>

<style>
.passTitle {
  margin-top: 2%;
  margin-left: 2%;
  color: teal;
  font-weight: 900;
  font-size: 20px;
}

.passCount1 {
  display: none;
}

.passCount2 {
  float: right;
}

.passCloseBtn {
  color: teal;
  font-weight: 900;
  font-size: 18px;
  float: right;
  border: 1px solid white;
  background-color: white;
}

.info {
  margin-top: 20px;
}

.passInfoList {
  border: 1px solid teal;
  border-radius: 4px;
  height: 260px;
  margin-top: 16px;
  font-size: 16px;
}

.passInfoTitle {
  color: white;
  background-color: teal;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  font-weight: 900;
  font-size: 16px;
  height: 40px;
  padding: 8px 0;
  padding-left: 20px;
}

.listLeft {
  font-weight: 900;
  color: #999;
  float: left;
  padding-left: 16px;
}

.listRight {
  font-weight: 900;
  float: right;
  padding-right: 16px;
}

/* 화면 축소 전 후 */

@media (min-width: 1220px) {
  .passCount1 {
    margin-top: 25px;
    margin-left: 10px;
    font-weight: 900;
    font-size: 14px;
    color: rgba(35, 163, 163, 0.831);
    display: block;
  }
}
</style>
