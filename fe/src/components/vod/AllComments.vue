<template>
  <div>
    <template v-if="commentsList.length">
    <div v-for="(comment,index) in paginatedData" :key="index">
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
      <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}} </span> 
      <template v-if="isBlockUser(comment.u_id)">
      <span class="comment__block__content">차단한 댓글입니다. </span> <br>
      </template>
      <template v-else>
      <span>{{ comment.c_contents}} </span> <br>
      </template>
      <span class="comment__uploadtime"> {{comment.c_upload_time}} </span>
      <!-- :class="{commet__like :comment.is_like_comment}"  -->
      <span @click="commentLike(index,comment)"><font-awesome-icon :icon="['fas', 'thumbs-up']" :id="`like-btn-${comment.c_id}`" :class="[comment.is_like_comment ? 'commet__like' :' comment__unlike' ]" style="cursor:pointer"/><span :id="`like-cnt-${comment.c_id}`">{{ comment.comment_good_count }}</span> </span>
      <hr>
    </div>
    <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
        <span class="page-count">{{pageNum+1}}/{{pageCount}} 페이지 </span>
        <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">다음</button>
    </div>
  </template>
  <template v-else>
    <h2>댓글이 없습니다! 댓글을 남겨주세요!</h2>
  </template>
  </div>
</template>

<script>
import {userlikeComment,fetchEpiComment , removeComment} from '@/api/comment'
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
        goFeed(uId){
      this.$router.push(`/feed/${uId}`)
    },
      itsMe(uId) {
            if (this.userInfo.u_id == uId) {
                return true
            }
            return false
      },
        DeleteComment(cId){
      this.$swal({
        // title: '플레이리스트를 삭제하시겠습니까?',
        text: '댓글을 삭제하시겠습니까?',
        customClass: {
          container: 'swal2-container'
        },
        icon:'error',
        showCancelButton: true,
        confirmButtonText: '삭제',
        confirmButtonColor: "#fc3c44",
        cancelButtonText: '취소',
        // showCloseButton: true,
        // showLoaderOnConfirm: true
      }).then((result) => {
        if(result.value) {
          this.$swal({
            text: '댓글을 삭제했습니다.',
             customClass: {
          container: 'swal2-container'
        },
            icon: 'success',
            timer: 1300,
            showConfirmButton: false,
          })
           removeComment(cId).then(()=>{
            this.getEpiComment();
          })
        } else {
          this.$swal({
            text: '댓글을 삭제를 취소했습니다.',
            icon: 'info',
             customClass: {
          container: 'swal2-container'
        },
            timer: 1300,
            showConfirmButton: false,
          })
        }
      })
    },
    isBlockUser(uId){
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
  this.commentsList = res.data.reverse()
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
   const likeBtn = document.getElementById(`like-btn-${comment.c_id}`)
    // const likeCount = document.querySelector(`#like-cnt-${comment.c_id}`)
    // likeBtn.style.color = comment.is_like_comment ? 'crimson' : 'black'
  this.commentsList[index].is_like_comment = !this.commentsList[index].is_like_comment
    
    likeBtn.classList.toggle('comment__like')
    likeBtn.classList.toggle('comment__unlike')
if (this.commentsList[index].is_like_comment){
      this.commentsList[index].comment_good_count += 1
      // likeBtn.style.color = '#fc3c44'
    } else {
      this.commentsList[index].comment_good_count -=1
      // likeBtn.style.color ='grey'
    }

      const commentInfo = {
        c_id : comment.c_id,
        u_id : this.userInfo.u_id
    }
      userlikeComment(commentInfo)
    // this.getEpiComment();

  }

      
    },
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