<template>
<div class="date-area-select">
    TEST

    <!-- Test Zone -->
    <Datepicker id="123" class="datePicker" type="" v-model="bothWay" placeholder="가는날 ~ 오는날" modelAuto range />
    <Datepicker class="datePicker" type="" v-model="oneWay" placeholder="가는날" />
    {{Format1(bothWay)}}
    <br>

    {{Format2(oneWay)}}

    <button type="button" @click="test">123</button>
    <!-- Test Zone -->

</div>
</template>

<script>
import Datepicker from '@vuepic/vue-datepicker';

export default ({
    name: "HelloWorld",
    data() {
        return {
            bothWay: [],
            oneWay: [],
            strArray: [],
            week: [],
        }
    },
    components: {
        Datepicker
    },
    methods: {
        test(){
         alert(this.oneWay)   
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
