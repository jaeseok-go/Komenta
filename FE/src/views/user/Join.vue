
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <div class="user join wrapC">
    <h1>가입하기</h1>
    <div class="form-wrap">
      <div class="input-with-label">
        <!-- data nickName연결 -->
        <input 
        v-model="nickName" 
        id="nickname" 
        placeholder="닉네임을 입력하세요." 
        type="text" />
        <label for="nickname">닉네임</label>
      </div>

      <div class="input-with-label">
        <!-- req 2 -->
        <!-- data email 연결 -->
        <input 
        v-model="email" 
        id="email" 
        placeholder="이메일을 입력하세요." 
        type="text"  
        autocapitalize="off"/>
        <label for="email">이메일</label>
      </div>

      <div class="input-with-label">
        <!-- data password연결, type도 password로 연결 -->
        <input 
        v-model="password" 
        id="password" 
        :type="passwordType" 
        placeholder="비밀번호를 입력하세요." />
        <label for="password">비밀번호</label>
      </div>

      <div class="input-with-label">
        <!-- passwordConfirm연결, type도 연결 -->
        <input
          v-model="passwordConfirm"
          :type="passwordConfirmType"
          id="password-confirm"
          placeholder="비밀번호를 다시한번 입력하세요."
        />
        <label for="password-confirm">비밀번호 확인</label>
      </div>
    </div>

    <label>
      <input v-model="isTerm" type="checkbox" id="term" />
      <span>약관을 동의합니다.</span>
    </label>
    <!-- termPopup false였다가 누르면 true로 바꿈 -->
    <span @click="termPopup=true">약관보기</span>
    <!-- req 2 email, password, passwordConfirm, isTerm이 하나라도 없으면 비활성화 -->
    <button 
    class="btn-bottom"
    :class="{disabled:isButtonDisabled}"
    :disabled="isButtonDisabled"
    @click= "joinComplete"
    >가입하기</button>
  </div>
</template>

<script>
// password유효성검사
import PV from "password-validator";
// email유효성검사
import * as EmailValidator from "email-validator";

export default {
  data: () => {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      nickName: "",
      isTerm: false,
      isLoading: false,
      error: {
        email: false,
        password: false,
        nickName: false,
        passwordConfirm: false,
        term: false
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false
    };
  },
  computed : {
    isButtonDisabled (){
      if (!this.nickName || !this.email || !this.password || !this.passwordConfirm || !this.isTerm) {
       return true
      }
      return false
    }
  },
  methods: {
    joinComplete() {
      console.log("회원가입완료!")
      this.$router.push("/user/JoinComplete");
    }
  }
};
</script>


