<template>
  <div>
    <div v-for="(comment,index) in paginatedData" :key="index">
      <span class="comment__username" @click="goFeed(comment.u_id)">{{comment.u_nickname}}</span> <br>
      <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}} </span> 
      <span>{{ comment.c_contents}} </span> <br>
      <span class="comment__uploadtime"> {{comment.c_upload_time}} </span>
      <span @click="commentLike(comment)"><i class="far fa-thumbs-up" :id="`like-btn-${comment.c_id}`" :class="{commet__like :comment.is_like_comment}"  style="cursor:pointer"></i><span :id="`like-cnt-${comment.c_id}`">{{ comment.comment_good_count }}</span> </span>
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
 .btn-right {
    text-align: right;
  }

  .btn-right .inline-block {
    margin: 0 5px;
  }
</style>