<template>
  <div>

        <router-link :to="{name:'VodPopular'}">조회순</router-link> | <router-link :to="{name:'VodComment'}">댓글순</router-link> | <router-link :to="{name:'VodRecent'}">최신순</router-link>
        <!-- 조회순 가장 많은 순서대로 ve_id, v_poster 요청
        댓글순 가장 많은 순서대로 ve_id, v_poster 요청
        조회순 가장 많은 순서대로 ve_id, v_poster 요청 -->

        <router-view></router-view>
  </div>
</template>

<script>
export default {

    
}
</script>

<style>

</style>