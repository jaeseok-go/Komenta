<template>
  <div>
      <h4>Now playing</h4>
      <!-- u_id -> GET -> 썸네일, 타이틀, 회차정보 --> <!-- 비디오 에피소드 아이디, 비디오 아이디, 비디오 에피소드 회차, 비디오 포스터  -->
      <!-- [{vh_id : 0,vh_name:"string",vh_watching_time : "string",vh_score : int,vh_comment : "string",vh_good : int,u_id : int,ve_id : int,}] -->
      <!-- <div v-show="!fetchedPlaylist">
        최근 본 VOD가 없습니다.
      </div>
      <div v-show="fetchedPlaylist">
        <div>{{fetchedPlaylist.v_poster}}</div>
        <div>{{fetchedPlaylist.ve_episode_num}}</div>
        <div>{{fetchedPlaylist.vh_name}}</div> 
      </div>
      -->
      <div class="nowPlaying">
        <div class="poster">
          <img :src="getPoster()" />
          <!-- <div class="posterCD"></div> -->
          <h3>{{nowPlayingVOD.v_title}} {{nowPlayingVOD.ve_episode_num}}화</h3>
        </div>
      </div>
  </div>
</template>

<script>
// import store from '@/stores/modules/user'
import {fetchRecentPlaylist} from '@/api/user'
import { mapState } from 'vuex';

export default {
  data(){
    return{
      nowPlayingVOD:{},
      playingVODInfo:{}
    }
  },
  created() {
    this.getNowPlaying();
  },
  computed:{
    ...mapState({
      userInfo: state => state.user.userInfo
    })
  },
  methods: {
    async getNowPlaying(){
      const response = await fetchRecentPlaylist(this.userInfo.u_id)
      console.log("vod 목록 : ",response.data)
      this.nowPlayingVOD = response.data.episodeList[0];
      this.playingVODInfo = response.data.historyList[0];
    },
    getPoster(){
      const poster = this.nowPlayingVOD.v_poster;
      return `http://i4b201.p.ssafy.io:7000/picture/poster/${poster}`;
    }
  },
}
</script>

<style>

</style>