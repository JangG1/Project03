<template>
<div class="fromTitle">
    <h3>출발지 검색</h3>
    <button class="fromAreaClose" id="closeBtn" src="../assets/close.png" @click="this.$emit('close')">X</button>
</div>
<br>

<div class="fromSearchBar">

    <input class="SearchBar" type="text" v-model="fromAreaInput" @input="[searchGroup($event), hide()]" @keydown="[toggleShow()]" placeholder="도시, 공항" />

    <div v-if=show>
        <div v-for="group in groupList" :key="group" class="group-item">
            <input class="areaList" type="button" v-model="group.area" @click="[select($event),close()]">
        </div>
    </div>
</div>
<br>

<button type="button" class="fromButton" @click="submit()">확인</button>
</template>

<script>
import data from "../components/FromArea.json";

const groupList = data;

export default {
    name: 'HelloWorld',
    components: {

    },
    data() {
        return {
            groupList,
            show: false,
            fromAreaInput: null,
            result: [],
        }
    },
    methods: {
        searchGroup(event) {
            const len = this.groupList.length;

            for (let i = 0; i < len; i++) {
                if (this.groupList[i].area.toLowerCase().indexOf(event.target.value.toLowerCase())) {
                    document.querySelectorAll(".group-item")[i].style.display = "none";
                } else {
                    document.querySelectorAll(".group-item")[i].style.display = "flex";
                }
            }
        },
        select(event) {
            let temp = event.target.value;
            this.fromAreaInput = temp;
        },
        close() {
            this.show = false;
        },
        toggleShow() {
            if (this.fromAreaInput != "") {
                this.show = true;

            }
        },
        hide() {
            if (this.fromAreaInput == "") {
                this.show = false;
                setTimeout(() => {

                }, 1000)

            }
        },
        submit() {
            this.$emit('update-fromArea', this.fromAreaInput);
        }
    },
}
</script>

<style scoped>
/*.fromTitle {
    
}

.fromTitle h3 {
    color: #999;
    margin-top: 2%;
    margin-left: 2%;
    float: left;
}

.fromAreaClose {
    border: none;
    background-color: rgb(246, 246, 246);
    color: #999;
    padding: 20px;    
    float: right;
    font-size: 20px;
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
    margin-top: 5%;
}

.fromSearchBar {
    margin-top: 8%;
}

.SearchBar {
    width: 96%;
    height: 50px;
    font-size: 22px;
    border: 3px solid teal;
    background-color: white;
    border-radius: 4px;
    text-align: left;
    padding-left: 20px;
}

.areaList {
    border: 1px solid;
    width: 96%;
    text-align: left;
    padding: 10px;
    font-size: 20px;
    background-color: white;
    margin-left: 2%;
}*/
</style>
