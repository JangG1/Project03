<template>
<div class="">
    <span class="passTitle">예약자 정보</span>
    <button class="passCloseBtn" @click="closeModal">X</button>
</div>

<div class="passInfoList">
    <div class="info">
        <span class="listLeft">한글 이름 :</span> <span class="listRight"> {{res.korLastName}} {{res.korFirstName}}</span><br><br>
        <span class="listLeft">영어 이름 :</span> <span class="listRight"> {{res.engLastName}} {{res.engFirstName}}</span><br><br>
        <span class="listLeft">생년 월일 :</span> <span class="listRight"> {{res.birthday}}</span><br><br>
        <span class="listLeft">성별 :</span> <span class="listRight"> {{Gender(res.gender)}}</span><br><br>
        <span class="listLeft">승객수 :</span> <span class="listRight"> 성인 {{res.adultCount}}명
            <span v-if="res.childCount > 0">, 유아 {{res.childCount}}명 </span>
            <span v-if="res.infantCount > 0">, 소아 {{res.infantCount}}명 </span>
        </span><br>
    </div>

</div>
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
    font-size: 24px;
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

.listLeft {
    font-weight: 900;
    color: #999;
    float: left;
    
}

.listRight{
    font-weight: 900;    
    float: right;
}
</style>
