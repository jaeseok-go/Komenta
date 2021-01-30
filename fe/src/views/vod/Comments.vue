<template>
  <div>
    댓글 컴포넌트
    6. 해당시간 댓글 보여주는 컴포넌트<br>
    GET(ve_id)-> res(댓글유저, 댓글내용, 좋아요수,댓글시간정보,playtime)<br>
    <!-- playtime에 댓글이 떠야됨 -->
    <div>댓글유저,댓글내용,좋아요수,댓글시간정보</div>
    <!-- 로그인한 유저가 팔로우한 댓글이라면 주황색 글씨 -->
    <!-- 로그인한 유저가 차단한 댓글이라면 보이지 않게 -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      vodEpiComment: {}
    }
  },
  created() {
  // this.vodEpiComment = getVodEpi(ve_id)
  },
}
</script>

<style>

</style>