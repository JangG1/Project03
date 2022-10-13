<template>
<div class="test">
    <h1>Reservation</h1>
</div>
<h1>부모</h1>

<Calendar></Calendar>

<Modal :name="name" @update-name="updateName"></Modal>
{{name}}


<PopUp :count="count" @update-count="updateCount"></PopUp>
{{count}}
</template>

<script>
import Calendar from './Calendar.vue';
import Modal from './Modal.vue';
import PopUp from './PopUp.vue';

export default {
    name: 'HelloWorld',
    components: {
        Calendar,
        Modal,
        PopUp

    },
    props: [""],
    data() {
        return {
            name: "",
            AdultCount: 0,
            count: 0
        }
    },
    methods: {
        updateName(name){
            this.name = name;
        },
        updateCount(count){
            this.count = count;
        },
        
    }
}
</script>

<style scoped>
body {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #eee;
}

.test {
    background-color: white;
    border-radius: 10px;

    text-align: right;
    line-height: var(--button-height);
    font-size: 48px;
    font-family: Helvetica;
    padding: 0 20px;
    color: #666;
}

h3 {
    margin: 40px 0 0;
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    display: inline-block;
    margin: 0 10px;
}

a {
    color: #42b983;
}
</style>
