<template>
    <div >
        <div>
            <!-- <button @click="getCurTime">현재시간?</button> -->
            <video height="300px" ref="video" id="videotag" controls="controls" @timeupdate="onTimeUpdate">
                <source :src="getVideo()" id="player" type='video/mp4'/>
            </video>
            {{comments}}
           <div style="overflow:auto; width:400px; height:150px; white-space:pre-line;" id="comment_div">
            <div v-for="comment in comments" :key="comment.c_id">
                <p v-show="comment.c_playtime <= videoCurrentTime" class="testbtn">
                  <span class="comment__time" @click="goCommentTime(comment.c_playtime)"> {{comment.c_playtime}}</span> | {{comment.u_nickname}} : {{ comment.c_contents}} | {{comment.c_upload_time}} | 
                  <i class="far fa-thumbs-up"></i>{{ comment.comment_good_count }}    
                </p>
            </div>
            </div>
            <div>
            <input type='text' id=msg v-model="userComment" placeholder="댓글을 입력하세욤"/>
            <button @click="createComment()">create</button>
            </div>

        </div>
    </div>
</template>

<script>
import { fetchEpiComment,  commentInsert } from '@/api/comment';
import {endVodWatch} from '@/api/vod';
import {mapState} from 'vuex';

export default {
    props: { 
    // 변수 : 변수타입 
    // 기록을 해주는 이유는 데이터 타입을 알기 쉽게 위해서.
    comments: Array, 
    veId : Number,
    sendcommenttime:[String,Number],
    vodEpiInfo:Object,
    // 반드시 필요하다는 의미
    // required: true,
    // 값을 전달하지 않을 경우 디폴트값 설정 가능 
   
    },
    data () {
        return {
            // showComment:false,
            selectedId:0,
            videoCurrentTime: 0,
            userComment:'',
            now: "00:00:00",
            pre_diffHeight :0,
            bottom_flag : true,
        }
    },
    created() {
        // comments 시간순으로 정렬, parseFloat(문자열 실수로 크기비교)
        this.getEpiComment();
    },
    methods:{   
    async getEpiComment() {
    try {
        const epiId = this.$route.params.id;
        console.log(epiId,this.veId,'ve_id')  
      const res = await fetchEpiComment(epiId)
      console.log(res,'Comment??')
      this.comments = res.data
      this.comments.sort(function (a,b) {
            return parseFloat(a.c_playtime) < parseFloat(b.c_playtime) ? -1 : parseFloat(a.c_playtime) > parseFloat(b.c_playtime) ? 1:0;
        })
        console.log(this.comments,'댓글?')

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
        nowTime(){
            const date = new Date();
            this.nowTime = date.getHours() + ":" + date.getMinutes()
            + ':' + date.getSeconds()
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
        // 댓글 보이기 -> 이러면 둘다 보이나,,?ㅎ
        showComment(index){
            this.selectedId = index;
            const scrollDiv = document.getElementById('comment_div');
            scrollDiv.scrollTop = scrollDiv.scrollHeight;
            return true
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
                const commentInfo = {
                    c_contents : this.userComment,
                    c_playtime : this.videoCurrentTime,
                    u_id : this.userInfo.u_id,
                    ve_id : this.veId
                }
            const res = await commentInsert(commentInfo)
            console.log(res,'댓글써졌니?')
            this.getEpiComment();
            } catch {
                console.log('댓글썼는데 실패함')
                
            }



  }
    },
    watch : {
        sendcommenttime : function() {
            this.goCommentTime(this.sendcommenttime)
        },
        // 비디오 시간을 보며 스크롤 자동으로 내리기
        videoCurrentTime :function (){
            const scrollDiv = document.getElementById('comment_div');
            scrollDiv.scrollTop = scrollDiv.scrollHeight;
        },

    },
    computed:{
         ...mapState({
      userInfo: state => state.user.userInfo,
    }),
    },
    beforeDestroy(){
        const watching = {
            // u_id: this.userInfo.u_id,
            ve_id: this.veId,
            vh_watching_time: this.$moment(this.videoCurrentTime).format('hh:mm:ss')
        }
        const end = endVodWatch(watching);
        console.log('시청기록끝',end,this.videoCurrentTime,'->',this.$moment(Number(this.videoCurrentTime)).format('hh:mm:ss'))
}
}
</script>

<style scoped>

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
</style>