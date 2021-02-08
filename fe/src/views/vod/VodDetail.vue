<template>
  <div>
    <!-- 사이드바는 hidden -->
    <!-- <Asidebar></Asidebar> -->
    <Header></Header>
    <!-- vod -->
    <div id="appBody">
        <Video :comments="comments" :title="title" :sendcommenttime="sendcommenttime" :veId="vodEpiInfo.episodeInfo.ve_id"></Video>
        <hr>
        <!-- {{vodEpiInfo.episodeInfo}} -->
        <div>vod회차세부정보</div>
        <hr>
        <div>vod전체 내용</div>
        <!-- {{ vodInfo }} -->
        <!-- <router-link :to="{name:'VodAllEpi'}">전체회차</router-link> | <router-link :to="{name:'VodEpiComment'}">Best댓글</router-link>
        <router-view></router-view> -->
        <VodAllEpi :vodInfo="vodInfo"></VodAllEpi>
        <!-- router children 등록 VodAllEpi, VodEpiComment -->
        <Comments :comments="comments" @goCommentTime="goCommentTime"></Comments>
       
    </div>
  </div>
</template>

<script>
import Header from '@/components/common/Header';
// import Asidebar from '@/components/common/Asidebar';
import Comments from '@/views/vod/Comments';
import { fetchVodEpiDetail, fetchVodDetail } from '@/api/vod'
import { fetchEpiComment } from '@/api/comment'
import Video from '@/components/vod/Video'
import VodAllEpi from '@/components/vod/VodAllEpi'

export default {
components: { 
  Header,
  // Asidebar, 
  Comments,
  Video,
  VodAllEpi
},
name: 'VodDetail',
data(){
  return {
    // vod epi 세부정보
    vodEpiInfo : {
  "episodeInfo": {
    "ve_id": 1,
    "ve_episode_num": 1,
    "ve_contents": "contents directory",
    "ve_admin": null,
    "ve_upload_date": "2021-02-01 01:43:29",
    "v_id": 1,
    "v_title": "scream",
    "v_summary": "I'm scared",
    "v_director": "go-jae-seok",
    "v_actors": "bae-sang-woong",
    "v_age_grade": 15,
    "v_poster": "test",
    "gd_id": 0,
    "g_name": "drama",
    "gd_name": "thriller"
  },
  "vh_id": 1,
  "vh_watching_time": "00:00:00"
  },
    // vod 세부정보
    vodInfo : [],
    comments : [],
    sendcommenttime:"",
    title:''
  }
},

created(){
  this.getVodEpi();
  this.getEpiComment();
  this.getVodDetail();
  // 해당 회차 VOD 세부 내용 조회 GET
  this.title = this.vodEpiInfo.episodeInfo.v_title + this.vodEpiInfo.episodeInfo.ve_episode_num
  // response로 받은 vod정보 template에 뿌려주기
},
watch: {
    '$route'() {
      window.location.reload()
   },
},
methods : {
  goCommentTime(time) {
    this.sendcommenttime = time
  },
  async getVodEpi() {
    const epiId = this.$route.params.id;
    console.log(epiId)  
    try {
      const res = await fetchVodEpiDetail(epiId)
      console.log(res.data,'DETAIL???')
      this.vodEpiInfo = res.data
    } catch {
      console.log('에러!!')
    }
  },
   async getEpiComment() {
    const epiId = this.$route.params.id;
    console.log(epiId)  
    try {
      const res = await fetchEpiComment(epiId)
      console.log(res.data,'Comment??')
      // this.comments = res.data
    } catch {
      console.log('에러!!')
    }
  },
  async getVodDetail() {
    try {
      const res = await fetchVodDetail(this.vodEpiInfo.episodeInfo.ve_id)
      this.vodInfo = res.data
    } catch {
      console.log('에러,,,,')
    }
  },

},
};
</script>

<style scoped>
  #appBody {
      display: inline-block;
      position: fixed;
      top: 100px;
      /* background-color: red; */
      width: 100%;
  }
</style>