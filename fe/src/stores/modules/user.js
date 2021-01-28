import { loginUser } from '@/api/user.js'
import jwtDecode from 'jwt-decode'
// localstorage에 토큰 저장하는 방식으로 바꾸기! -> 이름만 localStorage로 바꾸면됨
const state = {
    adminAuth: 0,
    token: sessionStorage.getItem('access-token'),
    isLogin: sessionStorage.getItem('access-token') === null ? false : true,
    isLoginError: false,
    userInfo: sessionStorage.getItem('access-token') === null ? {} : jwtDecode(sessionStorage.getItem('access-token')),
    isPasswordConfirmed: false,
};

const mutations = {
    setToken(state, token,userData) {
        state.token = token
        sessionStorage.setItem('access-token', token)
        state.isLogin = true
        state.isLoginError = false
        state.userInfo = userData
        // state.userInfo = jwtDecode(token)
    },
    // fetchInfo(state,userData) {
    //     state.userInfo = userData
    //     console.log(state.userInfo,'제대로 들어갔냐')
    // },
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
        if (response.headers['auth-token']) {
            commit('setToken', response.headers['auth-token'], response.data)
            console.log(response.data,'유저정보들어왔니')
        } else {
            commit('loginError')
        }
        return response
    },
    async PASSWORDCONFIRM({ commit }, userData) {
        const response = await loginUser(userData)
        if (response.headers['access-token']) {
            commit('confirmComplete')
            commit('setToken', response.headers['access-token'])
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
