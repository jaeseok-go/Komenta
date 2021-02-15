<template>
  <div>
    <!-- 클릭 => 영상 나오기 -->
    <div class="wrapper">
      <button class="wrapper_button" @click="gotoLogin">LOGIN</button>
      <input type="checkbox">
      <div class="video">
        <video src="@/assets/videos/about.mp4" loop muted autoplay></video>
        <!-- <video src="@/assets/videos/about.mp4" width="40%" loop autoplay muted></video> -->
        <!-- <video src="https://www.robmillsarchitects.com/files/land/city/RMA_Web_land_city_1.mp4" loop muted autoplay></video> -->
      </div>
      <div class="text">
        <span data-text="ABOUT KOMENTA"></span>
      </div>
      <a href="#section2"><div class="text__scroll"><i class="fas fa-chevron-down"></i></div></a>
    </div>

    <!-- 2 -->
  <form id="section2">
  <input type="radio" name="tab" id="menu" checked="checked"/>
  <div class="container">
    <input type="radio" name="tab" id="home"/>
    <section class="home">
      <h1>Click</h1>
      <label for="home"></label>
      <div class="desc">
        <div class="home__desc__logo"><img src="@/assets/images/KOMENTA_logo.png" alt=""></div>
        <div class="home__desc__title">세상에 없었던 인터렉티브 VOD 서비스</div>
        <div class="home__desc__subtitle">카드를 넘겨보면서 KOMENTA에 대해 더 알아보세요!</div>
        <div class="home__desc__login">무료 체험하기</div>
      </div>
    </section>
    <input type="radio" name="tab" id="about"/>
    <section class="about">
      <!-- <h1>About</h1> -->
      <label for="about"></label>
      <div class="desc">
          <span class="desc__2__button">NEW</span>
          <div class="desc__2__title"><span>Komenta</span>의 'Drag and Drop 플레이리스트'</div>
          <div class="desc__2__subtitle">나만의 플레이 리스트를 만들고, "드래그 앤 드롭" 기능으로 손쉽게 플레이리스트를 관리해보세요.</div>
          <div class="desc__2__video"><video src="@/assets/videos/playlist.mp4" width="50%" autoplay muted loop></video></div>
      </div>
    </section>
    <input type="radio" name="tab" id="work"/>
    <section class="work">
      <!-- <h1>Work</h1> -->
      <label for="work"></label>
      <p>하이하이</p>
      <video src="@/assets/videos/about.mp4" width="50%" loop autoplay muted></video>
    </section>
    <input type="radio" name="tab" id="contact"/>
    <section class="contact">
      <h1>Contact</h1>
      <label for="contact"></label>
    </section>
  </div>
  <div class="menu">
    <div>
      <label for="menu"></label>
      <label for="home"></label>
    </div>
  </div>
</form>
<AboutFooter class="footer__margin" :class="{asideOn: isLogin}"></AboutFooter>
</div>
      
</template>

<script>
import AboutFooter from '@/components/common/AboutFooter'
export default {
  components:{
    AboutFooter
  },
  methods: {
    gotoLogin() {
      this.$router.push(`/member/login`)
    },
    gotoBack() {
      const menu = document.getElementById('menu')
      menu.checked = true;
    }
  }
  
}
</script>

<style scoped>
button {
  position: absolute;
  top: 1rem;
  right: 1rem;
}
.wrapper {
  --color: #fa233b;
  --color-invert: #fa233b;
  --clip-path: circle(100px at left);
  --clip-path-hover: circle(130px at left);
  --clip-path-clicked: circle(100vw at left);
  --duration: .4s;
  --timing-function: ease;
  position: relative;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
      -ms-flex-pack: center;
          justify-content: center;
  -webkit-box-align: center;
      -ms-flex-align: center;
          align-items: center;
  width: 100%;
}

.wrapper .video {
  height: 100vh;
  overflow: hidden;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
      -ms-flex-pack: center;
          justify-content: center;
  -webkit-box-align: center;
      -ms-flex-align: center;
          align-items: center;
  margin-left: 15px;
  -webkit-clip-path: var(--clip-path);
          clip-path: var(--clip-path);
  -webkit-transition: -webkit-clip-path var(--duration) var(--timing-function);
  transition: -webkit-clip-path var(--duration) var(--timing-function);
  transition: clip-path var(--duration) var(--timing-function);
  transition: clip-path var(--duration) var(--timing-function), -webkit-clip-path var(--duration) var(--timing-function);
}

