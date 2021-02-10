<template>
  <b-col>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-left']"/>
      </button>
      <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-right']"/>
      </button>
    </div>
    <ul class="stage clearfix">
      <li v-if="this.popularVODs.length == 0">
        <div class="noRegister-text">등록된 VOD가 없습니다.</div>
      </li>
      <li class="scene" v-for="(vod,index) in paginatedData" :key="index" v-else>
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
import { fetchPopularVOD } from '@/api/vod';
import { fetchAllVOD } from '@/api/user';
export default {
  data() {
    return {
      pageNum:0,
      popularVODs: [],
      allVODInfo:[],
    }
  },
  created() {
    this.getPopularVOD();
    this.getVODPoster();
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 5
    }
  },
  methods:{
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    async getPopularVOD() {
      const response = await fetchPopularVOD();
      console.log('인기순 정렬 : ', response);
      this.popularVODs = response.data;
    //   console.log(this.popularVODs);
    },
    async getVODPoster(){
        const response = await fetchAllVOD();
        console.log('vod poster list : ',response);
        this.allVODInfo = response.data;
        // console.log(this.allVODInfo);
    },
    getPoster(index) {
        for (let i = 0; i < this.allVODInfo.length; i++) {
            if(this.allVODInfo[i].v_id === index) {
                const poster = this.allVODInfo[i].v_poster;
                return `${process.env.VUE_APP_PICTURE}poster/${poster}`;
            }
        }
    },
    goVod(veId) {
      this.$router.push(`/voddetail/${veId}`);
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
  computed: {
    pageCount() {
      let listLeng = this.popularVODs.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.popularVODs.slice(start, end);
    }
  }
}
</script>

<style>
@import '../../../public/css/component.css';
@import '../../../public/css/demo.css';
@import '../../../public/css/normalize.css';

</style>