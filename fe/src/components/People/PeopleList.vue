<template>
<div class="demo">
  <svg class="sidebar" viewBox="0 0 300 500">
    <path class="s-path" fill="#fff" d="M0,0 50,0 a0,250 0 1,1 0,500 L0,500" />
  </svg>
  <div class="static">
    <div class="static__text">Pull white sidebar to the right</div>
  </div>
  <div class="sidebar-content">
    <div class="contact">
      <img src="http://i.imgur.com/ae5r9VA.png" alt="" class="contact__photo" />
      <span class="contact__name">Ethan Hawke</span>
      <span class="contact__status online"></span>
    </div>
    <div class="contact">
      <img src="http://i.imgur.com/tnMJGyT.png" alt="" class="contact__photo" />
      <span class="contact__name">Natalie Portman</span>
      <span class="contact__status online"></span>
    </div>
    <div class="contact">
      <img src="http://i.imgur.com/ae5r9VA.png" alt="" class="contact__photo" />
      <span class="contact__name">Kevin Spacey</span>
      <span class="contact__status online"></span>
    </div>
    <div class="contact">
      <img src="http://i.imgur.com/tnMJGyT.png" alt="" class="contact__photo" />
      <span class="contact__name">Rosamund Pike</span>
      <span class="contact__status online"></span>
    </div>
    <div class="contact">
      <img src="http://i.imgur.com/ae5r9VA.png" alt="" class="contact__photo" />
      <span class="contact__name">Robert Redford</span>
      <span class="contact__status online"></span>
    </div>
    <div class="contact">
      <img src="http://i.imgur.com/tnMJGyT.png" alt="" class="contact__photo" />
      <span class="contact__name">Scarlett Johansson</span>
      <span class="contact__status online"></span>
    </div>
    <div class="contact">
      <img src="http://i.imgur.com/ae5r9VA.png" alt="" class="contact__photo" />
      <span class="contact__name">Tom Cruise</span>
      <span class="contact__status"></span>
    </div>
    <div class="contact">
      <img src="http://i.imgur.com/tnMJGyT.png" alt="" class="contact__photo" />
      <span class="contact__name">Eva Green</span>
      <span class="contact__status"></span>
    </div>
    <div class="contact">
      <img src="http://i.imgur.com/ae5r9VA.png" alt="" class="contact__photo" />
      <span class="contact__name">Paul Newman</span>
      <span class="contact__status"></span>
    </div>
    <div class="contact">
      <img src="http://i.imgur.com/tnMJGyT.png" alt="" class="contact__photo" />
      <span class="contact__name">Thomas Break</span>
      <span class="contact__status"></span>
    </div>
    <div class="search">
      <img src="http://i.imgur.com/t8TeL1S.png" alt="" class="search__img" />
      <input type="text" class="search__input" placeholder="Search" />
    </div>
  </div>
  <div class="chat">
    <span class="chat__back"></span>
    <span class="chat__status">status</span>
    <div class="chat__person">
      <span class="chat__online active"></span>
      <span class="chat__name">Huehue Huehue</span>
    </div>
    <div class="chat__messages">
      <div class="chat__msgRow">
        <div class="chat__message mine">Such SVG, much Javascript, very CSS!</div>
      </div>
      <div class="chat__msgRow">
        <div class="chat__message notMine">Wow!</div>
      </div>
      <div class="chat__msgRow">
        <div class="chat__message notMine">Very elastic! Such easings!</div>
      </div>
      <div class="chat__msgRow">
        <div class="chat__message mine">
          Check out my other <a href="http://codepen.io/suez/public/" target="_blank">pens</a>
        </div>
      </div>
    </div>
    <input type="text" class="chat__input" placeholder="Your message" />
  </div>
</div>
    <!-- <div>
        <h1>피플 누르면 사람들 쭉 나옴, 내가 팔로우 한 사람들만 나옴</h1>
        <h2>유저가 팔로우 하고 있는 사람들 목록</h2>
        <div>{{ this.fetchedFollowingList }}</div>
        <h2>팔로우하고 있는 사람이 없으면? 팔로워가 많은 사람들을 보여주는게 좋을듯(디폴트)</h2>
        
    </div> -->
  
</template>

<script>
import { mapGetters } from 'vuex'
import store from '@/stores/modules/user'

export default {
    computed: {
        ...mapGetters(['fetchedFollowingList'])
    },
    created() {
        const userId = store.state.userInfo.u_id
        this.$store.dispatch('FETCH_FOLLOWING',userId)
        //여기서 인기유저 갖고옴 -> 재석님 피드백 듣고 작업하면될듯
    }

}
</script>

<style>
body {
    background: #eee
}

.followers {
    font-size: 12px
}
</style>