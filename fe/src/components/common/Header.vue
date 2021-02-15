<template>
  <div id="app" class="header-form">
    <!-- Searchbar with a placeholder -->
    <!-- <ion-searchbar placeholder="검색어를 입력하세요"></ion-searchbar> -->
    <div>
      <form class="search-box" @submit.prevent="close" :style="{ border: searchBox }">
        <input type="text" id="search" placeholder="검색어를 입력하세요" v-model="keyword" v-if="isView" @input="handleSearchInput" autocomplete="off"/>
        <label for="search" @click="viewSearchBox"><i class="fas fa-search icon-color"/></label>
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
    </div>
    <!-- 로그인 한 유저 프로필 -->
    <!-- <div class="user-profile-form navbar-collapse collapse">
      <ul class="navbar-nav navbar-align">
        <li class="nav-item dropdown">
          <a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#" data-toggle="dropdown">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-settings align-middle"><circle cx="12" cy="12" r="3"></circle><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path></svg>
          </a>

          <a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-toggle="dropdown" aria-expanded="false">
            <img src="img/avatars/avatar.jpg" class="avatar img-fluid rounded mr-1" alt="Charles Hall"> <span class="text-dark">Charles
              Hall</span>
          </a>
          <div class="dropdown-menu dropdown-menu-right">
            <a class="dropdown-item" href="pages-profile.html"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user align-middle mr-1"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg> Profile</a>
            <a class="dropdown-item" href="#"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-pie-chart align-middle mr-1"><path d="M21.21 15.89A10 10 0 1 1 8 2.83"></path><path d="M22 12A10 10 0 0 0 12 2v10z"></path></svg> Analytics</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="pages-settings.html"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-settings align-middle mr-1"><circle cx="12" cy="12" r="3"></circle><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path></svg> Settings &amp;
              Privacy</a>
            <a class="dropdown-item" href="#"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-help-circle align-middle mr-1"><circle cx="12" cy="12" r="10"></circle><path d="M9.09 9a3 3 0 0 1 5.83 1c0 2-3 3-3 3"></path><line x1="12" y1="17" x2="12.01" y2="17"></line></svg> Help Center</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Log out</a>
          </div>
        </li>
      </ul>
    </div> -->
  </div>
</template>

<script>
// import { IonSearchbar, IonToolbar } from '@ionic/vue';
// import { defineComponent } from 'vue';
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
      console.log('search 단어 : ',this.keyword);
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

          console.log('잘 합쳐졌을까? : ',this.searchLists)
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
      console.log('vod search res : ',vods)
      this.vodLists = vods.data;
      console.log('검색 - vod list : ',this.vodLists);
       const users = await searchUserlist();
       console.log('user search res : ',users)
       this.userLists = users.data;
       console.log('검색 - user lsit : ',this.userLists);
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
    viewSearchBox() {
      if (!this.isView) {
        this.isView = true;
        this.searchBox = '1.3px solid rgb(158, 158, 158)';
        return;
      }else {
        this.isView = false;
        this.searchBox = 'none';
        return;
      }
    },
  },
};
</script>

<style scoped>
#app {
  text-align: right;
}
</style>
