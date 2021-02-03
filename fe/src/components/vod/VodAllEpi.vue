<template>
  <div>VOD 전체회차정보
    4. 전체회차 탭<br>
        GET(v_id) -> res(vod 전체회차-회차최신순,날짜,vod회차세부내용)<br>
      <!-- 받은 데이터 v-for -->
      {{vodInfo}}
      <div>
        vod 포스터, 회차최신순, 회차 세부내용
      </div>
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