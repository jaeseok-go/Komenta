<template>
  <b-container>
    <div class="modify-btn">
      <!-- 상태 변경하기 -->
      <button>상태 변경</button>
    </div>
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
  </b-container>
</template>

<script>
import { fetchAllUsers } from '@/api/user';
export default { 
  data() {
    return {
      userList:[],
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
    },
  },
}
</script>

<style>

</style>