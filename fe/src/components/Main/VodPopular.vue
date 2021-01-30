<template>
    <div>
        <h1>인기순</h1>
        <div>this.popularVODs 뿌려주기</div>
    </div>
  
</template>

<script>
export default {
    data(){
        return {
            popularVODs:[]
        }
    },
    // methods:{
    //     //getPopularVOD api만들어야함
    //     fetchPopularVOD() {
    //         this.popularVODs = getPopularVOD()
    //     }
    // },
    created(){
        //인기순 vod get
        this.fetchPopularVOD()
    },

}
</script>

<style>

</style>