<template>
  <div>
    <!-- {{ comments }} -->
    <!-- playtime에 댓글이 떠야됨 -->
    <div v-for="comment in comments" :key="comment.c_id">
      <span class="comment__time" @click="goCommentTime(comment.c_playtime)"> {{comment.c_playtime}} </span> | {{comment.c_upload_time}} | {{comment.u_nickname}} : {{ comment.c_contents}} | 
      <i class="far fa-thumbs-up"></i>{{ comment.comment_good_count }}    
    </div>
  </div>
</template>

<script>
export default {
  props :{
    comments:Array,
    veId : Number,
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
      },
      
    }
}
</script>

<style scoped>
.comment__time {
  color: blue;
  cursor: pointer;
  text-decoration: none;
}
</style>