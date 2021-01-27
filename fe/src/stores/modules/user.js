import { loginUser } from '@/api/user.js'
import jwtDecode from 'jwt-decode'
// localstorage에 토큰 저장하는 방식으로 바꾸기! -> 이름만 localStorage로 바꾸면됨
const state = {
    adminAuth: 0,
    token: sessionStorage.getItem('token'),
    isLogin: sessionStorage.getItem('token') === null ? false : true,
    isLoginError: false,
    userInfo: sessionStorage.getItem('token') === null ? {} : jwtDecode(sessionStorage.getItem('token')),
    isPasswordConfirmed: false,
};

const mutations = {
    setToken(state, token) {
        state.token = token
        sessionStorage.setItem('token', token)
        state.isLogin = true
        state.isLoginError = false
        state.userInfo = jwtDecode(token)
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
        
        var client = new XMLHttpRequest();
        client.onreadystatechange = function () {
            // if (this.readyState == this.HEADERS_RECEIVED) {
                var contentType = client.getResponseHeader("auth-token");
                console.log(contentType);
            // }
        }
        // var myHeaders = new Headers();
        // console.log(myHeaders.get('auth-token'));
        // console.log("response 이후",)
        // if (response.data.token) {
        // commit('setToken', response.data.token)
        if (response.headers['auth-token']) {
            commit('setToken', response.headers['auth-token'])
        } else {
            commit('loginError')
        }
        return response
    },
    async PASSWORDCONFIRM({ commit }, userData) {
        const result = await loginUser(userData)
        if (result.headers['access-token']) {
            commit('confirmComplete')
            commit('setToken', result.headers['access-token'])
        } else {
            commit('loginError')
        }
        return result
    }

};

export default {
    state,
    mutations,
    actions
}
