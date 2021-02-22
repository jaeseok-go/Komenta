<template>
  <b-col>
    <div class="follow-form">
      <h4>차단 유저 관리</h4>
      <div class="drop-zone">
        <!-- 최근 본 VOD 리스트 중 하나씩 v-for돌림 -->
        <div class="drop-zone__inner">
          <div v-for="(follow, index) in unfollowings" :key="index" class="f-drag-el">
            <span>
              <img :src="getProfile(follow.u_profile_pic)" width="100%" />
              <p class="f-name">{{ follow.u_nickname }}</p>
            </span>
          </div>
        </div>
      </div>
    </div>
  </b-col>
</template>

<script>
import { fetchunfollowinglist } from '@/api/user'
export default {
  data() {
    return {
      unfollowings:[],
    }
  },
  props: ['getUserId'],
  created() {
    this.fetchUnFollowingList(this.getUserId);
  },
  methods: {
    async fetchUnFollowingList(userId){
      const response = await fetchunfollowinglist(userId);
      this.unfollowings = response.data;
    },
    getProfile(profile) {
      const path = profile.split('.');
      return `${process.env.VUE_APP_PICTURE}profile/${path[0]}`;
    },
  },
}
</script>

<style>
  .inline-block{
    display: inline-block;
  }

</style>