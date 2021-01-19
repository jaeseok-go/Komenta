<template>
  <div class="user join wrapC">
    <h1>비밀번호 변경</h1>
    <div class="form-wrap">
      <div class="input-with-label">
        <input v-model="nickName" id="nickname" placeholder="닉네임" type="text" />
        <label for="nickname">닉네임</label>
      </div>

      <div class="input-with-label">
        <input v-model="email" id="email" placeholder="이메일" type="text" />
        <label for="email">이메일</label>
      </div>
      <h4 class = "auth">인증하기</h4>

      <div class="input-with-label">
        <input v-model="password" id="password" :type="passwordType" placeholder="새 비밀번호" />
        <label for="password">비밀번호</label>
      </div>

      <div class="input-with-label">
        <input
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          id="password-confirm"
          placeholder="새 비밀번호 확인"
        />
        <label for="password-confirm">비밀번호 확인</label>
      </div>
    </div>


    <button 
    class="btn-bottom"
    :class="{disabled:isButtonDisabled}"
    :disabled="isButtonDisabled"
    @click= "joinComplete">SAVE</button>
  </div>
</template>

<script>
export default {
  data: () => {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      nickName: "",
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false
    };
  },
  methods: {
    joinComplete() {
      console.log("비밀번호변경!")
      this.$router.push("/user/joinComplete");
    }
  },
  computed : {
    isButtonDisabled (){
      if (!this.nickName || !this.email || !this.password || !this.passwordConfirm) {
       return true
      }
      return false
    }
  }
};
</script>
