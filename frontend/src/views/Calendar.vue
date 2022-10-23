<template lang="en">

   

        <select id="browsers" role="listbox">
            <option value="Internet Explorer">Internet Explorer</option>
            <option value="Chrome">Chrome</option>
            <option value="Safari">Safari</option>
            <option value="Microsoft Edge">Microsoft Edge</option>
            <option value="Firefox">Firefox</option>
            <option value="Microsoft Edge">Microsoft Edge</option>
            <option value="Firefox">Firefox</option>
            <option value="Microsoft Edge">Microsoft Edge</option>

        </select>

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
        },
        hide() {
            if (this.area == "") {
                this.show = false;

            }
        },
    }
};
</script>

<style>
select {
    position: absolute;
    background-color: white;
    border: 3px solid blue;
    border-radius: 8px;    
    font-family: "NanumBarunGothicBold";
    font-size: 24px;
    width: 450px;
    height: 50px;
    padding: 5px;
    
    
}

option {
  position: absolute;
    background-color: white;
    border: 3px solid blue;
    border-radius: 8px;    
    font-family: "NanumBarunGothicBold";
    font-size: 24px;
    width: 450px;
    height: 50px;
    padding: 5px;
}

</style>
