<template>
  <div>
      <h4>Now playing</h4>
      <div class="nowPlaying">
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
      return `${process.env.VUE_APP_PICTURE}poster/${poster}`;
    },
    goVOD(ve_id){
      this.$router.push(`voddetail/${ve_id}`)
    }
  },
}
</script>

<style>

</style>