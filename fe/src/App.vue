<template>
    <div id="app">
      <aside v-if="isLogin">
        <Asidebar></Asidebar>
      </aside>
      <section>
        <Header id="header-section" v-if="isLogin"></Header>
        <router-view id="page-view" :class="{asideOn: isLogin}"></router-view>
        <!--  :key="$route.fullPath" -->
      </section>
      <!-- <nav class="bottomNav">
        바닥 고정 네비게이션
      </nav> -->

    </div>

</template>

<script>
// import "@/css/index.css"
import Asidebar from '@/components/common/Asidebar'
import Header from '@/components/common/Header'
// import store from '@/stores/modules/user'
import { mapState } from 'vuex'
// import Main from '@/components/Main/Main'

export default {
  name: 'App',
  components: {
    Asidebar,
    Header
  },
  computed: {
    ...mapState({
      userInfo:state => state.user.userInfo
    }),
    isLogin() {
      console.log(this.userInfo.u_id)
      if(this.userInfo.u_id === null) return false;
      else return true;
    }
  },
}
</script>

<style>


aside {
  border-radius: 4%;
  position: fixed;
  min-width: 170px;
  min-height: 100%;
  height: 100vh;
}


section {
  height: 100vh;
  /* padding-left: 220px; */
  width: 100%;
  

}

#page-view {
  min-height: 50rem;
  /* margin: 2rem; */
  
}

.bottomNav {
  bottom: 0; 
  background:#111; 
  transition:bottom .2s;}
.bottomNav.show {
  bottom: 0; 
  background:#111;}


</style>
