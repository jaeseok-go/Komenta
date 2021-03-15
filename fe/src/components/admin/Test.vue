<template>
    <div>
        <h3>Video 입니다.</h3>
        <video ref="video" id="videotag" controls="controls" @timeupdate="onTimeUpdate"  autoplay>
            <source :src="`${process.env.VUE_APP_VIDEO}스위트홈3`" id="player" type='video/webm' />
        </video>
        <b-button @click="a(5)" v-show="showcomment" class="testbtn">5초로 이동하는 댓글</b-button>

    </div>
</template>
<style>
.testbtn{
  color : black;
  background-color: gray;
}
</style>
<script>

export default {
    data(){
        return{
            currentTime: 0,
            time:5,
            showcomment : false,
        }
    },
    methods:{
        a(count){
            var vid = document.getElementById("videotag");
            vid.currentTime = count;
        },
        onTimeUpdate(){
            this.currentTime = this.$refs.video.currentTime;
            console.log(this.currentTime);
        }
    },
    watch: {
        currentTime: function(){
            console.log(this.showcomment);
            if(this.currentTime >= this.time){
                this.showcomment = true;
            }
            if(this.$refs.video.currentTime >= this.time){
                this.showcomment = true;
            }
        }
        
      }
}
</script>