.wrapper .video video {
  position: fixed;
  background: #c4cbde;
  top: 50%;
  left: 50%;
  min-width: 100%;
  min-height: 100%;
  width: auto;
  height: auto;
  -webkit-transform: translate(-50%, -50%);
          transform: translate(-50%, -50%);
}

.wrapper .text {
  position: relative;
  text-transform: uppercase;
  font-size: 5rem;
  font-weight: 600;
  letter-spacing: .2px;
  opacity: var(--opacity, 1);
  -webkit-transition: opacity 0.3s var(--timing-function) 0.2s;
  transition: opacity 0.3s var(--timing-function) 0.2s;
}

.wrapper .text::before, .wrapper .text::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: -1px;
  right: 25px;
  height: 0;
}

.wrapper .text::before {
  -webkit-box-shadow: 26px 0 0 1px var(--color);
          box-shadow: 26px 0 0 1px var(--color);
  right: var(--r, 100%);
  opacity: var(--opacity, 0);
  -webkit-transition: right .5s ease-in, opacity .1s linear;
  transition: right .5s ease-in, opacity .1s linear;
}

.wrapper .text::after {
  -webkit-box-shadow: 26px 0 0 1px var(--color-invert);
          box-shadow: 26px 0 0 1px var(--color-invert);
  -webkit-clip-path: var(--clip-path);
          clip-path: var(--clip-path);
  -webkit-transition: -webkit-clip-path var(--duration) var(--timing-function);
  transition: -webkit-clip-path var(--duration) var(--timing-function);
  transition: clip-path var(--duration) var(--timing-function);
  transition: clip-path var(--duration) var(--timing-function), -webkit-clip-path var(--duration) var(--timing-function);
}

.wrapper .text > span::before, .wrapper .text > span::after {
  content: attr(data-text);
  padding-left: 26px;
}

.wrapper .text > span::before {
  color: var(--color);
  font-weight: 700;
}

.wrapper .text > span::after {
  color: var(--color-invert);
  -webkit-clip-path: var(--clip-path);
          clip-path: var(--clip-path);
  -webkit-transition: -webkit-clip-path var(--duration) var(--timing-function);
  transition: -webkit-clip-path var(--duration) var(--timing-function);
  transition: clip-path var(--duration) var(--timing-function);
  transition: clip-path var(--duration) var(--timing-function), -webkit-clip-path var(--duration) var(--timing-function);
  position: absolute;
  left: 0;
}

.wrapper input {
  width: 1000px;
  height: 40px;
  margin: auto;
  position: absolute;
  left: 0;
  right: 0;
  border-radius: 40px;
  z-index: 2;
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  cursor: pointer;
}

.wrapper input:focus {
  outline: 0;
}

.wrapper input:hover ~ .video {
  -webkit-clip-path: var(--clip-path-hover);
          clip-path: var(--clip-path-hover);
}

.wrapper input:hover ~ .text::before {
  --r: 25px;
  --opacity: 1;
}

.wrapper input:hover ~ .text::after {
  -webkit-clip-path: var(--clip-path-hover);
          clip-path: var(--clip-path-hover);
}

.wrapper input:hover ~ .text > span::after {
  -webkit-clip-path: var(--clip-path-hover);
          clip-path: var(--clip-path-hover);
}

.wrapper input:checked {
  width: 100%;
  height: 100%;
  border-radius: 0;
}

.wrapper input:checked ~ .video {
  -webkit-clip-path: var(--clip-path-clicked);
          clip-path: var(--clip-path-clicked);
}

.wrapper input:checked ~ .text {
  --opacity: 0;
  -webkit-transition: opacity 0.3s var(--timing-function);
  transition: opacity 0.3s var(--timing-function);
}

.text__scroll {
  color: #A8A8A8	;
  position: absolute;
  bottom: 0;
  display: flex;
  font-size: 5rem;
  left: 50%;
  transform: translateX(-50px);
  opacity: var(--opacity, 1);
  -webkit-transition: opacity 0.3s ease 0.2s;
  transition: opacity 0.3s ease 0.2s;

}

.wrapper input:checked ~  a > .text__scroll {
  --opacity: 0;
  -webkit-transition: opacity 0.3s var(--timing-function);
  transition: opacity 0.3s var(--timing-function);
}

.wrapper input:checked ~ .text::after {
  -webkit-clip-path: var(--clip-path-clicked);
          clip-path: var(--clip-path-clicked);
}

