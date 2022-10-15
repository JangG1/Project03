<template>
<div id="myCarousel" class="carousel slide" data-bs-ride="carousel">

    <div class="carousel-inner">

        <span class="">
            <!--배너1-->
            <div class="carousel-item active">
                <!--배너 좌우 이동 버튼-->
                <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
                <!--슬라이드 페이지 표시-->
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <!--배너 이미지-->
                <svg class="banner" viewBox="0 60 900 340" width="1670" height="630">
                    <image href="../assets/banner1.jpg" />
                </svg>
                <div class="container">
                    <div class="carousel-caption">
                        <h1>지금 갈 수 있는 여행지는 어디?</h1>
                        <br>
                        <h4>자세히 보기</h4>
                    </div>
                </div>
            </div>
            <!--배너2-->
            <div class="carousel-item">
                <!--배너 좌우 이동 버튼-->

                <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
                <!--슬라이드 페이지 표시-->
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <!--배너 이미지-->
                <svg class="banner" viewBox="0 60 900 340" width="1670" height="630">
                    <image href="../assets/banner2.jpg" />
                </svg>
                <div class="container">
                    <div class="carousel-caption">
                        <h1>아름다운 야경과 낭만의 도시</h1>
                        <h4>10월 3일 헝가리 부다페스트 취항합니다</h4>
                        <br>
                        <h4>자세히 보기</h4>
                    </div>
                </div>
            </div>
            <!--배너3-->
            <div class="carousel-item">
                <!--배너 좌우 이동 버튼-->

                <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
                <!--슬라이드 페이지 표시-->
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <!--배너 이미지-->
                <svg class="banner" viewBox="0 60 900 340" width="1670" height="630">
                    <image href="../assets/banner3.jpg" />
                </svg>
                <div class="container">
                    <div class="carousel-caption">
                        <h1>실시간 항공권 비교하고</h1>
                        <h2>특가 항공권 예약하세요!</h2>
                        <br>
                        <h4>자세히 보기</h4>
                    </div>
                </div>
            </div>
        </span>

        <!--항공 예약 박스-->
        <!--------------------------------------------------------------------------------->

        <form class="res-form">
            <div class="date-area-select">
                <button type="button" @click="toggleRoundTrip" class="btn-field" id="resRoundTrip">왕복</button>
                <button type="button" @click="toggleOneWay" class="btn-field" id="resOneWay">편도</button>
                <br>
                <br>
                <div class="FromTo">
                    <!--FromArea-->
                    <img type="button" src="../assets/FromArea/seoul.jpg" @click="fromAreaPopUp" width="200">
                    <div v-if="fromAreaView == true" class="fromAreaView" :class="{ active : fromAreaView }">
                        <FromArea @close-popup="fromAreaPopUp"></FromArea>
                    </div>

                    <!--Area Change-->
                    <img type="button" class="ppg-refresh" src="../assets/change.png" @click="change" />
                    <!--ToArea-->
                    <img type="button" src="../assets/ToArea/main.jpg" @click="toAreaPopUp" width="200">
                    <div v-if="toAreaView == true" class="toAreaView" :class="{ active : toAreaView }">
                        <ToArea @close-popup="toAreaPopUp"></ToArea>
                    </div>
                </div>
                <br>
                <br>
                <hr>
                <Datepicker v-if="show1" class="datePicker" v-model="date" placeholder="                              가는날 ~ 오는날" modelAuto range />
                <Datepicker v-if="show2" class="datePicker" v-model="oneWay" placeholder="                                  탑승일 선택" />
            </div>

            <!--승객수 팝업-->
            <button type="button" class="btn-field" id="resPassenger" @click="popUp">승객 수</button>

            <div class="popup-view" :class="{ active : popupView }">
                <PopUp @close-popup="popUp()" :count="count" @update-count="updateCount"></PopUp>

            </div>
            {{count}}
            <input type="text" v-model="count">
            <div>

            </div>
            <!--좌석 선택-->

            <select id="inputState" class="form-select">
                <option selected>좌석 등급</option>
                <option>일반석</option>
                <option>이코노미</option>
                <option>비즈니스</option>
            </select>

            <input type="submit" value="항공편 검123색" class="submit-btn">

        </form>

    </div>
