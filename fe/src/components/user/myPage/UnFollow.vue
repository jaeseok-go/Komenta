<template>
  <b-col>
    <div class="follow-form">
      <h4>차단 유저 관리</h4>
      <div class="drop-zone">
        <!-- 최근 본 VOD 리스트 중 하나씩 v-for돌림 -->
        <div class="drop-zone__inner">
          <div v-for="(follow, index) in unfollowings" :key="index" class="f-drag-el">
            <span> <!--  @click="goFeed(follow.f_id)" -->
              <img :src="getProfile(follow.u_profile_pic)" width="100%" />
              <p class="f-name">{{ follow.u_nickname }}</p>
            </span>
          </div>
        </div>
      </div>
    </div>
<!-- 
    0. 검색창 <br>
    1. 언팔로우 유저의 프로필<br>
    2. 언팔로우 유저의 닉네임<br>
    3. 팔로우, 삭제 버튼<br>
     -->
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
      console.log('unfollowing list : ',response)
      this.unfollowings = response.data;
      console.log('나의 unfollowings', this.unfollowings);
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