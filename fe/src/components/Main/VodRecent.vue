<template>
  <b-col class="vodSort-form">
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-left']"/>
      </button>
      <span class="page-count">{{pageNum+1}}/{{pageCount}} </span>
      <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-right']"/>
      </button>
    </div>
    <ul class="stage clearfix">
      <li v-if="this.recentVODs.length == 0">
        <div class="noRegister-text">등록된 VOD가 없습니다.</div>
      </li>
      <li class="scene" v-for="(vod,index) in paginatedData" :key="index" style="cursor:pointer" v-else>
        <div class="movie" onclick="return true">
          <div class="poster">
            <img :src="getPoster(vod.v_id)" height="100%" alt="" />
          </div>
          <div class="info" @click="goVod(vod.ve_id)">
            <header class="info-text">
              <h1>{{ vod.v_title }} {{ vod.ve_episode_num }}회</h1>
              <span class="year" v-text="getRegistDate(vod.ve_upload_date)"></span>
              <hr>
              <span v-text="getVeContents(vod.ve_contents)"></span>
              <!-- <span class="rating">PG</span>
            <span class="duration">130 minutes</span> -->
            </header>
            <div>
              <button>보러가기</button>
            </div>
          </div>
        </div>
      </li>
    </ul>
  </b-col>
</template>

<script>
import { fetchRecentVOD } from '@/api/vod';
import { fetchAllVOD } from '@/api/user';
export default {
  data() {
    return {
      pageNum:0,
      allVODInfo:[],
      recentVODs: [],
    };
  },
  created() {
    this.getRecentVOD();
    this.getVODPoster();
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 5
    }
  },
  methods: {
    //getRecentVOD api만들어야함
    async getRecentVOD() {
      const response = await fetchRecentVOD();
      this.recentVODs = response.data;
    },
    async getVODPoster(){
        const response = await fetchAllVOD();
        this.allVODInfo = response.data;
    },
    getPoster(index) {
        for (let i = 0; i < this.allVODInfo.length; i++) {
            if(this.allVODInfo[i].v_id === index) {
                const poster = this.allVODInfo[i].v_poster;
                return `${process.env.VUE_APP_PICTURE}poster/${poster}`;
            }
        }
    },
    goVod(vId) {
      this.$router.push(`/voddetail/${vId}`);
    },
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    getRegistDate(date){
      const year = date.split(" ");
      return year[0];
    },
    getVeContents(contents) {
      if(contents === null){
        const errStr = "해당 VOD 줄거리를 제공하지 않습니다.";
        return errStr;
      }else if(contents.length > 93) {
        const reContents = contents.substring(0,94)+'...';
        return reContents;
      }else{
        return contents;
      }
    }
  },
  computed:{
    pageCount() {
        let listLeng = this.recentVODs.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

        if(listLeng % listSize > 0) page += 1;

        return page;
    },
    paginatedData() {
        const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;

        return this.recentVODs.slice(start, end);
        }
    }
};
</script>

<style>
@import '../../../public/css/component.css';
@import '../../../public/css/demo.css';
@import '../../../public/css/normalize.css';
</style>
