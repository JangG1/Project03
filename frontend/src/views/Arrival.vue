<template>
    <div>
        <div class="caption">
            <p>여행 일정</p>
        </div>
        <div class="info">
            <button type="button">
                <div class="info1">
                    {{ fromArea }} &nbsp;
                    <img src="../assets/arrow2.jpg"> &nbsp;
                    {{toArea}}
                </div>
                <img src="../assets/vertical.jpg" width="12" class="ver">
                <div class="info2">
                    <img class="infoImg" src="../assets/calendar.png" width="30" height="30"> &nbsp;
                    {{Format(startDate)}} ~ {{Format(returnDate)}}
                </div>
                <img src="../assets/vertical.jpg" width="12" class="ver">
                <div class="info3">
                    <img class="infoImg" src="../assets/person.png" width="20" height="20"> &nbsp;
                    {{AdultCount}}
                    <span v-show="ChildCount.substr(4,2) > 0">
                        {{ChildCount}}
                    </span>
                    <span v-show="InfantCount.substr(4,2) > 0">
                        {{InfantCount}}
                    </span>
                </div>
            </button>
        </div>
        <br>
        <div class="day-seat-select" v-for="res in st" :key="res">
    
            <div type="button" class="timeSelect" style="cursor: default">
                <div class="startTime">
                    <h3>{{res.start}} </h3>
                    <span> {{ fromArea }}</span>
                </div>
                <img type="image" class="arrow2" src="../assets/arrow.jpg">
                <div class="arriveTime">
                    <h3>{{res.arrive}} </h3>
                    <span>{{ toArea }}</span>
                </div>
            </div>
    
            <button type="button" class="seatSelect" @click="selectSeat">
                <h3>{{seat}} 스탠다드
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="check">
                        <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                    </svg>
                </h3>
                <h4>{{this.price}}</h4>
                <h4>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</h4>
            </button>
            <button type="button" class="seatSelect" @click="selectSeat">
                <h3>{{seat}} 플랙스
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="check">
                        <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                    </svg>
                </h3>
                <h4>{{this.price}}</h4>
                <h4>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</h4>
            </button>
    
        </div>
    
        <div>
            {{Format(startDate)}}<br>
            {{returnDate}}<br>
            {{priceFormat(seatPrice)}}
            
        </div>
        <div class="footNav">
            <span>예상 결제 금액</span>
            <span class="startPrice">{{selectPrice}}</span>
            <button type="button" class="submitBtn" @click="submit()">다음 여정</button>
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
                selectPrice: '0 원',
                seatPrice: 0,
    
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
            AdultCount: {
                type: Number
            },
            ChildCount: {
                type: Number
            },
            InfantCount: {
                type: Number
            }
        },
        created() {
    
        },
        computed: {
    
        },
        methods: {
            priceFormat() {
                let seatPrice = this.price = Math.floor(Math.random(6000, 1000) * 1000) + ",000원";
                return seatPrice;
            },
            selectSeat() {
                console.log()
                console.log()
                this.selectPrice = this.price;
    
            },
            Format(value) {
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
    
            submit() {
                let seat = document.getElementById('inputState').options[document.getElementById("inputState").selectedIndex].value;
    
                var string = this.bothWay.toString();
                var startDate = string.substring(0, 16);
                var returnDate = string.substring(43, 58);
    
                if (seat == "좌석 등급") {
                    alert("좌석을 선택해주세요")
                    return false;
                }
                this.$router.push({
                    name: 'Arrival',
                    params: {
                        fromArea: this.fromImgName,
                        toArea: this.toImgName,
                        seat: seat,
                        startDate: startDate,
                        returnDate: returnDate
                    }
                });
    
            }
    
        },
        mounted() {
    
        }
    }
    </script>
    
    <style>
    .caption {
        display: flex;
        margin-left: 11%;
    }
    
    .ver{
        margin-top: 25px;
        margin-left: 14px;
    }
    
    .info {
        margin-left: auto;
        margin-right: auto;
        width: 80%;
        display: flex;
    }
    
    .info button {
        width: 100%;
        height: 100px;
        background-color: white;
        font-size: 24px;
        border: 0.5px solid #999;
        display: flex;
        box-shadow: 4px 4px 4px rgb(68, 68, 68);
     
    }
    
    .info button div {
        padding: 30px;    
    }
    
    .infoImg {
        margin-left: 14px;
        margin-bottom: 4px;
    }
    
    .info button:hover {
        border: 2px solid teal;
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
        background-color: white;
        box-shadow: 4px 4px 8px rgb(68, 68, 68);
    }
    
    .timeSelect {
        width: 40%;
        height: 150px;
        border: 1px solid;
    }
    
    .startTime {
        width: 100px;
        float: left;
        margin-top: 8%;
        margin-left: 8%;
        
    }
    
    .arriveTime {
        float: right;
        margin-top: 8%;    
        margin-right: 5%;
    }
    
    .timeSelect span {
        font-size: 20px;
        color: #999;
    }
    
    .arrow2 {
        width: 140px;
        height: 30px;
        margin-top: 10%;
    
    }
    
    .seatSelect {
        border: 1px solid;
        border-top: 4px solid black;
        
    }
    
    .seatSelect:hover {
        border: 2px solid teal;
        border-top: 4px solid black;
    }
    
    .seatSelect:focus {
        width: 600px;
        height: 150px;
        transform: translate(-0.5%, -10%);    
        border-radius: 15px;
        background-color: teal;
        box-shadow: 4px 4px 10px rgb(68, 68, 68);
        color: white;
    
    }
    
    .check {
        float: right;
        color: white;
    }
    
    .footNav {
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
    
        height: 75px;
        padding: 1rem;
        color: white;
        background: teal;
        font-weight: bold;
        display: flex;
        justify-content: space-between;
        align-items: center;
    
    }
    
    .submitBtn {
        width: 15%;
        height: 130%;
        font-size: 25px;
        border-radius: 4px;
        color: white;
        background: teal;
        border: 1px solid white;
    }
    
    .startPrice {
        font-size: 20px;
        margin-left: 70%;
    }
    </style>
    