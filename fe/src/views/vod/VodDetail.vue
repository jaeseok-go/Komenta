<template>
  <div>
    <div id="appBody">
         <div class="video">
            <video @loadstart="goLastVod" height="500px" ref="video" id="videotag" controls="controls" @timeupdate="onTimeUpdate">
                <source :src="getVideo()" id="player" type='video/mp4'/>
            </video>
            
           <div id="comment_div">
             <div class="comment__scroll" id="comment__scroll">
            <div v-for="(comment,index) in commentsList" :key="index" @mouseover.middle="stopScroll" class="comment__text">
                <p v-show="comment.c_playtime <= nowTime(videoCurrentTime)" class="testbtn" :class="userFollowing(comment.u_id)">
                  <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}}</span>  <span @click="goFeed(comment.u_id)" class="comment__nickname">{{comment.u_nickname}} </span> {{ comment.c_contents}}  
                                                                                                                   <!-- "[isActive ? activeClass : '', errorClass]" -->
                  <span @click="commentLike(comment)"><i class="far fa-thumbs-up" :id="`like-btn-${comment.c_id}`" :class="[comment.is_like_comment ? 'commet__like' :' comment__unlike' ]" style="cursor:pointer"></i>
                  <!-- <span :id="`like-cnt-${comment.c_id}`">{{ comment.comment_good_count }}</span> -->
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
        <!-- <hr> -->
        <!-- <div class="videoepi">
        <h3>{{vodEpiInfo.v_title}} {{vodEpiInfo.ve_episode_num}}회 </h3>
        <span><img :src="getVodPoster(vodEpiInfo.v_poster)" alt="" width="200px"></span>
        <div>{{vodEpiInfo.v_summary}}</div>
        <h4>개요 : {{vodEpiInfo.g_name}}/{{vodEpiInfo.gd_name}}</h4>
        <h4>출연 : {{vodEpiInfo.v_actors}}</h4>
        <h4>연출 : {{vodEpiInfo.v_director}}</h4>
        </div> -->
        <hr>
        <router-link :to="{name:'BestComments'}"  >베스트 댓글</router-link> | 
        <router-link :to="{name:'AllComments'}">전체 댓글</router-link>
        <router-view  @goCommentTime="goCommentTime"></router-view>
       
       
    </div>
  </div>
</template>

<script>
import { startVodWatch, fetchVodEpiDetail, fetchVodDetail, endVodWatch} from '@/api/vod'
import { fetchEpiComment, userlikeComment, commentInsert } from '@/api/comment'
import {mapState} from 'vuex';
// import VodAllEpi from '@/components/vod/VodAllEpi'




export default {
components: { 
  // Comments,
  // Video,
  // VodAllEpi
},
name: 'VodDetail',
data(){
  return {
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
  }
},
created(){
  this.getVodEpi();
  // this.getVodDetail();
  this.getEpiComment();
  this.$store.dispatch('FETCH_FOLLOWING',this.userInfo.u_id)

  this.startWatchTime();
  
  
},
methods : {
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
      // this.getVodDetail();
    } catch {
      console.log('vod epi detail에러!!')
    }
  },
   
  async getVodDetail() {
    try {
      console.log(this.vodEpiInfo.v_id,'vod아이디')
      const res = await fetchVodDetail(this.vodEpiInfo.v_id)
      this.vodInfo = res.data
      console.log(this.vodInfo,'?????')
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
           return {
             comment__highlight: true
            }        
        
          }
        }
        return {
          comment__highlight: false
        }
        
      },

  //유저 댓글 좋아요 class추가/제거
  commentLike(comment){
    const likeBtn = document.querySelector(`#like-btn-${comment.c_id}`)
    // const likeCount = document.querySelector(`#like-cnt-${comment.c_id}`)

    // likeBtn.style.color = comment.is_like_comment ? 'crimson' : 'black'
    if (comment.is_like_comment) {
      // likeCount.innerText = comment.comment_good_count - 1
      likeBtn.style.color ='grey'
      } else {
        // likeCount.innerText = comment.comment_good_count + 1
        likeBtn.style.color = '#fc3c44'
      }
      const commentInfo = {
        c_id : comment.c_id,
        u_id : this.userInfo.u_id
    }
      userlikeComment(commentInfo)

  }


},
   watch : {
        // 비디오 시간을 보며 스크롤 자동으로 내리기
        videoCurrentTime :function (){
            const scrollDiv = document.getElementById('comment__scroll');
            scrollDiv.scrollTop = scrollDiv.scrollHeight;
        },
        
    },
    computed:{
         ...mapState({
      userInfo: state => state.user.userInfo,
      myFollowingList: state => state.user.myFollowingList
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
.comment__scroll::-webkit-scrollbar {
    width: 15px;
    height: 18px;
  }

   .comment__scroll::-webkit-scrollbar-button {
    width: 0;
    height: 0;
    display: none;
  }

  .comment__scroll::-webkit-scrollbar-corner {
    background-color: transparent;
  }
  .comment__scroll::-webkit-scrollbar-thumb {
    height: 6px;
    border: 4px solid transparent;
    background-clip: padding-box;
    -webkit-border-radius: 100px;
    background-color:rgb(194, 190, 190);
  }
  .comment__scroll {
  overflow: auto;
  white-space: pre-line;
  width: 400px;
  height: 425px;
  /* height: 100%; */
  }
  #appBody {
      width: 100%;
  }
  
#comment_div {
    /* width: 400px;
    height: 500px; */
    /* position: relative; */
    /* border: 1px solid rgb(179, 173, 173); */
    display: inline-block;
    background-color: #eee;
}
.video{
  font-family:Georgia, 'Times New Roman', Times, serif;
}
.video__comment{
  padding-left: 5%;
  display: inline-block;
  /* position: absolute; */
  /* position: sticky; */
  width: 100%;
  height: 80px;
  background-color: white;
}
.video__comment__box{
    margin-top:25px;
    display: inline-block;
    width: 50px;
    height: 50px; 
    border-radius: 50%;
    overflow: hidden;
}
.video__comment__profile{
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.video__comment__inner {
  padding-left: 5%;
  width: 85%;
  display: inline-block;
}
.video__comment__inner__nickname{
  color:	#C0C0C0;
  font-weight: bold;
}
.comment__time {
  color: darkgray;
  cursor: pointer;
  text-decoration: none;
}
.comment__highlight{
  /* background-color: yellow; */
  color: #fc3c44;
  font-weight: bold;
}
.commet__like{
  color :#fc3c44;

}
.comment__unlike{
  color: gray;
}
.video__comment__input{
  width: 80%;
  background-color: white;
  border-left-width:0;
  border-right-width:0;
  border-top-width:0;
  border-bottom:black 1px solid;
  
}
.comment__text{

}
.comment__nickname{
  color: gray;
  cursor: pointer;
}
</style>