<template>
  <b-container>
    <b-col>
      <div>
        휴대폰 번호 : <input type="text">
        <button @click="putAuthenBtn" :disabled="putPhoneNum">휴대폰 인증</button><br>
        <div :style="{display:authenDisplay}">
          {{timeCount}} <input type="text" v-model="authenNum">
          <button @click="putIdBtn" :disabled="!putAuthenNum">인증하기</button>
        </div>
      </div>
    </b-col>
    <b-col :style="{display:idDisplay}">
      <hr>
        고객님의 아이디는 <p :style="{display:'inline-block'}">{{userId}}</p> 입니다.
    </b-col>
  </b-container>
</template>

<script>
import { validatePhoneNum } from '@/utils/validations';
export default {
  data() {
    return {
      userId:'test',
      timeCount:'300',
      authenDisplay:'none',
      idDisplay:'none',
      authenNum:'',
      confirmNum:'0000'
    }
  },
  computed: {
    putPhoneNum() {
      if(validatePhoneNum) {
        return true;
      }
      return false;
    },
    putAuthenNum() {
      if(this.authenNum.length >= 4) {
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
        this.idDisplay='block';
      }else {
        window.alert("인증 실패했습니다. 다시 시도해주세요.");
      }
    }
  },
}
</script>