<template>
    <div class="wrapper_about">
    <button @click="gotoLogin">LOGIN</button>
    <input class="input_about" type="checkbox">
    <div class="video2_about">
      <video src="https://www.robmillsarchitects.com/files/land/city/RMA_Web_land_city_1.mp4" loop muted autoplay></video>
    </div>
    <div class="text_about">
      <span data-text="ABOUT KOMENTA"></span>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    gotoLogin() {
      this.$router.push(`/member/login`)
    }
  }
  
}
</script>

<style scoped>
  button {
    position: fixed;
    top: 1rem;
    right: 2rem;
  }
</style>

