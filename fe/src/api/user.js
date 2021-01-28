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
    return instance.get(`check/sendEmail/`, userId)
}

// 휴대폰 인증
function phoneAuth(userPhonenum) {
    console.log(typeof userPhonenum)
    return instance.get(`member/find_id/`,userPhonenum)
}

// 유저 전체 목록 불러오기(관리자용)
function fetchAllUsers() {
    return instance.get('admin/member_list/')
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
}