<template>
<div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
    <!--공지 모달-->
    <div v-if="NoticeModalView == true" class="NoticeModalView" :class="{ active : NoticeModalView }">
        <NoticeModal @noticeView="noticeView" @move="move" @close="NoticeModalPopUp"></NoticeModal>
    </div>

    <div class="carousel-inner" id="top">
        <span class="">

            <!--배너1-->
            <div class="carousel-item active">
                <!--배너 좌우 이동 버튼-->
                <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                </button>
                <!--배너 이미지1-->
                <img class="banner" src="../assets/bannerImage/1.jpg">
                <div class="container1">
                    <div class="carousel-caption">
                        <h2>Fastrip</h2>
                        <h3>Airline Ticketing</h3>
                    </div>
                </div>
            </div>

            <!--배너2-->
            <div class="carousel-item">
                <!--배너 좌우 이동 버튼-->
                <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                </button>
                <!--배너 이미지2-->
                <img class="banner" src="../assets/bannerImage/2.jpg">
                <div class="container2">
                    <div class="carousel-caption">
                        <h2>Budapest</h2>
                        <h3>in Hungary</h3>
                        <br>
                        <h4 @click="ing">자세히 보기</h4>
                    </div>
                </div>
            </div>

            <!--배너3-->
            <div class="carousel-item">
                <!--배너 좌우 이동 버튼-->
                <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                </button>
                <!--배너 이미지3-->
                <img class="banner" src="../assets/bannerImage/3.jpg">
                <div class="container3">
                    <div class="carousel-caption">
                        <h2>Hokkaido, Biei</h2>
                        <h3>in Japan</h3>
                        <br>
                        <h4 @click="ing">자세히 보기</h4>
                    </div>
                </div>
            </div>
            
            <!--배너4-->
            <div class="carousel-item">
                <!--배너 좌우 이동 버튼-->
                <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                </button>
                <!--배너 이미지4-->
                <img class="banner" src="../assets/bannerImage/4.jpg">
                <div class="container4">
                    <div class="carousel-caption">
                        <h2>Fastrip</h2>
                        <h3>The lowest-priced airline ticket!</h3>
                        <br>
                        <h4 @click="ing">자세히 보기</h4>
                    </div>
                </div>
            </div>
        </span>

        <!--항공 예약 박스-->
        <!--------------------------------------------------------------------------------->

        <div class="resForm">
            <div class="res-area-select">
                <button type="button" @click="toggleRoundTrip" class="btn-field" id="resRoundTrip">왕복</button>
                <button type="button" @click="toggleOneWay" class="btn-field" id="resOneWay">편도</button>
                <br>
                <br>
                <div class="FromTo">
                    <!--FromArea-->
                    <img v-if="fromBtn1" type="button" id="fromValue" class="fromBtn" :src="require(`../assets/FromArea/${fromImgName}.jpg`)" @click="fromAreaPopUp" width="200" />
                    <img v-if="toBtn2" type="button" id="toValue" class="toBtn" :src="require(`../assets/ToArea/${toImgName}.jpg`)" @click="toAreaPopUp" width="200" />
                    <div v-if="fromAreaView == true" class="fromAreaView" :class="{ active : fromAreaView }">
                        <FromArea @close="fromAreaPopUp" @update-fromArea="updateFromArea"></FromArea>
                    </div>
                    <!--Area Change-->
                    <img type="button" class="ppg-refresh" src="../assets/change.png" @click="change" />

                    <!--ToArea-->
                    <img v-if="fromBtn2" type="button" class="fromBtn" :src="require(`../assets/FromArea/${fromImgName}.jpg`)" @click="fromAreaPopUp" width="200" />
                    <img v-if="toBtn1" type="button" class="toBtn" :src="require(`../assets/ToArea/${toImgName}.jpg`)" @click="toAreaPopUp" width="200" />
                    <div v-if="toAreaView == true" class="toAreaView" :class="{ active : toAreaView }">
                        <ToArea @close="toAreaPopUp" @update-toArea="updateToArea"></ToArea>
                    </div>
                </div>
                <br>
                <br>
                <hr>

                <!--여행 날짜 선택-->

                <!--왕복 날짜 선택-->
                <Datepicker v-if="datePickerShow1" class="datePicker" @update:model-value="datepickerShow1" v-model="bothWay" placeholder="                              가는날 ~ 오는날" format="yyyy-MM-dd" :min-date="new Date()" modelAuto range>
                    <template #month="{ value }">
                        {{ value + 1 + "월"}}
                    </template>
                    <template #month-overlay="{ value }">
                        {{ value + 1 + "월"}}
                    </template>
                </Datepicker>
                <div type="button" class="selectDate1" v-if="selectDate1">
                    <div class="selectDate2">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi">
                            <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
                        </svg>
                        {{Format1(bothWay)}}
                    </div>
                </div>

                <!--편도 날짜 선택-->
                <Datepicker v-if="datePickerShow2" class="datePicker" @update:model-value="datepickerShow2" v-model="oneWay" placeholder="                                  탑승일 선택" format="yyyy-MM-dd" :min-date="new Date()">
                    <template #month="{ value }">
                        {{ value + 1 + "월"}}
                    </template>
                    <template #month-overlay="{ value }">
                        {{ value + 1 + "월"}}
                    </template>
                </Datepicker>
                <div type="button" class="selectDate1" v-if="selectDate2">
                    <div class="selectDate2">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi">
                            <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
                        </svg>
                        {{Format2(oneWay)}}
                    </div>
                </div>

            </div>

            <!--승객수 팝업-->
            <button type="button" class="btn-field" id="resPassenger" @click="popUp">
                <div class="count1">승객 수 </div>
                <div class="countImg">
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
                    </svg>
                </div>
                <div class="count2">
                    <span class="adultCount">성인{{AdultCount}}명</span>
                    <span v-if="ChildCount > 0" class="childCount">, 소아{{ChildCount}}명</span>
                    <span v-if="InfantCount > 0" class="infantCount">, 유아{{InfantCount}}명</span>
                </div>
            </button>

            <div class="popup-view" :class="{ active : popupView }">
                <HeadCount @close-popup="popUp()" :AdultCount="AdultCount" :ChildCount="ChildCount" :InfantCount="InfantCount" @update-count="updateCount"></HeadCount>
            </div>
            <div>

            </div>
            <!--좌석 선택-->

            <select id="inputState" class="form-select">
                <option selected>좌석 등급</option>
                <option>일반석</option>
                <option>이코노미</option>
                <option>비즈니스</option>
            </select>

            <input type="submit" @click="submit()" value="항공편 검색" class="submit-btn">

        </div>

    </div>
