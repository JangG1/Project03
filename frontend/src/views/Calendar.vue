<template>
<div id="nav">
    <div class="menu-nav">
        <div class="search-form">
            <input size="sm" class="mr-sm-2" type="text" placeholder="검색어를 입력해주세요" v-model="keyword" @keyup.enter="searchresultshow(keyword)">
            <button size="sm" class="my-2 my-sm-0" type="submit" @click="searchresultshow(keyword)">
                <icon icon="search">검색</icon>
            </button>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name: "HelloWorld",
    data() {
        return {
            keyword: ''
        }
    },
    methods: {
        searchresultshow(keyword) {
            if (keyword !== '') { //검색어를 입력한 경우
                this.$router.push({
                    name: "SearchPage",
                    params: {
                        keyword: this.keyword,
                        isResultShow: true,
                    },
                });
                this.keyword = ''
                console.log('"', keyword, '"' + ' 검색')
            } else {
                alert('검색어를 입력해주세요!') //검색어를 입력하지 않은 경우
            }
        },
    }
};
</script>
