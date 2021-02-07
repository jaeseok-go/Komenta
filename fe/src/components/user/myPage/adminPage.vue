<template>
  <b-col>
    <nav>
      <router-link :to="{name:'UserManage'}">회원 관리</router-link> |
      <router-link :to="{name:'VodManage'}">VOD관리</router-link>
    </nav>
    <div>
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