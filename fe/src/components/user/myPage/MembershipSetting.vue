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
          사용중인 멤버십이 없습니다.<br>
          <h6 class="goJoinMember" @click="goMembership">멤버십 가입하러 가기</h6>
        </td>
      </tr>
      <!-- DB에 저장된 값 불러오기 기능 -->
      <tr v-else>
        <td>{{membership.m_name}}</td>
        <td>{{membership.m_use_date}}</td>
        <td>{{membership.m_expire_date}}</td>
        <td @click="changeState"><p class="membership-state">{{membership.m_state}}</p></td>
      </tr>
    </table>
<!-- 
    1. 멤버십 명 <br>
    2. 사용기간<br>
    3. 결제 예정일<br>
    4. 상태(해지하기, 해지취소) 버튼<br>
-->
  </b-col>
</template>

<script>
import { membership,fetchMyInfo } from '@/api/user'
export default {
  data() {
    return {
      membership: {},
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
        showCancelButton: true,
        confirmButtonText: '해지',
        cancelButtonText: '취소',
      }).then((result) => {
        if(result.value) {
          this.$swal({
            text: '멤버십이 정상적으로 해지되었습니다.',
            icon: 'success',
            timer: 1300,
            showConfirmButton: false,
          })
          membership().then(()=>{
            // console.log(this.getUserInfo(this.userInfo.u_id),'???')
            this.$store.dispatch('FETCH_MEMBERSHIP',this.getUserInfo(this.userInfo.u_id))

          })
            window.location.reload();
        }
      })
      }
    },
    // 위코드 안되면 아래 코드로 바꿈
    // async changeState(){
    //     const result = window.confirm('멤버십을 해지하시겠습니까? \n 해지 시, 모든 VOD 시청 및 댓글 작성이 제한됩니다.')
    //     if(result) {
    //       console.log('해지 ㄱ')
    //       const response = await membership();
    //       console.log("해지 결과 : ",response)
    //       this.$store.dispatch('FETCH_MEMBERSHIP',await this.getUserInfo(this.userInfo.u_id))
    //       alert('멤버십이 정상적으로 해지되었습니다.')
    //       window.location.reload();
    //     }
    //   }
    // },
    async getMembershipInfo(){
      var moment = require('moment');
      console.log("멤버십 가입 여부 : ",moment(this.userInfo.u_expire_member).format('YYYY-MM-DD'))
      console.log('현재 시간 : ',moment(new Date()).format('YYYY-MM-DD'))
      if(this.userInfo.u_expire_member == "0000-00-00 00:00:00" || moment(this.userInfo.u_expire_member).format('YYYY-MM-DD') <= moment(new Date()).format('YYYY-MM-DD')) {
        console.log('가입한 멤버십이 없거나 만료되었습니다.');
      }else {
        // console.log("가입한 멤버십이 있습니다.")
        let m_use_date = moment(this.userInfo.u_expire_member);
        this.membership.m_name = "Komenta 프리미엄";
        this.membership.m_use_date = m_use_date.format('YYYY-MM-DD');
        this.membership.m_expire_date = m_use_date.add(1,'days').format('YYYY-MM-DD');
        this.membership.m_state = '해지하기';

        // console.log("멤버십 정보 : ",this.membership)
      }
    },
    goMembership(){
      this.$router.push(`/member/membership`);
    },
    async getUserInfo(u_id){
      const response = await fetchMyInfo(u_id);
      console.log("멤버십 정보 잘 바꼈니?",response)
      return response.data.u_expire_member;
    }
  },
}
</script>

<style>

</style>