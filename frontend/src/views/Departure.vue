<template>
<div>
    <div class="caption">
        <p>가는 편 {{ fromArea }} </p>

        <svg xmlns="http://www.w3.org/2000/svg" class="arrow1">
            <path id="arrowImg" fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z" />
        </svg>

        <p> {{ toArea }} </p>
    </div>
    <div class="day-select">
        <button type="button">
            {{startDate.toString().substring(10,12) - 2}}
        </button>
        <button type="button">
            {{startDate.toString().substring(10,12) - 1}}
        </button>
        <button type="button">
            {{startDate.toString().substring(10,12)}} ({{this.day}})
        </button>
        <button type="button">
            {{startDate.toString().substring(10,12)}}
        </button>
        <button type="button">
            {{startDate.toString().substring(10,12) + 2}}
        </button>
    </div>
    <br>
    <div class="day-seat-select" v-for="res in st" :key="res">

        <button type="button" class="timeSelect" style="cursor: default">
            <p>{{res.start}} {{ fromArea }}</p>
            <img type="image" class="arrow2" src="../assets/arrow.jpg">
            <p>{{res.arrive}} {{ toArea }}</p>
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
        {{startDate}}<br>
        {{returnDate}}<br>
        {{this.week1}}
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
        startDate: {
            type: String
        },
        returnDate: {
            type: String
        },
    },
    created() {
        var week1 = this.startDate.substring(0, 3);

        var week2 = this.returnDate.substring(0, 3);

        if (week1 == "Mon") {
            week1 = "(월1)";
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

    },
    computed: {
        newDay() {
            return this.week1;
        },

    },
    methods: {
        priceFormat() {
            return this.price = Math.floor(Math.random(6000, 1000) * 1000) + ",000원";
        },
    },
}
</script>

<style>
.caption {
    display: flex;
    margin-left: 11%;
}

.arrow1 {
    width: 30px;
    height: 50px;
    margin-top: 9px;
    margin-left: 14px;

}

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

.day-select button:hover {
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
    color: #999;
    background-color: white;
}

.timeSelect {
    border: 1px solid;
    display: flex;
}

.timeSelect p {

    display: flex;
    padding: 35px;
}

.arrow2 {
    width: 200px;
    height: 30px;
    margin-top: 10%;

}

.seatSelect {
    border: 1px solid;
    border-top: 4px solid black;

}

.seatSelect:hover {
    border: 1.5px solid blue;
    border-top: 4px solid black;
}
</style>
