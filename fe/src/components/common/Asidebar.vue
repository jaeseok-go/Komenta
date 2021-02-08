<template>

    <div class="web-navigation">
            <div class="web-navigation__header">
                <span class="web-navigation__logo">Komenta</span>
            </div>
            <div class="web-navigation__scrollable">
              <!-- <div class="sidebar-scrollable-profile">
                  <div class="sidebar-profile-pic"><i class="fad fa-user-circle"></i></div>
                  <span class="sidebar-profilc-nickname">닉네임은 뭘로 하지</span>
                  <span class="sidebar-profilc-id">ssafy@komenta.com</span>
              </div> -->
              <ul class="web-navigation__nav-list">
                <li class="web-navigation__nav-list-item">
                  <router-link :to="{name:'Main'}">
                    <span class="sidebar-scrollable-title">
                      <span class="sidebar-scrollable-title-icon icon-bg1"><ion-icon name="person-circle"></ion-icon></span>
                      <span class="sidebar-scrollable-title-text">Home</span>
                    </span>
                  </router-link>
                  
                <li class="web-navigation__nav-list-item">
                  <router-link :to="{name:'MyPlayList'}">
                    <span class="sidebar-scrollable-title">
                      <span class="sidebar-scrollable-title-icon icon-bg2"><ion-icon name="albums-outline"></ion-icon></span>
                      <span class="sidebar-scrollable-title-text">Subscriptions</span>
                    </span>
                  </router-link>
                  
                <li class="web-navigation__nav-list-item">
                  <router-link :to="{name:'Category'}">
                    <span class="sidebar-scrollable-title">
                      <span class="sidebar-scrollable-title-icon icon-bg3"><ion-icon name="caret-forward-outline"></ion-icon></span>
                      <span class="sidebar-scrollable-title-text">ALL Video</span>
                    </span>
                  </router-link>

                <li class="web-navigation__nav-list-item">
                  <router-link :to="{name:'People'}">
                    <span class="sidebar-scrollable-title">
                      <span class="sidebar-scrollable-title-icon icon-bg4"><ion-icon name="globe-outline"></ion-icon></span>
                      <span class="sidebar-scrollable-title-text">People</span>
                    </span>
                  </router-link>
                </li>
                <li class="web-navigation__nav-list-item">
                  <router-link :to="{ name: 'Feed', params: { id: userInfo.u_id }}">
                    <span class="sidebar-scrollable-title">
                      <span class="sidebar-scrollable-title-icon icon-bg5"><ion-icon name="share-social-outline"></ion-icon></span>
                      <span class="sidebar-scrollable-title-text">My Feed</span>
                    </span>
                  </router-link>

                  <li class="web-navigation__nav-list-item">
                  <router-link :to="{name:'Main'}">
                    <span class="sidebar-scrollable-title">
                      <span class="sidebar-scrollable-title-icon icon-bg6"><ion-icon name="settings-outline"></ion-icon></span>
                      <span class="sidebar-scrollable-title-text">Settings</span>
                    </span>
                  </router-link>

                  <li class="web-navigation__nav-list-item">
                  <router-link :to="{name:'Main'}">
                    <span class="sidebar-scrollable-title">
                      <span class="sidebar-scrollable-title-icon icon-bg7"><ion-icon name="cloud"></ion-icon></span>
                      <span class="sidebar-scrollable-title-text">Help</span>
                    </span>
                  </router-link>

                  <!--?-->

                  
  
                </li>
              </ul>

            </div>
      </div>
</template>

<script>
import {mapState} from 'vuex';

export default {
    // el: '#app',
    computed: {
        ...mapState({
        userInfo: state => state.user.userInfo,
    }),
    }

};
</script>
