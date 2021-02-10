<template>
  <div>
    <h4>내가 좋아요 누른 play list</h4>
    <!-- 좋아요 누른 리스트가 없을 때 -> 인기 리스트 표출 -->
    <div v-if="historyList.length == 0">
      <div class="likeList-Form" v-for="(list, index) in likePlayLists" :key="index">
        <div class="vodList-Form">
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
              <!-- vod 리뷰(mouseon) -->
            </div>
          </div>
          <!-- 다음 버튼 -->
          <div class="btn-cover">
            <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">&gt;</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 좋아요 누른 리스트가 있을 때 -> 좋아요 리스트 표출 -->
    <div class="likeList-Form" v-for="(list, index) in likePlayLists" :key="index" v-else>
      <div class="vodList-Form">
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
            <!-- vod 리뷰(mouseon) -->
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
import { fetchLikePlaylist } from '@/api/user';
export default {
  data() {
    return {
      pageNum:0,
      likePlayLists:[],
      popularPlayLists:[]
    }
  },
  created() {
    this.fetchLikePlayList();
    this.fetchPopularPlayList();
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
      const poster = this.episodeList[index].v_poster;
      // console.log(poster)
      return `${process.env.VUE_APP_PICTURE}poster/${poster}`
    },
    async fetchLikePlayList(){
      const response = await fetchLikePlaylist(this.userInfo.u_id);
      console.log("좋아요 누른 리스트 정보 : ",response);
      this.likePlayLists = response.data;
    },
    async fetchPopularPlayList() {
      // /playlist/bestplist
    },
  },
}
</script>

<style>

</style>