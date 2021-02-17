<template>
  <b-col>
    <h4>회원 정보</h4>
    <hr>
    <div class="modify-btn">
      <button @click="showModalForm">내 정보 수정</button>
    </div>
    <span class="userInfo-form">
      <img :src="getUserProfile(userProfile)"  width="100px" alt="">
      <div class="displayinline showUserInfo-form">
        아이디 : {{userId}} <br>
        닉네임 : {{userNickName}}<br>
        휴대폰 번호 : {{userPhoneNumber}}
      </div>
      <hr>
    </span>
  </b-col>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {
      uId:'',
      userId:'',
      userPassword:'',
      userNickName:'',
      userPhoneNumber:'',
      userProfile:'',
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
    showModalForm() {
      this.$emit('showUserInfoForm')
    },
    getUserInfo() {
      this.uId = this.userInfo.u_id;
      this.userId = this.userInfo.u_email;
      this.userPassword = this.userInfo.u_pw;
      this.userNickName = this.userInfo.u_nickname;
      this.userPhoneNumber = this.userInfo.u_phone_number;
      this.userProfile = this.userInfo.u_profile_pic;
    },
    getUserProfile(profile){
      const path = profile.split('.')
      return `${process.env.VUE_APP_PICTURE}profile/${path[0]}`
    },
    modifyUser() {
      this.modiForm = 'block';
    },
    async modifyUserInfo(){
        const userData = {
          u_id:this.uId,
          u_email:this.userId,
          u_pw: this.userPassword,
          u_nickname : this.userNickName,
          u_phone_number : this.userPhoneNumber,
          u_profile_pic:this.userProfile
        };
        this.$swal({
        text: '수정 완료했습니다.',
        icon: 'success',
        timer: 1300,
        showConfirmButton: false,
      })
        // console.log('유저데이터잘들어왔니',userData)
        // console.log(this.userInfo)
        await this.$store.dispatch('MODIFY',userData)

      // catch(err) {
      //   console.log("수정 에러")
      //   console.log(err);
      // }
    }
  }
}
</script>

<style>
</style>