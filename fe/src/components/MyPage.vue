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
import { updateMyInfo } from '@/api/user';
import { mapState } from 'vuex';


export default {
  data() {
    return {
      uId:'',
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
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
    })
  },
  methods: {
    getUserInfo() {
      this.uId = this.userInfo.u_id;
      this.userId = this.userInfo.u_email;
      this.userPassword = this.userInfo.u_pw;
      this.userNickName = this.userInfo.u_nickname;
      this.userPhoneNumber = this.userInfo.u_phone_number;
    },
    modifyUser() {
      this.modiForm = 'block';
    },
    async modifyUserInfo(){
      try {
        const userData = {
          u_id:this.uId,
          u_email:this.userId,
          u_pw: this.userPassword,
          u_nickname : this.userNickName,
          u_phone_number : this.userPhoneNumber
        };
        console.log('유저데이터잘들어왔니',userData)
        console.log(this.userInfo)
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