<template>
  <div>VOD 에피소드 댓글
    5-1. 해당회차 베스트댓글 탭<br>
    GET(ve_id) -> res(좋아요순 vod회차 댓글정보(c_id,vod회차 댓글내용,댓글유저,댓글좋아요수,댓글생성시간))<br>
    <!-- vod댓글 좋아요 순서 v-for -->
    <div>
      댓글유저 | 댓글내용 | 댓글좋아요 수 | 댓글생성시간
    </div>
    5-2. 베스트 댓글 누르면 해당 시간대로 이동<br>
    <div @click="goCommentTime">
    GET(c_id) -> res(c_playtime)으로 이동???<br>

    </div>
  </div>
  
  
</template>

<script>
export default {
  data() {
    return {
      vodEpiInfo: {}
    }
  },
  created() {
  // this.vodEpiInfo = getVodEpi(ve_id)
  },
  methods: {
    goCommentTime() {
      // 댓글에 기록된 playtime으로 vod이동...!
    }
  },
}
</script>

<style>

</style>