<template>
    <div>
        <h1>가입하기</h1>
        <p>{{ logMessage }}</p>
        <form @submit.prevent="submitSignup">
            <div>
                <div>
                    <div>
                        <input 
                        v-model="username" 
                        placeholder="이름을 입력하세요." 
                        type="text" >
                    </div>

                    <div>
                        <input 
                        v-model="userId"  
                        placeholder="id를 입력하세요." 
                        type="text"  
                        autocapitalize="off">
                    </div>

                    <div>
                        <input
                        v-model="password"
                        type="passdword"
                        placeholder="비밀번호를 입력하세요"
                        >
                    </div>

                    <div>
                        <input
                        v-model="passwordConfirm"
                        :type="password"
                        placeholder="비밀번호를 다시 한번 입력하세요."
                        >
                    </div>

                    <button>휴대폰번호입력</button>

                </div>

                <label>
                    <input v-model="isTerm" type="checkbox" id="term" >
                    <span>약관을 동의합니다.</span>
                </label>
                <span @click="termPopup=true">약관보기</span>
                <button 
                :disabled="isSignupDisabled"
                @click= "signupComplete"
                >가입하기</button>
            </div>
        </form>
    </div>
</template>

<script>
// password,email유효성검사
import { validateEmail, validatePassword } from '@/utils/validations';



export default {
    data() {
        return {
            userId:"",
            password: "",
            passwordConfirm: "",
            username:"",
            userPhoneNumber: "",
            isTerm:"",
            termPopup: false,

            logMessage:"",
        };
    },
    computed : {
        isSignupDisabled (){
        if (!validateEmail(this.userId) || !this.username || !validatePassword(this.password) || !this.passwordConfirm || !this.isTerm) {
        return true
        }
        return false
        }
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
            this.initForm()
            
        },
        signupComplete() {
        console.log("회원가입완료!")
        this.$router.push("/login");
        },
        initForm() {
            this.username = '';
            this.password = '';
            this.nickname = '';
        }
    },
}
</script>
