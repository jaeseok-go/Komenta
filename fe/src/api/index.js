import axios from 'axios';
import { setInterceptors } from './config/interceptors'

function createInstance(url, options) {
    const instance = axios.create(Object.assign({ baseURL: url }, options));
    return instance;
}


function createInstanceWithAuth(url, options) {
    const instance = axios.create(Object.assign({ baseURL: url }, options));
    setInterceptors(instance);
    return instance;
}

export const auth = createInstance(process.env.VUE_APP_URL)
export const vod = createInstanceWithAuth(`${process.env.VUE_APP_API_URL}vod/`);