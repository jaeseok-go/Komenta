import { setInterceptors } from './config/interceptors'
// import axios from 'axios';

const instance = setInterceptors()

//회원가입 API
function registerUser(userData) {
    console.log('넘어는왔니?',userData)
    return instance.post('member/join/', userData);
    // return axios.post(`http://i4b201.p.ssafy.io:8080/member/join/`,userData)
}

//로그인 API
function loginUser(userData) {
    return instance.post('member/login/', userData);
}

// 내 정보 가져오는 API
function fetchMyInfo(userId) {
    return instance.get(`member/info/`,userId)
}

// 내 정보 수정
function updateMyInfo(userData) {
    return instance.put('member/update/', userData)
}

// 회원탈퇴
function deleteMyInfo(userId) {
    return instance.delete(`member/delete/`, userId)
}

// 이메일 인증
function emailAuth(userId) {
    //비밀번호 찾기
    return instance.get(`check/sendEmail/`, userId)
}

//아이디 중복 확인
function userIdChk(userId) {
    return instance.get(`member/chk_id/`, {
        params: { u_email: userId }
    })
}

function userNickNameChk(userNickName) {
    return instance.get(`member/chk_nickname/`, {
        params: { u_nickname: userNickName }
    })
}

//비밀번호 찾기 - 새 비밀번호 변경
function changePw(userData) {
    return instance.put(`member/change_pw`,userData)
}

// 휴대폰 인증
function phoneAuth(userPhonenum) {
    // console.log(typeof userPhonenum)
    //아이디찾기
    return instance.get(`member/find_id/`, {
        params: { u_phone_number: userPhonenum }
    }) 
}

// 유저 전체 목록 불러오기(관리자용)
function fetchAllUsers() {
    return instance.get('admin/member_list/')
}

// 유저가 최근 본 시청기록 목록
function fetchRecentPlaylist(userId) {
    return instance.get('',userId)
}

// 유저가 좋아요 누른 플레이 리스트
function fetchLikePlaylist(userId) {
    return instance.get('',userId)
}

// 유저가 피드에서 플레이 리스트 생성
function addPlaylist(data) {
    return instance.post('',data)
}

function fetchfollowinglist(userId) {
    return instance.get('',userId)
}

function fetchMyPlaylist(userId){
    return instance.get(`/${userId}`)
}

function fetchUserFeed(userId){
    return instance.get(`뭐뭐/머머/${userId}`)
}
export {
    registerUser,
    loginUser,
    fetchMyInfo,
    updateMyInfo,
    deleteMyInfo,
    fetchAllUsers,
    emailAuth,
    phoneAuth,
    userIdChk,
    userNickNameChk,
    changePw,
    fetchRecentPlaylist,
    fetchLikePlaylist,
    addPlaylist,
    fetchfollowinglist,
    fetchMyPlaylist,
    fetchUserFeed
    
}