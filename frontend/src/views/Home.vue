<template>
<div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <span class="">
            <!--배너1-->
            <div class="carousel-item active">
                <!--배너 좌우 이동 버튼-->
                <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                </button>
                <!--슬라이드 페이지 표시-->
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <!--배너 이미지1-->
                <svg class="banner" viewBox="0 80 890 315">
                    <image href="../assets/bannerImage/1.jpg" />
                </svg>
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
                <!--슬라이드 페이지 표시-->
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" class="active" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <!--배너 이미지2-->
                <svg class="banner" viewBox="0 60 890 315">
                    <image href="../assets/bannerImage/2.jpg" />
                </svg>
                <div class="container2">
                    <div class="carousel-caption">
                        <h2>Budapest</h2>
                        <h3>Capital of Hungary</h3>
                        <br>
                        <a>자세히 보기</a>
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
                <!--슬라이드 페이지 표시-->
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" class="active" aria-label="Slide 3"></button>
                </div>
                <!--배너 이미지3-->
                <svg class="banner" viewBox="0 40 890 315">
                    <image href="../assets/bannerImage/3.jpg" />
                </svg>
                <div class="container3">
                    <div class="carousel-caption">
                        <h2>Fastrip</h2>
                        <h3>The lowest-priced airline ticket!</h3>
                        <br>
                        <a>자세히 보기</a>
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
                    <img v-show="fromBtn1" type="button" id="fromValue" class="fromBtn" :src="require(`../assets/FromArea/${fromImgName}.jpg`)" @click="fromAreaPopUp" width="200" />
                    <img v-show="toBtn2" type="button" id="toValue" class="toBtn" :src="require(`../assets/ToArea/${toImgName}.jpg`)" @click="toAreaPopUp" width="200" />
                    <div v-if="fromAreaView == true" class="fromAreaView" :class="{ active : fromAreaView }">
                        <FromArea @close="fromAreaPopUp" @update-fromArea="updateFromArea"></FromArea>
                    </div>
                    <!--Area Change-->
                    <img type="button" class="ppg-refresh" src="../assets/change.png" @click="change" />

                    <!--ToArea-->
                    <img v-show="fromBtn2" type="button" class="fromBtn" :src="require(`../assets/FromArea/${fromImgName}.jpg`)" @click="fromAreaPopUp" width="200" />
                    <img v-show="toBtn1" type="button" class="toBtn" :src="require(`../assets/ToArea/${toImgName}.jpg`)" @click="toAreaPopUp" width="200" />
                    <div v-if="toAreaView == true" class="toAreaView" :class="{ active : toAreaView }">
                        <ToArea @close="toAreaPopUp" @update-toArea="updateToArea"></ToArea>
                    </div>
                </div>
                <br>
                <br>
                <hr>

                <!--여행 날짜 선택-->
                <Datepicker v-if="datePickerShow1" class="datePicker" @update:model-value="datepickerShow1" v-model="bothWay" placeholder="                              가는날 ~ 오는날" format="yyyy-MM-dd" :dayNames="lang.days" modelAuto range>
                    <template #month="{  value  }">
                        {{ value + 1 + "월"}}
                    </template>
                    <template #month-overlay="{ value }">
                        {{ value + 1 + "월"}}
                    </template>
                </Datepicker>
                <!--왕복 날짜 선택-->
                <div type="button" class="selectDate1" v-show="selectDate1">
                    <div class="selectDate2">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi">
                            <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
                        </svg>
                        {{Format1(bothWay)}}
                    </div>
                </div>

                <!--<input v-show="selectDate" type="text" v-model="bothWay">-->

                <!--편도 날짜 선택-->
                <Datepicker v-if="datePickerShow2" class="datePicker" @update:model-value="datepickerShow2" v-model="oneWay" placeholder="                                  탑승일 선택" format="yyyy-MM-dd" :dayNames="lang.days">
                    <template #month="{  value  }">
                        {{ value + 1 + "월"}}
                    </template>
                    <template #month-overlay="{ value }">
                        {{ value + 1 + "월"}}
                    </template>
                </Datepicker>
                <div type="button" class="selectDate1" v-show="selectDate2">
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
                <div class="count2">
                    <p class="adultCount">성인{{AdultCount}}명</p>
                    <p v-if="ChildCount > 0" class="childCount">, 소아{{ChildCount}}명</p>
                    <p v-if="InfantCount > 0" class="infantCount">, 유아{{InfantCount}}명</p>
                </div>
                <div class="countImg">
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
                    </svg>
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
    <button type="button" class="btn btn-lg btn-default" @click="ing">
        <img src="../assets/magnifier.jpg" width="30" height="30">
        <a href="">예약 조회</a>
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
        <img src="../assets/calendar.png" width="30" height="30">
        <a href="">항공편 현황</a>
    </button>

