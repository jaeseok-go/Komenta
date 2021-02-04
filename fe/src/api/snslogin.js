// import { setInterceptors } from './config/interceptors'
import axios from './config/axios'
// const instance = setInterceptors()

const kakaoHeader = {
    'Authorization': process.env.VUE_APP_KAKAO_ADMIN_KEY,
    'Content-type': 'application/x-www-form-urlencoded;charset=utf-8',
};

const getKakaoToken = async (code) => {
    console.log('loginWithKakao');
    try {
        const data = {
            grant_type: 'authorization_code',
            client_id: process.env.VUE_APP_KAKAO_REST_API_KEY,
            redirect_uri: process.env.VUE_APP_URL,
            code: code,
        };
        const queryString = Object.keys(data)
            .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(data[k]))
            .join('&');
        console.log(axios,'kakao인스턴스')
        const result = await axios.post('https://kauth.kakao.com/oauth/token', queryString, { headers: kakaoHeader });
        console.log('카카오 토큰', queryString,result);
        return result;
    } catch (e) {
        return e;
    }
};
const getKakaoUserInfo = async () => {
    let data = '';
    await window.Kakao.API.request({
        url: "/v2/user/me",
        success: function (response) {
            data = response;
        },
        fail: function (error) {
            console.log(error);
        },
    });
    console.log('카카오 계정 정보', data);
    return data;
}

// 사용자 정보 저장하기
const updateProfileObject = async (userData) => {
    await window.Kakao.API.request({
        url: '/v1/user/update_profile',
        data: {
            properties: userData,
        },
        success: function (response) {
            console.log(response);
        },
        fail: function (error) {
            console.log(error);
        }
    });
}


export {
    getKakaoToken,
    getKakaoUserInfo,
    updateProfileObject,
};