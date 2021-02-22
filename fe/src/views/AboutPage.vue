<template>
  <div>
    <!-- 클릭 => 영상 나오기 -->
    <div class="wrapper">
      <button class="wrapper_button bounce" @click="gotoLogin">LOGIN</button>
      <input type="checkbox">
      <div class="video">
        <video src="@/assets/videos/intro.mp4" loop muted autoplay></video>
      </div>
      <div class="text">
        <svg viewBox="0 0 500 500" class="svg">
          <path id="curve" fill="transparent" d="M100,250 C100,100 400,100 400,250" />
            <text width="50" >
              <textPath xlink:href="#curve" style="font-size:1.7rem" class="clickme_text">
                Click Me!
              </textPath>
            </text>
        </svg>
        <span data-text="ABOUT KOMENTA"></span>
      </div>
      <a href="#section2"><div class="text__scroll animated bounce animate__animated animate__fadeInUp "><i class="fas fa-chevron-down"></i></div></a>
    </div>

    <!-- 2 -->
  <form id="section2" >
  <input type="radio" name="tab" id="menu" checked="checked" class="goback goMenu"/>
  <div class="container">
    <input type="radio" name="tab" id="home" class="goback" @click="onlyOne('home')"/>
    <section class="home" @click="gotoSmall('home')">
      <label for="home"></label>
      <div class="desc">
        <div class="home__desc__logo"><img src="@/assets/images/KOMENTA_logo.png" alt=""></div>
        <div class="home__desc__title">세상에 없었던 인터렉티브 VOD 서비스</div>
        <div class="home__desc__subtitle">잠깐! 로그인을 하시기전에 <span>"#해시태그"</span>를 클릭해서 KOMENTA를 미리 즐겨보세요<i class="fas fa-arrow-alt-circle-up"></i></div>
        <div class="home__desc__login"  @click="gotoLogin">GO TO LOGIN</div>
      </div>
    </section>
    <input type="radio" name="tab" id="about" class="goback" @click="onlyOne('about')"/>
    <section class="about" @click="gotoSmall('about')">
      <button class="clickme_button">#Playlist</button>
      <label for="about"></label>
      <div class="desc">
          <span class="desc__2__button">NEW</span>
          <div class="desc__2__title"><span>Komenta</span>의 'Drag and Drop 플레이리스트'</div>
          <div class="desc__2__subtitle">나만의 플레이 리스트를 만들고, "드래그 앤 드롭" 기능으로 손쉽게 플레이리스트를 관리해보세요.</div>
          <div class="desc__2__video"><video src="@/assets/videos/playlist.mp4" width="50%" autoplay muted loop></video></div>
      </div>
    </section>
    <input type="radio" name="tab" id="work" class="goback" @click="onlyOne('work')" />
    <section class="work" @click="gotoSmall('work')">
      <button class="clickme_button clickme_button_comment">#Comment</button>
      <label for="work"></label>
      <div class="desc">
        <span class="desc__2__button">NEW</span>
        <div class="desc__3__title">실시간으로 나오는 댓글에 좋아요를 누르고 <span>베스트 댓글</span>을 함께 만들어나가보세요</div>
        <div class="desc__3__subtitle">언제 어느때 보든지 <span>실시간</span>으로 보는 것 같은 생생한 VOD<br>친구들을 <span>팔로우</span>하고, 친구의 댓글을 <span>하이라이트</span>하고 마음에 안드는 댓글은 그 자리에서 <span>차단</span>까지!<br><span>베스트 댓글 탭</span>에서 레전드 댓글을 확인해보세요
        <br><span>타임스탬프</span> 기능으로 댓글을 클릭하면 <span>해당 구간으로 이동</span>해서 더욱 편안한 감상을 즐겨보세요
        </div>
        <div class="desc__3__video"><video src="@/assets/videos/comment.mp4" width="50%" autoplay muted loop></video></div>
      </div>
    </section>
    <input type="radio" name="tab" id="contact" class="goback" @click="onlyOne('contact')"/>
    <section class="contact" @click="gotoSmall('contact')">
      <button class="clickme_button" >#Interest</button>
      <label for="contact"></label>
      <div class="desc">
        <span class="desc__2__button">NEW</span>
        <div class="desc__4__title"><span>취향</span>에 맞는 플레이리스트를 즐겨찾기하고, 편하게 찾아보세요</div>
        <div class="desc__4__subtitle"><span>플레이리스트</span>에서 VOD를 확인하고, 그 자리에서 바로 시청까지 한번에<br>친구의 <span>업데이트</span>된 플레이리스트는 <span>People</span>에서 손쉽게 확인해보세요</div>
        <div class="desc__4__video"><video src="@/assets/videos/interest.mp4" width="50%" autoplay muted loop></video></div>
      </div>
    </section>
  </div>