</div>
<br>

<div class="part2">
    <hr>

    <!-- 추천 여행지 -->
    <Product></Product>
    <!----------------->

    <hr>
</div>

<div class="recommendProduct">
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
<div class="part4">
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

<div class="footLogoName">
    <img class="footLogo" src="../assets/Logo.png">
    Fastrip
</div>
<footer class="footer">
    <p>&copy; 2022 Company, Fastrip</p>
    <a class="topBtn" href="">🎈 Top</a>
</footer>
</template>

<script>
import Datepicker from '@vuepic/vue-datepicker';
import HeadCount from './HeadCount.vue';
import FromArea from './FromArea.vue';
import ToArea from './ToArea.vue';
import axios from 'axios';
import Product from './Product.vue';

export default {
    name: 'HelloWorld',
    components: {
        Datepicker,
        HeadCount,
        FromArea,
        ToArea,
        Product
    },
    props: [''],
    data() {
        return {
            lang: {
                days: ['월', '화', '수', '목', '금', '토', '일']
            },
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
            returnDate: "",
        }
    },
    methods: {
        ing(){
            alert('준비중입니다.')
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
            this.datePickerShow1 = true;
            this.datePickerShow2 = false;
            this.selectDate1 = false;
            this.selectDate2 = false;
            this.bothWay = "";
            this.oneWay = "";
        },
        toggleOneWay() {
            this.datePickerShow1 = false;
            this.datePickerShow2 = true;
            this.selectDate1 = false;
            this.selectDate2 = false;
            this.bothWay = "";
            this.oneWay = "";
        },
        test() {
            let startDate = "";
            let returnDate = "";

            if (this.oneWay.toString() != false) {
                var string1 = this.oneWay.toString();
                startDate = string1.substring(0, 16);
                returnDate = null;
            } else if (this.bothWay.toString() != false) {
                var string2 = this.bothWay.toString();
                startDate = string2.substring(0, 16);
                returnDate = string2.substring(43, 58);
            }

            console.log(startDate)
            console.log(returnDate)
        },
        test1() {
            let seat = document.getElementById('inputState').options[document.getElementById("inputState").selectedIndex].value;

            alert(document.getElementById("fromValue1").value)
            alert(document.getElementById("toValue1").value)
            alert(document.getElementById("fromValue2").value)
            alert(document.getElementById("toValue2").value)

            if (this.selectDate1 == true) {
                alert(this.bothWay)
                alert("왕복")
            } else if (this.selectDate2 == true) {
                alert(this.oneWay)
                alert("편도")
            } else {
                alert("여행 일정을 선택해주세요.")
                return false;
            }

            alert(this.AdultCount)
            if (this.ChildCount > 0) {
                alert(this.ChildCount)
            }
            if (this.InfantCount > 0) {
                alert(this.InfantCount)
            }
            alert(seat)
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
        submit() {
            let seat = document.getElementById('inputState').options[document.getElementById("inputState").selectedIndex].value;
            let fromArea = "";
            let toArea = "";
            let startDate = "";
            let returnDate = "";

            if (this.selectDate1 == false && this.selectDate2 == false) {
                alert("날짜를 선택해주세요.")
            }

            if(this.fromImgName == "SEL"){
                alert("출발지를 선택해주세요.")
                return false;
            }

            if(this.toImgName == "main"){
                alert("도착지를 선택해주세요.")
                return false;
            }   

            if (this.oneWay.toString() != false) {
                var string1 = this.oneWay.toString();
                startDate = string1.substring(0, 16);
                returnDate = " ";
            } else if (this.bothWay.toString() != false) {
                var string2 = this.bothWay.toString();
                startDate = string2.substring(0, 16);
                returnDate = string2.substring(43, 58);
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

            this.$router.push({
                name: 'Departure',
                params: {
                    fromArea: fromArea,
                    toArea: toArea,
                    seat: seat,
                    startDate: startDate,
                    returnDate: returnDate,
                    AdultCount: "성인 " + this.AdultCount + "명",
                    ChildCount: ", 소아 " + this.ChildCount + "명",
                    InfantCount: ", 유아 " + this.InfantCount + "명",
                }
            });

        }
    },

    mounted() {
        this.getData()
    }

}
</script>

<style>
.part2 {
    background-image: url("../assets/part2.jpg");
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

a:hover,
a:visited,
a:active {
    color: rgb(77, 77, 77);
}
.container1 h2{
    color: rgba(213, 181, 22, 0.959);
    font-size: 220px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container1 h3{
    color: rgba(213, 181, 22, 0.959);
    margin-bottom: 160px;
    font-size: 40px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container2 h2{
    color: rgba(213, 181, 22, 0.959);
    font-size: 220px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container2 h3{
    color: rgba(213, 181, 22, 0.959);
    margin-bottom: 110px;
    font-size: 40px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}


.container3 h2{
    color: rgb(234, 246, 255);
    font-size: 150px;
    font-weight: 900;
    margin-left: 450px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.container3 h3{
    color: rgb(234, 246, 255);
    font-size: 25px;
    font-weight: 900;
    margin-bottom: 180px;
    margin-left: 520px;
    font-family: Georgia, 'Times New Roman', Times, serif;
}

.carousel-caption {
    margin-left: 600px;
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
    width: 98%;
    margin-left: 1%;
    margin-right: 1%;
}

.carousel-control-prev {
    /* 사이즈 */
    width: 40px;
    height: 40px;
    border-top: 8px solid white;
    /* 선 두께 */
    border-right: 8px solid white;
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
    border-top: 8px solid white;
    border-right: 8px solid white;
    /* 각도 */
    transform: rotate(45deg);
    top: 50%;
    right: 50px;
}

.refer {
    text-align: center;
    margin-top: 1%;
    margin-bottom: 2%;
}

.refer button {
    width: 400px;
}

.refer span {
    font-size: 30px;
}

.recommendProduct {
    margin-top: 10%;
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

.footer {
    display: flex;
    margin-left: 280px;
    margin-bottom: 30px;
    margin-top: 5px;
}

.topBtn {
    margin-left: 65%;
    margin-bottom: 2%;
}

.footLogo {
    margin-left: 270px;
    width: 100px;
    height: 70px;
}

.footLogoName {
    margin-top: 50px;
    font-size: 40px;
    font-family: "NanumBarunGothicBold";
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
.form-select,
.submit-btn {
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

.btn-field {
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
    white-space: nowrap;
    display: inline-block;
    float: right;
    margin-right: 3.5%;
    margin-top: 2.2%;
}

.count1 {
    white-space: nowrap;
    display: inline-block;
    float: left;
    margin-left: 7%;
    margin-top: 2.7%;
}

.count2 {
    white-space: nowrap;
    display: inline-block;
    margin-top: 2.5%;

}

.submit-btn {
    width: 300px;
    height: 50px;
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
.submit-btn:hover {
    border: 1.5px solid teal;
}

</style>
