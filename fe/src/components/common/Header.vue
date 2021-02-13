<template>
  <div id="app">
    <!-- Searchbar with a placeholder -->
    <!-- <ion-searchbar placeholder="검색어를 입력하세요"></ion-searchbar> -->
    <form class="search-box" @submit.prevent="searchTemplate" :style="{border:searchBox}">
      <input type="text" id="search" placeholder="검색어를 입력하세요" v-model="keyword" v-if="isView">
      <label for="search" @click="viewSearchBox"><i class="fas fa-search icon-color"/></label>
    </form>
  </div>
</template>

<script>
// import { IonSearchbar, IonToolbar } from '@ionic/vue';
// import { defineComponent } from 'vue';
import {searchVodlist} from '@/api/vod';
// import {searchUserlist} from '@/api/user';

export default {
  data() {
    return {
      keyword: '',
      isView: false,
      searchBox:'none',
      vodlists :[],
      userlists:[],
      searchlists:[]
    }
  },
  methods: {
   async getAllVodandUser(){
     const vods = await searchVodlist();
     this.vodlists = vods.data
     console.log(this.vodlists,'???')
    //  const users = await searchUserlist();
    //  this.userlists = users.data
   },
      searchTemplate() {
        this.getAllVodandUser();
        this.vodlists.forEach((vod) => {
          vod.indexOf(this.keyword)
          this.searchlists.push(vod)
        })
      console.log(this.searchlists,'검색!!!')
        
      if (this.keyword) {
        this.$router.push(`/search/${this.keyword}`)
      } else {
        alert('키워드를 입력해주세요.');
      }
    },
    viewSearchBox(){
      if(!this.isView) {
        this.isView = true;
        this.searchBox='1.3px solid rgb(158, 158, 158)';
        return;
      }else {
        this.getAllVodandUser();
        this.searchTemplate();
      }
    }
  }

}
</script>

<style scoped>
#app {
  text-align: right;
}
</style>