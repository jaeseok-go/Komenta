<template>
  <div>
    <!-- {{ comments }} -->
    <!-- playtime에 댓글이 떠야됨 -->
    <div>댓글유저,댓글내용,좋아요수,댓글시간정보</div>
    <div v-for="comment in comments" :key="comment.c_id">
      <span @click="goCommentTime(comment.c_playtime)"> ({{comment.c_playtime}})</span> | {{comment.c_upload_time}} | {{comment.u_nickname}} : {{ comment.c_contents}} | {{ comment.comment_good_count }}        
    </div>
  </div>
</template>

<script>
export default {
  // props :['comments'],
  data() {
    return {
      comments: [
        {
                    "c_id" : 6,
                    "c_contents":"ㅋㅋㅋㅋㅋㅋㅋㅋㅋ벌써 재밌겠다ㅋㅋㅋㅋㅋㅋ",
                    // "c_playtime" : "00:00:05",
                    "c_playtime" : "6",
                    "c_upload_time" : "2020.02.03 09:03:20",
                    "comment_good_count" : 1440000,
                    "u_id" : 17,
                    "u_nickname" : "아롱롱"
                },
                {
                    "c_id" : 7,
                    "c_contents":"ㅋㅋㅋㅋㅋㅋㅋㅋㅋ벌써 재밌겠다ㅋㅋㅋㅋㅋㅋ",
                    // "c_playtime" : "00:00:05",
                    "c_playtime" : "5.1",
                    "c_upload_time" : "2020.02.03 09:03:20",
                    "comment_good_count" : 10000,
                    "u_id" : 14,
                    "u_nickname" : "아롱롱"
                },
      ]
    }
  },
  created() {
        // comments 좋아요순으로 정렬, parseFloat(문자열 실수로 크기비교)
        this.comments.sort(function (a,b) {
            return parseFloat(a.comment_good_count) > parseFloat(b.comment_good_count) ? -1 : parseFloat(a.comment_good_count) < parseFloat(b.comment_good_count) ? 1:0;
        })
        console.log(this.comments)
    },
    methods : {
      goCommentTime(time){
        this.$emit('goCommentTime',time)
      }
    }
}
</script>

<style>

</style>