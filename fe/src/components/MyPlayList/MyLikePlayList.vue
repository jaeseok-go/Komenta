<template>
  <div>
    <h4>내가 좋아요 누른 play list</h4>
    <div v-if="likeUserPlaylists.length">
      <div v-for="(playlist,pindex) in likeUserPlaylists" :key="pindex">
        <h2><span @click="goPlaylsitDetail(playlist[0].pl_id)">{{playlist[0].pl_name}}</span></h2>
        <div class='drop-zone'>
          <div class="drop-zone__inner">
        <div 
        v-for="(vod,vindex) in playlist" :key="vindex"
        class='drag-el'
        >
          <span v-if="vod.gd_name" @click="goEpiDetail(vod.ve_id)"><img :src="getPoster(vod.gd_name,vod.v_title)"></span>
        </div>
      </div>
      </div>
      </div>
    </div>
    <div v-else>
      <p>좋아요 한 플레이 리스트가 없습니다. 마음에 드는 플레이 리스트를 담아보세요!</p>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
// import {fetchLikePlaylist} from '@/api/user'

export default {
  data() {
    return {
      pageNum:0,
      genreDetails:[
            {
                "gd_id": 1,
                "gd_name": "멜로",
                "g_id": 1
            },
            {
                "gd_id": 2,
                "gd_name": "스릴러",
                "g_id": 1
            },
            {
                "gd_id": 3,
                "gd_name": "코미디",
                "g_id": 1
            },
            {
                "gd_id": 4,
                "gd_name": "액션",
                "g_id": 1
            }
            ,
            {
                "gd_id": 5,
                "gd_name": "음악",
                "g_id": 2
            },
            {
                "gd_id": 6,
                "gd_name": "버라이어티",
                "g_id": 2
            },
            {
                "gd_id": 7,
                "gd_name": "토크",
                "g_id": 2
            },
            {
                "gd_id": 8,
                "gd_name": "요리",
                "g_id": 2
            }
            ,

            {
                "gd_id": 9,
                "gd_name": "환경",
                "g_id": 3
            },
            {
                "gd_id": 10,
                "gd_name": "역사",
                "g_id": 3
            },
            {
                "gd_id": 11,
                "gd_name": "휴먼",
                "g_id": 3
            }
            ,

            {
                "gd_id": 12,
                "gd_name": "축구",
                "g_id": 4
            },
            {
                "gd_id": 13,
                "gd_name": "농구",
                "g_id": 4
            },
            {
                "gd_id": 14,
                "gd_name": "배구",
                "g_id": 4
            },
            {
                "gd_id": 15,
                "gd_name": "야구",
                "g_id": 4
            }
            ,
            {
                "gd_id": 16,
                "gd_name": "로맨스/순정",
                "g_id": 5
            },
            {
                "gd_id": 17,
                "gd_name": "스포츠",
                "g_id": 5
            },
            {
                "gd_id": 18,
                "gd_name": "액션/추리",
                "g_id": 5
            },
            {
                "gd_id": 19,
                "gd_name": "코미디",
                "g_id": 5
            }

            ]
      // likeUserPlaylists:[]
    }
  },
  created() {
    this.$store.dispatch('FETCH_LIKEPLAYLIST',this.userInfo.u_id)
    // this.getLikePlayList()
  },
  computed: {
    ...mapState({
        userInfo: state => state.user.userInfo,
        likeUserPlaylists:state => state.user.likePlaylist
      }),
  },
  methods: {
    goPlaylsitDetail(plId){
      this.$router.push(`/playlist/${plId}`)
    },
    goEpiDetail(veId){
      this.$router.push(`/voddetail/${veId}`)
    },
    getPoster(gdname,title) {
       const gdId = this.genreDetails.find(genre => genre.gd_name === gdname);
            return `${process.env.VUE_APP_PICTURE}poster/${gdId.gd_id}_${title}`
      },

    //   async getLikePlayList(){
    // const response = await fetchLikePlaylist(this.userInfo.u_id);
    // console.log("좋아요 한 플레이리스트 순 : ",response.data);
    // this.likeUserPlaylists = response.data;
    // console.log(this.likeUserPlaylists)
    // }
        
    
    
  },
}
</script>

<style>

</style>