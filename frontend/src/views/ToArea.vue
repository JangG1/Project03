<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <h3>도착지 검색</h3>
                <button id="closeBtn" src="../assets/close.png" @click="this.$emit('close')">X</button>
            </div>
        </div>
        <br>
    
        <div class="">
    
            <input class="SearchBar" type="text" v-model="toAreaInput" @input="[searchGroup($event), hide()]" @keydown="[toggleShow()]" placeholder="도시, 공항"/>
    
            <div v-if=show class="">
                <div v-for="group in groupList" :key="group" class="group-item">
                    <input class="areaList" type="button" v-model="group.area" @click="[select($event),close()]">                
                    
                </div>
            </div>
        </div>
        <br>
    
        <!--test-->
    <button type="button" @click="submit()">확인</button>
    </div>
    </template>
    
    <script>
    import data from "../components/ToArea.json";
    
    const groupList = data;
    
    export default {
        name: 'HelloWorld',
        components: {
    
        },
        data() {
            return {
                groupList,
                show: false,
                toAreaInput: null,
                result: [],
            }
        },
        methods: {
            searchGroup(event) {
                const len = this.groupList.length;
                
                for (let i = 0; i < len; i++) {
                    if (this.groupList[i].area.toLowerCase().indexOf(event.target.value.toLowerCase())
                    ) {                        
                        document.querySelectorAll(".group-item")[i].style.display = "none";
                    } else {
                        document.querySelectorAll(".group-item")[i].style.display = "flex";
                    }
                }
    
            },
    
            select(event) {
                let temp = event.target.value;
                this.toAreaInput = temp;
            },
            close() {
                this.show = false;
            },
    
            toggleShow() {
                if (this.toAreaInput != "") {
                    this.show = true;
    
                }
            },
            hide() {
                if (this.toAreaInput == "") {
                    this.show = false;
                    setTimeout(() => {
                        
                }, 1000)
                
                }            
            },
            submit(){
                this.$emit('update-toArea', this.toAreaInput);
            }
    
        },

        
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
    
    .areaList {
        border: 1px solid;
        width: 96%;
        text-align: left;
        padding: 10px;
        font-size: 20px;
        background-color: white;
        margin-left: 2%;
    }
    </style>
    