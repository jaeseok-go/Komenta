<template>
  <div>VOD 전체회차정보

      {{vodInfo}}
      <!-- <div v-for="vod in vodinfo.episode">
        vod 포스터, 회차최신순, 회차 세부내용
      </div> -->
  </div>
  
  
</template>

<script>
export default {

  props: ['vodInfo'],
  created() {
  // this.vodInfo = getVod(v_id);

  }
}
</script>

<style>

</style>