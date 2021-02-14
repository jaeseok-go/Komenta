<template>
  <div>
    <div v-if="likeUserPlaylists.length">
      <span class="pl-comment"><b>{{userInfo.u_nickname}}</b>님이 좋아요를 누른 플레이 리스트를 확인하세요!</span>
      <div class="popular-play-list" v-for="(playlist, pindex) in likeUserPlaylists" :key="pindex" >
        <h2 @click="goPlaylsitDetail(playlist[0].pl_id)" style="cursor:pointer">
          <span class="list-title"> <!--  @click="goPlaylsitDetail(playlist[0].pl_id)" -->
            {{ playlist[0].pl_name }}
          </span>
          <span class="byUser">
            by. {{playlist.u_nickname}}
          </span>
        </h2>
        <div class="drop-zone">
          <div class="drop-zone__inner">
            <div v-for="(vod, vindex) in playlist" :key="vindex" class="drag-el">
              <span v-if="vod.gd_name" @click="goEpiDetail(vod.ve_id)">
                <img :src="getPoster(vod.v_poster)"/>
                <div class="vodInfo">
                  <!-- {{vod.v_title}} -->
                </div>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <p class="pl-comment"><b>{{userInfo.u_nickname}}</b>님이 좋아요 한 플레이 리스트가 없네요! 이런 플레이 리스트는 어떠신가요?</p>
      <div class="popular-play-list" v-for="(playlist, pindex) in popularPlayList" :key="pindex">
        <h2>
          <span class="list-title">
            {{ playlist.pldetail[0].pl_name }}
          </span>
          <span class="byUser" @click="goPlaylsitDetail(playlist.pldetail[0].pl_id)">
            by. {{playlist.pldetail[0].u_nickname}}
          </span>
        </h2>
        <div class="drop-zone">
          <div class="drop-zone__inner">
            <div v-for="(vod, vindex) in playlist.pldetail" :key="vindex" class="drag-el">
              <span v-if="vod.gd_name" @click="goEpiDetail(vod.ve_id)">
                <img :src="getPoster(vod.v_poster)"/>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { fetchPopularPlayList } from '@/api/vod' //, fetchPlayListDetail
import {searchUserlist} from '@/api/user'

export default {
  data() {
    return {
      pageNum: 0,
      popularPlayList:[]
    };
  },
  created() {
    this.$store.dispatch('FETCH_LIKEPLAYLIST', this.userInfo.u_id);
    this.getPopularPlayList();
    setTimeout(this.getByUserNickname,50);
    // this.getLikePlayList()
  },
  computed: {
    ...mapState({
      userInfo: (state) => state.user.userInfo,
      likeUserPlaylists: (state) => state.user.likePlaylist,
    })
  },
  methods: {
    goPlaylsitDetail(plId) {
      this.$router.push(`/playlist/${plId}`);
    },
    goEpiDetail(veId) {
      this.$router.push(`/voddetail/${veId}`);
    },
    getPoster(poster) { //gdname, title
      // const gdId = this.genreDetails.find((genre) => genre.gd_name === gdname);
      // return `${process.env.VUE_APP_PICTURE}poster/${gdId.gd_id}_${title}`;
      return `${process.env.VUE_APP_PICTURE}poster/${poster}`;
    },
    async getPopularPlayList() {
      try{
        const response = await fetchPopularPlayList();
        console.log("popular play list : ",response.data);
        for (let i = 0; i < response.data.length; i++) {
          const now = response.data[i];
          if (now.pldetail[0].v_id !== 0) {
            this.popularPlayList.push(response.data[i])
          }   
        }
        console.log("popular play list!!!!!!!!!!! : ",this.popularPlayList);
      }catch(err) {
        console.log(err,"err")
      }
    },
    // async getByUserNickname(plId) {
    //   const response = await fetchPlayListDetail(plId);
    //   console.log("by user nickname : ", response.data[0].u_nickname);
    //   return response.data[0].u_nickname;
    // },
    async getByUserNickname() {
      const response = await searchUserlist();
      // this.userNicname = response.data;
      for(let i = 0; i<this.likeUserPlaylists.length; i++){
        for(let j = 0; j<response.data.length; j++) {
          if(this.likeUserPlaylists[i][0].u_id === response.data[j].u_id){
            this.$set(this.likeUserPlaylists[i],'u_nickname',response.data[j].u_nickname);
          }
        }
      }
      console.log("유저 닉네임 잘 들어왔니? : ",this.likeUserPlaylists)
    }
  },
};
</script>

<style></style>

