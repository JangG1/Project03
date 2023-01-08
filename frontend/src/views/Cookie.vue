<template>
<div>
    <img src="@/assets/Logo.png" v-if="imgView"><br>
    <a @click="setTest">set cookie</a><br>
    <a @click="getTest">get cookie</a><br>
    <a @click="resetTest">reset cookie</a><br>

    <a @click="test">test</a><br>
    <a @click="reset">reset</a><br>
    =============================================
    <div class="browser-bottom_left">
        <input id="Chkbox" type="checkbox" class="today-btn">
        <label for="Chkbox" @click="checkModalToday">오늘 하루 그만보기</label>
    </div>
    <div class="browser-bottom_right">
        <label @click="closeModal">닫기</label>
    </div>
</div>
</template>

<script>
import VueCookies from 'vue-cookies'

export default {
    name: 'CookieTest',
    data() {
        return {
            imgView: true,
            now: "00:00:00"
        }
    },
    methods: {
        checkModalToday() {
            this.setCookie('bPop', 'Y', 1);
            this.closeModal();
        },

        setCookie(key, value, expireDays) {
            VueCookies.set(key, value, {
                expires: expireDays,
                path: ''
            });
        },
        setNoticeView() { //cookie 설정
            const checkbox = document.getElementById('Chkbox');

            const is_checked = checkbox.checked;

            VueCookies.set('view', is_checked, "1d")

            alert(is_checked)

        },
        getNoticeView() {            
            if (VueCookies.get('view') == "true") {
                this.imgView = false
            } else if(VueCookies.get('view') == "false"){
                this.imgView = true
            }
        },
        resetTest() {
            var moment = require('moment'); // moment 모듈불러오기

            var now = moment(); //오늘

            console.log("설정날짜 " + VueCookies.get('view'))
            console.log("오늘 " + now)

            //오늘이 1일인 경우  
            // if (VueCookies.get('view') == 1) {
            //     console.log("오늘은 1일")
            // }

            //쿠키등록일과 오늘 비교
            if (now == VueCookies.get('view')) {
                VueCookies.remove('view')
                console.log('삭제됨')
            }
        },
        reset() {
            VueCookies.remove('view')
            console.log('삭제됨')
        }
    },
    mounted() {
        this.getTest();

    },

}
</script>
