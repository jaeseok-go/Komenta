<template>
  <div>
    <div id="appBody">
        <Video :vodEpiInfo="vodEpiInfo" :sendcommenttime="sendcommenttime" :veId="vodEpiInfo.ve_id" :commentsList="commentsList"  @likeComment="likeComment" @unlikeComment="unlikeComment"></Video>
        <hr>
        <h3>{{vodEpiInfo.v_title}} {{vodEpiInfo.ve_episode_num}}회 </h3> <br>
        <p>{{vodEpiInfo.ve_upload_date}}</p>
        <span><img :src="getVodPoster(vodEpiInfo.v_poster)" alt="" width="200px"></span>
        <div>{{vodEpiInfo.v_summary}}</div>
        <h4>개요 : {{vodEpiInfo.g_name}}/{{vodEpiInfo.gd_name}}</h4>
        <h4>출연 : {{vodEpiInfo.v_actors}}</h4>
        <h4>연출 : {{vodEpiInfo.v_director}}</h4>
        <hr>
        <div>vod전체 내용</div>
      
        <!-- <router-link :to="{name:'VodAllEpi'}">전체회차</router-link> | <router-link :to="{name:'VodEpiComment'}">Best댓글</router-link>
        <router-view></router-view> -->
        <VodAllEpi :vodInfo="vodInfo"></VodAllEpi>
        <!-- router children 등록 VodAllEpi, VodEpiComment -->
        <Comments :commentsList="commentsList" @goCommentTime="goCommentTime" :veId="vodEpiInfo.ve_id"></Comments>
       
    </div>
  </div>
</template>

<script>
import { startVodWatch, fetchVodEpiDetail, fetchVodDetail } from '@/api/vod'
import { fetchEpiComment, userLikeComment, userUnlikeComment } from '@/api/comment'
import Video from '@/components/vod/Video'
// import VodAllEpi from '@/components/vod/VodAllEpi'
// import Comments from '@/views/vod/Comments';

export default {
components: { 
  Video,
  // VodAllEpi
  // Comments,
},
name: 'VodDetail',
data(){
  return {
    // vod epi 세부정보
    vodEpiInfo :{},
    // vod 세부정보
    vodInfo : [],
    commentsList:[],
    sendcommenttime:"",
  }
},
created(){
  this.getVodEpi();
  // this.getVodDetail();
  this.getEpiComment();
  
  // 시청기록 있으면 그냥 시작 없으면, 시청기록 만들어서 반환??
  const edID = Number(this.$route.params.id)
  console.log("edID : ",edID)
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
      console.log(res.data,'vod detail info')
      this.vodEpiInfo = res.data;
      console.log("vod epi info : ",this.vodEpiInfo);
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
      console.log(this.vodInfo,'vodInfo')
    } catch {
      console.log('vod episode 에러')
    }
  },
    async getEpiComment() {
      // console.log("epiId : ",this.$route.params.id)
      try {
      const epiId = Number(this.$route.params.id);
      console.log(epiId,'에피소드id')  
      const res = await fetchEpiComment(epiId)
      console.log(res.data,'Comment??')
      this.commentsList = res.data
      console.log("comments list : ",this.commentsList)
      // this.comments.sort(function (a,b) {
      //       return a.c_playtime < b.c_playtime ? -1 :a.c_playtime > b.c_playtime ? 1:0;
      //   })

  } catch {
    console.log('epicomment 에러!!')
    }
  },
    likeComment(commentInfo){
      userLikeComment(commentInfo)

  },
  unlikeComment(commentInfo){
      userUnlikeComment(commentInfo)
 
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