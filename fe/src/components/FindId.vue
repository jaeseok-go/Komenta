<template>
  <b-container class="container-setting">

    <template v-if="showCertiForm">
      <phone-certification @checkCertification="checkCertification"></phone-certification>
    </template>
    <b-col class="showID" :style="{ display: idDisplay }">

      고객님의 아이디는
      <p class="idView" :style="{ display: 'inline-block' }">{{ userId }}</p>
      입니다.
       <div class="sign-setting" >
          <button class="btn btn-normal btn-small" @click="$emit('close')">로그인</button>
        </div>
    </b-col>
  </b-container>
</template>

<script>
import '@/css/index.css'
import PhoneCertification from './user/PhoneCertification.vue';
import store from '@/stores/modules/user';

export default {
  components: { PhoneCertification },
  data() {
    return {
      userId: '',
      idDisplay: 'none',
      showCertiForm: true,
    };
  },
  methods: {
    checkCertification() {
      this.idDisplay = 'block';
      this.showCertiForm = false;
      this.userId = store.state.userInfo.u_email;
    },
    closeModal(e) {
      console.log(e)
      this.$emit('closeModal')
    },
  },
};
</script>