</div>
<!--------------------------------------------------------------------------------->

<!-- 조회버튼 -->
<div class="refer">
    <!--예약 조회 버튼-->
    <button type="button" class="btn btn-lg btn-default">
        <img src="../assets/magnifier.jpg" width="30" height="30">        
        <a href="/Reservation">예약 조회</a>
    </button>
    <!-- 버튼-->
    <img src="../assets/vertical.jpg" width="20" height="40">
    <button type="button" class="btn btn-lg btn-default" id="checkIn" @click="ing">
        <img src="../assets/check.png" width="30" height="30">
        <a href="">체크인</a>
    </button>
    <img src="../assets/vertical.jpg" width="20" height="40">
    <!--항공편 조회 버튼-->
    <button type="button" class="btn btn-lg btn-default" @click="ing">&nbsp;
        <a href="">✈ &nbsp; 항공편 현황</a>
    </button>

</div>
<br>

<div class="part2" id="part2">
    <hr>

    <!-- 추천 여행지 -->
    <Product></Product>
    <!----------------->

    <hr>
</div>

<div class="recommendProduct" id="part3">
    <div class="">
        <div class="">
            <br>
            <div class="travelTip">
                <h1>복잡한 여행을 Easy하게!</h1><br>
                <h3>전자서식을 작성하시면 여행이 한결 여유로워집니다.</h3><br>
                <a @click="ing">자세히 보기</a>
            </div>
        </div>
        <div>
            <img class="travelImg" src="../assets/travel1.jpg" />
        </div>
    </div>
</div>

<!--여행 도우미-->
<div class="part4" id="part4">
    <div class="side">
        <h2>여행의 완성을 위한 경험</h2>
    </div>
    <div class="sideTip">
        <div class="sideTip1">
            <a @click="ing">🔍&nbsp; 예약 조회</a>
            <a @click="ing">🏦&nbsp; 호텔</a>
            <a @click="ing">🚗&nbsp; 렌터카</a>
        </div>
        <div class="sideTip2">
            <a @click="ing">📖&nbsp; 기내 면세점</a>
            <a @click="ing">📝&nbsp; 여행자 보험</a>
            <a @click="ing">📋&nbsp; 여행 상품</a>
        </div>
    </div>

