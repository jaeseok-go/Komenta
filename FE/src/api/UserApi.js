/*
 User API 예시
 */

// const requestLogin = (data,callback,errorCallback) => {
//     //백앤드와 로그인 통신하는 부분
//     callback();

// }

// const UserApi = {
//     requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback)
// }

// export default UserApi

import axios from 'axios';
import { setInterceptors } from './config/interceptors';

function create(url,options) {
    const instance = axios.create(Object.assign({baseURL:url},options));
    return instance;
}

function createWithAuth(url, options) {
    const instance = axios.create(Object.assign({baseURL:url},options));
    setInterceptors(instance);
    return instance;
}

export const auth = create(process.env.VUE_APP_API_URL);