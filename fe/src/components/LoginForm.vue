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

      <div class="sns-login-logo kakao">
        <svg
          enable-background="new 0 0 24 24"
          height="50"
          viewBox="0 0 24 24"
          width="50"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="m12 1c-6.627 0-12 4.208-12 9.399 0 3.356 2.246 6.301 5.625 7.963-1.678 5.749-2.664 6.123 4.244 1.287.692.097 1.404.148 2.131.148 6.627 0 12-4.208 12-9.399 0-5.19-5.373-9.398-12-9.398z"
            fill="#3e2723"
          />
          <g fill="#ffeb3b">
            <path
              d="m10.384 8.27c-.317-.893-1.529-.894-1.845-.001-.984 3.052-2.302 4.935-1.492 5.306 1.078.489 1.101-.611 1.359-1.1h2.111c.257.487.282 1.588 1.359 1.1.813-.371-.489-2.195-1.492-5.305zm-1.614 2.987.692-1.951.691 1.951z"
            />
            <path
              d="m5.365 13.68c-1.198 0-.49-1.657-.692-4.742-.429-.074-1.76.297-1.76-.673 0-.371.305-.673.679-.673 2.518.18 4.224-.47 4.224.673 0 .987-1.275.59-1.76.673-.2 3.075.505 4.742-.691 4.742z"
            />
            <path
              d="m13.154 13.579c-1.159 0-.454-1.565-.663-5.301 0-.91 1.413-.909 1.413 0v4.04c.669.089 2.135-.33 2.135.63-.001 1.007-1.576.503-2.885.631z"
            />
            <path
              d="m19.556 13.38-1.624-2.137-.24.239v1.5c0 .38-.31.688-.693.688-1.203 0-.482-1.732-.692-5.392 0-.379.31-.688.692-.688 1.045 0 .594 1.478.692 2.166 1.96-1.873 1.913-2.072 2.316-2.072.556 0 .897.691.527 1.058l-1.578 1.567 1.704 2.243c.556.725-.555 1.556-1.104.828z"
            />
          </g>
        </svg>
      </div>
      <div class="sns-login-logo google"></div>
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
              type="password"
              placeholder="영문, 숫자, 특수문자 포함 10~15자 이내"
            />
            <!-- 눈 모양 클릭하면 아이콘 바뀌면서 비밀번호 표출(구현예정) -->
            <div class="input-group-append">

              <div class="input-group-text input-group-button" @click="viewPassword">
                <!-- <i class="far fa-eye"></i> -->
                <!-- <font-awesome-icon
                  :icon="['far', 'eye']"
                  :style="{ color: '#495057' }"
                /> -->
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

export default {
  data() {
    return {
      userId: '',
      password: '',
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
  },
};
</script>

<style>
.btn {
  color: white;
}
</style>
