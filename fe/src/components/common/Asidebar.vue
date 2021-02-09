<template>

    <div class="web-navigation">
            <div class="web-navigation__header">
                <span class="web-navigation__logo">
                  <img src="@/assets/images/KOMENTA_logo.png" alt="">
                </span>
            </div>
              <div class="web-navigation__scrollable">
                <ul class="web-navigation__nav-list">
                  
                  <div>
                    <li class="web-navigation__nav-list-item">
                      <router-link :to="{name:'Main'}">
                        <span class="sidebar-scrollable-title">
                          <span class="sidebar-scrollable-title-icon icon-bg5"><i class="fas fa-home"></i></span>
                          <span class="sidebar-scrollable-title-text">Home</span>
                        </span>
                      </router-link>
                    </li>
                      
                    <li class="web-navigation__nav-list-item">
                      <router-link :to="{name:'MyPlayList'}">
                        <span class="sidebar-scrollable-title">
                          <span class="sidebar-scrollable-title-icon icon-bg5"><i class="far fa-folder-open"></i></span>
                          <span class="sidebar-scrollable-title-text">Subscriptions</span>
                        </span>
                      </router-link>
                      </li>
                    <li class="web-navigation__nav-list-item">
                      <router-link :to="{name:'Category'}">
                        <span class="sidebar-scrollable-title">
                          <span class="sidebar-scrollable-title-icon icon-bg5"><i class="far fa-play-circle"></i></span>
                          <span class="sidebar-scrollable-title-text">ALL Video</span>
                        </span>
                      </router-link>
                      </li>
                  </div>
     
                <div>
                  <span id="sub-group">SOCIAL</span>
                  <li class="web-navigation__nav-list-item">
                    <router-link :to="{name:'People'}">
                      <span class="sidebar-scrollable-title">
                        <span class="sidebar-scrollable-title-icon icon-bg5"><i class="fas fa-globe"></i></span>
                        <span class="sidebar-scrollable-title-text">People</span>
                      </span>
                    </router-link>
                  </li>
                  <li class="web-navigation__nav-list-item">
                    <router-link :to="{ name: 'Feed', params: { id: userInfo.u_id }}">
                      <span class="sidebar-scrollable-title">
                        <span class="sidebar-scrollable-title-icon icon-bg5"><i class="fas fa-user-circle"></i></span>
                        <span class="sidebar-scrollable-title-text">My Feed</span>
                      </span>
                    </router-link>
                  </li>
                </div>

                <div>
                  <span  id="sub-group">PLAYLISTS</span>
                    <li class="web-navigation__nav-list-item" v-for="myPlayList in myPlayList.data" :key="myPlayList.id">
                      <router-link :to="{ name: 'PlayListDetail', params: { id: myPlayList[0].pl_id }}">
                        <span class="sidebar-scrollable-title">
                          <span class="sidebar-scrollable-title-icon icon-bg5"><i class="far fa-file-video"></i></span>
                          <span class="sidebar-scrollable-title-text rgba(0, 0, 0, 0.95);">{{myPlayList[0].pl_name}}</span>
                        </span>
                      </router-link>
                    </li>


                </div>
              </ul>

            </div>
      </div>
</template>

<script>
import {mapState} from 'vuex';
// import store from '@/stores/modules/user'

export default {
    computed: {
        ...mapState({
        userInfo: state => state.user.userInfo,
      }),
      ...mapState({
        myPlayList: state => state.user.myPlayList
      }),
    },
    created() {
      const userId = this.userInfo.u_id
      this.$store.dispatch('FETCH_MYPLAYLIST',userId)
    }
};
</script>
