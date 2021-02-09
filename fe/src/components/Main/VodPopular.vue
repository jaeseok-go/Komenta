<template>
  <b-col>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">&lt;</button>
      <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">&gt;</button>
    </div>
    <ul class="stage clearfix">
      <li v-if="this.popularVODs.length == 0">
        <div class="noRegister-text">등록된 VOD가 없습니다.</div>
      </li>
      <li class="scene" v-for="(vod,index) in paginatedData" :key="index" v-else>
        <div class="movie" onclick="return true">
          <div class="poster">
            <img :src="getPoster(vod.v_id)" height="100%" alt="" />
          </div>
          <div class="info" @click="goVod(vod.ve_id)">
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
import { fetchPopularVOD } from '@/api/vod';
import { fetchAllVOD } from '@/api/user';
export default {
  data() {
    return {
      pageNum:0,
      popularVODs: [],
      allVODInfo:[],
    }
  },
  created() {
    this.getPopularVOD();
    this.getVODPoster();
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 5
    }
  },
  methods:{
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    async getPopularVOD() {
      const response = await fetchPopularVOD();
      console.log('인기순 정렬 : ', response);
      this.popularVODs = response.data;
    //   console.log(this.popularVODs);
    },
    async getVODPoster(){
        const response = await fetchAllVOD();
        console.log('vod poster list : ',response);
        this.allVODInfo = response.data;
        // console.log(this.allVODInfo);
    },
    getPoster(index) {
        for (let i = 0; i < this.allVODInfo.length; i++) {
            if(this.allVODInfo[i].v_id === index) {
                const poster = this.allVODInfo[i].v_poster;
                return `http://i4b201.p.ssafy.io:7000/picture/poster/${poster}`;
            }
        }
    },
    goVod(veId) {
      this.$router.push(`/voddetail/${veId}`);
    }
  },
  computed: {
    pageCount() {
      let listLeng = this.popularVODs.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.popularVODs.slice(start, end);
    }
  }
}
</script>

<style>
@import '../../../public/css/component.css';
@import '../../../public/css/demo.css';
@import '../../../public/css/normalize.css';
.col {
  position: relative;
  bottom:65px;
}

.scene{
  width: 200px;
  height: 330px;
  margin: 30px 25px 10px 25px;
}

.movie {
  width: 200px;
  height: 330px;
}

.movie .poster, .movie .info {
  width: 200px;
  height: 330px;
}

.movie .poster {
  transform: translateZ(100px);
}

.movie .info {
  transform: rotateY(90deg) translateZ(100px);
}

.csstransforms3d .movie::after {
  width: 200px;
  height: 200px;
  transform: rotateX(90deg) translateY(99px);
  box-shadow: 0 22px 33px rgb(0 0 0 / 30%);
}

.noRegister-text {
  position: relative;
  top: 50px;
}


.btn-cover {
  text-align: right;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>