<template>
  <div>
    <div id="appBody">
         <div class="video">
            <video @loadstart="goLastVod" class="video__height" ref="video" id="videotag" controls="controls" @timeupdate="onTimeUpdate" width="100%" height="100%">
                <source :src="getVideo()" id="player" type='video/mp4'/>
            </video>
            
           <div id="comment_div">
             <div class="comment__scroll" id="comment__scroll">
            <div v-for="(comment,index) in commentsList" :key="index" @mouseover.middle="stopScroll" class="comment__text">
                <p v-show="comment.c_playtime <= nowTime(videoCurrentTime)" class="testbtn" :class=" {comment__highlight:userFollowing(comment.u_id),comment__hidden:userBlocking(comment.u_id)}">
                  <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}}</span>  <span @click="goFeed(comment.u_id)" class="comment__nickname">{{comment.u_nickname}} </span> {{ comment.c_contents}}  
                  <span @click="commentLike(index,comment)"><i class="far fa-thumbs-up" :id="`like-btn-${comment.c_id}`" :class="[comment.is_like_comment ? 'commet__like' :' comment__unlike' ]" style="cursor:pointer"></i>
                  <span :id="`like-cnt-${comment.c_id}`">{{ comment.comment_good_count }}</span>
                  </span>    
                </p>
            </div>
            </div>
            <div class="video__comment">
              <div class="video__comment__box"><img :src="getPoster()" alt="" class="video__comment__profile"></div>
              <div class="video__comment__inner">
                <span class="video__comment__inner__nickname">{{userInfo.u_nickname}}</span> <br>
                <div><input type='text' class="video__comment__input" id=msg v-model="userComment" placeholder="댓글을 입력하세요" @keydown.enter="createComment()"/>
                <span @click="createComment()"><i class="far fa-paper-plane"></i></span></div>
              </div>
            </div>
            </div>
        </div>
        <hr>
        <!-- <div v-show="!showEpiDetail" @click="showEpi()" class="vodepi__button"> <span class="vodepi__button__title"> <strong>추가 정보 더보기</strong> <i class="fas fa-sort-down"></i></span> </div>
        <div v-show="showEpiDetail"> -->
        <div class="vodepi">
        <div class="vodepi__img"><img :src="getVodPoster(vodEpiInfo.v_poster)" alt="" width="200px"></div>
        <div class="vodepi__detail">
        <h3> <strong> {{vodEpiInfo.v_title}} {{vodEpiInfo.ve_episode_num}}회</strong> </h3>
        {{vodEpiInfo.v_summary}} <br>
        <p></p>
        <strong>개요</strong> {{vodEpiInfo.g_name}}/{{vodEpiInfo.gd_name}} <br>
        <strong>출연</strong> {{vodEpiInfo.v_actors}} <br>
        <strong>감독</strong> {{vodEpiInfo.v_director}}
       <!-- </div>
        </div> -->
        
        <!-- <div @click="showEpi()" class="vodepi__button"><strong>닫기</strong> <i class="fas fa-sort-up"></i></div> -->
        </div>
        <hr>
        <br>
        <div v-show="!showVodDetail" @click="showVod()" class="vodepi__button"> <span class="vodepi__button__title"> <strong>전체 회차 더보기</strong> <i class="fas fa-sort-down"></i></span> </div>
        <div v-show="showVodDetail">
        <div><h4><strong>전체회차</strong></h4></div>
        <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          <font-awesome-icon :icon="['fas', 'angle-left']"/>
        </button>
        <span class="page-count">{{pageNum+1}}/{{pageCount}} </span>
        <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">
          <font-awesome-icon :icon="['fas', 'angle-right']"/>
        </button>
      </div>
        <div class="vod__detail">
        <div v-for="(vod,index) in paginatedData" :key="index" class="vod__detail__column">
          <div class="at-user vod__detail__img" @click="goVodEpi(vod.ve_id)"><img :src="getVodPoster(vodEpiInfo.v_poster)" alt="" height="150px"></div>
          <div class="vod__detail__num">{{vod.ve_episode_num}}회 </div>
        </div>
        </div>
        <div @click="showVod()" class="vodepi__button"><strong>닫기</strong> <i class="fas fa-sort-up"></i></div>
        </div>
        </div>
        <br>
       
        <div class="comments__container"> 
        <router-link :to="{name:'BestComments'}" active-class="comments__menu">
          <i class="fas fa-check"></i> BEST </router-link>
        <router-link :to="{name:'AllComments'}" active-class="comments__menu">
        <i class="fas fa-check"></i> 전체 댓글 </router-link>
        <router-view  @goCommentTime="goCommentTime"></router-view>
       </div>
       
    </div>
  </div>
