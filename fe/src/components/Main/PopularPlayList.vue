<template>
  <div>
      <h4>골라보는 플레이리스트</h4>
      <!-- 플레이 리스트 타이틀, 플레이 리스트 좋아요 개수, 플레이 리스트 작성자, 플레이 리스트 안 컨텐츠 개수 -->
      <!-- pl_id, pl_name, 플레이 리스트 좋아요 개수, 플레이 리스트 작성자, 플레이 리스트 안 컨텐츠 개수-->
      <!-- <div>{{ this.popularPlaylist }}</div> -->
      <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">&lt;</button>
        <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">&gt;</button>
      </div>
      <div v-if="this.popularPlaylist.length == 0">
        <div class="noRegister-text">등록된 플레이 리스트가 없습니다.</div>
      </div>
      <div class="playList" v-for="(playList, index) in paginatedData" :key="index" v-else>
        <div class="playList-Form">
          <div class="reprePoster">
            <img :src="getPoster(index)" width="210px" height="150px">
          </div>
          <div class="userPic">
            <img :src="getUserPic(index)" width="40px">
          </div>
          <div class="plInfo">
            <p>
              {{playList.pl_title}} <br>
              {{playList.u_nickname}}'S PICK <br>
            </p>
            <p class="likeInfo">
              <font-awesome-icon :icon="[starType, 'star']" :style="{ color: 'yellow'}"/>
              {{playList.pl_good_cnt}} | {{playList.pl_vod_num}}개
            </p>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
// import { fetchPopularPlayList } from '@/api/vod'

export default {
  data() {
    return {
      pageNum:0,
      popularPlaylist:[
        {
          pl_title:'카제하야 레전드 모음',
          u_nickname:'김싸피',
          u_profile_pic:'default_profile.png',
          v_poster:'너에게닿기를.jpg',
          pl_good_cnt:1256,
          pl_vod_num:6
        },
        {
          pl_title:'스트레스 받을 때 보는 예능',
          u_nickname:'닉네임은 뭘로 하지',
          u_profile_pic:'default_profile.png',
          v_poster:'명탐정코난.jpg',
          pl_good_cnt:1150,
          pl_vod_num:12
        },
        {
          pl_title:'내가 보려고 만든 거누 레전드',
          u_nickname:'거누사랑',
          u_profile_pic:'default_profile.png',
          v_poster:'테니스의 왕자.jpg',
          pl_good_cnt:567,
          pl_vod_num:32
        },
        {
          pl_title:'내가 보려고 만든 거누 레전드',
          u_nickname:'거누사랑',
          u_profile_pic:'default_profile.png',
          v_poster:'테니스의 왕자.jpg',
          pl_good_cnt:567,
          pl_vod_num:32
        },
        {
          pl_title:'스트레스 받을 때 보는 예능',
          u_nickname:'닉네임은 뭘로 하지',
          u_profile_pic:'default_profile.png',
          v_poster:'명탐정코난.jpg',
          pl_good_cnt:1150,
          pl_vod_num:12
        },
        {
          pl_title:'카제하야 레전드 모음',
          u_nickname:'김싸피',
          u_profile_pic:'default_profile.png',
          v_poster:'너에게닿기를.jpg',
          pl_good_cnt:1256,
          pl_vod_num:6
        },
      ],
      starType:'far',
    }
  },
  props: {
    pageSize: {
      type: Number,
      required: false,
      default: 4
    }
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    // getPopularPlayList() {
    //   //좋아요가 많은 순서대로 받아올수있을지? => ok
    //   this.popularPlaylist = fetchPopularPlayList()
    // }
    getPoster(index) {
      const poster = this.popularPlaylist[index].v_poster;
      return require(`@/assets/images/${poster}`);
    },
    getUserPic(index) {
      const profile = this.popularPlaylist[index].u_profile_pic;
      return require(`@/assets/images/${profile}`);
    },
  },
  computed: {
    pageCount() {
      let listLeng = this.popularPlaylist.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.popularPlaylist.slice(start, end);
    }
  },
  // created() {
  //   this.getPopularPlayList()
  // },

}
</script>

<style>
.playList {
  display: inline-block;
}

.playList-Form {
  width: 230px;
  height: 300px;
  background-color: rgb(187, 187, 187);
  margin: 2rem 2rem;
}

.userPic {
  position: relative;
  bottom: 10px;
  right: 15px;
  text-align: right;
}

.reprePoster {
  position: relative;
  top: 10px;
  left: 10px;
}

.likeInfo {
  position: relative;
  top:15px;
}
</style>