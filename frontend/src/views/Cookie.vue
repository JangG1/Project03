<template>
<div>
    <img src="@/assets/Logo.png" v-if="imgView"><br>
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
            imgView: true
        }
    },
    methods: {
        test() {
            var Now = new Date(); // 현재 날짜 및 시간
            var nowDay = Now.getDate(); // 일

            VueCookies.set('view', this.imgView = false, nowDay);
            var getViewCookie = VueCookies.get('view');

            console.log(getViewCookie)
        },
        setTest() { //cookie 설정
            var Now = new Date(); // 현재 날짜 및 시간
            var nowDay = Now.getDate(); // 일
            var setViewCookie = VueCookies.set('view', nowDay);
            var getViewCookie = VueCookies.get('view');

            VueCookies.set(setViewCookie)

            console.log(getViewCookie.toString())

        },
        getTest() { //mounted
            if (VueCookies.isKey('view')) {
                alert(VueCookies.get('view'))
            } else {
                alert('값 없음')
            }
        },
        resetTest() {
            var Now = new Date(); // 현재 날짜 및 시간

            var nowDay = Now.getDate(); // 일
            var tomorrow = parseInt(VueCookies.get('view')) + 1;

            console.log("설정날짜 " + VueCookies.get('view'))
            console.log("다음날 " + tomorrow)
            console.log("오늘 " + nowDay)

            //오늘이 1일인 경우  
            if (nowDay == 1) {
                console.log("오늘은 1일")
            }

            //쿠키등록일과 오늘 비교
            if (tomorrow == nowDay) {
                VueCookies.remove('view')
                console.log('삭제됨')
            }
        },
    },
    mounted() {
        this.resetTest();
    },
}
</script>
