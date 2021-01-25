import axios from '@/api/config/axios';

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
            redirect_uri: 'http://localhost:8080/auth',
            code: code,
        };
        const queryString = Object.keys(data)
            .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(data[k]))
            .join('&');
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

const getGoogleToken = (googleUser) => {
    console.log('구글test');
    var profile = googleUser.getBasicProfile();
    console.log('ID Token: ', googleUser.getAuthResponse().id_token);
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
};


export {
    getKakaoToken,
    getKakaoUserInfo,
    getGoogleToken,
};