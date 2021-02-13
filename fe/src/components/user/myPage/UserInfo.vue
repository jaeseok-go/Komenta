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
    
<!--
    1. 아이디|멤버십 이용 여부, 다음 결제일<br>
    2. 닉네임<br>
    3. 휴대폰 번호<br>
    4. 편집 버튼 클릭 시, 회원 정보 수정 폼(모달) <br>
    <br>
    회원정보 수정 <br>
    1. 프로필 사진 수정 <br>
    <hr>
    2. 아이디<br>
    3. 비밀번호<br>
    4. 비밀번호 확인<br>
    5. 닉네임<br>
    6. 휴대폰 번호 (변경하기 버튼)<br>
    7. 회원탈퇴(a태그)<br>
    8. 수정완료 (버튼)
-->
  </b-col>
</template>

<script>
// import { updateMyInfo } from '@/api/user';
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
      try {
        const userData = {
          u_id:this.uId,
          u_email:this.userId,
          u_pw: this.userPassword,
          u_nickname : this.userNickName,
          u_phone_number : this.userPhoneNumber,
          u_profile_pic:this.userProfile
        };
        console.log('유저데이터잘들어왔니',userData)
        console.log(this.userInfo)
        await this.$store.dispatch('MODIFY',userData)
        // console.log("수정 modify",response);
      }catch(err) {
        console.log("수정 에러")
        console.log(err);
      }
    }
  }
}
</script>

<style>
</style>