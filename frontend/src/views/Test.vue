<template>
  <div>
    main

    <ul>
      <li @click="clickList">
        Query 프로그래밍 방식
      </li>
      <router-link :to="{name: 'Query', query: {name: 'cat', age: 3}}">
        Query 선언적 방식
      </router-link>
      <li @click="clickParams">
        params
      </li>
      <router-link :to="{name: 'Departure', params: {name: 'dog', age:4}}">
        params 선언적 방식
      </router-link>
    </ul>
  </div>
</template>

<script>
    export default {
        name: 'HelloWorld',
        methods: {
            clickList () {
                this.$router.push({name: 'Query', query: {name: 'cat', age: 3}})
            },
            clickParams () {
                this.$router.push({name: 'Departure', params: {name: 'dog', age:4}})
            }
        }
    }
</script>