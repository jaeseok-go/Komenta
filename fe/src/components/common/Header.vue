<template>
  <div id="app">
    <!-- Searchbar with a placeholder -->
    <!-- <ion-searchbar placeholder="검색어를 입력하세요"></ion-searchbar> -->
      <button @click="logout">LOGOUT</button>
    <form class="search-box" @submit.prevent="searchTemplate">
        <input type="text" id="search" placeholder="검색어를 입력하세요" v-model="keyword">
        <label for="search" @click="searchTemplate"><i class="fas fa-search icon-color"></i></label>
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
  }

}
</script>

<style scoped>


/* 인풋박스 이용 */
.search-box {
    border: 1px solid white;
    border-radius: 20px;
    background-color: #e5e5e5;
    padding: 0.5rem;
    margin: 0.5rem;
    width: 25rem;
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
  color: #eee;
  margin-right: 8px;
}

.icon-color {
    font-size: 20px;
    color: #FA233B;
    position: relative;
    top: 5px;
    right: 2px;

}
  

</style>