<template>
  <div>
    <div v-for="comment in commentsList" :key="comment.c_id">
      <h3>{{comment.u_nickname}}</h3>
      <h4>{{ comment.c_contents}} </h4>
      <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}} </span> 
      <span> {{comment.c_upload_time}} </span>
      <span @click="likeComment(comment.c_id)"><i class="far fa-thumbs-up"></i>{{ comment.comment_good_count }} </span>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
  props :{
    commentsList:Array,
    veId : Number,
  },
   computed:{
      ...mapState({
      userInfo: state => state.user.userInfo,
    }),
    },
  created() {
        // comments 좋아요순으로 정렬, parseFloat(문자열 실수로 크기비교)
        this.commentsList.sort(function (a,b) {
            return parseFloat(a.comment_good_count) > parseFloat(b.comment_good_count) ? -1 : parseFloat(a.comment_good_count) < parseFloat(b.comment_good_count) ? 1:0;
        })
        console.log(this.commentsList)
    },
    methods : {
      goCommentTime(time){
        this.$emit('goCommentTime',time)
      },
      timeToSec(time){
        let splitTime = time.split(':')
        // console.log(splitTime)
        let changeTime = Number(splitTime[splitTime.length-1])
        for (let i = splitTime.length-2; i >= 0; i--) {
            let element = Number(splitTime[i]);
            changeTime += element*(60**(splitTime.length-i-1))
            // console.log(changeTime,'??초초초초??',element,60**(splitTime.length-i-1))
        }
        return changeTime
        },
      likeComment(cId){
      const commentInfo = {
        c_id : cId,
        u_id : this.userInfo.u_id
      }
      this.$emit('likeComment',commentInfo)
      
  }
      
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