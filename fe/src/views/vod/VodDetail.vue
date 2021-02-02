<template>
  <div>
    <!-- 사이드바는 hidden -->
    <Asidebar></Asidebar>
    <Header></Header>
    <!-- vod -->
    <div id="appBody">
        1-1. 해당회차 VOD보여주기 <br>
        GET(ve_id) -> res(비디오 불러오기)<br>
        <!-- 현재 vod시간을 알 방법이 있을까?  -->
        <div>VOD</div>
        <!-- 댓글컴포넌트 -->
        <div>
          <!-- v-for -->
          <!-- 현재 vod runtime 이후의 것을 v-for -->
          <div>
            <!-- 댓글정보를 props로 줌 :commentInfo="해당 runtime 댓글 한개 정보 아니면 댓글 id를 줌" -->
            <Comments ></Comments>
          </div>

        </div>
        1-2. 해당회차 세부내용<br>
        GET(ve_id) -> res(v_id,회차,날짜,vod회차세부내용,)<br>
        {{vodEpiInfo.episodeInfo}}
        <div>vod회차세부정보</div>
        3. 해당 VOD 전체 세부 내용<br>
        GET(v_id) -> res(vod이름(v_title),vod세부내용,vod장르 대분류/소분류,vod출연진,vod감독,)<br>
        <div>vod전체 내용</div>
        <!-- router children 등록 VodAllEpi, VodEpiComment -->
        <!-- <router-link :to="{name:'VodAllEpi'}">전체회차</router-link> | <router-link :to="{name:'VodEpiComment'}">Best댓글</router-link>
        <router-view></router-view> -->
        
       
    </div>
  </div>
</template>

<script>
import Header from '@/components/common/Header';
import Asidebar from '@/components/common/Asidebar';
import Comments from '@/views/vod/Comments';
import { fetchVodEpiDetail } from '@/api/vod'

export default {
components: { 
  Header,
  Asidebar, 
  Comments,
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
    vodInfo : {}
  }
},

created(){
  this.getVodEpi();
  // 해당 회차 VOD 세부 내용 조회 GET
  // this.vodEpiInfo = getVodEpi(ve_id)
  // this.vodInfo = getVod(v_id);
  // response로 받은 vod정보 template에 뿌려주기
},
watch: {
    '$route'() {
      window.location.reload()
   },
},
methods : {
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
  }
},
};
</script>

<style scoped>
  #appBody {
      display: inline-block;
      position: fixed;
      top: 100px;
      background-color: red;
      width: 100%;
  }
</style>