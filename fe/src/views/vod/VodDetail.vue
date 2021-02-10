<template>
  <div>
    <div id="appBody">
        <Video :vodEpiInfo="vodEpiInfo" :sendcommenttime="sendcommenttime" :veId="vodEpiInfo.episodeInfo.ve_id" :comments="comments"></Video>
        <hr>
        <h3>{{vodEpiInfo.episodeInfo.v_title}} {{vodEpiInfo.episodeInfo.ve_episode_num}}회 </h3> <br>
        <p>{{vodEpiInfo.episodeInfo.ve_upload_date}}</p>
        <span><img :src="getVodPoster(vodEpiInfo.episodeInfo.v_poster)" alt="" width="200px"></span>
        <div>{{vodEpiInfo.episodeInfo.v_summary}}</div>
        <h4>개요 : {{vodEpiInfo.episodeInfo.g_name}}/{{vodEpiInfo.episodeInfo.gd_name}}</h4>
        <h4>출연 : {{vodEpiInfo.episodeInfo.v_actors}}</h4>
        <h4>연출 : {{vodEpiInfo.episodeInfo.v_director}}</h4>
        <hr>
        <div>vod전체 내용</div>
      
        <!-- <router-link :to="{name:'VodAllEpi'}">전체회차</router-link> | <router-link :to="{name:'VodEpiComment'}">Best댓글</router-link>
        <router-view></router-view> -->
        <VodAllEpi :vodInfo="vodInfo"></VodAllEpi>
        <!-- router children 등록 VodAllEpi, VodEpiComment -->
        <Comments :comments="comments" @goCommentTime="goCommentTime" :veId="vodEpiInfo.episodeInfo.ve_id"></Comments>
       
    </div>
  </div>
</template>

<script>
import Comments from '@/views/vod/Comments';
import { startVodWatch, fetchVodEpiDetail, fetchVodDetail } from '@/api/vod'
import { fetchEpiComment } from '@/api/comment'
import Video from '@/components/vod/Video'
import VodAllEpi from '@/components/vod/VodAllEpi'

export default {
components: { 
  Comments,
  Video,
  VodAllEpi
},
name: 'VodDetail',
data(){
  return {
    // vod epi 세부정보
    vodEpiInfo :{},
    // vod 세부정보
    vodInfo : [],
    comments:[],
    sendcommenttime:"",
  }
},
created(){
  this.getVodEpi();
  // this.getVodDetail();
  this.getEpiComment();
  
  // 시청기록 있으면 그냥 시작 없으면, 시청기록 만들어서 반환??
  const edID = Number(this.$route.params.id)
  const res = startVodWatch(edID);
  console.log('시청기록 시작',res,this.$route.params.id)
  
},
watch: {
  //   '$route'() {
  //     window.location.reload()
  //  },
},
methods : {
  getVodPoster(poster){
    return `${process.env.VUE_APP_PICTURE}poster/${poster}`
  },
  goCommentTime(time) {
    this.sendcommenttime = time
  },
  async getVodEpi() {
    try {
      const epiId = Number(this.$route.params.id);
      console.log(epiId)  
      const res = await fetchVodEpiDetail(epiId)
      console.log(res.data,'DETAIL???')
      this.vodEpiInfo = res.data
      this.getVodDetail();
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
  date_ascending(a, b) {
    const dateA = new Date(a.c_playtime).getTime();
    const dateB = new Date(b.c_playtime).getTime();
    return dateA > dateB ? 1 : -1;
    },
    async getEpiComment() {
      try {
      const epiId = Number(this.$route.params.id);
      console.log(epiId,'에피소드id')  
      const res = await fetchEpiComment(epiId)
      console.log(res.data,'Comment??')
      this.comments = res.data
      console.log(this.comments)
    //   console.log(this.comments.sort(date_ascending(a, b) {
    // const dateA = new Date(a.c_playtime).getTime();
    // const dateB = new Date(b.c_playtime).getTime();
    // return dateA > dateB ? 1 : -1;
    // }))

      // this.comments.sort(function (a,b) {
      //       return parseFloat(a.c_playtime) < parseFloat(b.c_playtime) ? -1 : parseFloat(a.c_playtime) > parseFloat(b.c_playtime) ? 1:0;
      //   })

  } catch {
    console.log('epicomment 에러!!')
    }
  },

},

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
</style>