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
                          <span class="sidebar-scrollable-title-icon icon-bg1"><ion-icon name="person-circle"></ion-icon></span>
                          <span class="sidebar-scrollable-title-text">Home</span>
                        </span>
                      </router-link>
                    </li>
                      
                    <li class="web-navigation__nav-list-item">
                      <router-link :to="{name:'MyPlayList'}">
                        <span class="sidebar-scrollable-title">
                          <span class="sidebar-scrollable-title-icon icon-bg2"><ion-icon name="albums-outline"></ion-icon></span>
                          <span class="sidebar-scrollable-title-text">Subscriptions</span>
                        </span>
                      </router-link>
                      </li>
                    <li class="web-navigation__nav-list-item">
                      <router-link :to="{name:'Category'}">
                        <span class="sidebar-scrollable-title">
                          <span class="sidebar-scrollable-title-icon icon-bg3"><ion-icon name="caret-forward-outline"></ion-icon></span>
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
                  </li>
                </div>

                <div>
                  <span  id="sub-group">PLAYLISTS</span>


                </div>
              </ul>

            </div>
      </div>
</template>

<script>
import {mapState} from 'vuex';
// import store from '@/stores/modules/user'

export default {
    // el: '#app',
    computed: {
        ...mapState({
        userInfo: state => state.user.userInfo,
      }),
      ...mapState({
        myPlayList: state => state.user.myPlayList
      })
    },
    // methods: {
    //     fetchMyPlaylist() {
    //     // const userId = this.userInfo.u_id
    //     const response = store.$dispatch('FETCH_MYPLAYLIST',5)
    //     console.log(response,'으..')
    //   }
    // },
    // created() {
    //   this.fetchMyPlaylist();
    //   console.log('여기?')

    // },


};
</script>

<style scoped>
#sub-group {
    display: inline-block;
    margin-top: 22px;
    margin-bottom: 0.8rem;
    font-size: 10px;
    line-height: 1.3;
    font-weight: 600;
    letter-spacing: 0;
    opacity: .5;
    text-transform: uppercase;
}


</style>