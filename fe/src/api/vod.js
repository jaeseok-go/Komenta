import { setInterceptors } from './config/interceptors'
import axios from 'axios'
import store from '@/stores/modules/user'

const instance = setInterceptors()


//댓글순 vod 조회
function fetchCommentVOD() {
    return instance.get('vod/list_comment');
}

//조회순 vod 조회
function fetchPopularVOD() {
    return instance.get('vod/list_watching')
}

//최신순 vod조회
function fetchRecentVOD() {
    return instance.get('vod/list_recent')
}


//vod 리스트 가져오기(VOD 추가)
function fetchVodList() {
    return instance.get(`admin/vod_list`)
}

//등록된 episode 가져오기(VOD 추가)
function fetchAllEpi() {
    return instance.get(`admin/episode_all`)
}

//등록된 장르 가져오기(VOD 추가)
function fetchAllGenre() {
    return instance.get(`genre/list_genre`)
}

//장르 대분류 선택 조회(VOD 추가)
function fetchGenreDetail(gId) {
    return instance.get(`genre/list_genre_detail`, {
        params: {
            g_id: gId
        }
    })
}

// 장르 대분류별 VOD선택 조회
function fetchMainGenreVod(gId) {
    return instance.get('vod/list_genre',{
        parmas: {
            g_id:gId
        }
    })
}

// 장르 대분류별 소분류 VOD선택 조회
function fetchSubGenreVod(gdId) {
    return instance.get('vod/list_genre_detail', {
        parmas: {
            gd_id: gdId
        }
    })
}


//VOD 소분류에 의한 vod list 가져오기
function fetchVodListByGenreDetailId(gdId) {
    return instance.get(`admin/vod_list_by_gdid/${gdId}`, {
        params: {
            gd_id: gdId,
        }
    })
}

//vod 세부내용조회
function fetchVodDetail(vId) {
    return instance.get(`vod/vod_info/${vId}`,
        {
            params: {
                v_id: vId,
            }
        })
}

// vod_epi로 세부내용조회
function fetchVodEpiDetail(veId) {
    return instance.get(`/vod/vodnum/${veId}`,
        {
            params: {
                ve_id: veId,
            }
        })
}

//vod 정보 추가
function sendVODInfo(vodInfo) {
    return instance.post(`admin/vod_regist`,vodInfo)
}

//vod 영상 저장
function insertVOD(vodForm) {
    // console.log("vod 영상 정보 : ",vodForm.get('file'))
    return axios.post('http://i4b201.p.ssafy.io:8080/admin/video_upload',vodForm, { 
      headers: {
         'Content-Type': 'multipart/form-data',
         'auth-token': store.state.token
     }
    })
}

function insertVodPoster(vodForm) {
    // console.log("vod 포스터 정보 : ",vodForm.get('v_poster'))
    return axios.post('http://i4b201.p.ssafy.io:8080/admin/poster_upload',vodForm, { 
      headers: {
         'Content-Type': 'multipart/form-data',
         'auth-token': store.state.token
     }
    })
}

// 플레이리스트 세부페이지
function fetchPlayListDetail(plId) {
    return instance.get(`playlist/playlist_detail/${plId}`, {
        params: {
            pl_id: plId
        }
    })
}

// 좋아요가 많은 플레이리스트 순(전체 플레이리스트 중 탑 10)
function fetchPopularPlayList() {
    return instance.get(`playlist/bestplist`)
}

// 플레이리스트 컨텐츠의 상세보기
function fetchPlaylistContent(plcId){
    return instance.get(`playlist/contents_detail/${plcId}`,{
        params:{
            plc_id:plcId
        }
    })
}

// vod검색
function searchVodlist() {
 return instance.get('search/list_vod')
}

// vod 시청시작
function startVodWatch(veId){
    return instance.post('vod/start_watching',veId)
}

// vod 시청 끝
function endVodWatch(vodData){
    return instance.put('vod/end_watching',vodData)
}

export {
    fetchCommentVOD,
    fetchPopularVOD,
    fetchRecentVOD,
    fetchVodEpiDetail,
    fetchVodList,
    fetchAllEpi,
    fetchAllGenre,
    fetchGenreDetail,
    fetchMainGenreVod,
    fetchSubGenreVod,
    fetchVodListByGenreDetailId,
    fetchVodDetail,
    sendVODInfo,
    insertVOD,
    insertVodPoster,
    fetchPlayListDetail,
    fetchPopularPlayList,
    fetchPlaylistContent,
    searchVodlist,
    startVodWatch,
    endVodWatch
}