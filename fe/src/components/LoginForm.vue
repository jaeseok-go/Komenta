<template>
    <div>
      <h1>로그인</h1>
      <p>{{ logMessage }}</p>
      <form @sumbit.prevent="submitLogin">
          <div>
            <input 
              v-model="email" 
              placeholder="이메일"
              type="text" >
          </div>
          <p>
            <span v-if="!isUserIdValid">
                이메일 형식으로 입력해주세요.
            </span>
          </p>
      

        <div>
          <input
          v-model="password"
          type="password" 
          placeholder="비밀번호를 입력하세요"
          >
        </div>

        <button
        :disabled="isLoginValid"
        @click="loginComplete"
        >로그인</button>
      </form>
    </div>
</template>

<script>
import { validateEmail } from '@/utils/validations'

export default {
    data() {
        return {
            userId: "",
            password: "",
            //log
            logMessage: "",
        };
    },
    computed: {
      isUserIdValid() {
        if (!this.userId) {
          return true;
        }
        return validateEmail(this.userId);
      },
      isLoginValid() {
        if (!this.isUserIdValid || !this.password) {
          return true
        }
          return false
      }
    },
    methods: {
      async loginComplete() {
        try {
          const userData = {
            userId: this.userId,
            passdword: this.password,
          }
          await this.$store.dispatch('LOGIN',userData)
          this.$router.push('/main')
        } catch (error) {
          this.logMessage = error.response.error
        } finally {
          this.initForm()
        }
      },
      initForm() {
        this.userId = '';
        this.password = '';
      }
    }
}
</script>

<style>
.btn {
  color: white;
}
</style>
