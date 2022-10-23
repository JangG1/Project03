<template>
<i class="fas fa-search">
    <input v-model="AreaInput" @input="submitAutoComplete" type="text" style="margin-bottom : 15px;" />
</i>
<div class="autocomplete disabled">
    <div style="cursor: pointer" v-for="(res,i) in result" :key="i">
        <span id="area">{{res}}</span>
    </div>
</div>

<!--test-->
<i class="fas fa-search">
    <input v-model="AreaInput2" @input="submitAutoComplete2" type="text" style="margin-bottom : 15px;" />
</i>
<div class="autocomplete disabled">
    <div style="cursor: pointer" v-for="res in result" :key="res">
        <div id="area">{{res}}</div>
        <input type="button" class="toArea" v-model="result.res" @input="test">
    </div>
</div>

<!--test2-->
=================
<i class="fas fa-search">
    <input v-model="AreaInput2" @input="submitAutoComplete" type="text" style="margin-bottom : 15px;" />
</i>
<div class="autocomplete disabled">
    <div class="test" v-for="(areas, idx) in toArea2" :key="idx">
        {{areas.area}}
        {{idx}}
        <input type="button" class="toArea" v-model="toArea2" @input="test">
    </div>
</div>

<!--test3-->
<div v-for="(areas, idx) in toArea2" :key="idx">
    <select id="inputState" class="form-select" v-for="(a, idx) in areas" :key="idx">
        <option selected>{{a}}</option>
    </select>
</div>
</template>

<script>
import ToArea2 from "../components/ToArea2.json";

export default {
    data() {
        return {
            AreaInput: null,
            result: [],
            ToArea2: ToArea2,
            result2: [],
            temp: [],
            toArea2: ToArea2
        };
    },
    methods: {
        submitAutoComplete() {
            const autocomplete = document.querySelector(".autocomplete");
            if (this.AreaInput) {
                autocomplete.classList.remove("disabled");
                this.result = ToArea2.filter((area) => {
                    return area.match(new RegExp("^" + this.AreaInput, "i"));
                });
                console.log("1" + this.result);
                console.log("2" + this.AreaInput)
            } else {
                autocomplete.classList.add("disabled");
            }
        },
        submitAutoComplete2() {
            const autocomplete2 = document.querySelector(".autocomplete");
            if (this.AreaInput2) {
                autocomplete2.classList.remove("disabled");
                this.result2 = ToArea2.filter((area2) => {
                    return area2.match(new RegExp(this.AreaInput2, "i"));
                });
                console.log("1" + this.result2);
                console.log("2" + this.AreaInput2)
            } else {
                autocomplete2.classList.add("disabled");
            }
        },
        test() {
            let temp = this.result;
            this.result = this.temp;
            this.AreaInput = temp;
            console.log(this.result);
        },
        test2() {
            let temp = [];
            temp.push([this.result[0]]);
            console.log("result : " + this.result);
            console.log("temp1 : " + temp);
            return temp;
        },
        change() {
            let test = document.getElementById('area2');
            let convert = test.outerHTML;
            console.log(convert);
        }
    },
    computed: {

    },
    mounted() {

    }
}
</script>

<style>
#area:hover {
    color: black;
    border: 1px solid;
}
</style>
