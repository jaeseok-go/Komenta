<template>
  <b-col>
    <h4>언팔로우 관리(차단)</h4>
    <hr>
    <div class="inline-block">
      <h4 class="inline-block">Unfollowing 관리</h4>
      <div class="inline-block">
        <input type="text">
        <button>검색</button>
      </div>
      <div class="follow-card">
        <div v-for="(follow, index) in unfollowings" :key="index">
          <div class="inline-block">
            <img :src=follow.f_img>
          </div>
          <div class="inline-block">{{follow.f_nickname}}</div>
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
export default {
  data() {
    return {
      unfollowings:[
        // {
        //   f_img:'#',
        //   f_nickname: 'Kim SSAFY'
        // },
        // {
        //   f_img:'#',
        //   f_nickname: 'Park Deajeon'
        // },
        // {
        //   f_img:'#',
        //   f_nickname: 'Choi Seoul'
        // },
        // {
        //   f_img:'#',
        //   f_nickname: 'Kim SSAFY'
        // },
        // {
        //   f_img:'#',
        //   f_nickname: 'Park Deajeon'
        // },
        // {
        //   f_img:'#',
        //   f_nickname: 'Kim SSAFY'
        // },
        // {
        //   f_img:'#',
        //   f_nickname: 'Park Deajeon'
        // },
        // {
        //   f_img:'#',
        //   f_nickname: 'Choi Seoul'
        // },
        // {
        //   f_img:'#',
        //   f_nickname: 'Kim SSAFY'
        // },
        // {
        //   f_img:'#',
        //   f_nickname: 'Park Deajeon'
        // },
      ],
    }
  },
  methods: {
    fetchUnFollowingList(){
      this.unfollowings = this.$store.state.myUnFollowingList;
    }
  },
}
</script>

<style>
  .inline-block{
    display: inline-block;
  }

  .follow-card {
    height: 100px;
    overflow: auto;
    -ms-overflow-style: none;
    scrollbar-width: none;
  }

  .follow-card::-webkit-scrollbar {
    display: none;
  }
</style>