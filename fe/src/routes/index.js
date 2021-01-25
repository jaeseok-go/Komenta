import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/member/login',
      name: 'Login',
      component: () => import('@/views/user/Login.vue'),
    },
    {
      path: '/member/join',
      name: 'Signup',
      component: () => import('@/views/user/Signup.vue'),
    },
    {
      path: '/auth',
      name: 'Kakao',
      component: () => import('@/components/user/snsLogin/Kakao.vue'),
    },
    {
      path: '/findId',
      name: 'FindId',
      component: () => import('@/components/FindID.vue'),
    },
    {
    path:'/findpw',
    name:"FindPW",
    component: () => import('@/components/FindPW.vue')
    },
  ],
});
