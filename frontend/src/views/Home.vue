<template>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, 그리고 Bootstrap 기여자들">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Carousel Template · Bootstrap v5.1</title>
    
    <link rel="canonical" href="https://getbootstrap.kr/docs/5.1/examples/carousel/">
    
    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
    <meta name="theme-color" content="#7952b3">
    
  
 
    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="3" aria-label="Slide 4"></button>
        </div>
    
        <div class="carousel-inner">
            <!--배너1-->
            <div class="carousel-item active">
                <svg class="banner" viewBox="0 100 900 190" width="1520" height="600">
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
                <svg class="banner" viewBox="0 100 900 190" width="1520" height="600">
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
                <svg class="banner" viewBox="0 100 900 190" width="1520" height="600">
                    <image href="../assets/banner2.jpg" />
                </svg>
                <div class="container">
                    <div class="carousel-caption">
                        <h1>아름다운 야경과 낭만의 도시</h1>
                        <p>10월 3일 헝가리 부다페스트 취항합니다</p>
                        <br>
                        <p>자세히 보기</p>
                    </div>
                </div>
            </div>
            <!--배너4-->
            <div class="carousel-item">
                <svg class="banner" viewBox="0 100 900 190" width="1520" height="600">
                    <image href="../assets/banner2.jpg" />
                </svg>
                <div class="container">
                    <div class="carousel-caption">
                        <h1>아름다운 야경과 낭만의 도시</h1>
                        <p>10월 3일 헝가리 부다페스트 취항합니다</p>
                        <br>
                        <p>자세히 보기</p>
                    </div>
                </div>
            </div>
    
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    
    <!-- 조회버튼 -->
    <div class="refer">
        <!--예약 조회 버튼-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
            </svg>
            &nbsp; 예약 조회 &nbsp; ㅣ
        </button>
        <!-- 버튼-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-airplane" viewBox="0 0 16 16">
                <path d="M6.428 1.151C6.708.591 7.213 0 8 0s1.292.592 1.572 1.151C9.861 1.73 10 2.431 10 3v3.691l5.17 2.585a1.5 1.5 0 0 1 .83 1.342V12a.5.5 0 0 1-.582.493l-5.507-.918-.375 2.253 1.318 1.318A.5.5 0 0 1 10.5 16h-5a.5.5 0 0 1-.354-.854l1.319-1.318-.376-2.253-5.507.918A.5.5 0 0 1 0 12v-1.382a1.5 1.5 0 0 1 .83-1.342L6 6.691V3c0-.568.14-1.271.428-1.849Zm.894.448C7.111 2.02 7 2.569 7 3v4a.5.5 0 0 1-.276.447l-5.448 2.724a.5.5 0 0 0-.276.447v.792l5.418-.903a.5.5 0 0 1 .575.41l.5 3a.5.5 0 0 1-.14.437L6.708 15h2.586l-.647-.646a.5.5 0 0 1-.14-.436l.5-3a.5.5 0 0 1 .576-.411L15 11.41v-.792a.5.5 0 0 0-.276-.447L9.276 7.447A.5.5 0 0 1 9 7V3c0-.432-.11-.979-.322-1.401C8.458 1.159 8.213 1 8 1c-.213 0-.458.158-.678.599Z" />
            </svg>
            &nbsp; 체크인 &nbsp; ㅣ
        </button>
        <!--항공편 조회 버튼-->
        <button type="button" class="btn btn-lg btn-default">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-calendar-check" viewBox="0 0 16 16">
                <path d="M10.854 7.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7.5 9.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
                <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
            </svg>
            &nbsp; 항공편 조회
        </button>
    
    </div>
    <br>
    <hr>
    
    <!-- 추천 여행지 -->
    
    <div class="recommendProduct">
        <h3>지금 떠나기 좋은 여행</h3>
        <br>
        <div class="row">
    
            <!-- 추천 여행지 1 -->
    
            <div class="col-lg-3">
                <img src="../assets/rec1.jpg" width="300" height="400" />
                <h5>서울/인천- 두바이</h5>
                <h6>일반석 왕복</h6>
                <h6>KRW 1,158,500 ~</h6>
                <p><a class="btn btn-secondary" href="#">자세히 보기 &raquo;</a></p>
            </div>
    
            <!-- 추천 여행지 2 -->
            <div class="col-lg-3">
    
                <img src="../assets/rec2.jpg" width="300" height="400" />
                <h5>서울/인천- 두바이</h5>
                <h6>일반석 왕복</h6>
                <h6>KRW 340,600 ~</h6>
                <p><a class="btn btn-secondary" href="#">자세히 보기 &raquo;</a></p>
    
            </div>
            <!-- 추천 여행지 3 -->
            <div class="col-lg-3">
                <img src="../assets/rec3.jpg" width="300" height="400" />
                <h5>서울/인천- 후쿠오카</h5>
                <h6>일반석 왕복</h6>
                <h6>KRW 704,200 ~</h6>
                <p><a class="btn btn-secondary" href="#">자세히 보기 &raquo;</a></p>
            </div>
    
            <!-- 추천 여행지 4 -->
            <div class="col-lg-3">
                <img src="../assets/rec4.jpg" width="300" height="400" />
                <h5>서울/인천- 바르셀로나</h5>
                <h6>일반석 왕복</h6>
                <h6>KRW 909,200 ~</h6>
                <p><a class="btn btn-secondary" href="#">자세히 보기 &raquo;</a></p>
            </div>
    
            <!-- 추천 여행지 5 -->
            <div class="col-lg-3">
                <img src="../assets/rec5.jpg" width="300" height="400" />
                <h5>서울/인천- 치앙마이</h5>
                <h6>일반석 왕복</h6>
                <h6>KRW 1,546,800 ~</h6>
                <p><a class="btn btn-secondary" href="#">자세히 보기 &raquo;</a></p>
            </div>
    
        </div>
    
        <hr class="featurette-divider">
    
        <div class="row featurette">
            <div class="col-md-7">
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
    
        <!-- /END THE FEATURETTES -->
    
    </div><!-- /.container -->
    
    <!-- FOOTER -->
    <footer class="container">
        <p class="float-end"><a href="#">Back to top</a></p>
        <p>&copy; 2017–2022 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
    </footer>
    </template>
    
    <script>
    export default {
    
        name: 'HelloWorld',
        data() {
            return {
                name1: "Dio1",
                name2: "Dio2",
            }
        },
        props: {
            msg: String
        },
        method: {
            plus() {
                this.count++;
            },
            minus() {
                this.count--;
            }
        }
    }
    </script>
    
    <style>
    h1,
    h4 {
        color: black;
    }
    
    h5 {
        font-size: 18px;
        margin-top: 20px;
    
    }
    
    img {
        border-radius: 15px;
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
        margin-top: 15px;
    }
    
    .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
    }
    
    @media (min-width: 768px) {
        .bd-placeholder-img-lg {
            font-size: 3.5rem;
        }
    }
    
    .carousel-control-prev {
        width: 50px;
        /* 사이즈 */
        height: 50px;
        /* 사이즈 */
        border-top: 10px solid white;
        /* 선 두께 */
        border-right: 10px solid white;
        /* 선 두께 */
        transform: rotate(225deg);
        /* 각도 */
        position: absolute;
        top: 50%;
        left: 70px;
    }
    
    .carousel-control-next {
        width: 50px;
        /* 사이즈 */
        height: 50px;
        /* 사이즈 */
        border-top: 10px solid white;
        /* 선 두께 */
        border-right: 10px solid white;
        /* 선 두께 */
        transform: rotate(45deg);
        /* 각도 */
        position: absolute;
        top: 46%;
        right: 70px;
    }
    
    .refer {
        margin-top: 50px;
        text-align: center;
    }
    
    .recommendProduct {
        margin-top: 50px;
        margin-left: 50px;
    }
    .tip{
        margin-top: 30px;
        margin-left: 100px;
        
    }
    .side{
        margin-top: 10px;
        margin-bottom: 50px;
        text-align: center;
    }
    </style>
    