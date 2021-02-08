<template>
  <div>
      <!-- 여기 플레이리스트 타이틀, 플레이 리스트 대표사진, 플레이 리스트 작성자, 플레이 리스트 안에 콘텐츠들(각각 장르, 제목, 몇화인지, 러닝타임)-->
      <h1>스트리밍 리스트 디테일 페이지</h1>
      {{playlists[0].pl_name}} | {{ playlists[0].u_nick_name }} | <img src="playlists[0].u_profile_pic" alt="유저프로필">
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
          <td>  {{playlist.v_title}}  </td>
          <td>{{ playlist.vh_comment }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>


  </div>
</template>

<script>
import { fetchPlayListDetail } from '@/api/vod'


export default {
    data() {
        return {
            playlists:[
  {
    "pl_id": 1,
    "pl_name": "test playlist",
    "pl_comment": null,
    "pl_good_count": 1,
    "u_id": 1,
    "u_nick_name": null,
    "u_profile_pic": "default_profile.png",
    "plc_id": 0,
    "v_id": 0,
    "v_title": null,
    "v_actors": null,
    "v_director": null,
    "v_poster": null,
    "ve_id": 0,
    "ve_episode_num": 0,
    "vh_id": 0,
    "vh_comment": null,
    "vh_score": 0,
    "g_name": null,
    "gd_name": null
  }
],
        }
    },
    methods: {
        getStreamingListDetail() {
            const plId = this.$route.params.id;
            this.playlists = fetchPlayListDetail(plId)
        }
    },
    created() {
        this.getStreamingListDetail()
    },

}
</script>

<style>

</style>