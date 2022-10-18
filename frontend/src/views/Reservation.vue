<template>
<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
            <tr>
                <th scope="col">No.</th>
                <th scope="col">예약날짜</th>
                <th scope="col">왕복/편도</th>
                <th scope="col">좌석</th>
                <th scope="col">출발지</th>
                <th scope="col">도착지</th>
            </tr>
        </thead>

        <tbody>
            <tr v-for="res in ress" :key="res">
                <td>{{res.res_no}}</td>
                <td>{{res.res_date.substr(0, 16).replace("T"," ")}}</td>
                <td>{{res.way}}</td>
                <td>{{res.seat}}</td>
                <td>{{res.fromArea}}</td>
                <td>{{res.toArea}}</td>
            </tr>
        </tbody>
    </table>

</div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'HelloWorld',
    components: {},
    props: [""],
    data() {
        return {
            
            ress: [],


        }
    },
    methods: {
        getData() {
            axios.get('/res/all')
                .then((response) => {
                    this.ress = response.data
                })
        },

        },

    
    mounted() {
        this.getData()

    }
}
</script>

<style scoped>
body {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #eee;
}

.test {
    background-color: white;
    border-radius: 10px;

    text-align: right;
    line-height: var(--button-height);
    font-size: 48px;
    font-family: Helvetica;
    padding: 0 20px;
    color: #666;
}

h3 {
    margin: 40px 0 0;
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    display: inline-block;
    margin: 0 10px;
}

a {
    color: #42b983;
}
</style>
