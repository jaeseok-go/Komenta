import Vue from 'vue';
import Router from 'vue-router';
import store from '@/stores/modules/user';
// import { loadView,loadComponent } from '@/utils/loadPage'

Vue.use(Router);

const router =  new Router({
  mode: 'history',
  // base: process.env.VUE_APP_URL,
  routes: [
    // Main은 나중에 바꿀예정
    {
      path:'/',
      name:'AboutPage',
      component: () => import('@/views/AboutPage.vue'),
      meta: { auth: false },
    },
    {
      path: '/main',
      name: 'Main',
      component: () => import('@/components/Main/Main.vue'),
      meta: { auth: true },
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
      meta: { auth: true },
    },
    {
      path: '/admin/test',
      name: 'Test',
      component: () => import('@/components/admin/Test.vue'),
      meta: { auth: true },
    },
    {
      path: '/admin/vodPosterInput',
      name: 'PosterInput',
      component: () => import('@/components/admin/vodPosterInput.vue'),
      meta: { auth: true },
    },
    {
      path: 'people',
      name:'People',
      component: () => import('@/components/People/PeopleList.vue'),
      meta: { auth: true },
    },
    {
      path: '/member/mypage',
      name: 'MyPage',
      component: () => import('@/views/user/MyPage.vue'),
      meta: { auth: true },
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
          path: 'vodmanage',
          name: "VodManage",
          component: () => import('@/components/admin/VodManagement.vue'),
          meta: { auth: true },
        },
      ]
    },
    {
      path: '/admin/vodInsert',
      name: 'VODInsert',
      component: () => import('@/components/admin/VodInsert.vue'),
      meta: { auth: true },
    },
    {
      path: '/voddetail/:id',
      name: 'VodDetail',
      component: () => import('@/views/vod/VodDetail.vue'),
      meta: { auth: true },
      props: true,
      redirect:'/voddetail/:id/bestcomments',
      children: [
        {
          path: 'bestcomments',
          name: 'BestComments',
          component: () => import('@/components/vod/BestComments.vue'),
          props: true,
        },
        {
          path: 'allcomments',
          name: "AllComments",
          component: () => import('@/components/vod/AllComments.vue'),
          props: true,
        },
      ]
    },
    {
      path: '/category',
      name: 'Category',
      component: () => import('@/views/vod/Category.vue'),
      meta: { auth: true },
    },
    {
      path: '/feed/:id',
      name: 'Feed',
      component: () => import('@/components/Feed/MyFeed.vue'),
      meta: { auth: true },
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
      meta: { auth: true },
    },
    {
      path:'/playlist/:id',
      name:'PlayListDetail',
      component: () => import('@/components/MyPlayList/PlayListDetail'),
      meta: { auth: true },
    },

  ],
});



// router네비게이션가드 beforeEach에 콜백함수의 인자로 to,from,next가 들어감
// to:이동하려는 페이지 , from:현재페이지 , next:페이지이동할때 호출하는 API
router.beforeEach((to, from, next) => {
  // 라우터페이지정보에(to).meta에.auth에 true 이고(&&:AND)
  // 그정보가 store의 getters에 isLogin에 사용자가 로그인했는지 여부가 true/false로 있음
  // auth:true인 라우터는 login했는지 확인하고 안했으면(False,!) 로그인페이지로 이동
  if (to.meta.auth && !store.state.isLogin) {
    // log를찍고
    console.log('인증이 필요합니다');
    // next를 호출해야지 /login페이지로 이동
    next('/member/login');
    // return을 꼭 적어줘야됨! 그래야 아래에 있는 next()가 실행되지 않는다
    return;
  }
  next();
});

export default router;