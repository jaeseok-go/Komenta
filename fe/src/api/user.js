import { setInterceptors } from './config/interceptors'
// import axios from 'axios';
// import store from '@/stores/index.js';


const instance = setInterceptors()
// const instance = axios.create({
//     baseURL: process.env.VUE_APP_URL,
//     headers: {
//         Authorization: store.state.user.token, // header의 속성
//     },
// });

//회원가입 API
function registerUser(userData) {
    console.log('넘어는왔니?',userData)
    return instance.post('member/join', userData);
    // return axios.post(`http://i4b201.p.ssafy.io:8080/member/join/`,userData)
}

//로그인 API
function loginUser(userData) {
    return instance.post('member/login', userData);
}

// 내 정보 가져오는 API
function fetchMyInfo(userId) {
    return instance.get(`member/info`,userId)
}

// 내 정보 수정
function updateMyInfo(userData) {
    return instance.put('member/update', userData)
}

// 회원탈퇴
function deleteMyInfo(userId) {
    return instance.delete(`member/delete`, {
        params: { u_id: userId }
    })
}

// 이메일 인증
function emailAuth(userId) {
    //비밀번호 찾기
    return instance.get(`check/sendEmail`, userId)
}

//아이디 가입 여부 확인
function userIdChk(userId) {
    return instance.get(`member/chk_id`, {
        params: { u_email: userId }
    })
}

//아이디 중복 확인
function dupIdChk(userId) {
    return instance.get(`member/dup_id_chk`, {
        params: { u_email: userId }
    })
}

//닉네임 중복 확인
function dupNickNameChk(userNickName) {
    return instance.get(`member/dup_nickname_chk`, {
        params: { u_nickname: userNickName }
    })
}

//비밀번호 찾기 - 새 비밀번호 변경
function changePw(userData) {
    return instance.put(`member/change_pw`,userData)
}

// 휴대폰 인증
function phoneAuth(userPhonenum) {
    // console.log(typeof userPhonenum)
    //아이디찾기
    return instance.get(`member/find_id/`, {
        params: { u_phone_number: userPhonenum }
    }) 
}

// 멤버십 가입/해지
function membership() {
    return instance.post('member/membership')
}

// 프로필 사진 file 업로드 
function uploadProfile(profile) {
    return instance.post('member/profile_upload',profile)
}

// 유저 전체 목록 불러오기(관리자용)
function fetchAllUsers() {
    return instance.get('admin/member_list')
}

// 유저 정보 수정(관리자용)
function updateUserInfo(userData) {
    return instance.put('admin/member_update', userData)
}

//VOD 목록 조회(관리자용)
function fetchAllVOD() {
    return instance.get('admin/vod_list')
}

function fetchMyComment(uId) {
    return instance.get(`myinfo/comment_list`, {
        params:{u_id:uId}
    })
}

// 유저가 최근 본 시청기록 목록
function fetchRecentPlaylist(userId) {
    return instance.get('vod/myvod', {
        params: { u_id: userId }
    })
}

// 유저가 좋아요 누른 플레이 리스트
function fetchLikePlaylist(userId) {
    return instance.get(`playlist/get_favorite_plist/${userId}`, {
        params: { u_id: userId }
    })
}

// 유저가 피드에서 플레이 리스트 생성
function addPlaylist(data) {
    return instance.post('playlist/move_history_to_playlist',data)
}

//플레이리스트 VOD생성(나의 시청기록에서 시청기록을 끌어당겨 플레이리스트에 추가)
function addPlaylistVod(vodInfo) {
    return instance.post('playlist/move_history_to_playlist',vodInfo)
}



// 플레이리스트 수정
function modifyPlaylist(playlistInfo) {
    return instance.put('playlist/plist_update',playlistInfo)
}

// 플레이리스트 삭제
function deletePlaylist(plId){
    return instance.delete('playlist/plist_delete',plId)
}

// 플레이리스트 좋아요 취소
function removePlaylist(playlistInfo){
    return instance.put('playlist/plist_update', playlistInfo)
}

// 회원이 등록한 플레이리스트 목록 조회
function fetchMyPlaylist(userId){
    return instance.get(`playlist/get_plist_list/${userId}`, {
        params: { u_id: userId }
    })
}

// 플레이리스트의 컨텐츠 리뷰 입력
function addReviewPlaylist(playlistInfo){
    return instance.put('playlist/add_contents_review', playlistInfo)
}

// 팔로잉 조회 
function fetchfollowinglist(userId) {
    return instance.get('follow/follow_list', {
        params: { u_id: userId }
    })
}

// 팔로워 조회 
function fetchfollowerlist(userId) {
    return instance.get('follow/follower_list', {
        params: { u_id: userId }
    })
}

// 팔로우 추가/ 취소
function modifyfollow(followInfo) {
    return instance.post('follow/add_sub',followInfo)
}


// 언팔로잉 조회
function fetchunfollowinglist(userId) {
    return instance.get('follow/unfollowing_list', {
        params: { u_id: userId }
    })
}

// 언팔로우 추가 / 취소
function modifyunfollow(unfollowInfo) {
    return instance.post('follow/un_add_sub',unfollowInfo)
}

// 유저 검색
function searchUserlist(){
    return instance.get('search/list_member')
}


export {
    registerUser,
    loginUser,
    fetchMyInfo,
    updateMyInfo,
    deleteMyInfo,
    fetchAllUsers,
    updateUserInfo,
    fetchAllVOD,
    emailAuth,
    phoneAuth,
    membership,
    uploadProfile,
    userIdChk,
    dupIdChk,
    dupNickNameChk,
    changePw,
    fetchMyComment,
    fetchRecentPlaylist,
    fetchLikePlaylist,
    addPlaylist,
    addPlaylistVod,
    deletePlaylist,
    fetchfollowinglist,
    fetchMyPlaylist,
    modifyPlaylist,
    fetchfollowerlist,
    modifyfollow,
    fetchunfollowinglist,
    modifyunfollow,
    removePlaylist,
    addReviewPlaylist,
    searchUserlist
    
}