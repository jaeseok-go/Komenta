<template>
  <div id="app" class="header-form">
    <div>
      <form class="search-box" @submit.prevent="close">
        <input type="text" id="search" placeholder="검색어를 입력하세요" v-model="keyword" @input="handleSearchInput" autocomplete="off"/>
        <label for="search"><i class="fas fa-search icon-color"/></label>
      </form>
      <div class="search-result-box" v-if="searchLists.vodSList != undefined && (searchLists.vodSList.length > 0 || searchLists.userSList.length > 0)">
        <div class="strongText" v-if="searchLists.vodSList.length > 0">VOD</div>
        <div v-for="(item, index) in searchLists.vodSList" :key="`V-${index}`">
          <div class="selectedResult" @click="goVodDetail(item.ve_id)">{{item.v_title}}</div>
        </div>
        <hr v-if="searchLists.vodSList.length > 0 && searchLists.userSList.length > 0">
        <div class="strongText" v-if="searchLists.userSList.length > 0">User</div>
        <div v-for="(item, index) in searchLists.userSList" :key="`U-${index}`">
          <div class="selectedResult" @click="geUserFeed(item.u_id)" v-text="fetchUser(item.u_nickname, item.u_email)"></div>
        </div>
      </div>
      <div class="search-result-box" v-else-if="searchLists.vodSList != undefined && (searchLists.vodSList.length == 0 && searchLists.userSList.length == 0)">
        <div class="selectedResult">
          검색 결과가 없습니다.
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { searchVodlist } from '@/api/vod';
import {searchUserlist} from '@/api/user';

export default {
  data() {
    return {
      keyword: '',
      isView: false,
      searchBox: 'none',
      vodLists: [],
      userLists: [],
      searchLists:[],
    };
  },
  created() {
    this.getAllVodAndUser();
  },
  methods: {
    handleSearchInput(e) {
      this.keyword = e.target.value;
      if (this.keyword.length !== 0) {
        clearTimeout(this.debounce);
        this.debounce = setTimeout(() => {
          const vodFilters = this.vodLists.filter((item) =>
            item.v_title.includes(this.keyword)
          );
          this.$set(this.searchLists,'vodSList',vodFilters);
          
          const userFilters = this.userLists.filter((item) =>
            item.u_nickname.includes(this.keyword)
          );
          this.$set(this.searchLists,'userSList',userFilters);
        }, 500);
      } else {
        clearTimeout(this.debounce);
        this.debounce = setTimeout(() => {
          this.searchLists = [];
        }, 500);
      }
    },
    async getAllVodAndUser() {
      const vods = await searchVodlist();
      this.vodLists = vods.data;
      const users = await searchUserlist();
      this.userLists = users.data;
    },
    goVodDetail(veId){
      this.keyword = '';
      window.location.href = `/voddetail/${veId}`;
    },
    geUserFeed(uId){
      this.keyword = '';
      window.location.href = `/feed/${uId}`;
    },
    fetchUser(nickname, email) {
      const text = nickname+"("+email+")";
      if(text.length > 23) return text.substring(0,24)+'...';
      else return text;
    },
  },
};
</script>

<style scoped>
#app {
  text-align: right;
}
</style>
