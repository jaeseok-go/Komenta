<template>
  <div>
    <h2>전체 댓글</h2>
    <div v-for="comment in commentsList" :key="comment.c_id">
      <h3>{{comment.u_nickname}}</h3>
      <h4>{{ comment.c_contents}} </h4>
      <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}} </span> 
      <span> {{comment.c_upload_time}} </span>
      <span @click="commentLike(comment)"><i class="far fa-thumbs-up" :id="`like-btn-${comment.c_id}`" :class="{commet__like :comment.is_like_comment}"></i><span :id="`like-cnt-${comment.c_id}`">{{ comment.comment_good_count }}</span> </span>
      <hr>
    </div>
  </div>
</template>

<script>
import {userlikeComment,fetchEpiComment } from '@/api/comment'

import {mapState} from 'vuex'
export default {
  data(){
    return {
      commentsList:[]
    }
  },
   computed:{
      ...mapState({
      userInfo: state => state.user.userInfo,
    }),
    },
  created() {
  this.getEpiComment();
    },
    methods : {
         async getEpiComment() {
    try {
        const epiId = this.$route.params.id; 
      const res = await fetchEpiComment(epiId)
      this.commentsList = res.data
      // this.commentsList.sort(function (a,b) {
      //        return parseFloat(a.c_upload_time) > parseFloat(b.c_upload_time) ? -1 : parseFloat(a.c_upload_time) < parseFloat(b.c_upload_time) ? 1:0;
      //   })
      //   console.log(this.commentsList,'댓글?')

    } catch {
      console.log('epicomment 에러!!')
    }
  },
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
 commentLike(comment){
   const commentInfo = {
     c_id : comment.c_id,
   u_id : this.userInfo.u_id
 }
   userlikeComment(commentInfo)

    const likeBtn = document.querySelector(`#like-btn-${comment.c_id}`)
    const likeCount = document.querySelector(`#like-cnt-${comment.c_id}`)

    // likeBtn.style.color = comment.is_like_comment ? 'crimson' : 'black'
    if (comment.is_like_comment) {
      likeCount.innerText = comment.comment_good_count - 1
      likeBtn.style.color ='black'
      console.log(likeBtn,likeCount)
      } else {
        likeCount.innerText = comment.comment_good_count + 1
        likeBtn.style.color = '#fc3c44'
      }

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
.commet__like{
  color :#fc3c44;
}
</style>