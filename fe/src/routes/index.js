import Vue from 'vue';
import Router from 'vue-router';


Vue.use(Router);

export default new Router({
	mode: 'history',
	routes: [
		{
		path: '/login',
		name:"Login",
		component: () => import('@/views/user/Login.vue'),
		},
		{
		path: '/signup',
		name:"Signup",
		component: () => import('@/views/user/Signup.vue'),
		},
		{
		path: '/auth',
		name:"Kakao",
		component: () => import('@/components/user/snsLogin/Kakao.vue'),
		},

	],
});
