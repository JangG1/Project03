<template>
    <div class="container">
      선택한 주 : {{ dateRange.start }} ~ {{ dateRange.end }}
      <br />
      <br />
      <Datepicker
        inline
        :editable="false"
        valueType="format"
        format="YYYY-MM-DD"
        :getClasses="getClasses"
        :lang="datepickerLang"
        :value="weekTime"
        :disabled-date="dislabedDate"
        @pick="calendarPick"
      />
      
    </div>
  </template>
  
<script>
import Datepicker from '@vuepic/vue-datepicker';
import moment from "moment";

export default ({
    name: "HelloWorld",
    components: {
        Datepicker
    },
    data() {
      return {
        date:"",
        weekTime: null,
        dateRange: {
          start: null,
          end: null,
        },
        datepickerLang: {
          yearFormat: "YYYY년",
          monthFormat: "M월",
          monthBeforeYear: false,
        },
      };
    },
    methods: {
      // 선택한 영역 class set
      getClasses(cellDate, currentDates) {
        if (currentDates.length === 0) return;
        //기준 날짜
        const cellDateVal = moment(cellDate).format("YYYYMMDD");
        // 주 시작일 날짜
        const startWeekDay = moment(currentDates[0])
          .startOf("week")
          .format("YYYYMMDD");
        // 주 종료일 날짜
        const endWeekDay = moment(currentDates[0])
          .endOf("week")
          .format("YYYYMMDD");
        
        // 주 시작점 & 종료점 class
        if (cellDateVal === startWeekDay || cellDateVal === endWeekDay) {
          return "active";
        }
        // 중간영역 class
        if (
          moment(cellDateVal).isAfter(startWeekDay) &&
          moment(cellDateVal).isBefore(endWeekDay)
        ) {
          return "in-range";
        }
      },
      // 캘린더 비활성화 영역 - 당일 주를 포함하여 선택 가능
      dislabedDate(date) {
        return (
          moment(date).format("YYYYMMDD") >=
          moment()
            .add("7", "days")
            .startOf("week")
            .format("YYYYMMDD")
        );
      },
      calendarPick(item) {
        this.weekTime = moment(item).format("YYYYMMDD");
        // 선택한 날짜의 주 첫째일과 마지막일 date set - 첫째일을 일요일로 설정
        this.dateRange.start = moment(item)
          .startOf("week")
          .format("YYYYMMDD");
        this.dateRange.end = moment(item)
          .endOf("week")
          .format("YYYYMMDD");
      },

    },
});

  </script>
  