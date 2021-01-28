import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
  mode: 'history',
  // base: process.env.VUE_APP_URL,
  routes: [
    // Main은 나중에 바꿀예정
    {
      path: '/',
      name: 'Main',
      component: () => import('@/views/user/Login.vue'),
    },
    {
      path: '/member/login',
      name: 'Login',
      component: () => import('@/views/user/Login.vue'),
      children: [
        {
          path: 'findId',
          name: 'FindId',
          component: () => import('@/components/FindId.vue'),
        },
        {
          path: 'findPw',
          name: "FindPw",
          component: () => import('@/components/FindPw.vue')
        },
      ]
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
      path: '/member/certification',
      name: "PhoneCertification",
      component: () => import('@/components/user/PhoneCertification.vue'),
    },
    {
      path: '/member/membership',
      name: "Membership",
      component: () => import('@/components/Membership.vue'),
    },
    {
      path: '/admin/admintest',
      name: 'AdminTest',
      component: () => import('@/components/admin/AdminTest.vue'),
    },
    {
      path: '/admin/test',
      name: 'Test',
      component: () => import('@/components/admin/Test.vue'),
    },
  ],
});
