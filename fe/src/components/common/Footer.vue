<template>
  <div>
    <v-footer
    padless
   
  >
    <v-card
      flat
      tile
       id="footer__color"
      class="indigo lighten-1 white--text text-center" width="100%"
    >

      <div class="margin_top_rem">
       <span>세상에 없었던 인터렉티브 VOD 서비스 </span> 지금 멤버십을 가입해주세요 <br>
        <router-link to="/member/membership"><span>멤버십가입하러가기</span></router-link>
      </div>
       <v-card-text class="margin_top_rem">
         (SSAFY 4기) 대전 2반 1팀 - Komenta <strong>배상웅, 고재석, 김채린, 박수아, 정혜림 </strong> <br>
         <strong>본 프로젝트는 삼성전자 청년 SW 아카데미 교육의 일환으로써 어쩌구 저쩌구 무단 공유 및 배포를 엄격히 금합니다. </strong></v-card-text>

      <v-card-text class="white--text">
        {{ new Date().getFullYear() }} — <strong>Komenta</strong>
      </v-card-text>
    </v-card>
  </v-footer>
  
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