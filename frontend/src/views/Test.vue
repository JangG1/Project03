<template>
    <div>
      <input
        class="search-input"
        type="text"
        placeholder="그룹명, 데뷔년도, 멤버수를 입력하세요"
        @input="searchGroup($event)"
      />
  
      <ul class="group-list">
        <li class="group-list-header">
          <span style="width: 200px;">그룹명</span>
          <span>데뷔년도</span>
          <span>멤버수</span>
        </li>
        <li v-for="group in groupList" :key="group" class="group-item">
          <span style="width: 200px;">{{ group.name }}</span>
          <span>{{ group.debut }}</span>
          <span>{{ group.member }}</span>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import data from "@/components/group.json";
  
  const groupList = data;
  
  export default {
    data() {
      return {
        groupList,
      };
    },
  
    methods: {
      searchGroup(event) {
        const len = this.groupList.length;
  
        for (let i = 0; i < len; i++) {
          if (
            this.groupList[i].name.includes(event.target.value) === false &&
            this.groupList[i].debut.includes(event.target.value) === false &&
            this.groupList[i].member.includes(event.target.value) === false
          ) {
            document.querySelectorAll(".group-item")[i].style.display = "none";
          } else {
            document.querySelectorAll(".group-item")[i].style.display = "flex";
          }
        }
      },
    },
  };
  </script>
  
  <style>
  * {
    padding: 0;
    margin: 0;
  }
  .search-input {
    display: block;
    padding: 4px 8px;
    margin: 10px auto;
    width: 320px;
    font-size: 16px;
    outline: none;
  }
  
  .group-list {
    margin: 0 auto;
    width: 360px;
  }
  
  .group-list li {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 32px;
    list-style-type: none;
  }
  
  .group-list span {
    display: block;
    width: 33%;
    text-align: center;
  }
  
  .group-list-header {
    font-weight: 700;
    border-bottom: 1px solid #bdbdbd;
  }
  </style>