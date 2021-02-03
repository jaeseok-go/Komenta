import { setInterceptors } from './config/interceptors'

const instance = setInterceptors()

//실시간 베스트 댓글 유저 랭킹
function fetchBestComment() {
    return instance.get('/comment/comment_rank');
}

//댓글입력
function commentInsert(){
    return instance.post('/comment/insert');
}

//회차별 댓글 정보
function fetchEpiComment(veId) {
    return instance.get(`/comment/ve_c_list/${veId}`);
}

export {
    fetchBestComment,
    commentInsert,
    fetchEpiComment
}