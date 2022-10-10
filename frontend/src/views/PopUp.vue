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
                성인<br>
                <img src="../assets/plus.png" @click="AdultPlus">
                &nbsp; {{ AdultCount }} &nbsp;
                <img src="../assets/minus.png" @click="AdultMinus">
            </div>
        </div>
        <div class="col-sm-6 ">
            <div>
                소아<br>
                <img src="../assets/plus.png" @click="ChildPlus">
                &nbsp; {{ ChildCount }} &nbsp;
                <img src="../assets/minus.png" @click="ChildMinus">
            </div>
        </div>
        <div class="col-md-3">
            <div>
                유아<br>
                <img src="../assets/plus.png" @click="InfantPlus">
                &nbsp; {{ InfantCount }} &nbsp;
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

img {
    cursor: pointer;
    width: 30px;
    height: 30px;
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
