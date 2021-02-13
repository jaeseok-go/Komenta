<template>
    <div>
    <label for="mainGenre">장르</label>
    <select v-model="selectedGenre" id="mainGenre">
        <option disabled value="">--장르선택--</option>
        <option v-for="genre in allGenres" :key="genre.g_id" :value="`${genre.g_id}`">{{genre.g_name}}</option>
    </select>
    <label for="subGenre">세부장르</label>
    <select v-model="selectedGenreDetail" id="subGenre">
        <option disabled value="">--세부 장르선택--</option>
        <option v-for="genrdetail in subGenres" :key="genrdetail.gd_id" :value="`${genrdetail.gd_id}`">{{genrdetail.gd_name}}</option>
    </select>
    <div v-for='vod in vodlists' :key="vod.v_id">
    <span @click="goVodDetail(vod.v_id)"><img :src="getPoster(vod.v_poster)" alt="" height="200px"></span>
    </div>
    </div>
</template>

<script>
import {fetchVodList,fetchAllGenre,fetchGenreDetail,fetchMainGenreVod, fetchSubGenreVod, fetchVodDetail} from '@/api/vod'

export default {
    name: 'Category',
    data() {
        return {
            selectedGenre:'',
            selectedGenreDetail:'',
            allGenres :[],
            vodlists:[],
            subGenres:[],
        };
    },
    created(){
        this.getAllVOD();
        this.getMainGenre();
        //default 모든 VOD 조회
    },
    methods: {
        async getAllVOD(){
            const response = await fetchVodList();
            console.log("all vod : ",response);
            this.vodlists = response.data;
        },
        async getMainGenre() {
            const genres = await fetchAllGenre();
            this.allGenres = genres.data
            console.log(this.allGenres,'대분류장르')
        },
        async getMainGenreDetail(gId) {
            const sub = await fetchGenreDetail(gId);
            this.subGenres = sub.data
            const vod = await fetchMainGenreVod(gId);
            this.vodlists = vod.data
            console.log(gId,'대분류 장르 vod',this.vodlists)
        },
        async getSubGenre(gdId) {
            const vod = await fetchSubGenreVod(gdId);
            this.vodlists = vod.data
            console.log(gdId,'장르세부vod')
        },
        async goVodDetail(vId){
            console.log(vId,'vod가장 첫 epi로 이동')
            const res = await fetchVodDetail(vId)
            // VOD의 가장 첫 epi로 보내기
            this.$router.push(`/voddetail/${res.data[0].ve_id}`)
        },
        getPoster(path){
            return `${process.env.VUE_APP_PICTURE}poster/${path}`
        }
    },
    watch:{
        selectedGenre : function(){
            console.log(this.selectedGenre,'대분류 장르 선택')
            this.getMainGenreDetail(this.selectedGenre)
        },
        selectedGenreDetail:function(){
            console.log(this.selectedGenreDetail,'소분류 장르 선택')
            this.getSubGenre(this.selectedGenreDetail)
        }
    },
};
</script>

<style scoped>
label {
    font-weight: bold;
}

</style>