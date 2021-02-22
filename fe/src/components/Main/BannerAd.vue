<template>
  <div>
    <div class="bannerAd-Form">
      <div class="banner">
        <vueper-slides fade :touchable="false" fixed-height="500px">
          <vueper-slide
            v-for="(slide, i) in slides"
            :key="i"
            :image="slide.image"
            :link="slide.link" />
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
          link: '/voddetail/72'
        },
        {
          title: '배너2',
          content: 'Photo by Max Rive',
          image: require('@/assets/images/루카.png'),
          link: '/voddetail/69'
        },
        {
          title: '배너3',
          content: 'Photo by Max Rive',
          image: require('@/assets/images/난리났네 난리났어.png'),
          link: '/voddetail/76'
        },
        {
          title: '배너4',
          content: 'Photo by Max Rive',
          image: require('@/assets/images/여신강림.png'),
          link: '/voddetail/74'
        }
      ],
    }
  },
}
</script>

<style>

</style>