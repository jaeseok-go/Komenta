<template>
  <b-col>
    <table>
      <tr>
        <td>회원번호</td>
        <td>아이디</td>
        <td>닉네임</td>
        <td>상태</td>
      </tr>
      <tr v-if="adminList.length == 0">
        <td colspan="4">
          제한 회원이 없습니다.
        </td>
      </tr>
      <tr v-for="(user,index) in paginatedData" :key="index" v-else>
        <td>{{user.u_id}}</td>
        <td>{{user.u_email}}</td>
        <td>{{user.u_nickname}}</td>
          <!-- 라벨버튼 모양으로 만들기 -->
        <td>
          <div :class="`btn-label ${user.u_state}`" @click="changeState(index)">
            {{user.u_state}}
          </div>
        </td>
      </tr>
    </table>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
      <span class="page-count">{{pageNum+1}}/{{pageCount}} 페이지 </span>
      <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">다음</button>
    </div>
  </b-col>
</template>

<script>
import {updateUserInfo} from '@/api/user';
export default {
  data() {
    return {
      pageNum:0,
    }
  },
  props: {
    adminList:{
      type:Array,
      required: false
    },
    pageSize: {
      type: Number,
      required: false,
      default: 5
    }
  },
  methods:{
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    async changeState(index){
      const idx = (index + ((this.pageNum*this.pageSize)));
      try{
        if(this.adminList[idx].u_state === '일반회원') {
          this.adminList[idx].u_state = '제한회원';
          const userdata = {
            u_email:this.adminList[idx].u_email,
            u_expire_member:this.adminList[idx].u_expire_member,
            u_id:this.adminList[idx].u_id,
            u_is_admin:false,
            u_is_blocked:1,
            u_nickname:this.adminList[idx].u_nickname,
            u_phone_number:this.adminList[idx].u_phone_number,
            u_profile_pic:this.adminList[idx].u_profile_pic,
            u_pw:this.adminList[idx].u_pw,
          };
          const response = await updateUserInfo(userdata);
          console.log("일반->제한: ",response);
          window.location.reload();
        }else if(this.adminList[idx].u_state === '제한회원'){
          this.adminList[idx].u_state = '일반회원';
          const userdata = {
            u_email:this.adminList[idx].u_email,
            u_expire_member:this.adminList[idx].u_expire_member,
            u_id:this.adminList[idx].u_id,
            u_is_admin:false,
            u_is_blocked:0,
            u_nickname:this.adminList[idx].u_nickname,
            u_phone_number:this.adminList[idx].u_phone_number,
            u_profile_pic:this.adminList[idx].u_profile_pic,
            u_pw:this.adminList[idx].u_pw,
          };
          const response = await updateUserInfo(userdata);
          console.log("제한->일반: ",response);
          window.location.reload();
        }
      }catch(err) {
        console.log("상태수정 에러 : ",err);
      }
    }
  },
  computed: {
    pageCount() {
      let listLeng = this.adminList.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.adminList.slice(start, end);
    }
  }
}
</script>

<style>
table {
  width: 100%;
  border-collapse: collapse;
}
table th {
  font-size: 1.2rem;
}
table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 1.1rem;
}

.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>