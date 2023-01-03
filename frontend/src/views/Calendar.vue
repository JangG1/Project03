<template>
<div class="date-area-select">
    TEST
    <button type="button" @click="test">Test</button>
    <!-- Test Zone -->
    <Datepicker id="123" class="datePicker" type="date" v-model="bothWay" format="yyyy-MM-dd" placeholder="가는날 ~ 오는날" :dayNames="lang.days" fixed-start range>
        <template #month="{  value  }">
            {{ value + 1 + "월"}}
        </template>
        <template #month-overlay="{ value }">
            {{ value + 1 + "월"}}
        </template>
    </Datepicker>
    <Datepicker class="datePicker" type="" v-model="oneWay" placeholder="가는날" />
    {{Format1(bothWay)}}<br>
    {{bothWay}}<br>
    {{JSON.stringify(this.bothWay)}}

    <input type="button" v-model="bothWay">
    <br>

    {{Format2(oneWay)}}

    <button type="button" @click="test">123</button>
    <br>
    <!-- Test Zone -->
    <input type="date" class="datePicker" v-model="date" placeholder="가는날">
    {{JSON.stringify(date)}}<br>
    {{date}}
    <Datepicker v-model="date" :dayNames="['1', '2', '3', '4', '5', '6', '7']" />
</div>
</template>

<script>
import Datepicker from '@vuepic/vue-datepicker';
import {
    ref
} from 'vue';

export default ({
    name: "HelloWorld",
    data() {
        return {
            bothWay: "",
            oneWay: [],
            strArray: [],
            week: [],
            date: [],
            text: 1,
            lang: {
                days: ['월', '화', '수', '목', '금', '토', '일'],
                months: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월', ]
            }
        }
    },
    components: {
        Datepicker
    },
    setup() {

        const ariaLabels = ref({
            menu: 'Some custom menu label'
        })

        return {

            ariaLabels,
        }
    },
    methods: {
        test1() {
            setTimeout(function () {
                alert("Test중")
            }, 3000);
        },
        test2() {
            /*let today = new Date();

            document.write(today.toLocaleDateString() + '<br>');
            document.write(today.toLocaleTimeString() + '<br>');
            document.write(today.toLocaleString() + '<br><br>');

            document.write(today.toLocaleDateString('en-US'))*/

            let today = new Date();

            let year = today.getFullYear(); // 년도
            let month = today.getMonth() + 1; // 월
            let date = today.getDate(); // 날짜
            let day = today.getDay(); // 요일 
            let hours = today.getHours(); // 시
            let minutes = today.getMinutes(); // 분
            let seconds = today.getSeconds(); // 초
            let milliseconds = today.getMilliseconds(); // 밀리초

            return alert(year + "/" + month + "/" + date + "/" + day + "/" + hours + ":" + minutes + ":" + seconds + ":" + milliseconds)
        },
        Format1(value) {

            var string = value.toString();
            var strArray = string.substring(0, 58);
            var from = strArray.substring(0, 16);
            var to = strArray.substring(43, 58);

            var year1 = from.substring(11, 15);
            var day1 = from.substring(8, 10);
            var week1 = from.substring(0, 3);
            var month1 = from.substring(4, 7);

            var year2 = to.substring(11, 15);
            var day2 = to.substring(8, 10);
            var week2 = to.substring(0, 3);
            var month2 = to.substring(4, 7);

            if (week1 == "Mon") {
                week1 = "(월)";
            } else if (week1 == "Tue") {
                week1 = "(화)"
            } else if (week1 == "Wed") {
                week1 = "(수)"
            } else if (week1 == "Thu") {
                week1 = "(목)"
            } else if (week1 == "Fri") {
                week1 = "(금)"
            } else if (week1 == "Sat") {
                week1 = "(토)"
            } else if (week1 == "Sun") {
                week1 = "(일)"
            }

            if (month1 == "Jan") {
                month1 = "1";
            } else if (month1 == "Feb") {
                month1 = "2"
            } else if (month1 == "Mar") {
                month1 = "3"
            } else if (month1 == "Apr") {
                month1 = "4"
            } else if (month1 == "May") {
                month1 = "5"
            } else if (month1 == "Jun") {
                month1 = "6"
            } else if (month1 == "Jul") {
                month1 = "7"
            } else if (month1 == "Aug") {
                month1 = "8"
            } else if (month1 == "Sep") {
                month1 = "9"
            } else if (month1 == "Oct") {
                month1 = "10"
            } else if (month1 == "Nov") {
                month1 = "11"
            } else if (month1 == "Dec") {
                month1 = "12"
            }

            if (week2 == "Mon") {
                week2 = "(월)";
            } else if (week2 == "Tue") {
                week2 = "(화)"
            } else if (week2 == "Wed") {
                week2 = "(수)"
            } else if (week2 == "Thu") {
                week2 = "(목)"
            } else if (week2 == "Fri") {
                week2 = "(금)"
            } else if (week2 == "Sat") {
                week2 = "(토)"
            } else if (week2 == "Sun") {
                week2 = "(일)"
            }

            if (month2 == "Jan") {
                month2 = "1";
            } else if (month2 == "Feb") {
                month2 = "2"
            } else if (month2 == "Mar") {
                month2 = "3"
            } else if (month2 == "Apr") {
                month2 = "4"
            } else if (month2 == "May") {
                month2 = "5"
            } else if (month2 == "Jun") {
                month2 = "6"
            } else if (month2 == "Jul") {
                month2 = "7"
            } else if (month2 == "Aug") {
                month2 = "8"
            } else if (month2 == "Sep") {
                month2 = "9"
            } else if (month2 == "Oct") {
                month2 = "10"
            } else if (month2 == "Nov") {
                month2 = "11"
            } else if (month2 == "Dec") {
                month2 = "12"
            }

            return year1 + "-" + month1 + "-" + day1 + week1 + " ~ " + year2 + "-" + month2 + "-" + day2 + week2;
        },

        Format2(value) {
            var string = value.toString();
            var total = string.substring(0, 16);

            var year = total.substring(11, 15);
            var day = total.substring(8, 10);
            var week = total.substring(0, 3);
            var month = total.substring(4, 7);

            if (week == "Mon") {
                week = "(월)";
            } else if (week == "Tue") {
                week = "(화)"
            } else if (week == "Wed") {
                week = "(수)"
            } else if (week == "Thu") {
                week = "(목)"
            } else if (week == "Fri") {
                week = "(금)"
            } else if (week == "Sat") {
                week = "(토)"
            } else if (week == "Sun") {
                week = "(일)"
            }

            if (month == "Jan") {
                month = "1";
            } else if (month == "Feb") {
                month = "2"
            } else if (month == "Mar") {
                month = "3"
            } else if (month == "Apr") {
                month = "4"
            } else if (month == "May") {
                month = "5"
            } else if (month == "Jun") {
                month = "6"
            } else if (month == "Jul") {
                month = "7"
            } else if (month == "Aug") {
                month = "8"
            } else if (month == "Sep") {
                month = "9"
            } else if (month == "Oct") {
                month = "10"
            } else if (month == "Nov") {
                month = "11"
            } else if (month == "Dec") {
                month = "12"
            }

            return year + "-" + month + "-" + day + week;
        },

    },

})
</script>

<style>

</style>
