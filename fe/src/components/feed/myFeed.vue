<template>
    <div>
        <Header></Header>
        <Asidebar></Asidebar>
    해당 피드의 상세페이지 u_id가 일치하지 않으면 타유저피드보기, 일치하면 마이피드
    (피드로 들어가는법...? u_id??)
        <div>
            <!-- 1.유저 프로필 -->
            <!-- 나:팔로우버튼 안보임 너:팔로우버튼 보임 -->
            <div>
                {{ userInfo.u_profile_pic}}
                {{ userInfo.u_nickname }}
                <!-- 팔로우버튼은 '나'면 안보이고, 다른사람은 보임. 그리고 누르면 색깔과 함께 unfollow로 바뀜
                2. 팔로우할 수 있는 버튼(f_id는 해당 페이지를 들어올때 해당 피드의 userId를 같이 넘겨줌, 라우터 보낼때 parmas로 같이 보내면되지않을까)
                    POST(u_id,f_id) -> 팔로우 추가 / 취소 -->
                <button>FOLLOW</button>
            </div>

            <!-- 2.시청목록-->
            <!-- 로그인한 유저일때만 시청목록을 보여준다 -->
            <div>내가 시청한 콘텐츠 목록</div>
            <div>{{ this.fetchedPlaylist }}</div>

            <!-- <유저일때> -->
            <!-- 3.플레이리스트 폼 추가 -->
            <!-- POST(u_id,pl_name) 보냄 -> res(업데이트된 플레이리스트 정보)
            +버튼 누르면 플레이리스트 추가하는 모달나오게 함 -->
            <button @click="showModal">+</button>
            <Modal v-if="showModal" @close="showModal=false">
                <h3 slot="header">
                나만의 스트리밍 리스트를 만들어보세요
                <i class="closeModalBtn fa fa-times"
                aria-hidden="true"
                @click="showModal = false">
                </i>
                </h3>
                <p slot="body"><input type="text" v-model="pl_name"></p>
            </Modal>


        
 

        </div>


        <div>플레이리스트</div>
        <!-- 유저 -->
        2. 플레이리스트에 VOD추가

        내가 시청한 플레이리스트에서 추가버튼 드래그앤 드랍으로 끌어와서 추가
        POST(u_id,v_id,pl_id) 보냄 -> 해당 플레이리스트에 vod저장
        res(업데이트된 플레이리스트 정보,플레이리스트 이름(pl_name), 각 플레이리스트에 속한 컨텐츠 사진, 제목)

        3. 플레이리스트에 컨텐츠 클릭
        GET(u_id,pl_id)보냄 -> res(해당 컨텐츠 상세 정보,포스터(v_poster),컨텐츠이름(v_title),회차(ve_episode_num),컨텐츠리뷰정보(디비없음))
        모달로 표시하면서 플레이리스트 평을 적는 칸이 나온다
        4. 컨텐츠 리뷰 적기
        POST(u_id,pl_id,plc_id,리뷰내용(디비에없음)) 보냄 -> res(저장됐는지 확인)
        <!-- 타유저 -->
        5. 플레이리스트 좋아요
        POST(u_id,pl_id) 보냄 -> 유저정보에 좋아요한 플레이리스트 저장
        <!-- 유저,타유저 모두 -->
        6. 플레이리스트 제목 클릭 
        GET(u_id,pl_id) 보냄 -> res(pl_id,pl_name,u_id,u_nickname,u_profile_pic,v_poster,gd_id(장르대분류/소분류),ve_episode_num,해당유저플레이리스트 리뷰) 플레이리스트 상세 페이지로 이동(해당 플레이리스트 상세페이지 이동 가능?) 
        
        7-1. 플레이리스트 컨텐츠 클릭 -> 이건 해당 user의 vod_history에 없는데 어떻게 될까
        GET(u_id,pl_id,plc_id) 보냄 -> res(컨텐츠이름,회차정보,리뷰정보) 컨텐츠 모달 띄움
        7-2. 지금 시청하기 버튼 -> plc_id는 해당 VOD 회차의 id??? ve_id필요
        GET(plc_id) 보냄 -> 해당 VOD의 상세페이지로 이동
    </div>
</template>

<script>
// import { mapGetters } from 'vuex';
// import store from '@/stores/modules/user';
// import Header from '@/components/common/Header';
// import Asidebar from '@/components/common/Asidebar';
// import Modal from '@/components/common/Modal'

// export default {
//     name: 'myFeed',
//     components:{
//         Header,
//         Asidebar,
//         Modal

//     },
//     data() {
//         return {
//             userId:userInfo.u_id,
//             pl_name: '',
//             showModal:false,
//             userInfo: 
//         };
//     },

//     methods: {
//         showModal() {
//             showModal=true
//         },
//         addPlaylist() {
//             const userId = store.state.userInfo.u_id
//             const pl_name = this.pl_name
//             const data = {userId, pl_name}
//             this.$store.dispatch('ADD_PLAYLIST',data)
//         },


//     },
//     computed: {
//         ...mapGetters(['fetchedPlaylist']),
//         ...mapGetters(['userInfo'])
//     },
//     created() {
//         const userId = store.state.userInfo.u_id
//         // const userId = this.userInfo.u_id
//         this.$store.dispatch('FETCH_RECENTPLAYLIST',userId)
//     }
 
// };
</script>
