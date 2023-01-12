<template>
<div class="res">
    <div class="resTitle">예약 조회</div>
    <div class="table-responsive">
        <span v-if="passengerView" class="passengerView">
            <PassengerModal @close="passengerPopUp" :user="user"></PassengerModal>
        </span>

        <table class="table table-striped table-sm">
            <thead>
                <tr>
                    <th scope="col">예약 번호</th>
                    <th scope="col">예약자 정보</th>
                    <!--
                    //1일전 날짜 오류로 수정필요
                    //DB 이상없음
                    <th scope="col">예약 날짜</th>
                    -->
                    <th scope="col">가는 편</th>
                    <th scope="col">오는 편</th>
                    <th scope="col">왕복/편도</th>
                    <th scope="col">좌석</th>
                    <th scope="col">출발지 → 도착지 (항공편)</th>
                    <th scope="col">등급</th>
                    <th scope="col">도착지 → 출발지 (항공편)</th>
                    <th scope="col">등급</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="res in res" :key="res">
                    <td>{{"Fastrip - " + res.res_no}}</td>
                    <td><input type="button" class="passBtn" value="예약자 정보" @click="passengerPopUp(res.res_no)"></td>
                    <!-- 
                    //1일전 날짜 오류로 수정필요
                    //DB 이상없음
                    <td>{{res.res_date.substr(0, 16).replace("T"," ")}}</td>
                     -->
                    <td>{{res.startDate + " " + res.startTime1 + " ~ " + res.arriveTime1}}</td>
                    <td>{{res.returnDate + " " + res.startTime2}}
                        <span v-show="res.returnDate != returnYearValue">~</span>
                        <span v-show="res.returnDate == returnYearValue">-</span>
                        {{res.arriveTime2}}</td>
                    <td>{{res.way}}</td>
                    <td>{{res.seat}}</td>
                    <td>{{res.fromArea + " → " + res.toArea + "(" + res.flight1 + ")"}}</td>
                    <td>{{res.seatClass1}}</td>
                    <td v-if="res.returnDate != returnYearValue">{{res.toArea + " → " + res.fromArea + "(" + res.flight2 + ")"}}</td>
                    <td v-if="res.returnDate == returnYearValue">{{res.oneWayArea}}
                        <span v-show="res.returnDate == returnYearValue">-</span>
                    </td>
                    <td>{{res.seatClass2}}<span v-show="res.returnDate == returnYearValue">-</span></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- FOOTER -->
    <div class="Footer">
        <div class="FootLeft">
            <img class="footLogo" src="../assets/Logo2.png">
            <span> Fastrip</span>
            <p>&copy; 2022 Company, Fastrip</p>
        </div>

        <div class="FootRight">
            <a class="topBtn" href="#top">✈ Top</a>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'
import PassengerModal from "@/components/PassengerModal";

export default {
    name: 'HelloWorld',
    components: {
        PassengerModal
    },
    props: [""],
    data() {
        return {
            res: [],
            passengerView: false,
            returnYearValue: " ",
            user: {
                res_no: this.res_no,
                AdultCount: this.AdultCount,
                ChildCount: this.ChildCount,
                InfantCount: this.InfantCount,
                korLastName: this.$store.state.name.substring(0, 1),
                korFirstName: this.$store.state.name.substring(1, 3),
                engLastName: this.engLastName,
                engFirstName: this.engFirstName,
                gender: this.$store.state.gender,
                birthday: this.$store.state.birthday.toString().replace(/\B(?=(\d{2})+(?!\d))/g, "."),
            }
        }
    },
    methods: {
        getData() { //예약내역 전부 조회
            axios.get('/res/all')
                .then((response) => {
                    this.res = response.data
                })
        },
        passengerPopUp(value) { //예약자 정보 팝업
            this.passengerView = (this.passengerView) ? false : true
            this.$store.dispatch("res_no", value - 1);
        },
        Gender(value) { //성별 한글패치
            let gender = value
            if (gender == "male") {
                gender = "남자"
            } else if (gender == "female") {
                gender = "여자"
            }
            return gender
        },

    },
    mounted() {
        this.getData()

    }
}
</script>

<style scoped>
.res {
    padding-top: 100px;
}

.table-responsive {
    margin-left: 10%;
    margin-right: 10%;
    margin-bottom: 10%;
    height: 500px;
    /* overflow: hidden; */
    background-color: white;
    border-radius: 6px;

    border: 2px solid teal;
}

.table-responsive::-webkit-scrollbar {
    width: 15px;

}

.table-responsive::-webkit-scrollbar-thumb {
    background-color: teal;
    border-radius: 10px;
    /*스크롤바의 색상*/
}

.table-responsive::-webkit-scrollbar-track {
    background-color: white;
    border-radius: 10px;
    /*스크롤바 트랙 색상*/
}

.passengerView {
    padding: 20px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 30%;
    height: 32%;
    border-radius: 15px;
    background-color: white;
    box-shadow: 2px 2px 10px lightgrey;
}

tr {
    text-align: center;
    font-size: 20px;

}

th {
    background-color: teal;
    font-weight: 900;
    color: white;
    padding: 10px 0;
}

td {
    padding: 20px;
    font-size: 18px;
    font-family: Verdana, Geneva, Tahoma, sans-serif;

}

thead {
    font-weight: 900;

}

.resTitle {
    font-size: 30px;
    font-weight: 900;
    color: teal;
    margin-left: 10%;
    margin-bottom: 1%;

}

.passBtn {
    border: none;
    border-radius: 4px;
    background-color: teal;
    color: white;
    padding: 6px;
}

.Footer {
    margin-top: 100px;
    border-top: 1px solid;

}

.FootLeft {
    float: left;
    margin-left: 280px;
    margin-top: 30px;
    font-size: 40px;
}

.footLogo {
    width: 100px;
    height: 70px;
}

.FootRight {
    float: right;
    margin-right: 280px;
    margin-top: 60px;
}

.topBtn,
a:hover,
a:visited,
a:active {
    color: rgb(77, 77, 77);
}
</style>
