<template>
    <div class="carousel-wrapper">
      <VueSlickCarousel v-bind="slickOptions">
        <div v-for="i in 5" :key="i" class="img-warpper">
          <img src="../assets/rec1.jpg" />
        </div>
      </VueSlickCarousel>
    </div>
  </template>
  
  <script>


  export default {
    name:"HelloWorld",
    components: {

    },
    data() {
      return {
        slickOptions: {
          slidesToShow: 3
        }
      }
    }
  }
  </script>
  
  <style>
  .carousel-wrapper {
    padding: 40px;
    height: 150px;
  }
  .img-warpper img {
    margin: auto;
    width: 200px;
    height: 100px;
    background-image: linear-gradient(gray 100%, transparent 0);
  }
  .slick-next:before, .slick-prev:before {
    color: black;
  }
  </style>
  