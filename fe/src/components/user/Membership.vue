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
      <p>※ 멤버십 가입 시 유의사항 ※</p>
      <li>Komenta 서비스는 비영리 목적으로 제작된 홈페이지로써 사용자에게 금전적 요구를 하지 않습니다.</li>
      <li>Komenta는 반응형 웹이 적용되지 않았기 때문에 PC 환경에서 사용하길 권장합니다.</li>
      <li>Komenta 서비스는 모든 사용자에게 시청 연령대를 준수하길 권장하고 있습니다.</li>
      <li>타인에게 불쾌감을 줄 수 있는 댓글 작성 시, 임의적으로 댓글 기능이 제한될 수 있습니다.</li>
      <li>이용권은 1달 주기로 사용자가 직접 갱신해야 합니다(헷)</li>
      <li>과도한 접속량으로 인해 해당 서비스가 원활하게 제공되지 않을 수 있습니다.</li>
      <li>본 서비스는 설문조사를 위해 본래의 의도와 다르게 멤버십 없이도 해당 기능을 이용할 수 있습니다.</li>
      <li>VOD 화질은 영상마다 제각각 다를 수 있음을 양해부탁드립니다.</li>
      <li>VOD 저작권 문제로 인해 영상의 길이가 길지 않음을 양해 부탁드립니다.</li>
      <li>서비스 이용 권장사양은 PC환경의 Chrome에 최적화 되어있습니다.</li>
      <li>서비스 이용 도중 문의 사항이 생길 경우, "배상웅 팀장님"께 문의 바랍니다.</li>
      <li>이제 더 이상 할 말이 없습니다.</li>
      <li>열심히 만들었으니까 좋게좋게 봐주십셔</li>
      <li>자 이제 설문조사 고고</li>
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
      if(this.userInfo.u_expire_member === "0000-00-00 00:00:00" || moment(this.userInfo.u_expire_member).format('YYYY-MM-DD') <= moment(new Date()).format('YYYY-MM-DD')) {
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
