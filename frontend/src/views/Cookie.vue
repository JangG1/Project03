<template>
<div>
    <img src="@/assets/Logo.png" v-if="view"><br>
    <a @click="setTest">set cookie</a><br>
    <a @click="getTest">get cookie</a><br>
    <a @click="resetTest">reset cookie</a><br>

    <a @click="test">test</a><br>
</div>
</template>

<script>
import VueCookies from 'vue-cookies'

export default {
    name: 'CookieTest',
    data() {
        return {
            view: true
        }
    },
    methods: {
        test(){
            var Now = new Date(); // 현재 날짜 및 시간

            var nowDay = Now.getDate(); // 일

            alert(nowDay)
            alert(nowDay + 1)
        },
        setTest: () => { //cookie 설정
            var Now = new Date(); // 현재 날짜 및 시간
            var nowDay = Now.getDate(); // 일

            VueCookies.set('test', nowDay)
            console.log(VueCookies.set('test', nowDay))
        },
        getTest: () => { //mounted
            if (VueCookies.isKey('test')) {
                alert(VueCookies.get('test'))
            } else {
                alert('값 없음')
            }
        },
        resetTest: () => {
            var Now = new Date(); // 현재 날짜 및 시간

            var nowDay = Now.getDate(); // 일
            var setDay = parseInt(VueCookies.get('test')) + 1;

            console.log("설정날짜 " + VueCookies.get('test'))
            console.log("다음날 " + setDay)
            console.log("오늘 " + nowDay)

            if(setDay == nowDay){
            VueCookies.remove('test')
            console.log('삭제됨')
            }
        },
    },
    mounted() {
        this.resetTest();
    },
}
</script>
