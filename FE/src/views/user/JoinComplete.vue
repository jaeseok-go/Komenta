<template>
  <div class="user join wrapC">
    <h1>가입완료</h1>
    <p class="mail-text">회원가입 인증 메일이 발송되었습니다. <br/>이메일을 확인해 주세요.</p>
    <button class="btn-bottom">메일 재발송</button>
    <img src="@/assets/images/happy.jpg" alt="happy" width=100% height=100%>
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
