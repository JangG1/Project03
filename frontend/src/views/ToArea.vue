<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <h3>출발지 검색</h3>
                <button id="closeBtn" src="../assets/close.png" @click="$emit('closeModal')">X</button>
            </div>
        </div>
        <br>
        <div>
            <input class="SearchBar" @input="test" @keyup="[toggleShow(), hide()]" v-model="area" list="browsers" placeholder="도시, 공항">
    
            <datalist id="browsers" v-if="show">
    
                <option v-for="(item, index) in toArea2" :key="index" :value="item.area" />
    
            </datalist>
    
        </div>
    
    </div>
    </template>
    
    <script>
    import ToArea2 from "../components/ToArea2.json";
    
    export default {
        name: 'HelloWorld',
        components: {
    
        },
        data() {
            return {
                show: false,
                toArea2: ToArea2,
            }
        },
        methods: {
            toggleShow() {            
                if (this.area != "") {
                    this.show = true;  
                    
                }
            },
            hide() {
                if (this.area == "") {
                    this.show = false;
    
                }
            },test(e){
          console.log(e.target.value)
          let message = e.target.value
          let pattern = /([^가-힣\x20])/i
          this.valid = (message.length > 1 && pattern.test(message) === false)
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
    
    .SearchBar {
        width: 96%;
        height: 50px;
        font-size: 22px;
        border: 3px solid rgb(192, 192, 192);
        background-color: rgb(246, 246, 246);
        text-align: left;
        padding-left: 20px;
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
        background-color: rgb(246, 246, 246);
    }
    </style>
    