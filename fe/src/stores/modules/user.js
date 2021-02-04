import { loginUser, 
    updateMyInfo,
    fetchLikePlaylist, addPlaylist, fetchfollowinglist, fetchMyPlaylist, fetchUserFeed,fetchRecentPlaylist } from '@/api/user.js' //fetchRecentPlaylist, 
import jwtDecode from 'jwt-decode'
// localstorage에 토큰 저장하는 방식으로 바꾸기! -> 이름만 localStorage로 바꾸면됨
const state = {
    adminAuth: 0,
    token: sessionStorage.getItem('auth-token'),
    isLogin: sessionStorage.getItem('auth-token') === null ? false : true,
    isLoginError: false,
    userInfo: sessionStorage.getItem('auth-token') === null ? {
        u_id: null,
        u_email: null,
        // u_pw: null,
        u_phone_number: null,
        u_nickname: null,
        u_expire_member: null,
        u_is_admin: false,
        u_is_blocked: false,
        u_profile_pic: 'default_profile.png',
    } : jwtDecode(sessionStorage.getItem('auth-token')),
    isPasswordConfirmed: false,
    recentPlaylist:[],
    likePlaylist:[],
    myFollowingList: [],
    myUnFollowingList:[],
    myPlayList:[],
    userFeed:[],
};

const mutations = {
    setToken(state, token) {
        console.log("수정 후 처음 들어오는 토큰 : ",token);
        state.token = token
        sessionStorage.setItem('auth-token', token)
        state.isLogin = true
        state.isLoginError = false
        // state.userInfo = userData
        state.userInfo = jwtDecode(token)
        console.log('여긴 store', state.userInfo)
    },
    fetchInfo(state, userData) {
        state.userInfo = userData
        // console.log(state.userInfo,'제대로 들어갔냐')
    },
    setEmail(state, email) {
        state.userInfo.u_email = email
    },
    setPhonenum(state, phone) {
        state.userInfo.u_phone_number = phone
        console.log(state.userInfo, '제대로 들어갔냐')

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
    setRecentPlaylist(state, recentPlaylist) {
        state.recentPlaylist = recentPlaylist
        console.log(state.recentPlaylist,'최근목록리스트 state')
    },
    setLikePlaylist(state, likePlaylist) {
        state.likePlaylist = likePlaylist
    },
    setMyfollowingList(state, followingList){
        state.myFollowingList = followingList
    },
    setMyUnfollowingList(state, unfollowingList){
        state.myUnFollowingList = unfollowingList
    },
    setMyPlayList(state, myPlayList){
        state.myPlayList = myPlayList
    },
    setUserFeed(state, userFeed){
        state.userFeed = userFeed
    }
}

const actions = {
    async LOGIN({ commit }, userData) {
        console.log("야야야야야ㅑ야", commit, userData)
        const response = await loginUser(userData);
        //1-1.로그인할때 u_id로 최근 시청 목록, 좋아요 누른 플레이 리스트 목록 갖고오기
        const recentPlaylist = await fetchRecentPlaylist(userData.userId)
        //1-2.응답으로 들어온 recentPlaylist를 store에 저장하기
        commit('setRecentPlaylist', recentPlaylist)
        
        
        if (response.data['auth-token']) {
            commit('setToken', response.data['auth-token'])
            //t commit('fetchInfo', response.data.daa)
            console.log(response.data.data, '유저정보들어왔니')

        } else {
            commit('loginError')
        }
        console.log(state.userInfo)
        return response
    },
    async MODIFY({ commit }, userData) {
        console.log("수정할 정보 : ",userData);
        const response = await updateMyInfo(userData);
        console.log("회원 정보 수정 response : ",response)
        console.log('token jwt decode',jwtDecode(response.data['auth-token']))
        commit('setToken',response.data['auth-token'])
    },
    async PASSWORDCONFIRM({ commit }, userData) {
        const response = await loginUser(userData)
        if (response.data['auth-token']) {
            commit('confirmComplete')
            commit('setToken', response.data['auth-token'])
        } else {
            commit('loginError')
        }
        return response
    },
    async FETCH_RECENTPLAYLIST({ commit }, userId) {
        //1-1.로그인할때 u_id로 최근 시청 목록, 좋아요 누른 플레이 리스트 목록 갖고오기
        const recentPlaylist = await fetchRecentPlaylist(userId)
        console.log(recentPlaylist.data,'최근 플레이리스트..')
        //1-2.응답으로 들어온 recentPlaylist를 store에 저장하기
        commit('setRecentPlaylist', recentPlaylist.data)
    },
    async FETCH_LIKEPLAYLIST({ commit }, userId) {
        //2-1.로그인할때 u_id로 내가 좋아요 누른 모든 플레이리스트 목록 갖고오기
        const likePlaylist = await fetchLikePlaylist(userId)
        commit('setLikePlaylist', likePlaylist)
    },
    async ADD_PLAYLIST({ commit }, data) {
        const response = await addPlaylist(data)
        console.log(response,commit)
    },
    async FETCH_FOLLOWING({ commit }, userId) {
        const followingList = await fetchfollowinglist(userId)
        commit('setMyfollowingList', followingList )
    },
    async FETCH_MYPLAYLIST({ commit }, userId){
        const myPlayList = await fetchMyPlaylist(userId)
        commit('setMyPlayList',myPlayList)
    },
    //유저 피드 정보
    async FETCH_FEED({ commit },userId){
        const userFeed = await fetchUserFeed(userId)
        commit('setUserFeed',userFeed)
    }

};

const getters = {
    fetchedUserInfo(state) {
        return state.userInfo
    },
    fetchedRecentPlaylist(state) {
        return state.recentPlaylist
    },
    fetchedLikePlaylist(state) {
        return state.likePlaylist
    },
    fetchedFollowingList(state) {
        return state.myFollowingList
    },
    fetcedMyPlaylist(state) {
        return state.myPlayList
    },
    fetchedUserFeedInfo(state) {
        return state.userFeed
    }
}

export default {
    state,
    mutations,
    getters,
    actions
}
