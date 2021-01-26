<template>
  <div>
    <div class="alert alert-danger alert-dismissible" role="alert" :style="{ display: display }">
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
          <p class="warning-form">
            <span class="warning-text" v-if="!isUserIdValid && userId">
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
          <router-link :to="{name:'FindIdPw'}"><div class="sign-setting-findIdPw">아이디 또는 비밀번호를 잊으셨습니까?</div></router-link>
          <h6>아직 계정이 없으시다면? <router-link to="/signup">회원 가입</router-link></h6>
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
      display: 'none'
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
        // this.$router.push('/main');
        console.log('아유데어?')
      } catch (error) {
        this.logMessage = error.response.error;
        this.loginError();
      } finally {
        this.initForm();
      }
    },
    initForm() {
      this.userId = '';
      this.password = '';
    },
    loginError() {
      this.display = 'block';
    }
  }
};
</script>
