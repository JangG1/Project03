<template>
    
    <div class="container">
    <div class="row">
        <div class="col-xs-12">123</div>
    </div>
    <div class="row">
        <div class="col-sm-6 col-md-3">111</div>
        <div class="col-sm-6">222</div>
        <div class="col-md-3">333</div>
    </div>
    <div class="row">
        <div class="col-xs-12">확인</div>
    </div>
</div>

</template>

<script>
export default {
    name: 'HelloWorld',
    data() {
        return {
            AdultCount: 0,
            ChildCount: 0,
            InfantCount: 0,
        }
    },
    methods: {
        AdultPlus() {
            this.AdultCount++;

        },
        AdultMinus() {
            if (this.AdultCount != 0)
                this.AdultCount--;
        },
        ChildPlus() {
            this.ChildCount++;

        },
        ChildMinus() {
            if (this.ChildCount != 0)
                this.ChildCount--;
        },
        InfantPlus() {
            this.InfantCount++;

        },
        InfantMinus() {
            if (this.InfantCount != 0)
                this.InfantCount--;
        },
    }
}
</script>

<style scoped>
h1{
    float: left;
    margin-top: 30px;
    margin-left: 30px;
}

form{
    padding: 1px;
    display: inline;
    margin-top: 50px;
}

img{
    cursor: pointer;
    width: 30px;
    height: 30px;
}

.resForm {
    
    font-size: 26px;
}

.submit-btn {
    width: 200px;    
    margin-top: 80px;
}

.closeBtn{
    width: 200px;
    float: right;
}
</style>
