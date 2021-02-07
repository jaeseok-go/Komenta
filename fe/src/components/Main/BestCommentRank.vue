<template>
  <div>
      <h4>실시간 베스트 댓글 유저</h4>
      <!-- 합칠 때 style에 있는 table width 속성 지우기 -->
      <table>
        <thead>
          <colgroup>
            <col width="5%">
            <col width="5%">
            <col width="40%">
            <col width="40%">
            <col width="10%">
          </colgroup>
        </thead>
        <tbody>
          <tr v-if="bestComment.length == 0">
            <td colspan="5">
              가입한 회원이 없습니다.
            </td>
          </tr>
          <tr v-for="(user,index) in bestComment" :key="index" v-else>
            <td class="commRank-num" :class="{red: index < 3, nonHighLight: index >= 3}">{{index+1}}</td> 
            <!-- :class="{red:fetchBestComment(index)}" -->
            <td class="userPic">
              <div>
                <img :src="getProfile(index)" width="35px" alt="" />
              </div>
            </td>
            <td>{{user.u_nickname}}</td>
            <td>{{user.v_title}} {{user.ve_episode_num}}화</td>
            <td>
              <font-awesome-icon :icon="['fas', 'thumbs-up']" :style="{ color: '#fc3c44'}"/>
              {{user.c_good_count}}
            </td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>
// import { fetchBestComment } from '@/api/comment'

export default {
  data() {
    return {
      bestComment:[
        {
          u_profile_pic:'default_profile.png',
          u_nickname:'테스트 중이지롱',
          v_title:'너에게 닿기를 시즌 1',
          ve_episode_num:1,
          c_good_count:1325
        },
        {
          u_profile_pic:'default_profile.png',
          u_nickname:'테스트 중이지롱',
          v_title:'너에게 닿기를 시즌 1',
          ve_episode_num:1,
          c_good_count:1325
        },
        {
          u_profile_pic:'default_profile.png',
          u_nickname:'테스트 중이지롱',
          v_title:'너에게 닿기를 시즌 1',
          ve_episode_num:1,
          c_good_count:1325
        },
        {
          u_profile_pic:'default_profile.png',
          u_nickname:'테스트 중이지롱',
          v_title:'너에게 닿기를 시즌 1',
          ve_episode_num:1,
          c_good_count:1325
        },
        {
          u_profile_pic:'default_profile.png',
          u_nickname:'테스트 중이지롱',
          v_title:'너에게 닿기를 시즌 1',
          ve_episode_num:1,
          c_good_count:1325
        },
        {
          u_profile_pic:'default_profile.png',
          u_nickname:'테스트 중이지롱',
          v_title:'너에게 닿기를 시즌 1',
          ve_episode_num:1,
          c_good_count:1325
        },
        {
          u_profile_pic:'default_profile.png',
          u_nickname:'테스트 중이지롱',
          v_title:'너에게 닿기를 시즌 1',
          ve_episode_num:1,
          c_good_count:1325
        }
      ],
    }
  },
  created() {
    this.getBestComment()
  },
  methods: {
    async getBestComment() {
      //getBestComment api만들어야함
      // const response = await fetchBestComment();
      // this.bestComment = response.data;
    },
    getProfile(index) {
      let profile = this.bestComment[index].u_profile_pic;
       return require(`@/assets/images/${profile}`);
    },
  },
  
}
</script>

<style>
table {
  width:50%;
}

.commRank-num {
  font-size: 30px;
  font-weight: 700;
}

.commRank-num.red {
  color: #fc3c44;
}

.commRank-num.nonHighLight {
  font-size: 25px;
  font-weight: 600;
}

.userPic img {
  border-radius: 50%;
  border:1px solid black;
}
</style>