</div>

<!-- FOOTER -->
<footer class="Footer">
    <div class="FootLeft">
        <img class="footLogo" src="../assets/Logo2.png">
        <span> Fastrip</span>
        <p>&copy; 2022 Company, Fastrip</p>
    </div>

    <div class="FootRight">
        <a class="topBtn" href="#top">✈ Top</a>
    </div>
</footer>
</template>

<script>
import Datepicker from '@vuepic/vue-datepicker';
import HeadCount from './HeadCount.vue';
import FromArea from './FromArea.vue';
import ToArea from './ToArea.vue';
import axios from 'axios';
import Product from './Product.vue';
import NoticeModal from "@/components/NoticeModal.vue";
import VueCookies from 'vue-cookies'

export default {
    name: 'HelloWorld',
    components: {
        Datepicker,
        HeadCount,
        FromArea,
        ToArea,
        Product,
        NoticeModal
    },
    props: [''],
    data() {
        return {
            toBtn: "",
            fromBtn: "",
            bothWay: [],
            oneWay: [],
            popupView: false,
            datePickerShow1: true,
            datePickerShow2: false,
            selectDate1: false,
            selectDate2: false,
            product: [],
            count: 1,
            fromAreaView: false,
            toAreaView: false,
            showModal: false, //true일 때 모달창 보여짐
            modalDatas: [], //모달에 보낼 데이터 배열
            products: [],
            AdultCount: 1,
            ChildCount: 0,
            InfantCount: 0,
            fromImgName: 'SEL',
            toImgName: 'main',
            image: "",
            fromBtn1: true,
            toBtn1: true,
            fromBtn2: false,
            toBtn2: false,
            NoticeModalView: true,
            week: ['일', '월', '화', '수', '목', '금', '토'],

            startDate: [this.startYear, this.startMonth, this.startDay, this.startWeek],
            startYear: "",
            returnDate: [this.returnYear, this.returnMonth, this.returnDay, this.returnWeek],
        }
    },
    methods: {
        ing() {
            alert('준비중입니다.')
        },
        NoticeModalPopUp() {
            this.NoticeModalView = (this.NoticeModalView) ? false : true
        },
        datepickerShow1() {
            this.datePickerShow1 = false;
            this.selectDate1 = true;
        },
        datepickerShow2() {
            this.datePickerShow2 = false;
            this.selectDate2 = true;
        },
        toggleRoundTrip() {
            const target1 = document.getElementById('resRoundTrip');
            target1.style.color = "white"
            target1.style.background = "rgb(13, 165, 165)"

            const target2 = document.getElementById('resOneWay');
            target2.style.color = "#999"
            target2.style.background = "rgba(255, 255, 255, 0.963)"

            this.datePickerShow1 = true;
            this.datePickerShow2 = false;
            this.selectDate1 = false;
            this.selectDate2 = false;
            this.bothWay = "";
            this.oneWay = "";
        },
        toggleOneWay() {
            const target1 = document.getElementById('resOneWay');
            target1.style.color = "white"
            target1.style.background = "rgb(13, 165, 165)"

            const target2 = document.getElementById('resRoundTrip');
            target2.style.color = "#999"
            target2.style.background = "rgba(255, 255, 255, 0.963)"

            this.datePickerShow1 = false;
            this.datePickerShow2 = true;
            this.selectDate1 = false;
            this.selectDate2 = false;
            this.bothWay = "";
            this.oneWay = "";
        },
        Format(value) {
            this.show1 = false;
            var regexp = /(\d{3}(\d{3})(\d))$/g;
            return value.toString().substr(0, 16).replace(regexp, '');
        },
        change() {
            this.fromBtn1 = (this.fromBtn1) ? false : true
            this.toBtn1 = (this.toBtn1) ? false : true
            this.fromBtn2 = (this.fromBtn2) ? false : true
            this.toBtn2 = (this.toBtn2) ? false : true
        },
        popUp() {
            this.popupView = (this.popupView) ? false : true

        },
        fromAreaPopUp() {
            this.fromAreaView = (this.fromAreaView) ? false : true
        },
        toAreaPopUp() {
            this.toAreaView = (this.toAreaView) ? false : true
        },
        getData() {
            axios.get('/prod/all')
                .then((response) => {
                    this.products = response.data
                })
        },
        updateCount(AdultCount, ChildCount, InfantCount) {
            this.AdultCount = AdultCount;
            this.ChildCount = ChildCount;
            this.InfantCount = InfantCount;
            this.popupView = (this.popupView) ? false : true
            console.log("Home : " + this.AdultCount)
            console.log("Home : " + this.ChildCount)
            console.log("Home : " + this.InfantCount)
        },
        updateFromArea(image) {
            this.fromImgName = image;
            this.fromAreaView = (this.fromAreaView) ? false : true
        },

        updateToArea(image) {
            this.toImgName = image;
            this.toAreaView = (this.toAreaView) ? false : true
        },
        Format1(value) {
            let startDate1 = new Date(value[0]);
            let startDate2 = new Date(value[1]);

            let year1 = startDate1.getFullYear();
            let month1 = String(startDate1.getMonth() + 1).padStart(2, "0");
            let day1 = String(startDate1.getDate()).padStart(2, "0");
            let date1 = this.week[startDate1.getDay()];
          
            let year2 = startDate2.getFullYear();
            let month2 = String(startDate2.getMonth() + 1).padStart(2, "0");
            let day2 = String(startDate2.getDate()).padStart(2, "0");
            let date2 = this.week[startDate2.getDay()];
            
            return year1 + "-" + month1 + "-" + day1 + "(" + date1 + ") ~ " +  year2 + "-" + month2 + "-" + day2 + "(" + date2 + ")"
        },
        Format2(value) {
            let startDate = new Date(value);

            let year = startDate.getFullYear();
            let month = String(startDate.getMonth() + 1).padStart(2, "0");
            let day = String(startDate.getDate()).padStart(2, "0");
            let date = this.week[startDate.getDay()];

            return year + "-" + month + "-" + day + "(" + date + ") "
        },
        noticeView(value) { //cookie 설정            
            if (value == true) {
                VueCookies.set('view', value, "1d")
            }
        },
        getNoticeView() {
            if (VueCookies.get('view') == "true") {
                this.NoticeModalView = false
            } else if (VueCookies.get('view') == "false") {
                this.NoticeModalView = true
            }
        },
        submit() {
            let chooseInfo = {};
            let seat = document.getElementById('inputState').options[document.getElementById("inputState").selectedIndex].value;
            let fromArea = "";
            let toArea = "";

            if (this.selectDate1 == true) {
                //왕복 출발일
                this.startYear = this.bothWay[0].getFullYear();
                this.startMonth = this.bothWay[0].getMonth() + 1;
                this.startDay = this.bothWay[0].getDate();
                this.startWeek = this.week[this.bothWay[0].getDay()];

                //도착일
                this.returnYear = this.bothWay[1].getFullYear();
                this.returnMonth = this.bothWay[1].getMonth() + 1;
                this.returnDay = this.bothWay[1].getDate();
                this.returnWeek = this.week[this.bothWay[1].getDay()];
            } else if (this.selectDate2 == true) {
                //편도 출발일
                this.startYear = this.oneWay.getFullYear();
                this.startMonth = this.oneWay.getMonth() + 1;
                this.startDay = this.oneWay.getDate();
                this.startWeek = this.week[this.oneWay.getDay()];

                this.returnYear = '';
                this.returnMonth = '';
                this.returnDay = '';
                this.returnWeek = '';
            }

            if (this.selectDate1 == false && this.selectDate2 == false) {
                alert("날짜를 선택해주세요.")
                return false;
            }

            if (this.fromImgName == "SEL") {
                alert("출발지를 선택해주세요.")
                return false;
            }

            if (this.toImgName == "main") {
                alert("도착지를 선택해주세요.")
                return false;
            }

            if (seat == "좌석 등급") {
                alert("좌석을 선택해주세요.")
                return false;
            }

            if (this.fromBtn1 == true) {
                fromArea = this.fromImgName
                toArea = this.toImgName
            } else {
                fromArea = this.toImgName
                toArea = this.fromImgName
            }
            
            chooseInfo = {
                fromArea: fromArea,
                    toArea: toArea,
                    seat: seat,
                    startYear: this.startYear,
                    startMonth: this.startMonth,
                    startDay: this.startDay,
                    startWeek: this.startWeek,
                    returnYear: this.returnYear,
                    returnMonth: this.returnMonth,
                    returnDay: this.returnDay,
                    returnWeek: this.returnWeek,
                    AdultCount: this.AdultCount,
                    ChildCount: this.ChildCount,
                    InfantCount: this.InfantCount
                }

            this.$store.dispatch("chooseInfo", chooseInfo);

            this.$router.push('Departure')           

        },
        top() {

        }
    },

    mounted() {
        this.getNoticeView()
    }

}
</script>

