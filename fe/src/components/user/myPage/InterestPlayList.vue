<template>
  <b-col>
    <h4>관심 리스트 관리(레이아웃 배치 생각 중)</h4>
    <hr>
    1. 리스트 명<br>
    2. 리스트 만든 유저 닉네임<br>
    3. 편집 버튼 클릭 시, 리스트 삭제 기능<br>
  </b-col>
</template>

<script>
// import {fetchLikePlaylist} from '@/api/user';
export default {
  data() {
    return {
      playList:[],
    }
  },
  created() {
    // this.getPlayList();
  },
  methods: {
    // async getPlayList() {
      // const response = await fetchLikePlaylist();
      // console.log("playList: ",response)
    // }
  },
}
</script>

<style>

</style>