</div>
<!--------------------------------------------------------------------------------->

<!--Test Zone-->

<!-- 조회버튼 -->
<div class="refer">
    <!--예약 조회 버튼-->
    <button type="button" class="btn btn-lg btn-default">
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
        </svg>
        <a href="">예약 조회</a>
    </button>
    <!-- 버튼-->
    <span>I</span>
    <button type="button" class="btn btn-lg btn-default" id="checkIn">
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-airplane" viewBox="0 0 16 16">
            <path d="M6.428 1.151C6.708.591 7.213 0 8 0s1.292.592 1.572 1.151C9.861 1.73 10 2.431 10 3v3.691l5.17 2.585a1.5 1.5 0 0 1 .83 1.342V12a.5.5 0 0 1-.582.493l-5.507-.918-.375 2.253 1.318 1.318A.5.5 0 0 1 10.5 16h-5a.5.5 0 0 1-.354-.854l1.319-1.318-.376-2.253-5.507.918A.5.5 0 0 1 0 12v-1.382a1.5 1.5 0 0 1 .83-1.342L6 6.691V3c0-.568.14-1.271.428-1.849Zm.894.448C7.111 2.02 7 2.569 7 3v4a.5.5 0 0 1-.276.447l-5.448 2.724a.5.5 0 0 0-.276.447v.792l5.418-.903a.5.5 0 0 1 .575.41l.5 3a.5.5 0 0 1-.14.437L6.708 15h2.586l-.647-.646a.5.5 0 0 1-.14-.436l.5-3a.5.5 0 0 1 .576-.411L15 11.41v-.792a.5.5 0 0 0-.276-.447L9.276 7.447A.5.5 0 0 1 9 7V3c0-.432-.11-.979-.322-1.401C8.458 1.159 8.213 1 8 1c-.213 0-.458.158-.678.599Z" />
        </svg>
        <a href="">체크인</a>
    </button>
    <span>I</span>
    <!--항공편 조회 버튼-->
    <button type="button" class="btn btn-lg btn-default">&nbsp;
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
            <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
            <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
        </svg>
        <a href="">항공편 조회</a>
    </button>

</div>
<br>
<hr>

<!-- 추천 여행지 -->

