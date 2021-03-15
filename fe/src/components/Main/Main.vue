<template>
  <div class="main">
    <!-- 인기순, 댓글순, 최신순 정렬 탭 -->
    <VODSections></VODSections>
    <b-col class="card2">
      <!-- 실시간 인기 댓글 유저 -->
      <BestCommentRank class="col-md-6 half-card"></BestCommentRank>
      <!-- 보고 있는 VOD -->
      <NowPlaying class="col-md-6 half-card Now-Playing"></NowPlaying>
    </b-col>
    <banner-ad class="ad-space"></banner-ad>
    <!-- 인기있는 플레이 리스트 -->
    <PopularPlayList></PopularPlayList>
  </div>
</template>

<script>
import BestCommentRank from '@/components/Main/BestCommentRank';
import NowPlaying from '@/components/Main/NowPlaying';
import PopularPlayList from '@/components/Main/PopularPlayList';
import VODSections from '@/components/Main/VODSections';
import BannerAd from '@/components/Main/BannerAd.vue';
import { mapState } from 'vuex';

export default {
  data() {
    return {
      vods: [],
    };
  },
  components: {
    VODSections,
    BestCommentRank,
    NowPlaying,
    PopularPlayList,
    BannerAd
  },
  computed:{
      ...mapState({
        userInfo: state => state.user.userInfo,
      })
  },
  created() {
    //   this.$route.push({name:'VodPopular'});
  },
  methods: {
    
  },
};
</script>

<style>
</style>