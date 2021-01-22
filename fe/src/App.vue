<template>
  <div>
    <Google/>
    <router-view></router-view>
  </div>
</template>

<script>
import Google from "@/components/user/snsLogin/Google"
export default {
  name: 'App',
  components: {
   Google
  }
}
</script>

<style>
</style>
