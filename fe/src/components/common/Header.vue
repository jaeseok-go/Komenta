<template>
  <div id="app">
    <!-- Searchbar with a placeholder -->
    <!-- <ion-searchbar placeholder="검색어를 입력하세요"></ion-searchbar> -->
      <button @click="logout">LOGOUT</button>
    <form class="search-box" @submit.prevent="searchTemplate" :style="{border:searchBox}">
        <input type="text" id="search" placeholder="검색어를 입력하세요" v-model="keyword" v-if="isView">
        <label for="search" @click="viewSearchBox"><i class="fas fa-search icon-color"/></label>
    </form>
  </div>
</template>

<script>
// import { IonSearchbar, IonToolbar } from '@ionic/vue';
// import { defineComponent } from 'vue';


export default {
  data() {
    return {
      keyword: '',
      isView: false,
      searchBox:'none',
    }
  },
  methods: {
    logout() {
      if (confirm('로그아웃 하시겠습니까?')) {
        this.$store.commit('logout');
        if (this.$route.path !== '/') {
          this.$router.push('/');
        }
      }
    },
      searchTemplate() {
      if (this.keyword) {
        this.$router.push(`/search/${this.keyword}`)
      } else {
        alert('키워드를 입력해주세요.');
      }
    },
    viewSearchBox(){
      if(!this.isView) {
        this.isView = true;
        this.searchBox='1px solid rgb(158, 158, 158)';
        return;
      }else {
        this.searchTemplate();
      }
    }
  }

}
</script>

<style scoped>
#header-section {
  text-align: right;
}
/* 인풋박스 이용 */
.search-box {
    border: 1px solid rgb(158, 158, 158);
    border-radius: 20px;
    /* background-color: #e5e5e5; */
    padding: 0.14rem 0.5rem;
    margin: 0.5rem;
    width: 17rem;
    display: flex;
    align-items: center;
    flex-grow: 1;
}

.search-box > input {
  height: 25px;
  width: 100%;
  padding: 5px;
  border: transparent;
  border-style: none;
  border-radius: 10px;
  color: black;
  margin-right: 8px;
}

.search-box input:focus {
  outline: none;
  caret-color: black;
}

.icon-color {
  font-size: 20px;
  color: #FA233B;
  position: relative;
  top: 5px;
  right: 2px;
  cursor:pointer;
}
  

</style>