.wrapper input:checked ~ .text > span::after {
  -webkit-clip-path: var(--clip-path-clicked);
          clip-path: var(--clip-path-clicked);
}
/*# sourceMappingURL=index.css.map */

/* section2 */

body {
	margin: 0;
	padding: 0;
	font-weight: 300;
	font-family: 'Raleway', sans-serif;
	background: #fff;
	background: radial-gradient(ellipse at center, rgba(0,0,0,0) 0%, rgba(0,0,0,0.05) 80%, rgba(0,0,0,0.15) 100%);
	overflow: hidden;
	transform-style: preserve-3d;
}

input[type='radio'],
.hide {
	display: none;
}

input[type='radio']:checked + section {
	/* top: 0;
   */
   top: -3rem;
  left: -1rem;
}

.container {
	width: 100%;
	height: 100vh;
	transition: all 500ms;
	transform-style: preserve-3d;
}

.container section {
	position: absolute;
	top: 100vh;
	width: 100%;
	height: 100vh;
	transition: top 500ms;
	transform-style: preserve-3d;
}

.container section.home {
  background: white;
}

.container section.about {
  background: white;

}

.container section.work {
  background: white;

}

.container section.contact {
  background: white;

}

.container section h1 {
	margin: 0;
	font-size: 64px;
	transform: translate(0, 40vh);
	text-align: center;
	/* color: rgba(255,255,255,0.85); */
  color: black;
	transition: transform 500ms;
	transform-style: preserve-3d;
}

.container section label {
	display: block;
	position: absolute;
	top: 0;
	width: 100%;
	height: 100vh;
}

.menu {
	display: block;
	position: fixed;
	top: 20px;
	left: 25px;
  /* 아래꺼 크게 해서 어쩌고하면 적용될듯 */
  /* top: 50%; */
  /* left: 50%; */
	z-index: 1000;
	transform: 0deg;
	transition: all 200ms;
	width: 35px;
	height: 4px;
	background: rgba(0,0,0,0.25);
}

.menu::before,
.menu::after {
	content: '';
	display: block;
	position: absolute;
	width: 35px;
	height: 4px;
	background: rgba(0,0,0,0.25);
	transition: all 200ms;
}

.menu::before {
	top: 10px;
}

.menu::after {
	top: 20px;
}

.menu:hover {
	background: #fff;
}

.menu:hover::before,
.menu:hover::after {
	background: #fff;
}

.menu div,
.menu div label:nth-child(1) {
	display: block;
	width: 35px;
	height: 35px;
	position: absolute;
	top: -6px;
}

.menu div label:nth-child(2) {
	display: none;
}

#menu:checked + .container {
	transform: scale(0.65) translateY(-18%);
	transition: all 500ms;
	transform-style: preserve-3d;
}

#menu:checked + .container section {
	cursor: pointer;
	overflow: hidden;
	opacity: 1;
	box-shadow: 0 0 40px rgba(0,0,0,0.25);
	transform-style: preserve-3d;
}

#menu:checked + .container section h1 {
	transform: scale(0.5);
	transform-style: preserve-3d;
}

#menu:checked + .container + .menu {
	top: 30px;
	left: 20px;
	transform: rotate(225deg);
	transition: all 200ms;
	transform-origin: center center;
	background: #646464;
}

#menu:checked + .container + .menu::before,
#menu:checked + .container + .menu::after {
	background: #646464;
	transition: all 200ms;
}

#menu:checked + .container + .menu::before {
	opacity: 0;
	transition: opacity 0s;
}

#menu:checked + .container + .menu::after {
	margin-top: -20px;
	transform: rotate(90deg);
}

#menu:checked + .container + .menu div label:nth-child(2) {
	display: block;
	width: 35px;
	height: 35px;
	position: absolute;
	top: -6px;
}

#menu:checked + .container + .menu div label:nth-child(1) {
	display: none;
}

.container section.home {
	z-index: 99;
}

.container section.about {
	z-index: 98;
}

.container section.work {
	z-index: 97;
}

.container section.contact {
	z-index: 96;
}

#menu:checked + .container section.home {
	top: 192px;
}

#menu:checked + .container section.home:hover {
	top: 188px;
}

#menu:checked + .container section.about {
	top: 132px;
}

#menu:checked + .container section.about:hover {
	top: 128px;
}

#menu:checked + .container section.work {
	top: 72px;
}

#menu:checked + .container section.work:hover {
	top: 68px;
}

#menu:checked + .container section.contact {
	top: 12px;
}

#menu:checked + .container section.contact:hover {
	top: 8px;
}

/* 3 */

</style>

