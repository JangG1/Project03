<template>
<div class="recommendProduct">
    <h3>지금 떠나기 좋은 여행</h3><br>

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
</div>
</template>

    
<script>
export default {
    name: 'HelloWorld',
    components: {},
    props: [""],
    data() {
        return {

        }
    },
    methods: {

    },

}
</script>

    
<style>
.card {
    border: 4px solid rgb(225, 225, 225);
    border-radius: 20px;        
    
}
</style>
