import Vue from 'vue';
import Router from 'vue-router';
// import { loadView,loadComponent } from '@/utils/loadPage'

Vue.use(Router);

export default new Router({
  mode: 'history',
  // base: process.env.VUE_APP_URL,
  routes: [
    // Main은 나중에 바꿀예정
    {
      path: '/',
      name: 'Main',
      // component: loadComponent('Main','Main'),
      component: () => import('@/components/Main/Main.vue')
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
      component: () => import('@/components/user/Membership.vue'),
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
    {
      path: '/member/myPage',
      name: 'MyPage',
      component: () => import('@/views/user/MyPage.vue'),
      children: [
        {
          path: 'userManage',
          name: 'UserManage',
          component: () => import('@/components/admin/UserManagement.vue'),
        },
        {
          path: 'vodManage',
          name: "VodManage",
          component: () => import('@/components/admin/VodManagement.vue')
        },
      ]
    },
    {
      path: '/MyFeed',
      name: 'MyFeed',
      component: () => import('@/components/Feed/myFeed'),
    },
    {
      path: '/VodDetail',
      name: 'VodDetail',
      component: () => import('@/views/vod/VodDetail.vue'),
    },
    {
      path: '/Category',
      name: 'Category',
      component: () => import('@/views/vod/Category.vue'),
    },
    {
      path: '/Myplaylist',
      name: 'Myplaylist',
      component: () => import('@/components/Feed/MyPlayList.vue'),
    },
    {
      path: '/Google',
      name: 'GoogleLogin',
      component: () => import('@/components/user/snsLogin/GoogleLogin.vue'),
    },
    {
      path: '*',
      name: 'NotFound',
      component: () => import('@/views/NotFoundPage.vue'),
    },
    {
      path:'/playlist/:id',
      name:'PlaylistDetail',
      component: () => import('@/components/MyStreamingList/StreamingListDetail'),

    },
    //path 정해지면 추가예정
    // {
    //   path: '',
    //   name: 'VODSections',
    //   component: () => import('@/components/Main/VODSections.vue'),
    //   children: [
    //     {
    //       path: 'popular',
    //       name: 'VodPopular',
    //       component: () => import('@/components/Main/VodPopular'),
    //     },
    //     {
    //       path: 'recent',
    //       name: "VodRecent",
    //       component: () => import('@/components/Main/VodRecent'),
    //     }, 
    //     {
    //       path: 'comment',
    //       name: "VodComment",
    //       component: () => import('@/components/Main/VodComment'),
    //     },

    //   ]
    // },
  ],
});
