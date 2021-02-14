<template>
  <div>
      <div class="best-comm-form">
        <h4 class="page-title">실시간 베스트 댓글 유저</h4>
        <!-- 합칠 때 style에 있는 table width 속성 지우기 -->
        <table>
          <tr v-if="bestComment.length == 0">
            <td colspan="5">
              가입한 회원이 없습니다.
            </td>
          </tr>
          <tr class="comm-rank-item" v-for="(user,index) in bestComment" :key="index" v-else style="cursor:pointer">
            <td class="commRank-num" :class="{red: index < 3, nonHighLight: index >= 3}">{{index+1}}</td> 
            <!-- :class="{red:fetchBestComment(index)}" -->
            <td class="userPic">
              <div @click="goUserFeed(user.u_id)">
                <img :src="getProfile(index)" alt="" v-if="user.u_profile_pic"/>
              </div>
            </td>
            <td class="comm-user-nickName" :class="{'name-highlight': index < 3}" @click="goUserFeed(user.u_id)">{{user.u_nickname}}</td>
            <td class="comm-vod-epi" @click="goVodEpi(user.ve_id)">{{user.v_title}} {{user.ve_episode_num}}화</td>
            <td class="comm-good-cnt">
              <font-awesome-icon :icon="['fas', 'thumbs-up']" :style="{ color: '#fc3c44'}"/>
              {{user.c_good_count}}
            </td>
          </tr>
        </table>
      </div>
  </div>
</template>

<script>
import { fetchBestComment } from '@/api/comment'

export default {
  data() {
    return {
      bestComment:[],
    }
  },
  created() {
    this.getBestComment()
  },
  methods: {
    async getBestComment() {
      //getBestComment api만들어야함
      const response = await fetchBestComment();
      this.bestComment = response.data.slice(0,10);
      // console.log(this.bestComment)
    },
    getProfile(index) {
      let profile = this.bestComment[index].u_profile_pic.split('.');
      let picName = profile[0]
       return `${process.env.VUE_APP_PICTURE}profile/${picName}`;
    },
    goUserFeed(uId){
      this.$router.push(`/feed/${uId}`)
    },
    goVodEpi(veId){
      this.$router.push(`/voddetail/${veId}`)
    },
  },
  
}
</script>

<style>
</style>