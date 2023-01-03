<template>
    <div>
      <a @click="getTest">get cookie</a><br>
      <a @click="setTest">set cookie</a><br>
      <a @click="resetTest">reset cookie</a><br>
      <a @click="getAll">getAll cookie</a><br>
    </div>
  </template>
  
  <script>
  import VueCookies from 'vue-cookies'
  
  export default {
    name: 'CookieTest',
    methods: {
      getTest: () => {
        if (VueCookies.isKey('test')) { 
            alert(VueCookies.get('test')) 
        } else {
          alert('값 없음')
        }
      },
      setTest: () => {
        VueCookies.set('test', new Date())
        console.log(VueCookies.set('test', new Date()))
      },
      resetTest: () => {
        VueCookies.remove('test')
        alert('삭제됨')
      },
      getAll(){
        VueCookies.keys().join("\n");
        console.log(VueCookies.keys().join("\n"))
      }
    }
  }
  </script>