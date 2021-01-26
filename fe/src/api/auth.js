import { auth } from './index'

//회원가입 API
function registerUser(userData) {
    return auth.post('member/join', userData);
}

//로그인 API
function loginUser(userData) {
    return auth.post('member/login', userData);
}

export {
    registerUser,
    loginUser
}