<template>
  <div>
    <div class="alert alert-danger alert-dismissible" role="alert">
      <button
        type="button"
        class="close"
        data-dismiss="alert"
        aria-label="Close"
      >
        <span aria-hidden="true">×</span>
      </button>
      <div class="alert-message">
        <strong
          >아이디 또는 비밀번호가 일치하지 않습니다. 다시 시도해주세요.</strong
        >
      </div>
    </div>
    <b-container class="container-setting">
      <!-- <h1>로그인</h1> -->
      <!-- <p>{{ logMessage }}</p> -->
      <google/>
      <kakao/>
      <hr class="inContent or" />
      <b-col class="bcol-login">
        <b-form @sumbit.prevent="submitLogin">
          <div class="input-group">
            <div class="input-group-prepend">
              <div class="input-group-text">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="18"
                  height="18"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="#495057"
                  stroke-width="1"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                >
                  <path
                    d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"
                  ></path>
                  <polyline points="22,6 12,13 2,6"></polyline>
                </svg>
              </div>
            </div>
            <input
              v-model="userId"
              class="form-control form-control-lg"
              placeholder="example@example.com"
              type="text"
              autocapitalize="off"
            />
          </div>
          <p class="warning-form warning-text">
            <span v-if="!isUserIdValid && userId">
              id를 이메일형식으로 입력하세요.
            </span>
          </p>

          <div class="input-group">
            <input
              v-model="password"
              class="form-control form-control-lg"
              :type="passwordType"
              placeholder="영문, 숫자, 특수문자 포함 10~15자 이내"
            />
            <!-- 눈 모양 클릭하면 아이콘 바뀌면서 비밀번호 표출(구현예정) -->
            <div class="input-group-append">

              <div class="input-group-text input-group-button" @click="viewPassword">
                <font-awesome-icon
                  :icon="['far', fwName]"
                  :style="{ color: '#495057' }"
                />
              </div>
            </div>
          </div>
          <button
            class="btn btn-normal btn-large"
            :disabled="isLoginValid"
            @click="loginComplete"
          >
            로그인
          </button>
        </b-form>
        <div class="sign-setting">
          <h6><a href="#">비밀번호</a> 를 잊어버리셨습니까?</h6>
          <h6>아직 계정이 없으시다면? <a href="#">회원 가입</a></h6>
        </div>
      </b-col>
    </b-container>
  </div>
</template>

<script>
import { validateEmail } from '@/utils/validations';
import Google from "@/components/user/snsLogin/Google";
import Kakao from "@/components/user/snsLogin/Kakao";


export default {
  components: { 
    Google,
    Kakao,
    },
  data() {
    return {
      userId: '',
      password: '',
      passwordType:"password",
      fwName:"eye",
      //log
      logMessage: '',
    };
  },
  computed: {
    isUserIdValid() {
      return validateEmail(this.userId);
    },

    isLoginValid() {
      if (!this.isUserIdValid || !this.password) {
        return true;
      }
      return false;
    },
  },
  methods: {
    viewPassword() {
      // tupe이 password가 tureaus text, false라면 type이 password
      this.passwordType = this.passwordType==="password" ? "text" : "password";
      if(this.passwordType === "text") {
        this.fwName = "eye-slash"
      }else {
        this.fwName = "eye"
      }
    },
    async loginComplete() {
      try {
        const userData = {
          userId: this.userId,
          password: this.password,
        };
        await this.$store.dispatch('LOGIN', userData);
        this.$router.push('/main');
      } catch (error) {
        this.logMessage = error.response.error;
      } finally {
        this.initForm();
      }
    },
    initForm() {
      this.userId = '';
      this.password = '';
    },
  }
};
</script>

<style>
.btn {
  color: white;
}
</style>
