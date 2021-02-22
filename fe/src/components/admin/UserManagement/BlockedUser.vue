<template>
  <b-col class="userInfo-table-form">
    <table class="table-border-style">
      <tr>
        <td>회원번호</td>
        <td>아이디</td>
        <td>닉네임</td>
        <td>상태</td>
      </tr>
      <tr v-if="blockedList.length == 0">
        <td colspan="4">
          제한 회원이 없습니다.
        </td>
      </tr>
      <tr v-for="(user,index) in paginatedData" :key="index" v-else>
        <td>{{user.u_id}}</td>
        <td>{{user.u_email}}</td>
        <td>{{user.u_nickname}}</td>
        <td>
          <div :class="`btn-label ${user.u_state}`" @click="changeState(index)">
            {{user.u_state}}
          </div>
        </td>
      </tr>
    </table>
    <div class="admin-btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-left']"/>
      </button>
      <span class="page-count">{{pageNum+1}}/{{pageCount}} 페이지 </span>
      <button :disabled="pageNum >= pageCount-1" @click="nextPage" class="page-btn">
        <font-awesome-icon :icon="['fas', 'angle-right']"/>
      </button>
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
    blockedList:{
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
        if(this.blockedList[idx].u_state === '일반회원') {
          this.blockedList[idx].u_state = '제한회원';
          const userdata = {
            u_email:this.blockedList[idx].u_email,
            u_expire_member:this.blockedList[idx].u_expire_member,
            u_id:this.blockedList[idx].u_id,
            u_is_admin:false,
            u_is_blocked:1,
            u_nickname:this.blockedList[idx].u_nickname,
            u_phone_number:this.blockedList[idx].u_phone_number,
            u_profile_pic:this.blockedList[idx].u_profile_pic,
            u_pw:this.blockedList[idx].u_pw,
          };
          await updateUserInfo(userdata);
          window.location.reload();
        }else if(this.blockedList[idx].u_state === '제한회원'){
          this.blockedList[idx].u_state = '일반회원';
          const userdata = {
            u_email:this.blockedList[idx].u_email,
            u_expire_member:this.blockedList[idx].u_expire_member,
            u_id:this.blockedList[idx].u_id,
            u_is_admin:false,
            u_is_blocked:0,
            u_nickname:this.blockedList[idx].u_nickname,
            u_phone_number:this.blockedList[idx].u_phone_number,
            u_profile_pic:this.blockedList[idx].u_profile_pic,
            u_pw:this.blockedList[idx].u_pw,
          };
          await updateUserInfo(userdata);
          window.location.reload();
        }
      }catch(err) {
        console.log(err);
      }
    }
  },
  computed: {
    pageCount() {
      let listLeng = this.blockedList.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);

      if(listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;

      return this.blockedList.slice(start, end);
    }
  }
}
</script>

<style>
</style>