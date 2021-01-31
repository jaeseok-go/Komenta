<template>
  <b-container>
    <div class="modify-btn">
      <!-- 상태 변경하기 -->
      <button>상태 변경</button>
    </div>
    <h4>전체 회원</h4>
    <table>
      <tr>
        <td>회원번호</td>
        <td>아이디</td>
        <td>닉네임</td>
        <td>상태</td>
      </tr>
      <tr v-if="userList.length == 0">
        <td colspan="4">
          가입한 회원이 없습니다.
        </td>
      </tr>
      <!-- 페이징 처리...어케해... -->
      <tr v-for="(user, index) in userList" :key="index" v-else>
        <td>{{user.u_id}}</td>
        <td>{{user.u_email}}</td>
        <td>{{user.u_nickname}}</td>
        <td>
          <!-- 라벨버튼 모양으로 만들기 -->
          <div>{{user.u_state}}</div>
        </td>
      </tr>
    </table>
    <br>

    <h4>제한 회원</h4>
    <table>
      <tr>
        <td>회원번호</td>
        <td>아이디</td>
        <td>닉네임</td>
        <td>상태</td>
      </tr>
      <tr v-if="blockedUserList.length == 0">
        <td colspan="4">
          제한상태의 회원이 없습니다.
        </td>
      </tr>
      <!-- 페이징 처리...어케해... -->
      <tr v-for="(user, index) in blockedUserList" :key="index" v-else>
        <td>{{user.u_id}}</td>
        <td>{{user.u_email}}</td>
        <td>{{user.u_nickname}}</td>
        <td>
          <!-- 라벨버튼 모양으로 만들기 -->
          <div>{{user.u_state}}</div>
        </td>
      </tr>
    </table>
    <br>

    <h4>관리자</h4>
    <table>
      <tr>
        <td>회원번호</td>
        <td>아이디</td>
        <td>닉네임</td>
        <td>상태</td>
      </tr>
      <tr v-if="adminList.length == 0">
        <td colspan="4">
          관리자가 없습니다.
        </td>
      </tr>
      <!-- 페이징 처리...어케해... -->
      <tr v-for="(user, index) in adminList" :key="index" v-else>
        <td>{{user.u_id}}</td>
        <td>{{user.u_email}}</td>
        <td>{{user.u_nickname}}</td>
        <td>
          <!-- 라벨버튼 모양으로 만들기 -->
          <div>{{user.u_state}}</div>
        </td>
      </tr>
    </table>
  </b-container>
</template>

<script>
import { fetchAllUsers } from '@/api/user';
export default { 
  data() {
    return {
      userList:[],
      blockedUserList:[],
      adminList:[]
    }
  },
  created() {
    this.getUserList();
  },
  methods: {
    async getUserList() {
      const response = await fetchAllUsers();
      console.log("회원 목록 : ",response);

      for (let user = 0; user < response.data.length; user++) {
        // console.log("user[",user,"] : ",response.data[user]);
        if(response.data[user].u_is_admin) {
          response.data[user].u_state = '관리자';
        }else {
          if(response.data[user].u_is_blocked) {
            response.data[user].u_state = '제한회원';
          }else {
            response.data[user].u_state = '일반회원';
          }
        }
      }
      this.userList = response.data;

      //각 list에 저장
      for(let num = 0; num<response.data.length; num++) {
        if(response.data[num].u_is_admin){
          this.adminList = this.adminList.concat(response.data[num]);
        }else{
          if(response.data[num].u_is_blocked){
            this.blockedUserList = this.blockedUserList.concat(response.data[num]);
          }
        }
      }
    },
  },
}
</script>

<style>

</style>