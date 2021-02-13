<template>
  <div>
    <div v-for="(comment,index) in commentsList" :key="comment.c_id">
      <div class="comment__rank">{{index+1}} </div>
      <div class="comment__commentbox">
      <span class="comment__username" @click="goFeed(comment.u_id)">{{comment.u_nickname}}</span> 
      <span @click="blockUser(comment.u_id)" class="comment__block" v-if="isBlockUser(comment.u_id)">차단하기</span> <br>
      <div class="comment__best">BEST</div>
      <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}} </span> 
      <span>{{ comment.c_contents}} </span> <br>
      <span class="comment__uploadtime"> {{comment.c_upload_time}} </span>
      <!-- :class="[comment.is_like_comment ? 'commet__like' :' comment__unlike' ]" -->
      <span @click="commentLike(comment)"><i class="far fa-thumbs-up" :id="`like-btn-${comment.c_id}`" style="cursor:pointer"></i><span :id="`like-cnt-${comment.c_id}`">{{ comment.comment_good_count }}</span> </span>
      </div>
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
    isBlockUser(uId){
      console.log(uId)
    },
    goFeed(uId){
      this.$router.push(`/feed/${uId}`)
    },
         async getEpiComment() {
    try {
        const epiId = this.$route.params.id; 
      const res = await fetchEpiComment(epiId)
      this.commentsList = res.data.slice(0,10)
      this.commentsList.sort(function (a,b) {
             return parseFloat(a.comment_good_count) > parseFloat(b.comment_good_count) ? -1 : parseFloat(a.comment_good_count) < parseFloat(b.comment_good_count) ? 1:0;
        })
        console.log(this.commentsList,'댓글?')

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
        const commentInfo = {
          c_id : comment.c_id,
        u_id : this.userInfo.u_id
      }
        userlikeComment(commentInfo)

  }

      
    }
}
</script>

<style scoped>

</style>