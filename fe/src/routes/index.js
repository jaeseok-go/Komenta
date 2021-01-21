import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
	mode: 'history',
	routes: [
		{
		path: '/login',
		component: () => import('@/views/user/Login.vue'),
		},
		{
		path: '/signup',
		component: () => import('@/views/user/Signup.vue'),
		},
	],
});
