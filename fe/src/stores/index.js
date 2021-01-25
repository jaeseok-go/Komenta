import Vue from 'vue';
import Vuex from 'vuex';
import { saveAuthToCookie, saveUserToCookie } from '@/utils/cookies'
import { loginUser } from '@/api/auth'


Vue.use(Vuex);

export default new Vuex.Store({
    state:{
        userId:"",
        password: "",
        passwordConfirm: "",
        username:"",
        nickName: "",
        userPhoneNumber: "",
        // 추가
        platform : "",
    },
    mutations:{
        setUser(state,payload){
            state.usename = payload.name;
            state.platform = payload.platform;
        },
        setToken(state, token) {
            state.token = token
        },
        clearToken(state) {
            state.token = '';
        }

    },
    actions: {
        async LOGIN({ commit }, userData) {
            const { data } = await loginUser(userData);
            commit('setToken', data.token);
            commit('setUsername', data.user.username);
            saveAuthToCookie(data.token);
            saveUserToCookie(data.user.username);
            return data
        }
    }
});

