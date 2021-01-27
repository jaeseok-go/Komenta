import axios from 'axios';
import store from '@/stores/index.js';

export function setInterceptors() {
  let instance = axios.create({
    baseURL: process.env.VUE_APP_URL,
    // CORS 방지코드
    // headers: {
    //   'Access-Control-Allow-Origin': '*',
    //   'Content-Type': 'application/json; charset = utf-8'
    // }
  });
  instance.interceptors.request.use(
    (config) => {
      let token = store.state.user.token;
      if (token) {
        config.headers['access-token'] = token;
      }
      return config;
    },
    (error) => Promise.reject(error.response)
  );
  instance.interceptors.response.use(
    (config) => config,
    (error) => Promise.reject(error.response)
  );
  return instance;
}



