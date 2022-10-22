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
                <svg class="banner" viewBox="0 60 890 300" width="1870" height="630">
                    <image href="../assets/banner1.jpg" />
                </svg>
                <div class="container">
                    <div class="carousel-caption">
                        <h3>지금 갈 수 있는 여행지는 어디?</h3>
                        <br>
                        <h4>자세히 보기</h4>
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
                <svg class="banner" viewBox="0 60 890 300" width="1870" height="630">
                    <image href="../assets/banner2.jpg" />
                </svg>
                <div class="container">
                    <div class="carousel-caption">
                        <h3>아름다운 야경과 낭만의 도시</h3>
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
                <svg class="banner" viewBox="0 60 890 300" width="1870" height="630">
                    <image href="../assets/banner3.jpg" />
                </svg>
                <div class="container">
                    <div class="carousel-caption">
                        <h3>실시간 항공권 비교하고</h3>
                        <h4>특가 항공권 예약하세요!</h4>
                        <br>
                        <h4>자세히 보기</h4>
                    </div>
                </div>
            </div>
        </span>

        <!--항공 예약 박스-->
        <!--------------------------------------------------------------------------------->

        <form class="resForm">
            <div class="date-area-select">
                <button type="button" @click="toggleRoundTrip" class="btn-field" id="resRoundTrip">왕복</button>
                <button type="button" @click="toggleOneWay" class="btn-field" id="resOneWay">편도</button>
                <br>
                <br>
                <div class="FromTo">
                    <!--FromArea-->
                    <img type="button" class="fromBtn" src="../assets/FromArea/seoul.jpg" @click="fromAreaPopUp" width="200">
                    <div v-if="fromAreaView == true" class="fromAreaView" :class="{ active : fromAreaView }">
                        <FromArea @close-popup="fromAreaPopUp"></FromArea>
                    </div>
                    <!--Area Change-->
                    <img type="button" class="ppg-refresh" src="../assets/change.png" @click="change" />
                    <!--ToArea-->
                    <img type="button" class="toBtn" src="../assets/ToArea/main.jpg" @click="toAreaPopUp" width="200">
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
            <button type="button" class="btn-field" id="resPassenger" @click="popUp">
                <span>승객 수</span>{{space}}<span>{{count}}명</span>
            </button>
            <div class="popup-view" :class="{ active : popupView }">
                <HeadCount @close-popup="popUp()" :count="count" @update-count="updateCount"></HeadCount>
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

            <input type="submit" value="항공편 검색" class="submit-btn">

        </form>

    </div>
</div>
<!--------------------------------------------------------------------------------->

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
                <button type="button" class="btn btn-lg btn-default" id="checkIn">
                    <a href="">자세히 보기 </a>
                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z" />
                    </svg>
                </button>
            </div>
        </div>
        <div>
            <img class="travelImg" src="../assets/travel1.jpg" />
        </div>
    </div>
</div>

