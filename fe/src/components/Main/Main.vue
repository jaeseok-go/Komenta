<template>
    <div>
        <VODSections></VODSections>
        <div class="container">
		<!-- Top Navigation -->
		<div class="wrapper">
			<ul class="stage clearfix">

				<li class="scene" v-for="(vod,index) in vods" :key="vod.v_id">
					<div class="movie" onclick="return true">
						<div class="poster"><img :src="getPoster(index)" height="100%" width="100%" alt=""></div>
						<div class="info" @click="goVod(vod.v_id)">
							<header>
								<h1>{{vod.v_title}} {{vod.ve_episode_num}}회</h1>
								<span class="year">{{vod.ve_upload_date}}</span>
								<!-- <span class="rating">PG</span>
								<span class="duration">130 minutes</span> -->
							</header>
							<p>
								보러가기
							</p>
						</div>
					</div>
				</li>

			</ul>
		</div><!-- /wrapper -->
	</div><!-- /container -->

        <BestCommentRank></BestCommentRank>
        <NowPlaying></NowPlaying>
        <PopularPlayList></PopularPlayList>
    </div>
  
</template>

<script>
import BestCommentRank from '@/components/Main/BestCommentRank'
import NowPlaying from '@/components/Main/NowPlaying'
import PopularPlayList from '@/components/Main/PopularPlayList'
import VODSections from '@/components/Main/VODSections'

export default {
    data(){
        return {
            vods:[
                    {
                        "g_name": "드라마",
                        "gd_id": 0,
                        "gd_name": "호러",
                        "v_id": 0,
                        "v_title": "스위트홈",
                        "ve_admin": "string",
                        "ve_contents": "string",
                        "ve_episode_num": 1,
                        "ve_id": 0,
                        "ve_upload_date": "2021-02-06"
                    },
                    {
                        "g_name": "드라마",
                        "gd_id": 0,
                        "gd_name": "로맨스",
                        "v_id": 0,
                        "v_title": "사랑의불시착",
                        "ve_admin": "string",
                        "ve_contents": "string",
                        "ve_episode_num": 1,
                        "ve_id": 0,
                        "ve_upload_date": "2021-02-06"
                    },
                    {
                        "g_name": "예능",
                        "gd_id": 0,
                        "gd_name": "버라이어티",
                        "v_id": 0,
                        "v_title": "런닝맨",
                        "ve_admin": "string",
                        "ve_contents": "string",
                        "ve_episode_num": 550,
                        "ve_id": 0,
                        "ve_upload_date": "2021-02-06"
                    },
                    {
                        "g_name": "드라마",
                        "gd_id": 0,
                        "gd_name": "호러",
                        "v_id": 0,
                        "v_title": "스위트홈",
                        "ve_admin": "string",
                        "ve_contents": "string",
                        "ve_episode_num": 1,
                        "ve_id": 0,
                        "ve_upload_date": "2021-02-06"
                    },
                    {
                        "g_name": "드라마",
                        "gd_id": 0,
                        "gd_name": "로맨스",
                        "v_id": 0,
                        "v_title": "사랑의불시착",
                        "ve_admin": "string",
                        "ve_contents": "string",
                        "ve_episode_num": 1,
                        "ve_id": 0,
                        "ve_upload_date": "2021-02-06"
                    },
                    {
                        "g_name": "예능",
                        "gd_id": 0,
                        "gd_name": "버라이어티",
                        "v_id": 0,
                        "v_title": "런닝맨",
                        "ve_admin": "string",
                        "ve_contents": "string",
                        "ve_episode_num": 550,
                        "ve_id": 0,
                        "ve_upload_date": "2021-02-06"
                    }
                ]
        }
    },
    components: {
        // Header,
        // AsideBar,
        VODSections,
        BestCommentRank,
        NowPlaying,
        PopularPlayList
    },
    methods:{
        getPoster(poster) {
            console.log(poster)
            return require(`@/assets/images/poster0${poster}.jpg`)
            // return require(`@/assets/images/sweet_home.png`)
        },
        goVod(vId){
            this.$router.push(`/voddetail/${vId}`)
        }
    }

}

</script>

<style>
@import '../../../public/css/component.css';
@import '../../../public/css/demo.css';
@import '../../../public/css/normalize.css';

</style>