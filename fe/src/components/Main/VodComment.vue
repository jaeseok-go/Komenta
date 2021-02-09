<template>
  <b-col>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        &lt;
      </button>
      <button
        :disabled="pageNum >= pageCount - 1"
        @click="nextPage"
        class="page-btn"
      >
        &gt;
      </button>
    </div>
    <ul class="stage clearfix">
      <li v-if="this.commentVODs.length == 0">
        <div class="noRegister-text">등록된 VOD가 없습니다.</div>
      </li>
      <li class="scene" v-for="vod in paginatedData" :key="vod.v_id" v-else>
        <div class="movie" onclick="return true">
          <div class="poster">
            <img :src="getPoster(vod.v_id)" height="100%" alt="" />
          </div>
          <div class="info" @click="goVod(vod.v_id)">
            <header>
              <h1>{{ vod.v_title }} {{ vod.ve_episode_num }}회</h1>
              <span class="year">{{ vod.ve_upload_date }}</span>
              <!-- <span class="rating">PG</span>
            <span class="duration">130 minutes</span> -->
            </header>
            <p>
              보러가기
            </p>
          </div>
        </div>
      </li>
    </ul>
  </b-col>
</template>

<script>
import { fetchCommentVOD } from '@/api/vod';
import { fetchAllVOD } from '@/api/user';
export default {
  data() {
    return {
      pageNum:0,
      allVODInfo:[],
      commentVODs: [],
    };
  },
  created() {
    this.getcommentVOD();
    this.getVODPoster();
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 5
    }
  },
  methods: {
    //fetchCommentVOD api만들어야함
    async getcommentVOD() {
      const response = await fetchCommentVOD();
      console.log('댓글순 정렬 왜 암것도 없냐 ',response)
      this.commentVODs = response.data;
    },
    async getVODPoster(){
        const response = await fetchAllVOD();
        this.allVODInfo = response.data;
    },
    getPoster(index) {
        for (let i = 0; i < this.allVODInfo.length; i++) {
            if(this.allVODInfo[i].v_id === index) {
                const poster = this.allVODInfo[i].v_poster;
                return `http://i4b201.p.ssafy.io:7000/picture/poster/${poster}`;
            }
        }
    },
    goVod(vId) {
      this.$router.push(`/voddetail/${vId}`);
    },
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  },
  computed:{
    pageCount() {
        let listLeng = this.commentVODs.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

        if(listLeng % listSize > 0) page += 1;

        return page;
    },
    paginatedData() {
        const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;

        return this.commentVODs.slice(start, end);
        }
    }
};
</script>

<style></style>
