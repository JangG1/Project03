<template>
    <div>
      <h1>Query</h1>
  
      <h2>name: {{ $route.query.name }}</h2>
      <h2>age: {{ $route.query.age }}</h2>
    </div>
  </template>
  
  <script>
      export default {
          name: 'HelloWorld',
      }
  </script>