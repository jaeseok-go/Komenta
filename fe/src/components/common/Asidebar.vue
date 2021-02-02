<template>
    <div class='whole-border'>

    <div class="sidebar">
            <div class="sidebar-logo">
                <span>Komenta</span>
            </div>
            <div class="sidebar-profile">
                <div class="sidebar-profile-pic"><i class="fad fa-user-circle"></i></div>
                <span class="sidebar-profilc-nickname">닉네임은 뭘로 하지</span>
                <span class="sidebar-profilc-id">ssafy@komenta.com</span>
                
            </div>
            <ul class="sidebar-ul">
              <li class="sidebar-child">
                <router-link :to="{name:'Main'}">
                  <span class="sidebar-title">
                    <span class="sidebar-title-icon icon-bg1"><ion-icon name="person-circle"></ion-icon></span>
                    <span class="sidebar-title-text">Home</span>
                  </span>
                </router-link>
                
              <li class="sidebar-child">
                <router-link :to="{name:'MyPlayList'}">
                  <span class="sidebar-title">
                    <span class="sidebar-title-icon icon-bg2"><ion-icon name="albums-outline"></ion-icon></span>
                    <span class="sidebar-title-text">Subscriptions</span>
                  </span>
                </router-link>
                
              <li class="sidebar-child">
                <router-link :to="{name:'Category'}">
                  <span class="sidebar-title">
                    <span class="sidebar-title-icon icon-bg3"><ion-icon name="caret-forward-outline"></ion-icon></span>
                    <span class="sidebar-title-text">ALL Video</span>
                  </span>
                </router-link>

              <li class="sidebar-child">
                <router-link :to="{name:'Main'}">
                  <span class="sidebar-title">
                    <span class="sidebar-title-icon icon-bg4"><ion-icon name="globe-outline"></ion-icon></span>
                    <span class="sidebar-title-text">People</span>
                  </span>
                </router-link>
              </li>
              <li class="sidebar-child">
                <router-link :to="{name:'Feed'}">
                  <span class="sidebar-title">
                    <span class="sidebar-title-icon icon-bg5"><ion-icon name="share-social-outline"></ion-icon></span>
                    <span class="sidebar-title-text">My Feed</span>
                  </span>
                </router-link>

                <li class="sidebar-child">
                <router-link :to="{name:'Main'}">
                  <span class="sidebar-title">
                    <span class="sidebar-title-icon icon-bg6"><ion-icon name="settings-outline"></ion-icon></span>
                    <span class="sidebar-title-text">Settings</span>
                  </span>
                </router-link>

                <li class="sidebar-child">
                <router-link :to="{name:'Main'}">
                  <span class="sidebar-title">
                    <span class="sidebar-title-icon icon-bg7"><ion-icon name="cloud"></ion-icon></span>
                    <span class="sidebar-title-text">Help</span>
                  </span>
                </router-link>

                
 
              </li>
            </ul>
      </div>
    </div>
</template>

<script>

export default {
    // el: '#app',

};
</script>
