<template>
<div class="">
    <span class="passTitle">예약자 정보</span>
    <button class="passCloseBtn" @click="closeModal">X</button>
</div>

<div class="passCount1">승객수 :
    <span class="passCount2"> (총 {{res.adultCount+res.childCount+res.infantCount}}명)</span>     
    <span class="passCount2" v-if="res.infantCount > 0">, 소아 {{res.infantCount}}명</span>
    <span class="passCount2" v-if="res.childCount > 0">, 유아 {{res.childCount}}명</span>
    <span class="passCount2">성인 {{res.adultCount}}명</span>     
</div><br>

<div class="passInfoList">
    <div class="passInfoTitle">
        승객 <span v-if="res.adultCount > 1">1</span>
    </div>
    <div class="info">        
        <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{res.korName}} </span><br><br>
        <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{res.engName}} </span><br><br>
        <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{res.birthday}}</span><br><br>
        <span class="listLeft">성별 :</span> <span class="listRight"> {{Gender(res.gender)}}</span><br><br>        
    </div>
</div>

<span v-for="(pas,index) in res.addPassGender" :key="index">
<div class="passInfoList">
    <div class="passInfoTitle">
        승객 {{ index + 2 }}
    </div>
    <div class="info">        
        <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{res.addPassKorName[index*2+1]}}{{res.addPassKorName[index*2]}}</span><br><br>
        <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{res.addPassEngName[index*2+1]}} {{res.addPassEngName[index*2]}} </span><br><br>
        <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{res.addPassBirthday[index]}}</span><br><br>
        <span class="listLeft">성별 :</span> <span class="listRight"> {{res.addPassGender[index]}}</span><br><br>
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
        }
    },
    props: {
        user: {
            type: String,
            default: ''
        }
    },
    methods: {
        closeModal() {
            this.$emit('close')
        },
        getData() {
            let res_no = this.$store.state.res_no + 1;

            axios.get('/res/' + res_no + '')
                .then((response) => {
                    this.res = response.data
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
        this.getData()

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

.passCount1{
    margin-top: 30px;
    margin-left: 10px;
    font-weight: 900;
    font-size: 20px;
    color: rgba(35, 163, 163, 0.831);
}

.passCount2{
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

.passInfoList{
    border: 1px solid teal;
    border-radius: 4px;
    height: 300px;
    margin-top: 20px;
    font-size: 20px;
}

.passInfoTitle{
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

.listRight{
    font-weight: 900;    
    float: right;
    padding-right: 20px;
}
</style>
