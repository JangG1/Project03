<template>

    <div>
        <!--승객수 팝업-->
    
        <div>
            <h1>자식2</h1>
            <v-text-field
                label="Regular"
                
            ></v-text-field>
            
            <span>승객 수 : {{ cicityOfChildty }} </span><br>
            
            <span>번호 : {{ phone }} </span><br>
            
        </div>
        <v-btn @click="changeUser">수정</v-btn>
    </div>
    </template>
    
    <script>
    
    import '@vuepic/vue-datepicker/dist/main.css'
    
    
    export default {
        name: "HelloWorld",
        props: ['cicityOfChildty','name','phone','hasDog'],
        computed:{
            hasDogKr(){
                return this.hasDog === true ? '있음' : '없음'
            }
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
    