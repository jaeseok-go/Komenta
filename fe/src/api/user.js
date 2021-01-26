import { setInterceptors } from './config/interceptors'

const instance = setInterceptors()


//회원가입 API
function registerUser(userData) {
    return instance.post('join', userData);
}

//로그인 API
function loginUser(userData) {
    return instance.post('login', userData);
}

// 내 정보 가져오는 API -> 마이페이지....api추가 필요...
function fetchMyInfo(userId) {
    return instance.get(`member/${userId}`)
}

// 내 정보 수정
function updateMyInfo(userData) {
    return instance.put('member/update', userData)
}

// 회원탈퇴
function deleteMyInfo(userId) {
    return instance.delete(`member/delete`, userId)
}

// 이메일 인증
function emailAuth(userId) {
    return instance.get(`member/authEmail`, userId)
}

// 휴대폰 인증
function phoneAuth(userPhonenum) {
    return instance.get(`member/authPhone`, userPhonenum)
}

// 유저 전체 목록 불러오기(관리자용)
function fetchAllUsers() {
    return instance.get('admin/memberlist')
}

// 가입된 회원수 가져오기(관리자용)
function fetchUsersCount() {
    return instance.get('admin/member')
}

export {
    registerUser,
    loginUser,
    fetchMyInfo,
    updateMyInfo,
    deleteMyInfo,
    fetchAllUsers,
    fetchUsersCount,
    emailAuth,
    phoneAuth,
}