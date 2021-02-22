<template>
  <b-col>
    <h4>멤버십 관리</h4>
    <hr>
    <table class="table-border-style">
      <tr>
        <td>멤버십</td>
        <td>사용 기간</td>
        <td>결제 예정일</td>
        <td>상태</td>
      </tr>
      <tr v-if="this.membership.m_name == undefined">
        <td colspan="5">
          {{NonMemText}}<br>
          <h6 class="goJoinMember" @click="goMembership">멤버십 가입하러 가기</h6>
        </td>
      </tr>
      <tr v-else>
        <td>{{membership.m_name}}</td>
        <td>{{membership.m_use_date}}</td>
        <td>{{membership.m_expire_date}}</td>
        <td @click="changeState"><p class="membership-state">{{membership.m_state}}</p></td>
      </tr>
    </table>
  </b-col>
</template>

<script>
import { membership,fetchMyInfo } from '@/api/user'
export default {
  data() {
    return {
      membership: {},
      NonMemText: '사용중인 멤버십이 없습니다.'
    }
  },
  props:{
    userInfo:Object,
  },
  created(){
    this.getMembershipInfo();
  },
  methods: {
    changeState(){
      if(this.membership.m_state == '해지하기') {
        //멤버십 해지하는 기능
        this.$swal({
          title:'멤버십을 해지하시겠습니까?',
          text:'해지 시, 모든 VOD 시청 및 댓글 작성이 제한됩니다.',
           customClass: {
          container: 'swal2-container'
        },
        icon:'warning',
          showCancelButton: true,
          confirmButtonText: '해지',
        confirmButtonColor: "#fc3c44",
          cancelButtonText: '취소',
        }).then((result) => {
          if(result.value) {
            this.$swal({
              text: '멤버십이 정상적으로 해지되었습니다.',
              customClass: {
          container: 'swal2-container'
        },
              icon: 'success',
              timer: 1300,
              showConfirmButton: false,
            })
            membership().then(()=>{
              this.$store.dispatch('FETCH_MEMBERSHIP', this.getUserInfo(this.userInfo.u_id))

            })
            window.location.reload();
          }
        })
      }
    },
    async getMembershipInfo(){
      var moment = require('moment');
      if(this.userInfo.u_expire_member == "0000-00-00 00:00:00" || moment(this.userInfo.u_expire_member).format('YYYY-MM-DD') <= moment(new Date()).format('YYYY-MM-DD')) {
        this.NonMemText = '사용중인 멤버십이 없습니다.';
      }else {
        let m_use_date = moment(this.userInfo.u_expire_member);
        this.membership.m_name = "Komenta 프리미엄";
        this.membership.m_use_date = m_use_date.format('YYYY-MM-DD');
        this.membership.m_expire_date = m_use_date.add(1,'days').format('YYYY-MM-DD');
        this.membership.m_state = '해지하기';

      }
    },
    goMembership(){
      this.$router.push(`/member/membership`);
    },
    async getUserInfo(u_id){
      const response = await fetchMyInfo(u_id);
      return response.data.u_expire_member;
    }
  },
}
</script>

<style>

</style>