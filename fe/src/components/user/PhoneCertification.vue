<template>
    <b-col class="col-setting col-center">
      <div class="phoneNum-form">
        <form @submit.prevent="sendCertificationNumber">
          <p class="phone-label">휴대폰 번호 : </p><input type="text" class="form-control form-control-lg find" v-model="userPhoneNum" placeholder="휴대폰 번호를 입력하세요(-제외한 숫자만 입력)"/>
          <button class="btn btn-normal btn-authentic" :disabled="!putPhoneNum">
            휴대폰 인증</button>
          <br />
        </form>
        <div class="authentic-form" :style="{ display: authenDisplay }">
          {{ resTimeData }} <input type="text" class="form-control form-control-lg authentic" v-model="authenNum" />
          <button class="btn btn-normal btn-authentic" @click="checkCertification" :disabled="!putAuthenNum">인증하기</button>
          <p class="authentic-text" :style="{display:resetBtnDisplay}">인증 문자가 도착하지 않았다면? <b class="" @click="smsReset">다시보내기</b></p>
        </div>
      </div>
    </b-col>
</template>

<script>
import { validatePhoneNum } from '@/utils/validations';
// import { phoneAuth } from '@/api/user'

export default {
    name: 'PhoneCertification',
    data() {
        return {
            userPhoneNum:'',
            phonebtn:false,
            timeCount: 300,
            resTimeData : '',
            polling: null,
            authenDisplay: 'none',
            idDisplay: 'none',
            resetBtnDisplay: 'none',
            authenNum: '',
            confirmNum: '0000',
        };
    },
    computed: {
    putAuthenBtn() {
      return validatePhoneNum(this.userPhoneNum);
    },
    putPhoneNum() {
      if(this.putAuthenBtn) {
        return true;
      }
      return false;
    },
    putAuthenNum() {
      if (this.authenNum.length >= 4) {
        return true;
      }
      return false;
    },
  },
  methods: {
    sendCertificationNumber() {
      // const response = await phoneAuth(this.userPhoneNum)
      // 인증번호 params확인필요
      // this.authenNum = response;
      // console.log(response)
      window.alert('인증 번호를 발송했습니다.');
      this.start();
      this.authenDisplay = 'block';
    },
    checkCertification() {
      if (this.confirmNum === this.authenNum) {
        window.alert('인증에 성공했습니다.');
        this.timeStop();
        this.resetBtnDisplay = 'none';
        
        this.$emit('checkCertification',this.userPhoneNum)
      } else {
        window.alert('인증 실패했습니다. 다시 시도해주세요.');
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
};
</script>

<style lang="scss" scoped>

</style>