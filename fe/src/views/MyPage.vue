<template>
  <b-container>
    <b-col>
      <h4>회원 정보</h4>
      1. 아이디  |   멤버십 이용 여부, 다음 결제일<br>
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
    </b-col>
    <b-col>
      <h4>내가 단 댓글</h4>
      1. 컨텐츠 명(제목+회차)<br>
      2. 댓글 내용(a태그 -> 해당 회차 페이지로 이동)<br>
      3. 좋아요 수<br>
      4. 대댓글 수<br>
      5. 댓글 쓴 날짜<br>
      6. 편집 버튼 클릭 시, 댓글 수정, 삭제 가능<br>
    </b-col>
    <b-col>
      <h4>시청 VOD 관리</h4>
      1. 이미지(포스터...?)<br>
      2. VOD 명 + 회차 정보<br>
      3. 최근 시청 날짜<br>
      4. 편집 버튼 클릭 시, 시청 기록 삭제 가능<br>
    </b-col>
    <b-col>
      <h4>관심 리스트 관리(레이아웃 배치 생각 중)</h4>
      1. 리스트 명<br>
      2. 리스트 만든 유저 닉네임<br>
      3. 편집 버튼 클릭 시, 리스트 삭제 기능<br>
    </b-col>
    <b-col>
      <h4>팔로우 관리</h4>
      1. 나를 팔로우<br>
        1-0. 검색창<br>
        1-1. 팔로우 유저의 프로필<br>
        1-2. 팔로우 유저의 닉네임<br>
        1-3. 팔로우 버튼, 차단 버튼<br>
      2. 내가 팔로우<br>
        2-0. 검색창<br>
        2-1. 팔로우 유저의 프로필<br>
        2-2. 팔로우 유저의 닉네임<br>
        2-3. 삭제, 차단 버튼<br>
    </b-col>
    <b-col>
      <h4>언팔로우 관리(차단)</h4>
      0. 검색창 <br>
      1. 언팔로우 유저의 프로필<br>
      2. 언팔로우 유저의 닉네임<br>
      3. 팔로우, 삭제 버튼<br>
    </b-col>
    <b-col>
      <h4>멤버십 관리</h4>
      1. 멤버십 명 <br>
      2. 사용기간<br>
      3. 결제 예정일<br>
      4. 상태(해지하기, 해지취소) 버튼<br>
    </b-col>
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
  h4 {
    font-weight: 700;
    margin-top: 2rem;
  }
</style>