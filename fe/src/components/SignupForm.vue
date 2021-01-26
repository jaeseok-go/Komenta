<template>
  <b-container class="container-setting">
    <!-- <p>{{ logMessage }}</p> -->
    <b-form @submit.prevent="signin">
      <b-row>
        <b-col class="col-setting">
          <h2 class="title-text">회원가입하기</h2>
          <div>
            <div>
              <div class="input-group">
                <div class="input-group-prepend">
                  <div class="input-group-text">
                    <!-- 메일아이콘 -->
                    <font-awesome-icon :icon="['far', 'envelope' ]" :style="{color:'#495057'}" />
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
              <p class="icon-inline-block" v-show="isUserIdValid && !isDuplicaion">
              <!-- 체크표시 아이콘 -->
                <font-awesome-icon class="fw-icon fwCheck" :icon="['fas', 'check' ]" />
              </p>
              <!-- 중복 아이디일 때 표출(구현예정) -->
              <p class="icon-inline-block" v-show="isUserIdValid && !isDuplicaion">
              <!-- 엑스표시 아이콘 -->
                <font-awesome-icon class="fw-icon fwTimes" :icon="['fas', 'times' ]" />
              </p>
            </div>
            <p class="warning-form warning-signup">
              <span class="warning-text" v-if="!isUserIdValid">
                id를 이메일형식으로 입력하세요.
              </span>
            </p>

            <div>
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
                    <font-awesome-icon :icon="['far', fwName ]" :style="{color:'#495057'}" />
                  </div>
                </div>
              </div>
            </div>
            <p class="warning-form warning-signup">
              <span class="warning-text" v-if="!isPasswordValid">
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
            <p class="warning-form warning-signup">
              <span class="warning-text" v-if="!isPasswordConfirmValid">
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

            <div>
              <p></p>
              <input
                v-model="userPhoneNumber"
                class="form-control form-control-lg"
                placeholder="휴대폰번호를 입력하세요(-제외한 숫자만 입력)"
                type="text"
              />
              <input type="text" v-if="phonebtn" class="form-control form-control-lg">
              <!-- 인증번호 시간 + 인증번호 다시보내기 버튼 필요 -->
            </div>
            <button class="btn btn-normal btn-middle btn-center" @click="clickphonebtn">
              휴대폰 인증
            </button>
          </div>

          <div class="fw-checkbox">
              <input type="checkbox" id="term1" value="term1" v-model="isTerm.term1"/>
              <label for="term1"><font-awesome-icon class="chk-label" :icon="[isTerm.icon1, 'check-circle']" /></label>
              <label for="term1">회원약관(필수)</label>
              <span class="termView">약관보기</span>
          </div>
          <div class="fw-checkbox">
            <input type="checkbox" id="term2" value="term2" v-model="isTerm.term2"/>
            <label for="term2"><font-awesome-icon class="chk-label" :icon="[isTerm.icon2, 'check-circle']" /></label>
            <label for="term2">개인정보 수집 및 이용 동의(필수)</label>
            <span class="termView">약관보기</span>
          </div>
          <div class="fw-checkbox">
            <input type="checkbox" id="term3" value="term3" v-model="isTerm.term3"/>
            <label for="term3"><font-awesome-icon class="chk-label" :icon="[isTerm.icon3, 'check-circle']" /></label>
            <label for="term3">마케팅/홍보의 수집 및 이용 동의(선택)</label>
            <span class="termView">약관보기</span>
          </div>
          <div class="fw-checkbox">
            <input type="checkbox" id="allTerm" value="allTerm" v-model="allTerm" @click="allTermcheck"/>
            <label for="allTerm"><font-awesome-icon class="chk-label" :icon="[isTerm.icon4, 'check-circle']" /></label>
            <label for="allTerm">전체 동의</label>
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
import { registerUser } from '@/api/auth';
// password,email유효성검사
import { validateEmail, validatePassword } from '@/utils/validations';

