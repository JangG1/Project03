<template>
<div>
    <div id="app">
  <h1>{{ count }}</h1><br/>
  <p>{{ text }}</p>
  <button @click="count++">카운트 증가</button>
</div>

</div>
</template>

<script>
export default {
    name: "HelloWorld",
    data() {
        return {
            count: 0,
    text: '변경 전'
        }
    },
    methods: {
        arrowFunction() {
            setTimeout(() => {
                alert('arrow timeout')                
            }, 1000)
        }

    },
    watch: {
    count: function (n, o) {
      this.text = o + '에서 ' + n + '로 변경!'
    }
  }

}
</script>

<style>

</style>
