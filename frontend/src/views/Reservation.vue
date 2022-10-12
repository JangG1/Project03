<template>
<div class="test">
    <form>

        <h1>Reservation</h1>

        <button type="button" @click="decrement">-</button>
        <button type="button" @click="increment">+</button>
        <hr>


    </form>
</div>
<h1>부모</h1>
<div>{{city}}</div>
  <button type="button" @click="changeName">교체</button>
  <Calendar :cicityOfChildty="city"></Calendar>
  <Calendar 
  :name="name"
  :phone="phone"
  >
  </Calendar>


</template>

<script>
import Calendar from './Calendar.vue';


export default {
    name: 'HelloWorld',
    components: {
        Calendar,
      
    },
    props:[""],
    data() {
        return {
            city: "Seoul",
            name: "Jiwon",
            phone: "1234-5678",            
        }
    },
    methods: {
        plus() {
            this.personCount++;
        },
        minus() {
            if (this.personCount != 0)
                this.personCount--;
        },
        change() {
            let temp = this.input;
            this.input = this.output;
            this.output = temp;
        },
        increment() {
            this.$emit("change", this.count + 1);
        },
        decrement() {
            this.$emit("change", this.count - 1);
        },
        changeName(){
            this.city = "Dio"
        },
        parents(){
            console.log("잘받았어")
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

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
