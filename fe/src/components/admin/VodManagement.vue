<template>
  <b-col>
    <h4>전체 VOD</h4>
    <div class="btn-right">
      <router-link :to="{name:'VODInsert'}">VOD 추가</router-link>
    </div>
    <table style="text-align:center;">
      <colgroup>
        <col width="10%" />
        <col width="15%" />
        <col width="45%" />
        <col width="30%" />
      </colgroup>
      <thead>
        <th>등록번호</th>
        <th>이미지</th>
        <th>제목</th>
        <th>종류/장르</th>
      </thead>
      <tbody v-if="vodList.length == 0">
        <td colspan="4">
          등록된 VOD가 없습니다.
        </td>
      </tbody>
      <tbody v-for="(vod, index) in vodList" :key="index" v-else>
        <td>{{vod.v_id}}</td>
        <td><img :src="getPicPath(index)" width="50%"></td>
        <td><router-link to="">{{vod.v_title}}</router-link></td>
        <td>{{vod.g_name}}/{{vod.gd_name}}</td>
      </tbody>
    </table>
    <br>
  </b-col>
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
      this.vodList = response.data;
    },
    getPicPath(index){
      console.log('vod 정보 : ',this.vodList[index].v_poster)
      if(this.vodList[index].v_poster) {
        const picPath = require(`@/assets/images/${this.vodList[index].v_poster}`);
        console.log('poster 이름 : ',picPath)
        return picPath;
      } else {
        console.log('이미지 없음')
        return '#'
      }
    }
  },
}
</script>

<style>
  .btn-right {
    text-align: right;
  }
</style>