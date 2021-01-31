<template>
    <div>
        <h1>피플 누르면 사람들 쭉 나옴, 내가 팔로우 한 사람들만 나옴</h1>
        <h2>유저가 팔로우 하고 있는 사람들 목록</h2>
        <div>{{ this.fetchedFollowingList }}</div>
        <h2>팔로우하고 있는 사람이 없으면? 팔로워가 많은 사람들을 보여주는게 좋을듯(디폴트)</h2>
        
    </div>
  
</template>

<script>
import { mapGetters } from 'vuex'
import store from '@/stores/modules/user'

export default {
    computed: {
        ...mapGetters(['fetchedFollowingList'])
    },
    created() {
        const userId = store.state.userInfo.u_id
        this.$store.dispatch('FETCH_FOLLOWING',userId)
        //여기서 인기유저 갖고옴 -> 재석님 피드백 듣고 작업하면될듯
    }

}
</script>

<style>

</style>