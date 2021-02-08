<template>
  <b-col>
    <h4>좋아요 누른 Play List</h4>
    <!-- vod 목록 보여줄 전체 폼 -->
    <div>
      <!-- 시청중인 VOD가 없을 때 -->
      <div v-if="fetchedLikePlaylist.length == 0">
        <p>시청중인 VOD가 없습니다.</p>
      </div>
      <!-- 시청중인 VOD가 있을 때 -->
      <div v-else>
        <!-- 이전 버튼 -->
        <div></div>
        <div v-for="vod in fetchedLikePlaylist" :key="vod.v_id">
          <!-- vod poster -->
          <div>
            <img src="" alt="">
          </div>
        </div>
        <!-- 다음 버튼 -->
        <div></div>
      </div>
    </div>
  </b-col>
</template>

<script>
export default {
  computed: {
    ...mapGetters(['fetchedLikePlaylist'])
  }
}
</script>

<style>

</style>