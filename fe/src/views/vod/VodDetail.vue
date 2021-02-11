<template>
  <div>
    <div id="appBody">
        <!-- <Video :vodEpiInfo="vodEpiInfo" :sendcommenttime="sendcommenttime" :veId="vodEpiInfo.episodeInfo.ve_id" :commentsList="commentsList"  @likeComment="likeComment" @unlikeComment="unlikeComment"></Video> -->
         <div>
            <!-- <button @click="getCurTime">현재시간?</button> -->
            <video @loadstart="goLastVod" height="300px" ref="video" id="videotag" controls="controls" @timeupdate="onTimeUpdate">
                <source :src="getVideo()" id="player" type='video/mp4'/>
            </video>
           <div style="overflow:auto; width:400px; height:300px; white-space:pre-line;" id="comment_div">
            <div v-for="(comment,index) in commentsList" :key="index">
                <p v-show="comment.c_playtime <= nowTime(videoCurrentTime)" class="testbtn" :class="userFollowing(comment.u_id)">
                  <span class="comment__time" @click="goCommentTime(timeToSec(comment.c_playtime))"> {{comment.c_playtime}}</span> | <span @click="goFeed(comment.u_id)">{{comment.u_nickname}} </span>: {{ comment.c_contents}} | {{comment.c_upload_time}} | 
                  <span @click="likeComment(comment.c_id)"><i class="far fa-thumbs-up"></i>{{ comment.comment_good_count }}</span>    
                </p>
            </div>
            </div>
            <div>
            <input type='text' id=msg v-model="userComment" placeholder="댓글을 입력하세욤" @keydown.enter="createComment()"/>
            <button @click="createComment()">create</button>
            </div>

        </div>
        <hr>
        <h3>{{vodEpiInfo.episodeInfo.v_title}} {{vodEpiInfo.episodeInfo.ve_episode_num}}회 </h3> <br>
        <p>{{vodEpiInfo.episodeInfo.ve_upload_date}}</p>
        <span><img :src="getVodPoster(vodEpiInfo.episodeInfo.v_poster)" alt="" width="200px"></span>
        <div>{{vodEpiInfo.episodeInfo.v_summary}}</div>
        <h4>개요 : {{vodEpiInfo.episodeInfo.g_name}}/{{vodEpiInfo.episodeInfo.gd_name}}</h4>
        <h4>출연 : {{vodEpiInfo.episodeInfo.v_actors}}</h4>
        <h4>연출 : {{vodEpiInfo.episodeInfo.v_director}}</h4>
        <hr>
        <div>베스트 댓글</div>
        <Comments :commentsList="commentsList" @goCommentTime="goCommentTime" :veId="vodEpiInfo.episodeInfo.ve_id"></Comments>
      
        <!-- <router-link :to="{name:'VodAllEpi'}">전체회차</router-link> | <router-link :to="{name:'VodEpiComment'}">Best댓글</router-link>
        <router-view></router-view> -->
        <!-- <VodAllEpi :vodInfo="vodInfo"></VodAllEpi> -->
        <!-- router children 등록 VodAllEpi, VodEpiComment -->
       
    </div>
  </div>
</template>

<script>
import Comments from '@/views/vod/Comments';
// import {fetchfollowinglist} from '@/api/user';
import { startVodWatch, fetchVodEpiDetail, fetchVodDetail, endVodWatch} from '@/api/vod'
import { fetchEpiComment, userLikeComment, userUnlikeComment, commentInsert } from '@/api/comment'
// import Video from '@/components/vod/Video'
import {mapState} from 'vuex';
// import VodAllEpi from '@/components/vod/VodAllEpi'




export default {
components: { 
  Comments,
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
  console.log(this.userInfo.u_id,'유저아이디')
  this.$store.dispatch('FETCH_FOLLOWING',this.userInfo.u_id)

  // 시청기록 있으면 그냥 시작 없으면, 시청기록 만들어서 반환??
  const edID = Number(this.$route.params.id)
  const res = startVodWatch(edID);
  console.log('시청기록 시작',res,this.$route.params.id)
  
},
methods : {
  goLastVod(){
    const endtime = document.getElementById("videotag");
    if (this.vodEpiInfo.vh_watching_time) {
      endtime.currentTime = this.timeToSec(this.vodEpiInfo.vh_watching_time)
      console.log('이게되나?',this.vodEpiInfo.vh_watching_time)
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
      console.log(this.vodEpiInfo.episodeInfo.v_id,'vod아이디')
      const res = await fetchVodDetail(this.vodEpiInfo.episodeInfo.v_id)
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
  console.log(this.myFollowingList,'나의 팔로잉')
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
            const path =`${process.env.VUE_APP_VIDEO}${this.vodEpiInfo.episodeInfo.gd_id}_${this.vodEpiInfo.episodeInfo.v_title.replace(/(\s*)/g, "")}_${this.vodEpiInfo.episodeInfo.ve_episode_num}화`
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
                console.log(vod.currentTime,'????댓글시간등록')
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
  likeComment(cId){
      const commentInfo = {
        c_id : cId,
        u_id : this.userInfo.u_id
      }
      userLikeComment(commentInfo)
      
  },
  unlikeComment(cId){
      const commentInfo = {
        c_id : cId,
        u_id : this.userInfo.u_id
      }
      userUnlikeComment(commentInfo)
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


},
   watch : {
        // 비디오 시간을 보며 스크롤 자동으로 내리기
        videoCurrentTime :function (){
            const scrollDiv = document.getElementById('comment_div');
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
          ve_id: this.veId,
          vh_watching_time: this.nowTime(this.videoCurrentTime)
      }
      const end = endVodWatch(watching);
      console.log('시청기록끝',end,this.videoCurrentTime,'->',this.nowTime(this.videoCurrentTime))
}
};
</script>

<style scoped>
  #appBody {
      overflow-y: scroll;
      display: inline-block;
      position: fixed;
      top: 100px;
      /* background-color: red; */
      width: 100%;
  }
  
#comment_div {
    white-space: pre;
    overflow-y: scroll;
    width:500px;
    height:200px;
    border: 1px solid black;
    display: inline-block;
}

.comment__time {
  color: blue;
  cursor: pointer;
  text-decoration: none;
}
.comment__highlight{
  background-color: yellow;
}
</style>