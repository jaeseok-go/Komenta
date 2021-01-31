import { loginUser, fetchRecentPlaylist, fetchLikePlaylist } from '@/api/user.js'
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
    recentPlaylist:[],
    likePlaylist:[],
};

const mutations = {
    setToken(state, token) {
        state.token = token
        sessionStorage.setItem('auth-token', token)
        state.isLogin = true
        state.isLoginError = false
        // state.userInfo = userData
        state.userInfo = jwtDecode(token)
        console.log('여긴 store',state.userInfo)
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
    setModifyUserInfo(state, userData) {
        state.userInfo.u_nickname = userData.u_nickname
        state.userInfo.u_phone_number = userData.u_phone_number
        state.userInfo.u_profile_pic = userData.u_profile_pic
    },
    //1-3.최근 목록 플레이 리스트 저장
    saveRecentPlaylist(state, recentPlaylist) {
        state.recentPlaylist = recentPlaylist
    },
    saveLikePlaylist(state, likePlaylist) {
        state.likePlaylist = likePlaylist
    }
}

const actions = {
    async LOGIN({ commit }, userData) {
        console.log("야야야야야ㅑ야",commit,userData)
        const response = await loginUser(userData);
        
        
        if (response.data['auth-token']) {
            commit('setToken', response.data['auth-token'])
            //t commit('fetchInfo', response.data.daa)
            console.log(response.data.data,'유저정보들어왔니')
            
            //2-1.로그인할때 u_id로 내가 좋아요 누른 모든 플레이리스트 목록 갖고오기
            const likePlaylist = await fetchLikePlaylist(response.data.data.u_id)
            commit('saveLikePlaylist',likePlaylist)

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
    },
    async FETCH_RECENTPLAYLIST({ commit }, userId) {
        //1-1.로그인할때 u_id로 최근 시청 목록, 좋아요 누른 플레이 리스트 목록 갖고오기
        const recentPlaylist = await fetchRecentPlaylist(userId)
        //1-2.응답으로 들어온 recentPlaylist를 store에 저장하기
        commit('saveRecentPlaylist', recentPlaylist)
    }

};

const getters = {
    fetchedPlaylist(state) {
        return state.recentPlaylist
    }
}

export default {
    state,
    mutations,
    getters,
    actions
}
