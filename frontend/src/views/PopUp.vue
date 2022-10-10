<template>
<div class="container">
    <div class="row">
        <div class="col-xs-12">        
            <h3>승객 선택</h3>
                <button id="closeBtn" src="../assets/close.png" @click="$emit('closeModal')">X</button>            
        </div>
    </div>
    <hr>
    <br>
    <div class="row">
        <div class="col-sm-6 col-md-3">            
            <div>
                <h4>성인</h4><br><br>
                <img src="../assets/plus.png" @click="AdultPlus">
                <span>&nbsp; {{ AdultCount }} &nbsp;</span>
                <img src="../assets/minus.png" @click="AdultMinus">
            </div>
        </div>
        <div class="col-sm-6 ">
            <div>
                <h4>소아</h4><br><br>
                <img src="../assets/plus.png" @click="ChildPlus">
                <span>&nbsp; {{ ChildCount }} &nbsp;</span>
                <img src="../assets/minus.png" @click="ChildMinus">
            </div>
        </div>
        <div class="col-md-3">
            <div>
                <h4>유아</h4><br><br>
                <img src="../assets/plus.png" @click="InfantPlus">
                <span>&nbsp; {{ InfantCount }} &nbsp;</span>
                <img src="../assets/minus.png" @click="InfantMinus">
            </div>
        </div>
    </div>
    <div class="row">
        <button type="button" class="submitBtn">확인</button>
    </div>
</div>
</template>

<script>
export default {
    name: 'HelloWorld',
    data() {
        return {
            AdultCount: 0,
            ChildCount: 0,
            InfantCount: 0,
        }
    },
    methods: {
        AdultPlus() {
            this.AdultCount++;

        },
        AdultMinus() {
            if (this.AdultCount != 0)
                this.AdultCount--;
        },
        ChildPlus() {
            this.ChildCount++;

        },
        ChildMinus() {
            if (this.ChildCount != 0)
                this.ChildCount--;
        },
        InfantPlus() {
            this.InfantCount++;

        },
        InfantMinus() {
            if (this.InfantCount != 0)
                this.InfantCount--;
        },
    }
}
</script>

<style scoped>
h3 {
    float: left;
    margin-top: 30px;
    margin-left: 30px;
}

h4{

    font-size: 24px;
    color: #999;
    
    
    display: table;
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;
    margin-bottom: 30px;
}

span{
    border: none;
    border: 1px solid rgb(193, 188, 188);
    border-radius: 20px;
    font-size: 24px;
    color: #999;
    background: linear-gradient(135deg, rgba(230, 230, 230, 1) 0%, rgba(246, 246, 246, 1) 100%);
    margin: 15px;
}

img {
    cursor: pointer;
    width: 30px;
    height: 30px;
}

.container{    
    background-color: rgb(243, 240, 240);
}
.submitBtn {
    width: 200px;
    margin-top: 100px;
    display: table;
    margin-left: auto;
    margin-right: auto;
    border: 2px solid;
    border-radius: 5px;
    background-color: white;
}

#closeBtn {    
    float: right;
    margin-top: 22px;
    margin-right: 14px;
    border: none;
    background-color: white;    
}


</style>
