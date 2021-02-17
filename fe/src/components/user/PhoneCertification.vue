<template>
    <b-col class="col-setting col-center">
      <div class="phoneNum-form">
        <form @submit.prevent="sendCertificationNumber">
          <p class="phone-label">휴대폰 번호 : </p><input type="text" @focus="isAuthen" class="form-control form-control-lg find" v-model="userPhoneNum" placeholder="휴대폰 번호를 입력하세요(-제외한 숫자만 입력)"/>
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
import { phoneAuth } from '@/api/user';
import store from '@/stores/index';
import { mapState } from 'vuex';
import { registerUser } from '@/api/user'; //, userIdChk, userNickNameChk

export default {
    name: 'PhoneCertification',
    data() {
        return {
            userId:'',
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
    props: ['getIdChk', 'getUserId'],
    computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    }),
    // 인증번호가 4자리아니고 3자리인것도 있다
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
    isAuthen(){
      console.log("props getIdChk 체크 : ",this.getIdChk)
      console.log("props userId 체크 : ", this.getUserId)
      if(this.getIdChk != undefined) {
        console.log("비밀번호 찾기 페이지임")
        if(!this.getIdChk && !this.getUserId){
          alert("아이디 체크를 먼저 진행해주세요")
          this.$emit('idChkFocus');
        }
      }else if(this.getIdChk == undefined){
        console.log("비밀번호 찾기 아니니까 무시ㄱㄱ")
      }
    },
    async sendCertificationNumber() {
      const response = await phoneAuth(this.userPhoneNum)
      // 인증번호 params response에서 확인필요
      // this.confirmNum = `${response.data.auth_number}`;
      // response.data.u_email
      this.userId = response.data.u_email;
      console.log(response)
      this.confirmNum = response.data.auth_number;
      window.alert('인증 번호를 발송했습니다.');
      this.start();
      this.authenDisplay = 'block';
    },
    async checkCertification() {
      console.log(this.confirmNum,this.authenNum)
      if (this.confirmNum === this.authenNum) {
        window.alert('인증에 성공했습니다.');
        this.timeStop();
        this.resetBtnDisplay = 'none';
        if ((this.userInfo.u_phone_number === null) && this.userInfo.u_email) {
          console.log(this.userInfo,this.snsFlag,'어떻게들어오니ㅠ')
          this.$store.commit('setPhonenum',this.userPhoneNum);
          console.log(this.userInfo,'데이터들어왔니카카오구글')
          const res = await registerUser(this.userInfo);
          await this.$store.dispatch('LOGIN',{
            u_email:this.userInfo.u_email,
            u_pw:this.userInfo.u_email
          })
          console.log(res,'sns회원가입과 로그인')
          this.$router.push(`/main/vodpopular`)
        } else {
          store.commit('setEmail', this.userId);
          store.commit('setPhonenum',this.userPhoneNum);
          this.$emit('checkCertification')
        }
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