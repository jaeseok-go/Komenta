<template>
    <div >
        <div>
            <video height="500px" controls autoplay src="@/assets/videos/sweethome.mp4" ></video>
            <!-- <video height="500px" controls src="@/assets/videos/sweethome.mp4"></video> -->
        </div>
    </div>
</template>

<script>

export default {
    data () {
        return {
            video: {
                sources: [{
                    // src: 'https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm',
                    src: '@/assets/videos/sweethome.mp4',
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
    methods:{
      changeSrc(){
        const vod = this.$refs.myvideo
        console.log(vod)
        vod.src = `'@/assets/videos/sweethome.mp4`
      }
    },

}
</script>