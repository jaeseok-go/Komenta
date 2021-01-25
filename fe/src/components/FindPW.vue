<template> 
    <b-container>
        <b-col>
            <div>
                <form @submit.prevent="putAuthenBtn">
                    <div>아이디: <input v-model="userId" placeholder="example@example.com" type="text"/></div>
                    <div>휴대폰 번호: <input v-model="phoneNumber" type="text"/></div>
                    <button :disabled="!putPhoneNum">휴대폰 인증</button>
                </form>
            </div>
            <div :style="{display:authenDisplay}">
            {{timeCount}} <input type="text" v-model="authenNum">
            <button @click="putIdBtn" :disabled="!putAuthenNum">인증하기</button>
            </div>
        </b-col>

        <b-col :style="{display:idDisplay}">
        <hr>
            <form @submit.prevent="changePW">
                <div>새 비밀번호: <input type="text" v-model="newPw"></div>
                <div>비밀번호 확인: <input type="text" v-model="newPwConfirm"></div>
                <button>비밀번호 변경</button>
            </form>
        </b-col>
    </b-container>
</template>

<script>
// import { validatePhoneNum } from '@/utils/validations';


export default {
    data() {
        return {
            userId: "",
            phoneNumber: "",
            newPw: "",
            newPwConfirm: "",

            timeCount:'300',
            authenDisplay:'none',
            idDisplay:'none',
            authenNum: "",
            confirmNum:'0000'
        }
    },
    computed: {
        putPhoneNum() {
            if (this.phoneNumber === '970121') {
                return true;
            } 
            return false;
        },
        putAuthenNum() {
            if (this.authenNum.length >=4 ){
                return true;
            }
            return false;
        }
    },
    methods: {
        putAuthenBtn() {
           window.alert("인증 번호를 발송했습니다.");
           this.authenDisplay='block';
        },
        putIdBtn(){
            if(this.confirmNum === this.authenNum) {
            window.alert("인증에 성공했습니다.");
            this.authenDisplay='block';
            }else {
             window.alert("인증 실패했습니다. 다시 시도해주세요.");
            }
        }

    },
}
</script>

<style>

</style>

