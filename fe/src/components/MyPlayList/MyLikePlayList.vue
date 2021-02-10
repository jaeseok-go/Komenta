<template>
  <div>
    <h4>내가 좋아요 누른 play list</h4>
    {{likePlayLists}}
    <div class="likeList-Form" v-for="(list, pindex) in likePlayLists" :key="pindex">
      <div class="vodList-Form">
        <!-- 이전 버튼 -->
        <div class="btn-cover displayInlineBlock">
          <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">&lt;</button>
        </div>
        {{list.pl_name}}
        <div v-for="(vod,index) in paginatedData" :key="index">
          <div>
            {{vod}}
          </div>
          <!-- vod poster -->
          <div class="vodListPoster">
            <img :src="getPoster()">
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
import {fetchLikePlaylist} from '@/api/user'

export default {
  data() {
    return {
      pageNum:0,
      likePlayLists:[]
    }
  },
  created() {
    this.getLikePlayList()
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
      }),
    pageCount() {
      let listLeng = this.likePlaylists.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.likeplaylists.slice(start, end);
    }
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    getPoster() {
      const poster = this.likePlaylists[0].v_poster;
      console.log(poster)
      return `${process.env.VUE_APP_PICTURE}poster/${poster}`
    },
      async getLikePlayList(){
    const response = await fetchLikePlaylist(this.userInfo.u_id);
    console.log("좋아요 한 플레이리스트 순 : ",response.data);
    this.likePlaylists = response.data;
    console.log(this.likePlaylists)
    }
        
    
    
  },
}
</script>

<style>

</style>