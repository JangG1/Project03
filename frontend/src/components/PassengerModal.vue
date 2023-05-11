<template>
    <div data-aos="fade-up" data-aos-duration="2000">
<div>
    <span class="passTitle">예약자 정보</span>
    <button class="passCloseBtn" @click="closeModal">X</button>
</div>

<!-- 총 승객수 -->
<div class="passCount1">승객수 :
    <span class="passCount2"> (총 {{ addAdult?.length + addChild?.length + addInfant?.length  ?? '로딩중'}}명)</span>
    <span class="passCount2" v-if="addInfant?.length > 0">, 소아 {{addInfant.length  ?? '로딩중'}}명</span>
    <span class="passCount2" v-if="addChild?.length > 0">, 유아 {{addChild.length  ?? '로딩중'}}명</span>
    <span class="passCount2">성인 {{addAdult.length ?? '로딩중'}}명</span>
</div>
<br>

<!-- 승객 정보 (성인) -->
<span v-for="(pas,index) in addAdult" :key="index">
    <div class="passInfoList">
        <div class="passInfoTitle">
            성인
            <span v-if="index == 0">1 [예약자]</span>
            <span v-if="index > 0">{{ index + 1 }}</span>
        </div>
        <div class="info">
            <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{addAdult?.[index].korName}}</span><br><br>
            <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{addAdult?.[index].engLastName}} {{addAdult?.[index].engFirstName}}</span><br><br>
            <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{addAdult?.[index].birthday}}</span><br><br>
            <span class="listLeft">성별 :</span> <span class="listRight"> {{addAdult?.[index].gender}}</span><br><br>
        </div>
    </div>
</span>

<!-- 승객 정보 (유아) -->
<span v-for="(pas,index) in addChild" :key="index">
    <div class="passInfoList">
        <div class="passInfoTitle">
            유아 {{ index + 1 }}
        </div>
        <div class="info">
            <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{addChild?.[index].korName}}</span><br><br>
            <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{addChild?.[index].engLastName}} {{addChild?.[index].engFirstName}}</span><br><br>
            <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{addChild?.[index].birthday}}</span><br><br>
            <span class="listLeft">성별 :</span> <span class="listRight"> {{addChild?.[index].gender}}</span><br><br>
        </div>
    </div>
</span>

<!-- 승객 정보 (소아) -->
<span v-for="(pas,index) in addInfant" :key="index">
    <div class="passInfoList">
        <div class="passInfoTitle">
            소아 {{ index + 1 }}
        </div>
        <div class="info">
            <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{addInfant?.[index].korName}}</span><br><br>
            <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{addInfant?.[index].engLastName}} {{addInfant?.[index].engFirstName}}</span><br><br>
            <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{addInfant?.[index].birthday}}</span><br><br>
            <span class="listLeft">성별 :</span> <span class="listRight"> {{addInfant?.[index].gender}}</span><br><br>
        </div>
    </div>
</span>
</div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            res1: [],
            res2: [],
            addAdult: [],
            addChild: [],
            addInfant: [],
            pasIndex: this.$store.state.res_no,
            res_no: this.$store.state.res_no,
        }
    },
    props: {
        user: {
            type: String,
            default: ''
        },
    },
    methods: {
        closeModal() {
            this.$emit('close')
        },
        getData() {
            let email = this.$store.state.userInfo.email;

            this.$store.dispatch("setLoading", true);

            axios.get('http://58.225.45.251:8200/api/res/resList/' + email)
                .then((res) => {
                    this.res1 = res.data
                })
            this.$store.dispatch("setLoading", false);
        },
        getData2() {
            this.$store.dispatch("setLoading", true);

            axios.get('http://58.225.45.251:8200/api/res/addPas/' + this.res_no)
                .then((res) => {
                    this.res2 = res.data;
                    this.addAdult = res.data.adult?.addAdult;
                    this.addChild = res.data.child?.addChild;
                    this.addInfant = res.data.infant?.addInfant;
                })
                
            this.$store.dispatch("setLoading", false);
        },
        Gender(value) {
            let gender = value
            if (gender == "male") {
                gender = "남자"
            } else if (gender == "female") {
                gender = "여자"
            }
            return gender
        },

    },
    mounted() {
        this.getData();
        this.getData2();
    }
}
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
