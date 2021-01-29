import { loginUser } from '@/api/user.js'
import jwtDecode from 'jwt-decode'
// localstorage에 토큰 저장하는 방식으로 바꾸기! -> 이름만 localStorage로 바꾸면됨
const state = {
    adminAuth: 0,
    token: sessionStorage.getItem('auth-token'),
    isLogin: sessionStorage.getItem('auth-token') === null ? false : true,
    isLoginError: false,
    userInfo: sessionStorage.getItem('auth-token') === null ? {
        u_id:null,
        u_email: null,
        // u_pw: null,
        u_phone_number: null,
        u_nickname: null,
        u_expire_member :null,
        u_is_admin:false,
        u_is_blocked:false,
        u_profile_pic:null,
    } : jwtDecode(sessionStorage.getItem('auth-token')),
    isPasswordConfirmed: false,
};

const mutations = {
    setToken(state, token) {
        state.token = token
        sessionStorage.setItem('auth-token', token)
        state.isLogin = true
        state.isLoginError = false
        // state.userInfo = userData
        // console.log(state.userInfo)
        state.userInfo = jwtDecode(token)
    },
    // fetchInfo(state,userData) {
    //     state.userInfo = userData
    //     console.log(state.userInfo,'제대로 들어갔냐')
    // },
    setEmail(state,email){
        state.userInfo.u_email = email
    },
    setPhonenum(state,phone){
        state.userInfo.u_phone_number = phone
    },
    logout(state) {
        state.token = ''
        state.isLogin = false
        state.isLoginError = false
        sessionStorage.clear()
    },
    loginError(state) {
        state.isLoginError = true
    },
    confirmComplete(state) {
        state.isPasswordConfirmed = true
    },
    enteredAccount(state) {
        state.isPasswordConfirmed = false
    },
}

const actions = {
    async LOGIN({ commit }, userData) {
        console.log("야야야야야ㅑ야",commit,userData)
        const response = await loginUser(userData);
        // console.log("response: ", response)
        // console.log("auth-token: ", response.headers['auth-token']);
        // var myHeaders = new Headers();
        // console.log(myHeaders.get('auth-token'));
        // console.log("response 이후",)
        // if (response.data.token) {
        // commit('setToken', response.data.token)
        if (response.data['auth-token']) {
            commit('setToken', response.data['auth-token'])
            // commit('fetchInfo', response.data.data)
            console.log(response.data.data,'유저정보들어왔니')
        } else {
            commit('loginError')
        }
        console.log(state.userInfo)
        return response
    },
    async PASSWORDCONFIRM({ commit }, userData) {
        const response = await loginUser(userData)
        if (response.data['access-token']) {
            commit('confirmComplete')
            commit('setToken', response.data['access-token'])
        } else {
            commit('loginError')
        }
        return response
    }

};

export default {
    state,
    mutations,
    actions
}