<div class="recommendProduct">
    <h3>지금 떠나기 좋은 여행</h3><br>
    {{products}}
    <input type="text" name="아이디" class="inputValues" id="id" v-model="test" />
    <button type="button" @click="sendData()">보내기</button>
    <div class="">

        <div class="container">

            <div class="row row-cols-4 row-cols-sm-2 row-cols-md-4 g-3">
                <!--추천 여행지 1-->
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="../assets/rec1.jpg" width="300" height="400" />
                        <div class="card-body">
                            <h5>서울/인천- 두바이</h5>
                            <h6>일반석 왕복</h6>
                            <h6>KRW 1,158,500 ~</h6>
                            <br>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">자세히 보기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--추천 여행지 2-->
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="../assets/rec2.jpg" width="300" height="400" />
                        <div class="card-body">
                            <h5>서울/인천- 후쿠오카</h5>
                            <h6>일반석 왕복</h6>
                            <h6>KRW 704,200 ~</h6>
                            <br>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">자세히 보기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--추천 여행지 3-->
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="../assets/rec3.jpg" width="300" height="400" />
                        <div class="card-body">
                            <h5>서울/인천- 싱가포르</h5>
                            <h6>일반석 왕복</h6>
                            <h6>KRW 591,800 ~</h6>
                            <br>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">자세히 보기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--추천 여행지 4-->
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="../assets/rec4.jpg" width="300" height="400" />
                        <div class="card-body">
                            <h5>서울/인천- 바르셀로나</h5>
                            <h6>일반석 왕복</h6>
                            <h6>KRW 909,200 ~</h6>
                            <br>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">자세히 보기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--추천 여행지 5-->
                <div class="col">
                    <div class="card shadow-sm">
                        <img src="../assets/rec5.jpg" width="300" height="400" />
                        <div class="card-body">
                            <h5>서울/인천- 치앙마이</h5>
                            <h6>일반석 왕복</h6>
                            <h6>KRW 1,546,800 ~</h6>
                            <br>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary">자세히 보기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <br>
    <hr>

    <div class="row featurette">
        <div class="col-md-7">
            <br>
            <h1 class="featurette-heading">복잡한 여행을 Easy하게!</h1><br>
            <h3 class="text-muted">전자서식을 작성하시면 여행이 한결 여유로워집니다.</h3><br>
            <p class="lead">자세히 보기</p>
        </div>
        <div class="col-md-5">
            <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500">
                <rect width="100%" height="100%" />
                <image href="../assets/banner1.jpg" height="100%" width="100%" />
            </svg>

        </div>
    </div>
    <hr>
    <!--여행 도우미-->
    <div class="tip">
        <h4>여행의 완성을 위한 경험</h4>
    </div>
    <div class="side">

        <!--마일리지 카드-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
            </svg>
            &nbsp; 예약 조회 &nbsp; ㅣ
        </button>
        <!--호텔-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-airplane" viewBox="0 0 16 16">
                <path d="M6.428 1.151C6.708.591 7.213 0 8 0s1.292.592 1.572 1.151C9.861 1.73 10 2.431 10 3v3.691l5.17 2.585a1.5 1.5 0 0 1 .83 1.342V12a.5.5 0 0 1-.582.493l-5.507-.918-.375 2.253 1.318 1.318A.5.5 0 0 1 10.5 16h-5a.5.5 0 0 1-.354-.854l1.319-1.318-.376-2.253-5.507.918A.5.5 0 0 1 0 12v-1.382a1.5 1.5 0 0 1 .83-1.342L6 6.691V3c0-.568.14-1.271.428-1.849Zm.894.448C7.111 2.02 7 2.569 7 3v4a.5.5 0 0 1-.276.447l-5.448 2.724a.5.5 0 0 0-.276.447v.792l5.418-.903a.5.5 0 0 1 .575.41l.5 3a.5.5 0 0 1-.14.437L6.708 15h2.586l-.647-.646a.5.5 0 0 1-.14-.436l.5-3a.5.5 0 0 1 .576-.411L15 11.41v-.792a.5.5 0 0 0-.276-.447L9.276 7.447A.5.5 0 0 1 9 7V3c0-.432-.11-.979-.322-1.401C8.458 1.159 8.213 1 8 1c-.213 0-.458.158-.678.599Z" />
            </svg>
            &nbsp; 호텔 &nbsp; ㅣ
        </button>
        <!--렌터카-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
                <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
            </svg>
            &nbsp; 렌터카 &nbsp; ㅣ
        </button>
        <!--기내 면세점-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
                <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
            </svg>
            &nbsp; 기내 면세점 &nbsp; ㅣ
        </button>
        <!--여행자 보험-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
                <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
            </svg>
            &nbsp; 여행자 보험 &nbsp; ㅣ
        </button>
        <!--여행 상품-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
                <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
            </svg>
            &nbsp; 여행 상품
        </button>

    </div>

</div>

<!-- FOOTER -->
<footer class="container">
    <p class="float-end"><a href="#">Back to top</a></p>
    <p>&copy; 2017–2022 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
</footer>
</template>

<script>
import Datepicker from '@vuepic/vue-datepicker';
import PopUp from './PopUp.vue';
import FromArea from './FromArea.vue';
import ToArea from './ToArea.vue';
import axios from 'axios';

