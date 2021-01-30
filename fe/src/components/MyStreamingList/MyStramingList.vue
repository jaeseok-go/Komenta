<template>
    <div>
        <h1>마이 스트리밍 리스트</h1>
        <Header></Header>
        <AsideBar></AsideBar>
        <div>Recently Played</div>
        <ul>
            <li v-for="recentVOD in recentVODS" :key="recentVOD.id"></li>
        </ul>
        
        <div> 좋아요 누른 스트리밍 리스트 </div>
        <!-- u_id => pl_id, pl_name, u_id(플레이리스트 작성자), pl_pic(플레이리스트 대표 사진), ve_id(플레이 리스트 각각 vod 에피소드 id) -->
        <ul>
            <li v-for="likePlayList in likePlayLists" :key="likePlayList.id"></li>
        </ul>

    </div>
  
</template>

<script>
import { mapState } from 'vuex';
import AsideBar from '@/components/common/Asidebar'
import Header from '@/components/common/Header'

export default {
    components: {
        AsideBar,
        Header
    },
    data() {
        return {
            recentVODs:[],
            likePlayLists:[],
        }
    },
    computed: {
        ...mapState({
            userInfo: state => state.user.userInfo
                }),
    },
    methods: {
        fetchRecentPlaylist() {
            const u_id = userInfo.u_id
            // const u_id = store.userInfo.u_id(helper method 안쓰고?)
            this.recentVODs = getRecentPlaylist(u_id)
        },
        fetchLikePlayList() {
            const u_id = userInfo.u_id
            this.likePlayLists = getLikePlayList(u_id)
        }

    },
    created() {
        this.fetchRecentPlaylist(),
        this.fetchLikePlayList()
    },

}
</script>


<style>

</style>