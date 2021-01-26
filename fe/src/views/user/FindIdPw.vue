<template>
    <div>
        <router-link :to="{name:'FindId'}">아이디 찾기</router-link> |
        <router-link :to="{name:'FindPw'}">비밀번호 찾기</router-link> |
        <router-view></router-view>
    </div>
</template>

<script>


export default {

}
</script>

<style>

</style>