<!--여행 도우미-->
<div class="part4">
    <hr>
    <div class="side">
        <h2>여행의 완성을 위한 경험</h2>
    </div>
    <div class="sideTip">

        <!--마일리지 카드-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
            </svg>
            <a href="">예약 조회</a>
        </button>
        
        <!--호텔-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-hospital" viewBox="0 0 16 16">
                <path d="M8.5 5.034v1.1l.953-.55.5.867L9 7l.953.55-.5.866-.953-.55v1.1h-1v-1.1l-.953.55-.5-.866L7 7l-.953-.55.5-.866.953.55v-1.1h1ZM13.25 9a.25.25 0 0 0-.25.25v.5c0 .138.112.25.25.25h.5a.25.25 0 0 0 .25-.25v-.5a.25.25 0 0 0-.25-.25h-.5ZM13 11.25a.25.25 0 0 1 .25-.25h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5a.25.25 0 0 1-.25-.25v-.5Zm.25 1.75a.25.25 0 0 0-.25.25v.5c0 .138.112.25.25.25h.5a.25.25 0 0 0 .25-.25v-.5a.25.25 0 0 0-.25-.25h-.5Zm-11-4a.25.25 0 0 0-.25.25v.5c0 .138.112.25.25.25h.5A.25.25 0 0 0 3 9.75v-.5A.25.25 0 0 0 2.75 9h-.5Zm0 2a.25.25 0 0 0-.25.25v.5c0 .138.112.25.25.25h.5a.25.25 0 0 0 .25-.25v-.5a.25.25 0 0 0-.25-.25h-.5ZM2 13.25a.25.25 0 0 1 .25-.25h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5a.25.25 0 0 1-.25-.25v-.5Z" />
                <path d="M5 1a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v1a1 1 0 0 1 1 1v4h3a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1h3V3a1 1 0 0 1 1-1V1Zm2 14h2v-3H7v3Zm3 0h1V3H5v12h1v-3a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3Zm0-14H6v1h4V1Zm2 7v7h3V8h-3Zm-8 7V8H1v7h3Z" />
            </svg>
            <a href="">호텔</a>
        </button>
        <!--렌터카-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-car-front" viewBox="0 0 16 16">
                <path d="M4 9a1 1 0 1 1-2 0 1 1 0 0 1 2 0Zm10 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0ZM6 8a1 1 0 0 0 0 2h4a1 1 0 1 0 0-2H6ZM4.862 4.276 3.906 6.19a.51.51 0 0 0 .497.731c.91-.073 2.35-.17 3.597-.17 1.247 0 2.688.097 3.597.17a.51.51 0 0 0 .497-.731l-.956-1.913A.5.5 0 0 0 10.691 4H5.309a.5.5 0 0 0-.447.276Z" />
                <path fill-rule="evenodd" d="M2.52 3.515A2.5 2.5 0 0 1 4.82 2h6.362c1 0 1.904.596 2.298 1.515l.792 1.848c.075.175.21.319.38.404.5.25.855.715.965 1.262l.335 1.679c.033.161.049.325.049.49v.413c0 .814-.39 1.543-1 1.997V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.338c-1.292.048-2.745.088-4 .088s-2.708-.04-4-.088V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.892c-.61-.454-1-1.183-1-1.997v-.413a2.5 2.5 0 0 1 .049-.49l.335-1.68c.11-.546.465-1.012.964-1.261a.807.807 0 0 0 .381-.404l.792-1.848ZM4.82 3a1.5 1.5 0 0 0-1.379.91l-.792 1.847a1.8 1.8 0 0 1-.853.904.807.807 0 0 0-.43.564L1.03 8.904a1.5 1.5 0 0 0-.03.294v.413c0 .796.62 1.448 1.408 1.484 1.555.07 3.786.155 5.592.155 1.806 0 4.037-.084 5.592-.155A1.479 1.479 0 0 0 15 9.611v-.413c0-.099-.01-.197-.03-.294l-.335-1.68a.807.807 0 0 0-.43-.563 1.807 1.807 0 0 1-.853-.904l-.792-1.848A1.5 1.5 0 0 0 11.18 3H4.82Z" />
            </svg>
            <a href="">렌터카</a>
        </button>
        <!--기내 면세점-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-bag-heart" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M10.5 3.5a2.5 2.5 0 0 0-5 0V4h5v-.5Zm1 0V4H15v10a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V4h3.5v-.5a3.5 3.5 0 1 1 7 0ZM14 14V5H2v9a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1ZM8 7.993c1.664-1.711 5.825 1.283 0 5.132-5.825-3.85-1.664-6.843 0-5.132Z" />
            </svg>
            <a href="">기내 면세점</a>
        </button>
        <!--여행자 보험-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-shield-check" viewBox="0 0 16 16">
                <path d="M5.338 1.59a61.44 61.44 0 0 0-2.837.856.481.481 0 0 0-.328.39c-.554 4.157.726 7.19 2.253 9.188a10.725 10.725 0 0 0 2.287 2.233c.346.244.652.42.893.533.12.057.218.095.293.118a.55.55 0 0 0 .101.025.615.615 0 0 0 .1-.025c.076-.023.174-.061.294-.118.24-.113.547-.29.893-.533a10.726 10.726 0 0 0 2.287-2.233c1.527-1.997 2.807-5.031 2.253-9.188a.48.48 0 0 0-.328-.39c-.651-.213-1.75-.56-2.837-.855C9.552 1.29 8.531 1.067 8 1.067c-.53 0-1.552.223-2.662.524zM5.072.56C6.157.265 7.31 0 8 0s1.843.265 2.928.56c1.11.3 2.229.655 2.887.87a1.54 1.54 0 0 1 1.044 1.262c.596 4.477-.787 7.795-2.465 9.99a11.775 11.775 0 0 1-2.517 2.453 7.159 7.159 0 0 1-1.048.625c-.28.132-.581.24-.829.24s-.548-.108-.829-.24a7.158 7.158 0 0 1-1.048-.625 11.777 11.777 0 0 1-2.517-2.453C1.928 10.487.545 7.169 1.141 2.692A1.54 1.54 0 0 1 2.185 1.43 62.456 62.456 0 0 1 5.072.56z" />
                <path d="M10.854 5.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
            </svg>
            <a href="">여행자 보험</a>
        </button>
        <!--여행 상품-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-journal-text" viewBox="0 0 16 16">
                <path d="M5 10.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z" />
                <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z" />
                <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z" />
            </svg>
            <a href="">여행 상품</a>
        </button>

    </div>

    <hr>
