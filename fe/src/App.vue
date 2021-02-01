<template>
  <div>
    <router-view></router-view>
    <Main></Main>
  </div>
</template>

<script>
// import "@/css/index.css"
import Main from '@/components/Main/Main'

export default {
  components: {
    Main
  },
  name: 'App',
}
</script>

<style>

</style>
