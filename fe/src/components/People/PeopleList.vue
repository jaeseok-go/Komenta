<template>
  <div>
    
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
body {
    background: #eee
}

.followers {
    font-size: 12px
}
</style>