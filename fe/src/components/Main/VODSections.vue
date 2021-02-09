<template>
  <div>
    <!-- <div class="container">
      <div class="btn-group">
        <div class="btn">First</div>
        <div class="btn active">Middle</div>
        <div class="btn">Last</div>
     </div> -->
      <div class="selectTab">
        <router-link :to="{name:'VodPopular'}" class="text-color">
          <ion-chip outline color="primary">
            <ion-label>인기순</ion-label>
          </ion-chip>
        </router-link>
        <router-link :to="{name:'VodComment'}" class="text-color">
        <ion-chip outline color="success">
          <ion-label>댓글순</ion-label>
        </ion-chip>
        </router-link>
        <router-link :to="{name:'VodRecent'}" class="text-color">
          <ion-chip outline color="danger">
            <ion-label>최신순</ion-label>
          </ion-chip>
        </router-link>
      </div>
      <router-view></router-view>
    </div>
</template>

<style>
  ion-chip {
    margin: 0.2rem;
    padding: 0.5rem;
  }

  ion-label {
    color: black;
  }

  .selectTab {
    z-index: 999;
    position: relative;
    display: inline-block;
  }

  /* .col {
    z-index: 998;
  } */

</style>

