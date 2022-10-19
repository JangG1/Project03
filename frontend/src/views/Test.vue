<template>
    <div class="image-album">
      <div class="images">
        <img
          class="image"
          v-for="imageUrl in imageUrls"
          v-bind:key="imageUrl.index"
          v-bind:src="imageUrl"
        />
      </div>
      <div v-if="imageUrls.length > 1" class="image-circle-wrapper">
        <div
          class="image-circle"
          v-for="(imageUrl, index) in imageUrls"
          v-bind:key="imageUrl.index"
          v-bind:class="{ activeImg: index === curPos }"
        ></div>
      </div>
    </div>

    <button class="carousel-control-prev" type="button" @click="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>

    </button>
    <button class="carousel-control-next" type="button" @click="next">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>

    </button>
  </template>
  
  <script>
  export default {
    name: "imageSlider",
    data() {
      return {
        curPos: 0,
        postion: 0,
        start_x: 0,
        end_x: 0,
        IMAGE_WIDTH: 0,
        images: null,
        imageUrls: [
          "../assets/rec1.jpg",
          "https://www.shutterstock.com/blog/wp-content/uploads/sites/5/2019/09/shutterstock_1151632343.jpg?w=2000",
          "https://www.shutterstock.com/blog/wp-content/uploads/sites/5/2019/09/shutterstock_1429964489.jpg?w=2000",
          "https://www.shutterstock.com/blog/wp-content/uploads/sites/5/2019/09/shutterstock_1429964489.jpg?w=2000",
        ],
      };
    },
    methods: {
      prev() {
        if (this.curPos > 0) {
          this.postion += this.IMAGE_WIDTH;
          this.images.style.transform = `translateX(${this.postion}px)`;
          this.curPos = this.curPos - 1;
        }
      },
      next() {
        if (this.curPos < this.imageUrls.length - 1) {
          this.postion -= this.IMAGE_WIDTH;
          this.images.style.transform = `translateX(${this.postion}px)`;
          this.curPos = this.curPos + 1;
        }
      },
  
    },
    computed: {
      getImageWidth: () => {
        const imgWidth = document.querySelector(".images").offsetWidth;
        return imgWidth;
      },
    },
    mounted() {
      this.IMAGE_WIDTH = this.getImageWidth;
      this.images = document.querySelector(".images");
      this.images.addEventListener("touchstart", this.touch_start);
      this.images.addEventListener("touchend", this.touch_end);
    },
  };
  </script>
  
  <style scopped>
  .image-album {    
    height: auto;
    max-width: 500px;
    max-height: 500px;
    overflow: hidden;
  }
  .images {
    position: relative;
    display: flex;
    height: auto;
    transition: transform 0.5s;
  }
  .image {
    width: 100%;
    height: auto;
    max-width: 2000px;
    max-height: 2000px;
  }
  .image-circle-wrapper {
    display: flex;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -18px);
  }
  .image-circle {
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background-color: white;
    border: 1px solid #d2d2d2;
    margin-right: 12px;
  }
  .image-circle:last-child {
    margin-right: 0;
  }
  .image-circle.activeImg {
    background-color: #404040;
  }

  .carousel-control-prev {
    /* 사이즈 */
    width: 40px;
    height: 40px;
    border-top: 8px solid rgb(71, 71, 71);
    /* 선 두께 */
    border-right: 8px solid rgb(71, 71, 71);
    /* 각도 */
    transform: rotate(225deg);
    top: 50%;
    left: 50px;
}

.carousel-control-next {
    /* 사이즈 */
    width: 40px;
    height: 40px;
    /* 선 두께 */
    border-top: 8px solid rgb(71, 71, 71);
    border-right: 8px solid rgb(71, 71, 71);
    /* 각도 */
    transform: rotate(45deg);
    position: absolute;
    top: 50%;
    right: 50px;
}
  </style>