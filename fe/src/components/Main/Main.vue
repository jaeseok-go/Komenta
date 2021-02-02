<template>
    <div>
        <VODSections></VODSections>
        <BestCommentRank></BestCommentRank>
        <NowPlaying></NowPlaying>
        <PopularPlayList></PopularPlayList>
    </div>
  
</template>

<script>
import BestCommentRank from '@/components/Main/BestCommentRank'
import NowPlaying from '@/components/Main/NowPlaying'
import PopularPlayList from '@/components/Main/PopularPlayList'
import VODSections from '@/components/Main/VODSections'

export default {
    components: {
        // Header,
        // AsideBar,
        VODSections,
        BestCommentRank,
        NowPlaying,
        PopularPlayList
    }

}
</script>

<style>

</style>