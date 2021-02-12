<template>
  <div>
    <!-- 회원 정보 관리 -->
    <user-info @showUserInfoForm="showUserInfoForm"></user-info>
    <Modal v-if="showUserInfoModal">
        <div slot="header">
          <h3 class="findIdPw__title">내 정보 수정</h3>
          <span id="closeModalBtn" @click="closeUserInfoModal">
            <i class="fa fa-times" aria-hidden="true"></i>
          </span>
          <hr>                                                                                                      
        </div>
        <div slot="body">
          <form @submit.prevent="modifyUserInfo">
            <div class="profile-form">
              <div class="img">
                <v-img v-if="userProfilePic" :src="getUserProfile(userProfilePic)" width="90px"/>
                <!-- <img :src="require(`@/assets/images/${userProfilePic}`)" width="100px"> -->
                <!-- {{userProfilePic}} -->
              </div>
              <div class="file-input">
                <!-- <input type="file" :v-model="modifyUserInfo" accept="image/gif,image/jpeg,image/png" /> -->
                <!-- <button>프로필 사진 변경</button> -->
                <input id="profile" ref="imageInput" type="file" hidden @change="onChangeImages()" required accept="image/jpeg,image/jpg">
                <v-btn type="button" @click="onClickImageUpload">이미지 업로드</v-btn>
                {{userProfilePic}}
              </div>
              
              <!-- <img :src="require(`../../assets/images/${userProfilePic}`)"> -->
            </div>
            <hr>
            <div class="modi-form form-text">
              아이디 : <br>
              비밀번호 : <br>
              비밀번호 확인 : <br>
              닉네임 : <br>
              휴대폰 번호 : <br>
            </div>
            <div class="modi-form">
              <input type="text" v-model="userId" disabled><br>
              <!-- 영문, 숫자, 특수문자 포함 8~15자 이내 -->
              <input type="password" v-model="userPassword"><br>
              <p class="warning-form warning-signup">
                <span class="warning-text" v-if="!isPasswordValid">
                  password를 8자 이상 입력하세요.
                </span>
              </p>
              <!-- 비밀번호 일치 여부 확인 -->
              <input type="password" v-model="confirmPW"><br>
              <p class="warning-form warning-signup">
                <span class="warning-text" v-if="!isPasswordConfirmValid">
                  password가 일치하지 않습니다.
                </span>
              </p>
              <!-- 닉네임 중복 여부 확인 -->
              <input type="text" v-model="userNickName">
              <!-- 중복 닉네임이 아닐 때 표출 -->
              <p class="icon-inline-block" v-show="!isUserNickNameEmpty && !isNickNameDuplicaionCheck">
                <font-awesome-icon class="fw-icon fwCheck" :icon="['fas', 'check' ]" />
              </p>
              <!-- 중복 닉네임일 때 표출 -->
              <p class="icon-inline-block" v-show="!isUserNickNameEmpty && isNickNameDuplicaionCheck">
                <font-awesome-icon class="fw-icon fwTimes" :icon="['fas', 'times' ]" />
              </p>
              <br>

              <!-- 변경하기 클릭 시,  -->
              <input type="text" v-model="userPhoneNumber" v-if="phoneNumForm" disabled>
              <button @click="changePhoneNumber" v-if="phoneNumForm">변경하기</button><br v-if="phoneNumForm">
              <phone-certification class="modify-authentic" @click="changePhoneNumber" v-if="authPhoneNumForm"></phone-certification>
            </div>
            <div>
              <p @click="unSubscribe">회원탈퇴</p>
              <button>수정완료</button>
            </div>
          </form>
        </div>
    </Modal>

    <div v-if="userIsAdmin == 1">
      <admin-page></admin-page>
    </div>
    <div v-else>
      <!-- 내가 단 댓글 관리 -->
      <my-comment></my-comment>

      <!-- 시청 VOD 관리 -->
      <watched-vod :getUserId="uId"></watched-vod>

      <!-- 팔로우 관리 -->
      <follow :getUserId="uId"></follow>

      <!-- 언팔로우 관리 -->
      <un-follow></un-follow>

      <!-- 멤버십 관리 -->
      <membership-setting :userInfo="this.userInfo"></membership-setting>
    </div>
  </div>
</template>

<script>
import UserInfo from '@/components/user/myPage/UserInfo'
import MyComment from '@/components/user/myPage/MyComment';
import WatchedVod from '@/components/user/myPage/WatchedVOD'
import Follow from '@/components/user/myPage/Follow.vue';
import UnFollow from '@/components/user/myPage/UnFollow.vue';
import MembershipSetting from '@/components/user/myPage/MembershipSetting.vue';
import AdminPage from '@/components/user/myPage/adminPage.vue';
import Modal from '@/components/common/Modal';
import PhoneCertification from '@/components/user/PhoneCertification.vue';

import { validatePassword } from '@/utils/validations';
import { deleteMyInfo, uploadProfile } from '@/api/user';
import { mapState } from 'vuex';


