<template>
  <b-col>
    <h4>멤버십 관리</h4>
    <hr>
    <table>
      <tr>
        <td>멤버십</td>
        <td>사용 기간</td>
        <td>결제 예정일</td>
        <td>상태</td>
      </tr>
      <tr v-if="!membership">
        <td colspan="5">
          사용중인 멤버십이 없습니다.<br>
          <a href="#"><h6>멤버십 가입하러 가기</h6></a>
        </td>
      </tr>
      <!-- DB에 저장된 값 불러오기 기능 -->
      <tr v-else>
        <td>{{membership.m_name}}</td>
        <td>{{membership.m_use_date}}</td>
        <td>{{membership.m_expire_date}}</td>
        <td @click="changeState">{{membership.m_state}}</td>
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
export default {
  data() {
    return {
      membership: {
        m_name:'1달 정기권',
        m_use_date:'2021-01-20',
        m_expire_date:'2021-01-21',
        m_state:'해지하기'
      },
      stateBtnColor:'red'
    }
  },
  methods: {
    changeState(){
      if(this.membership.m_state == '해지하기') {
        this.membership.m_state = '해지취소';
        //멤버십 해지하는 기능
      }else {
        this.membership.m_state = '해지하기';
        //멤버십 해지 취소하는 기능
      }
    }
  },
}
</script>

<style>

</style>