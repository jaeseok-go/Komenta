<template>
  <div>
      <div class="best-comm-form">
        <h4>실시간 베스트 댓글 유저</h4>
        <!-- 합칠 때 style에 있는 table width 속성 지우기 -->
        <table>
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
                <img :src="getProfile(index)" alt="" />
              </div>
            </td>
            <td>{{user.u_nickname}}</td>
            <td>{{user.v_title}} {{user.ve_episode_num}}화</td>
            <td>
              <font-awesome-icon :icon="['fas', 'thumbs-up']" :style="{ color: '#fc3c44'}"/>
              {{user.c_good_count}}
            </td>
          </tr>
        </table>
      </div>
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
      let profile = this.bestComment[index].u_profile_pic.split('.');
      let picName = profile[0]
       return `${process.env.VUE_APP_PICTURE}profile/${picName}`;
    },
  },
  
}
</script>

<style>
</style>