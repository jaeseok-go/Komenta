<template>
    <div>
        <h1>댓글순</h1>
        <div>this.commentVODs 뿌려주기</div>
    </div>
  
</template>

<script>
export default {
    data(){
        return {
            commentVODs:[]
        }
    },
    // methods:{
    //     //getcommentVOD api만들어야함
    //     fetchCommentVOD() {
    //         this.commentVODs = getcommentVOD()
    //     }
    // },
    created(){
        //댓글순 vod get
        this.fetchCommentVOD()
    },

}
</script>

<style>

</style>