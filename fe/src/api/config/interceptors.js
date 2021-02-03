import axios from 'axios';
import store from '@/stores/index.js';

export function setInterceptors() {
  // let ddddd = store.state.user.token;
  let instance = axios.create({
    baseURL: process.env.VUE_APP_URL,
    // CORS 방지코드
    // headers: {
    //   Authorization: store.state.user.token
      // 'Access-Control-Allow-Origin': '*',
      // 'Content-Type': 'application/json; charset = utf-8'
    // }
  });
  instance.interceptors.request.use(
    // console.log(instance.interceptors.request)
    (config) => {
      let token = store.state.user.token;
      // if (token) {
      //   config.headers['Authorization'] = 'JWT ' + store.state.user.token
      // }
      if (token) {
        config.headers['auth-token'] = token;
        console.log(config.headers,'토큰????????')
        console.log(config)

      }
      return config;
    },
    (error) => Promise.reject(error.response)
  );
  instance.interceptors.response.use(
    (config) => {
      // console.log("status: ", config.data.status);
      // console.log("token: ", config.data['auth-token']);
      return config;
    },
    // config => config,
    (error) => Promise.reject(error.response)
  );
  return instance;
}



