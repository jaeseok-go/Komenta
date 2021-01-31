<template>
  <div>
      <h1>실시간 베스트 댓글 유저</h1>
      <!-- 코멘트 아이디, 코멘트 내용, 비디오 에피소드 아이디, 비디오 에피소드 회차, 비디오 컨텐츠(내용인가 제목인가), 댓글 좋아요 개수 -->
      <!--{ c_id:0, c_contents:"string", ve_id:0, ve_episode_num:0,ve_contents:"string","댓글 좋아요 개수":0 }// -->
      <div>{{this.fetchBestComment.c_content}}</div>
  </div>
</template>

<script>
import { fetchBestComment } from '@/api/comment'

export default {
  data() {
    return {
      bestComment:{}

    }
  },
  methods: {
    getBestComment() {
      //getBestComment api만들어야함
      this.bestComment = fetchBestComment()
    }
  },
  created() {
    this.getBestComment()
  },


}
</script>

<style>

</style>