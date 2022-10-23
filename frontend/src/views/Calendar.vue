<template>
<div>
    <button type="button" @click="arrowFunction">Test</button>

</div>
</template>

<script>
export default {
    name: "HelloWorld",
    data() {
        return {
            number: '',
        }
    },
    methods: {
        arrowFunction() {
            setTimeout(() => {
                alert('arrow timeout')                
            }, 1000)
        }

    },

}
</script>

<style>

</style>