export default {
    name: 'HelloWorld',
    components: {
        Datepicker,
        PopUp,
        FromArea,
        ToArea,
    },
    props: ['cityOfChild'],
    data() {
        return {
            isModalViewed: false,
            toBtn: "",
            fromBtn: "",
            date: null,
            oneWay: null,
            popupView: false,
            show1: true,
            show2: false,
            product: [],
            count: 1,
            fromAreaView: false,
            toAreaView: false,
            showModal: false, //true일 때 모달창 보여짐
            modalDatas: [], //모달에 보낼 데이터 배열
            products: [],
            test: ""
        }
    },
    methods: {
        plus() {
            this.count++;
        },
        minus() {
            this.count--;
        },
        change() {
            let temp = this.toBtn;
            this.toBtn = this.fromBtn;
            this.fromBtn = temp;
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
        closePopUp() {

        },
        toggleRoundTrip() {
            this.show1 = true;
            this.show2 = false;
        },
        toggleOneWay() {
            this.show1 = false;
            this.show2 = true;
        },
        getData() {
            axios.get('api/product/all')
                .then((response) => {
                    this.products = response.data
                })
        },
        sendData() {
            axios
                .post("/api/product/add", {
                    test: this.test,
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

        updateCount(count) {
            this.count = count;
        },

    },

    created() {
            this.getData();
        },
        
}
</script>

<style>
h5 {
    font-size: 18px;
    margin-top: 20px;

}

a {
    text-decoration: none;
    color: rgb(77, 77, 77);
    font-size: 24px;
    margin-left: 20px;
}

a:hover,
a:visited,
a:active {
    color: rgb(77, 77, 77);
}

img {
    border-radius: 10px;
    box-shadow: 7px 7px 15px 0px rgb(39, 39, 39), 8px 8px 16px -10px rgba(0, 0, 0, .15);
}

body {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
}

.carousel-caption {
    margin-left: 600px;
}

.carousel-indicators {
    margin-left: 855px;
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
}

.carousel-control-prev {
    width: 40px;
    /* 사이즈 */
    height: 40px;
    /* 사이즈 */
    border-top: 8px solid white;
    /* 선 두께 */
    border-right: 8px solid white;
    /* 선 두께 */
    transform: rotate(225deg);
    /* 각도 */

    top: 50%;
    left: 700px;

}

.carousel-control-next {
    width: 40px;
    /* 사이즈 */
    height: 40px;
    /* 사이즈 */
    border-top: 8px solid white;
    /* 선 두께 */
    border-right: 8px solid white;
    /* 선 두께 */
    transform: rotate(45deg);
    /* 각도 */
    position: absolute;
    top: 50%;
    right: 50px;
}

svg,
span {
    color: rgb(77, 77, 77);
}

.refer {
    text-align: center;
}

.refer button {
    width: 400px;
}

.refer span {

    font-size: 30px;
}

.recommendProduct {
    margin-top: 40px;
    color: rgb(139, 139, 139);
}

.tip {
    margin-top: 30px;
    margin-left: 100px;

}

.side {
    margin-top: 10px;
    margin-bottom: 50px;
    text-align: center;
}

.carousel-inner {
    height: 800px;
}

.res-form {
    width: 650px;
    height: 750px;
    box-shadow: 7px 7px 15px 0px rgb(123, 123, 123), 8px 8px 16px -10px rgba(0, 0, 0, .15);
    border: 2px solid rgb(193, 188, 188);
    border-radius: 20px;
    background-color: rgba(252, 252, 252, 0.957);
    margin: 0 auto;
    margin-top: 10px;
    margin-left: 10px;
    position: absolute;
    padding: 15px;
}

.datePicker,
.btn-field,
.form-select,
.submit-btn {
    border: none;
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

}

.FromTo input {
    width: 200px;
    height: 100px;
    border: none;
    background-color: none;
    text-align: center;
    cursor: pointer;
}

.submit-btn {
    width: 300px;
    height: 50px;
}

.date-area-select {
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
}

#resFrom,
#resTo {
    width: 100px;
}

#resPassenger,
#inputState {
    width: 565px;
    height: 70px;
    display: table;
    margin-left: auto;
    margin-right: auto;
    color: #999;
    background: linear-gradient(135deg, rgba(230, 230, 230, 1) 0%, rgba(246, 246, 246, 1) 100%);
    background: linear-gradient(135deg, rgb(255, 255, 255) 0%, rgb(237, 237, 237) 100%);
}

#inputState {
    padding-left: 50px;
    font-size: 22px;
}

.fromBtn,
.toBtn {
    width: 150px;
    height: 100px;
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
    text-align: center;
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
    z-index: 1;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 60%;
    height: 40%;
    text-align: center;
    border-radius: 15px;
    background-color: rgb(246, 246, 246);
    box-shadow: 2px 2px 10px lightgrey;
}
</style>
