<template>
<div class="">
    <span class="passTitle">예약자 정보</span>{{ addAdult }}<br>{{res}}
    <button class="passCloseBtn" @click="closeModal">X</button>
</div>

<div class="passCount1">승객수 :
    <span class="passCount2"> (총 {{user[pasIndex].adultCount+user[pasIndex].childCount+user[pasIndex].infantCount}}명)</span>
    <span class="passCount2" v-if="user[pasIndex].infantCount > 0">, 소아 {{user[pasIndex].infantCount}}명</span>
    <span class="passCount2" v-if="user[pasIndex].childCount > 0">, 유아 {{user[pasIndex].childCount}}명</span>
    <span class="passCount2">성인 {{user[pasIndex].adultCount}}명</span>
</div>
<br>

<!-- 예약자 정보 -->
<div class="passInfoList">
    <div class="passInfoTitle">
        승객 [예약자]<span v-if="res.adultCount > 1">1</span>
    </div>
    <div class="info">
        <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{user[0].korName}} </span><br><br>
        <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{user[0].engLastName}} {{user[0].engFirstName}} </span><br><br>
        <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{user[0].birthday}}</span><br><br>
        <span class="listLeft">성별 :</span> <span class="listRight"> {{Gender(user[0].gender)}}</span><br><br>
    </div>
</div>

<!-- 추가 승객 정보 (성인) -->
<span v-for="(pas,index) in addAdult" :key="index">
    <div class="passInfoList" v-if="user[0].adultCount > 1">
        <div class="passInfoTitle">
            승객 {{ index + 2 }}
        </div>
        <div class="info">
            <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{addAdult[index].korName}}</span><br><br>
            <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{addAdult[index].engLastName}} {{addAdult[index].engFirstName}}</span><br><br>
            <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{addAdult[index].birthday}}</span><br><br>
            <span class="listLeft">성별 :</span> <span class="listRight"> {{addAdult[index].gender}}</span><br><br>
        </div>
    </div>
</span>

<!-- 추가 승객 정보 (유아) -->
<span v-for="(pas,index) in addChild" :key="index">
    <div class="passInfoList" v-if="user[0].childCount > 0">
        <div class="passInfoTitle">
            승객 {{ index + 2 }}
        </div>
        <div class="info">
            <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{addChild[index].korName}}</span><br><br>
            <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{addChild[index].engLastName}} {{addChild[index].engFirstName}}</span><br><br>
            <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{addChild[index].birthday}}</span><br><br>
            <span class="listLeft">성별 :</span> <span class="listRight"> {{addChild[index].gender}}</span><br><br>
        </div>
    </div>
</span>

<!-- 추가 승객 정보 (소아) -->
<span v-for="(pas,index) in addInfant" :key="index">
    <div class="passInfoList" v-if="user[0].infantCount > 0">
        <div class="passInfoTitle">
            승객 {{ index + 2 }}
        </div>
        <div class="info">
            <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{addInfant[index].korName}}</span><br><br>
            <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{addInfant[index].engLastName}} {{addInfant[index].engFirstName}}</span><br><br>
            <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{addInfant[index].birthday}}</span><br><br>
            <span class="listLeft">성별 :</span> <span class="listRight"> {{addInfant[index].gender}}</span><br><br>
        </div>
    </div>
</span>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            res: [],
            addAdult: [],
            addChild: [],
            addInfant: [],
            pasIndex: this.$store.state.res_no - 1, 
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
        getData2() {            
            axios.get('/res/addPas/' + this.res_no)
                .then((res) => {
                    this.res = res.data;
                    this.addAdult = res.data.adult.addAdult;
                    this.addChild = res.data.child.addChild;
                    this.addInfant = res.data.infant.addInfant;
                    console.log(res)
                    console.log(this.addAdult)
                })

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
    font-size: 28px;
}

.passCount1 {
    margin-top: 30px;
    margin-left: 10px;
    font-weight: 900;
    font-size: 20px;
    color: rgba(35, 163, 163, 0.831);
}

.passCount2 {
    float: right;
}

.passCloseBtn {
    color: teal;
    font-weight: 900;
    font-size: 22px;
    float: right;
    border: 1px solid white;
    background-color: white;
}

.info {
    margin-top: 25px;

}

.passInfoList {
    border: 1px solid teal;
    border-radius: 4px;
    height: 300px;
    margin-top: 20px;
    font-size: 20px;
}

.passInfoTitle {
    color: white;
    background-color: teal;
    border-top-left-radius: 4px;
    border-top-right-radius: 4px;
    font-weight: 900;
    font-size: 20px;
    height: 45px;
    padding: 8px 0;
    padding-left: 20px;
}

.listLeft {
    font-weight: 900;
    color: #999;
    float: left;
    padding-left: 20px;
}

.listRight {
    font-weight: 900;
    float: right;
    padding-right: 20px;
}
</style>
