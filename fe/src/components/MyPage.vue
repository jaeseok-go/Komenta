<template>
  <div>
    {{userNickName}}님의 정보<br>
    ID :{{userId}} <br> 
    Password : {{userPassword}} <br>
    Phone Number : {{userPhoneNumber}} <br>

    <button @click="modifyUser">정보 수정하기</button>
    <div :style="{display: modiForm}">
      <hr>
      <form @submit.prevent="modifyUserInfo">
        ID : <input type="text" v-model="userId"><br>
        PW : <input type="text" v-model="userPassword"><br>
        NickName : <input type="text" v-model="userNickName"><br>
        Phone Num : <input type="text" v-model="userPhoneNumber"><br>
        <button>수정하기</button>
      </form>
    </div>
  </div>
</template>

<script>
import store from '@/stores/modules/user'
import { updateMyInfo } from '@/api/user';
import jwtDecode from 'jwt-decode'

export default {
  data() {
    return {
      userId:'',
      userPassword:'',
      userNickName:'',
      userPhoneNumber:'',
      modiForm:'none'
    }
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      const response = jwtDecode(store.state.token);
      console.log("store userinfo : ",response);
      // const response = await this.$store.dispatch('LOGIN', {});
      // this.userId = response.u_email;
      // this.userPassword = response.u_pw;
      // this.userNickName = response.u_nickname;
      // this.userPhoneNumber = response.u_phone_number;
    },
    modifyUser() {
      this.modiForm = 'block';
    },
    async modifyUserInfo(){
      try {
        const userData = {
          u_email:this.userId,
          u_pw: this.userPassword,
          u_nickname : this.userNickName,
          u_phone_number : this.userPhoneNumber
        };

        const response = await updateMyInfo(userData);
        console.log("수정 modify",response);
      }catch(err) {
        console.log("수정 에러")
        console.log(err);
      }
        

    }
  },
}
</script>

<style>

</style>