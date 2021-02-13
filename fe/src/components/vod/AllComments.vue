<template>
  <div>
    <div v-for="(comment,index) in paginatedData" :key="index">
      <span class="comment__username" @click="goFeed(comment.u_id)">{{comment.u_nickname}}</span> 
      <template v-if="isBlockUser(comment.u_id)">
      <span @click="blockUser(comment.u_id)" class="comment__block">
      차단취소</span>
      </template>
      <template v-else>
      <span @click="blockUser(comment.u_id)" class="comment__block">
      차단하기</span>
      </template>
     <br>
      <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}} </span> 
      <template v-if="isBlockUser(comment.u_id)">
      <span class="comment__block__content">차단한 댓글입니다. </span> <br>
      </template>
      <template v-else>
      <span>{{ comment.c_contents}} </span> <br>
      </template>
      <span class="comment__uploadtime"> {{comment.c_upload_time}} </span>
      <!-- :class="{commet__like :comment.is_like_comment}"  -->
      <span @click="commentLike(index,comment)"><i class="far fa-thumbs-up" :class="[comment.is_like_comment ? 'commet__like' :' comment__unlike' ]" :id="`like-btn-${comment.c_id}`"  style="cursor:pointer"></i><span :id="`like-cnt-${comment.c_id}`">{{ comment.comment_good_count }}</span> </span>
      <hr>
    </div>
    <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
        <span class="page-count">{{pageNum+1}}/{{pageCount}} 페이지 </span>
        <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">다음</button>
    </div>
  </div>
</template>

<script>
import {userlikeComment,fetchEpiComment } from '@/api/comment'
import {modifyunfollow} from '@/api/user'

import {mapState} from 'vuex'
export default {
  props:{
    pageSize: {
      type:Number,
      required: false,
      default: 10
    }
  },
  data(){
    return {
      pageNum:0,
      commentsList:[]
    }
  },
  computed:{
      ...mapState({
      userInfo: state => state.user.userInfo,
      myUnFollowingList: state => state.user.myUnFollowingList,
    }),
    pageCount() {
      let listLeng = this.commentsList.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.commentsList.slice(start, end);
    }
  },
  created() {
  this.getEpiComment();
  },
  methods : {
    isBlockUser(uId){
      console.log(this.myUnFollowingList,'언팔')
      for (let i = 0; i < this.myUnFollowingList.length; i++) {
        const unfollowuser = this.myUnFollowingList[i];
        if (unfollowuser.f_id == uId) {
          return true
        }
      }
      console.log(uId)
      return false
    },
  blockUser(uId) {
    const blockInfo = {
      u_id : this.userInfo.u_id,
      uf_id : uId
    }
    modifyunfollow(blockInfo)
    
  },
  nextPage() {
      this.pageNum += 1;
  },
  prevPage() {
    this.pageNum -= 1;
  },
  async getEpiComment() {
  try {
  const epiId = this.$route.params.id; 
  const res = await fetchEpiComment(epiId)
  this.commentsList = res.data
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
   commentLike(index,comment){
    const likeBtn = document.querySelector(`#like-btn-${comment.c_id}`)
    // const likeCount = document.querySelector(`#like-cnt-${comment.c_id}`)
     this.commentsList[index].is_like_comment = !this.commentsList[index].is_like_comment
    // likeBtn.style.color = comment.is_like_comment ? 'crimson' : 'black'
    // if (comment.is_like_comment) {
    //   likeCount.innerText = comment.comment_good_count - 1
    //   likeBtn.style.color ='grey'
    //   } else {
    //     likeCount.innerText = comment.comment_good_count + 1
    //     likeBtn.style.color = '#fc3c44'
    //   }
    if (this.commentsList[index].is_like_comment){
      this.commentsList[index].comment_good_count += 1
      likeBtn.style.color = '#fc3c44'
    } else {
      this.commentsList[index].comment_good_count -=1
      likeBtn.style.color ='grey'
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
 .btn-right {
    text-align: right;
  }

  .btn-right .inline-block {
    margin: 0 5px;
  }
</style>