</div>

<!-- FOOTER -->
<img class="footLogo" src="../assets/Logo.png"><span class="footLogoName"> Fastrip</span>
<footer class="container">
    <p>&copy; 2022 Company, Fastrip</p>
    <a class="topBtn" href="">Top</a>
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
    props: ['cityOfChild'],
    data() {
        return {
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
            test: "",
            test1: "",
            test2: "",
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
        toggleRoundTrip() {
            this.show1 = true;
            this.show2 = false;
        },
        toggleOneWay() {
            this.show1 = false;
            this.show2 = true;
        },
        getData() {
            axios.get('/prod/all')
                .then((response) => {
                    this.products = response.data
                })
        },
        updateCount(count) {
            this.count = count;
        },

    },

    mounted() {
        this.getData()

    }

}
</script>

<style>
.part2 {
    background-color: rgb(236, 239, 244);
}

.part4 {
    /*background-color: rgb(241, 233, 231);*/
    background-image: url( "../assets/part4.jpg" );
}

h1,
h2 {
    font-size: 60px;
    color: black;
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

.carousel-caption {
    margin-left: 600px;

}

.carousel-indicators {
    margin-left: 880px;
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
    color: rgb(139, 139, 139);
}

.travelImg {
    width: 1300px;
    height: 650px;
    border-radius: 8px;
    margin-left: 16%;
    margin-bottom: 12%;
}

.travelTip {
    text-align: center;
    margin-bottom: 5%;
}

.side {
    text-align: center;
    margin-top: 18%;
}

.sideTip {
    height: 150px;
    border: 2px solid #999;
    border-radius: 8px;
    width: 70%;
    margin-top: 6%;
    margin-bottom: 18%;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
}

.sideTip button {
    margin-top: 3.5%;    
}

footer{
    display: flex;    
}

.topBtn{
    margin-left: 70%;
}

.carousel-inner {
    height: 800px;
}

.footLogo{
    margin-left: 15%;
    width: 100px;
    height: 70px;
}

.footLogoName{    
    font-size: 40px;
    font-family: "NanumBarunGothicBold";
}

.resForm {
    width: 650px;
    height: 750px;
    box-shadow: 7px 7px 15px 0px rgb(123, 123, 123), 8px 8px 16px -10px rgba(0, 0, 0, .15);
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

.btn-field span {
    display: contents;

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
    border: 4px solid rgb(225, 225, 225);
    border-radius: 20px;
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
</style>
