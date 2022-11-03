<template>
<div>
    <h1>가는 편 {{ fromArea }} -> {{ toArea }} </h1>
    <div class="day-select">
        <button type="button">1</button>
        <button type="button">2</button>
        <button type="button">3</button>
        <button type="button">4</button>
        <button type="button">5</button>
    </div>
    <br>
    <div class="day-seat-select">
        <button type="button">
            <h3>07:40 {{ fromArea }} -> 10:45 {{ toArea }}</h3>
        </button>
        <button type="button">
            <h3>일반석 스탠다드</h3>
            <h3>{{Math.floor(Math.random()*(400 - 200) + 200)}},000 원</h3>
            <h4>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</h4>
        </button>
        <button type="button">
            <h3>일반석 플랙스</h3>
            <h3>{{Math.floor(Math.random()*(700 - 350) + 350)}},000 원</h3>
            <h4>{{Math.floor(Math.random()*(10 - 1) + 1)}}석</h4>
        </button>
    </div>

</div>
</template>

  
<script>
export default {
    name: 'HelloWorld',
    data(){
        return{
            price: "",
        }
    },
    props: {
        fromArea: {
            type: String,
            default: ''
        },
        toArea: {
            type: String,
            default: ''
        }
    },
    methods:{

    }
}
</script>

<style>
.day-select {
    width: 1500px;
    height: 100px;
    margin-left: auto;
    margin-right: auto;
    
    display: flex;
}

.day-select button{
    width: 400px;
    height: 100px;
    border: 0.5px solid #999;
    
}

.day-seat-select {
    width: 1500px;
    margin-left: auto;
    margin-right: auto;
    border: 0.5px solid #999;
    display: flex;
    padding: 40px;
}

.day-seat-select button{
    width: 400px;
    height: 150px;
    margin: 10px;
    color: #999;
    border: 0.5px solid #999;
}
</style>
