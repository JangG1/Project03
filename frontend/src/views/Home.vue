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
                <svg class="banner" viewBox="0 60 890 310">
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
                <svg class="banner" viewBox="0 60 890 310">
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
                <svg class="banner" viewBox="0 60 890 310">
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
    <button type="button" class="btn btn-lg btn-default">
        <img src="../assets/magnifier.jpg" width="30" height="30">
        <a href="">예약 조회</a>
    </button>
    <!-- 버튼-->
    <img src="../assets/vertical.jpg" width="20" height="40">
    <button type="button" class="btn btn-lg btn-default" id="checkIn">
        <img src="../assets/check.png" width="30" height="30">
        <a href="">체크인</a>
    </button>
    <img src="../assets/vertical.jpg" width="20" height="40">
    <!--항공편 조회 버튼-->
    <button type="button" class="btn btn-lg btn-default">&nbsp;
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
    <div class="side">
        <h2>여행의 완성을 위한 경험</h2>
    </div>
    <div class="sideTip">
        <div class="sideTip1">

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
        </div>
        <div class="sideTip2">
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
    </div>

</div>

<!-- FOOTER -->

<div class="footLogoName">
    <img class="footLogo" src="../assets/Logo.png">
    Fastrip
</div>
<footer class="footer">
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

.container h4{
    color: white;
}
.carousel-caption {
    margin-left: 600px;
    color: white;
}

.carousel-indicators {
    margin-left: 915px;
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
    margin-left: 3%;
    margin-top: 10%;
}

.side svg {
    height: 200px;
}

.sideTip {
    width: 650px;
    height: 210px;
    margin-left: 3%;
    margin-top: 3%;
    border: 4px solid white;
    border-radius: 10px;
}

.sideTip1 {
    height: 80px;
    border-radius: 8px;
    width: 30%;
    margin-left: 2%;
    margin-top: 5%;
    text-align: center;
}

.sideTip2 {
    margin-left: 2%;
}

.sideTip1 button {
    margin-top: 3.5%;
    color: white;
}

.sideTip1 button:hover,
.sideTip1 button:visited,
.sideTip1 button:active {
    color: white;
}

.sideTip1 a:hover,
.sideTip1 a:visited,
.sideTip1 a:active {
    color: white;
}

.sideTip2 button {
    color: white;
}

.sideTip2 button:hover,
.sideTip2 button:visited,
.sideTip2 button:active {
    color: white;
}

.sideTip2 a:hover,
.sideTip2 a:visited,
.sideTip2 a:active {
    color: white;
}

.footer {
    display: flex;
    margin-left: 280px;
    margin-bottom: 30px;
    margin-top: 20px;
}

.topBtn {
    margin-left: 1250px;
    margin-bottom: 30px;
}

.carousel-inner {
    height: 800px;
}

.footLogo {
    margin-left: 270px;
    width: 100px;
    height: 70px;
}

.footLogoName {
    margin-top: 20px;
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
