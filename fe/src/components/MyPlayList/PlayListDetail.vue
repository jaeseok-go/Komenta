<template>
  <div>
      <!-- 여기 플레이리스트 타이틀, 플레이 리스트 대표사진, 플레이 리스트 작성자, 플레이 리스트 안에 콘텐츠들(각각 장르, 제목, 몇화인지, 러닝타임)-->
      <h1>스트리밍 리스트 디테일 페이지</h1>
      <div>
          포스터 / 타이틀 / 즐겨찾기(좋아요) 버튼 / 닉네임
      </div>

      <div>

      </div>
      

  </div>
</template>

<script>
import { fetchPlayListDetail } from '@/api/vod'
import store from '@/stores/modules/user'

export default {
    data() {
        return {
            streamingListDetail:{},
        }
    },
    methods: {
        getStreamingListDetail() {
            const u_id = store.state.userInfo.u_id
            const pl_id = this.$route.params.id;
            const data = {u_id, pl_id}
            this.streamingListDetail = fetchPlayListDetail(data)
        }
    },
    created() {
        this.getStreamingListDetail()
    },

}
</script>

<style>

</style>