</template>

<script>
import { startVodWatch, fetchVodEpiDetail, fetchVodDetail, endVodWatch} from '@/api/vod'
import { fetchEpiComment, userlikeComment, commentInsert } from '@/api/comment'
import {mapState} from 'vuex';


export default {
components: { 
  // Comments,
  // Video,
},
name: 'VodDetail',
data(){
  return {
    pageNum:0,
    // vod epi 세부정보
    vodEpiInfo :{},
    // vod 세부정보
    vodInfo : [],
    commentsList:[],
    selectedId:0,
    videoCurrentTime: 0,
    userComment:'',
    now: "00:00:00",
    pre_diffHeight :0,
    bottom_flag : true,
    // followingComment:false
    showEpiDetail:true,
    showVodDetail:true
  }
},
created(){
  this.getVodEpi();
  // this.getVodDetail();
  this.getEpiComment();
  this.$store.dispatch('FETCH_FOLLOWING',this.userInfo.u_id)
  this.$store.dispatch('FETCH_UNFOLLOWING',this.userInfo.u_id)

  this.startWatchTime();
  
  
},
methods : {
  goVodEpi(veId){
    this.$router.push(`/voddetail/${veId}`).catch(error => {
    if(error.name === "NavigationDuplicated" ){
        location.reload();
    }
});
  },
  nextPage() {
      this.pageNum += 1;
  },
  prevPage() {
    this.pageNum -= 1;
  },
  showVod(){
    if (this.showVodDetail) {
      this.showVodDetail = false
    } else {
      this.showVodDetail = true
    }
  },
  showEpi(){
    if (this.showEpiDetail) {
      this.showEpiDetail = false
    } else {
      this.showEpiDetail = true
    }
  },
  getPoster(){
    const profile = this.userInfo.u_profile_pic.split('.')
    return `${process.env.VUE_APP_PICTURE}profile/${profile[0]}`
  },
  startWatchTime(){
    const edID = Number(this.$route.params.id)
    const res = startVodWatch(edID);
    console.log('시청기록 시작',res,this.$route.params.id)
  },
  goLastVod(){
    const endtime = document.getElementById("videotag");
    if (this.vodEpiInfo.vh_watching_time) {
      endtime.currentTime = this.timeToSec(this.vodEpiInfo.vh_watching_time)
      console.log('시청기록시간부터 시작?',this.vodEpiInfo.vh_watching_time)
    }
  },
  goFeed(uId){
    this.$router.push(`/feed/${uId}`)
  },
  getVodPoster(poster){
    return `${process.env.VUE_APP_PICTURE}poster/${poster}`
  },
  async getVodEpi() {
    try {
      const epiId = Number(this.$route.params.id);
      console.log(epiId)  
      const res = await fetchVodEpiDetail(epiId)
      this.vodEpiInfo = res.data
      console.log('VODEPI 상세 정보',this.vodEpiInfo)
      this.getVodDetail();
    } catch {
      console.log('vod epi detail에러!!')
    }
  },
   
  async getVodDetail() {
    try {
      console.log(this.vodEpiInfo.v_id,'vod아이디')
      const res = await fetchVodDetail(this.vodEpiInfo.v_id)
      this.vodInfo = res.data
    } catch {
      console.log('vod episode 에러')
    }
  },
    async getEpiComment() {
    try {
        const epiId = this.$route.params.id; 
      const res = await fetchEpiComment(epiId)
      this.commentsList = res.data
      this.commentsList.sort(function (a,b) {
            return a.c_playtime < b.c_playtime ? -1 :a.c_playtime > b.c_playtime ? 1:0;
        })
        console.log(this.commentsList,'댓글?')

    } catch {
      console.log('epicomment 에러!!')
    }
  },
      onscroll() {
        const objDiv = document.getElementById("comment_div");

        if((objDiv.scrollTop + objDiv.clientHeight) == objDiv.scrollHeight){
                // 채팅창 전체높이 + 스크롤높이가 스크롤 전체높이와 같다면
                // 이는 스크롤이 바닥을 향해있다는것이므로
                // 스크롤 바닥을 유지하도록 플래그 설정
                this.bottom_flag = true;
        }

        if(this.pre_diffHeight > objDiv.scrollTop + objDiv.clientHeight ){
            // 스크롤이 한번이라도 바닥이 아닌 위로 상승하는 액션이 발생할 경우
            // 스크롤 바닥유지 플래그 해제
            this.bottom_flag = false;  
        }
                //
            // this.pre_diffHeight = objDiv.scrollTop + objDiv.clientHeight
        // }

        },
        nowTime(num){
            let myNum = parseInt(num, 10);
            let hours   = Math.floor(myNum / 3600);
            let minutes = Math.floor((myNum - (hours * 3600)) / 60);
            let seconds = myNum - (hours * 3600) - (minutes * 60);

            if (hours   < 10) {hours   = "0"+hours;}
            if (minutes < 10) {minutes = "0"+minutes;}
            if (seconds < 10) {seconds = "0"+seconds;}
            return hours+':'+minutes+':'+seconds;
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
            getCurTime() { 
            const vod = document.getElementById("videotag");
            alert(vod.currentTime,'현재시간?');
        },
        // 비디오 불러오기
        getVideo() {
            const path =`${process.env.VUE_APP_VIDEO}${this.vodEpiInfo.gd_id}_${this.vodEpiInfo.v_title.replace(/(\s*)/g, "")}_${this.vodEpiInfo.ve_episode_num}화`
            console.log(path,'동영상주소')
            return path
        },
        // 해당시간으로 댓글 이동
        goCommentTime(time){
            const vod = document.getElementById("videotag");
            vod.currentTime = time;
    
        },
        // 비디오 시간과 currentTIme 일치시킴
        onTimeUpdate(){
            const vod = document.getElementById("videotag");
            this.videoCurrentTime = vod.currentTime;
        },
        async createComment() {
            try {
                const vod = document.getElementById("videotag");
                console.log(vod.currentTime,'댓글시간등록')
                const commentInfo = {
                    c_contents : this.userComment,
                    c_playtime : this.nowTime(vod.currentTime),
                    u_id : this.userInfo.u_id,
                    ve_id : this.$route.params.id
                }
            const res = await commentInsert(commentInfo)
            console.log(res,'댓글써졌니?')
            this.getEpiComment();
            this.userComment=""
            } catch {
                console.log('댓글썼는데 실패함')
                
            }



  },
  scrollEvent (){
    window.__scrollPosition = document.documentElement.scrollTop || 0;
    document.addEventListener('scroll', function() {
      let _documentY = document.documentElement.scrollTop;
      let _direction = _documentY - window.__scrollPosition >= 0 ? 1 : -1;
      console.log(_direction); // 콘솔창에 스크롤 방향을 출력

      window.__scrollPosition = _documentY; // Update scrollY
});
  },
  // 팔로잉들 댓글 강조
  userFollowing(uId){
        for (let i = 0; i < this.myFollowingList.length; i++) {
        const following = this.myFollowingList[i];
        if (following.f_id == uId) {
          //  return {
          //    comment__highlight: true
          //   }        
        
        return true
          }
        }
        // return {
        //   comment__highlight: false
        // }
        return false
        
      },
    // 차단한 댓글 강조
  userBlocking(uId){
    // console.log(this.myUnFollowingList,'언팔리스트')
     for (let i = 0; i < this.myUnFollowingList.length; i++) {
        const blocking = this.myUnFollowingList[i];
        if (blocking.f_id == uId) {
          //  return {
          //    comment__hidden: true
          //   }        
          return true
        
          }
        }
        // return {
        //   comment__hidden: false
        // }
        return false
        
  },

  //유저 댓글 좋아요 class추가/제거
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
      // this.getEpiComment();

  }

},
   watch : {
        // 비디오 시간을 보며 스크롤 자동으로 내리기
        videoCurrentTime :function (){
            const scrollDiv = document.getElementById('comment__scroll');
            scrollDiv.scrollTop = scrollDiv.scrollHeight;
        },
        
    },
    props: {
    pageSize: {
      type: Number,
      required: false,
      default: 4
    }
  },
    computed:{
    pageCount() {
      let listLeng = this.vodInfo.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.vodInfo.slice(start, end);
    },
         ...mapState({
      userInfo: state => state.user.userInfo,
      myFollowingList: state => state.user.myFollowingList,
      myUnFollowingList: state => state.user.myUnFollowingList,
    }),
    },
    beforeDestroy(){
      const watching = {
          // u_id: this.userInfo.u_id,
          ve_id: this.vodEpiInfo.ve_id,
          vh_watching_time: this.nowTime(this.videoCurrentTime)
      }
      const end = endVodWatch(watching);
      console.log('시청기록끝',watching,end,this.videoCurrentTime,'->',this.nowTime(this.videoCurrentTime))
}
};
</script>

<style scoped>
</style>