export default {
  components:{
    UserInfo,
    MyComment,
    WatchedVod,
    Follow,
    UnFollow,
    MembershipSetting,
    AdminPage,
    Modal,
    PhoneCertification,
  },
  data() {
    return {
      uId:'',
      userId:'',
      userPassword:'',
      confirmPW:'',
      userNickName:'',
      userPhoneNumber:'',
      userProfilePic:'',
      profilePicFile:'',
      userIsAdmin:0,
      modiForm:'none',
      showUserInfoModal:false,
      phoneNumForm:true,
      authPhoneNumForm: false,

    }
  },
  created() {
    this.getUserInfo();
  },
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo,
    }),
    isUserNickNameEmpty() {
      if(!this.userNickName || (this.userNickName == this.userInfo.u_nickname)) {
        return true;
      }
      return false;
    },
    isNickNameDuplicaionCheck() { //async
      // const result = await userNickNameChk(this.username)
      const result = 'true';
      console.log(result)
      if(result === 'true') {
        // console.log('DUPLICATED')
        return true;
      }
      return false;
    },
    isPasswordValid() {
      if (!this.userPassword) {
        return true;
      }
      return validatePassword(this.userPassword);
    },
    isPasswordConfirmValid() {
      if (!this.confirmPW) {
        return true;
      } else if (this.userPassword != this.confirmPW) {
        return false;
      }
      return true;
    },

  },
  methods: {
    getUserProfile(profile){
      const picName = profile.split('.')
      return `${process.env.VUE_APP_PICTURE}profile/${picName[0]}`
    },
    closeUserInfoModal() {
      this.showUserInfoModal = false;
      console.log('들어와라,,')
    },
    showUserInfoForm() {
      this.showUserInfoModal = true;
    },
    getUserInfo() {
      this.uId = this.userInfo.u_id;
      this.userId = this.userInfo.u_email;
      // this.userPassword = this.userInfo.u_pw;
      this.userNickName = this.userInfo.u_nickname;
      this.userPhoneNumber = this.userInfo.u_phone_number;
      this.userProfilePic = this.userInfo.u_profile_pic;
      this.userIsAdmin = this.userInfo.is_admin;
      console.log(this.userInfo,'바꼈니?')
    },
    modifyUser() {
      this.modiForm = 'block';
    },
    changePhoneNumber() {
      this.phoneNumForm =false;
      this.authPhoneNumForm = true;
    },
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    onChangeImages() {
      this.userProfilePic = this.$refs.imageInput.files[0].name;
      this.profilePicFile = this.$refs.imageInput.files[0];
      console.log("user pic : ",this.userProfilePic)
      console.log("user pic Info : ",this.profilePicFile)
    },
    async modifyUserInfo(){
      if(!this.userPassword) {
        alert("비밀번호를 입력하세요.")
        return;
      }
      if(!this.confirmPW) {
        alert("비밀번호를 확인하세요.")
        return;
      }
      if(!this.userNickName) {
        alert("닉네임을 설정해주세요.")
        return;
      }
      if(!this.userPhoneNumber) {
        alert("휴대폰 번호를 입력하세요")
        return;
      }
      try {
        let profilePic = new FormData();
        let pic = this.userProfilePic.split('.');
        // console.log(pic,'잘렸니?')
        profilePic.append("profile", this.profilePicFile, String(pic[0]+'.jpg'))

        // controller 수정!!! 아마 경로 잘못 되어 있어서? Profile 아니고 User
        await uploadProfile(profilePic)
        .then((response) => {
          console.log("프로필 사진 잘 들어감",response.data);
          this.closeUserInfoModal();
          
          // window.location.reload();
        })
        .catch((err) => {
          console.log(err);
          alert("프로필 사진을 업로드 하던 중 오류가 발생했습니다.");
          return false;
        });
        const userData = {
          u_id:this.uId,
          u_email:this.userId,
          u_pw: this.userPassword,
          u_nickname : this.userNickName,
          u_phone_number : this.userPhoneNumber,
          u_profile_pic : this.userProfilePic
        };
        console.log('유저데이터잘들어왔니',userData)
        await this.$store.dispatch('MODIFY',userData)
        console.log(this.userInfo)
        this.userId = this.userInfo.u_email
        this.userNickName = this.userInfo.u_nickname
        this.u_phone_number = this.userInfo.u_phone_number
        

      }catch(err) {
        console.log("수정 에러")
        console.log(err);
      }
    },
    async unSubscribe(){
      const userConfirm = confirm("회원 탈퇴 시, 모든 데이터는 복구가 불가능합니다.\n 탈퇴를 계속 진행하시겠습니까?");
      console.log(userConfirm);

      try{
        if(userConfirm) {
          console.log('회원탈퇴 진행',this.uId);
          const response = await deleteMyInfo(this.uId);
          console.log("회원탈퇴...",response);
          alert("Komenta를 이용해주셔서 감사합니다. 정상적으로 탈퇴처리 되었습니다.");
        }
        this.$route.push({name:'Login'});
      }catch(err){
        console.log(err)
      }
    },
  },
}
</script>

<style>
  h4 {
    font-weight: 700;
    margin-top: 2rem;
  }
  input {
    border:1px solid black;
  }
  .modal-header {
    border-bottom: none;
  }
  .modi-form {
    display: inline-block;
  }
  .form-text {
    text-align: right;
  }

  .profile-form .img {
    /* background-color: blueviolet; */
    border: 1px solid black;
    width: 100px;
    height: 100px;
  }

  .profile-form div {
    display: inline-block;
    text-align: center;
  }

  .profile-form input[type="file"] {
    display: block;
    margin-bottom: 1rem;
  }

  .profile-form .file-input{
    position: relative;
    bottom: 40px;
    margin-left: 1rem;
  }

  .profile-form .v-btn {
    border: 1px solid black;
    padding: 0.1rem 5.7em;
  }

</style>