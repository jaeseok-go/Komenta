<template>
    <div>
        <h1>인기순</h1>
        <div>{{ this.popularVODs }}}}</div>
    </div>
  
</template>

<script>
import { fetchPopularVOD } from '@/api/vod'
export default {
    data(){
        return {
            popularVODs:[]
        }
    },
    methods:{
        getPopularVOD() {
            this.popularVODs = fetchPopularVOD()
        }
    },
    created(){
        //인기순 vod get
        this.getPopularVOD()
    },

}
</script>

<style>

</style>