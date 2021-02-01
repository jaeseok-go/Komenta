<template>
    <div>
        <h1>마이 스트리밍 리스트</h1>
        <Header></Header>
        <AsideBar></AsideBar>
        <div>Recently Played</div>
        <ul>
            <li v-for="recentVOD in fetchedRecentPlaylist" :key="recentVOD.id"></li>
        </ul>
        
        <div> 좋아요 누른 스트리밍 리스트 </div>
        <!-- u_id => pl_id, pl_name, u_id(플레이리스트 작성자), pl_pic(플레이리스트 대표 사진), ve_id(플레이 리스트 각각 vod 에피소드 id) -->
        <ul>
            <li v-for="likePlayList in fetchedLikePlaylist" :key="likePlayList.id"></li>
        </ul>

    </div>
  
</template>

<script>
// import { mapState } from 'vuex';
import { mapGetters } from 'vuex';
import store from '@/stores/modules/user'
import AsideBar from '@/components/common/Asidebar'
import Header from '@/components/common/Header'

export default {
    components: {
        AsideBar,
        Header
    },
    computed: {
    ...mapGetters(['fetchedRecentPlaylist']),
    ...mapGetters(['fetchedLikePlaylist']),
    },
    created() {
        const userId = store.state.userInfo.u_id
        this.$store.dispatch('FETCH_RECENTPLAYLIST',userId)
        this.$store.dispatch('FETCH_LIKEPLAYLIST',userId)
    },

}
</script>


<style>

</style>