<template>
<i class="fas fa-search">
    <input v-model="AreaInput" @input="submitAutoComplete" type="text" style="margin-bottom : 15px;" />
</i>
<div class="autocomplete disabled">
    <div style="cursor: pointer" v-for="(res,i) in result" :key="i">
       <input type="button" v-model="result" @click="test">
    </div>    
</div>
</template>

<script>
import ToARea from "../ToARea.js";

export default {
    data() {
        return {
            AreaInput: null,
            result: null,
        };
    },
    methods: {
        submitAutoComplete() {
            const autocomplete = document.querySelector(".autocomplete");            
            //const korean = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/;
            if (this.AreaInput) {
                autocomplete.classList.remove("disabled");
                this.result = ToARea.filter((area) => {                    
                    return area.match(new RegExp("^" + this.AreaInput, "i"));
                });
                console.log("1"+this.result);
                console.log("2"+this.AreaInput)
            } else {
                autocomplete.classList.add("disabled");
            }
        },
        test(){
            console.log(this.result);
            let temp = this.result;
            this.result = this.fromBAreaInputtn;
            this.AreaInput = temp;
        },
        
    },
    mounted(){
        
    }
}
</script>