</form>
<footer>
<AboutFooter class="footer__margin" :class="{asideOn: isLogin}"></AboutFooter>

</footer>
</div>
      
</template>

<script>
import AboutFooter from '@/components/common/AboutFooter'
export default {
  components:{
    AboutFooter
  },
  data() {
    return {
      classList : ['home','about','contact','work']
    }
  },
  methods: {
    onlyOne(id) {
      for (let index = 0; index < this.classList.length; index++) {
        const element =  this.classList[index];
        
        if (id !== element ) {
          const another = document.querySelector(`.${element}`)
          console.log(another,id,'언아더')
          another.style.display = 'none';
        }
        
      }
    },
    
    
    gotoLogin() {
      this.$router.push(`/member/login`)
    },
    gotoSmall(id) {
      const INPUT = document.querySelectorAll('.goback')
      const MENU = document.querySelector('.goMenu')
      console.log(INPUT,'DLSVDSDS')
      for (let index = 0; index < INPUT.length; index++) {
        const element = INPUT[index];
        if (element.checked) {
          element.checked = false;
          MENU.checked = true;
        }
        
      }
      for (let index = 0; index < this.classList.length; index++) {
        const element =  this.classList[index];
        
        if (id !== element ) {
          const another = document.querySelector(`.${element}`)
          console.log(another,id,'언아더')
          another.style.display = 'block';
        }
        
      }

    }
  }
  
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Leckerli+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Indie+Flower&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Gloria+Hallelujah&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Gloria+Hallelujah&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Sue+Ellen+Francisco&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Sue+Ellen+Francisco&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Architects+Daughter&family=Nanum+Pen+Script&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Poor+Story&display=swap');
.clickme_text {
  font-family: 'Architects Daughter', cursive;
}
.wrapper_button {
  position: absolute;
  top: 1rem;
  right: 1rem;
  font-family: 'Poor Story', cursive;
  font-size: 2rem;
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
  font-weight: 700;
  letter-spacing: .2px;
  opacity: var(--opacity, 1);
  -webkit-transition: opacity 0.3s var(--timing-function) 0.2s;
  transition: opacity 0.3s var(--timing-function) 0.2s;
  font-family: 'Raleway', sans-serif;
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
  font-weight: 900  !important;
  font-family: 'Raleway', sans-serif;
  
}

.wrapper .text > span::after  {
  color: var(--color-invert);
  -webkit-clip-path: var(--clip-path);
          clip-path: var(--clip-path);
  -webkit-transition: -webkit-clip-path var(--duration) var(--timing-function);
  transition: -webkit-clip-path var(--duration) var(--timing-function);
  transition: clip-path var(--duration) var(--timing-function);
  transition: clip-path var(--duration) var(--timing-function), -webkit-clip-path var(--duration) var(--timing-function);
  position: absolute;
  left: 0;
  font-family: 'Raleway', sans-serif;
}

.wrapper input {
  width: 1000px;
  height: 550px;
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
  color: #A8A8A8    ;
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

/* section2 */


input[type='radio'],
.hide {
    display: none;
}

input[type='radio']:checked + section {
  top: -3rem;
  left: -1rem;
  cursor: pointer;
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
  transform: scale(0);
    text-align: center;
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
  cursor: pointer;
}

.menu {
    display: block;
    position: fixed;
    top: 20px;
    left: 25px;

    z-index: 1000;
    transform: 0deg;
    transition: all 200ms;
    width: 35px;
    height: 4px;
    background: rgba(0,0,0,0.25);
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
  display: none;
}

.menu div label:nth-child(2) {
    display: none;
}

#menu:checked + .container {
  transform:scale(0.6) translate(-2%, -18%);
    transition: all 500ms;
    transform-style: preserve-3d;
}

#menu:checked + .container section {
    cursor: pointer;
    overflow: hidden;
    opacity: 1;
    box-shadow: 0 0 40px rgba(0,0,0,0.25);


}

#menu:checked + .container section h1 {
    transform-style: preserve-3d;
}

#menu:checked + .container + .menu {
    top: 30px;
    left: 20px;
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

#menu:checked + .container + .menu div label:nth-child(4) {
   display: none;
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
</style>