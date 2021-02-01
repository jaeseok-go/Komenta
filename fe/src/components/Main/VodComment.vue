<template>
    <div>
        <h1>댓글순</h1>
        <div>{{this.commentVODs }}}}</div>
    </div>
  
</template>

<script>
import { fetchCommentVOD } from '@/api/vod'
export default {
    data(){
        return {
            commentVODs:[]
        }
    },
    methods:{
        //fetchCommentVOD api만들어야함
        getcommentVOD() {
            this.commentVODs = fetchCommentVOD()
        }
        
    },
    created(){
        //댓글순 vod get
        this.getcommentVOD()
    },

}
</script>

<style>

</style>