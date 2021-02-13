<template> 
    <b-container class="container-setting find-idpw">
        <template v-if="showCertiForm">
          <div class="form-sort id-chk">
              아이디: <input v-model="userId" class="form-control form-control-lg find" placeholder="example@example.com" type="text"/>
              <button class="btn btn-normal btn-authentic" @click="checkId" :disabled="!isUserIdValid">아이디 확인</button>
          </div>
          <div @click="checkId">
            <phone-certification
              @checkCertification="checkCertification"
            ></phone-certification>
          </div>
            <hr>
        </template>

          <b-col :style="{display:pwDisplay}">
          <div class="newPW-form">
            <form @submit.prevent="submitChangePw">
              <div class="newPW-form-move">
                <div class="right-sort form-sort">
                  <p class="form-sort-text">새 비밀번호:</p>
                  <p class="form-sort-text">비밀번호 확인:</p>
                </div>
                <div class="form-sort">
                  <input v-model="newPw" class="form-control form-control-lg find" type="password"/> <br>
                  <input v-model="newPwConfirm" class="form-control form-control-lg find" type="password"/>
                </div>
              </div>
              <button class="btn btn-normal btn-middle newPWBtn-move" :disabled="isPasswordChangeValid">비밀번호 변경</button>
            </form>
          </div>
        </b-col>
    </b-container>
</template>

<script>
import PhoneCertification from './user/PhoneCertification.vue';
import { validateEmail, validatePassword } from '@/utils/validations';
import { changePw } from '@/api/user';
import { userIdChk } from '@/api/user'

export default {
    components: { PhoneCertification },
    data() {
        return {
            userId: "",
            authenId:"",
            newPw: "",
            newPwConfirm: "",
            pwDisplay:'none',
            showCertiForm: true,
        }
    },
    computed : {
      isUserIdValid() {
        if (!this.userId) {
          return true;
        }
        return validateEmail(this.userId);
      },
      isPasswordValid() {
        if (!this.newPw) {
          return true;
        }
        return validatePassword(this.newPw);
      },
      isPasswordConfirmValid() {
        if (!this.newPwConfirm) {
          return true;
        } else if (this.newPw != this.newPwConfirm) {
          return false;
        }
        return true;
      },
      isPasswordChangeValid() {
        if (
          !this.isPasswordValid ||
          !this.isPasswordConfirmValid
        ) {
          return true;
        }
        return false;
      },
    },
    methods: {
        async checkId(){
          const response = await userIdChk(this.userId)
          // 인증번호 params확인필요
          console.log(response)
          this.authenId = response.data;
          if (response.data === false) {
             alert('아이디가 틀렸습니다.')
            this.userId = ""
          }
          return;
          },
        checkCertification() {
          this.showCertiForm =  false;
          this.pwDisplay = 'block';
          },
        submitChangePw() {
          const userData = {
            // ...this.$store.state.userInfo,
            u_email:this.userId,
            u_pw: this.newPw,
          };
          console.log(userData)
          changePw(userData)
          // 로그인 버튼 누르고 라우터로 가게 하기
          
        },
    },
    
}
</script>

<style>

</style>

