<template>
  <div class="user" id="login">
    <div class="wrapC">
      <h1>
        로그인을 하고 나면
        <br />좋은 일만 있을 거예요.
      </h1>
      <div class="input-with-label">
      <!-- req 1-1 -->
      <!-- class error.email이 참이면 error클래스가 적용, error.email이 false이고, email.length가 0이면 complete class적용  -->
        <input
          v-model="email"
          v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
          @keyup.enter="Login"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
          autocapitalize="off"
        />
        <label for="email">이메일</label>
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </div>

      <div class="input-with-label">
        <input
          v-model="password"
          type="password"
          v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
          id="password"
          @keyup.enter="Login"
          placeholder="비밀번호를 입력하세요."
        />
        <label for="password">비밀번호</label>
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>
      <!-- isSubmit이 false이면 disabled 클래스 적용, isSubmit이 false이면 버튼 비활성화 -->
      <button
        class="btn btn--back btn--login"
        @click="onLogin"
        :disabled="isButtonDisabled"
        :class="{disabled : isButtonDisabled }"
      >로그인</button>
      <div class="sns-login">
        <div class="text">
          <p>SNS 간편 로그인</p>
          <div class="bar"></div>
        </div>

        <kakaoLogin :component="component" />
        <GoogleLogin :component="component"/>
      </div>
      <div class="add-option">
        <div class="text">
          <p>혹시</p>
          <div class="bar"></div>
        </div>
        <div class="wrap">
          <p>비밀번호를 잊으셨나요?</p>
          <router-link to="/user/ChangePw" class="btn--text">비밀번호 찾기</router-link>
        </div>
        <div class="wrap">
          <p>아직 회원이 아니신가요?</p>
          <router-link to="/user/join" class="btn--text">가입하기</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
// password유효성검사
import PV from "password-validator";
// email유효성검사
import * as EmailValidator from "email-validator";
// sns로그인기능
import KakaoLogin from "../../components/user/snsLogin/Kakao.vue";
import GoogleLogin from "../../components/user/snsLogin/Google.vue";
import UserApi from "../../api/UserApi";

export default {
  components: {
    KakaoLogin,
    GoogleLogin
  },
  created() {
    this.component = this;
    // 비밀번호 8자~100자사이 숫자, 문자 포함(비밀번호형식)
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    // password에 변화가 있으면 checkForm함수 실행
    password: function(v) {
      this.checkForm();
    },
    // email에 변화가 있으면 checkForm함수 실행
    email: function(v) {
      this.checkForm();
    }
  },
  methods: {
    goError() {
      console.log('에러!');
      this.$router.push("/Error");
    },
    checkForm() {
      // email.length가 0이상이고 email이 이메일형식이 false라면(맞다면 true,아니면 false)
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;
      // password.length가 0이상이고, password형식이 false라면(틀리다면)
      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;
      // isSubmit 변수 true로 할당
      let isSubmit = true;
      // error객체 하나하나(email, password가 v에 들어감)
      Object.values(this.error).map(v => {
        // email이나 password가 true(에러!, 에러가아니라면 false임)라면 isSubmit은 false
        if (v) isSubmit = false;
      });
      // isSubmit이 true면 이메일,비밀번호 valid!, false라면 하나라도 valid하지 않음
      this.isSubmit = isSubmit;
    },
    onLogin() {
      // isSubmit이 true라면(valid하다면)
      if (this.isSubmit) {
        // email, password를 data 변수에 넣음
        let { email, password } = this;
        let data = {
          email,
          password
        };

        //요청 후에는 버튼 비활성화
        this.isSubmit = false;
        // UserApi에 requestLogin으로 data를 보냄
        UserApi.requestLogin(
          data,
          res => {
            //통신을 통해 전달받은 값 콘솔에 출력
            //console.log(res);

            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;
            // 요청성공했으니 main으로 이동
            this.$router.push("/feed/main");
          },
          // login실패
          error => {
            //요청이 끝나면 버튼 활성화
            this.isSubmit = true;
            // 요청실패했으니 Error으로 이동
            this.$router.push("/Error");
          }
        );
      }
    }
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false
      },
      isSubmit: false,
      component: this,
    };
  },
  computed : {
    isButtonDisabled (){
      if (!this.email || !this.password || !this.isSubmit) {
       return true
      } 
      return false
    }
  }
};
</script>


