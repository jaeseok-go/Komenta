<template>
    <div >
        <div>
            <video height="300px" ref="video" id="videotag" controls="controls" @timeupdate="onTimeUpdate"  autoplay>
                <!-- <source src="@/assets/videos/swwethome.mp4" id="player" type='video/mp4' /> -->
                <!-- <source :src="getVideo(veId)" id="player" type='video/mp4' /> -->
            </video>

           <h3>회차별 댓글</h3> 
            <div v-for="comment in comments"  :key="comment.c_id">
                <!-- c_playtime?? c_uploadtime?? -->
                <p @click="goCommentTime(comment.c_playtime)" v-show="showComment(comment.c_id)" class="testbtn">
                    {{comment.u_nickname}} : {{ comment.c_contents}} | {{ comment.comment_good_count }}
                </p>
            </div>

        </div>
    </div>
</template>

<script>

export default {
    data () {
        return {
            currentTime: 0,
            comments : [
                {
                    "c_id" : 1,
                    "c_contents":"ㅋㅋㅋㅋㅋㅋㅋㅋㅋ벌써 웃곀ㅋㅋㅋㅋㅋㅋ",
                    "c_playtime" : "00:00:05",
                    "c_upload_time" : "2020.02.03 09:03:20",
                    "comment_good_count" : 10000,
                    "u_id" : 1,
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
            }
        }
    },
    created() {
        this.currentTime = this.$refs.video.currentTime
        // const veId = this.$route.parmas.id;
        // this.getVideo(veId)
    },
    methods:{
        // 비디오 불러오기
        getVideo(veId) {
            //  vedio 회차 동영상 path "/home/ubuntu/Video/" + vedto.getVe_id() + vdto.getV_title() + vedto.getVe_episode_num() + ".mp4";
            console.log(veId)
            // return `/home/ubuntu/Video/${vod.v_id}/${vod.v_title}/${vod.ve_title}/${vod.ve_id}.mp4`;
            const path =`@/assets/videos/swwethome.mp4`
            return path
        },
        // 댓글 보이기
        showComment(cId){
            console.log(cId)
            return true
        },
        // 해당시간으로 댓글 이동
        goCommentTime(time){
            const vod = document.getElementById("videotag");
            vod.currentTime = time;
        },
        // 비디오 시간과 currentTIme 일치시킴
        onTimeUpdate(){
            this.currentTime = this.$refs.video.currentTime;
            console.log(this.currentTime);
        }
    },
    watch: {
        // Whenever the movie prop changes, fetch new data
        currentTime :  {
            // Will fire as soon as the component is created
            immediate: true,
            handler(){
                this.comments.forEach(function(comment){
                    if (comment.c_playtime <= this.currentTime) {
                        this.showComment(comment.c_id)
                    }
                })
            }
        },

      }

}
</script>

<style>
.testbtn{
  color : black;
  background-color: gray;
}
</style>