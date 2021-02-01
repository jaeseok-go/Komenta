<template>
        <!-- 사이드바 <br>
        1. Komenta로고 <br>
        로그인전 / 로그인시 store에 저장된 값 가져오기
        2. 회색사람 아이콘 / 유저 프로필사진 <br>
        3. 로그인 후 이용해 주세요(로그인으로 가는 a링크) / 유저 닉네임 <br>
        4. 메뉴 / 로그인 안하면로그인페이지로 이동! <br>
        1) Home -> mainpage로 이동 <br>
        2) My StreamingList -> StreamingList로 이동 <br>
        3) VOD -> 카테고리 페이지로 이동 <br>
        4) 마이페이지 -> 마이페이지로 이동 <br>
        5) 내피드 -> myFeed로 이동 <br>
        6) 고객센터 -> 아직 안정함 <br>
        7) 로그아웃 <br> -->

    <div id="#app">
        <v-app id="inspire">
            <v-card
            width="256"
            height="70rem"
            tile
        >
            <v-navigation-drawer permanent>
                <h1>Komenta</h1>
                <v-list>
                    <v-list-item>
                        <v-list-item-avatar>
                            <v-img src="https://cdn.vuetifyjs.com/images/john.png"></v-img>
                        </v-list-item-avatar>
                    </v-list-item>
  
                    <v-list-item link>
                        <v-list-item-content>
                            <v-list-item-title class="title">
                                John Leider
                            </v-list-item-title>
                            <v-list-item-subtitle>john@vuetifyjs.com</v-list-item-subtitle>
                        </v-list-item-content>
  
                        <v-list-item-action>
                            <v-icon>mdi-menu-down</v-icon>
                        </v-list-item-action>
                        </v-list-item>
                        </v-list>
                        <v-divider></v-divider>
                        <v-list
                            nav
                            dense
                        >
                        <v-list-item-group
                            v-model="selectedItem"
                            color="primary"
                        >
                        <v-list-item
                        v-for="(item, i) in items"
                        :key="i"
                        >
                        <v-list-item-icon>
                            <v-icon v-text="item.icon"></v-icon>
                        </v-list-item-icon>
  
                        <v-list-item-content>
                            <v-list-item-title v-text="item.text"></v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-navigation-drawer>
    </v-card>
  </v-app>
    </div>

</template>

<script>

export default {
    // el: '#app',

    name: 'asidebar',
    data:() => ({
        selectedItem: 0,
        items: [
        { text: 'Home', icon: 'home' },
        { text: 'My Streaming List', icon: 'album' },
        { text: 'VOD', icon: 'ondemand_video' },
        { text: 'My Feed', icon: 'mdi-check-circle' },
        { text: 'My Page', icon: 'account_circle' },
        { text: '고객센터', icon: 'support_agent' },
        ],
    }),
    mounted() {
        
    },
    methods: {
        
    },
};
</script>

<style scoped>
    /* #app {
        width: 200px;
        display: inline-block;
        background-color: yellowgreen;
    } */
</style>