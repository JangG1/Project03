<template>
    <div class="">
        <div class="infoTitle">
            <h3>여행 예약 정보</h3>
            <!-- <button class="payModalCloseBtn" @click="this.$emit('close')">X</button> -->
        </div>
        <br>

        <div class="infoList">
            <div class="mInfo">
                승객 정보
            </div>
            {{ name }}
        </div>
    

        <br>
    
        <!--test-->
        <button type="button" class="reserBtn" @click="submit()">예약 하기</button>
    </div>
    </template>
    
    <script>
    export default {
        name: 'HelloWorld',
        components: {
    
        },
        data() {
            return {
            }
        },
        props:{
            name: {
            type: String,
            default: ''
        },
        },
        methods: {      
            updateTest(){

            },
            Gender(value){
                let gender = value
                if(gender == "male"){
                    gender = "남자"
                }else if(gender == "female"){
                    gender = "여자"
                }
                return gender
            },
            submit(){
                alert("예약하시겠습니까?")
            }
        },
        creted(){

        }
    }
    </script>
    
    <style>
    
    .infoTitle h3{
        margin-top: 2%;
        margin-left: 4%;
        color: teal;
        font-weight: 900;
    }
    
    .payModalCloseBtn {
        float: right;
        border: none;
        font-size: 24px;
        color: #999;
        background-color: white;
    }
    
    .infoList {
        border: 1px solid teal;
        border-radius: 4px;
        margin-bottom: 40px;
        
    }
    
    .infoList h3 {
        font-size: 20px;
        font-weight: 900;
        color: #999;
    }
    
    .mInfo,
    .To,
    .From{
        font-weight: 900;
        font-size: 24px;
        color: white;
        background-color: teal;
        padding: 6px;
        margin-bottom: 20px;
    }
    
    .reserBtn {
        width: 180px;
        height: 50px;
        font-size: 24px;
        color: white;
        border: 1px solid white;
        border-radius: 4px;
        background-color: teal;
    }
    </style>
    