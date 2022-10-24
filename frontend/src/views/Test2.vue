<template>
<div class="container">
    <div>
        <Logo />
        <h1 class="title">
            select data 테스트
        </h1>
    </div>
    <select v-for="n in selectCnt" :key="n" v-model="selectExcludes[n-1]" class="select">
        <option value="undefined">
            선택 안함
        </option>
        <option v-for="(item, index) in selectList" :key="index" :disabled="isVisible(item.id)" :value="item.id">
            {{ item.name }}
        </option>
    </select>
    <br><br>
    <input type="button" value="현재 데이터 확인" @click="dataCheck">
    <br><br>
    <!--test-->
    <i class="fas fa-search">
        <input v-model="AreaInput" @input="submitAutoComplete" type="text" style="margin-bottom : 15px;" />
    </i>
    <div class="autocomplete disabled">
        <div style="cursor: pointer" v-for="(res,i) in result" :key="i">
            <span id="area">{{res}}</span>            
            <input type="button" v-model="result" @click="test">            
        </div>
    </div>
    <select v-model="selected">
        <option v-for="(item, index) in toArea2" :key="index" :disabled="isVisible(item.id)" :value="item.area">
            {{ item.area }}
        </option>
    </select>
    selected : {{selected}}

    <!--test2-->
    ================================================
    <div>
  
  <input type="text" list="list"/>
  <datalist id ="list">
    <option v-for="(item, index) in toArea2" :key="index" :value="item.area" />
  </datalist>
</div>
</div>
</template>

<script>

export default {
    name: "HelloWorld",
    data() {
        return {        
            AreaInput: null,
            result: [],
            selected: "",                                                        
            selectExcludes: []
        }
    },
    methods: {
        isVisible(data) {
            let returnFlag = false
            for (const i in this.selectExcludes) {
                if (this.selectExcludes[i] === data) {
                    returnFlag = true
                }
            }
            return returnFlag
        },
        dataCheck() {
            console.log(this.selectExcludes)
        },

       
        test() {
            let temp = this.result;
            this.result = this.temp;
            this.AreaInput = temp;
            console.log(this.result);
        },
    }
}
</script>

<style>
#area:hover {
    color: black;
    border: 1px solid;
}
</style>