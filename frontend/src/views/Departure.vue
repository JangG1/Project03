<template>
<div>
    <h1>가는 편 {{ fromArea }} -> {{ toArea }} </h1>
    <div class="day-select">
        <button type="button">
            {{bothWay.toString().substring(11,12) - 2}}                        
        </button>
        <button type="button">
            {{bothWay.toString().substring(11,12) - 1}}
        </button>
        <button type="button">
            {{bothWay.toString().substring(11,12)}}
        </button>
        <button type="button">
            {{bothWay.toString().substring(11,12)}}
        </button>
        <button type="button">
            {{bothWay.toString().substring(11,12) + 2}}
        </button>
    </div>
    <br>
    <div class="day-seat-select" v-for="res in st" :key="res">

            <button type="button" class="timeSelect" style="cursor: default">
                <h3>{{res.start}} {{ fromArea }}
                    ->
                    {{res.arrive}} {{ toArea }}</h3>
            </button>


        <button type="button" class="seatSelect">
            <h3>{{seat}} 스탠다드</h3>
            <h3>{{priceFormat(price)}}</h3>
            <h4>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</h4>
        </button>
        <button type="button" class="seatSelect">
            <h3>{{seat}} 플랙스</h3>
            <h3>{{priceFormat(price)}}</h3>
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
import startTime from '../components/startTime.json';

const st = startTime;

export default {
    name: 'HelloWorld',
    components: {

    },
    data() {
        return {
            st,
            start: '',
            arrive: '',
            price: '',
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
        priceFormat() {
            return this.price = Math.floor(Math.random(6000, 1000) * 1000) + ",000원";
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

.day-select button:hover{
    border: 1.5px solid blue;
}

.day-seat-select {
    width: 1500px;
    margin-left: auto;
    margin-right: auto;

    display: flex;
    padding: 40px;
}

.day-seat-select button {
    width: 40%;
    height: 150px;
    margin-left: auto;
    margin-right: auto;
    color: #999;    
    background-color: white;
}

.timeSelect{
    border: 1px solid;
}

.seatSelect{
    border: 1px solid;
    border-top: 4px solid black;
    
}

.seatSelect:hover{
    border: 1.5px solid blue;
    border-top: 4px solid black;
}
</style>
