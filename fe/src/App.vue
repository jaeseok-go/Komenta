<template>
    <div id="app">
      <aside v-if="isLogin">
        <Asidebar></Asidebar>
      </aside>
      <section>
        <Header id="header-section" v-if="isLogin"></Header>
        <router-view id="page-view" :class="{asideOn: isLogin, asideOff: !isLogin}"></router-view>
        <footer class="footer_padding" :class="{'asideOn-footer': isLogin}">
          <Footer v-if="isLogin" class="footer__margin"></Footer> 
        </footer>
      </section>
  </div>
</template>

<script>
import Asidebar from '@/components/common/Asidebar'
import Header from '@/components/common/Header'
import Footer from '@/components/common/Footer'
import { mapState } from 'vuex'

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
      if(this.userInfo.u_id === null) return false;
      else return true;
    },
    //로그인되있으면 어바웃 페이지 안보여줌
    dontshowAboutPage() {
      if (this.userInfo.u_id) return false;
      else return true
    }
  },
    methods: {
    goTop() {
      window.scrollTo(0, 0);
    }
  },
   watch: {
    '$route'() {
      if (this.$route.name !== 'AboutPage' && this.$route.name !== 'BestComments' &&  this.$route.name !== 'AllComments') {
        this.goTop();
      }
    }
  }
}
</script>

<style>
</style>
