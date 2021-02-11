<template>
  <b-col>
    <!-- <div class="container__recentSection"> -->
            <!-- 최근 본 VOD div -->
            <h3 class="container__recentSection__recentList">Recently Watched </h3>
    <!-- </div> -->
              <div class='drop-zone'>
            <!-- 최근 본 VOD 리스트 중 하나씩 v-for돌림 -->
                <div class="drop-zone__inner">
                    <div 
                    v-for='(vod,index) in recentlyPlayLists.episodeList' 
                    :key='index'
                    class='drag-el'
                    >
                    <span @click="goVod(vod.ve_id)">
                    <img :src="getVodPoster(vod.gd_id,vod.v_title)" width="100%">
                    </span>
                    </div>
                </div>
            </div>
      

  </b-col>
</template>

<script>
import {fetchRecentPlaylist} from '@/api/user';


export default {
  props:['getUserId'],
  data() {
    return {
      recentlyPlayLists:{
        // episodeList:[],
        // historyList:[]
      }
    }
  },
  created() {
    this.getRecentPlayList();
  },
  methods: {
    goVod(veId){
      this.$router.push(`/voddetail/${veId}`)
    },
    async getRecentPlayList() {
    try {
        const res = await fetchRecentPlaylist()
        this.recentlyPlayLists = res.data
        console.log('나의 최신vod목록:',this.recentlyPlayLists)
    } catch {
        console.log('최신vod에러')
    }
    },
    getVodPoster(gdId,title){
        return `${process.env.VUE_APP_PICTURE}poster/${gdId}_${title}`
    },
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