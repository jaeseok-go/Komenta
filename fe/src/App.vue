<template>
    <div id="app">
      <aside v-if="isLogin">
        <Asidebar></Asidebar>
      </aside>
      <section>
        <Header id="header-section" v-if="isLogin"></Header>
        <router-view id="page-view" :class="{asideOn: isLogin, asideOff: !isLogin}"></router-view>
        <!--  :key="$route.fullPath" -->
        <div class="margin__top"></div>
        <footer>
        <Footer v-if="isLogin" :class="{asideOn: isLogin}"></Footer>
        </footer>
      </section>
  </div>
</template>

<script>
// import "@/css/index.css"
import Asidebar from '@/components/common/Asidebar'
import Header from '@/components/common/Header'
import Footer from '@/components/common/Footer'
// import store from '@/stores/modules/user'
import { mapState } from 'vuex'
// import Main from '@/components/Main/Main'

export default {
  name: 'App',
  components: {
    Asidebar,
    Header,
    Footer
  },
  computed: {
    ...mapState({
      userInfo:state => state.user.userInfo
    }),
    isLogin() {
      console.log("유저 정보 : ",this.userInfo)
      if(this.userInfo.u_id === null) return false;
      else return true;
    },
    //로그인되있으면 어바웃 페이지 안보여줌
    dontshowAboutPage() {
      if (this.userInfo.u_id) return false;
      else return true
    }
  },
}
</script>

<style>
</style>
