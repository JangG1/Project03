<template>
<div class="test">
    <form>

        <h1>Reservation</h1>

        <button type="button" @click="decrement">-</button>
        <button type="button" @click="increment">+</button>
        <hr>


    </form>
</div>
</template>

<script>


export default {
    name: 'HelloWorld',
    components: {

    },
    props:["count"],
    data() {
        return {

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
