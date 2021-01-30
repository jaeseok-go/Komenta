<template>
    <div id="app">
        사이드바 <br>
        1. Komenta로고 <br>
        <!-- 로그인전 / 로그인시 store에 저장된 값 가져오기 -->
        2. 회색사람 아이콘 / 유저 프로필사진 <br>
        3. 로그인 후 이용해 주세요(로그인으로 가는 a링크) / 유저 닉네임 <br>
        4. 메뉴 / 로그인 안하면로그인페이지로 이동! <br>
        1) Home -> mainpage로 이동 <br>
        2) My StreamingList -> StreamingList로 이동 <br>
        3) VOD -> 카테고리 페이지로 이동 <br>
        4) 마이페이지 -> 마이페이지로 이동 <br>
        5) 내피드 -> myFeed로 이동 <br>
        6) 고객센터 -> 아직 안정함 <br>
        7) 로그아웃 <br>
    </div>
</template>

<script>
export default {
    name: 'asidebar',

    data() {
        return {
            
        };
    },
    mounted() {
        
    },
    methods: {
        
    },
};
</script>

<style scoped>
    #app {
        width: 200px;
        display: inline-block;
        background-color: yellowgreen;
    }
</style>