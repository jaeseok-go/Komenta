<template>
  <b-container>
    <b-col>
      <div>
        휴대폰 번호 : <input type="text" v-model="userPhoneNum"/>
        <button @click="putAuthenBtn" :disabled="!putPhoneNum">
          휴대폰 인증</button
        ><br />
        <div :style="{ display: authenDisplay }">
          {{ timeCount }} <input type="text" v-model="authenNum" />
          <button @click="putIdBtn" :disabled="!putAuthenNum">인증하기</button>
        </div>
      </div>
    </b-col>
    <b-col :style="{ display: idDisplay }">
      <hr />
      고객님의 아이디는
      <p :style="{ display: 'inline-block' }">{{ userId }}</p>
      입니다.
    </b-col>
  </b-container>
</template>

<script>
import { validatePhoneNum } from '@/utils/validations';
export default {
  data() {
    return {
      userId: 'test',
      userPhoneNum: '',
      timeCount: 300,
      resTimeData : '',
      authenDisplay: 'none',
      idDisplay: 'none',
      authenNum: '',
      confirmNum: '0000',
    };
  },
  created() {
    this.start();
  },
  computed: {
    isUserPhoneNumValid() {
      return validatePhoneNum(this.userPhoneNum);
    },
    putPhoneNum() {
      if(this.isUserPhoneNumValid) {
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
    putAuthenBtn() {
      window.alert('인증 번호를 발송했습니다.');
      this.authenDisplay = 'block';
    },
    putIdBtn() {
      if (this.confirmNum === this.authenNum) {
        window.alert('인증에 성공했습니다.');
        this.idDisplay = 'block';
      } else {
        window.alert('인증 실패했습니다. 다시 시도해주세요.');
      }
    },
    start(){ // 1초에 한번씩 start 호출 
      this.polling = setInterval( () =>{ 
        this.timeCounter--  //1씩 감소 
        this.resTimeData = this.preTime() 
        if (this.timeCounter <= 0) this.timeStop() 
      },1000) 
    }, 
    // 시간 형식으로 변환 리턴 
    prettyTime() { 
      let time = this.timeCounter / 60 
      let minutes = parseInt(time) 
      let secondes = Math.round((time - minutes) * 60) 
      return this.pad(minutes, 2) + ":" + this.pad(secondes, 2) 
    }, 
    // 2자리수로 만들어줌 09,08... 
    pad(n, width) { 
      n = n + ''; 
      return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n 
    }, 
    timeStop() { 
      clearInterval(this.polling) 
    }, 
    // 재발행 
    smsReset() { 
      clearInterval(this.polling) 
      this.tcounter = 180 
      this.startTime() 
    },
  },
  beforeDestroy() { 
    clearInterval(this.polling) 
  }
};
</script>
