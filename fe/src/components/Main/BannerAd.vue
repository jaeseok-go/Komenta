<template>
  <div>
    <!-- <h1>Banner 들어갈 자리</h1> -->
    <div class="bannerAd-Form">
      <div class="banner">
        <!-- <img src="http://img.wkorea.com/w/2020/12/style_5fdac1f775097.jpg"> -->
        <vueper-slides fade :touchable="false" fixed-height="500px">
          <vueper-slide
            v-for="(slide, i) in slides"
            :key="i"
            :image="slide.image" />
            <!-- :title="slide.title"
            :content="slide.content"  -->
          <!-- <template v-slot:content>
            <div class="vueperslide__content-wrapper" style="flex-direction: row">
              <i class="material-icons">access_time</i>
              <span>{{ slide.title }}</span>
            </div>
          </template> -->
        </vueper-slides>
      </div>
    </div>
  </div>
</template>

<script>
import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
export default {
  components: { VueperSlides, VueperSlide },
  data() {
    return {
      slides: [
        {
          title: '배너1',
          content: 'Photo by Max Rive',
          image: require('@/assets/images/윤스테이.png'),
          // link: 'https://www.maxrivephotography.com/index/C0000rU1RKCHdqwI/G0000X57AtIzuRX0/I0000Gvr9HqdtyXk'
        },
        {
          title: '배너2',
          content: 'Photo by Max Rive',
          image: require('@/assets/images/루카.png'),
          // link: 'https://www.maxrivephotography.com/index/C0000rU1RKCHdqwI/G0000X57AtIzuRX0/I0000Gvr9HqdtyXk'
        },
        {
          title: '배너3',
          content: 'Photo by Max Rive',
          image: require('@/assets/images/난리났네 난리났어.png'),
          // link: 'https://www.maxrivephotography.com/index/C0000rU1RKCHdqwI/G0000X57AtIzuRX0/I0000Gvr9HqdtyXk'
        },
        {
          title: '배너4',
          content: 'Photo by Max Rive',
          image: require('@/assets/images/여신강림.png'),
          // link: 'https://www.maxrivephotography.com/index/C0000rU1RKCHdqwI/G0000X57AtIzuRX0/I0000Gvr9HqdtyXk'
        }
      ],
    }
  },
}
</script>

<style>

</style>