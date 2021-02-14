<template>
  <b-col class="membership-form">
    <div>
      <h4>멤버십 가입</h4>
    </div>
    <!-- 첫 번째 카드 -->
    <div class="membership-form-card">
      <!-- 멤버십 가입 혜택 설명 -->
      <div class="ms-best">
        <p>Pro</p>
      </div>
      <div>
          <p class="ms-title">Komenta 프리미엄</p>
          <p class="ms-title-sub">
            매번 새로운 VOD를 제일 먼저 볼 수 있는 기회!<br>
            돈은 필요 없어요! 1분짜리 설문조사 완료하고, Komenta 100% 즐기기!
          </p>
      </div>
      <!-- 멤버십 세부 설명 -->
      <div class="ms-content">
        <!-- 세부 설명 -->
        <li>VOD 스트리밍 | VOD 댓글 작성 및 보기</li>
        <li>자신만의 Feed | 타유저의 Play List 보기</li>
        <div class="ms-content-img">
          <img src="@/assets/images/membership.png" width="70%">
        </div>
      </div>
      <div class="ms-btn">
        <!-- 등록하기 버튼(구글 폼으로 이동) -->
        <button @click="signMembership">가입하기</button>
      </div>
    </div>
    <div class="membership-warning">
      <li>멤버십 가입 시 유의사항</li>
    </div>
  </b-col>
</template>

<script>
import { mapState } from 'vuex'
import { fetchMyInfo,membership } from '@/api/user'
export default {
  computed: {
    ...mapState({
      userInfo: (state) => state.user.userInfo
    })
  },
  methods: {
    async signMembership(){
      console.log('회원 멤버십 정보 : ',this.userInfo.u_expire_member)
      var moment = require('moment');
      if(this.userInfo.u_expire_member === "0000-00-00 00:00:00" || this.userInfo.u_expire_member < moment(new Date()).format('YYYY-MM-DD HH:MM:SS')) {
        const result = await membership();
        console.log('그래서 가입이 된겨? ',result)
        // this.userInfo.u_expire_member = this.getUserInfo(this.userInfo.u_id);
        this.$store.dispatch('FETCH_MEMBERSHIP',await this.getUserInfo(this.userInfo.u_id))
        window.open('https://forms.gle/WPi6iM7Q5Doyyx2y5');
        this.$router.push({name:'MyPage'});
      }else {
        alert('이미 멤버십에 가입하신 회원입니다!')
      }
    },
    async getUserInfo(uid) {
      const response = await fetchMyInfo(uid);
      console.log("멤버십 정보 잘 바꼈니?",response)
      return response.data.u_expire_member;
    }
  },
};
</script>
