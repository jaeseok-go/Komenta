<template>
    <div class="container">
        <div class="container__following">
        <!-- 프로필 팔로잉 사진 목록 -->
        <!-- {{this.following_list}} -->
        <!-- {{this.updateFollowPlaylist}} -->
            <div class="container__following__Profle">
                <!-- 버튼 누르기 -->
                <!-- <div class="btn-cover">
                    <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
                        <font-awesome-icon :icon="['fas', 'angle-left']"/>
                    </button>
                    <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">
                        <font-awesome-icon :icon="['fas', 'angle-right']"/>
                    </button>
                </div> -->
                <div v-for="(user,index) in this.following_list" :key="index">
                    <div @click="gotoFeed(user.f_id)" class="container__following__Profle__background"><img :src="getProfile(index)" class="container__following__Profle__img"></div>
                </div>
            </div>
        
            
            <div class="at-section">
                <div class="at-section__title"><span class="at-section__nickname">{{userInfo.u_nickname}}</span>님의 친구들의 새로운 소식을 확인해보세요</div>
                <span class="at-section__subtitle">친구들의 플레이리스트를 확인하고, 취향에 맞는 VOD를 추천받아보세요!</span>
            </div>
            <div class="at-grid basic-scroll">
                <div class="at-column" v-for="(user,index) in this.following_list" :key="index">
                    <div class="at-user">
                        <div class="at-user__avatar" :src="user.u_profile_pic"></div>
                        <div class="at-user__name">{{user.u_nickname}}</div>
                        <div class="at-user__title"><img :src="getProfile(index)" width="100" height="100"></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- <div class="container__influencer">
            <div class="at-section">
                <div class="at-section__title popular-title at-section__nickname">KOMENTA TOP9 DJ</div>
                <span class="at-section__subtitle">{{userInfo.u_nickname }}님의 취향에 맞는 스트리밍DJ를 팔로우해보세요!</span>
            </div>
            <div class="at-grid basic-scroll">
                <div class="at-column" v-for="(user,index) in this.top9" :key="index">
                    <div class="at-user">
                        <div class="at-user__avatar" :src="user.u_profile_pic"></div>
                        <div class="at-user__name">{{user.u_nickname}}</div>
                        <div class="at-user__title">{{user.u_profile_pic}}</div>
                    </div>
                </div>
            </div>
        </div> -->
    </div>

</template>

<script>
import { top9followers,fetchfollowinglist,updateFollowPlaylist} from '@/api/user'
// import { mapState } from 'vuex'
import store from '@/stores/modules/user'

export default {
    data() {
        return {
            // pageNum:0,
            following_list:[],
            top9:[],
            updateFollowPlaylist:[],
            // pageSize:5,
    }},
    props: {
        pageSize: {
        type: Number,
        required: false,
        default: 5
        }
    },
    computed: {
        userInfo() {
            return store.state.userInfo;
        },
        // pageCount() {
        //     let listLeng = this.following_list.length;
        //     console.log(listLeng,'리스트렝,,,')
        //     listSize = this.pageSize;
        //     page = Math.floor(listLeng / listSize);

        //     if(listLeng % listSize > 0) page += 1;

        //     return page;
        // },
        // paginatedData() {
        //     const start = this.pageNum * this.pageSize;
        //     end = start + this.pageSize;

        //     return this.following_list.slice(start,end);
        // }
    },
    created() {
        this.getFollowing()
        this.getTopInfluencer()
        this.getUpdateFollowPlaylist()
    },
    methods: {
      gotoFeed(userId) {
          this.$router.push(`/feed/${userId}`)
      },
      nextPage() {
          this.pageNum +=1;
      },
      prevPage() {
          this.pageNum -=1;
      },
      getProfile(index) {
        //   console.log(index)
          const profile = this.following_list[index].u_profile_pic.split('.')[0]
          console.log(`${process.env.VUE_APP_PICTURE}profile/${profile}`)
          return `${process.env.VUE_APP_PICTURE}profile/${profile}`;
      },
      async getUpdateFollowPlaylist() {
          const response = await updateFollowPlaylist()
          console.log(response.data,'리스펀스 데이타 ㅋㅋㅋ')
          this.updateFollowPlaylist = response.data
      },
      async getFollowing() {
        const userId = store.state.userInfo.u_id
        const response = await fetchfollowinglist(userId)
        this.following_list = response.data
      },
      async getTopInfluencer() {
          const response = await top9followers()
          this.top9 = response.data
      },
    },


}
</script>

<style>

