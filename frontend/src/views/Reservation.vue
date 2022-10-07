<template>
  <div class="hello">
    <h1>Reservation</h1>
  
    <div>
      성인 {{ personCount }} <br>
      <button type="button" @click="plus()">+</button>
      <button type="button" @click="minus()">-</button>
    </div>

  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
    data() {
        return {
          personCount: 0,
        }
    },
  methods:{
    plus(){
      this.personCount++;
    },
    minus(){
      if(this.personCount != 0)
      this.personCount--;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
