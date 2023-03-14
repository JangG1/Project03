<template>
<div>
    <div class="areaSearchTitle">
        <h3>출발지 검색</h3>
        <!--모달 종료 버튼-->
        <button class="fromAreaClose" id="closeBtn" src="../assets/close.png" @click="this.$emit('close')">X</button>
    </div>
    <br>

    <div class="fromSearchBar">
        <!--검색창-->
        <input class="search-input" v-model="fromAreaInput" type="text" placeholder="도시, 공항" @input="searchGroup($event)" @keydown="[toggleShow()]" />
        <!--검색 내역-->
        <div class="group-list" v-if=show>
            <li v-for="FromArea in FromAreas" :key="FromArea">
                <input type="button" v-model="FromArea.area" class="group-item" @click="[select($event),close()]">
            </li>
        </div>
    </div>
    <!--확인 버튼-->
    <button type="button" v-if="fromButton" class="fromButton" @click="submit()">확인</button>
</div>
</template>

<script>
import data from "@/components/FromArea.json";

const FromAreas = data;

export default {
    data() {
        return {
            FromAreas,
            show: false,
            fromAreaInput: null,
            result: [],
            fromButton: false,
        };
    },

    methods: {
        searchGroup(event) {
            const len = this.FromAreas.length;

            for (let i = 0; i < len; i++) {
                if (
                    this.FromAreas[i].area.includes(event.target.value) === false &&
                    this.FromAreas[i].area.toLowerCase().indexOf(event.target.value.toLowerCase())
                ) {
                    document.querySelectorAll(".group-item")[i].style.display = "none";
                } else {
                    document.querySelectorAll(".group-item")[i].style.display = "flex";                    
                }
            }

            
        },
        select(event) {
            let temp = event.target.value;
            this.fromAreaInput = temp;
            this.fromButton = true;
        },
        close() {
            this.show = false;
        },
        toggleShow() {
            if (this.fromAreaInput != "") {
                this.show = true;

            }
        },
        submit() {
            this.$emit('update-fromArea', this.fromAreaInput);
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

.fromAreaClose {
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

.fromButton {
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

.fromSearchBar {
    margin-top: 8%;
}
</style>
