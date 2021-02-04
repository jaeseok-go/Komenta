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
      component: () => import('@/components/Main/Main.vue'),
      redirect: '/vodpopular',
      children: [
        {
          path: 'vodpopular',
          name: 'VodPopular',
          component: () => import('@/components/Main/VodPopular.vue'),
        },
        {
          path: 'vodrecent',
          name: "VodRecent",
          component: () => import('@/components/Main/VodRecent.vue')
        },
        {
          path: 'vodcomment',
          name: "VodComment",
          component: () => import('@/components/Main/VodComment.vue')
        },
      ]
    },
    {
      path: '/member/login',
      name: 'Login',
      component: () => import('@/views/user/Login.vue'),
      children: [
        {
          path: 'findid',
          name: 'FindId',
          component: () => import('@/components/FindId.vue'),
        },
        {
          path: 'findpw',
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
      path: '/member/mypage',
      name: 'MyPage',
      component: () => import('@/views/user/MyPage.vue'),
      // redirect: '/member/myPage/userManage',
      children: [
        {
          path: 'usermanage',
          name: 'UserManage',
          component: () => import('@/components/admin/UserManagement/UserManagement.vue'),
          // redirect: 'usermanage/allUser',
          children: [
            {
              path: 'alluser',
              name: 'AllUser',
              component: () => import('@/components/admin/UserManagement/AllUser.vue'),
              props: true
            },
            {
              path: 'blockeduser',
              name: 'BlockedUser',
              component: () => import('@/components/admin/UserManagement/BlockedUser.vue'),
              props: true
            },
            {
              path: 'adminUser',
              name: 'AdminUser',
              component: () => import('@/components/admin/UserManagement/AdminUser.vue'),
              props: true
            },
          ]
        },
        {
          path: '/vodmanage',
          name: "VodManage",
          component: () => import('@/components/admin/VodManagement.vue'),
          // childeren: [
            
          // ]
        },
      ]
    },
    {
      path: '/vodInsert',
      name: 'VodInsert',
      component: () => import('@/components/admin/VodInsert.vue')
    },
    {
      path: '/voddetail/:id',
      name: 'VodDetail',
      component: () => import('@/views/vod/VodDetail.vue'),
      props: true
    },
    {
      path: '/category',
      name: 'Category',
      component: () => import('@/views/vod/Category.vue'),
    },
    {
      path: '/feed/:id',
      name: 'Feed',
      component: () => import('@/components/Feed/MyFeed.vue'),
    },
    {
      path: '/google',
      name: 'GoogleLogin',
      component: () => import('@/components/user/snsLogin/GoogleLogin.vue'),
    },
    {
      path: '*',
      name: 'NotFound',
      component: () => import('@/views/NotFoundPage.vue'),
    },
    {
      path:'/myplaylist',
      name:'MyPlayList',
      component: () => import('@/components/MyPlayList/MyPlayList.vue'),
    },
    {
      path:'/playlist/:id',
      name:'PlayListDetail',
      component: () => import('@/components/MyPlayList/PlayListDetail')
    },


  ],
});
