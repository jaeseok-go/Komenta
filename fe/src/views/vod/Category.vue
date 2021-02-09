<template>
    <div>
    <select v-model="selectedGenre">
        <option disabled value="">--장르선택--</option>
        <option v-for="genre in allGenres" :key="genre.g_id" :value="`${genre.g_id}`">{{genre.g_name}}</option>
    </select>
    <select v-model="selectedGenreDetail">
        <option disabled value="">--세부 장르선택--</option>
        <option v-for="genrdetail in subGenres" :key="genrdetail.gd_id" :value="`${genrdetail.gd_id}`">{{genrdetail.gd_name}}</option>
    </select>
    <div v-for='vod in vodlists' :key="vod.v_id">
    <span @click="goVodDetail(vod.v_id)">{{ vod.v_poster }}</span>
    </div>
    </div>
</template>

<script>
import {fetchAllGenre,fetchMainGenreVod, fetchSubGenreVod, fetchVodDetail} from '@/api/vod'

export default {
    name: 'Category',
    data() {
        return {
            selectedGenre:'',
            selectedGenreDetail:'',
            allGenres :[],
            vodlists:[],
            subGenres:[],
            genreDetails:[[
            {
                "gd_id": 1,
                "gd_name": "멜로",
                "g_id": 1
            },
            {
                "gd_id": 2,
                "gd_name": "스릴러",
                "g_id": 1
            },
            {
                "gd_id": 3,
                "gd_name": "코미디",
                "g_id": 1
            },
            {
                "gd_id": 4,
                "gd_name": "액션",
                "g_id": 1
            }]
            ,
            [{
                "gd_id": 5,
                "gd_name": "음악",
                "g_id": 2
            },
            {
                "gd_id": 6,
                "gd_name": "버라이어티",
                "g_id": 2
            },
            {
                "gd_id": 7,
                "gd_name": "토크",
                "g_id": 2
            },
            {
                "gd_id": 8,
                "gd_name": "요리",
                "g_id": 2
            }]
            ,

            [{
                "gd_id": 9,
                "gd_name": "환경",
                "g_id": 3
            },
            {
                "gd_id": 10,
                "gd_name": "역사",
                "g_id": 3
            },
            {
                "gd_id": 11,
                "gd_name": "휴먼",
                "g_id": 3
            }]
            ,

            [{
                "gd_id": 12,
                "gd_name": "축구",
                "g_id": 4
            },
            {
                "gd_id": 13,
                "gd_name": "농구",
                "g_id": 4
            },
            {
                "gd_id": 14,
                "gd_name": "배구",
                "g_id": 4
            },
            {
                "gd_id": 15,
                "gd_name": "야구",
                "g_id": 4
            }]
            ,
            [{
                "gd_id": 16,
                "gd_name": "로맨스/순정",
                "g_id": 5
            },
            {
                "gd_id": 17,
                "gd_name": "스포츠",
                "g_id": 5
            },
            {
                "gd_id": 18,
                "gd_name": "액션/추리",
                "g_id": 5
            },
            {
                "gd_id": 19,
                "gd_name": "코미디",
                "g_id": 5
            }]

            ]
        };
    },
    created(){
        this.getAllGenre();
    },
    methods: {
        async getAllGenre() {
            const genres = await fetchAllGenre();
            this.allGenres = genres.data
            console.log(this.allGenres,'모든장르')
        },
        async getGenreDetail(gId) {
            for (let i = 0; i < this.genreDetails.length; i++) {
                const subgenre = this.genreDetails[i];
                if (subgenre[0].g_id == gId) {
                    this.subGenres = subgenre
                }
            }
            const vod = await fetchMainGenreVod(gId);
            this.vodlists = vod.data
            console.log(gId,'장르내용',this.genreDetails, this.vodlists)
        },
        async getVodlist(gdId) {
            const vod = await fetchSubGenreVod(gdId);
            this.vodlists = vod.data
            console.log(gdId,'장르세부내용')
        },
        async goVodDetail(vId){
            console.log(vId,'vod가장 첫 epi로 이동')
            const res = await fetchVodDetail(vId)
            // VOD의 가장 첫 epi로 보내기
            this.$router.push(`/voddetail/${res.data[0].ve_id}`)
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