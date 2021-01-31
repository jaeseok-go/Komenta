<template>
  <b-col>
    <h4>팔로우 관리</h4>
    <hr>
    <div class="inline-block followForm">
      <h4 class="inline-block">Follower 관리</h4>
      <div class="inline-block">
        <input type="text">
        <button>검색</button>
      </div>
      <div class="follow-card">
        <div v-for="(follow, index) in followings" :key="index">
          <div class="inline-block">
            <img :src=follow.f_img>
          </div>
          <div class="inline-block">{{follow.f_nickname}}</div>
        </div>
      </div>
    </div>
    <!-- 1. 나를 팔로우<br>
      1-0. 검색창<br>
      1-1. 팔로우 유저의 프로필<br>
      1-2. 팔로우 유저의 닉네임<br>
      1-3. 팔로우 버튼, 차단 버튼<br> -->
    <div class="inline-block followForm">
      <h4 class="inline-block">Following 관리</h4>
      <div class="inline-block">
        <input type="text">
        <button>검색</button>
      </div>
      <div class="follow-card">
        <div v-for="(follow, index) in followers" :key="index">
          <div class="inline-block">
            <img :src=follow.f_img>
          </div>
          <div class="inline-block">{{follow.f_nickname}}</div>
        </div>
      </div>
    </div>
    <!-- 2. 내가 팔로우<br>
      2-0. 검색창<br>
      2-1. 팔로우 유저의 프로필<br>
      2-2. 팔로우 유저의 닉네임<br>
      2-3. 삭제, 차단 버튼<br> -->
  </b-col>
</template>
<script>
export default {
  data() {
    return {
      followings:[
        {
          f_img:'#',
          f_nickname: 'Kim SSAFY'
        },
        {
          f_img:'#',
          f_nickname: 'Park Deajeon'
        },
        {
          f_img:'#',
          f_nickname: 'Choi Seoul'
        },
        {
          f_img:'#',
          f_nickname: 'Kim SSAFY'
        },
        {
          f_img:'#',
          f_nickname: 'Park Deajeon'
        },
      ],
    followers:[
      {
        f_img:'#',
        f_nickname: 'Choi Seoul'
      },
      {
        f_img:'#',
        f_nickname: 'Kim SSAFY'
      },
      {
        f_img:'#',
        f_nickname: 'Park Deajeon'
      },
      {
        f_img:'#',
        f_nickname: 'Choi Seoul'
      },
      {
        f_img:'#',
        f_nickname: 'Kim SSAFY'
      },
      {
        f_img:'#',
        f_nickname: 'Park Deajeon'
      },
      {
        f_img:'#',
        f_nickname: 'Choi Seoul'
      }
    ]
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

  .followForm {
    width: 50%;
  }
</style>