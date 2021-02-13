<template>
  <div id="app">
    <!-- Searchbar with a placeholder -->
    <!-- <ion-searchbar placeholder="검색어를 입력하세요"></ion-searchbar> -->
    <form
      class="search-box"
      @submit.prevent="searchTemplate"
      :style="{ border: searchBox }"
    >
      <input
        type="text"
        id="search"
        placeholder="검색어를 입력하세요"
        v-model="keyword"
        v-if="isView"
        @input="handleSearchInput"
      />
      <label for="search" @click="viewSearchBox"
        ><i class="fas fa-search icon-color"
      /></label>
    </form>
      <div class="search-result-box">
        <div v-for="(item, index) in searchLists.vodSList" :key="`V-${index}`">
          <div>{{item.v_title}}</div>
        </div>
        <div v-for="(item, index) in searchLists.userSList" :key="`U-${index}`">
          <div>{{item.u_nickname}}</div>
        </div>
      </div>
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
    // searchTemplate() {
    //   this.getAllVodandUser();
    //   this.vodlists.forEach((vod) => {
    //     vod.indexOf(this.keyword);
    //     this.searchlists.push(vod);
    //   });
    //   console.log(this.searchlists, '검색!!!');

    //   if (this.keyword) {
    //     this.$router.push(`/search/${this.keyword}`);
    //   } else {
    //     alert('키워드를 입력해주세요.');
    //   }
    // },
    viewSearchBox() {
      if (!this.isView) {
        this.isView = true;
        this.searchBox = '1.3px solid rgb(158, 158, 158)';
        return;
      } 
      // else {
      //   this.getAllVodandUser();
      //   this.searchTemplate();
      // }
    },
  },
};
</script>

<style scoped>
#app {
  text-align: right;
}
</style>