<style>
.NoticeModalView {
    padding: 20px;
    position: fixed;
    top: 45%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 600px;
    height: 780px;
    border-radius: 15px;
    background-color: white;
    box-shadow: 1px 1px 6px teal;
    z-index: 10;
}

.NoticeModalView::before {
    display: block;
}

.NoticeodalView::-webkit-scrollbar {
    width: 0px;
}

.part2 {
    background-image: url("../assets/part2.jpg");
    margin-top: 5%;
    margin-bottom: 10%;
}

.part4 {
    /*background-color: rgb(241, 233, 231);*/
    background-image: url("../assets/part4.jpg");
    padding-top: 10%;
    padding-bottom: 33%;

}

h1,
h2 {
    font-size: 60px;
    margin-top: 40px;
}

a {
    text-decoration: none;
    font-size: 24px;
    margin-left: 20px;
    cursor: pointer;
}

.topBtn,
a:hover,
a:visited,
a:active {
    color: rgb(77, 77, 77);
}

.container1 h2 {
    color: rgba(0, 128, 128, 0.445);
    font-size: 100px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container1 h3 {
    color: rgba(0, 128, 128, 0.445);
    margin-bottom: 320px;
    font-size: 30px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container2 h2 {
    color: rgba(238, 203, 30, 0.959);
    font-size: 80px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container2 h3 {
    color: rgba(238, 203, 30, 0.959);
    font-size: 30px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container2 h4 {
    color: rgba(238, 203, 30, 0.959);
    font-size: 20px;
    margin-bottom: 240px;
    cursor: pointer;
}

.container3 h2 {
    color: rgb(219, 230, 237);
    font-size: 60px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container3 h3 {
    color: rgb(219, 230, 237);
    font-size: 30px;    
    margin-left: 50px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container3 h4 {
    color: rgb(219, 230, 237);
    margin-left: 100px;
    margin-bottom: 240px;    
}

.container4 h2 {
    color: rgb(234, 246, 255);
    font-size: 80px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container4 h3 {
    color: rgb(234, 246, 255);
    font-size: 18px;
    font-weight: 900;
    margin-left: 50px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container4 h4 {
    color: rgb(234, 246, 255);
    margin-left: 100px;
    margin-bottom: 240px;
}

.carousel-caption {
    margin-left: 630px;
    color: white;
}

.carousel-indicators {
    margin-left: 935px;
    color: rgba(213, 181, 22, 0.959);
}

.loginBtn {
    color: black;
    background-color: white;
    border-radius: 4px;
    width: 70px;
    height: 40px;
}

.banner {
    border-radius: 15px;
    border: 1px solid #999;
    width: 98%;
    height: 780px;
    margin-left: 1%;
    margin-right: 1%;
}

.bannerImg{
    border: 10px solid #999;
}

.carousel-control-prev {
    /* 사이즈 */
    width: 40px;
    height: 40px;
    border-top: 8px solid #999;
    /* 선 두께 */
    border-right: 8px solid #999;
    /* 각도 */
    transform: rotate(225deg);
    top: 50%;
    left: 710px;
}

.carousel-control-next {
    /* 사이즈 */
    width: 40px;
    height: 40px;
    /* 선 두께 */
    border-top: 8px solid #999;
    border-right: 8px solid #999;
    /* 각도 */
    transform: rotate(45deg);
    top: 50%;
    right: 50px;
}

.refer {
    text-align: center;
    margin-top: 2.5%;
    margin-bottom: 2%;
    font-size: 30px;

}

.refer button {
    width: 400px;
    font-weight: 900;
}

.recommendProduct {
    margin-top: 10%;
    margin-bottom: 3%;
}

.travelImg {
    width: 800px;
    height: 600px;
    margin-left: 29%;
    margin-bottom: 10%;
}

.travelTip {
    text-align: center;
    margin-bottom: 2%;
}

.side {
    color: white;
    margin-left: 5%;
    margin-top: 10%;

}

.sideTip {
    width: 650px;
    height: 210px;
    margin-left: 3%;
    margin-top: 3%;
    border: 4px solid white;
    border-radius: 10px;
    color: white;
}

.sideTip1 {
    border-radius: 8px;
    padding: 40px 100px;
}

.sideTip2 {
    padding: 10px 60px;
}

.Footer {
    border-top: 1px solid;
}

.FootLeft {
    float: left;
    margin-left: 280px;
    margin-top: 30px;
    font-size: 40px;
    padding-bottom: 50px;
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

.carousel-inner {
    height: 800px;
}

.resForm {
    width: 650px;
    height: 750px;
    box-shadow: 7px 7px 15px 0px rgb(107, 138, 138), 8px 8px 16px -10px rgb(82, 96, 96);
    border: 2px solid rgb(193, 188, 188);
    border-radius: 20px;
    background-color: rgba(252, 252, 252, 0.952);
    margin: 0 auto;
    margin-top: 0.5%;
    margin-left: 1.5%;
    position: absolute;
    padding: 15px;
    text-align: center;
}

.datePicker,
.form-select {
    border: 1px solid rgb(193, 188, 188);
    border-radius: 20px;
    font-size: 24px;
    color: #999;
    background: linear-gradient(135deg, rgba(255, 255, 255, 0.963) 0%, rgba(237, 237, 237, 0.959) 100%);
    display: table;
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;
    margin-bottom: 30px;
    cursor: pointer;
}

.btn-field:not(#resRoundTrip, #resOneWay) {
    border: 1px solid rgb(193, 188, 188);
    border-radius: 20px;
    font-size: 24px;
    color: #999;
    background: linear-gradient(135deg, rgba(255, 255, 255, 0.963) 0%, rgba(237, 237, 237, 0.959) 100%);
    margin-top: 30px;
    cursor: pointer;
    display: inline-block;
    white-space: nowrap;

}

.adultCount {
    display: inline-block;
    white-space: nowrap;
}

.childCount {
    white-space: nowrap;
    display: inline-block;

}

.infantCount {
    white-space: nowrap;
    display: inline-block;

}

.countImg {
    float: right;
    margin-right: 3%;
}

.count1 {
    float: left;
    margin-left: 7%;
}

.count2 {
    float: right;
    margin-right: 3%;
}

.submit-btn {
    width: 300px;
    height: 50px;
    border: 1px solid rgb(193, 188, 188);
    border-radius: 20px;
    font-size: 24px;
    color: white;
    background: rgb(13, 165, 165);
    display: table;
    margin-left: auto;
    margin-right: auto;
    margin-top: 30px;
    margin-bottom: 30px;
    cursor: pointer;
}

.res-area-select {
    height: 430px;
    background: linear-gradient(135deg, rgba(255, 255, 255, 0.612) 0%, rgb(237, 237, 237) 100%);
    border-radius: 50px;
    border: 1px solid rgb(193, 188, 188);
    padding: 30px;
}

#resRoundTrip,
#resOneWay {
    width: 85px;
    display: inline;
    margin: 6px;
    font-size: 24px;
    border-radius: 20px;
    border: 1px solid rgb(193, 188, 188);
    color: #999;
    background: rgba(255, 255, 255, 0.963);
}

#resRoundTrip {
    color: white;
    background: rgb(13, 165, 165);
}

.ppg-refresh {
    width: 50px;
    height: 40px;
    margin: 0rem 1rem 0rem 1rem;
    vertical-align: middle;
    cursor: pointer;
    border: 4px solid rgb(225, 225, 225);
    border-radius: 18px;
}

#resFrom,
#resTo {
    width: 100px;
}

#resPassenger,
#inputState {
    width: 565px;
    height: 70px;
    background: linear-gradient(135deg, rgb(255, 255, 255) 0%, rgb(237, 237, 237) 100%);
}

#inputState {
    padding-left: 50px;
    font-size: 22px;
}

.fromBtn,
.toBtn {
    width: 40%;
    height: 115px;
    border: 4px solid rgb(225, 225, 225);
    border-radius: 20px;
}

.bi {
    width: 15.5px;
    height: 15.5px;
    float: left;
    margin-left: 12px;
}

.datePicker {
    width: 450px;
    border-radius: 20px;
    color: #999;
    box-shadow: -4px -4px 10px -8px rgba(255, 255, 255, 1), 4px 4px 10px -8px rgba(0, 0, 0, .3);
    display: table;
    margin-left: auto;
    margin-right: auto;
    margin-top: 43px;
    padding: 9px;

}

.selectDate1 {
    width: 450px;
    height: 16.2%;
    border: 1px solid rgb(193, 188, 188);
    border-radius: 20px;
    color: #999;
    background: linear-gradient(135deg, rgba(255, 255, 255, 0.963) 0%, rgba(237, 237, 237, 0.959) 100%);
    box-shadow: -4px -4px 10px -8px rgba(255, 255, 255, 1), 4px 4px 10px -8px rgba(0, 0, 0, .3);
    display: table;
    margin-left: auto;
    margin-right: auto;
    margin-top: 43px;
    padding: 9px;
    font-size: 15px;
}

.selectDate2 {
    height: 96%;
    color: #999;
    border: 1px solid rgb(193, 188, 188);
    border-radius: 4px;
    background-color: white;
    margin-top: 2px;
    text-align: center;
    padding: 10px 0;
}

.popup-view {
    opacity: 0;
    display: none;
    visibility: hidden;
    position: fixed;
    z-index: 1;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 60%;
    height: 40%;
    border-radius: 15px;
    background-color: rgb(244, 244, 244);
    box-shadow: 2px 2px 10px lightgrey;
}

.active {
    opacity: 1;
    display: block;
    visibility: visible;
}

#checkIn {
    padding-left: 30px;
    padding-right: 20px;
}

.fromAreaView,
.toAreaView {
    padding: 20px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 60%;
    height: 40%;
    text-align: center;
    border-radius: 15px;
    background-color: rgb(246, 246, 246);
    box-shadow: 2px 2px 10px lightgrey;
    z-index: 5
}

.fromBtn:hover,
.toBtn:hover,
.ppg-refresh:hover,
.datePicker:hover,
.selectDate1:hover,
.selectDate2:hover,
.btn-field:hover,
.form-select:hover,
.submit-btn:hover,
#resPassenger:hover,
#resRoundTrip:hover,
#resOneWay:hover  {
    border: 2.5px solid teal;
}

/* 화면 축소 전 후 */

@media (min-width: 1450px) {
    .NoticeModalView {
        left: 60%
    }

    .container1 h2 {
        font-size: 150px;
    }

    .container1 h3 {
        font-size: 30px;
        margin-bottom: 250px;
    }

    .container2 h2 {
        font-size: 120px;
    }

    .container2 h3 {
        font-size: 40px;
    }

    .container2 h4 {
        font-size: 20px;
        margin-bottom: 200px;        
    }

}

/* 화면 축소 전 후 */

@media (min-width: 1900px) {
    .NoticeModalView {
        left: 70%
    }

    .container1 h2 {
        font-size: 240px;
    }

    .container1 h3 {
        font-size: 40px;
        margin-bottom: 220px;
    }

    .container2 h2 {        
        font-size: 190px;
        
    }

    .container2 h3 {
        font-size: 40px;
        margin-left: 10%;
    }

    .container2 h4 {
        font-size: 30px;
        margin-bottom: 170px;
        margin-left: 10%;
    }
    
}

/* 화면 축소 전 후 */

@media (min-width: 1600px) {
    .container3 h2 {
        font-size: 80px;
        margin-left: 40%;
    }
    
    .container3 h3 {
        font-size: 30px;
        margin-left: 57%;
    }

    .container3 h4 {
        font-size: 25px;
        margin-left: 57%;
        margin-bottom: 220px;
    }

    .container4 h2 {
        font-size: 100px;
        margin-left: 70%;
    }
    
    .container4 h3 {
        font-size: 20px;
        margin-left: 70%;
    }

    .container4 h4 {
        font-size: 25px;
        margin-left: 75%;
        margin-bottom: 220px;
    }
}

/* 화면 축소 전 후 */

@media (min-width: 2000px) {

    .container3 h2 {
        font-size: 100px;
        margin-left: 10%;
    }

    .container3 h3 {
        font-size: 40px;
        margin-left: 30%;
    }

    .container3 h4 {
        font-size: 30px;
        margin-left: 30%;
        margin-bottom: 200px;
    }

    .container4 h2 {
        font-size: 140px;
        margin-left: 10%;
    }

    .container4 h3 {
        font-size: 25px;
        margin-left: 10%;
    }

    .container4 h4 {
        font-size: 30px;
        margin-left: 10%;
        margin-bottom: 200px;
    }

}
</style>
