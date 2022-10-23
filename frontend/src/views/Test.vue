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
        <br>
    
        <!--test-->
        <i class="fas fa-search">
            <input class="SearchBar" v-model="AreaInput" @input="submitAutoComplete" type="text"  />
        </i>
        <br>
    
        <div class="autocomplete">
            <div style="cursor: pointer" v-for="(res,i) in result" :key="i">
                <span id="area" @click="test1">res : {{res}}</span>
                <span id="area" @click="test2">result : {{result}}</span>
                <input type="button" class="toArea" v-model="result" @click="test">
            </div>
        </div>
    
    </div>
    </template>
    
    <script>
    import ToArea2 from "../components/ToArea2.json";
    import ToArea from "../ToArea.js";
    
    export default {
        name: 'HelloWorld',
        components: {
    
        },
        data() {
            return {
                show: false,
                toArea2: ToArea2,
                AreaInput: null,
                result: [],
                ToArea2: ToArea2,
                result2: [],
                temp: [],
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
            },
            test() {            
                let temp = this.result;
                this.result = this.temp;
                this.AreaInput = temp;
                console.log(this.result);
            },
            test1() {                        
                console.log(this.res);
            },
            test2() {            
                console.log(this.result);
            },
            submitAutoComplete() {
                const autocomplete = document.querySelector(".autocomplete");
                if (this.AreaInput) {
                    autocomplete.classList.remove("disabled");
                    this.result = ToArea.filter((area) => {
                        return area.match(new RegExp("^" + this.AreaInput, "i"));
                    });
                    console.log("1" + this.result);                
                    
                } else {
                    autocomplete.classList.add("disabled");
                }
            },
    
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
    
    .autocomplete {
        color: blue;
        border: 1px solid;
    }
    
    </style>
    