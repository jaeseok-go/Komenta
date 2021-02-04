<template>
    <div >
        <div>
            <!-- <button @click="getCurTime">현재시간?</button> -->
            <video height="300px" ref="video" id="videotag" controls="controls" @timeupdate="onTimeUpdate" autoplay>
                <source :src="getVideo()" id="player" type='video/mp4'/>
            </video>

           <h3>회차별 댓글</h3> 
           <input type="text" v-model="userComment">
           <div style="overflow:auto; width:500px; height:150px;" id="comment_div">
            <div v-for="comment in comments" :key="comment.c_id">
                <p v-show="comment.c_playtime <= videoCurrentTime" class="testbtn">
                  <span @click="goCommentTime(comment.c_playtime)"> ({{comment.c_playtime}})</span> | {{comment.c_upload_time}} | {{comment.u_nickname}} : {{ comment.c_contents}} | {{ comment.comment_good_count }}
                </p>
            </div>
            </div>

        </div>
    </div>
</template>

<script>
import { commentInsert } from '@/api/comment'

export default {
    props: { 
    // 변수 : 변수타입 
    // 기록을 해주는 이유는 데이터 타입을 알기 쉽게 위해서.
    // comments: Array, 
    veId : Number,
    // 반드시 필요하다는 의미
    // required: true,
    // 값을 전달하지 않을 경우 디폴트값 설정 가능 
   
    },
    data () {
        return {
            // showComment:false,
            selectedId:0,
            videoCurrentTime: 0,
            sortcomments:[],
            comments : [
                {
                    "c_id" : 1,
                    "c_contents":"ㅋㅋㅋㅋㅋㅋㅋㅋㅋ벌써 재밌겠다ㅋㅋㅋㅋㅋㅋ",
                    // "c_playtime" : "00:00:05",
                    "c_playtime" : "5",
                    "c_upload_time" : "2020.02.03 09:03:20",
                    "comment_good_count" : 10000,
                    "u_id" : 1,
                    "u_nickname" : "아롱롱"
                },
            
                {
                    "c_id" : 2,
                    "c_contents":"스위트홈 웹툰 정주행 하러 가야지~~~~",
                    "c_playtime" : "20",
                    // "c_playtime" : "00:00:20",
                    "c_upload_time" : "2020.12.03 09:03:20",
                    "comment_good_count" : 30000,
                    "u_id" : 2,
                    "u_nickname" : "롱롱롱"
                },
                {
                    "c_id" : 3,
                    "c_contents":"아 깜짝이야....진심 무섭겠다ㅠㅠㅠㅠㅠㅠㅠ",
                    "c_playtime" : "23",
                    // "c_playtime" : "00:00:20",
                    "c_upload_time" : "2020.12.23 09:43:20",
                    "comment_good_count" : 99999,
                    "u_id" : 3,
                    "u_nickname" : "우리조는 1등"
                },
                {
                    "c_id" : 4,
                    "c_contents":"ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ",
                    // "c_playtime" : "00:00:05",
                    "c_playtime" : "5",
                    "c_upload_time" : "2020.02.03 09:03:20",
                    "comment_good_count" : 10000,
                    "u_id" : 3,
                    "u_nickname" : "아롱롱"
                },
                {
                    "c_id" : 5,
                    "c_contents":"ㅋㅋㅋㅋㅋㅋㅋㅋㅋ웹툰보다별로,,ㅋㅋㅋ",
                    // "c_playtime" : "00:00:05",
                    "c_playtime" : "5.5",
                    "c_upload_time" : "2020.02.03 09:03:20",
                    "comment_good_count" : 10000,
                    "u_id" : 4,
                    "u_nickname" : "아롱롱"
                },
                {
                    "c_id" : 6,
                    "c_contents":"ㅋㅋㅋㅋㅋㅋㅋㅋㅋ벌써 재밌겠다ㅋㅋㅋㅋㅋㅋ",
                    // "c_playtime" : "00:00:05",
                    "c_playtime" : "6",
                    "c_upload_time" : "2020.02.03 09:03:20",
                    "comment_good_count" : 10000,
                    "u_id" : 17,
                    "u_nickname" : "아롱롱"
                },
                {
                    "c_id" : 7,
                    "c_contents":"ㅋㅋㅋㅋㅋㅋㅋㅋㅋ벌써 재밌겠다ㅋㅋㅋㅋㅋㅋ",
                    // "c_playtime" : "00:00:05",
                    "c_playtime" : "5.1",
                    "c_upload_time" : "2020.02.03 09:03:20",
                    "comment_good_count" : 10000,
                    "u_id" : 14,
                    "u_nickname" : "아롱롱"
                },
            ],
            video: {
                sources: [{
                    // src: 'https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm',
                    src: '@/assets/videos/sweethome.mp4',
                    type: 'video/mp4'
                }],
                options: {
                    autoplay: true,
                    volume: 0.6,
                    poster: 'http://covteam.u.qiniudn.com/poster.png'
                }
            },
                userComment:'',
                now: "00:00:00"
        }
    },
    created() {
        this.comments.sort(function (a,b) {
            return parseFloat(a.c_playtime) < parseFloat(b.c_playtime) ? -1 : parseFloat(a.c_playtime) > parseFloat(b.c_playtime) ? 1:0;
        })
        console.log(this.comments)
        // this.videoCurrentTime = this.$refs.video.currentTime
        // console.log(this.videoCurrentTime, this.$refs.video.currentTime)
        // const veId = this.$route.parmas.id;
        // this.getVideo(veId)
    },
    methods:{   
        // addDiv(){
        //     const div = document.createElement('div');
        //     comments.foreach(comment => )
        //     div.innerText= this.commennts;
        //     document.getElementById('comment_div').appendChild(div);
        // },
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
            //  vedio 회차 동영상 path "/home/ubuntu/Video/" + vedto.getVe_id() + vdto.getV_title() + vedto.getVe_episode_num() + ".mp4";
            // console.log(veId)
            // return `/home/ubuntu/Video/${vod.v_id}/${vod.v_title}/${vod.ve_title}/${vod.ve_id}.mp4`;
            const path =require(`@/assets/videos/sweethome.mp4`)
            return path
        },
        // 댓글 보이기 -> 이러면 둘다 보이나,,?ㅎ
        showComment(index){
            this.selectedId = index;
            // console.log(cId)
            return true
        },
        // 해당시간으로 댓글 이동
        goCommentTime(time){
            const vod = document.getElementById("videotag");
            vod.currentTime = time;
            // console.log(typeof vod.currentTime,typeof time)
        },
        // 비디오 시간과 currentTIme 일치시킴
        onTimeUpdate(){
            const vod = document.getElementById("videotag");
            this.videoCurrentTime = vod.currentTime;
            // this.videoCurrentTime = this.$refs.video.currentTime;
            // console.log(this.videoCurrentTime,vod.currentTime);
        },
        async createComment() {
        try {
        // c_contents(댓글 내용), u_playtime(등록 시간), u_id(회원 아이디), ve_id(vod 회차 아이디)
        const commentInfo = {
            c_contents : this.userComment,
            u_playtime : this.nowTime,
            // 댓글 등록 시간?? 비디오시간?
            c_playtime : this.videoCurrentTime,
            // c_upload_time : "string",
            u_id : this.$store.state.userInfo.u_id,
            ve_id : this.veId
        }
        const res = await commentInsert(commentInfo)
        console.log(res)
        } catch {
        console.log('댓글썼는데 실패함')
        }
  }
    },
    watch : {
    // videoCurrentTime : function(newValue, oldValue) {
    //     if (newValue == 1) {
    //     // 1보다 커지는 경우 실행
    //     }
    //     else if (newValue > 5) {
    //     // 5보다 커지는 경우에만 실행
    //     }
    // }
    }

}
</script>

<style>
.testbtn{
  color : black;
  background-color: gray;
}
</style>