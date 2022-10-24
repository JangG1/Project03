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
    <select v-model="seat" id="">
        <option value="일반석" selected>일반석</option>
        <option value="이코노미">이코노미</option>
        <option value="비즈니스">비즈니스</option>
    </select><br>
    가는 편
    <input type="text" class="inputValues" id="id" v-model="departDate" /><br>
    오는 편
    <input type="text" class="inputValues" id="id" v-model="returnDate" /><br>

    <button type="button" @click="testSend()">보내기</button><br>

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

export default {
    name: 'HelloWorld',
    components: {},
    props: [""],
    data() {
        return {
            ress: [],
        }
    },
    methods: {
        getData() {
            axios.get('/res/all')
                .then((response) => {
                    this.ress = response.data
                })
        },
        testSend() {
            axios
                .post("/res/test", {
                    email: this.email,
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
    },
    mounted() {
        this.getData()

    }
}
</script>

<style scoped>
label{
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
