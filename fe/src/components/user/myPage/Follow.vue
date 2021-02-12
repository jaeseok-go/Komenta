<template>
  <b-col class="follow-setting">
    <h3>팔로우 관리</h3>
    <div class="follow-form">
      <h4>Follower 관리</h4>
      <div class="drop-zone">
        <!-- 최근 본 VOD 리스트 중 하나씩 v-for돌림 -->
        <div class="drop-zone__inner">
          <div v-for="(follow, index) in followers" :key="index" class="drag-el">
            <span @click="goFeed(follow.f_id)">
              <img :src="getProfile(follow.u_profile_pic)" width="100%" />
              <p>{{ follow.u_nickname }}</p>
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="follow-form">
      <h4>Followings 관리</h4>
      <div class="drop-zone">
        <!-- 최근 본 VOD 리스트 중 하나씩 v-for돌림 -->
        <div class="drop-zone__inner">
          <div v-for="(follow, index) in followings" :key="index" class="drag-el">
            <span @click="goFeed(follow.f_id)">
              <img :src="getProfile(follow.u_profile_pic)" width="100%" />
              <p>{{ follow.u_nickname }}</p>
            </span>
          </div>
        </div>
      </div>
    </div>
  </b-col>
</template>
<script>
import { fetchfollowinglist, fetchfollowerlist } from '@/api/user';
export default {
  data() {
    return {
      followings: [],
      followers: [],
    };
  },
  props: ['getUserId'],
  created() {
    this.fetchFollowingList(this.getUserId);
    this.fetchfollowerlist(this.getUserId);
  },
  methods: {
    goFeed(uId) {
      this.$router.push(`/feed/${uId}`);
    },
    getProfile(profile) {
      const path = profile.split('.');
      return `${process.env.VUE_APP_PICTURE}profile/${path[0]}`;
    },
    async fetchFollowingList(userId) {
      console.log('getUserId', userId);
      const response = await fetchfollowinglist(userId);
      this.followings = response.data;
      console.log('나의 followings', this.followings);
    },
    async fetchfollowerlist(userId) {
      console.log('getUserId', userId);
      const response = await fetchfollowerlist(userId);
      this.followers = response.data;
      console.log('나의 followers', this.followers);
    },
  },
};
</script>

<style></style>
