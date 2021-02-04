<template>
  <b-col>
    <h4>시청 VOD 관리</h4>
    <hr>
    <div class="btn-right">
      <button @click="showModalForm">더보기</button>
    </div>
    <!-- vueper Slides 이용 -->
    <div class="VODCard">
      <div class="watchedVOD" v-for="(vod, index) in watchedVODList" :key="index">
        <!-- 원형으로 포스터 표시 -->
        <div class="watchedVODImg">
          <img :src="require(`@/assets/images/${vod.v_poster}.png`)" width="153px">
        </div>
        <div class="watchedVODText">
          {{vod.v_name}} {{vod.v_ep_num}}화<br>
          일시 º {{vod.watched_date}}
          <!-- VOD명 + 회차 정보 -->
          <!-- 최근 시청 날짜 -->
        </div>
      </div>
    </div>
  <!-- 
    1. 이미지(포스터...?)<br>
    2. VOD 명 + 회차 정보<br>
    3. 최근 시청 날짜<br>
    4. 편집 버튼 클릭 시, 시청 기록 삭제 가능<br> 
  -->
  </b-col>
</template>

<script>
// import {VueperSlides, VueperSlide } from 'vueperslides'
// import 'vueperslides/dist/vueperslides.css'
// import {fetchRecentPlaylist} from '@/api/user';
export default {
  // components:{
  //   VueperSlides,
  //   VueperSlide
  // },
  props:['getUserId'],
  data() {
    return {
      watchedVODList: [
        {
          v_name:'사랑의 불시착',
          v_ep_num:'21',
          v_poster:'test',
          watched_date:'2021-01-20'
        },
        {
          v_name:'사랑의 불시착',
          v_ep_num:'21',
          v_poster:'test',
          watched_date:'2021-01-20'
        },
        {
          v_name:'사랑의 불시착',
          v_ep_num:'21',
          v_poster:'test',
          watched_date:'2021-01-20'
        },
        {
          v_name:'사랑의 불시착',
          v_ep_num:'21',
          v_poster:'test',
          watched_date:'2021-01-20'
        },
        {
          v_name:'사랑의 불시착',
          v_ep_num:'21',
          v_poster:'test',
          watched_date:'2021-01-20'
        }
      ],
      recentlyPlayList:[]
    }
  },
  created() {
    // this.getVODList();
  },
  methods: {
    showModalForm() {
      this.$emit('showVODForm')
    },
    getVODList(){
      // console.log(this.getUserId);
      // this.watchedVODList = this.$store.state.recentPlaylist;
      // this.watchedVODList = response.data;
      //최근 시청 VOD 5개만 표출
      // for (let index = 0; index < 5; index++) {
      //   this.recentlyPlayList.concat(this.watchedVODList[index]);
      // }
    }
  },
}
</script>

<style>
  .btn-right {
    text-align: right;
  }

  .VODCard {
    text-align: center;
  }

  .watchedVOD {
    width: 150px;
    display: inline-block;
    margin-right: 1rem;
  }

  .watchedVODImg {
    height: 200px;
    /* background-color: orange; */
  }

  .watchedVODText {
    text-align: center;
  }

  .vueperslider__content-wrapper {
    margin-right: 1rem;
  }
</style>