<template>
<div>
    <h1>가는 편 {{ fromArea }} -> {{ toArea }} </h1>
    <div class="day-select">
        <button type="button">1</button>
        <button type="button">2</button>
        <button type="button">3</button>
        <button type="button">4</button>
        <button type="button">5</button>
    </div>
    <br>
    <div class="day-seat-select">
        <button type="button">
            <h3>{{Math.floor(Math.random()*(9 - 1) + 1)}}:{{Math.floor(Math.random()*(59 - 10) + 10)}} {{ fromArea }}
                ->
                {{Math.floor(Math.random()*(23 - 12) + 12)}}:{{Math.floor(Math.random()*(59 - 10) + 10)}} {{ toArea }}</h3>
        </button>
        <button type="button">
            <h3>{{seat}} 스탠다드</h3>
            <h3>{{Math.floor(Math.random()*(400 - 200) + 200)}},000 원</h3>
            <h4>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</h4>
        </button>
        <button type="button">
            <h3>{{seat}} 플랙스</h3>
            <h3>{{Math.floor(Math.random()*(700 - 350) + 350)}},000 원</h3>
            <h4>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</h4>
        </button>
    </div>
    <div>
        {{bothWay}}
    </div>
    <div>
        <input type="text">
    </div>
</div>
</template>

<script>
export default {
    name: 'HelloWorld',
    data() {
        return {
            price: "",
        }
    },
    props: {
        fromArea: {
            type: String,
            default: ''
        },
        toArea: {
            type: String,
            default: ''
        },
        seat: {
            type: String,
            default: ''
        },
        bothWay: {
            type: String,
            default: ''
        }
    },
    methods: {
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
    }
}
</script>

<style>
.day-select {
    width: 1500px;
    height: 100px;
    margin-left: auto;
    margin-right: auto;

    display: flex;
}

.day-select button {
    width: 400px;
    height: 100px;
    border: 0.5px solid #999;
    background-color: white;
}

.day-seat-select {
    width: 1500px;
    margin-left: auto;
    margin-right: auto;
    border: 0.5px solid #999;
    display: flex;
    padding: 40px;
}

.day-seat-select button {
    width: 400px;
    height: 150px;
    margin-left: auto;
    margin-right: auto;
    color: #999;
    border: 0.5px solid #999;
    background-color: white;
}
</style>
