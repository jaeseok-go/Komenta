<template>
  <div>
    <!-- asidebar에서 내피드를 클릭했을때 feed/f_id로 오게 함 -->
    <!-- f_id로 정보 받아서 처리 -->
    <div class="container">
      <section>
        <div class="userprofile">
          <div class="userprofile__picbox">
            <div class="userprofile__picbox__content">
              <img :src="getProfile()" />
            </div>
          </div>
          <div class="userprofile__name">{{ feedUserInfo.u_nickname }}</div>
          <template v-if="!showMyRecent() && showFollowBtn">
            <button class="userprofile__button" @click="followUser">
              FOLLOW
            </button>
          </template>
          <template v-else-if="!showMyRecent() && !showFollowBtn">
            <button class="userprofile__button" @click="followUser">
              UNFOLLOW
            </button>
          </template>
          <!-- <div v-if="!showMyRecent() && isFollowed()">
                        <button class="userprofile__button" @click="followUser">FOLLOW</button>
                    </div> -->
        </div>

        <template v-if="showMyRecent()">
          <div class="container__recentSection">
            <!-- 최근 본 VOD div -->
            <h3 class="container__recentSection__recentList">
              Recently Watched
            </h3>
            <button
              class="container__recentSection__createPlaylistButton"
              @click="showModalForm"
            >
              <h4 class="container__recentSection__createPlaylistTitle">
                새로운 플레이리스트 만들기
              </h4>
            </button>
          </div>
          <div class="drop-zone" @dragover.prevent @dragenter.prevent>
            <!-- 최근 본 VOD 리스트 중 하나씩 v-for돌림 -->
            <div class="drop-zone__inner">
              <div
                v-for="(vod, index) in myrecentlists.episodeList"
                :key="index"
                class="drag-el"
                draggable
                @dragstart="startDrag($event, vod)"
              >
                <img :src="getVodPoster(vod.v_poster)" width="100%" />
                {{ vod.v_poster }}
              </div>
            </div>
          </div>

          <Modal v-if="showModal" @close="showModal = false">
            <h4 slot="header">
              나만의 스트리밍 리스트를 만들어보세요
              <span @click="showModal = false"
                ><i class="closeModalBtn fa fa-times" aria-hidden="true"> </i
              ></span>
            </h4>
            <p slot="body" @keydown.enter="createPlaylist">
              <input
                class="input-group"
                type="text"
                v-model="plName"
                placeholder="플레이리스트 제목을 적어주세요."
              />
              <br />
              <input
                class="input-group"
                type="text"
                v-model="plComment"
                placeholder="플레이리스트 내용을 적어주세요."
              />
              <br />
              <button @click="createPlaylist">제출</button>
            </p>
          </Modal>
          <!-- 플레이리스트 수만큼 drop-zon v-for -->
          <div v-for="(playlist, index) in playlists" :key="index">
            <h3 style="cursor:pointer">
              <span @click="goPlaylsitDetail(playlist[0].pl_id)"
                >{{ playlist[0].pl_name }} </span
              ><span @click="deleteUserPlaylist(playlist[0].pl_id)"
                ><i class="far fa-trash-alt"></i>
              </span>
            </h3>
            <div
              class="drop-zone"
              @drop="onDrop($event, playlist[0].pl_id)"
              @dragover.prevent
              @dragenter.prevent
            >
              <!-- 한 플레이리스트의 컨텐츠만큼 v-for(5개씩 보여주면 옆으로 넘기는 식으로 해야될것같음) -->
              <!-- startDrag -1이면  -->
              <div class="drop-zone__inner">
                <div
                  v-for="(vod, index) in playlist"
                  :key="index"
                  class="drag-el"
                  draggable
                >
                  <span v-if="vod.gd_name" @click="goEpiDetail(vod.ve_id)"
                    ><img :src="getPlaylistVodPoster(vod.v_poster)"
                  /></span>
                </div>
              </div>
            </div>
          </div>
        </template>

        <template v-else>
          <div v-for="(playlist, index) in playlists" :key="index">
            <h3 style="cursor:pointer">
                <span @click="goPlaylsitDetail(playlist[0].pl_id)">{{ playlist[0].pl_name }}</span>
                <template v-if="isLikePlaylist(playlist[0].pl_id)">
                    <span class="playlsit-icon" @click="addlikeUserPlaylist(playlist[0].pl_id)">
                        <!-- <i class="far fa-star"></i> -->
                        <font-awesome-icon :icon="['far', 'star' ]" />
                    </span>
                </template>
                <template v-else>
                    <span class="playlsit-icon" @click="cancellikePlaylist(playlist[0].pl_id)">
                        <!-- <i class="fas fa-star playlist__star"></i> -->
                        <font-awesome-icon class="playlist__star" :icon="['fas', 'star' ]"/>
                    </span>
                </template>
            </h3>
            <div class="drop-zone">
              <!-- 한 플레이리스트의 컨텐츠만큼 v-for(5개씩 보여주면 옆으로 넘기는 식으로 해야될것같음) -->
              <!-- startDrag -1이면  -->
              <div class="drop-zone__inner">
                <div
                  v-for="(vod, index) in playlist"
                  :key="index"
                  class="drag-el"
                >
                  <span v-if="vod.gd_name" @click="goEpiDetail(vod.ve_id)"
                    ><img :src="getPlaylistVodPoster(vod.v_poster)"
                  /></span>
                </div>
              </div>
            </div>
          </div>
        </template>
      </section>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import {
  fetchMyInfo,
  fetchRecentPlaylist,
  unlikePlaylist,
  likePlaylist,
  deletePlaylist,
  addReviewPlaylist,
  fetchMyPlaylist,
  addPlaylist,
  modifyfollow,
  addPlaylistVod,
  fetchfollowinglist,
} from '@/api/user';
import Modal from '@/components/common/Modal';

