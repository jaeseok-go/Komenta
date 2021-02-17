<template>
  <div id="footer__color">
    <div class="margin_top_rem footer_title">
       (SSAFY 4기) 대전 2반 1팀 Komenta <br>
        <strong> <span class="footer__text">배상웅, 고재석, 김채린, 박수아, 정혜림</span> </strong> <br>
        본 프로젝트는 삼성전자 청년 SW 아카데미 교육의 일환으로써 어쩌구 저쩌구 무단 공유 및 배포를 엄격히 금합니다.
    </div>
      <div class="margin_top_rem footer_title">
      <router-link to="/member/membership"> <span class="footer_membership">세상에 없었던 인터렉티브 VOD 서비스  지금 멤버십을 가입해주세요</span> </router-link>
      </div>
      <div class="white--text footer_title">
        {{ new Date().getFullYear() }} — <strong>Komenta</strong>
      </div>
  </div>
</template>

<script>
export default {
  name: 'Footer',
  
  data() {
    return {
      
    };
  },
  mounted() {
    
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>

</style>