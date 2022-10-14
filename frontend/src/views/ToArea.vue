<template>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <h3>도착지 검색</h3>
                <button id="closeBtn" src="../assets/close.png" @click="$emit('closeModal')">X</button>
            </div>
        </div>
        <br>
        <div class="">
            <div class="">
                <input type="text" class="SearchBar" placeholder="도시, 공항">
            </div>
        </div>

    </div>
</template>

    
    
<script>
export default {
    name: 'HelloWorld',
    components: {

    },
    data() {
        return {

        }
    },
    methods: {

    }
}
</script>
    
    
<style scoped>
h3 {
    float: left;
    margin-top: 30px;
    margin-left: 30px;
    color: #999;
}

.SearchBar {
    width: 96%;
    height: 50px;
    font-size: 22px;
    border-bottom: 1.5px solid;
    background-color: rgb(246, 246, 246);
    text-align: left;
    padding-left: 20px;
}

#closeBtn {
    width: 32px;
    height: 40px;
    float: right;
    margin-top: 22px;
    margin-right: 14px;
    border: none;
    font-size: 24px;
    color: #999;
    background-color: rgb(246, 246, 246);
}
</style>
