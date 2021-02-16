<template>
  <div class="category-component">
    <h4>{{vodList[0].g_name}}</h4>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-left']"/>
      </button>
      <span class="page-count">{{pageNum+1}}/{{pageCount}} </span>
      <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-right']"/>
      </button>
    </div>
    <div class="category-img-form" v-for="vod in paginatedData" :key="vod.v_id">
        <span class="category-img" @click="goVodDetail(vod.v_id)">
            <img :src="getPoster(vod.v_poster)">
        </span>
        <span class="category-title">{{getTitle(vod.v_title)}}</span>
    </div>
  </div>
</template>

<script>
import { fetchVodDetail } from '@/api/vod'
export default {
  data() {
    return {
      pageNum:0,
    }
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 6
    },
    vodList: {
      type: Array
    }
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    async goVodDetail(vId){
        console.log(vId,'vod가장 첫 epi로 이동')
        const res = await fetchVodDetail(vId)
        // VOD의 가장 첫 epi로 보내기
        this.$router.push(`/voddetail/${res.data[0].ve_id}`)
    },
    getPoster(path){
        return `${process.env.VUE_APP_PICTURE}poster/${path}`
    },
    getTitle(title) {
        if(title.length >= 11) return title.substring(0,11)+'...';
        else return title;
    }
  },
  computed: {
    pageCount() {
      let listLeng = this.vodList.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.vodList.slice(start, end);
    }
  }
}
</script>

<style>

</style>