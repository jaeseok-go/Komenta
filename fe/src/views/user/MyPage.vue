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
              <input type="text" v-model="userPassword"><br>
              <input type="text" v-model="confirmPW"><br>
              <input type="text" v-model="userNickName"><br>
              <input type="text" v-model="userPhoneNumber" disabled>
              <button>변경하기</button><br>
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
import Modal from '@/components/common/Modal'

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
    Modal
  },
  data() {
    return {
      uId:'',
      userId:'',
      userPassword:'',
      confirmPW:'',
      userNickName:'',
      userPhoneNumber:'',
      modiForm:'none',
      showModal:false,
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