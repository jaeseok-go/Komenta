import { loginUser, 
    updateMyInfo,
    fetchLikePlaylist, fetchfollowinglist, fetchunfollowinglist, fetchMyPlaylist, fetchRecentPlaylist } from '@/api/user.js' //fetchRecentPlaylist, 
import jwtDecode from 'jwt-decode'

const state = {
    adminAuth: 0,
    token: sessionStorage.getItem('auth-token'),
    isLogin: sessionStorage.getItem('auth-token') === null ? false : true,
    isLoginError: false,
    userInfo: sessionStorage.getItem('auth-token') === null ? {
        token_expired : null,
        u_id: null,
        u_email: null,
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
        state.token = token
        sessionStorage.setItem('auth-token', token)
        state.isLogin = true
        state.isLoginError = false
        state.userInfo = jwtDecode(token)
    },
    fetchInfo(state, userData) {
        state.userInfo = userData
    },
    setEmail(state, email) {
        state.userInfo.u_email = email
    },
    setPhonenum(state, phone) {
        state.userInfo.u_phone_number = phone

    },
    logout(state) {
        state.token = ''
        state.isLogin = false
        state.isLoginError = false
        if (this.$gAuth != undefined) {
            this.$gAuth.signOut();
        }
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
    },
    setExpireMember(state, memDate) {
        state.userInfo.u_expire_member = memDate;
    }
}

const actions = {
    async LOGIN({ commit }, userData) {
        const response = await loginUser(userData);
        if (response.data['auth-token']) {
            commit('setToken', response.data['auth-token'])
        } else {
            commit('loginError')
        }
        //1-1.로그인할때 u_id로 최근 시청 목록, 좋아요 누른 플레이 리스트 목록 갖고오기
        const recentPlaylist = await fetchRecentPlaylist()
        //1-2.응답으로 들어온 recentPlaylist를 store에 저장하기
        commit('setRecentPlaylist', recentPlaylist.data)

        //로그인할때, 내가 만든 플레이리스트 목록 저장 
        const myPlayList = await fetchMyPlaylist(state.userInfo.u_id)
        commit('setMyPlayList',myPlayList.data)
        return response
    },
    async MODIFY({ commit }, userData) {
        const response = await updateMyInfo(userData);
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
        //1-2.응답으로 들어온 recentPlaylist를 store에 저장하기
        commit('setRecentPlaylist', recentPlaylist.data)
    },
    async FETCH_LIKEPLAYLIST({ commit }, userId) {
        //2-1.로그인할때 u_id로 내가 좋아요 누른 모든 플레이리스트 목록 갖고오기
        const likePlaylist = await fetchLikePlaylist(userId)
        commit('setLikePlaylist', likePlaylist.data)
    },
    //팔로잉 조회
    async FETCH_FOLLOWING({ commit }, userId) {
        const followingList = await fetchfollowinglist(userId)
        commit('setMyfollowingList', followingList.data )
    },
    //팔로잉 조회
    async FETCH_UNFOLLOWING({ commit }, userId) {
        const unfollowingList = await fetchunfollowinglist(userId)
        commit('setMyUnfollowingList', unfollowingList.data)
    },
    async FETCH_MYPLAYLIST({ commit }, userId){
        const myPlayList = await fetchMyPlaylist(userId)
        commit('setMyPlayList',myPlayList.data)
    },
    FETCH_MEMBERSHIP({ commit }, memberDate) {
        commit('setExpireMember', memberDate);
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
