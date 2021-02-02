import { setInterceptors } from './config/interceptors'

const instance = setInterceptors()

//인기 플레이 리스트
function fetchPopularPlayList() {
    return instance.get('');
}

//댓글순 vod 조회
function fetchCommentVOD() {
    return instance.get('');
}

//인기순 vod 조회
function fetchPopularVOD() {
    return instance.get('')
}

//최신순 vod조회
function fetchRecentVOD() {
    return instance.get('')
}

function fetchStreamingListDetail(data) {
    return instance.get(``,{
        params: { u_id: data.u_id, pl_id: data.pl_id }
    })
}

//vod 리스트 가져오기(VOD 추가)
function fetchVodList() {
    return instance.get(`admin/vod_list/`)
}

//등록된 episode 가져오기(VOD 추가)
function fetchAllEpi() {
    return instance.get(`admin/episode_all/`)
}

//등록된 장르 가져오기(VOD 추가)
function fetchAllGenre() {
    return instance.get(`genre/list_genre/`)
}

//장르 소분류 선택 조회(VOD 추가)
function fetchGenreDetail(gId, gdId) {
    return instance.get(`genre/list_genre_detail`, {
        params: {
            g_id: gId,
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

// vod_epi로 세부내용조회
function fetchVodEpiDetail({veId}) {
    return instance.get(`/vod/vodnum/${veId}`,
        {
            params: {
                ve_id: veId,
            }
        })
}



export {
    fetchPopularPlayList,
    fetchCommentVOD,
    fetchPopularVOD,
    fetchRecentVOD,
    fetchStreamingListDetail,
    fetchVodEpiDetail,
    fetchVodList,
    fetchAllEpi,
    fetchAllGenre,
    fetchGenreDetail,
    fetchVodListByGenreDetailId
}