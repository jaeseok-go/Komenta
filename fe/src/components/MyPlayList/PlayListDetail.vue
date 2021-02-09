<template>
  <div>
      <!-- 여기 플레이리스트 타이틀, 플레이 리스트 대표사진, 플레이 리스트 작성자, 플레이 리스트 안에 콘텐츠들(각각 장르, 제목, 몇화인지, 러닝타임) -->
      <img :src="getUserProfile(playlists[0].u_profile_pic)" alt="" width="20%"> {{ playlists[0].u_nickname }}
      <h1>{{playlists[0].pl_name}}</h1>
      <h3>{{playlists[0].pl_comment}}</h3>
       
    <v-simple-table
        fixed-header
        height="300px"
    >
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            Genre
          </th>
          <th class="text-left">
            Title
          </th>
          <th class="text-left">
            Review
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="playlist in playlists"
          :key="playlist.pl_id"
        >
        <td>{{playlist.g_name}}/{{playlist.gd_name}}</td>
          <td>  {{playlist.v_title}} {{playlist.ve_episode_num}}회  </td>
          <td v-if="showMyRecent()"><input type="text" v-model='review' :placeholder="playlist.vh_comment"  @keydown.enter="addComment(playlist.plc_id)"></td>
          <td v-else>{{ playlist.vh_comment }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>


  </div>
</template>

<script>
import { fetchPlayListDetail } from '@/api/vod'
import { mapState } from 'vuex';
import { addReviewPlaylist} from '@/api/user'


export default {
    data() {
        return {
            playlists:[],
            review:'',
        }
    },
    methods: {
      playlistReview(userreview){
        this.review = userreview;
      },
        async addComment(plId){
            try {
                const commentInfo = {
                    plc_id : plId,
                    vh_comment : this.review
                }
                const res = await addReviewPlaylist(commentInfo);
                console.log(res,'등록?')
                this.getStreamingListDetail();
            } catch {
                console.log('플레이리스트 comment생성 실패')
            }
        },
        showMyRecent() {
            if (this.userInfo.u_id == this.playlists[0].u_id) {
                return true
            }
            return false
        },
        async getStreamingListDetail() {
            const plId = this.$route.params.id;
            const res = await fetchPlayListDetail(plId)
            this.playlists = res.data
            console.log(this.playlists,'dd')
        },
        getUserProfile(path) {
          const profile = path.split('.')
          console.log(`http://i4b201.p.ssafy.io:7000/picture/profile/${profile[0]}`)
            return `http://i4b201.p.ssafy.io:7000/picture/profile/${profile[0]}`
        }
    },
    created() {
        this.getStreamingListDetail()

    },
    computed: {
        ...mapState({
        userInfo: state => state.user.userInfo,
    }),
    }

}
</script>

<style>

</style>