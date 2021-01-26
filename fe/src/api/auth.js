import { instance } from './index'

//회원가입 API
function registerUser(userData) {
    return instance.post('member/join', userData);
}

//로그인 API
function loginUser(userData) {
    return instance.post('member/login', userData);
}

export {
    registerUser,
    loginUser
}