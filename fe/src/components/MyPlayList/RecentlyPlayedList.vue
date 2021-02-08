<template>
  <div>
    <h4>최근 시청한 VOD 목록</h4>
    <!-- vod 목록 보여줄 전체 폼 -->
    <div class="rPlayList-Form">
      <!-- 시청중인 VOD가 없을 때 -->
      <div v-if="historyList.length == 0">
        <p>시청중인 VOD가 없습니다.</p>
      </div>
      <!-- 시청중인 VOD가 있을 때 -->
      <div v-else>
        <!-- 이전 버튼 -->
        <div class="btn-cover displayInlineBlock">
          <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">&lt;</button>
        </div>
        <div v-for="(vod,index) in paginatedData" :key="vod.v_id">
          <!-- <div>
            {{vod}}
          </div> -->
          <!-- vod poster -->
          <div class="vodListPoster">
            <img :src="getPoster(index)">
            <!-- vod 개요(mouseon) -->
          </div>
        </div>
        <!-- 다음 버튼 -->
        <div class="btn-cover">
          <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">&gt;</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { fetchRecentPlaylist } from '@/api/user';
export default {
  data() {
    return {
      pageNum:0,
      historyList:[],
      episodeList:[]
    }
  },
  created() {
    this.fetchRPlayList();
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 6
    }
  },
  computed: {
    // ...mapGetters(['fetchedRecentPlaylist']),
    ...mapState({
        userInfo: state => state.user.userInfo,
      }),
    pageCount() {
      let listLeng = this.historyList.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.historyList.slice(start, end);
    }
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    getPoster(index) {
      const poster = String(this.episodeList[index].gd_id+'_'+ this.episodeList[index].v_title);
      console.log("popular poster name : ",poster)
      return `http://i4b201.p.ssafy.io:7000/picture/poster/${poster}`;
      //this.vod_all.gd_id+'_'+this.vod_all.v_title+'.jpg' //포스터 이름
      //this.vod_all.gd_id+'_'+vodReplace+"_"+this.vod_all.ve_episode_num+'화.mp4' //vod 영상 이름
      // return require(`http://i4b201.p.ssafy.io:7000/Picture/Poster/${poster}`);
    },
    async fetchRPlayList(){
      const response = await fetchRecentPlaylist(this.userInfo.u_id);
      console.log("최근 시청 VOD 정보 : ",response);
      this.historyList = response.data.historyList;
      this.episodeList = response.data.episodeList;
    },
  },
}
</script>

<style>

</style>