<template>
  <b-container class="container-setting">
    <phone-certification @checkCertification="checkCertification"></phone-certification>
    <b-col :style="{ display: idDisplay }">
      <hr />
      고객님의 아이디는
      <p class="idView" :style="{ display: 'inline-block' }">{{ userId }}</p>
      입니다.
    </b-col>
  </b-container>
</template>

<script>
import '@/css/index.css'
import PhoneCertification from './user/PhoneCertification.vue';

export default {
  components: { PhoneCertification },
  data() {
    return {
      userId: 'test@ssafy.com',
      idDisplay: 'none',
    };
  },
  methods: {
    checkCertification() {
      this.idDisplay = 'block';
    },
  },
};
</script>
