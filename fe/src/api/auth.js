import { auth } from './index'

//회원가입 API
function registerUser(userData) {
    return auth.post('join', userData);
}

//로그인 API
function loginUser(userData) {
    return auth.post('login', userData);
}

//회원정보 수정 API
function updateUser(userData) {
    return auth.post('update', userData);
}

export {
    registerUser,
    loginUser,
    updateUser
}