<template>
    <div>
        <h1>최신순</h1>
        <div>this.recentVODs 뿌려주기</div>
    </div>
  
</template>

<script>
export default {
    data(){
        return {
            recentVODs:[]
        }
    },
    methods:{
        //getRecentVOD api만들어야함
        fetchRecentVOD() {
            this.recentVODs = getRecentVOD()
        }
    },
    created(){
        //최신순 vod get
        this.fetchRecentVOD()
    },


}
</script>

<style>

</style>