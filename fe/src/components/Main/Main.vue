<template>
    <div>
        <BestCommentRank></BestCommentRank>
        <NowPlaying></NowPlaying>
        <PopularPlayList></PopularPlayList>
        <VODSections></VODSections>
    </div>
  
</template>

<script>
import BestCommentRank from '@/components/Main/BestCommentRank'
import NowPlaying from '@/components/Main/NowPlaying'
import PopularPlayList from '@/components/Main/PopularPlayList'
import VODSections from '@/components/Main/VODSections'

export default {
    components: {
        VODSections,
        BestCommentRank,
        NowPlaying,
        PopularPlayList
    }

}
</script>

<style>

</style>