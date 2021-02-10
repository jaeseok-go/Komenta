<template>
    <div>
        <RecentlyPlayList></RecentlyPlayList>
        <MyLikePlayList></MyLikePlayList>
        <!-- <PopularPlayList></PopularPlayList> -->
    </div>
</template>

<script>
import RecentlyPlayList from '@/components/MyPlayList/RecentlyPlayedList'
import MyLikePlayList from '@/components/MyPlayList/MyLikePlayList'
// import PopularPlayList from '@/components/MyPlayList/PopularPlayList'


export default {
    
    components: {
        RecentlyPlayList,
        MyLikePlayList,
        // PopularPlayList
    },

}
</script>


<style>

</style>