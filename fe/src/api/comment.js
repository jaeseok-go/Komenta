import { setInterceptors } from './config/interceptors'

const instance = setInterceptors()

//인기 플레이 리스트
function fetchBestComment() {
    return instance.get('');
}


export {
    fetchBestComment
}