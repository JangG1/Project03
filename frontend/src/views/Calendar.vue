<template>
<fieldset>
    <legend>
        Datalist Form
    </legend>
    <label>Select Browser</label>
    <input  @keyup="[toggleShow(), hide()]" v-model="area" autocomplete="off" list="browsers" placeholder="Select your fav browser">
    

    <datalist id="browsers" v-if="show">

        <option v-for="(item, index) in toArea2" :key="index" :value="item.area" />

    </datalist>

</fieldset>
{{toArea2}}
</template>

<script>
import ToArea2 from "../components/ToArea2.json";

export default {
    name: "HelloWorld",
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
        },hide(){
          if (this.area == "") {
                this.show = false;
                
            }
        },
    }
};
</script>

<style>
fieldset {
    border: 1px solid blue;
    width: 360px;
    border-radius: 5px;
}

legend,
label {
    color: blue;
    font-size: 24px;
    font-family: sans-serif;
}

input {
    font-size: 18px;
    padding: 5px;
    height: 35px;
    width: 350px;
    border: 1px solid blue;
    outline: none;
    border-radius: 5px;
    color: blue;
    /*   border-bottom: none; */
}

datalist {
    position: absolute;
    background-color: white;
    border: 1px solid blue;
    border-radius: 0 0 5px 5px;
    border-top: none;
    font-family: sans-serif;
    width: 350px;
    padding: 5px;
    max-height: 10rem;
    overflow-y: auto
}

option {
    background-color: white;
    padding: 4px;
    color: blue;
    margin-bottom: 1px;
    font-size: 18px;
    cursor: pointer;
}

option:hover,
.active {
    background-color: lightblue;
}
</style>
