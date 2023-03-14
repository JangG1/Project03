<template>
    <div>
        <div class="areaSearchTitle">
            <h3>도착지 검색</h3>
            <!--모달 종료 버튼-->
            <button class="toAreaClose" id="closeBtn" src="../assets/close.png" @click="this.$emit('close')">X</button>
        </div>
        <br>
    
        <div class="toSearchBar">
            <!--검색창-->
            <input class="search-input" v-model="toAreaInput" type="text" placeholder="도시, 공항" @input="searchGroup($event)" @keydown="[toggleShow()]" />
            <!--검색 내역-->
            <div class="group-list" v-if=show>
                <li v-for="ToArea in ToAreas" :key="ToArea">
                    <input type="button" v-model="ToArea.area" class="group-item" @click="[select($event),close()]">
                </li>
            </div>
        </div>
        <!--확인 버튼-->
        <button type="button" v-if="toButton" class="toButton" @click="submit()">확인</button>
    </div>
    </template>
    
    <script>
    import data from "@/components/ToArea.json";
    
    const ToAreas = data;
    
    export default {
        data() {
            return {
                ToAreas,
                show: false,
                toAreaInput: null,
                result: [],
                toButton: false,
            };
        },
    
        methods: {
            searchGroup(event) {
                const len = this.ToAreas.length;
    
                for (let i = 0; i < len; i++) {
                    if (
                        this.ToAreas[i].area.includes(event.target.value) === false &&
                        this.ToAreas[i].area.toLowerCase().indexOf(event.target.value.toLowerCase())
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
                this.toButton = true;
            },
            close() {
                this.show = false;
            },
            toggleShow() {
                if (this.toAreaInput != "") {
                    this.show = true;
    
                }
            },
            submit() {
                this.$emit('update-toArea', this.toAreaInput);
            }
        },
    };
    </script>
    
    <style>
    .areaSearchTitle {
        font-weight: 900;
        font-size: 24px;
        margin-top: 2%;
        margin-left: 2%;
    }
    
    .areaSearchTitle h3{
        float: left;
    }
    
    .toAreaClose {
        border: none;
        background-color: rgb(250, 250, 250);
        color: #606060;
        float: right;
        font-size: 22px;
        font-weight: 900;
        margin-right: 1%;
    }
    
    .search-input {
        padding: 18px;
        padding-left: 18px;
        width: 95%;
        font-size: 18px;
        font-weight: 900;
        border: 2px solid teal;
        border-radius: 4px;
        box-shadow: 1px 1px 6px #999;
        position: sticky;
        top: 0;
    }
    
    .group-list li {
        display: flex;               
    }
    
    .group-item {
        border: 1px solid;
        background-color: white;
        font-weight: 600;
        color: #363636;
        margin-left: 25px;        
        width: 95%;
        box-shadow: 1px 1px 6px #999;
        padding: 20px;
        display: contents;
    }
    
    .toButton {
        width: 85px;
        height: 50px;
        font-size: 20px;
        font-weight: 900;
        border-radius: 4px;
        color: teal;
        border: 3px solid teal;
        background-color: white;
        margin-top: 200px;        
    }
    
    .toSearchBar {
        margin-top: 8%;
    }
    </style>
    