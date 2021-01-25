import Vue from 'vue'
import App from './App.vue'
import router from '@/routes/index';
import store from '@/stores/index';
import BootstrapVue from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faEye, faEyeSlash, faEnvelope, faCheckCircle as farCheckC } from "@fortawesome/free-regular-svg-icons";
import { faCheckCircle as fasCheckC, faCheck, faTimes } from "@fortawesome/free-solid-svg-icons";
// google 로그인
import '@/plugins/firebase';

import '@/css/index.css'

Vue.config.productionTip = false;
Vue.use(BootstrapVue);

library.add(fasCheckC, faCheck, faTimes) //fas lib
library.add(faEye, faEyeSlash, faEnvelope, farCheckC) //far lib
Vue.component('font-awesome-icon', FontAwesomeIcon)

// kakao로그인
window.Kakao.init(process.env.VUE_APP_KAKAO_JS_KEY);

new Vue({
  router,
	store,
  render: h => h(App),
}).$mount('#app')
