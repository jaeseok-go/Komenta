<template>
    <div>
    <select v-model="selectedGenre">
        <option disabled value="">--장르선택--</option>
        <option v-for="genre in allGenres" :key="genre.g_id" :value="`${genre.g_id}`">{{genre.g_name}}</option>
    </select>
    <select v-model="selectedGenreDetail">
        <option disabled value="">--세부 장르선택--</option>
        <option v-for="genrdetail in genreDetails[selectedGenre]" :key="genrdetail.gd_id" :value="`${genrdetail.gd_id}`">{{genrdetail.gd_name}}</option>
    </select>
    <div v-for='vod in vodlists' :key="vod.v_id">
    <span @click="goVodDetail(vod.v_id)">{{ vod.v_poster }}</span>
    </div>
    </div>
</template>

<script>
// import {fetchAllGenre,fetchMainGenreVod, fetchSubGenreVod, fetchVodDetail} from '@/api/vod'

export default {
    name: 'Category',
    data() {
        return {
            selectedGenre:'',
            selectedGenreDetail:'',
            allGenres :[
            {
                "g_id": 1,
                "g_name": "드라마"
            },
            {
                "g_id": 2,
                "g_name": "예능"
            },
            {
                "g_id": 3,
                "g_name": "다큐"
            },
            {
                "g_id": 4,
                "g_name": "스포츠"
            },
            {
                "g_id": 5,
                "g_name": "애니메이션"
            }
            ],
            vodlists:[
                [
                {
                    "g_name": "드라마",
                    "gd_id": 0,
                    "gd_name": "멜로",
                    "v_actors": "string",
                    "v_age_grade": 0,
                    "v_director": "string",
                    "v_id": 0,
                    "v_poster": "string",
                    "v_summary": "string",
                    "v_title": "string"
                }
                ]
            ]
        };
    },
    created(){
        // this.getAllGenre();
    },
    methods: {
        getAllGenre() {
            // this.allGenres = fetchAllGenre();
        },
        getGenreDetail(gId) {
            // this.genreDetails = fetchMainGenreVod(gId);
            // this.vodlists = fetchMainGenreVod(gId);
            console.log(gId,'장르내용')
        },
        getVodlist(gdId) {
            // this.vodlists = fetchSubGenreVod(gdId);
            console.log(gdId,'장르세부내용')
        },
        async goVodDetail(vId){
            console.log(vId,'vod가장 첫 epi로 이동')
            // const res = await fetchVodDetail(vId)
            // VOD의 가장 첫 epi로 보내기
            // this.$router.push(`/voddetail/${res.data[0].ve_id}`)
        },
    },
    watch:{
        selectedGenre : function(){
            console.log(this.selectedGenre)
            this.getGenreDetail(this.selectedGenre)
        },
        selectedGenreDetail:function(){
            console.log(this.selectedGenreDetail)
            this.getVodlist(this.selectedGenreDetail)
        }
    },
};
</script>

<style lang="scss" scoped>

</style>