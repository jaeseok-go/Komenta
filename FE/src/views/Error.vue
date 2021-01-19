<template>
  <div>
    <img src="@/assets/images/anger.jpeg" alt="anger" width=100% height=100%>
    <button class="btn-bottom" @click="goHome">Home</button>
    <h1>ERROR!!!!</h1>
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
