<template>
<div class="res">
    <div class="resTitle">예약 조회</div>
    <div class="table-responsive">
        <!--예약자 정보 모달-->
        <span v-if="passengerView" class="passengerView">
            <PassengerModal @close="passengerModal" :user="user"></PassengerModal>
        </span>
        <!--예약 취소 모달--> 
        <span v-if="resCancelView" class="passengerView">
            <ResCancelModal @close="resDelModal"></ResCancelModal>
        </span>

        <div v-if="res == ''" class="resBlank">
            <img src="../assets/magnifier.jpg"><br>
            <span v-if="this.$store.state.isLogin == true">예약 내역 없음.</span><br>
            <div v-if="this.$store.state.isLogin == false">
            <input type="text" v-model="emailValue" class="emailTextBar" @keyup.enter="getData(emailValue)" placeholder="이메일을 입력해주세요.">
            <input type="button" class="emailGetData" @click="getData(emailValue)" value="비회원 조회하기">
            </div>
        </div>

        <table v-if="res != ''" class="table table-striped table-sm">
            <thead>
                <tr>
                    <th class='fixedHeader' scope="col">예약 취소</th>
                    <th class='fixedHeader' scope="col">예약 번호</th>
                    <th class='fixedHeader' scope="col">예약자 정보</th>
                    <th class='fixedHeader' scope="col">예약 날짜</th>
                    <th class='fixedHeader' scope="col">가는 편</th>
                    <th class='fixedHeader' scope="col">오는 편</th>
                    <th class='fixedHeader' scope="col">왕복/편도</th>
                    <th class='fixedHeader' scope="col">좌석</th>
                    <th class='fixedHeader' scope="col">출발지 → 도착지 (항공편)</th>
                    <th class='fixedHeader' scope="col">등급</th>
                    <th class='fixedHeader' scope="col">도착지 → 출발지 (항공편)</th>
                    <th class='fixedHeader' scope="col">등급</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="res in res" :key="res">
                    <td>
                        <button type="button" class="resDelBtn" @click="resDelModal(res?.res_no)">취소</button>                                                                        
                    </td>
                    <td>{{"Fastrip - " + res?.res_no}}</td>
                    <td><input type="button" class="passBtn" value="예약자 정보" @click="passengerModal(res?.res_no)"></td>
                    <!-- 예약날짜 -->
                    <td>{{resDate1(res?.res_date)}}</td>
                    <!-- 가는편/오는편 -->
                    <td>{{resDate2(res?.startDate) + " " + res?.startTime1 + " ~ " + res?.arriveTime1}}</td>
                    <td v-if="res?.way == '왕복'">{{resDate2(res?.returnDate) + " " + res?.startTime2}} ~ {{res?.arriveTime2}}</td>
                    <td v-if="res?.way == '편도'" class="oneWay">-</td>
                    <td>{{res?.way}}</td>
                    <td>{{res?.seat}}</td>
                    <td>{{res?.fromArea + " → " + res?.toArea + "(" + res?.flight1 + ")"}}</td>
                    <td>{{res?.seatClass1}}</td>
                    <td v-if="res?.way == '왕복'">{{res?.toArea + " → " + res?.fromArea + "(" + res?.flight2 + ")"}}</td>
                    <td v-if="res?.way == '편도'">{{res?.oneWayArea}}
                        <span v-if="res?.way == '편도'">-</span>
                    </td>
                    <td>{{res?.seatClass2}}<span v-if="res?.way == '편도'">-</span></td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- FOOTER -->
    <div class="Footer">
        <div class="FootLeft">
            <img class="footLogo" src="../assets/Logo2.png" loading="lazy">
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
import ResCancelModal from "@/components/ResCancelModal";

