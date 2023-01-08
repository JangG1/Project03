<template>
<div class="table-responsive">
    이메일
    <input type="text" class="inputValues" id="id" v-model="email" /><br>
    출발
    <input type="text" class="inputValues" id="id" v-model="fromArea" /><br>
    도착
    <input type="text" class="inputValues" id="id" v-model="toArea" /><br>
    왕복/편도
    <select v-model="way" id="">
        <option value="왕복" selected>왕복</option>
        <option value="편도">편도</option>
    </select><br>
    좌석
    <select v-model="seat" id="inputState">
        <option value="일반석">일반석</option>
        <option value="이코노미">이코노미</option>
        <option value="비즈니스">비즈니스</option>
    </select><br>

    <!--여행 날짜 선택-->
    <Datepicker class="datePicker" @update:model-value="datepickerShow1" v-model="bothWay" placeholder="                              가는날 ~ 오는날" modelAuto range />

    <!--왕복 날짜 선택-->
    <div type="button" class="selectDate1" @click="resetDate1" v-show="selectDate1">
        <div class="selectDate2">
            <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi">
                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
            </svg>
            {{bothWay}}
        </div>
    </div>

    <!--<input v-show="selectDate" type="text" v-model="bothWay">-->

    <!--편도 날짜 선택-->
    <Datepicker v-if="datePickerShow2" class="datePicker" @update:model-value="datepickerShow2" v-model="oneWay" placeholder="                                  탑승일 선택" />

    <div type="button" class="selectDate1" @click="resetDate2" v-show="selectDate2">
        <div class="selectDate2">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi">
                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
            </svg>
            {{oneWay}}
        </div>
    </div>

    <button type="button" @click="testSend()">보내기</button><br>

    <button type="button" @click="test()">test</button>

    <label>예약 조회</label>
    <table class="table table-striped table-sm">
        <thead>
            <tr>
                <th scope="col">예약 번호</th>
                <th scope="col">예약 날짜</th>
                <th scope="col">가는 편</th>
                <th scope="col">오는 편</th>
                <th scope="col">왕복/편도</th>
                <th scope="col">좌석</th>
                <th scope="col">출발지</th>
                <th scope="col">도착지</th>
            </tr>
        </thead>

        <tbody>
            <tr v-for="res in ress" :key="res">
                <td>{{"Fastrip-" + res.res_no}}</td>
                <td>{{res.res_date.substr(0, 16).replace("T"," ")}}</td>
                <td>{{res.departDate}}</td>
                <td>{{res.returnDate}}</td>
                <td>{{res.way}}</td>
                <td>{{res.seat}}</td>
                <td>{{res.fromArea}}</td>
                <td>{{res.toArea}}</td>
            </tr>
        </tbody>
    </table>

</div>
</template>

<script>
import axios from 'axios'
import Datepicker from '@vuepic/vue-datepicker';

export default {
    name: 'HelloWorld',
    components: {
        Datepicker
    },
    props: [""],
    data() {
        return {
            ress: [],
            bothWay: [],
            oneWay: [],
            selectDate1: false,
            selectDate2: false
        }
    },
    methods: {
        test(){
            console.log(this.$store.state.userInfo.email)
            console.log(this.seat)
            console.log(this.fromImgName)
            console.log(this.toImgName)
            console.log(this.Date)
            console.log(this.way)
            console.log(this.AdultCount)
            console.log(this.ChildCount)
            console.log(this.InfantCount)
        },
        test2() {
            let ttt = document.getElementById('testValue').value

            if (this.selectDate1 == true) {
                this.Date = this.bothWay;
                this.way = "왕복"
            } else if (this.selectDate2 == true) {
                this.Date = this.oneWay;
                this.way = "편도"
            } else {
                console.log("여행 일정을 선택해주세요")
                return false;
            }

            console.log(this.$store.state.userInfo.email)
            console.log(ttt)
            console.log(this.way)
        },
        datepickerShow1() {
            this.datePickerShow1 = false;
            this.selectDate1 = true;
        },
        getData() {
            axios.get('/res/all')
                .then((response) => {
                    this.ress = response.data
                })
        },
        testSend() {

            axios
                .post("/res/test", {
                    email: this.$store.state.userInfo.email,
                    seat: this.seat,
                    way: this.way,
                    fromArea: this.fromArea,
                    toArea: this.toArea,
                    departDate: this.departDate,
                    returnDate: this.returnDate,
                })
                .then(res => {
                    console.log(res)
                    console.log("보내짐")
                })
                .catch(err => {
                    console.log(err)
                    console.log("안보내짐")
                })
        },
        resSend() {
            let seat = document.getElementById('inputState').options[document.getElementById("inputState").selectedIndex].value;

            if (this.selectDate1 == true) {
                this.Date = this.bothWay;
                this.way = "왕복"
            } else if (this.selectDate2 == true) {
                this.Date = this.oneWay;
                this.way = "편도"
            } else {
                console.log("여행 일정을 선택해주세요")
                return false;
            }

            if (this.ChildCount == 0) {
                this.ChildCount = null;
            }

            if (this.InfantCount == 0) {
                this.InfantCount = null;
            }

            console.log(this.$store.state.userInfo.email)
            console.log(seat)
            console.log(this.fromImgName)
            console.log(this.toImgName)
            console.log(this.Date)
            console.log(this.way)
            console.log(this.AdultCount)
            console.log(this.ChildCount)
            console.log(this.InfantCount)

            axios.post("/res/test", {
                    email: this.$store.state.userInfo.email,
                    seat: seat,
                    Date: this.Date,
                    way: this.way,
                    fromArea: this.fromImgName,
                    toArea: this.toImgName,
                    AdultCount: this.AdultCount,
                    ChildCount: this.ChildCount,
                    InfantCount: this.InfantCount,
                })
                .then(res => {
                    console.log(res)
                    console.log("보내짐")
                })
                .catch(err => {
                    console.log(err)
                    console.log("안보내짐")
                })
        },
    },
    mounted() {
        this.getData()

    }
}
</script>

<style scoped>
label {
    float: left;
    margin-left: 20px;
    margin-bottom: 10px;
    font-size: 30px;
}

tr {

    font-size: 20px;
}

td {
    padding: 10px;
    font-size: 17px;
}
</style>
