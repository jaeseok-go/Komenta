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
$(document).ready(function() {
  var $svg = $(".sidebar"),
      $demo = $(".demo"),
      $path = $(".s-path"),
      $sCont = $(".sidebar-content"),
      $chat = $(".chat"),
      demoTop = $demo.offset().top,
      demoLeft = $demo.offset().left,
      diffX = 0,
      curX = 0,
      finalX = 0,
      frame = 1000 / 60,
      animTime = 600,
      sContTrans = 200,
      animating = false;

  var easings = {
    smallElastic: function(t,b,c,d) {
      var ts = (t/=d)*t;
      var tc = ts*t;
      return b+c*(33*tc*ts + -106*ts*ts + 126*tc + -67*ts + 15*t);
    },
    inCubic: function(t,b,c,d) {
      var tc = (t/=d)*t*t;
      return b+c*(tc);
    }
  };

  function createD(top, ax, dir) {
    return "M0,0 "+top+",0 a"+ax+",250 0 1,"+dir+" 0,500 L0,500";
  }

  var startD = createD(50,0,1),
      midD = createD(125,75,0),
      finalD = createD(200,0,1),
      clickMidD = createD(300,80,0),
      clickMidDRev = createD(200,100,1),
      clickD = createD(300,0,1),
      currentPath = startD;

  function newD(num1, num2) {
    var d = $path.attr("d"),
        num2 = num2 || 250,
        nd = d.replace(/\ba(\d+),(\d+)\b/gi, "a" + num1 + "," + num2);
    return nd;
  }

  function animatePathD(path, d, time, handlers, callback, easingTop, easingX) {
    var steps = Math.floor(time / frame),
        curStep = 0,
        oldArr = currentPath.split(" "),
        newArr = d.split(" "),
        oldLen = oldArr.length,
        newLen = newArr.length,
        oldTop = +oldArr[1].split(",")[0],
        topDiff = +newArr[1].split(",")[0] - oldTop,
        nextTop,
        nextX,
        easingTop = easings[easingTop] || easings.smallElastic,
        easingX = easings[easingX] || easingTop;

    $(document).off("mousedown mouseup");

    function animate() {
      curStep++;
      nextTop = easingTop(curStep, oldTop, topDiff, steps);
      nextX = easingX(curStep, curX, finalX-curX, steps);
      oldArr[1] = nextTop + ",0";
      oldArr[2] = "a" + Math.abs(nextX) + ",250";
      oldArr[4] = (nextX >= 0) ? "1,1" : "1,0";
      $path.attr("d", oldArr.join(" "));
      if (curStep > steps) {
        curX = 0;
        diffX = 0;
        $path.attr("d", d);
        currentPath = d;
        if (handlers) handlers1();
        if (callback) callback();
        return;
      }
      requestAnimationFrame(animate);
    }
    animate();
  }

  function handlers1() {

    $(document).on("mousedown touchstart", ".s-path", function(e) {
      var startX =  e.pageX || e.originalEvent.touches[0].pageX;

      $(document).on("mousemove touchmove", function(e) {
        var x = e.pageX || e.originalEvent.touches[0].pageX;
        diffX = x - startX;
        if (diffX < 0) diffX = 0;
        if (diffX > 300) diffX = 300;
        curX = Math.floor(diffX/2);
        $path.attr("d", newD(curX));
      });
    });

    $(document).on("mouseup touchend", function() {
      $(document).off("mousemove touchmove");
      if (animating) return;
      if (!diffX) return;
      if (diffX < 40) {
        animatePathD($path, newD(0), animTime, true);
      } else {
        animatePathD($path, finalD, animTime, false, function() {
          $sCont.addClass("active");
          setTimeout(function() {
            $(document).on("click", closeSidebar);
          }, sContTrans);
        });
      }
    });

  }

  handlers1();

  function closeSidebar(e) {
    if ($(e.target).closest(".sidebar-content").length ||
        $(e.target).closest(".chat").length) return;
    if (animating) return;
    animating = true;
    $sCont.removeClass("active");
    $chat.removeClass("active");
    $(".cloned").addClass("removed");
    finalX = -75;
    setTimeout(function() {
      animatePathD($path, midD, animTime/3, false, function() {
        $chat.hide();
        $(".cloned").remove();
        finalX = 0;
        curX = -75;
        animatePathD($path, startD, animTime/3*2, true);
        animating = false;
      }, "inCubic");
    }, sContTrans);
    $(document).off("click", closeSidebar);
  }

  function moveImage(that) {
    var $img = $(that).find(".contact__photo"),
        top = $img.offset().top - demoTop,
        left = $img.offset().left - demoLeft,
        $clone = $img.clone().addClass("cloned");

    $clone.css({top: top, left: left});
    $demo.append($clone);
    $clone.css("top");
    $clone.css({top: "1.8rem", left: "25rem"});
  }

  function ripple(elem, e) {
    var elTop = elem.offset().top,
        elLeft = elem.offset().left,
        x = e.pageX - elLeft,
        y = e.pageY - elTop;
    var $ripple = $("<div class='ripple'></div>");
    $ripple.css({top: y, left: x});
    elem.append($ripple);
  }

  $(document).on("click", ".contact", function(e) {
    if (animating) return;
    animating = true;
    $(document).off("click", closeSidebar);
    var that = this,
        name = $(this).find(".contact__name").text(),
        online = $(this).find(".contact__status").hasClass("online");
    $(".chat__name").text(name);
    $(".chat__online").removeClass("active");
    if (online) $(".chat__online").addClass("active");
    ripple($(that),e);
    setTimeout(function() {
      $sCont.removeClass("active");
      moveImage(that);
      finalX = -80;
      setTimeout(function() {
        $(".ripple").remove();
        animatePathD($path, clickMidD, animTime/3, false, function() {
          curX = -80;
          finalX = 0;
          animatePathD($path, clickD, animTime*2/3, true, function() {
            $chat.show();
            $chat.css("top");
            $chat.addClass("active");
            animating = false;
          });
        }, "inCubic");
      }, sContTrans);
    }, sContTrans);
  });

  $(document).on("click", ".chat__back", function() {
    if (animating) return;
    animating = true;
    $chat.removeClass("active");
    $(".cloned").addClass("removed");
    setTimeout(function() {
      $(".cloned").remove();
      $chat.hide();
      finalX = 100;
      animatePathD($path, clickMidDRev, animTime/3, false, function() {
        curX = 100;
        finalX = 0;
        animatePathD($path, finalD, animTime*2/3, true, function() {
          $sCont.addClass("active");
          $(document).on("click", closeSidebar);
          animating = false;
        });
      }, "inCubic");
    }, sContTrans);
  });

  $(window).on("resize", function() {
    demoTop = $demo.offset().top;
    demoLeft = $demo.offset().left;
  });

});
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