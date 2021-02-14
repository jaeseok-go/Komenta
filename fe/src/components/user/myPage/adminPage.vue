<template>
  <b-col class="adminTab">
    <nav class="admin-tab-form">
      <div class="admin-tab-item">
        <router-link :to="{name:'UserManage'}">회원 관리</router-link>
      </div>
      <div class="admin-tab-item">
        <router-link :to="{name:'VodManage'}">VOD관리</router-link>
      </div>
    </nav>
    <div class="admin-tab-view">
      <router-view></router-view>
    </div>
  </b-col>
</template>

<script>
export default {
  created(){
    this.$router.push({name:'UserManage'});
  }
}
</script>

<style>

</style>