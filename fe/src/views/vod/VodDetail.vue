<template>
    <div>
        <!-- 사이드바는 hidden -->
        <Asidebar></Asidebar>
        <Header></Header>
        <!-- vod -->
        <div id="appBody">
            1-1. 해당회차 VOD보여주기 <br>
            GET(ve_id) -> res(비디오 불러오기)<br>
            1-2. 해당회차 세부내용<br>
            GET(ve_id) -> res(v_id,회차,날짜,vod회차세부내용,)<br>
            3. 해당 VOD 전체 세부 내용<br>
            GET(v_id) -> res(vod이름(v_title),vod세부내용,vod장르 대분류/소분류,vod출연진,vod감독,)<br>
            4. 전체회차 탭<br>
            GET(v_id) -> res(vod 전체회차-회차최신순,날짜,vod회차세부내용)<br>
            5-1. 해당회차 베스트댓글 탭<br>
            GET(ve_id) -> res(좋아요순 vod회차 댓글정보(c_id,vod회차 댓글내용,댓글유저,댓글좋아요수,댓글생성시간))<br>
            5-2. 베스트 댓글 누르면 해당 시간대로 이동<br>
            GET(c_id) -> res(c_playtime)으로 이동???<br>
            6. 해당시간 댓글 보여주는 컴포넌트<br>
            GET(ve_id)-> res(댓글유저, 댓글내용, 좋아요수,댓글시간정보,playtime)<br>
        </div>
    </div>
</template>

<script>
import Header from '@/components/common/Header';
import Asidebar from '@/components/common/Asidebar';
export default {
  components: { 
      Header,
      Asidebar, 
    },
    name: 'VodDetail',

    data() {
        return {
            
        };
    },
    mounted() {
        
    },
    methods: {
        
    },
};
</script>

<style scoped>
    #appBody {
        display: inline-block;
        position: fixed;
        top: 100px;
        background-color: red;
        width: 100%;
    }
</style>