export default {
  data() {
    return {
      userId: '',
      password: '',
      passwordConfirm: '',
      fwName:"eye",
      username: '',
      userPhoneNumber: '',
      phonebtn:false,
      passwordType:"password",
      isTerm: {
        term1:false,
        term2:false,
        term3:false,
        icon1:'far',
        icon2:'far',
        icon3:'far',
        icon4:'far'
      },
      allTerm:false,
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
    clickphonebtn() {
      this.phonebtn = !this.phonebtn
    },
    viewPassword() {
      // tupe이 password가 tureaus text, false라면 type이 password
      this.passwordType = this.passwordType==="password" ? "text" : "password";
      if(this.passwordType === "text") {
        this.fwName = "eye-slash"
      }else {
        this.fwName = "eye"
      }
    },
    isDuplicaion() {
      console.log('DUPLICATED')
    },
    allTermcheck(){
      console.log(this.allTerm)
      this.allTerm=!this.allTerm
      if (this.allTerm) {
        this.isTerm.term1=true
        this.isTerm.term2=true
        this.isTerm.term3=true
        this.isTerm.icon1 = 'fas'
        this.isTerm.icon2 = 'fas'
        this.isTerm.icon3 = 'fas'
        this.isTerm.icon4 = 'fas'
        // this.allTerm=true
      } else {
        this.isTerm.term1=false
        this.isTerm.term2=false
        this.isTerm.term3=false
        this.isTerm.icon1 = 'far'
        this.isTerm.icon2 = 'far'
        this.isTerm.icon3 = 'far'
        this.isTerm.icon4 = 'far'
        // this.allTerm=false
      }
    },
    async submitSignup() {
      // nickname params로 넘겨주기 추가해야함
      const userData = {
          userId:this.userId,
          username: this.username,
          password: this.password,
          // nickname: this.nickname,
          userPhoneNumber : this.userPhoneNumber,
      };
      const { data } = await registerUser(userData);
      console.log(data.username);
      this.logMessage = `${this.username}님이 가입되었습니다`;
      this.initForm();
      this.$router.push('/login');
    },
    signupComplete() {
      console.log('회원가입완료!');
    },
    initForm() {
      this.username = '';
      this.password = '';
      this.nickname = '';
    },
    // firebase email로 회원가입 추가
    async signin() {
      this.$firebase.auth().createUserWithEmailAndPassword(this.userId, this.password)
      .then(() => {
        // Signed in
        const user = this.$firebase.auth().currentUser;
        console.log('폰번',this.userPhoneNumber,user.phoneNumber)
        user.updateProfile({
          displayName : this.username,
          phoneNumber : this.userPhoneNumber
        })
        .then(()=>{
          console.log('유저있니',user.user)
          this.$firebase.auth().signInWithEmailAndPassword(this.userId, this.password)
          console.log(`${this.username}님이 로그인!!!`)
          console.log('비번은이걸로',user.refreshToken)
          this.$router.push({name:'Login'})

        })
        // ...
      })
      .catch((error) => {
        const errorCode = error.code;
        const errorMessage = error.message;
        alert(errorCode,errorMessage)
        // console.log(errorCode)
        // console.log(errorCode)
      });

 
    },

  },
  watch: {
    'isTerm.term1': function() {
      if(this.isTerm.term1) {
        this.isTerm.icon1 = 'fas'
      }else {
        this.isTerm.icon1 = 'far'
      }
    },
    'isTerm.term2': function() {
      if(this.isTerm.term2) {
        this.isTerm.icon2 = 'fas'
      }else {
        this.isTerm.icon2 = 'far'
      }
    },
    'isTerm.term3': function() {
      if(this.isTerm.term3) {
        this.isTerm.icon3 = 'fas'
      }else {
        this.isTerm.icon3 = 'far'
      }
    },
  },
};
</script>

