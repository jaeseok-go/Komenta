<template>
  <b-col>
    <!-- 최근 본 VOD div -->
    <h3 class="container__recentSection__recentList">Recently Watched </h3>
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
            <div class="vodInfo">
              <div class="vod-info-form">
                <p class="vod-info-title" v-html="vodTitleReName(vod.v_title, vod.ve_episode_num)"></p>
                <p class="vod-info-genre">{{vod.g_name}}/{{vod.gd_name}}</p>
                <button class="vod-info-btn">보러가기</button>
              </div>
            </div>
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
      recentlyPlayLists:{}
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
        const res = await fetchRecentPlaylist()
        this.recentlyPlayLists = res.data
    },
    getVodPoster(gdId,title){
        return `${process.env.VUE_APP_PICTURE}poster/${gdId}_${title}`
    },
    vodTitleReName(title, epi_num) {
      let name = String(title + ' ' + epi_num + '화');
      let rename = '';
      for (let i = 0; i < name.length; i++) {
        if (i % 10 == 0) {
          rename += `<br>` + name.charAt(i);
        } else {
          rename += name.charAt(i);
        }
      }
      return rename;
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
  }

  .watchedVODText {
    text-align: center;
  }

  .vueperslider__content-wrapper {
    margin-right: 1rem;
  }
</style>