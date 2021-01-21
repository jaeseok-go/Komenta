<template>
  <b-container class="container-setting">
    <h2 class="title-text">회원가입하기</h2>
    <!-- <p>{{ logMessage }}</p> -->
    <b-form @submit.prevent="submitSignup">
      <b-row>
        <b-col class="col-setting">
          <div>
            <div>
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
              <!-- 중복 아이디가 아닐 때 표출(구현예정) -->
              <p
                class="icon-inline-block"
                v-show="isUserIdValid && !isDuplicaion"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="40"
                  height="40"
                  viewBox="0 0 28 28"
                  fill="none"
                  stroke="rgb(0, 218, 65)"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                >
                  <polyline points="20 6 9 17 4 12"></polyline>
                </svg>
              </p>
              <!-- 중복 아이디일 때 표출(구현예정) -->
              <p
                class="icon-inline-block"
                v-show="isUserIdValid && !isDuplicaion"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="40"
                  height="40"
                  viewBox="0 0 28 28"
                  fill="none"
                  stroke="rgb(211, 12, 12)"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                >
                  <line x1="18" y1="6" x2="6" y2="18"></line>
                  <line x1="6" y1="6" x2="18" y2="18"></line>
                </svg>
              </p>
            </div>
            <p class="warning-form warning-text">
              <span v-if="!isUserIdValid">
                id를 이메일형식으로 입력하세요.
              </span>
            </p>

            <div>
              <div class="input-group">
                <input
                  v-model="password"
                  class="form-control form-control-lg"
                  type="password"
                  placeholder="영문, 숫자, 특수문자 포함 10~15자 이내"
                />
                <!-- 눈 모양 클릭하면 아이콘 바뀌면서 비밀번호 표출(구현예정) -->
                <div class="input-group-append">
                  <div class="input-group-text input-group-button">
                    <font-awesome-icon :icon="['far', 'eye']" :style="{color:'#495057'}" />
                  </div>
                </div>
              </div>
            </div>
            <p>
              <span v-if="!isPasswordValid">
                password를 8자 이상 입력하세요.
              </span>
            </p>

            <div>
              <input
                v-model="passwordConfirm"
                class="form-control form-control-lg"
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
                class="form-control form-control-lg"
                placeholder="이름을 입력하세요."
                type="text"
              />
            </div>

            <button class="btn btn-normal btn-middle btn-center">
              휴대폰 인증
            </button>
          </div>

          <div>
              <input type="checkbox" id="term1" value="회원약관" v-model="isTerm" />
              <label for="term1">회원약관(필수)</label>
              <!-- <span>회원약관(필수)</span> -->
              <span @click="termPopup = true">약관보기</span>
          </div>
          <div>
            <input type="checkbox" id="term2" value="개인정보" v-model="isTerm" />
            <label for="term2">개인정보 수집 및 이용 동의(필수)</label>
            <span @click="termPopup = true">약관보기</span>
          </div>
          <div>
            <input type="checkbox" id="term3" value="마케팅홍보" v-model="isTerm" />
            <label for="term3">마케팅/홍보의 수집 및 이용 동의(선택)</label>
            <span @click="termPopup = true">약관보기</span>
          </div>
          <div>
            <input type="checkbox" id="term4" value="전체동의" v-model="allTerm" />
            <label for="term4">전체 동의</label>
          </div>
        </b-col>
        <b-col>
          <!-- DB에서 받아와서 가장 최신 등록된 VOD 포스터를 광고로 띄우면 될듯(구현예정) -->
          <img src="@/assets/images/test.png" alt="테스트용">
        </b-col>
      </b-row>
      <button
        class="btn btn-normal btn-middle"
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
      isTerm: [],
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
        return true;
      } else if (this.password != this.passwordConfirm) {
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
