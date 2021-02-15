<template>
  <div>
    <div v-for="(comment,index) in commentsList" :key="comment.c_id">
      <div class="comment__rank">{{index+1}} </div>
      <div class="comment__commentbox">
      <span class="comment__username" @click="goFeed(comment.u_id)">{{comment.u_nickname}}</span> 
      <template v-if="itsMe(comment.u_id)"><span class="comment__block" @click="DeleteComment(comment.c_id)">삭제</span></template>
      <template v-else-if="isBlockUser(comment.u_id)">
      <span @click="blockUser(comment.u_id)" class="comment__block">
      차단취소</span>
      </template>
      <template v-else>
      <span @click="blockUser(comment.u_id)" class="comment__block">
      차단하기</span>
      </template>
     <br>
      <div class="comment__best">BEST</div>
      <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}} </span> 
      <template v-if="isBlockUser(comment.u_id)">
      <span class="comment__block__content">차단한 댓글입니다. </span> <br>
      </template>
      <template v-else>
      <span>{{ comment.c_contents}} </span> <br>
      </template>
      <span class="comment__uploadtime"> {{comment.c_upload_time}} </span>
      <!-- :class="[comment.is_like_comment ? 'commet__like' :' comment__unlike' ]" -->
      <span @click="commentLike(index,comment)"><font-awesome-icon :icon="['fas', 'thumbs-up']" :id="`like-btn-${comment.c_id}`" :class="[comment.is_like_comment ? 'commet__like' :' comment__unlike' ]" style="cursor:pointer"/><span :id="`like-cnt-${comment.c_id}`">{{ comment.comment_good_count }}</span> </span>
      </div>
      <hr>
    </div>
  </div>
</template>

<script>
import {userlikeComment,fetchEpiComment, removeComment} from '@/api/comment'
import {modifyunfollow} from '@/api/user'

import {mapState} from 'vuex'
export default {
  data(){
    return {
      commentsList:[],

    }
  },
   computed:{
      ...mapState({
      userInfo: state => state.user.userInfo,
      myUnFollowingList: state => state.user.myUnFollowingList,
    }),
    },
  created() {
  this.getEpiComment();
  // this.$store.dispatch('FETCH_UNFOLLOWING',this.userInfo.u_id)
  // console.log(this.myUnFollowingList,'언팔유저')
    },
  methods : {
      itsMe(uId) {
            if (this.userInfo.u_id == uId) {
                return true
            }
            return false
      },
    async DeleteComment(cId){
    await removeComment(cId,'댓삭')
     this.getEpiComment();
    },
    isBlockUser(uId){
      // console.log(this.myUnFollowingList,'언팔')
      for (let i = 0; i < this.myUnFollowingList.length; i++) {
        const unfollowuser = this.myUnFollowingList[i];
        if (unfollowuser.f_id == uId) {

          return true
        }
      }

      return false
    },
    async blockUser(uId) {
    const blockInfo = {
      u_id : this.userInfo.u_id,
      uf_id : uId
    }
    await modifyunfollow(blockInfo)
    this.$store.dispatch('FETCH_UNFOLLOWING',this.userInfo.u_id)

  },
    goFeed(uId){
      this.$router.push(`/feed/${uId}`)
    },
    async getEpiComment() {
    try {
        const epiId = this.$route.params.id; 
      const res = await fetchEpiComment(epiId)
      this.commentsList = res.data.sort(function (a,b) {
             return parseFloat(a.comment_good_count) > parseFloat(b.comment_good_count) ? -1 : parseFloat(a.comment_good_count) < parseFloat(b.comment_good_count) ? 1:0;
        }).slice(0,10)

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
   async commentLike(index,comment){
    const likeBtn = document.getElementById(`like-btn-${comment.c_id}`)
    // const likeCount = document.querySelector(`#like-cnt-${comment.c_id}`)
    // likeBtn.style.color = comment.is_like_comment ? 'crimson' : 'black'
  this.commentsList[index].is_like_comment = !this.commentsList[index].is_like_comment
    
    likeBtn.classList.toggle('comment__like')
    likeBtn.classList.toggle('comment__unlike')
    // console.log('버튼',likeBtn)
// if (this.commentsList[index].is_like_comment){
      // this.commentsList[index].comment_good_count += 1
      // likeBtn.style.color = '#fc3c44'
    // } else {
      // this.commentsList[index].comment_good_count -=1
      // likeBtn.style.color ='grey'
    // }
      const commentInfo = {
        c_id : comment.c_id,
        u_id : this.userInfo.u_id
    }
      await userlikeComment(commentInfo)
      this.getEpiComment();

  }

      
    }
}
</script>

<style scoped>

</style>