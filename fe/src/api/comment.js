import { setInterceptors } from './config/interceptors'

const instance = setInterceptors()

//인기 플레이 리스트
function fetchBestComment() {
    return instance.get('');
}

function commentInsert(){
    return instance.post('/comment/insert');
}

function fetchEpiComment(veId) {
    return instance.get(`/comment/ve_c_list/${veId}`);
}

export {
    fetchBestComment,
    commentInsert,
    fetchEpiComment
}