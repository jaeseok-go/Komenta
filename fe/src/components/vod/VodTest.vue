<template>
    <div >
        <div class="container">
            <video height="500px" controls src="@/assets/videos/sweethome.mp4"></video>
            <!-- <my-video :sources="video.sources" :options="video.options"></my-video> -->
            <!-- <Video :sources="video.sources" :options="video.options"></Video> -->
        </div>
    </div>
</template>

<script>
// import myVideo from 'vue-video'
// import Video from '@/components/vod/Video'

export default {
    data () {
        return {
            video: {
                sources: [{
                    src: 'https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm',
                    // src: '@/assets/videos/sweethome.mp4',
                    type: 'video/mp4'
                }],
                options: {
                    autoplay: true,
                    volume: 0.6,
                    poster: 'http://covteam.u.qiniudn.com/poster.png'
                }
            }
        }
    },
    components: {
        // myVideo,
        // Video
    }
}
</script>