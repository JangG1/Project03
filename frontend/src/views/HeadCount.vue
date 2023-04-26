<template>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <h3>승객 선택</h3>
            <button class="countClose" id="closeBtn" src="../assets/close.png" @click="this.$emit('close')">X</button>
        </div>
    </div>
    <hr>
    <br>
    <div class="countBox">
        <div class="countBox1">
            <div>성인
                <button class="descBtn" type="button" @click="showAdultDesc">?</button>
            </div>
            <div v-if="adultDesc" class="adultDesc">국제선 만 12세 이상, 국내선 만 13세 이상</div>
            <div class="countBtn">
                <div class="plus" @click="AdultPlus">+</div>
                <div class="count2"> {{ AdultCount }} </div>
                <div class="minus" @click="AdultMinus">-</div>
            </div>
        </div>
        <div class="countBox2">
            <div>유아
                <button class="descBtn" type="button" @click="showChildDesc">?</button>
            </div>
            <div v-if="childDesc" class="childDesc">국제선 만 12세 미만, 국내선 만 13세 미만</div>
            <div class="countBtn">
                <div class="plus" @click="ChildPlus">+</div>
                <div class="count2"> {{ ChildCount }} </div>
                <div class="minus" @click="ChildMinus">-</div>
            </div>
        </div>
        <div class="countBox3">
            <div>소아
                <button class="descBtn" type="button" @click="showInfantDesc">?</button>
            </div>
            <div v-if="infantDesc" class="infantDesc">만 2세 미만</div>
            <div class="countBtn">
                <div class="plus" @click="InfantPlus">+</div>
                <div class="count2"> {{ InfantCount }} </div>
                <div class="minus" @click="InfantMinus">-</div>
            </div>
        </div>
    </div>

    <button type="button" class="countSubBtn" @click="submit()">확인</button>

</div>
<!--Test-->
</template>

<script>
import AOS from 'aos';
import "aos/dist/aos.css";

export default {
    name: 'HelloWorld',
    props: [

    ],
    data() {
        return {
            AdultCount: 1,
            ChildCount: 0,
            InfantCount: 0,
            count: [],
            adultDesc: false,
            childDesc: false,
            infantDesc: false,
        }
    },
    created() {
        AOS.init();
    },
    methods: {
        AdultPlus() {
            this.AdultCount++;
        },
        AdultMinus() {
            if (this.AdultCount != 1)
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
        showAdultDesc() {
            this.adultDesc = (this.adultDesc) ? false : true
        },
        showChildDesc() {
            this.childDesc = (this.childDesc) ? false : true
        },
        showInfantDesc() {
            this.infantDesc = (this.infantDesc) ? false : true
        },
        submit() {
            this.$emit('update-count', this.AdultCount, this.ChildCount, this.InfantCount);
        },

    }
}
</script>

<style scoped>
.col-xs-12>h3 {
    float: left;
    margin-top: 30px;
    margin-left: 30px;
    font-size: 20px;
    font-weight: 900;
}

h4 {
    font-size: 18px;
    color: #616161;
    display: table;
    margin-left: auto;
    margin-right: auto;
    margin-top: 10px;
}

.countBox {
    display: flex;
    width: 600px;    
    font-size: 18px;
    margin-left: 22px;
}

.countBox1,
.countBox2,
.countBox3 {
    width: 200px;
    margin-left: 50px;
    margin-right: 50px;
}

.countBtn {
    display: flex;    
    margin-top: 30px;
    height: 30px;
}

.count2 {
    font-size: 20px;
    width: 30px;
    height: 35px;
    border-bottom: 2px solid;
    margin-left: 20px;
    margin-right: 20px;
}

.plus,
.minus {

    width: 30px;
    border-radius: 20px;
    border: 1.5px solid #cdcdcd;
    cursor: pointer;
}

.plus:hover,
.minus:hover {
    border: 1.5px solid teal;
}

.descBtn {
    width: 20px;
    height: 20px;
    border: 1.5px solid #616161;
    border-radius: 20px;
    margin-left: 4px;
    font-size: 1px;    
}

.descBtn:hover {
    border: 1.5px solid teal;
}

.adultDesc,
.childDesc,
.infantDesc {
    border: 1.5px solid teal;
    border-radius: 10px;
    position: fixed;
    margin-top: 15px;
    font-size: 14px;
    height: 35px;
    padding: 5px 0;
}

.adultDesc:after,
.childDesc:after,
.infantDesc:after {
    content: "";
    border-top: 0px solid transparent;
    border-left: 8px solid transparent;
    border-right: 8px solid transparent;
    border-bottom: 17px solid rgb(13, 165, 165);
    position: absolute;
    top: -17px;
    left: 77px;    
}

.adultDesc{
    width: 300px;
    color: white;
    border: 1px solid rgb(13, 165, 165);
    background: rgb(13, 165, 165);
}
.childDesc{
    width: 300px;
    color: white;
    border: 1px solid rgb(13, 165, 165);
    background: rgb(13, 165, 165);
}
.infantDesc {
    width: 130px;
    color: white;
    border: 1px solid rgb(13, 165, 165);
    background: rgb(13, 165, 165);
}

.countClose {
    border: none;
    background-color: rgb(244, 244, 244);
    color: #606060;
    float: right;
    font-size: 20px;
    font-weight: 900;
    margin-right: 2%;
    margin-top: 3%;
}

.countSubBtn {
    width: 160px;
    height: 40px;
    font-size: 14px;
    font-weight: 900;
    border-radius: 4px;
    color: teal;
    border: 3px solid teal;
    background-color: white;
    margin-top: 60px;
    margin-bottom: 40px;
    margin-left: auto;
    margin-right: auto;
}
</style>
