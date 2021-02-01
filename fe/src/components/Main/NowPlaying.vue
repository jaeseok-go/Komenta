<template>
  <div>
      <h1>Now playing</h1>
      <!-- u_id -> GET -> 썸네일, 타이틀, 회차정보 --> <!-- 비디오 에피소드 아이디, 비디오 아이디, 비디오 에피소드 회차, 비디오 포스터  -->
      <!-- [{vh_id : 0,vh_name:"string",vh_watching_time : "string",vh_score : int,vh_comment : "string",vh_good : int,u_id : int,ve_id : int,}] -->
      
      <div>{{fetchedPlaylist.v_poster}}</div>
      <div>{{fetchedPlaylist.ve_episode_num}}</div>
      <div>{{fetchedPlaylist.vh_name}}</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
// import store from '@/stores/modules/user'

export default {
  computed: {
    ...mapGetters(['fetchedPlaylist']) 
  },


}
</script>

<style>

</style>