<template>
  <b-container>
    <h2>회원가입하기</h2>
    <!-- <p>{{ logMessage }}</p> -->
    <b-form @submit.prevent="submitSignup">
      <b-row>
        <b-col>
          <div>
            <div>
              <b-form-input
                v-model="userId"
                class="form-control form-large"
                placeholder="example@example.com"
                type="text"
                autocapitalize="off"
              />
            </div>
            <p class="warning-text">
              <span v-if="!isUserIdValid">
                id를 이메일형식으로 입력하세요.
              </span>
            </p>

            <div>
              <input
                v-model="password"
                class="form-control"
                type="password"
                placeholder="영문, 숫자, 특수문자 포함 10~15자 이내"
              />
            </div>
            <p>
              <span v-if="!isPasswordValid">
                password를 8자 이상 입력하세요.
              </span>
            </p>

            <div>
              <input
                v-model="passwordConfirm"
                class="form-control"
                type="password"
                placeholder="비밀번호를 다시 한번 입력하세요."
              />
            </div>
            <p>
              <span v-if="!isPasswordConfirmValid">
                password가 일치하지 않습니다.
              </span>
            </p>

            <div>
              <input
                v-model="username"
                class="form-control"
                placeholder="이름을 입력하세요."
                type="text"
              />
            </div>

            <button class="btn btn-normal btn-middle">휴대폰 인증</button>
          </div>

          <div>
            <label>
              <input v-model="isTerm" type="checkbox" id="term" />
              <span>회원약관(필수)</span>
            </label>
            <span @click="termPopup = true">약관보기</span>
          </div>
          <div>
            <label>
              <input v-model="isTerm" type="checkbox" id="term" />
              <span>개인정보 수집 및 이용 동의(필수)</span>
            </label>
            <span @click="termPopup = true">약관보기</span>
          </div>
          <div>
            <label>
              <input v-model="isTerm" type="checkbox" id="term" />
              <span>마케팅/홍보의 수집 및 이용 동의(선택)</span>
            </label>
            <span @click="termPopup = true">약관보기</span>
          </div>
          <div>
            <label>
              <input v-model="isTerm" type="checkbox" id="term" />
              <span>전체 동의</span>
            </label>
          </div>
        </b-col>
        <b-col>
          광고 배너
        </b-col>
      </b-row>
      <button
        class="btn btn-normal btn-middle btn-submit"
        :disabled="isSignupDisabled"
        @click="signupComplete"
      >
        가입하기
      </button>
    </b-form>
  </b-container>
</template>

<script>
// password,email유효성검사
import { validateEmail, validatePassword } from '@/utils/validations';

export default {
  data() {
    return {
      userId: '',
      password: '',
      passwordConfirm: '',
      username: '',
      userPhoneNumber: '',
      isTerm: '',
      termPopup: false,

      logMessage: '',
    };
  },
  computed: {
    isUsernameValid() {
      if (!this.username) {
        return false;
      }
      return true;
    },
    isUserIdValid() {
      if (!this.userId) {
        return true;
      }
      return validateEmail(this.userId);
    },
    isPasswordValid() {
      if (!this.password) {
        return true;
      }
      return validatePassword(this.password);
    },
    isPasswordConfirmValid() {
      if (!this.passwordConfirm) {
        return false;
      } else if (this.password !== this.passwordConfirm) {
        return false;
      }
      return true;
    },
    isSignupDisabled() {
      if (
        !this.isUsernameValid ||
        !this.isUserIdValid ||
        !this.isPasswordValid ||
        !this.isPasswordConfirmValid ||
        !this.isTerm
      ) {
        return true;
      }
      return false;
    },
  },
  methods: {
    submitSignup() {
      // const userData = {
      //     username: this.username,
      //     password: this.password,
      //     nickname: this.nickname,
      //     userPhoneNumber : this.userPhoneNumber,
      // },
      this.logMessage = `${this.username}님이 가입되었습니다`;
      this.initForm();
    },
    signupComplete() {
      console.log('회원가입완료!');
      this.$router.push('/login');
    },
    initForm() {
      this.username = '';
      this.password = '';
      this.nickname = '';
    },
  },
};
</script>
