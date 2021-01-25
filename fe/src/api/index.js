import axios from 'axios';
import { setInterceptors } from './config/interceptors'

function createInstance() {
    return axios.create({
        baseURL: process.env.VUE_APP_URL,
    })
}


function createInstanceWithAuth(url) {
    const instance = axios.create({
        baseURL: `${process.env.VUE_APP_URL}${url}`,
        // headers: {
        //   Authorization: store.state.token,
        // }
    });
    // 인터셉터까지 설정된 인스턴스 리턴
    return setInterceptors(instance)
}

export const instance = createInstance()
export const posts = createInstanceWithAuth('posts');