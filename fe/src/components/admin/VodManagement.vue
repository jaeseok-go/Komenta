<template>
  <b-col>
    <h4>전체 VOD</h4>
    <div class="btn-right">
      <router-link :to="{name:'VODInsert'}">VOD 추가</router-link>
    </div>
    <table style="text-align:center;">
      <thead>
        <th>등록번호</th>
        <th>제목</th>
        <th>종류/장르</th>
        <th>등록일</th>
        <th>담당자</th>
        <th>설정</th>
      </thead>
      <tbody v-if="vodList.length == 0">
        <td colspan="6">
          등록된 VOD가 없습니다.
        </td>
      </tbody>
      <tbody v-for="(vod, index) in paginatedData" :key="index" v-else>
        <td>{{vod.ve_id}}</td>
        <td><p @click="goVod(vod.ve_id)">{{vod.v_title}}</p></td>
        <td>{{vod.g_name}}/{{vod.gd_name}}</td>
        <td>{{vod.ve_upload_date}}</td>
        <td>{{vod.ve_admin}}</td>
        <td>수정 / 삭제</td>
      </tbody>
    </table>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
      <span class="page-count">{{pageNum+1}}/{{pageCount}} 페이지 </span>
      <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">다음</button>
    </div>
  </b-col>
</template>

<script>
import {fetchAllEpi} from '@/api/vod';
export default {
  data() {
    return {
      pageNum:0,
      vodList:[],
    }
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 10
    },
  },
  created() {
    this.getVODList();
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    async getVODList() {
      const response = await fetchAllEpi();
      console.log("vod list : ",response);
      this.vodList = response.data;
    },
    goVod(veId) {
      this.$router.push(`/voddetail/${veId}`);
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
  .btn-right {
    text-align: right;
  }
</style>