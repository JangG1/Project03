<template>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <h3>출발지 검색</h3>
            <button id="closeBtn" src="../assets/close.png" @click="$emit('closeModal')">X</button>
        </div>
    </div>
    <br>

    <div class="">

        <input class="SearchBar" type="text" v-model="AreaInput" @input="[searchGroup($event), hide()]" @keydown="[toggleShow()]"/>

        <div v-if=show class="autocomplete">
            <div v-for="group in groupList" :key="group" class="group-item">
                <input class="areaList" type="button" v-model="group.area" @click="[test($event),close()]">
            </div>
        </div>
    </div>
    <br>

    <!--test-->

</div>
</template>

<script>
import data from "../components/ToArea2.json";

const groupList = data;

export default {
    name: 'HelloWorld',
    components: {

    },
    data() {
        return {
            groupList,
            show: false,
            AreaInput: null,
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

        test(event) {
            let temp = event.target.value;
            event.target.value = this.temp;
            this.AreaInput = temp;
        },
        close() {
            this.show = false;
        },

        toggleShow() {
            if (this.AreaInput != "") {
                this.show = true;

            }
        },
        hide() {
            if (this.AreaInput == "") {
                this.show = false;
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
