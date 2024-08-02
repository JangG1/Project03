<template>
<div class="">
    <button class="resDelCloseBtn" @click="closeModal">X</button>
</div>

<div class="resDelBody">
    <div class="resDelText">
        <h4>예약 번호 
        <span class="flightNum">[ Fastrip - {{ res_no }} ] </span></h4><br>
        <h3>예약을 취소하시겠습니까?</h3>
    </div>
    <button class="resDelBtn1" @click="resCancel">예</button>
    <button class="resDelBtn2" @click="closeModal">아니요</button>
</div>
</template>

<script>
import axios from 'axios'
import { EX_IP } from "../config";

export default {
    data() {
        return {
            res: [],
            res_no: this.$store.state.res_no
        }
    },
    props: {},
    methods: {
        closeModal() {
            this.$emit('close')
        },
        resCancel() {
            axios.post(EX_IP+":8200/api/res/remove/" + this.res_no)
                .then(res => {
                    console.log(res)
                    console.log("보내짐")                    
                })
                .catch(err => {
                    console.log(err)
                    console.log("안보내짐")
                })
                alert("예약이 취소되었습니다.")
                //삭제 후 페이지 새로고침
                window.location.reload()                
        },
    },
    mounted() {

    }
}
</script>

<style>

.resDelCloseBtn {
    color: teal;
    font-weight: 900;
    font-size: 20px;
    float: right;
    border: 1px solid white;
    background-color: white;
}

.resDelBody {
    color: teal;
    text-align: center;
}

.resDelBtn1,
.resDelBtn2 {
    color: white;
    background-color: teal;
    border: none;
    border-radius: 4px;
    padding: 6px;
    padding-left: 20px;
    padding-right: 20px;
    margin: 5%;    
    font-size: 16px;
}

.resDelText{
    padding: 16px 0;    
}

.resDelText h3{    
    font-size: 18px;
    font-weight: 900;
}

.resDelText h4{
    font-size: 18px;
    font-weight: 900;
}

.flightNum{
    color: #565555;    
}
</style>
