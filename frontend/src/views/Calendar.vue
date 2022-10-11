<template>
<Datepicker v-model="date" modelAuto range />
<div>
    <!--승객수 팝업-->

    <div>
        <span>승객 수 : {{ count }}</span>
        <Reservation :count="count" @change="changeCount" />
    </div>
</div>
</template>

<script>
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import Reservation from '@/views/Reservation.vue';

export default {
    name: "HelloWorld",
    props: ['AdultCount'],
    components: {
        Datepicker,
        Reservation
    },
    data() {
        return {
            date: null,
            count: 0,
        };
    },
    methods: {
        changeCount(value) {
            this.count = value;
        }
    }
}
</script>
