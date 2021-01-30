<template>
  <b-container>
    <user-info v-on:showModalForm="showModalForm"></user-info>
    <Modal v-if="showModal" @click="$emit('close')">
        <div slot="header">
          <h3 class="findIdPw__title">내 정보 수정</h3>
          <span id="closeModalBtn">
            <i class="fa fa-times" aria-hidden="true" @click="showModal = false"></i>
          </span>
          <hr>                                                                                                      
        </div>
        <div slot="body">
          <form @submit.prevent="modifyUserInfo">
            <div>
              <!-- <img src="@/imgs/{{userProfilePic}}"> -->
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
              <input type="text" v-model="userPassword"><br>
              <!-- 비밀번호 일치 여부 확인 -->
              <input type="text" v-model="confirmPW"><br>
              <p class="warning-form warning-signup">
                <span class="warning-text" v-if="!isPasswordValid">
                  password를 8자 이상 입력하세요.
                </span>
              </p>
              <!-- 닉네임 중복 여부 확인 -->
              <input type="text" v-model="userNickName">
              <p class="warning-form warning-signup">
                <span class="warning-text" v-if="!isPasswordConfirmValid">
                  password가 일치하지 않습니다.
                </span>
              </p>
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
              <a href="#">회원탈퇴</a>
              <button>수정완료</button>
            </div>
          </form>
        </div>
    </Modal>
    <my-comment></my-comment>
    <watched-vod></watched-vod>
    <interest-play-list></interest-play-list>
    <follow></follow>
    <un-follow></un-follow>
    <membership-setting></membership-setting>
    <!-- {{userNickName}}님의 정보<br>
    ID :{{userId}} <br> 
    Password : {{userPassword}} <br>
    Phone Number : {{userPhoneNumber}} <br> -->

    <!-- <button @click="modifyUser">정보 수정하기</button>
    <div :style="{display: modiForm}">
      <hr>
      <form @submit.prevent="modifyUserInfo">
        ID : <input type="text" v-model="userId"><br>
        PW : <input type="text" v-model="userPassword"><br>
        NickName : <input type="text" v-model="userNickName"><br>
        Phone Num : <input type="text" v-model="userPhoneNumber"><br>
        <button>수정하기</button>
      </form>
    </div> -->
  </b-container>
</template>

<script>
import UserInfo from '@/components/user/myPage/UserInfo'
import MyComment from '@/components/user/myPage/MyComment';
import WatchedVod from '@/components/user/myPage/WatchedVOD'
import InterestPlayList from '@/components/user/myPage/InterestPlayList.vue';
import Follow from '@/components/user/myPage/Follow.vue';
import UnFollow from '@/components/user/myPage/UnFollow.vue';
import MembershipSetting from '@/components/user/myPage/MembershipSetting.vue';
import Modal from '@/components/common/Modal';
import PhoneCertification from '@/components/user/PhoneCertification.vue';

import { updateMyInfo } from '@/api/user';
import { mapState } from 'vuex';

export default {
  components:{
    UserInfo,
    MyComment,
    WatchedVod,
    InterestPlayList,
    Follow,
    UnFollow,
    MembershipSetting,
    Modal,
    PhoneCertification
  },
  data() {
    return {
      uId:'',
      userId:'',
      userPassword:'',
      confirmPW:'',
      userNickName:'',
      userPhoneNumber:'',
      userProfilePic:'3.png',
      modiForm:'none',
      showModal:false,
      phoneNumForm:true,
      authPhoneNumForm: false
    }
  },
  created() {
    this.getUserInfo();
  },
  computed: {
    ...mapState({
      userInfo: state => state.user.userInfo
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
  },
  methods: {
    closeModal() {
      this.showModal = false
      console.log('들어와라,,')
    },
    showModalForm() {
      this.showModal = true
    },
    getUserInfo() {
      this.uId = this.userInfo.u_id;
      this.userId = this.userInfo.u_email;
      this.userPassword = this.userInfo.u_pw;
      this.userNickName = this.userInfo.u_nickname;
      this.userPhoneNumber = this.userInfo.u_phone_number;
      // this.userProfilePic = this.userInfo.u_profile_pic;
      console.log(this.userInfo,'바꼈니?')
    },
    modifyUser() {
      this.modiForm = 'block';
    },
    changePhoneNumber() {
      this.phoneNumForm =false;
      this.authPhoneNumForm = true;
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
        this.getUserInfo();
      }catch(err) {
        console.log("수정 에러")
        console.log(err);
      }
        

    }
  },
}
</script>

<style>
  h4 {
    font-weight: 700;
    margin-top: 2rem;
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
</style>