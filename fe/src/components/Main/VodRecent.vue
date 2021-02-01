<template>
    <div>
        <h1>최신순</h1>
        <div>{{ this.recentVODs }}</div>
    </div>
  
</template>

<script>
import { fetchRecentVOD } from '@/api/vod'

export default {

    data(){
        return {
            recentVODs:[]
        }
    },
    methods:{
        //getRecentVOD api만들어야함
        getRecentVOD() {
            this.recentVODs = fetchRecentVOD()
        }
    },
    created(){
        //최신순 vod get
        this.getRecentVOD()
    },


}
</script>

<style>

</style>