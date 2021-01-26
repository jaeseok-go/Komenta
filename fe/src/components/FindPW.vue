<template> 
    <b-container class="container-setting">
        <b-col class="col-setting col-center">
            <div class="phoneNum-form">
                <form @submit.prevent="sendCertificationNumber">
                    <div class="form-sort">
                        <div class="right-sort form-sort">
                            <p class="form-sort-text">아이디:</p>
                            <p class="form-sort-text">휴대폰 번호:</p>
                        </div>
                        <div class="form-sort">
                            <input v-model="userId" class="form-control form-control-lg find" placeholder="example@example.com" type="text"/> <br>
                            <input v-model="phoneNumber" class="form-control form-control-lg find" type="text"/>
                        </div>
                    </div>
                    <div class="form-sort">
                      <button class="btn btn-normal btn-authentic" :disabled="!putPhoneNum">휴대폰 인증</button>
                    </div>
                </form>
            </div>
            <div class="authentic-form" :style="{display:authenDisplay}">
                <span class="authentic-form-warning-text">{{resTimeData}}</span> <input type="text" class="form-control form-control-lg authentic" v-model="authenNum">
                <button class="btn btn-normal btn-authentic" @click="getPWchangeForm" :disabled="!putAuthenNum">인증하기</button>
                <p :style="{display:resetBtnDisplay}">인증 문자가 도착하지 않았다면? <b class="" @click="smsReset">다시보내기</b></p>
            </div>
        </b-col>
        <b-col :style="{display:pwDisplay}">
          <hr>
          <div class="newPW-form">
            <form @submit.prevent="changePW">
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
              <button class="btn btn-normal btn-middle newPWBtn-move">비밀번호 변경</button>
            </form>
          </div>
        </b-col>
    </b-container>
</template>

<script>
// import { validatePhoneNum } from '@/utils/validations';
// import '@/assets/css/app.css';

export default {
    data() {
        return {
            userId: "",
            phoneNumber: "",
            newPw: "",
            newPwConfirm: "",

            timeCount:300,
            resTimeData : '',
            polling: null,
            authenDisplay:'none',
            pwDisplay:'none',
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
        sendCertificationNumber() {
           window.alert("인증 번호를 발송했습니다.");
           this.start();
           this.authenDisplay='block';
        },
        getPWchangeForm(){
            if(this.confirmNum === this.authenNum) {
            window.alert("인증에 성공했습니다.");
            this.pwDisplay='block';
            }else {
             window.alert("인증 실패했습니다. 다시 시도해주세요.");
            }
        },
        start(){ // 1초에 한번씩 start 호출
            this.resTimeData = this.prettyTime();
            this.polling = setInterval(()=>{
                this.timeCount--;
                this.resTimeData = this.prettyTime();
                if(this.timeCount == 0) this.timeStop();
                if(this.timeCount == 270) {
                this.resetBtnDisplay = 'block'
                }
            }, 1000);
        },
        // 시간 형식으로 변환 리턴
        prettyTime() { 
            let time = this.timeCount / 60;
            let minutes = parseInt(time);
            let secondes = Math.round((time - minutes) * 60);
            return this.pad(minutes, 2) + ":" + this.pad(secondes, 2);
        },
        // 2자리수로 만들어줌 09,08... 
        pad(n, width) {
            n = n + '';
            return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
        },
        timeStop() {
            clearInterval(this.polling);
            this.polling = null;
        },
        // 재발행 
        smsReset() {
            clearInterval(this.polling);
            this.timeCount = 300;
            //sms 인증 문자 다시 보내는 로직(구현예정)
            this.start();
        },
    },
    beforeDestroy() { 
        clearInterval(this.polling) 
    }
}
</script>

<style>

</style>

