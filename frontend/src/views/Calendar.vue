<template>

<div>
    <!--승객수 팝업-->

    <div>
        <h1>자식1</h1>
        <span>승객 수 : {{ cicityOfChildty }} </span><br>
        <span>이름 : {{ name }} </span><br>
        <span>번호 : {{ phone }} </span><br>
    </div>
    
</div>
</template>

<script>

import '@vuepic/vue-datepicker/dist/main.css'


export default {
    name: "HelloWorld",
    props: ['cicityOfChildty','name','phone'],
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
