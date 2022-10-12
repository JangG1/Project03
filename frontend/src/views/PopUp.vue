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
                <button type="button" @click="AdultPlus">+</button>
                <span>&nbsp; {{ AdultCount }} &nbsp;</span>
                <button type="button" @click="AdultMinus">-</button>                
            </div>
        </div>
        <div class="col-sm-6 ">
            <div>
                <h4>소아</h4><br><br>
                <button type="button" @click="ChildPlus">+</button>
                <span>&nbsp; {{ ChildCount }} &nbsp;</span>
                <button type="button" @click="ChildMinus">-</button>
            </div>
        </div>
        <div class="col-md-3">
            <div>
                <h4>유아</h4><br><br>
                <button type="button" @click="InfantPlus">+</button>
                <span>&nbsp; {{ InfantCount }} &nbsp;</span>
                <button type="button" @click="InfantMinus">-</button>
            </div>
        </div>
        <button type="button" class="submitBtn" @click="submit()">확인</button>
    </div>
  <div>{{city}}</div>
  <button type="button" @click="changeName">교체</button>
</div>
<Calendar :city="city"></Calendar>
<Calendar 
:name="name"
:phone="phone"
:hasDog="hasDog"
>
</Calendar>

</template>

<script>
import Calendar from './Calendar.vue';

export default {
    name: 'HelloWorld',
    props : {            
    },
    components: {
        Calendar,
},
    data() {
        return {
            AdultCount: 1,
            ChildCount: 0,
            InfantCount: 0,
            name: 'Hoza',
            phone: '1234-5678',
            hasDog: true,
            city: "Tokyo"
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
        changeName(){
            this.city = "Dio";
        }
        
    }
}
</script>

<style scoped>
h3 {
    float: left;
    margin-top: 30px;
    margin-left: 30px;
    color: #999;
}

h4 {
    font-size: 24px;
    color: #999;
    display: table;
    margin-left: auto;
    margin-right: auto;
    margin-top: 10px;
    
}

span {
    border-radius: 20px;
    margin: 15px;
}

img {
    cursor: pointer;
    width: 30px;
    height: 30px;
}

button:not(#closeBtn,.submitBtn){
    width: 40px;
    border: none;
    border: 1px solid rgb(193, 188, 188);
    border-radius: 15px;
    font-size: 24px;
    color: #999;
    background: linear-gradient(135deg, rgba(230, 230, 230, 1) 0%, rgba(246, 246, 246, 1) 100%);   
}

.container {
    background-color: rgb(244, 244, 244);    
    border-radius: 15px;
}

.submitBtn {
    width: 200px;
    margin-top: 90px;
    margin-bottom: 30px;
    display: table;
    margin-left: auto;
    margin-right: auto;
    border: none;
    border: 1px solid rgb(193, 188, 188);
    border-radius: 15px;
    font-size: 24px;
    color: #999;
    background: linear-gradient(135deg, rgba(230, 230, 230, 1) 0%, rgba(246, 246, 246, 1) 100%);   
}

#closeBtn {
    width: 32px;
    height: 40px;
    float: right;
    margin-top: 22px;
    margin-right: 14px;
    border: none;   
    font-size: 24px;
    color: #999;
    background-color: rgb(244, 244, 244);  
}
</style>
