<template>
  <div class="wrap">
  <div id="app" 
       @keyup.down="selectValue('down')"
       @keyup.up="selectValue('up')">
    <div class="search">
      <input class="s" placeholder="검색어를 입력하세요" 
             @input="searchQuery=event.target.value">
      <ul class="r" tabindex="0" 
          :class="{ show: isActive }"
         @mouseover="removeValue">
        <li tabindex="-1" 
            v-for="(el, index) in filterList" 
            :key="index"
            @click="changeValue(el.name)"
            @keyup.enter="selectValue('enter', el.name)">
          <span>{{ el.name }}</span>
        </li>
      </ul>
      <p>Console: <strong>On</strong></p>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'Search',
   data() {
     return {
       isActive: false,
       searchQuery: '',
       names: names,
     }
  },
  methods: {
    changeValue(str) {
      console.log(`change value: ${str}`);
      this.isActive = false;
      document.querySelector('.s').value = str;
    },
    selectValue(keycode, str) {
      if (this.isActive === true) {
        const hasClass = document.querySelector('.r').classList.contains('key');
        if (keycode === 'down') {
          if (!hasClass) {
            const thisEl = document.querySelectorAll('.r li')[0];
            document.querySelector('.r').classList.add('key');
            thisEl.classList.add('sel');
            thisEl.focus();
          } else {
            const lastEl = document.querySelector('.r li:last-child');
            const thisEl = document.querySelector('.r li.sel');
            const nextEl = thisEl.nextElementSibling;
            if (!lastEl.classList.contains('sel')) {
              thisEl.classList.remove('sel');
              nextEl.classList.add('sel');
              nextEl.focus();
            }
          }
        }
        if (keycode === 'up' && hasClass) {
          const firstEl = document.querySelectorAll('.r li')[0];
          const thisEl = document.querySelector('.r li.sel');
          const prevEl = thisEl.previousElementSibling;
          if (!firstEl.classList.contains('sel')) {
            thisEl.classList.remove('sel');
            prevEl.classList.add('sel');
            prevEl.focus();
          } else {
            document.querySelector('.s').focus();
          }
        }
        if (keycode === 'enter' && hasClass) {
          this.changeValue(str);
        }
      }
    },
    removeValue() {
      if (document.querySelector('.r').classList.contains('key')) {
        document.querySelector('.r').classList.remove('key');
        document.querySelector('.r li.sel').classList.remove('sel');
      }
    },
  },
  computed: {
    filterList() {
      const str = this.searchQuery;
      const reg = /[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9|\s]/.test(str);
      console.log(`typing value: ${str}`);
      if (reg === false && str !== '' && str !== ' ') {
        this.isActive = true;
        return this.names.filter((el) => {
          return el.name.match(str);
        });
      } else {
        this.isActive = false;
      }
    },
  },
}
</script>

<style scoped>
html, body {
  height: 100%;
}
body {
  background-color: #ddd;
  font-size: 14px;
  color: #333;
}
strong {
  font-weight: bold;
}
.wrap {
  display: table;
  padding: 200px 20px;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}
#app {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
  .search {
    position: relative;
    margin: 0 auto;
    width: 100%;
    max-width: 600px;
    .s {
      padding: 10px 20px;
      width: 100%;
      max-width: 600px;
      height: 60px;
      box-sizing: border-box;
      box-shadow: 0 0 3px rgba(#000, 0.2);
      border: 1px solid #888;
      font-size: 16px;
    }
    .r {
      display: none;
      position: absolute;
      left: 0;
      top: 60px;
      width: 100%;
      height: 156px;
      overflow-y: auto;
      &.show {
        display: block;
      }
      li {
        margin-top: -1px;
        padding: 0 20px;
        width: 100%;
        height: 40px;
        background-color: #fff;
        box-sizing: border-box;
        border: 1px solid #888;
        outline: none;
        font-size: 16px;
        line-height: 40px;
        cursor: pointer;
        &:hover, &.sel {
          background-color: darken(#fff, 5%);
        }
      }
    }
    p {
      padding: 10px 0;
      text-align: right;
      font-size: 12px;
    }
  }
}
</style>