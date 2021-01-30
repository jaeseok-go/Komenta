<template>
  <div>
      <h1>Now playing</h1>
      <!-- u_id -> GET -> 썸네일, 타이틀, 회차정보 --> <!-- 비디오 에피소드 아이디, 비디오 아이디, 비디오 에피소드 회차, 비디오 포스터  -->
      <!-- ve_id, v_id, ve_episode_num, v_poster -->
      
      <div>포스터</div>
      <div>this.nowEpiInfo.v_poster</div>
      <div>this.nowEpiInfo.v_id</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nowEpiInfo:{},
    }
  },
  methods: {
    // 마지막으로 봤던 vod 정보(vod poster, vod title, vod epic num)
    // fatchNowPlaying() {
    //   this.nowEpiInfo = getNowVOD()
    // },
  },
  created() {
    this.fetchNowPlaying()
  },

}
</script>

<style>

</style>