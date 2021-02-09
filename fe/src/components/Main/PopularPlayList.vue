<template>
  <div>
      <h4>골라보는 플레이리스트</h4>
      <!-- 플레이 리스트 타이틀, 플레이 리스트 좋아요 개수, 플레이 리스트 작성자, 플레이 리스트 안 컨텐츠 개수 -->
      <!-- pl_id, pl_name, 플레이 리스트 좋아요 개수, 플레이 리스트 작성자, 플레이 리스트 안 컨텐츠 개수-->
      <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">&lt;</button>
        <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">&gt;</button>
      </div>
      <div v-if="this.popularPlaylist.size == 0">
        <div class="noRegister-text">등록된 플레이 리스트가 없습니다.</div>
      </div>
      <div class="playList" v-for="(playList, index) in paginatedData" :key="index" v-else>
        <div class="playList-Form">
          <div class="reprePoster">
            <img :src="getPoster(index)" width="210px" height="150px">
          </div>
          <div class="userPic">
            <img :src="getUserPic(index)" width="40px">
          </div>
          <div class="plInfo">
            <p>
              {{playList.pldetail[0].pl_name}} <br>
              {{playList.u_nick_name}}'S PICK <br>
            </p>
            <p class="likeInfo">
              <font-awesome-icon :icon="[starType, 'star']" :style="{ color: '#e2c000'}"/>
              {{playList.plb_cnt}} | {{playList.pldetail.length}}개
            </p>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import { fetchPopularPlayList } from '@/api/vod'

export default {
  data() {
    return {
      pageNum:0,
      popularPlaylist:[],
      starType:'far',
    }
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 4
    }
  },
  created() {
    this.getPopularPlayList()
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    async getPopularPlayList() {
      //좋아요가 많은 순서대로 받아올수있을지? => ok
      const response = await fetchPopularPlayList();
      console.log("popular play list : ",response);
      this.popularPlaylist = response.data;
      console.log('popularPlayList 저장 : ',this.popularPlaylist)
    },
    getPoster(index) {
      var poster = this.popularPlaylist[index].pldetail[0].v_poster;
      console.log("comment poster name : ",poster)
      return `http://i4b201.p.ssafy.io:7000/picture/poster/${poster}`;
    },
    getUserPic(index) {
      const profile = this.popularPlaylist[index].u_profile_pic.split('.');
      let picName = profile[0];
      console.log("picname : ",picName);
      return `http://i4b201.p.ssafy.io:7000/picture/user/${picName}`;
    },
  },
  computed: {
    pageCount() {
      let listLeng = this.popularPlaylist.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.popularPlaylist.slice(start, end);
    }
  },
}
</script>
