<template>
  <div>
      <h4 class="page-title"><i class="fas fa-forward"></i> Now playing <i class="fas fa-backward"></i></h4>
      <div class="nowPlaying nonShow" v-if="nowPlayingVOD.ve_id == undefined">
        <div class="poster-form">
          <div class="poster">
            <img :src="require(`@/assets/images/nonNowPlaying.png`)" />
            <div class="posterCD">
              <div class="posterCD-middle"></div>
            </div>
          </div>
          <p class="nonNowPlaying-text">시청 중인 VOD가 없습니다.</p>
        </div>
      </div>
      <div class="nowPlaying isShow" v-else>
        <div class="poster-form">
          <div class="poster" @click="goVOD(nowPlayingVOD.ve_id)">
            <img :src="getPoster()" />
            <div class="posterCD">
              <div class="posterCD-middle"></div>
            </div>
          </div>
          <h3>{{nowPlayingVOD.v_title}} {{nowPlayingVOD.ve_episode_num}}화</h3>
        </div>
      </div>
  </div>
</template>

<script>
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
      this.nowPlayingVOD = response.data.episodeList[0];
      this.playingVODInfo = response.data.historyList[0];
    },
    getPoster(){
      const poster = this.nowPlayingVOD.v_poster;
      return `${process.env.VUE_APP_PICTURE}poster/${poster}`;
    },
    goVOD(ve_id){
      this.$router.push(`/voddetail/${ve_id}`)
    }
  },
}
</script>

<style>

</style>