export default {
    name: 'HelloWorld',
    components: {
        PassengerModal,
        ResCancelModal
    },
    props: [""],
    data() {
        return {
            week: ['일', '월', '화', '수', '목', '금', '토'],
            res: [],
            passengerView: false,
            resCancelView: false,
            user: [],
        }
    },
    methods: {
        resDate1(value) { //협정 세계시(UTC) 시간 차이로 인한 시간 재정의
            let resDate = new Date(value);

            let year = resDate.getFullYear();
            let month = String(resDate.getMonth() + 1).padStart(2, "0");
            let day = String(resDate.getDate()).padStart(2, "0");
            let date = this.week[resDate.getDay()];
            let hour = String(resDate.getHours()).padStart(2, "0");
            let minute = String(resDate.getMinutes()).padStart(2, "0");

            return year + "-" + month + "-" + day + "(" + date + ") " + hour + ":" + minute
        },
        resDate2(value) {
            let resDate = new Date(value);

            let year = resDate.getFullYear();
            let month = String(resDate.getMonth() + 1).padStart(2, "0");
            let day = String(resDate.getDate()).padStart(2, "0");
            let date = this.week[resDate.getDay()];

            return year + "-" + month + "-" + day + "(" + date + ") "
        },
        emailGetData(value){
            console.log("1 : " + value);
            this.getData(value);
        },
        getData(value) { //로그인 email 기준 예약내역 전부 조회
            //로그인 시 자동으로 DB로 name과 email 전송
            //로그인 상태에서 예약 완료시 예약데이터에 name과 email 전송
            //로그인 상태에서 email 기준으로 예약되었던 email과 매칭 후 예약 내역 조회
            let email = '';
            if(this.$store.state.isLogin == true){
                email = this.$store.state.userInfo.email;
            } else if (this.$store.state.isLogin == false){
                email = value;
                
            }
            //ft5698@nate.com
            //let email = "test@test.com";
            axios.get('/res/resList/' + email)
                .then((res) => {
                    this.res = res.data
                    this.user = res.data
                })
                .catch(err => {
                    console.log(err)                            
                    console.log("ss")
                })
        },
        passengerModal(value) { //예약자 정보 팝업
            this.passengerView = (this.passengerView) ? false : true
            this.$store.dispatch("res_no", value);

        },
        resDelModal(value){//예약 취소 팝업
            console.log(value)
            this.resCancelView = (this.resCancelView) ? false : true
            this.$store.dispatch("res_no", value);
        },
        Gender(value) { //성별 컨버트
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
.fixedHeader {
	position: sticky;
	top: 0;
}
.res {
    padding-top: 100px;
}

.resBlank {
    font-size: 20px;
    font-weight: 900;
    color: #999;
    text-align: center;
    padding-top: 13%;
}

.resBlank img {
    width: 60px;
    height: 60px;
    margin-bottom: 10px;
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
    content: url(@/assets/Logo2.png);    
    padding: 20px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 20%;
    height: 15%;
    border-radius: 15px;
    background-color: white;
    box-shadow: 2px 2px 10px lightgrey;
    overflow: auto;
    z-index: 1;
}

/* 화면 축소 전 후 */

@media (min-width: 850px) {
    .passengerView {       
        content: none; 
    padding: 20px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 35%;
    height: 38%;
    border-radius: 15px;
    background-color: white;
    box-shadow: 2px 2px 10px lightgrey;
    overflow: auto;
}

.passengerView::-webkit-scrollbar {
    width: 15px;

}

.passengerView::-webkit-scrollbar-thumb {
    background-color: teal;
    border-radius: 10px;
    /*스크롤바의 색상*/
}

.passengerView::-webkit-scrollbar-track {
    background-color: white;
    border-radius: 10px;
    border: 0.1px solid teal;
    /*스크롤바 트랙 색상*/
}

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

.resDelBtn{
    color: white;
    font-weight: 900;
    background-color: teal;
    border: none;        
    border-radius: 4px;
    padding: 6px;
}

.emailGetData{    
    border: 3px solid teal;
    border-radius: 4px;
    color: teal;
    background-color: white;
    padding: 10px;
    font-weight: 900;
}

.emailTextBar{
    width: 350px;
    margin-top: 50px;
    border: 3px solid 999;
    border-radius: 4px;
    color: #999;
    background-color: white;
    padding: 10px;    
    margin-right: 15px;
}

.emailTextBar:hover{    
    border: 3px solid teal;
}

.oneWay{
    padding-left: 100px;
    padding-right: 100px;
}
</style>
