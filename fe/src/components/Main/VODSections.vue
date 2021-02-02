<template>
  <div>
      <div>
        <ion-chip outline color="primary">
          <ion-label><router-link :to="{name:'VodPopular'}" class="text-color">인기순</router-link></ion-label>
        </ion-chip>
        <ion-chip outline color="success">
          <ion-label><router-link :to="{name:'VodComment'}" class="text-color">댓글순</router-link></ion-label>
        </ion-chip>
        <ion-chip outline color="danger">
          <ion-label><router-link :to="{name:'VodRecent'}" class="text-color">최신순</router-link></ion-label>
        </ion-chip>
      </div>
    
          <router-view></router-view>
  </div>
</template>

