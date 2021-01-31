<template>
  <div>
      <h1>골라보는 플레이리스트</h1>
      <!-- 플레이 리스트 타이틀, 플레이 리스트 좋아요 개수, 플레이 리스트 작성자, 플레이 리스트 안 컨텐츠 개수 -->
      <!-- pl_id, pl_name, 플레이 리스트 좋아요 개수, 플레이 리스트 작성자, 플레이 리스트 안 컨텐츠 개수-->
      <div>{{ this.popularPlaylist }}</div>
  </div>
</template>

<script>
import { fetchPopularPlayList } from '@/api/vod'

export default {
  data() {
    return {
      popularPlaylist:[]
    }
  },
  methods: {
    getPopularPlayList() {
      //좋아요가 많은 순서대로 받아올수있을지? => ok
      this.popularPlaylist = fetchPopularPlayList()
    }
  },
  created() {
    this.getPopularPlayList()
  },

}
</script>

<style>

</style>