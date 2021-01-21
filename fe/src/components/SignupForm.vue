<template>
    <div>
        <!-- <h1>가입하기</h1>
        <form @submit.prevent="submitSignup">
            <div>
                <div>
                    <div>
                        <input 
                        v-model="username" 
                        id="nickname" 
                        placeholder="이름을 입력하세요." 
                        type="text" />
                    </div>

                    <div>
                        <input 
                        v-model="email" 
                        id="email" 
                        placeholder="이메일을 입력하세요." 
                        type="text"  
                        autocapitalize="off"/>
                    </div>

                    <div>
                        <input 
                        v-model="password" 
                        id="password" 
                        :type="passwordType" 
                        placeholder="비밀번호를 입력하세요." />
                    </div>

                    <div>
                        <input
                        v-model="passwordConfirm"
                        :type="passwordConfirmType"
                        id="password-confirm"
                        placeholder="비밀번호를 다시 한번 입력하세요."
                        />
                    </div>
                </div>

                <label>
                    <input v-model="isTerm" type="checkbox" id="term" />
                    <span>약관을 동의합니다.</span>
                </label>
                <span @click="termPopup=true">약관보기</span>
                <button 
                :disabled="isSignupDisabled"
                @click= "signupComplete"
                >가입하기</button> -->
            <!-- </div>
        </form> -->
    </div>
</template>

<script>
export default {
    name: 'SignupForm',
    components: {

    },
};
</script>

<style lang="scss" scoped>

</style>