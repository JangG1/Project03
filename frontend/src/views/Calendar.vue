<template>
<div>
  <label for="numbers">number</label>
  <select id="numbers">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
      <option value="6">6</option>
  </select>
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
