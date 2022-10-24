<template>
<div class="">
    TEST
    <div>
        <div class="calculator">
            <div class="result" style="grid-area: result">

            </div>

            <button style="grid-area: number-1" @click="append(1)">1</button>
            <button style="grid-area: number-2" @click="append(2)">2</button>
            <button style="grid-area: number-3" @click="append(3)">3</button>
            <button style="grid-area: number-4" @click="append(4)">4</button>
            <button style="grid-area: number-5" @click="append(5)">5</button>
            <button style="grid-area: number-6" @click="append(6)">6</button>
            <button style="grid-area: number-7" @click="append(7)">7</button>
            <button style="grid-area: number-8" @click="append(8)">8</button>
            <button style="grid-area: number-9" @click="append(9)">9</button>
            <button style="grid-area: number-10" @click="append(10)">10</button>
            <button style="grid-area: number-11" @click="append(11)">11</button>
            <button style="grid-area: number-12" @click="append(12)">12</button>
            <button style="grid-area: number-13" @click="append(13)">13</button>

            <button style="grid-area: dot" @click="append('.')">.</button>
        </div>
    </div>
</div>
</template>

<script>
export default ({
    name: "HelloWorld",
    data() {
        return {
            equation: '0',
            isDecimalAdded: false,
            isOperatorAdded: false,
            isStarted: false,
        }
    },

    methods: {
        test(event) {
            let temp = event.target.value;
            console.log(this.bothWay)
            console.log(event.target.value)
            return temp.replace(/(\d{4}(\d{2})(\d))/, '$1-$2-$3')
        }

    },
    filters: {
        dateFormat(value) {
            if (value === null) {
                return 0
            }

            return value.replace(/(\d{4}(\d{2})(\d))/, '$1-$2-$3');
        },
    }

})
</script>

<style>

</style>
