import Vue from 'vue'
import App from './App.vue'
import router from '@/routes/index';
import store from '@/stores/index';
import BootstrapVue from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faEye } from "@fortawesome/free-regular-svg-icons";

Vue.config.productionTip = false
Vue.use(BootstrapVue)

library.add(faEye)
Vue.component('font-awesome-icon', FontAwesomeIcon)

new Vue({
  router,
	store,
  render: h => h(App),
}).$mount('#app')
