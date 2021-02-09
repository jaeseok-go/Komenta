<template>
  <div>
    <!-- 사이드바는 hidden -->
    <!-- <Asidebar></Asidebar> -->
    <Header></Header>
    <!-- vod -->
    <div id="appBody">
        <Video :vodEpiInfo="vodEpiInfo" :sendcommenttime="sendcommenttime" :veId="vodEpiInfo.episodeInfo.ve_id"></Video>
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
    vodEpiInfo :{},
    // vod 세부정보
    vodInfo : [],

    sendcommenttime:"",
  }
},
created(){
  this.getVodEpi();

  this.getVodDetail();
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
      console.log('vod epi detail에러!!')
    }
  },
   
  async getVodDetail() {
    try {
      const res = await fetchVodDetail(this.vodEpiInfo.episodeInfo.v_id)
      this.vodInfo = res.data
      console.log(this.vodInfo,'?????')
    } catch {
      console.log('vod episode 에러')
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