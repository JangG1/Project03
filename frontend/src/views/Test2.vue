<template>
    <div id="searchresult">
      <div class="result-title">
        총 {{$store.getters.getFilteredProduct(keyword).length}}개의 상품이 검색되었습니다.
      </div>
      
      <div class="result-items">
        <div
        class="resultitem"
        :key="index"
        v-for="(product, index) in $store.getters.getFilteredProduct(keyword)"
        @click="detailshow(index)"
      >
        <div class="product-img">
          <img v-bind:src="product.productimg" />
        </div>
        <div class="product-info">
          <li class="product-info-title">{{ product.title }}</li>
          <li class="product-info-price">{{ product.price }}원</li>
        </div>
      </div>
      </div>
    </div>
  </template>
  <script>
  export default {
    name: "HelloWorld",
    props: ['keyword'], //상위 컴포넌트인 SearchPage.vue로부터 데이터 받음
    data() {
      return {
      }
    },
    methods: {}
  }
  </script>