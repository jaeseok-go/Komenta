<template>
  <b-container>
    <h4>전체 VOD</h4>
    <table>
      <tr>
        <td>등록번호</td>
        <td>제목</td>
        <td>종류/장르</td>
        <td>등록일</td>
        <td>담당자</td>
      </tr>
      <tr v-if="vodList.length == 0">
        <td colspan="4">
          등록된 VOD가 없습니다.
        </td>
      </tr>
      <!-- 페이징 처리...어케해... -->
      <tr v-for="(vod, index) in vodList" :key="index" v-else>
        <td>{{vod.v_id}}</td>
        <td>{{vod.v_title}}</td>
        <td>{{vod.g_name}}/{{vod.gd_name}}</td>
          <!-- 등록일, 담당자 추가 -->
        <!-- <td>{{vod.}}</td> -->
      </tr>
    </table>
    <br>
  </b-container>
</template>

<script>
import {fetchAllVOD} from '@/api/user';
export default {
  data() {
    return {
      vodList:[],
    }
  },
  created() {
    this.getVODList();
  },
  methods: {
    async getVODList() {
      const response = await fetchAllVOD();
      console.log("vod list : ",response);
    }
  },
}
</script>

<style>

</style>