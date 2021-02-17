<template>
  <div>
    <div v-if="likeUserPlaylists.length">
      <span class="pl-comment"><b>{{userInfo.u_nickname}}</b>님이 좋아요를 누른 플레이 리스트를 확인하세요!</span>
      <div class="popular-play-list" v-for="(playlist, pindex) in likeUserPlaylists" :key="pindex" >
        <h2 style="cursor:pointer">
          <span class="list-title"  @click="goPlaylsitDetail(playlist[0].pl_id)"> <!--  @click="goPlaylsitDetail(playlist[0].pl_id)" -->
            {{ playlist[0].pl_name }}
          </span>
          <template v-if="isLikePlaylist(playlist[0].pl_id)">
            <span class="playlsit-icon-size" @click="addlikeUserPlaylist(playlist[0].pl_id)" width='20px'>
                <font-awesome-icon :icon="['far', 'star' ]" />
            </span>
        </template>
        <template v-else>
            <span class="playlsit-icon-size" @click="cancellikePlaylist(playlist[0].pl_id)">
                <font-awesome-icon class="playlist__star" :icon="['fas', 'star' ]"/>
            </span>
        </template>
          <span class="byUser" @click="goFeed(playlist.u_id)">
            by. {{playlist.u_nickname}}
          </span>
        </h2>
        <div class="drop-zone">
          <div class="drop-zone__inner">
            <div v-for="(vod, vindex) in playlist" :key="vindex" class="drag-el">
              <span v-if="vod.gd_name" @click="goEpiDetail(vod.ve_id)">
                <img :src="getPoster(vod.v_poster)"/>
                <div class="vodInfo">
                  <div class="vod-info-form">
                    <p class="vod-info-title" v-html="vodTitleReName(vod.v_title, vod.ve_episode_num)"></p>
                    <p class="vod-info-genre">{{vod.g_name}}/{{vod.gd_name}}</p>
                    <button class="vod-info-btn">보러가기</button>
                  </div>
                </div>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <hr v-if="likeUserPlaylists.length">
    <div>
      <p class="pl-comment" v-html="getRecommTitle()"></p>
      <div class="popular-play-list" v-for="(playlist, pindex) in popularPlayList" :key="pindex">
        <h2>
          <span class="list-title" style="cursor:pointer" @click="goPlaylsitDetail(playlist.pldetail[0].pl_id)">
            {{ playlist.pldetail[0].pl_name }}
          </span>
          <template v-if="isLikePlaylist(playlist.pldetail[0].pl_id)">
            <span class="playlsit-icon" @click="addlikeUserPlaylist(playlist.pldetail[0].pl_id)">
              <font-awesome-icon :icon="['far', 'star']" />
            </span>
          </template>
          <template v-else>
            <span class="playlsit-icon" @click="cancellikePlaylist(playlist.pldetail[0].pl_id)">
              <font-awesome-icon class="playlist__star" :icon="['fas', 'star']"/>
            </span>
          </template>
          <span class="byUser" @click="goFeed(playlist.pldetail[0].u_id)">
            by. {{playlist.pldetail[0].u_nickname}}
          </span>
        </h2>
        <div class="drop-zone">
          <div class="drop-zone__inner">
            <div v-for="(vod, vindex) in playlist.pldetail" :key="vindex" class="drag-el">
              <span v-if="vod.gd_name" @click="goEpiDetail(vod.ve_id)">
                <img :src="getPoster(vod.v_poster)"/>
                <div class="vodInfo">
                  <div class="vod-info-form">
                    <p class="vod-info-title" v-html="vodTitleReName(vod.v_title, vod.ve_episode_num)"></p>
                    <p class="vod-info-genre">{{vod.g_name}}/{{vod.gd_name}}</p>
                    <button class="vod-info-btn">보러가기</button>
                  </div>
                </div>
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
import {searchUserlist, unlikePlaylist, likePlaylist,} from '@/api/user'

export default {
  data() {
    return {
      pageNum: 0,
      popularPlayList:[],
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
    goFeed(uId){
      this.$router.push(`/feed/${uId}`)
    },
    isLikePlaylist(plId) {
      for (let i = 0; i < this.likeUserPlaylists.length; i++) {
        const playlist = this.likeUserPlaylists[i];
        if (playlist[0].pl_id == plId) {
            console.log('false')
          return false;
        }
      }
      console.log('true')
      return true;
    },
    async addlikeUserPlaylist(plId) {
      await likePlaylist({ pl_id: plId });
      this.$store.dispatch('FETCH_LIKEPLAYLIST',this.userInfo.u_id)
      // alert('플레이리스트 좋아요 함')
    },
    async cancellikePlaylist(plId) {
      await unlikePlaylist({ pl_id: plId });
      this.$store.dispatch('FETCH_LIKEPLAYLIST',this.userInfo.u_id)
      // alert('플레이리스트 좋아요 취소함')
    },
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
    },
    vodTitleReName(title, epi_num) {
    //{{vod.v_title}} {{vod.ve_episode_num}}화
      let name = String(title+" "+epi_num+"화");
      // console.log('name : ',name)
      let rename = "";
      for(let i = 0; i<name.length; i++){
        if(i%10 == 0) {
          rename +=  `<br>`+name.charAt(i);
        }else {
          rename += name.charAt(i);
        }
      }
      // console.log("rename : ",rename)
      return rename;
    },
    getRecommTitle() {
      if(this.likeUserPlaylists.length == 0){
        return `<b>${this.userInfo.u_nickname}</b>님이 좋아요 한 플레이 리스트가 없네요! 이런 플레이 리스트는 어떠신가요?`
      }else {
        return `<div class='addRecomm'><b>${this.userInfo.u_nickname}</b>님! 이런 플레이 리스트는 어떠신가요?</div>`
      }
    }
  },
};
</script>

<style></style>

