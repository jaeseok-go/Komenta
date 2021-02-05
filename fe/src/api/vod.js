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
function fetchVodEpiDetail({veId}) {
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
    console.log(vodForm.get('file'))
    return axios.post('http://i4b201.p.ssafy.io:8080/admin/video_upload',vodForm, { 
      headers: {
         'Content-Type': 'multipart/form-data',
         'auth-token': store.state.token
     }
    })
}

function insertVodPoster(vodForm) {
    return axios.post('http://i4b201.p.ssafy.io:8080/admin/poster_upload',vodForm, { 
      headers: {
         'Content-Type': 'multipart/form-data',
         'auth-token': store.state.token
     }
    })
}
// 플레이리스트 세부페이지
function fetchPlayListDetail(plId) {
    return instance.get('', {
        params: {
            pl_id: plId
        }
    })
}

// 좋아요가 많은 플레이리스트 순
function fetchPopularPlayList() {
    return instance.get()
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
    fetchPopularPlayList
}