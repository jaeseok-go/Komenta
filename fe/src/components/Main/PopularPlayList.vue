<template>
  <div class="popular-list">
      <h4 class="page-title popular-list-title">골라보는 플레이리스트<div>인기있는 플레이리스트를 구독해보세요</div></h4>
      <!-- 플레이 리스트 타이틀, 플레이 리스트 좋아요 개수, 플레이 리스트 작성자, 플레이 리스트 안 컨텐츠 개수 -->
      <!-- pl_id, pl_name, 플레이 리스트 좋아요 개수, 플레이 리스트 작성자, 플레이 리스트 안 컨텐츠 개수-->
      <div class="btn-cover">
        <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
          <font-awesome-icon :icon="['fas', 'angle-left']"/>
        </button>
        <span class="page-count">{{pageNum+1}}/{{pageCount}} </span>
        <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">
          <font-awesome-icon :icon="['fas', 'angle-right']"/>
        </button>
      </div>
      <div class="play-list-form">
        <div v-if="this.popularPlaylist.length == 0">
          <div class="noRegister-text">등록된 플레이 리스트가 없습니다.</div>
        </div>
        <div class="play-list" v-else>
          <div class="playList" v-for="(playList, index) in paginatedData" :key="index">
            <div class="playList-Form" @click="goPlayList(playList.plb_id)">
              <div class="reprePoster">
                <img :src="getPoster(playList.pldetail[0].v_poster)" width="210px" height="150px">
              </div>
              <div class="userPic-popList">
                <img :src="getUserPic(playList.pldetail[0].u_profile_pic)">
              </div>
              <div class="plInfo">
                <p class="plUserInfo">
                  <b>{{playList.pldetail[0].u_nickname}}</b>'S PICK <br>
                  <span v-text="getPlTitle(playList.pldetail[0].pl_name)"></span>
                </p>
                <p class="likeInfo">
                  <font-awesome-icon :icon="[starType, 'star']" :style="{ color: '#e2c000'}"/>
                  {{playList.plb_cnt}} | {{playList.pldetail.length}}개
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import { fetchPopularPlayList } from '@/api/vod'

export default {
  data() {
    return {
      pageNum:0,
      popularPlaylist:[],
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
  created() {
    this.getPopularPlayList()
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    async getPopularPlayList() {
      //좋아요가 많은 순서대로 받아올수있을지? => ok
      try{
        const response = await fetchPopularPlayList();
        // console.log("popular play list!!!!!!!!!!! : ",response);
        for (let i = 0; i < response.data.length; i++) {
          const now = response.data[i];
          if (now.pldetail[0].v_id !==0) {
            this.popularPlaylist.push(response.data[i])
            // console.log('여기오지마')
          }
          
        }
        // console.log('popularPlayList 저장 : ',this.popularPlaylist)
        
      }catch(err) {
        console.log(err,"err")
      }
    },
    getPoster(path) {
     
      return `${process.env.VUE_APP_PICTURE}poster/${path}`;
    },
    getUserPic(path) {
      const profile = path.split('.')[0];
      return `${process.env.VUE_APP_PICTURE}profile/${profile}`;
    },
    goPlayList(pl_id){
      console.log("pl_id : ",pl_id);
      this.$router.push(`/playlist/${pl_id}`);
    },
    getPlTitle(pl_title){
      if(pl_title.length > 15){
        const rename = pl_title.substring(0,15)+"...";
        return rename;
      }else{
        return pl_title;
      }
    }
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
}
</script>
