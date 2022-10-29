<template>
<div class="date-area-select">
    TEST

    <!-- Test Zone -->
    <Datepicker class="datePicker" type="" v-model="bothWay" placeholder="가는날" />
    {{Format(bothWay)}}
    <input type="button" @click="test($event)" v-model="bothWay">
    {{array}}
    test : {{test}}123
    <!-- Test Zone -->

</div>
</template>

<script>
import Datepicker from '@vuepic/vue-datepicker';
import { reactive } from 'vue';

export default ({
    name: "HelloWorld",
    data() {
        return {
            bothWay: [],
        }
    },
    components: {
        Datepicker
    },
    methods: {
      Format(value) {
            /*var regexp = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/g;
            return num.toString().replace(regexp, ",");*/
            /*return num.toString().replace(/(\d{4}(\d{2})(\d))/, '$1-$2-$3')*/
            var regexp = /(\d{3}(\d{3})(\d))$/g;
            /*return value.toString().substr(0, 16).replace("Oct", '10월');*/
            return value.toString().substr(0, 16).replace(regexp, '');
        },
    },
    setup() {

        const array = reactive(['프뚜1', '프뚜2', '프뚜3']);

        const test = reactive([]);

        array.splice(0, 2);

        return {
          test
        };
    },

})
</script>

<style>

</style>
