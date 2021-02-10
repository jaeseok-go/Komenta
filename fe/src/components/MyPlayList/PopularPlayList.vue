<template>
  <div>
    <h4>내가 좋아요 누른 play list</h4>
    <!-- 좋아요 누른 리스트가 없을 때 -> 인기 리스트 표출 -->
    <div v-if="likeplaylist.length == 0">
      <div class="likeList-Form" v-for="(list, pindex) in likePlayList" :key="pindex">
        <div class="vodList-Form">
          <!-- 이전 버튼 -->
          <div class="btn-cover displayInlineBlock">
            <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">&lt;</button>
          </div>
          <div v-for="(vod,index) in paginatedData(pindex)" :key="vod.v_id">
            <div>
              {{vod}}
            </div>
            <!-- vod poster -->
            <div class="vodListPoster">
              <img :src="getPoster(index)">
              <!-- vod 리뷰(mouseon) -->
            </div>
          </div>
          <!-- 다음 버튼 -->
          <div class="btn-cover">
            <button :disabled="pageNum >= pageCount(index)-1" @click="nextPage" class="page-btn">&gt;</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 좋아요 누른 리스트가 있을 때 -> 좋아요 리스트 표출 -->
    <div class="likeList-Form" v-for="(list, pindex) in likePlayList" :key="pindex" v-else>
      <div class="vodList-Form">
        <!-- 이전 버튼 -->
        <div class="btn-cover displayInlineBlock">
          <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">&lt;</button>
        </div>
        <div v-for="(vod,index) in paginatedData(pindex)" :key="vod.v_id">
          <div>
            {{vod}}
          </div>
          <!-- vod poster -->
          <div class="vodListPoster">
            <img :src="getPoster(index)">
            <!-- vod 리뷰(mouseon) -->
          </div>
        </div>
        <!-- 다음 버튼 -->
        <div class="btn-cover">
          <button :disabled="pageNum >= pageCount(index)-1" @click="nextPage" class="page-btn">&gt;</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { fetchPopularPlayList } from '@/api/vod';
export default {
  data() {
    return {
      pageNum:0,
      popularPlayLists:[],
    }
  },
  created() {
    this.$store.dispatch('FETCH_LIKEPLAYLIST',this.userInfo.u_id)
    this.getPopularPlayList();
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 6
    }
  },
  computed: {
    ...mapState({
        userInfo: state => state.user.userInfo,
        likePlaylist:state => state.user.likePlaylist
      }),
    pageCount(index) {
      let listLeng = this.likePlaylist[index].length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData(index) {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.likeplaylist[index].slice(start, end);
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
      const poster = this.likePlaylist[index][0].v_poster;
      // console.log(poster)
      return `${process.env.VUE_APP_PICTURE}poster/${poster}`
    },
    async getPopularPlayList(){
      const response = await fetchPopularPlayList();
      console.log("좋아요가 많은 플레이리스트 순 : ",response.data);
      this.popularPlayLists = response.data;
    },
  },
}
</script>

<style>

</style>