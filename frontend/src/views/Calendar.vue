<template>

<div>
    <!--승객수 팝업-->

    <div>
        <h1>자식1</h1>
       

    </div>
    
</div>
</template>

<script>

import '@vuepic/vue-datepicker/dist/main.css'


export default {
    name: "HelloWorld",
    props: ['',],
    computed:{
    },
    components: {
        
        
    },
    data() {
        return {
            date: null,
            count: 0,
            abc: false
        };
    },
    methods: {
        changeUser(){
            this.$emit('child')
        }
    }
}
</script>
