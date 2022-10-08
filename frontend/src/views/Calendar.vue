<template>
    <Datepicker v-model="date" modelAuto range />
</template>

<script>

import Datepicker from '@vuepic/vue-datepicker';
    import '@vuepic/vue-datepicker/dist/main.css'
export default {
    name:"HelloWorld",
    components: { Datepicker },
        data() {
            return {
                date: null,
            };
        },
}
</script>