export default {
  components: {
    Modal,
  },
  data() {
    return {
      myFollowing: [],
      feedUserInfo: {},
      myrecentlists: {},
      playlists: [],
      showModal: false,
      showFollow: false,
      showRecent: false,
      dragVod: {},
      plName: '',
      plComment: '',
      epiComment: '',
      selectedId: [],
      showFollowBtn: true,
    };
  },
  created() {
    this.getFeedInfo();
    this.$store.dispatch('FETCH_LIKEPLAYLIST', this.userInfo.u_id);
    this.getFollowinglist();
    this.getUserPlayList();
    this.getRecentPlayList();
  },
  computed: {
    ...mapState({
      userInfo: (state) => state.user.userInfo,
      myFollowingList: (state) => state.user.myFollowingList,
      likePlaylist: (state) => state.user.likePlaylist,
    }),
  },
  methods: {
    getProfile() {
      const profile = this.feedUserInfo.u_profile_pic.split('.')[0];
      return `${process.env.VUE_APP_PICTURE}profile/${profile}`;
    },
    // drag and drop 메소드
    // drag 시작한 vod
    startDrag: (evt, vod) => {
      evt.dataTransfer.dropEffect = 'move';
      evt.dataTransfer.effectAllowed = 'move';
      evt.dataTransfer.setData('vodID', vod.ve_id);
    },
    // vod를 끌어다가 놓는 플레이리스트
    onDrop(evt, plId) {
      const vodID = evt.dataTransfer.getData('vodID');
      const vod = this.myrecentlists.historyList.find(
        (vod) => vod.ve_id == vodID
      );
      console.log(vod, '뭐가 선택됐는가');
      this.dragIndex = this.myrecentlists.historyList.indexOf(vod, 0);
      // const vhId = this.myrecentlists[this.dragIndex].historyList.vh_id
      console.log(this.dragIndex, '드래그인덱스임');
      // 해당 vod를 플레이리스트에 추가
      // this.myrecentlists.episodeList.pop(this.dragIndex)
      // this.myrecentlists.historyList.pop(this.dragIndex)
      // 플레이리스트추가api(vod.ve_id,plId)
      // 아래 코드에 보면 플레이리스트에 추가 돼있음 근데 vod 이름이 안들어감,,이건 data받아올거니까 바뀔때마다 새로 보이게 해야겠다!
      const epiInfo = {
        vh_id: vod.vh_id,
        pl_id: plId,
      };
      addPlaylistVod(epiInfo);
      this.getUserPlayList();
    },
    showModalForm() {
      this.showModal = true;
    },
    showMyRecent() {
      if (this.userInfo.u_id == this.feedUserInfo.u_id) {
        console.log('아임...유저....');
        return true;
      }
      return false;
    },

    async getFollowinglist() {
      const response = await fetchfollowinglist(this.userInfo.u_id);
      this.myFollowing = response.data;
      for (let index = 0; index < this.myFollowing.length; index++) {
        const element = this.myFollowing[index];
        if (element.f_id === Number(this.$route.params.id)) {
          this.showFollowBtn = false;
          return;
        }
      }
      this.showFollowBtn = true;
      return;
    },
    //팔로우하는 로직 추가 구현
    followUser() {
      const your_id = this.$route.params.id;
      const my_id = this.userInfo.u_id;
      const bothId = { u_id: my_id, f_id: your_id };
      const response = modifyfollow(bothId);
      console.log(response, '됐다');
      this.$store.dispatch('FETCH_FOLLOWING', this.userInfo.u_id);
      console.log(this.showFollowBtn, '->', !this.showFollowBtn);
      this.showFollowBtn = !this.showFollowBtn;
    },

    async addComment(plId) {
      try {
        const commentInfo = {
          plc_id: plId,
          vh_comment: this.epiComment,
        };
        await addReviewPlaylist(commentInfo);
      } catch {
        console.log('플레이리스트 comment생성 실패');
      }
    },
    async createPlaylist() {
      const playlistinfo = {
        pl_name: this.plName,
        pl_comment: this.plComment,
      };
      try {
        await addPlaylist(playlistinfo);
        this.showModal = false;
        this.getUserPlayList();
        this.$store.dispatch('FETCH_MYPLAYLIST', this.userInfo.u_id);
        this.plName = '';
        this.plComment = '';
      } catch {
        console.log('플레이리스트 생성 실패');
      }
    },
    goPlaylsitDetail(plId) {
      this.$router.push(`/playlist/${plId}`);
    },
    goEpiDetail(veId) {
      this.$router.push(`/voddetail/${veId}`);
    },
    async getRecentPlayList() {
      try {
        // userId를 같이 안보내주고 토큰으로??
        const res = await fetchRecentPlaylist();
        this.myrecentlists = res.data;
        console.log(this.myrecentlists, res, '나의 최신vod목록');
      } catch {
        console.log('최신vod에러');
      }
    },
    async getUserPlayList() {
      const userId = this.$route.params.id;
      try {
        const res = await fetchMyPlaylist(userId);
        this.playlists = res.data;
        console.log(res.data, this.recentlistLen, 'playlist목록');
      } catch {
        console.log('playlist목록에러');
      }
    },
    isLikePlaylist(plId) {
      for (let i = 0; i < this.likePlaylist.length; i++) {
        const playlist = this.likePlaylist[i];
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
    async deleteUserPlaylist(plId) {
      const response = await deletePlaylist(plId);
      console.log(response);
      this.getUserPlayList();
    },
    async getFeedInfo() {
      try {
        const feedUserId = this.$route.params.id;
        const response = await fetchMyInfo(feedUserId);
        this.feedUserInfo = response.data;
        console.log(feedUserId, this.feedUserInfo);
      } catch {
        console.log('피드목록 에러', this.$route.params.id);
      }
    },
    getPlaylistVodPoster(poster) {
      // const gdId = this.genreDetails.find(genre => genre.gd_name === gdname);
      return `${process.env.VUE_APP_PICTURE}poster/${poster}`;
    },
    getVodPoster(poster) {
      return `${process.env.VUE_APP_PICTURE}poster/${poster}`;
    },
  },
};
</script>
