<template>
  <div>
    <!-- 인기순, 댓글순, 최신순 정렬 탭 -->
    <VODSections></VODSections>
    <!-- 실시간 인기 댓글 유저 -->
    <BestCommentRank class="col-md-6"></BestCommentRank>

    <!-- 보고 있는 VOD -->
    <NowPlaying class="col-md-6"></NowPlaying>
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
        // {
        //   g_name: '드라마',
        //   gd_id: 0,
        //   gd_name: '호러',
        //   v_id: 0,
        //   v_title: '스위트홈',
        //   ve_admin: 'string',
        //   ve_contents: 'string',
        //   ve_episode_num: 1,
        //   ve_id: 0,
        //   ve_upload_date: '2021-02-06',
        // },
        // {
        //   g_name: '드라마',
        //   gd_id: 0,
        //   gd_name: '로맨스',
        //   v_id: 0,
        //   v_title: '사랑의불시착',
        //   ve_admin: 'string',
        //   ve_contents: 'string',
        //   ve_episode_num: 1,
        //   ve_id: 0,
        //   ve_upload_date: '2021-02-06',
        // },
        // {
        //   g_name: '예능',
        //   gd_id: 0,
        //   gd_name: '버라이어티',
        //   v_id: 0,
        //   v_title: '런닝맨',
        //   ve_admin: 'string',
        //   ve_contents: 'string',
        //   ve_episode_num: 550,
        //   ve_id: 0,
        //   ve_upload_date: '2021-02-06',
        // },
        // {
        //   g_name: '드라마',
        //   gd_id: 0,
        //   gd_name: '호러',
        //   v_id: 0,
        //   v_title: '스위트홈',
        //   ve_admin: 'string',
        //   ve_contents: 'string',
        //   ve_episode_num: 1,
        //   ve_id: 0,
        //   ve_upload_date: '2021-02-06',
        // },
        // {
        //   g_name: '드라마',
        //   gd_id: 0,
        //   gd_name: '로맨스',
        //   v_id: 0,
        //   v_title: '사랑의불시착',
        //   ve_admin: 'string',
        //   ve_contents: 'string',
        //   ve_episode_num: 1,
        //   ve_id: 0,
        //   ve_upload_date: '2021-02-06',
        // },
        // {
        //   g_name: '예능',
        //   gd_id: 0,
        //   gd_name: '버라이어티',
        //   v_id: 0,
        //   v_title: '런닝맨',
        //   ve_admin: 'string',
        //   ve_contents: 'string',
        //   ve_episode_num: 550,
        //   ve_id: 0,
        //   ve_upload_date: '2021-02-06',
        // },