<template>
    <div>
        <!-- <h1>마이 스트리밍 리스트</h1> -->
        <!-- <ul>
            <div>{{ fetchedRecentPlaylist}}</div>
        </ul> -->
        
        <!-- <div> 좋아요 누른 스트리밍 리스트 </div> -->
        <!-- u_id => pl_id, pl_name, u_id(플레이리스트 작성자), pl_pic(플레이리스트 대표 사진), ve_id(플레이 리스트 각각 vod 에피소드 id) -->
        <!-- <ul>
            <li v-for="(likePlayList,index) in fetchedLikePlaylist" :key="index"></li>
        </ul> -->
        <RecentlyPlayList></RecentlyPlayList>
        <MyLikePlayList></MyLikePlayList>
    </div>
</template>

<script>
// import { mapState } from 'vuex';
// import { mapGetters } from 'vuex';
// import store from '@/stores/modules/user'
// import AsideBar from '@/components/common/Asidebar'
// import Header from '@/components/common/Header'
import RecentlyPlayList from '@/components/MyPlayList/RecentlyPlayedList'
import MyLikePlayList from '@/components/MyPlayList/MyLikePlayList'

export default {
    
    components: {
        // AsideBar,
        // Header
        RecentlyPlayList,
        MyLikePlayList
    },
    computed: {
    // ...mapGetters(['fetchedRecentPlaylist']),
    // ...mapGetters(['fetchedLikePlaylist']),
    // ...mapState({recentPlaylist: state => state.recentPlaylist['historyList']})
    },
    created() {
        // const userId = store.state.userInfo.u_id
        const userId = 1 
        console.log(userId, '유저아이디는 있나???')
        this.$store.dispatch('FETCH_RECENTPLAYLIST',userId)
        // this.$store.dispatch('FETCH_LIKEPLAYLIST',userId)
    },

}
</script>


<style>

</style>