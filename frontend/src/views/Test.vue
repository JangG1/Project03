<template>
 <!--승객수 팝업-->
 <button type="button" class="btn-field" id="resPassenger" @click="popUp">승객 수</button>

<div class="popup-view" :class="{ active : popupView }">
    <PopUp @close-popup="popUp()"></PopUp>
    <button @click="closePopup">X</button>
</div>
</template>

<script>
import PopUp from './PopUp.vue';

export default {
    name: 'App',
    data() {
        return {
            products: "roomData",
            popupView: false,
        }
    },

    methods: {
        popUp() {
            this.popupView = (this.popupView) ? false : true
        },
        closePopup() {
            this.popupView = false;
        },
    },

    components: {
        PopUp,
        //왼쪽에는 아무렇게나 이름을 지어도 된다. 이 화면에서 사용할 이름을 짓는 부분
    }
}
</script>

<style>
.popup-view {
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
