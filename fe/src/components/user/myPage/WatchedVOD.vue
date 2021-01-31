<template>
  <b-col>
    <h4>시청 VOD 관리</h4>
    <hr>
    <div class="btn-right">
      <button @click="showModalForm">더보기</button>
    </div>
    <!-- vueper Slides 이용 -->
      <vueper-slides
        class="no-shadow"
        :visible-slides="5"
        :slide-ratio="1 / 4"
        :dragging-distance="70"
        :bullets="false">
        <vueper-slide v-for="i in 10" :key="i" :title="i.toString()" :style="'background-color: ' + ['#ff5252', '#42b983'][i % 2]" />
      </vueper-slides>

    <!-- <div class="watchedVOD" v-for="(vod, index) in watchedVODList" :key="index"> -->
      <!-- 원형으로 포스터 표시 -->
      <!-- <div class="watchedVODImg">
        <img :src=vod.v_img>
      </div>
      <div class="watchedVODText">
        {{vod.v_name}} {{vod.v_ep_num}}화<br>
        일시 º {{vod.watched_date}} -->
        <!-- VOD명 + 회차 정보 -->
        <!-- 최근 시청 날짜 -->
      <!-- </div>
    </div> -->
  <!-- 
    1. 이미지(포스터...?)<br>
    2. VOD 명 + 회차 정보<br>
    3. 최근 시청 날짜<br>
    4. 편집 버튼 클릭 시, 시청 기록 삭제 가능<br> 
  -->
  </b-col>
</template>

<script>
import {VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'

export default {
  components:{
    VueperSlides,
    VueperSlide
  },
  data() {
    return {
      watchedVODList: [
        {
          v_img:'#',
          v_name:'경이로운 소문',
          v_ep_num:'21',
          watched_date:'2021-01-20'
        },
        {
          v_img:'#',
          v_name:'경이로운 소문',
          v_ep_num:'21',
          watched_date:'2021-01-20'
        },
        {
          v_img:'#',
          v_name:'경이로운 소문',
          v_ep_num:'21',
          watched_date:'2021-01-20'
        }
      ]
    }
  },
  methods: {
    showModalForm() {
      this.$emit('showVODForm')
    },
  },
}
</script>

<style>
  .btn-right {
    text-align: right;
  }

  .watchedVOD {
    width: 150px;
    display: inline-block;
    margin-right: 1rem;
  }

  .watchedVODImg {
    height: 200px;
    background-color: orange;
  }

  .watchedVODText {
    text-align: center;
  }
</style>