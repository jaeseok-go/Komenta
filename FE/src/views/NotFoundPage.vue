<template>
  <div>
    <!-- <h1>Page is not found....</h1> -->
    <img src="@/assets/images/sad.jpg" alt="sadness" width=100% height=100%>
    <h1>Page Not Found!!!!</h1>
    <button class="btn-bottom" @click="goHome">Home</button>
  </div>
</template>

<script>
export default {
  methods:{
    goHome() {
      this.$router.push("/");
    }
  }
};
</script>

<style></style>
